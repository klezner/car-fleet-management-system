package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.status.Status;

@Getter
@Builder
public class VehicleResponse {
    private Long id;
    private String brand;
    private String model;
    private String registrationNumber;
    private String vinNumber;
    private Integer productionYear;
    private VehicleType type;
    private Status status;
}
