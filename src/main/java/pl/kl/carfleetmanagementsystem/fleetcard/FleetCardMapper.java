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

    public FleetCardResponse mapFleetCardToFleetCardResponse(FleetCard fleetCard) {

        return FleetCardResponse.builder()
                .id(fleetCard.getId())
                .number(fleetCard.getNumber())
                .expirationDate(fleetCard.getExpirationDate())
                .type(fleetCard.getType())
                .build();
    }
}
