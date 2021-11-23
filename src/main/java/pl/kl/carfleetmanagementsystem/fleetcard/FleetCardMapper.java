package pl.kl.carfleetmanagementsystem.fleetcard;

import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
public class FleetCardMapper {

    public FleetCard mapFleetCardRequestToFleetCard(FleetCardRequest fleetCardRequest) {
        final FleetCard fleetCard = FleetCard.builder()
                .id(fleetCardRequest.getId())
                .number(fleetCardRequest.getNumber())
                .expirationDate(fleetCardRequest.getExpirationDate())
                .type(fleetCardRequest.getType())
                .build();

        if (fleetCardRequest.getStatus() == null || fleetCardRequest.getStatus() == Status.ACTIVE) {
            fleetCard.setActive();
        } else if (fleetCardRequest.getStatus() == Status.INACTIVE) {
            fleetCard.setInactive();
        }

        return fleetCard;
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
