package pl.kl.carfleetmanagementsystem.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordChangeRequest {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Old password cannot be blank")
    private String oldPassword;
    @NotBlank(message = "New password cannot be blank")
    private String newPassword;
    @NotBlank(message = "Repeated new password cannot be blank")
    private String repeatedNewPassword;
}
