package pl.kl.carfleetmanagementsystem.validator;

import pl.kl.carfleetmanagementsystem.exception.TripOdometerStatusException;

public class ReturnOdometerStatusValidator {

    public static boolean validateOdometerStatusOnTripCreate(Integer departureOdometerStatus, Integer returnOdometerStatus, Integer lastReturnOdometerStatus) {
        validateDepartureOdometerStatus(departureOdometerStatus, lastReturnOdometerStatus);
        validateReturnOdometerStatus(departureOdometerStatus, returnOdometerStatus);

        return true;
    }

    public static boolean validateOdometerStatusOnTripEdit(Integer departureOdometerStatus, Integer returnOdometerStatus) {
        validateReturnOdometerStatus(departureOdometerStatus, returnOdometerStatus);

        return true;
    }

    private static boolean validateDepartureOdometerStatus(Integer departureOdometerStatus, Integer lastReturnOdometerStatus) {
        if (departureOdometerStatus < lastReturnOdometerStatus) {
            throw new TripOdometerStatusException("Incorrect departure odometer status. Departure odometer status should be greater or equal: " + lastReturnOdometerStatus);
        }
        return true;
    }

    private static boolean validateReturnOdometerStatus(Integer departureOdometerStatus, Integer returnOdometerStatus) {
        if (returnOdometerStatus < departureOdometerStatus) {
            throw new TripOdometerStatusException("Incorrect return odometer status. Return odometer status should be less or equal to: " + returnOdometerStatus);
        }
        return true;
    }
}
