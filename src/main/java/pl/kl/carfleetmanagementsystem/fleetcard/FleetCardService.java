package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FleetCardService {

    private final FleetCardMapper fleetCardMapper;
    private final FleetCardRepository fleetCardRepository;

    public void createFleetCard(FleetCardCreateRequest request) {
        final FleetCard fleetCard = fleetCardMapper.mapFleetCardCreateRequestToFleetCard(request);
        fleetCardRepository.save(fleetCard);
    }

    public List<FleetCardResponse> fetchAllFleetCards() {
        final List<FleetCard> fleetCardEntities = fleetCardRepository.findAll();

        return fleetCardEntities.stream()
                .map(fleetCardMapper::mapFleetCardToFleetCardResponse)
                .collect(Collectors.toList());
    }
}
