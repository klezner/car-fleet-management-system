package pl.kl.carfleetmanagementsystem.trip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "Departure date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @Column(nullable = false)
    @NotNull(message = "Departure date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    @Column(nullable = false)
    @NotNull(message = "Meter status cannot be blank")
    @Min(0)
    private Integer departureOdometerStatus;
    @Column(nullable = false)
    @NotNull(message = "Meter status cannot be blank")
    @Min(0)
    private Integer returnOdometerStatus;
    @Formula("return_meter_status - departure_meter_status")
    private Integer distance;
    private String comments;
    @JoinColumn(nullable = false)
    @NotNull(message = "Vehicle cannot be blank")
    @ManyToOne(optional = false)
    private Vehicle vehicle;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    @UpdateTimestamp
    public LocalDateTime modified;

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
