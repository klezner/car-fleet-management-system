package pl.kl.carfleetmanagementsystem.user;

import lombok.Getter;
import lombok.Setter;
import pl.kl.carfleetmanagementsystem.security.ApplicationUserRole;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ApplicationUserRequest {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "Role cannot be blank")
    private ApplicationUserRole role;
    @NotBlank()
    private boolean isAccountNonExpired;
    @NotBlank()
    private boolean isAccountNonLocked;
    @NotBlank()
    private boolean isCredentialsNonExpired;
    @NotBlank()
    private boolean isEnabled;
}
