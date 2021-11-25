package pl.kl.carfleetmanagementsystem.vehicle;

import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
public class VehicleMapper {

    public Vehicle mapVehicleRequestToVehicle(VehicleRequest vehicleRequest) {
        return Vehicle.builder()
                .id(vehicleRequest.getId())
                .brand(vehicleRequest.getBrand())
                .model(vehicleRequest.getModel())
                .registrationNumber(vehicleRequest.getRegistrationNumber())
                .vinNumber(vehicleRequest.getVinNumber())
                .productionYear(vehicleRequest.getProductionYear())
                .type(vehicleRequest.getType())
                .status(setVehicleStatus(vehicleRequest.getStatus()))
                .build();
    }

    private Status setVehicleStatus(Status status) {
        if (status == Status.INACTIVE) {
            return Status.INACTIVE;
        } else {
            return Status.ACTIVE;
        }
    }

    public VehicleResponse mapVehicleToVehicleResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .registrationNumber(vehicle.getRegistrationNumber())
                .vinNumber(vehicle.getVinNumber())
                .productionYear(vehicle.getProductionYear())
                .type(vehicle.getType())
                .status(vehicle.getStatus())
                .build();
    }

    public VehicleRequest mapVehicleToVehicleRequest(Vehicle vehicle) {
        return VehicleRequest.builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .registrationNumber(vehicle.getRegistrationNumber())
                .vinNumber(vehicle.getVinNumber())
                .productionYear(vehicle.getProductionYear())
                .type(vehicle.getType())
                .status(vehicle.getStatus())
                .build();
    }
}
