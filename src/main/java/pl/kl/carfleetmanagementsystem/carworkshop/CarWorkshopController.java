package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carworkshop")
public class CarWorkshopController {

    @GetMapping
    public String getCarWorkshopHomePage() {
        return "carworkshop/index";
    }
}
