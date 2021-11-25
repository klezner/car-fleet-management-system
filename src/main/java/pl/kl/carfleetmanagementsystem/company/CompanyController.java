package pl.kl.carfleetmanagementsystem.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @GetMapping("")
    public String getCompanyHomePage() {
        return "company/index";
    }
}
