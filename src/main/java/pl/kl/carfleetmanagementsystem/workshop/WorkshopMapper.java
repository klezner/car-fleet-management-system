package pl.kl.carfleetmanagementsystem.workshop;

import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
public class WorkshopMapper {

    public Workshop mapWorkshopRequestToWorkshop(WorkshopRequest workshopRequest) {
        return Workshop.builder()
                .id(workshopRequest.getId())
                .name(workshopRequest.getName())
                .zipCode(workshopRequest.getZipCode())
                .city(workshopRequest.getCity())
                .street(workshopRequest.getStreet())
                .number(workshopRequest.getNumber())
                .status(setWorkshopStatus(workshopRequest.getStatus()))
                .build();
    }

    private Status setWorkshopStatus(Status status) {
        if (status == Status.INACTIVE) {
            return Status.INACTIVE;
        } else {
            return Status.ACTIVE;
        }
    }

    public WorkshopResponse mapWorkshopToWorkshopResponse(Workshop workshop) {
        return WorkshopResponse.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .zipCode(workshop.getZipCode())
                .city(workshop.getCity())
                .street(workshop.getStreet())
                .number(workshop.getNumber())
                .status(workshop.getStatus())
                .build();
    }

    public WorkshopRequest mapWorkshopToWorkshopRequest(Workshop workshop) {
        return WorkshopRequest.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .zipCode(workshop.getZipCode())
                .city(workshop.getCity())
                .street(workshop.getStreet())
                .number(workshop.getNumber())
                .status(workshop.getStatus())
                .build();
    }
}
