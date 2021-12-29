package pl.kl.carfleetmanagementsystem.refueling;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LastRefuelingDataResponse {
    private LocalDate date;
    private Integer odometerStatus;
}
