package pl.kl.carfleetmanagementsystem.repair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.carfleetmanagementsystem.carworkshop.CarWorkshop;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "Vehicle left date at the workshop cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leftDate;
    @Column(nullable = false)
    @NotNull(message = "Vehicle left odometer status at workshop cannot be blank")
    private Integer leftOdometerStatus;
    @Column(nullable = false)
    @NotBlank(message = "Repair invoice number cannot be blank")
    private String invoiceNumber;
    @Column(nullable = false)
    @NotNull(message = "Repair invoice date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;
    @Column(nullable = false)
    @NotNull(message = "Repair cost cannot be blank")
    @Min(0)
    private BigDecimal repairCost;
    @NotNull(message = "Pickup date from the workshop date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;
    private String comments;
    @JoinColumn(nullable = false)
    @NotNull(message = "Car workshop is necessary")
    @ManyToOne(optional = false)
    private CarWorkshop carWorkshop;
    @JoinColumn(nullable = false)
    @NotNull(message = "Vehicle cannot is necessary")
    @ManyToOne(optional = false)
    private Vehicle vehicle;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    @UpdateTimestamp
    public LocalDateTime modified;

    public void setCarWorkshop(CarWorkshop carWorkshop) {
        this.carWorkshop = carWorkshop;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
