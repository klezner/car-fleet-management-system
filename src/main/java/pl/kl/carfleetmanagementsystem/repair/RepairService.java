package pl.kl.carfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.validator.DateValidator;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;
import pl.kl.carfleetmanagementsystem.workshop.Workshop;
import pl.kl.carfleetmanagementsystem.workshop.WorkshopService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairService {

    private final WorkshopService workshopService;
    private final VehicleService vehicleService;
    private final RepairMapper repairMapper;
    private final RepairRepository repairRepository;

    private final Integer VEHICLE_START_MILEAGE = 1;
    private final LocalDate SYSTEM_START_DATE = LocalDate.of(2020, 1, 1);

    @Transactional
    public void saveNewRepair(RepairRequest repairRequest) {
        DateValidator.validateRepairDatesOnRepairCreate(repairRequest.getLeftDate(), repairRequest.getPickupDate(), repairRequest.getInvoiceDate(), SYSTEM_START_DATE);
        final Repair repair = repairMapper.mapRepairRequestToRepair(repairRequest);
        addVehicleToRepair(repair, repairRequest.getVehicleId());
        addWorkshopToRepair(repair, repairRequest.getWorkshopId());
        repairRepository.save(repair);
    }

    private void addWorkshopToRepair(Repair repair, Long carWorkshopId) {
        final Workshop workshop = workshopService.fetchWorkshopById(carWorkshopId);
        repair.setWorkshop(workshop);
    }

    private void addVehicleToRepair(Repair repair, Long vehicleId) {
        final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
        repair.setVehicle(vehicle);
    }

    public List<RepairResponse> fetchAllRepairsResponses() {
        final List<Repair> repairEntities = fetchAllRepairs();
        return repairEntities.stream()
                .map(repairMapper::mapRepairToRepairResponse)
                .collect(Collectors.toList());
    }

    private List<Repair> fetchAllRepairs() {
        return repairRepository.findAll();
    }

    public RepairResponse fetchRepairResponse(Long id) {
        final Repair repair = fetchRepairById(id);
        return repairMapper.mapRepairToRepairResponse(repair);
    }

    private Repair fetchRepairById(Long id) {
        return repairRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Repair with id: " + id + " not found!"));
    }

    public RepairRequest fetchRepairRequest(Long id) {
        final Repair repair = fetchRepairById(id);
        return repairMapper.mapRepairToRepairRequest(repair);
    }

    @Transactional
    public void saveEditedRepair(RepairRequest repairRequest) {
        final Repair repair = repairMapper.mapRepairRequestToRepair(repairRequest);
        addWorkshopToRepair(repair, repairRequest.getWorkshopId());
        addVehicleToRepair(repair, repairRequest.getVehicleId());
        repairRepository.save(repair);
    }

    public void deleteRepair(Long id) {
        repairRepository.deleteById(id);
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
