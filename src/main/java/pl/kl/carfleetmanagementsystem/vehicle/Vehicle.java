package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import pl.kl.carfleetmanagementsystem.department.Department;
import pl.kl.carfleetmanagementsystem.fleetcard.FleetCard;
import pl.kl.carfleetmanagementsystem.status.Status;
import pl.kl.carfleetmanagementsystem.trip.Trip;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Vehicle brand cannot be blank")
    private String brand;
    @NotBlank(message = "Vehicle model cannot be blank")
    private String model;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Vehicle registration number cannot be blank")
    @Length(min = 3, max = 8, message = "Incorrect vehicle registration number length")
    private String registrationNumber;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Vehicle vin number cannot be blank")
    @Length(min = 17, max = 17, message = "Incorrect vehicle vin number length")
    private String vinNumber;
    @Column(nullable = false)
    @NotNull(message = "Vehicle production year cannot be blank")
    @Min(2000)
    @Max(2050)
    private Integer productionYear;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle type cannot be blank")
    private VehicleType type;
    @OneToOne(mappedBy = "vehicle")
    private FleetCard fleetCard;
    @OneToOne
    private Department department;
    @OneToMany(mappedBy = "vehicle")
    private Set<Trip> trips;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle status cannot be blank")
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

    public void setDepartment(Department department) {
        this.department = department;
    }
}
