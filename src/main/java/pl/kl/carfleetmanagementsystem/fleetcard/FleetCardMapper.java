package pl.kl.carfleetmanagementsystem.fleetcard;

import org.springframework.stereotype.Component;

@Component
public class FleetCardMapper {

    public FleetCard mapFleetCardRequestToFleetCard(FleetCardRequest fleetCardRequest) {
        return FleetCard.builder()
                .id(fleetCardRequest.getId())
                .number(fleetCardRequest.getNumber())
                .expirationDate(fleetCardRequest.getExpirationDate())
                .type(fleetCardRequest.getType())
                .build();
    }

    public FleetCardRequest mapFleetCardToFleetCardRequest(FleetCard fleetCard) {
        return FleetCardRequest.builder()
                .id(fleetCard.getId())
                .number(fleetCard.getNumber())
                .expirationDate(fleetCard.getExpirationDate())
                .type(fleetCard.getType())
                .status(fleetCard.getStatus())
                .build();
    }

    public FleetCardResponse mapFleetCardToFleetCardResponse(FleetCard fleetCard) {
        return FleetCardResponse.builder()
                .id(fleetCard.getId())
                .number(fleetCard.getNumber())
                .expirationDate(fleetCard.getExpirationDate())
                .type(fleetCard.getType())
                .status(fleetCard.getStatus())
                .build();
    }
}
