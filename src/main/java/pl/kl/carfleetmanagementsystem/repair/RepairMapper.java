package pl.kl.carfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleMapper;
import pl.kl.carfleetmanagementsystem.workshop.WorkshopMapper;

@Component
@RequiredArgsConstructor
public class RepairMapper {

    private final WorkshopMapper workshopMapper;
    private final VehicleMapper vehicleMapper;

    public Repair mapRepairRequestToRepair(RepairRequest repairRequest) {
        return Repair.builder()
                .id(repairRequest.getId())
                .leftDate(repairRequest.getLeftDate())
                .leftOdometerStatus(repairRequest.getLeftOdometerStatus())
                .invoiceNumber(repairRequest.getInvoiceNumber())
                .invoiceDate(repairRequest.getInvoiceDate())
                .repairCost(repairRequest.getRepairCost())
                .pickupDate(repairRequest.getPickupDate())
                .comments(repairRequest.getComments())
                .build();
    }

    public RepairResponse mapRepairToRepairResponse(Repair repair) {
        return RepairResponse.builder()
                .id(repair.getId())
                .leftDate(repair.getLeftDate())
                .leftOdometerStatus(repair.getLeftOdometerStatus())
                .invoiceNumber(repair.getInvoiceNumber())
                .invoiceDate(repair.getInvoiceDate())
                .repairCost(repair.getRepairCost())
                .pickupDate(repair.getPickupDate())
                .comments(repair.getComments())
                .workshop(workshopMapper.mapWorkshopToWorkshopResponse(repair.getWorkshop()))
                .vehicle(vehicleMapper.mapVehicleToVehicleResponse(repair.getVehicle()))
                .build();
    }

    public RepairRequest mapRepairToRepairRequest(Repair repair) {
        return RepairRequest.builder()
                .id(repair.getId())
                .leftDate(repair.getLeftDate())
                .leftOdometerStatus(repair.getLeftOdometerStatus())
                .invoiceNumber(repair.getInvoiceNumber())
                .invoiceDate(repair.getInvoiceDate())
                .repairCost(repair.getRepairCost())
                .pickupDate(repair.getPickupDate())
                .comments(repair.getComments())
                .workshopId(repair.getWorkshop().getId())
                .vehicleId(repair.getVehicle().getId())
                .build();
    }
}
