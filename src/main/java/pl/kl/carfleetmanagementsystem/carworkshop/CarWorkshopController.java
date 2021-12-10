package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
