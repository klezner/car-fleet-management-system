package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FleetCardService {

    private final FleetCardMapper fleetCardMapper;
    private final FleetCardRepository fleetCardRepository;

    public void createFleetCard(FleetCardCreateRequest request) {
        final FleetCard fleetCard = fleetCardMapper.mapFleetCardCreateRequestToFleetCard(request);
        fleetCardRepository.save(fleetCard);
    }
}
