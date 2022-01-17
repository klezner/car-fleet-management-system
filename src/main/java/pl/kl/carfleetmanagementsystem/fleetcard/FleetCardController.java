package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.status.SetStatus;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fleet-card")
public class FleetCardController implements SetStatus {

    private final VehicleService vehicleService;
    private final FleetCardService fleetCardService;

    @PreAuthorize("hasAnyAuthority('fleetcard:read')")
    @GetMapping
    public String getFleetCardHomePage() {
        return "fleetcard/index";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:create')")
    @GetMapping("/form")
    public String getFleetCardAddForm(Model model) {
        List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponsesWithoutFleetCard();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("fleetCard", new FleetCardRequest());
        return "fleetcard/add-form";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:create')")
    @PostMapping("/save")
    public String submitFleetCardAddForm(FleetCardRequest fleetCardRequest) {
        fleetCardService.saveNewFleetCard(fleetCardRequest);
        return "redirect:/fleet-card/list";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:read')")
    @GetMapping("/list")
    public String getFleetCardList(Model model) {
        final List<FleetCardResponse> fleetCards = fleetCardService.fetchAllFleetCardsResponses();
        model.addAttribute("fleetCards", fleetCards);
        return "fleetcard/list";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:update')")
    @GetMapping("/edit/{id}")
    public String getFleetCardEditForm(Model model, @PathVariable(name = "id") Long id) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponsesWithoutFleetCard();
        final FleetCardRequest fleetCard = fleetCardService.fetchFleetCardRequest(id);
        if (fleetCard.getVehicleId() != null) {
            vehicles.add(vehicleService.fetchVehicleResponse(fleetCard.getVehicleId()));
        }
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("fleetCard", fleetCard);
        return "fleetcard/edit-form";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitFleetCardEditForm(FleetCardRequest fleetCard) {
        fleetCardService.saveEditedFleetCard(fleetCard);
        return "redirect:/fleet-card/list";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:read')")
    @GetMapping("/{id}")
    public String getFleetCardDetails(Model model, @PathVariable(name = "id") Long id) {
        final FleetCardResponse fleetCard = fleetCardService.fetchFleetCardResponse(id);
        model.addAttribute("fleetCard", fleetCard);
        return "fleetcard/details";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:delete')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteFleetCard(@PathVariable(name = "id") Long id) {
        fleetCardService.deleteFleetCard(id);
        return "redirect:/fleet-card/list";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:set_status')")
    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        fleetCardService.setActive(id);
        return "redirect:/fleet-card/{id}";
    }

    @PreAuthorize("hasAnyAuthority('fleetcard:set_status')")
    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        fleetCardService.setInactive(id);
        return "redirect:/fleet-card/{id}";
    }
}
