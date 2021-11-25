package pl.kl.carfleetmanagementsystem.company;

import lombok.*;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {
    private Long id;
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    @NotNull(message = "Company status cannot be blank")
    private Status status;
}
