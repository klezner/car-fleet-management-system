package pl.kl.carfleetmanagementsystem.vehicle;

import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
public class VehicleMapper {

    public Vehicle mapVehicleRequestToVehicle(VehicleRequest vehicleRequest) {
        final Vehicle vehicle = Vehicle.builder()
                .id(vehicleRequest.getId())
                .brand(vehicleRequest.getBrand())
                .model(vehicleRequest.getModel())
                .registrationNumber(vehicleRequest.getRegistrationNumber())
                .vinNumber(vehicleRequest.getVinNumber())
                .productionYear(vehicleRequest.getProductionYear())
                .type(vehicleRequest.getType())
                .build();

        if (vehicleRequest.getStatus() == null || vehicleRequest.getStatus() == Status.ACTIVE) {
            vehicle.setActive();
        } else if (vehicleRequest.getStatus() == Status.INACTIVE) {
            vehicle.setInactive();
        }

        return vehicle;
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
