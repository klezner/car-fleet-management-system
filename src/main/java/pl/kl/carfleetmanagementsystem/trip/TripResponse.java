package pl.kl.carfleetmanagementsystem.trip;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TripResponse {
    private Long id;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Integer departureMeterStatus;
    private Integer returnMeterStatus;
    private String comments;
}
