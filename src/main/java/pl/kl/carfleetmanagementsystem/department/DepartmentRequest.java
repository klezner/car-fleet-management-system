package pl.kl.carfleetmanagementsystem.department;

import lombok.*;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentRequest {
    private Long id;
    @NotBlank(message = "Department name cannot be blank")
    private String name;
    @NotBlank(message = "Department abbreviation cannot be blank")
    private String abbreviation;
    private String comment;
    @NotNull(message = "Department status cannot be blank")
    private Status status;
}
