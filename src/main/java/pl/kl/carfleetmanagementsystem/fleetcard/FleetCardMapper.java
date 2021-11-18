package pl.kl.carfleetmanagementsystem.fleetcard;

import org.springframework.stereotype.Component;

@Component
public class FleetCardMapper {

    public FleetCard mapFleetCardCreateRequestToFleetCard(FleetCardCreateRequest request) {

        return FleetCard.builder()
                .number(request.getNumber())
                .expirationDate(request.getExpirationDate())
                .type(request.getType())
                .build();
    }
}
