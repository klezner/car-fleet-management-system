package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/list")
    public String getVehicleList(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/list";
    }

    @GetMapping("/edit/{id}")
    public String getVehicleEditForm(Model model, @PathVariable(name = "id") Long id) {
        final VehicleRequest vehicle = vehicleService.fetchVehicleRequest(id);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("types", VehicleType.values());
        return "vehicle/edit-form";
    }
}