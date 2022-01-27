package pl.kl.carfleetmanagementsystem.exception;

public class ApplicationUserNotLoggedInException extends RuntimeException {

    public ApplicationUserNotLoggedInException(String message) {
        super(message);
    }
}
