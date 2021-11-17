package pl.kl.carfleetmanagementsystem.fleetcard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FleetCardController {

    @GetMapping("/fleetcard")
    public String fleetCardHomePage() {
        return "/fleetcard/index";
    }
}
