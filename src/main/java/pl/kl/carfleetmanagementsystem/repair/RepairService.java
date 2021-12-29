package pl.kl.carfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.carworkshop.CarWorkshop;
import pl.kl.carfleetmanagementsystem.carworkshop.CarWorkshopService;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RepairService {

    private final CarWorkshopService carWorkshopService;
    private final VehicleService vehicleService;
    private final RepairMapper repairMapper;
    private final RepairRepository repairRepository;

    private final Integer VEHICLE_START_MILEAGE = 1;
    private final LocalDate SYSTEM_START_DATE = LocalDate.of(2020, 1, 1);

    @Transactional
    public void saveNewRepair(RepairRequest repairRequest) {
        final Repair repair = repairMapper.mapRepairRequestToRepair(repairRequest);
        addVehicleToRepair(repair, repairRequest.getVehicleId());
        addCarWorkshopToRepair(repair, repairRequest.getCarWorkshopId());
        repairRepository.save(repair);
    }

    private void addCarWorkshopToRepair(Repair repair, Long carWorkshopId) {
        final CarWorkshop carWorkshop = carWorkshopService.fetchCarWorkshopById(carWorkshopId);
        repair.setCarWorkshop(carWorkshop);
    }

    private void addVehicleToRepair(Repair repair, Long vehicleId) {
        final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
        repair.setVehicle(vehicle);
    }

    public LastRepairDataResponse fetchLastRepairDataOfVehicle(Long vehicleId) {
        final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
        final Repair repair = repairRepository.findTopByVehicleOrderByLeftOdometerStatusDesc(vehicle);
        if (repair != null) {
            return LastRepairDataResponse.builder()
                    .pickupDate(repair.getPickupDate())
                    .leftOdometerStatus(repair.getLeftOdometerStatus())
                    .build();
        } else {
            return LastRepairDataResponse.builder()
                    .pickupDate(SYSTEM_START_DATE)
                    .leftOdometerStatus(VEHICLE_START_MILEAGE)
                    .build();
        }
    }
}
