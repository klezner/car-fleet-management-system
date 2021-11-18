package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getFleetCardForm(Model model) {
        model.addAttribute("fleetCardCreateRequest", new FleetCardCreateRequest());
        return "fleetcard/form";
    }

    @PostMapping
    public String submitFleetCard(FleetCardCreateRequest fleetCardCreateRequest) {
        fleetCardService.createFleetCard(fleetCardCreateRequest);
        return "redirect:fleetcard/list";
    }

    @GetMapping("/list")
    public String getFleetCardList(Model model) {
        List<FleetCardResponse> fleetCards = fleetCardService.fetchAllFleetCards();
        model.addAttribute("fleetCards", fleetCards);
        return "fleetcard/list";
    }
}
