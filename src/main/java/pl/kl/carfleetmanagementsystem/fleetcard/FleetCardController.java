package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kl.carfleetmanagementsystem.status.SetStatus;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fleetcard")
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

    @PostMapping
    public String submitAddOrEditFleetCardForm(@Valid FleetCardRequest fleetCardRequest) {
        fleetCardService.saveFleetCard(fleetCardRequest);
        return "redirect:/fleetcard/list";
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

    @GetMapping("/{id}")
    public String getFleetCardDetails(Model model, @PathVariable(name = "id") Long id) {
        final FleetCardResponse fleetCard = fleetCardService.fetchFleetCardResponse(id);
        model.addAttribute("fleetCard", fleetCard);
        return "fleetcard/details";
    }

    @GetMapping("/delete/{id}")
    public String deleteFleetCard(@PathVariable(name = "id") Long id) {
        fleetCardService.deleteFleetCard(id);
        return "redirect:/fleetcard/list";
    }

    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        fleetCardService.setActive(id);
        return "redirect:/fleetcard/{id}";
    }

    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        fleetCardService.setInactive(id);
        return "redirect:/fleetcard/{id}";
    }
}
