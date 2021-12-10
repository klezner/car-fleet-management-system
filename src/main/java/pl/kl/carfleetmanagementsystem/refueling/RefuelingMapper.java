package pl.kl.carfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleMapper;

@Component
@RequiredArgsConstructor
public class RefuelingMapper {

    private final VehicleMapper vehicleMapper;

    public Refueling mapRefuelingRequestToRefueling(RefuelingRequest refuelingRequest) {
        return Refueling.builder()
                .id(refuelingRequest.getId())
                .date(refuelingRequest.getDate())
                .odometerStatus(refuelingRequest.getOdometerStatus())
                .fuelAmount(refuelingRequest.getFuelAmount())
                .refuelingCost(refuelingRequest.getRefuelingCost())
                .build();
    }

    public RefuelingResponse mapRefuelingToRefuelingResponse(Refueling refueling) {
        return RefuelingResponse.builder()
                .id(refueling.getId())
                .date(refueling.getDate())
                .odometerStatus(refueling.getOdometerStatus())
                .fuelAmount(refueling.getFuelAmount())
                .refuelingCost(refueling.getRefuelingCost())
                .vehicle(vehicleMapper.mapVehicleToVehicleResponse(refueling.getVehicle()))
                .build();
    }

    public RefuelingRequest mapRefuelingToRefuelingRequest(Refueling refueling) {
        return RefuelingRequest.builder()
                .id(refueling.getId())
                .date(refueling.getDate())
                .odometerStatus(refueling.getOdometerStatus())
                .fuelAmount(refueling.getFuelAmount())
                .refuelingCost(refueling.getRefuelingCost())
                .build();
    }
}
