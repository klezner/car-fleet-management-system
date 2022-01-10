package pl.kl.carfleetmanagementsystem.trip;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TripRequest {
    private Long id;
    @NotNull(message = "Departure date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @NotNull(message = "Departure date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    @NotNull(message = "Odometer status cannot be blank")
    @Min(0)
    private Integer departureOdometerStatus;
    @NotNull(message = "Odometer status cannot be blank")
    @Min(0)
    private Integer returnOdometerStatus;
    private String comments;
    private Long vehicleId;
}
