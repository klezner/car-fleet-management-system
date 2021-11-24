package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import pl.kl.carfleetmanagementsystem.fleetcard.FleetCard;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Vehicle brand cannot be blank")
    private String brand;
    @NotBlank(message = "Vehicle model cannot be blank")
    private String model;
    @NotBlank(message = "Vehicle registration number cannot be blank")
    @Length(min = 3, max = 8, message = "Incorrect vehicle registration number length")
    private String registrationNumber;
    @NotBlank(message = "Vehicle vin number cannot be blank")
    @Length(min = 17, max = 17, message = "Incorrect vehicle vin number length")
    private String vinNumber;
    @NotNull(message = "Vehicle production year cannot be blank")
    @Min(2000)
    @Max(2050)
    private Integer productionYear;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle type cannot be blank")
    private VehicleType type;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(mappedBy = "vehicle")
    private FleetCard fleetCard;

    protected void setActive() {
        status = Status.ACTIVE;
    }

    protected void setInactive() {
        status = Status.INACTIVE;
    }
}
