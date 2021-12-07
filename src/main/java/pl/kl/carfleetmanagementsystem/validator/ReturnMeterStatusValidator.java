package pl.kl.carfleetmanagementsystem.validator;

import pl.kl.carfleetmanagementsystem.exception.TripMeterStatusException;

public class ReturnMeterStatusValidator {

    public static boolean validateMeterStatusOnTripCreate(Integer departureMeterStatus, Integer returnMeterStatus, Integer lastReturnMeterStatus) {
        validateDepartureMeterStatus(departureMeterStatus, lastReturnMeterStatus);
        validateReturnMeterStatus(departureMeterStatus, returnMeterStatus);

        return true;
    }

    public static boolean validateMeterStatusOnTripEdit(Integer departureMeterStatus, Integer returnMeterStatus) {
        validateReturnMeterStatus(departureMeterStatus, returnMeterStatus);

        return true;
    }

    private static boolean validateDepartureMeterStatus(Integer departureMeterStatus, Integer lastReturnMeterStatus) {
        if (departureMeterStatus < lastReturnMeterStatus) {
            throw new TripMeterStatusException("Incorrect departure meter status. Departure meter status should be greater or equal: " + lastReturnMeterStatus);
        }
        return true;
    }

    private static boolean validateReturnMeterStatus(Integer departureMeterStatus, Integer returnMeterStatus) {
        if (returnMeterStatus < departureMeterStatus) {
            throw new TripMeterStatusException("Incorrect return meter status. Return meter status should be less or equal to: " + returnMeterStatus);
        }
        return true;
    }
}
