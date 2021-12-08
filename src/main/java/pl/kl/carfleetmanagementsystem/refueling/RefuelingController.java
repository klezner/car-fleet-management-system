package pl.kl.carfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/refueling")
public class RefuelingController {

    private final VehicleService vehicleService;
    private final RefuelingService refuelingService;

    @GetMapping
    public String getRefuelingHomePage() {
        return "refueling/index";
    }

    @GetMapping("/form")
    public String getRefuelingAddForm(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("refueling", new RefuelingRequest());
        return "refueling/add-form";
    }

    @PostMapping("/save")
    public String submitRefuelingForm(RefuelingRequest refuelingRequest) {
        refuelingService.saveNewRefueling(refuelingRequest);
        return "redirect:/refueling/list";
    }
}
