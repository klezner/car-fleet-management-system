package pl.kl.carfleetmanagementsystem.trip;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LastTripDataResponse {
    private LocalDate returnDate;
    private Integer returnMeterStatus;
}
