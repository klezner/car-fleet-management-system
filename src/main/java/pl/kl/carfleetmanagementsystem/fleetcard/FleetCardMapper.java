package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleMapper;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;

@Component
@RequiredArgsConstructor
public class FleetCardMapper {

    private final VehicleMapper vehicleMapper;

    public FleetCard mapFleetCardRequestToFleetCard(FleetCardRequest fleetCardRequest) {
        return FleetCard.builder()
                .id(fleetCardRequest.getId())
                .number(fleetCardRequest.getNumber())
                .expirationDate(fleetCardRequest.getExpirationDate())
                .type(fleetCardRequest.getType())
                .status(setFleetCardStatus(fleetCardRequest.getStatus()))
                .build();
    }

    private Status setFleetCardStatus(Status status) {
        if (status == Status.INACTIVE) {
            return Status.INACTIVE;
        } else {
            return Status.ACTIVE;
        }
    }

    public FleetCardRequest mapFleetCardToFleetCardRequest(FleetCard fleetCard) {
        return FleetCardRequest.builder()
                .id(fleetCard.getId())
                .number(fleetCard.getNumber())
                .expirationDate(fleetCard.getExpirationDate())
                .type(fleetCard.getType())
                .status(fleetCard.getStatus())
                .vehicleId(setVehicleId(fleetCard.getVehicle()))
                .build();
    }

    private Long setVehicleId(Vehicle vehicle) {
        if (vehicle != null) {
            return vehicle.getId();
        } else {
            return null;
        }
    }

    public FleetCardResponse mapFleetCardToFleetCardResponse(FleetCard fleetCard) {
        VehicleResponse vehicle;
        if (fleetCard.getVehicle() != null) {
            vehicle = vehicleMapper.mapVehicleToVehicleResponse(fleetCard.getVehicle());
        } else {
            vehicle = null;
        }

        return FleetCardResponse.builder()
                .id(fleetCard.getId())
                .number(fleetCard.getNumber())
                .expirationDate(fleetCard.getExpirationDate())
                .type(fleetCard.getType())
                .status(fleetCard.getStatus())
                .vehicle(vehicle)
                .build();
    }
}
