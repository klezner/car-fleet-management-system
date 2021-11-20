package pl.kl.carfleetmanagementsystem.fleetcard;

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
@RequestMapping("/fleetcard")
public class FleetCardController {

    private final FleetCardService fleetCardService;

    @GetMapping("")
    public String getFleetCardHomePage() {
        return "fleetcard/index";
    }

    @GetMapping("/form")
    public String getFleetCardAddForm(Model model) {
        model.addAttribute("fleetCard", new FleetCardRequest());
        return "fleetcard/add-form";
    }

    @PostMapping
    public String submitFleetCardForm(FleetCardRequest fleetCardRequest) {
        fleetCardService.saveFleetCard(fleetCardRequest);
        return "redirect:fleetcard/list";
    }

    @GetMapping("/list")
    public String getFleetCardList(Model model) {
        final List<FleetCardResponse> fleetCards = fleetCardService.fetchAllFleetCardsResponses();
        model.addAttribute("fleetCards", fleetCards);
        return "fleetcard/list";
    }

    @GetMapping("/edit/{id}")
    public String getFleetCardEditForm(Model model, @PathVariable(name = "id") Long id) {
        final FleetCardRequest fleetCard = fleetCardService.fetchFleetCardRequest(id);
        model.addAttribute("fleetCard", fleetCard);
        return "fleetcard/edit-form";
    }

    @GetMapping("/{id}")
    public String getFleetCardDetails(Model model, @PathVariable(name = "id") Long id) {
        final FleetCardResponse fleetCard = fleetCardService.fetchFleetCardResponse(id);
        model.addAttribute("fleetCard", fleetCard);
        return "fleetcard/details";
    }
}
