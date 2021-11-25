package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kl.carfleetmanagementsystem.status.SetStatus;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController implements SetStatus {

    private final VehicleService vehicleService;

    @GetMapping("")
    public String getVehicleHomePage() {
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

    @GetMapping("{id}")
    public String getVehicleDetails(Model model, @PathVariable(name = "id") Long id) {
        final VehicleResponse vehicle = vehicleService.fetchVehicleResponse(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle/details";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable(name = "id") Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        vehicleService.setActive(id);
        return "redirect:/vehicle/{id}";
    }

    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        vehicleService.setInactive(id);
        return "redirect:/vehicle/{id}";
    }
}
