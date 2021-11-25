package pl.kl.carfleetmanagementsystem.department;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.status.Status;

@Getter
@Builder
public class DepartmentResponse {
    private Long id;
    private String name;
    private String abbreviation;
    private String comment;
    private Status status;
}
