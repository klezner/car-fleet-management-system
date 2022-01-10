package pl.kl.carfleetmanagementsystem.repair;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LastRepairDataResponse {
    private LocalDate pickupDate;
    private Integer leftOdometerStatus;
}
