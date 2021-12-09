package pl.kl.carfleetmanagementsystem.refueling;

import org.springframework.stereotype.Component;

@Component
public class RefuelingMapper {

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
                .build();
    }
}
