package pl.kl.carfleetmanagementsystem.company;

import lombok.*;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {
    private Long id;
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
}
