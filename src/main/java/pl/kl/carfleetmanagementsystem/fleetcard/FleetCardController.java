package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public String getFleetCardHomePage() {
        return "fleetcard/index";
    }

    @GetMapping("/form")
    public String getFleetCardAddForm(Model model) {
        List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponsesWithoutFleetCard();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("fleetCard", new FleetCardRequest());
        return "fleetcard/add-form";
    }

    @PostMapping("/save")
    public String submitAddFleetCardForm(FleetCardRequest fleetCardRequest) {
        fleetCardService.saveNewFleetCard(fleetCardRequest);
        return "redirect:/fleet-card/list";
    }

    @GetMapping("/list")
    public String getFleetCardList(Model model) {
        final List<FleetCardResponse> fleetCards = fleetCardService.fetchAllFleetCardsResponses();
        model.addAttribute("fleetCards", fleetCards);
        return "fleetcard/list";
    }

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

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitFleetCardEditForm(FleetCardRequest fleetCard) {
        fleetCardService.saveEditedFleetCard(fleetCard);
        return "redirect:/fleet-card/list";
    }

    @GetMapping("/{id}")
    public String getFleetCardDetails(Model model, @PathVariable(name = "id") Long id) {
        final FleetCardResponse fleetCard = fleetCardService.fetchFleetCardResponse(id);
        model.addAttribute("fleetCard", fleetCard);
        return "fleetcard/details";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteFleetCard(@PathVariable(name = "id") Long id) {
        fleetCardService.deleteFleetCard(id);
        return "redirect:/fleet-card/list";
    }

    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        fleetCardService.setActive(id);
        return "redirect:/fleet-card/{id}";
    }

    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        fleetCardService.setInactive(id);
        return "redirect:/fleet-card/{id}";
    }
}
