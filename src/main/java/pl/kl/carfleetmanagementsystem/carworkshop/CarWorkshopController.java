package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/car-workshop")
public class CarWorkshopController {

    private final CarWorkshopService carWorkshopService;

    @GetMapping
    public String getCarWorkshopHomePage() {
        return "carworkshop/index";
    }

    @GetMapping("/form")
    public String getCarWorkshopAddForm(Model model) {
        model.addAttribute("carWorkshop", new CarWorkshopRequest());
        return "carworkshop/add-form";
    }

    @PostMapping("/save")
    public String submitCarWorkshopAddForm(CarWorkshopRequest carWorkshopRequest) {
        carWorkshopService.saveNewCarWorkshop(carWorkshopRequest);
        return "redirect:/car-workshop/list";
    }

    @GetMapping("/list")
    public String getCarWorkshopList(Model model) {
        final List<CarWorkshopResponse> carWorkshops = carWorkshopService.fetchAllCarWorkshopsResponses();
        model.addAttribute("carWorkshops", carWorkshops);
        return "carworkshop/list";
    }

    @GetMapping("/{id}")
    public String getCarWorkshopDetails(Model model, @PathVariable(name = "id") Long id) {
        final CarWorkshopResponse carWorkshop = carWorkshopService.fetchCarWorkshopResponse(id);
        model.addAttribute("carWorkshop", carWorkshop);
        return "carworkshop/details";
    }
}
