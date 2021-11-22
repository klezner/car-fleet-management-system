package pl.kl.carfleetmanagementsystem.vehicle;

import org.springframework.stereotype.Component;

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
                .build();
    }
}
