package pl.kl.carfleetmanagementsystem.repair;

import org.springframework.stereotype.Component;

@Component
public class RepairMapper {

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
}
