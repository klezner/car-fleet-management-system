package pl.kl.carfleetmanagementsystem.workshop;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.status.Status;

@Getter
@Builder
public class WorkshopResponse {
    private Long id;
    private String name;
    private String zipCode;
    private String city;
    private String street;
    private String number;
    private Status status;
}
