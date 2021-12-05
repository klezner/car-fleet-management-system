package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final VehicleService vehicleService;
    private final TripService tripService;

    @GetMapping
    public String getTripHomePage() {
        return "trip/index";
    }

    @GetMapping("/form")
    public String getTripAddForm(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("trip", new TripRequest());
        return "trip/add-form";
    }

    @PostMapping("/save")
    public String submitTripAddForm(@Valid TripRequest tripRequest) {
        tripService.saveNewTrip(tripRequest);
        return "redirect:/trip/list";
    }

    @GetMapping("/list")
    public String getTripList(Model model) {
        final List<TripResponse> trips = tripService.fetchAllTripsResponses();
        model.addAttribute("trips", trips);
        return "trip/list";
    }

    @GetMapping("/edit/{id}")
    public String getTripEditForm(Model model, @PathVariable(name = "id") Long id) {
        final TripRequest trip = tripService.fetchTripRequest(id);
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("trip", trip);
        return "trip/edit-form";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitTripEditForm(@Valid TripRequest tripRequest) {
        tripService.saveEditedTrip(tripRequest);
        return "redirect:/trip/list";
    }

    @GetMapping("{id}")
    public String getTripDetails(Model model, @PathVariable(name = "id") Long id) {
        final TripResponse trip = tripService.fetchTripResponse(id);
        model.addAttribute("trip", trip);
        return "trip/details";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteTrip(@PathVariable(name = "id") Long id) {
        tripService.deleteTrip(id);
        return "redirect:/trip/list";
    }

    @GetMapping("/last-return-date/vehicle/{id}")
    @ResponseBody
    public LocalDate getTripsLastReturnDateOfVehicle(@PathVariable(name = "id") Long vehicleId) {
        return tripService.fetchTripsLastReturnDateOfVehicle(vehicleId);
    }

    @GetMapping("/last-return-odometer-status/vehicle/{id}")
    @ResponseBody
    public Integer getTripsLastOdometerStatusOfVehicle(@PathVariable(name = "id") Long vehicleId) {
        return tripService.fetchTripsLastOdometerStatusOfVehicle(vehicleId);
    }
}
