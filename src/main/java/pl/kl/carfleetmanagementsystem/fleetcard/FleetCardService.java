package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.validator.DateValidator;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FleetCardService {

    private final VehicleService vehicleService;
    private final FleetCardMapper fleetCardMapper;
    private final FleetCardRepository fleetCardRepository;

    private final LocalDate SYSTEM_START_DATE = LocalDate.of(2020, 1, 1);

    @Transactional
    public void saveNewFleetCard(FleetCardRequest fleetCardRequest) {
        DateValidator.validateFleetCardExpirationDate(fleetCardRequest.getExpirationDate(), SYSTEM_START_DATE);
        final FleetCard fleetCard = fleetCardMapper.mapFleetCardRequestToFleetCard(fleetCardRequest);
        addVehicleToFleetCard(fleetCard, fleetCardRequest.getVehicleId());
        fleetCardRepository.save(fleetCard);
    }

    private void addVehicleToFleetCard(FleetCard fleetCard, Long vehicleId) {
        if (vehicleId != null) {
            final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
            fleetCard.setVehicle(vehicle);
        }
    }

    public List<FleetCardResponse> fetchAllFleetCardsResponses() {
        final List<FleetCard> fleetCardEntities = fetchAllFleetCards();
        return fleetCardEntities.stream()
                .map(fleetCardMapper::mapFleetCardToFleetCardResponse)
                .collect(Collectors.toList());
    }

    private List<FleetCard> fetchAllFleetCards() {
        return fleetCardRepository.findAll();
    }

    public FleetCardRequest fetchFleetCardRequest(Long id) {
        final FleetCard fleetCard = fetchFleetCardById(id);
        return fleetCardMapper.mapFleetCardToFleetCardRequest(fleetCard);
    }

    private FleetCard fetchFleetCardById(Long id) {
        return fleetCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FleetCard with id: " + id + " not found!"));
    }

    public FleetCardResponse fetchFleetCardResponse(Long id) {
        final FleetCard fleetCardEntity = fetchFleetCardById(id);
        return fleetCardMapper.mapFleetCardToFleetCardResponse(fleetCardEntity);
    }

    @Transactional
    public void saveEditedFleetCard(FleetCardRequest fleetCardRequest) {
        final FleetCard fleetCard = fleetCardMapper.mapFleetCardRequestToFleetCard(fleetCardRequest);
        fleetCardRepository.save(fleetCard);
    }

    public void deleteFleetCard(Long id) {
        fleetCardRepository.deleteById(id);
    }

    @Transactional
    public void setActive(Long id) {
        final FleetCard fleetCard = fetchFleetCardById(id);
        fleetCard.setActive();
        fleetCardRepository.save(fleetCard);
    }

    @Transactional
    public void setInactive(Long id) {
        final FleetCard fleetCard = fetchFleetCardById(id);
        fleetCard.setInactive();
        fleetCardRepository.save(fleetCard);
    }
}
