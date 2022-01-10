package pl.kl.carfleetmanagementsystem.repair;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.carworkshop.CarWorkshopResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class RepairResponse {
    private Long id;
    private LocalDate leftDate;
    private Integer leftOdometerStatus;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private BigDecimal repairCost;
    private LocalDate pickupDate;
    private String comments;
    private CarWorkshopResponse carWorkshop;
    private VehicleResponse vehicle;
}
