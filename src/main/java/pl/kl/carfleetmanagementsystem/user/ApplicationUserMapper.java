package pl.kl.carfleetmanagementsystem.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationUserMapper {

    public ApplicationUserSimpleResponse applicationUserToApplicationUserSimpleResponse(ApplicationUser applicationUser) {
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

    public ApplicationUserFullResponse applicationUserToApplicationUserFullResponse(ApplicationUser applicationUser) {
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


}
