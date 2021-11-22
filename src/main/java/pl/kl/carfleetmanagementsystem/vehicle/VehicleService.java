package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VehicleService {

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository cardRepository;

    @Transactional
    public void saveVehicle(VehicleRequest vehicleRequest) {
        final Vehicle vehicle = vehicleMapper.mapVehicleRequestToVehicle(vehicleRequest);
        cardRepository.save(vehicle);
    }
}
