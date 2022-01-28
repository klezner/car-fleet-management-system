package pl.kl.carfleetmanagementsystem.auth;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Column(nullable = false, length = 765)
    @NotBlank(message = "Authorities cannot be blank")
    private String authorities;
    @Column(nullable = false)
    private boolean isAccountNonExpired;
    @Column(nullable = false)
    private boolean isAccountNonLocked;
    @Column(nullable = false)
    private boolean isCredentialsNonExpired;
    @Column(nullable = false)
    private boolean isEnabled;

    public ApplicationUser(String username, String password, Set<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.setAuthorities(authorities);
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.authorities.split(";"))
                .map(authority -> (GrantedAuthority) () -> authority)
                .collect(Collectors.toSet());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(";"));
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnable() {
        isEnabled = true;
    }

    public void setDisable() {
        isEnabled = false;
    }
}
