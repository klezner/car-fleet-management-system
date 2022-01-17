package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/car-workshop")
public class CarWorkshopController {

    private final CarWorkshopService carWorkshopService;

    @PreAuthorize("hasAnyAuthority('carworkshop:read')")
    @GetMapping
    public String getCarWorkshopHomePage() {
        return "carworkshop/index";
    }

    @PreAuthorize("hasAuthority('carworkshop:create')")
    @GetMapping("/form")
    public String getCarWorkshopAddForm(Model model) {
        model.addAttribute("carWorkshop", new CarWorkshopRequest());
        return "carworkshop/add-form";
    }

    @PreAuthorize("hasAuthority('carworkshop:create')")
    @PostMapping("/save")
    public String submitCarWorkshopAddForm(CarWorkshopRequest carWorkshopRequest) {
        carWorkshopService.saveNewCarWorkshop(carWorkshopRequest);
        return "redirect:/car-workshop/list";
    }

    @PreAuthorize("hasAnyAuthority('carworkshop:read')")
    @GetMapping("/list")
    public String getCarWorkshopList(Model model) {
        final List<CarWorkshopResponse> carWorkshops = carWorkshopService.fetchAllCarWorkshopsResponses();
        model.addAttribute("carWorkshops", carWorkshops);
        return "carworkshop/list";
    }

    @PreAuthorize("hasAnyAuthority('carworkshop:read')")
    @GetMapping("/{id}")
    public String getCarWorkshopDetails(Model model, @PathVariable(name = "id") Long id) {
        final CarWorkshopResponse carWorkshop = carWorkshopService.fetchCarWorkshopResponse(id);
        model.addAttribute("carWorkshop", carWorkshop);
        return "carworkshop/details";
    }

    @PreAuthorize("hasAnyAuthority('carworkshop:update')")
    @GetMapping("/edit/{id}")
    public String getCarWorkshopEditForm(Model model, @PathVariable(name = "id") Long id) {
        final CarWorkshopRequest carWorkshop = carWorkshopService.fetchCarWorkshopRequest(id);
        model.addAttribute("carWorkshop", carWorkshop);
        return "carworkshop/edit-form";
    }

    @PreAuthorize("hasAnyAuthority('carworkshop:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitCarWorkshopEditForm(CarWorkshopRequest carWorkshop) {
        carWorkshopService.saveEditedCarWorkshop(carWorkshop);
        return "redirect:/car-workshop/list";
    }

    @PreAuthorize("hasAnyAuthority('carworkshop:delete')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCarWorkshop(@PathVariable(name = "id") Long id) {
        carWorkshopService.deleteCarWorkshop(id);
        return "redirect:/car-workshop/list";
    }

    @PreAuthorize("hasAnyAuthority('carworkshop:set_status')")
    @GetMapping("/active/{id}")
    public String setActive(@PathVariable(name = "id") Long id) {
        carWorkshopService.setActive(id);
        return "redirect:/car-workshop/{id}";
    }

    @PreAuthorize("hasAnyAuthority('carworkshop:set_status')")
    @GetMapping("/inactive/{id}")
    public String setInactive(@PathVariable(name = "id") Long id) {
        carWorkshopService.setInactive(id);
        return "redirect:/car-workshop/{id}";
    }
}
