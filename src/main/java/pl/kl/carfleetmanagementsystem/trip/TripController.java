package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public String getTripHomePage() {
        return "trip/index";
    }

    @GetMapping("/form")
    public String getTripAddForm(Model model) {
        model.addAttribute("trip", new TripRequest());
        return "trip/add-form";
    }

    @PostMapping
    public String submitTripForm(TripRequest tripRequest) {
        tripService.saveTrip(tripRequest);
        return "redirect:/trip/list";
    }

    @GetMapping("/list")
    public String getTripList(Model model) {
        final List<TripResponse> trips = tripService.fetchAllTripsResponses();
        model.addAttribute("trips", trips);
        return "trip/list";
    }
}
