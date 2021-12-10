package pl.kl.carfleetmanagementsystem.refueling;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefuelingRequest {
    private Long id;
    @NotNull(message = "Refueling date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "Meter status during refueling cannot be blank")
    private Integer odometerStatus;
    @NotNull(message = "Amount of fuel refueled cannot be blank")
    @Min(0)
    private Double fuelAmount;
    @NotNull(message = "Refueling cost cannot be blank")
    @Min(0)
    private BigDecimal refuelingCost;
    private Long vehicleId;
}
