package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.department.DepartmentResponse;
import pl.kl.carfleetmanagementsystem.department.DepartmentService;
import pl.kl.carfleetmanagementsystem.status.SetStatus;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController implements SetStatus {

    private final DepartmentService departmentService;
    private final VehicleService vehicleService;

    @PreAuthorize("hasAnyAuthority('vehicle:read')")
    @GetMapping
    public String getVehicleHomePage() {
        return "vehicle/index";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:create')")
    @GetMapping("/add")
    public String getVehicleAddForm(Model model) {
        final List<DepartmentResponse> departments = departmentService.fetchAllDepartmentsResponses();
        model.addAttribute("departments", departments);
        model.addAttribute("types", VehicleType.values());
        model.addAttribute("vehicle", new VehicleRequest());
        return "vehicle/add-form";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:create')")
    @PostMapping("/save")
    public String submitVehicleAddForm(VehicleRequest vehicleRequest) {
        vehicleService.saveNewVehicle(vehicleRequest);
        return "redirect:/vehicle/list";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:read')")
    @GetMapping("/list")
    public String getVehicleList(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/list";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:update')")
    @GetMapping("/edit/{id}")
    public String getVehicleEditForm(Model model, @PathVariable(name = "id") Long id) {
        final List<DepartmentResponse> departments = departmentService.fetchAllDepartmentsResponses();
        final VehicleRequest vehicle = vehicleService.fetchVehicleRequest(id);
        model.addAttribute("departments", departments);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("types", VehicleType.values());
        return "vehicle/edit-form";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitVehicleEditForm(VehicleRequest vehicleRequest) {
        vehicleService.saveEditedVehicle(vehicleRequest);
        return "redirect:/vehicle/list";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:read')")
    @GetMapping("{id}")
    public String getVehicleDetails(Model model, @PathVariable(name = "id") Long id) {
        final VehicleResponse vehicle = vehicleService.fetchVehicleResponse(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle/details";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:delete')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteVehicle(@PathVariable(name = "id") Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicle/list";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:set_status')")
    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        vehicleService.setActive(id);
        return "redirect:/vehicle/{id}";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:set_status')")
    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        vehicleService.setInactive(id);
        return "redirect:/vehicle/{id}";
    }
}
