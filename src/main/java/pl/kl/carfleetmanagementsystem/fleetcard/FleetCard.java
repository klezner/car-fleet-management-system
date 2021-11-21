package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
    @Enumerated(EnumType.STRING)
    private Status status;

    protected void setActive() {
        status = Status.ACTIVE;
    }

    protected void setInactive() {
        status = Status.INACTIVE;
    }
}
