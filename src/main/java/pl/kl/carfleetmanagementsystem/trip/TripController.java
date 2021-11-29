package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {

    @GetMapping
    public String getTripHomePage() {
        return "trip/index";
    }
}
