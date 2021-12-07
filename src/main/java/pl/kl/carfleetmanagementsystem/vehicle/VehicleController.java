package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kl.carfleetmanagementsystem.department.DepartmentResponse;
import pl.kl.carfleetmanagementsystem.department.DepartmentService;
import pl.kl.carfleetmanagementsystem.status.SetStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController implements SetStatus {

    private final DepartmentService departmentService;
    private final VehicleService vehicleService;

    @GetMapping
    public String getVehicleHomePage() {
        return "vehicle/index";
    }

    @GetMapping("/form")
    public String getVehicleAddForm(Model model) {
        final List<DepartmentResponse> departments = departmentService.fetchAllDepartmentsResponses();
        model.addAttribute("departments", departments);
        model.addAttribute("types", VehicleType.values());
        model.addAttribute("vehicle", new VehicleRequest());
        return "vehicle/add-form";
    }

    @PostMapping
    public String submitAddOrEditVehicleForm(@Valid VehicleRequest vehicleRequest) {
        vehicleService.saveVehicle(vehicleRequest);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/list")
    public String getVehicleList(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/list";
    }

    @GetMapping("/edit/{id}")
    public String getVehicleEditForm(Model model, @PathVariable(name = "id") Long id) {
        final List<DepartmentResponse> departments = departmentService.fetchAllDepartmentsResponses();
        final VehicleRequest vehicle = vehicleService.fetchVehicleRequest(id);
        model.addAttribute("departments", departments);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("types", VehicleType.values());
        return "vehicle/edit-form";
    }

    @GetMapping("{id}")
    public String getVehicleDetails(Model model, @PathVariable(name = "id") Long id) {
        final VehicleResponse vehicle = vehicleService.fetchVehicleResponse(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle/details";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable(name = "id") Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        vehicleService.setActive(id);
        return "redirect:/vehicle/{id}";
    }

    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        vehicleService.setInactive(id);
        return "redirect:/vehicle/{id}";
    }
}
