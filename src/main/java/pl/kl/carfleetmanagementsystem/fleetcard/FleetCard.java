package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.carfleetmanagementsystem.status.Status;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FleetCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Fleet card number cannot be blank")
    private String number;
    @Column(nullable = false)
    @NotNull(message = "Fleet card expiration date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    @Column(nullable = false)
    @NotBlank(message = "Fleet card type cannot be blank")
    private String type;
    @OneToOne
    private Vehicle vehicle;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Fleet card status cannot be blank")
    private Status status;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    @UpdateTimestamp
    public LocalDateTime modified;

    protected void setActive() {
        status = Status.ACTIVE;
    }

    protected void setInactive() {
        status = Status.INACTIVE;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
