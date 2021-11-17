package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FleetCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Fleet card number cannot be blank")
    private String number;
    @NotNull(message = "Fleet card expiration date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    @NotBlank(message = "Fleet card type cannot be blank")
    private String type;
}
