package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FleetCardRequest {
    private Long id;
    @NotBlank(message = "Fleet card number cannot be blank")
    private String number;
    @NotNull(message = "Fleet card expiration date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    @NotBlank(message = "Fleet card type cannot be blank")
    private String type;
    private Long vehicleId;
    @NotNull(message = "Fleet card status cannot be blank")
    private Status status;
}
