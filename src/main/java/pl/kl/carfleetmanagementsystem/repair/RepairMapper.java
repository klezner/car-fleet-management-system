package pl.kl.carfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.carworkshop.CarWorkshopMapper;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleMapper;

@Component
@RequiredArgsConstructor
public class RepairMapper {

    private final CarWorkshopMapper carWorkshopMapper;
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
                .carWorkshop(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(repair.getCarWorkshop()))
                .vehicle(vehicleMapper.mapVehicleToVehicleResponse(repair.getVehicle()))
                .build();
    }
}
