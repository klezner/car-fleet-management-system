package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehicleRequest {
    private Long id;
    @NotBlank(message = "Vehicle brand cannot be blank")
    private String brand;
    @NotBlank(message = "Vehicle model cannot be blank")
    private String model;
    @NotBlank(message = "Vehicle registration number cannot be blank")
    @Length(min = 3, max = 8, message = "Incorrect vehicle registration number length")
    private String registrationNumber;
    @NotBlank(message = "Vehicle vin number cannot be blank")
    @Length(min = 17, max = 17, message = "Incorrect vehicle vin number length")
    private String vinNumber;
    @NotNull(message = "Vehicle production year cannot be blank")
    @Min(2000)
    @Max(2050)
    private Integer productionYear;
    @NotNull(message = "Vehicle type cannot be blank")
    private VehicleType type;
}
