package pl.kl.carfleetmanagementsystem.workshop;

import lombok.*;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkshopRequest {
    private Long id;
    @NotBlank(message = "Workshop name cannot be blank")
    private String name;
    @NotBlank(message = "Zip code cannot be blank")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Incorrect format of zip code. Correct format is: XX-XXX")
    private String zipCode;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotBlank(message = "Street name cannot be blank")
    private String street;
    @NotBlank(message = "Number of building cannot be blank")
    private String number;
    @NotNull(message = "Workshop status cannot be blank")
    private Status status;
}
