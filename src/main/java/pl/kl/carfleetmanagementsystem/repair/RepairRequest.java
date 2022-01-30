package pl.kl.carfleetmanagementsystem.repair;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RepairRequest {
    private Long id;
    @NotNull(message = "Vehicle left date at the workshop cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leftDate;
    @NotNull(message = "Vehicle left odometer status at workshop cannot be blank")
    private Integer leftOdometerStatus;
    @NotBlank(message = "Repair invoice number cannot be blank")
    private String invoiceNumber;
    @NotNull(message = "Repair invoice date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;
    @NotNull(message = "Repair cost cannot be blank")
    @Min(0)
    private BigDecimal repairCost;
    @NotNull(message = "Pickup date from the workshop date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;
    private String comments;
    @NotNull(message = "Workshop is necessary")
    private Long workshopId;
    @NotNull(message = "Vehicle cannot is necessary")
    private Long vehicleId;
}
