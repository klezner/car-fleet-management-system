package pl.kl.carfleetmanagementsystem.company;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.status.Status;

@Getter
@Builder
public class CompanyResponse {
    private Long id;
    private String name;
    private Status status;
}
