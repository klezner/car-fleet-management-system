package pl.kl.carfleetmanagementsystem.validator;

import pl.kl.carfleetmanagementsystem.exception.PasswordsNotEqualException;

public class PasswordValidator {

    public static boolean validatePasswordChange(String oldPassword, String newPassword, String newPasswordConfirm) {
        validateNewPassword(newPassword, newPasswordConfirm);
        validateFormPasswords(oldPassword, newPassword);

        return true;
    }

    private static boolean validateNewPassword(String newPassword, String newPasswordConfirm) {
        if (!newPassword.equals(newPasswordConfirm)) {
            throw new PasswordsNotEqualException("New password is not equal to confirmed password");
        }
        return true;
    }

    private static boolean validateFormPasswords(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            throw new PasswordsNotEqualException("New password is not equal to confirmed password");
        }
        return true;
    }
}
