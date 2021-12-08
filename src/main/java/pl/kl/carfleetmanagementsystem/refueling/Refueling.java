package pl.kl.carfleetmanagementsystem.refueling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Refueling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "Refueling date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(nullable = false)
    @NotNull(message = "Meter status during refueling cannot be blank")
    private Integer odometerStatus;
    @Column(nullable = false)
    @NotNull(message = "Amount of fuel refueled cannot be blank")
    @Min(0)
    private Double fuelAmount;
    @Column(nullable = false)
    @NotNull(message = "Refueling cost cannot be blank")
    @Min(0)
    private BigDecimal refuelingCost;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    @UpdateTimestamp
    public LocalDateTime modified;
}
