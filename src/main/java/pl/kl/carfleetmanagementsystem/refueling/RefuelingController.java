package pl.kl.carfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/refueling")
public class RefuelingController {

    private final VehicleService vehicleService;
    private final RefuelingService refuelingService;

    @PreAuthorize("hasAnyAuthority('refueling:read')")
    @GetMapping
    public String getRefuelingHomePage() {
        return "refueling/index";
    }

    @PreAuthorize("hasAuthority('refueling:create')")
    @GetMapping("/add")
    public String getRefuelingAddForm(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("refueling", new RefuelingRequest());
        return "refueling/add-form";
    }

    @PreAuthorize("hasAuthority('refueling:create')")
    @PostMapping("/save")
    public String submitRefuelingAddForm(RefuelingRequest refuelingRequest) {
        refuelingService.saveNewRefueling(refuelingRequest);
        return "redirect:/refueling/list";
    }

    @PreAuthorize("hasAuthority('refueling:read')")
    @GetMapping("/list")
    public String getRefuelingList(Model model) {
        final List<RefuelingResponse> refuelings = refuelingService.fetchAllRefuelingsResponses();
        model.addAttribute("refuelings", refuelings);
        return "/refueling/list";
    }

    @PreAuthorize("hasAuthority('refueling:read')")
    @GetMapping("/{id}")
    public String getRefuelingDetails(Model model, @PathVariable(name = "id") Long id) {
        final RefuelingResponse refueling = refuelingService.fetchRefuelingResponse(id);
        model.addAttribute("refueling", refueling);
        return "refueling/details";
    }

    @PreAuthorize("hasAuthority('refueling:update')")
    @GetMapping("/edit/{id}")
    public String getRefuelingEditForm(Model model, @PathVariable(name = "id") Long id) {
        final RefuelingRequest refueling = refuelingService.fetchRefuelingRequest(id);
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("refueling", refueling);
        return "refueling/edit-form";
    }

    @PreAuthorize("hasAuthority('refueling:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitRefuelingEditForm(RefuelingRequest refuelingRequest) {
        refuelingService.saveEditedRefueling(refuelingRequest);
        return "redirect:/refueling/list";
    }

    @PreAuthorize("hasAuthority('refueling:delete')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteRefueling(@PathVariable(name = "id") Long id) {
        refuelingService.deleteRefueling(id);
        return "redirect:/refueling/list";
    }

    @PreAuthorize("hasAuthority('vehicle:read')")
    @GetMapping("/last-refueling-data/vehicle/{id}")
    @ResponseBody
    public LastRefuelingDataResponse getVehiclesLastRefuelingData(@PathVariable(name = "id") Long vehicleId) {
        return refuelingService.fetchLastRefuelingDataOfVehicle(vehicleId);
    }
}
