package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VehicleService {

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;

    @Transactional
    public void saveVehicle(VehicleRequest vehicleRequest) {
        final Vehicle vehicle = vehicleMapper.mapVehicleRequestToVehicle(vehicleRequest);
        vehicleRepository.save(vehicle);
    }

    public List<VehicleResponse> fetchAllVehiclesResponses() {
        final List<Vehicle> vehicleEntites = fetchAllVehicles();
        return vehicleEntites.stream()
                .map(vehicleMapper::mapVehicleToVehicleResponse)
                .collect(Collectors.toList());
    }

    private List<Vehicle> fetchAllVehicles() {
        return vehicleRepository.findAll();
    }

    public VehicleRequest fetchVehicleRequest(Long id) {
        final Vehicle vehicle = fetchVehicleById(id);
        return vehicleMapper.mapVehicleToVehicleRequest(vehicle);
    }

    private Vehicle fetchVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with id: " + id + " not found!"));
    }
}
