package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class FleetCardCreateRequest {
        @NotBlank(message = "Fleet card number cannot be blank")
        private String number;
        @NotNull(message = "Fleet card expiration date cannot be blank")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate expirationDate;
        @NotBlank(message = "Fleet card type cannot be blank")
        private String type;
}
