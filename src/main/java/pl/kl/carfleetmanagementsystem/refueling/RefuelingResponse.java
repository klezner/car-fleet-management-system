package pl.kl.carfleetmanagementsystem.refueling;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class RefuelingResponse {
    private Long id;
    private LocalDate date;
    private Integer odometerStatus;
    private Double fuelAmount;
    private BigDecimal refuelingCost;
}
