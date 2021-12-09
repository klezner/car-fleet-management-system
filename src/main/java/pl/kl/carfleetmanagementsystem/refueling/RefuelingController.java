package pl.kl.carfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String submitRefuelingAddForm(RefuelingRequest refuelingRequest) {
        refuelingService.saveNewRefueling(refuelingRequest);
        return "redirect:/refueling/list";
    }

    @GetMapping("/list")
    public String getRefuelingList(Model model) {
        final List<RefuelingResponse> refuelings = refuelingService.getchAllRefuelingsResponses();
        model.addAttribute("refuelings", refuelings);
        return "/refueling/list";
    }

    @GetMapping("/{id}")
    public String getRefuelingDetails(Model model, @PathVariable(name = "id") Long id) {
        final RefuelingResponse refueling = refuelingService.fetchRefuelingResponse(id);
        model.addAttribute("refueling", refueling);
        return "refueling/details";
    }

    @GetMapping("/edit/{id}")
    public String getRefuelingEditForm(Model model, @PathVariable(name = "id") Long id) {
        final RefuelingRequest refueling = refuelingService.fetchRefuelingRequest(id);
        model.addAttribute("refueling", refueling);
        return "refueling/edit-form";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitRefuelingEditForm(RefuelingRequest refuelingRequest) {
        refuelingService.saveEditedRefueling(refuelingRequest);
        return "redirect:/refueling/list";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteRefueling(@PathVariable(name = "id") Long id) {
        refuelingService.deleteRefueling(id);
        return "redirect:/refueling/list";
    }
}
