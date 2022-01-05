package pl.kl.carfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.carworkshop.CarWorkshopResponse;
import pl.kl.carfleetmanagementsystem.carworkshop.CarWorkshopService;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleResponse;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repair")
public class RepairController {

    private final CarWorkshopService carWorkshopService;
    private final VehicleService vehicleService;
    private final RepairService repairService;

    @GetMapping
    public String getRepairHomePage() {
        return "repair/index";
    }

    @GetMapping("/form")
    public String getRepairAddForm(Model model) {
        final List<VehicleResponse> vehicles = vehicleService.fetchAllVehiclesResponses();
        final List<CarWorkshopResponse> carWorkshops = carWorkshopService.fetchAllCarWorkshopsResponses();
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("carWorkshops", carWorkshops);
        model.addAttribute("repair", new RepairRequest());
        return "repair/add-form";
    }

    @PostMapping("/save")
    public String submitRepairAddForm(RepairRequest repairRequest) {
        repairService.saveNewRepair(repairRequest);
        return "redirect:/repair/list";
    }

    @GetMapping("/list")
    public String getRepairList(Model model) {
        final List<RepairResponse> repairs = repairService.fetchAllRepairsResponses();
        model.addAttribute("repairs", repairs);
        return "/repair/list";
    }

    @GetMapping("/{id}")
    public String getRepairDetails(Model model, @PathVariable(name = "id") Long id) {
        final RepairResponse repair = repairService.fetchRepairResponse(id);
        model.addAttribute("repair", repair);
        return "repair/details";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteRepair(@PathVariable(name = "id") Long id) {
        repairService.deleteRepair(id);
        return "redirect:/repair/list";
    }

    @GetMapping("/last-repair-data/vehicle/{id}")
    @ResponseBody
    public LastRepairDataResponse getVehiclesLastRepairData(@PathVariable(name = "id") Long vehicleId) {
        return repairService.fetchLastRepairDataOfVehicle(vehicleId);
    }
}
