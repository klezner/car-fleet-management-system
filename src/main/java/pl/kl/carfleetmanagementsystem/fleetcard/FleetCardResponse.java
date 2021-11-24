package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.status.Status;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;

import java.time.LocalDate;

@Getter
@Builder
public class FleetCardResponse {
    private Long id;
    private String number;
    private LocalDate expirationDate;
    private String type;
    private Status status;
    private VehicleResponse vehicle;
}
