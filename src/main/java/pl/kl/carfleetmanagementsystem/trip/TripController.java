package pl.kl.carfleetmanagementsystem.trip;

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
@RequestMapping("/trip")
public class TripController {

    private final VehicleService vehicleService;
    private final TripService tripService;

    @PreAuthorize("hasAnyAuthority('trip:read')")
    @GetMapping
    public String getTripHomePage() {
        return "trip/index";
    }

    @PreAuthorize("hasAnyAuthority('trip:create')")
    @GetMapping("/add")
    public String getTripAddForm(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("trip", new TripRequest());
        return "trip/add-form";
    }

    @PreAuthorize("hasAnyAuthority('trip:create')")
    @PostMapping("/save")
    public String submitTripAddForm(TripRequest tripRequest) {
        tripService.saveNewTrip(tripRequest);
        return "redirect:/trip/list";
    }

    @PreAuthorize("hasAnyAuthority('trip:read')")
    @GetMapping("/list")
    public String getTripList(Model model) {
        final List<TripResponse> trips = tripService.fetchAllTripsResponses();
        model.addAttribute("trips", trips);
        return "trip/list";
    }

    @PreAuthorize("hasAnyAuthority('trip:update')")
    @GetMapping("/edit/{id}")
    public String getTripEditForm(Model model, @PathVariable(name = "id") Long id) {
        final TripRequest trip = tripService.fetchTripRequest(id);
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("trip", trip);
        return "trip/edit-form";
    }

    @PreAuthorize("hasAnyAuthority('trip:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitTripEditForm(TripRequest tripRequest) {
        tripService.saveEditedTrip(tripRequest);
        return "redirect:/trip/list";
    }

    @PreAuthorize("hasAnyAuthority('trip:read')")
    @GetMapping("{id}")
    public String getTripDetails(Model model, @PathVariable(name = "id") Long id) {
        final TripResponse trip = tripService.fetchTripResponse(id);
        model.addAttribute("trip", trip);
        return "trip/details";
    }

    @PreAuthorize("hasAnyAuthority('trip:delete')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteTrip(@PathVariable(name = "id") Long id) {
        tripService.deleteTrip(id);
        return "redirect:/trip/list";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:read')")
    @GetMapping("/last-trip-data/vehicle/{id}")
    @ResponseBody
    public LastTripDataResponse getVehicleLastTripData(@PathVariable(name = "id") Long vehicleId) {
        return tripService.fetchLastTripDataOfVehicle(vehicleId);
    }
}
