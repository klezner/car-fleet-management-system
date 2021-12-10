package pl.kl.carfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefuelingService {

    private final VehicleService vehicleService;
    private final RefuelingMapper refuelingMapper;
    private final RefuelingRepository refuelingRepository;

    private final Integer VEHICLE_START_MILEAGE = 1;
    private final LocalDate SYSTEM_START_DATE = LocalDate.of(2020, 1, 1);

    @Transactional
    public void saveNewRefueling(RefuelingRequest refuelingRequest) {
        final Refueling refueling = refuelingMapper.mapRefuelingRequestToRefueling(refuelingRequest);
        addVehicleToRefueling(refueling, refuelingRequest.getVehicleId());
        refuelingRepository.save(refueling);
    }

    private void addVehicleToRefueling(Refueling refueling, Long vehicleId) {
        final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
        refueling.setVehicle(vehicle);
    }

    public List<RefuelingResponse> fetchAllRefuelingsResponses() {
        final List<Refueling> refuelingEntities = fetchAllRefuelings();
        return refuelingEntities.stream()
                .map(refuelingMapper::mapRefuelingToRefuelingResponse)
                .collect(Collectors.toList());
    }

    private List<Refueling> fetchAllRefuelings() {
        return refuelingRepository.findAll();
    }

    public RefuelingResponse fetchRefuelingResponse(Long id) {
        final Refueling refueling = fetchRefuelingById(id);
        return refuelingMapper.mapRefuelingToRefuelingResponse(refueling);
    }

    private Refueling fetchRefuelingById(Long id) {
        return refuelingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Refueling with id: " + id + " not found!"));
    }

    public RefuelingRequest fetchRefuelingRequest(Long id) {
        final Refueling refueling = fetchRefuelingById(id);
        return refuelingMapper.mapRefuelingToRefuelingRequest(refueling);
    }

    @Transactional
    public void saveEditedRefueling(RefuelingRequest refuelingRequest) {
        final Refueling refueling = refuelingMapper.mapRefuelingRequestToRefueling(refuelingRequest);
        addVehicleToRefueling(refueling, refuelingRequest.getVehicleId());
        refuelingRepository.save(refueling);
    }

    public void deleteRefueling(Long id) {
        refuelingRepository.deleteById(id);
    }

    public LastRefuelingDataResponse fetchLastRefuelingDataOfVehicle(Long vehicleId) {
        final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
        final Refueling refueling = refuelingRepository.findTopByVehicleOrderByOdometerStatusDesc(vehicle);
        if (refueling != null) {
            return LastRefuelingDataResponse.builder()
                    .date(refueling.getDate())
                    .odometerStatus(refueling.getOdometerStatus())
                    .build();
        } else {
            return LastRefuelingDataResponse.builder()
                    .date(SYSTEM_START_DATE)
                    .odometerStatus(VEHICLE_START_MILEAGE)
                    .build();
        }
    }
}
