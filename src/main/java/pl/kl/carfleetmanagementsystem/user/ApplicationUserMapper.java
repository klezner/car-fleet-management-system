package pl.kl.carfleetmanagementsystem.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApplicationUserMapper {

    private final PasswordEncoder passwordEncoder;

    public ApplicationUserSimpleResponse mapApplicationUserToApplicationUserSimpleResponse(ApplicationUser applicationUser) {
        return ApplicationUserSimpleResponse.builder()
                .id(applicationUser.getId())
                .username(applicationUser.getUsername())
                .role(getApplicationUserRole(applicationUser.getAuthorities()))
                .isAccountNonExpired(applicationUser.isAccountNonExpired())
                .isAccountNonLocked(applicationUser.isAccountNonLocked())
                .isCredentialsNonExpired(applicationUser.isCredentialsNonExpired())
                .isEnabled(applicationUser.isEnabled())
                .build();
    }

    public ApplicationUserFullResponse mapApplicationUserToApplicationUserFullResponse(ApplicationUser applicationUser) {
        return ApplicationUserFullResponse.builder()
                .id(applicationUser.getId())
                .username(applicationUser.getUsername())
                .role(getApplicationUserRole(applicationUser.getAuthorities()))
                .permissions(getApplicationUserPermissions(applicationUser.getAuthorities()))
                .isAccountNonExpired(applicationUser.isAccountNonExpired())
                .isAccountNonLocked(applicationUser.isAccountNonLocked())
                .isCredentialsNonExpired(applicationUser.isCredentialsNonExpired())
                .isEnabled(applicationUser.isEnabled())
                .build();
    }

    private List<String> getApplicationUserPermissions(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .filter(authority -> !authority.getAuthority().startsWith("ROLE_"))
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
    }

    private String getApplicationUserRole(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .findFirst()
                .orElse("Unknown Role");
    }


    public ApplicationUser mapApplicationUserRequestToApplicationUser(ApplicationUserRequest applicationUserRequest) {
        final ApplicationUser applicationUser = ApplicationUser.builder()
                .username(applicationUserRequest.getUsername())
                .password(passwordEncoder.encode(applicationUserRequest.getPassword()))
                .isEnabled(applicationUserRequest.isEnabled())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();

        applicationUser.setAuthorities(applicationUserRequest.getRole().getGrantedAuthorities());

        return applicationUser;
    }
}
