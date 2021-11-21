package pl.kl.carfleetmanagementsystem.validator;

import pl.kl.carfleetmanagementsystem.exception.FleetCardExpirationDateException;

import java.time.LocalDate;

public class DateValidator {

    public static boolean validateFleetCardExpirationDate(LocalDate expirationDate, LocalDate systemStartDate) {
        if (expirationDate.isBefore(systemStartDate)) {
            throw new FleetCardExpirationDateException("Incorrect expiration date. Expiration date should be after: " + systemStartDate.toString());
        }
        return true;
    }
}
