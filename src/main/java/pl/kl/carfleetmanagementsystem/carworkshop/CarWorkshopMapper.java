package pl.kl.carfleetmanagementsystem.carworkshop;

import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
public class CarWorkshopMapper {

    public CarWorkshop mapCarWorkshopRequestToCarWorkshop(CarWorkshopRequest carWorkshopRequest) {
        return CarWorkshop.builder()
                .name(carWorkshopRequest.getName())
                .zipCode(carWorkshopRequest.getZipCode())
                .city(carWorkshopRequest.getCity())
                .street(carWorkshopRequest.getStreet())
                .number(carWorkshopRequest.getNumber())
                .status(setCarWorkshopStatus(carWorkshopRequest.getStatus()))
                .build();
    }

    private Status setCarWorkshopStatus(Status status) {
        if (status == Status.INACTIVE) {
            return Status.INACTIVE;
        } else {
            return Status.ACTIVE;
        }
    }

    public CarWorkshopResponse mapCarWorkshopToCarWorkshopResponse(CarWorkshop carWorkshop) {
        return CarWorkshopResponse.builder()
                .id(carWorkshop.getId())
                .name(carWorkshop.getName())
                .zipCode(carWorkshop.getZipCode())
                .city(carWorkshop.getCity())
                .street(carWorkshop.getStreet())
                .number(carWorkshop.getNumber())
                .status(carWorkshop.getStatus())
                .build();
    }
}
