package pl.kl.carfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;
import pl.kl.carfleetmanagementsystem.workshop.WorkshopResponse;
import pl.kl.carfleetmanagementsystem.workshop.WorkshopService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repair")
public class RepairController {

    private final WorkshopService workshopService;
    private final VehicleService vehicleService;
    private final RepairService repairService;

    @PreAuthorize("hasAnyAuthority('repair:read')")
    @GetMapping
    public String getRepairHomePage() {
        return "repair/index";
    }

    @PreAuthorize("hasAnyAuthority('repair:create')")
    @GetMapping("/form")
    public String getRepairAddForm(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        final List<WorkshopResponse> workshops = workshopService.fetchAllWorkshopsResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("workshops", workshops);
        model.addAttribute("repair", new RepairRequest());
        return "repair/add-form";
    }

    @PreAuthorize("hasAnyAuthority('repair:create')")
    @PostMapping("/save")
    public String submitRepairAddForm(RepairRequest repairRequest) {
        repairService.saveNewRepair(repairRequest);
        return "redirect:/repair/list";
    }

    @PreAuthorize("hasAnyAuthority('repair:read')")
    @GetMapping("/list")
    public String getRepairList(Model model) {
        final List<RepairResponse> repairs = repairService.fetchAllRepairsResponses();
        model.addAttribute("repairs", repairs);
        return "/repair/list";
    }

    @PreAuthorize("hasAnyAuthority('repair:read')")
    @GetMapping("/{id}")
    public String getRepairDetails(Model model, @PathVariable(name = "id") Long id) {
        final RepairResponse repair = repairService.fetchRepairResponse(id);
        model.addAttribute("repair", repair);
        return "repair/details";
    }

    @PreAuthorize("hasAnyAuthority('repair:update')")
    @GetMapping("/edit/{id}")
    public String getRepairEditForm(Model model, @PathVariable(name = "id") Long id) {
        final RepairRequest repair = repairService.fetchRepairRequest(id);
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        final List<WorkshopResponse> workshops = workshopService.fetchAllWorkshopsResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("workshops", workshops);
        model.addAttribute("repair", repair);
        return "repair/edit-form";
    }

    @PreAuthorize("hasAnyAuthority('repair:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitRepairEditForm(RepairRequest repairRequest) {
        repairService.saveEditedRepair(repairRequest);
        return "redirect:/repair/list";
    }

    @PreAuthorize("hasAnyAuthority('repair:read')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteRepair(@PathVariable(name = "id") Long id) {
        repairService.deleteRepair(id);
        return "redirect:/repair/list";
    }

    @PreAuthorize("hasAnyAuthority('vehicle:read')")
    @GetMapping("/last-repair-data/vehicle/{id}")
    @ResponseBody
    public LastRepairDataResponse getVehiclesLastRepairData(@PathVariable(name = "id") Long vehicleId) {
        return repairService.fetchLastRepairDataOfVehicle(vehicleId);
    }
}
