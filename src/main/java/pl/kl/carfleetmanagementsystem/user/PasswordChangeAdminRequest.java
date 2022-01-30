package pl.kl.carfleetmanagementsystem.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordChangeAdminRequest {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "New password cannot be blank")
    private String newPassword;
}
