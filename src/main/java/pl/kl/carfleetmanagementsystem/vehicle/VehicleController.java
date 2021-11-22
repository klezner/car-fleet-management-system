package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("")
    public String getCarHomePage() {
        return "vehicle/index";
    }

    @GetMapping("/form")
    public String getVehicleAddForm(Model model) {
        model.addAttribute("vehicle", new VehicleRequest());
        model.addAttribute("types", VehicleType.values());
        return "vehicle/add-form";
    }

    @PostMapping
    public String submitVehicleForm(VehicleRequest vehicleRequest) {
        vehicleService.saveVehicle(vehicleRequest);
        return "redirect:/vehicle/list";
    }
}
