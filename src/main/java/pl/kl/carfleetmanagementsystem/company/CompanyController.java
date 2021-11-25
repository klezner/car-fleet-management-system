package pl.kl.carfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public String getCompanyHomePage() {
        return "company/index";
    }

    @GetMapping("/list")
    public String getCompanyList(Model model) {
        final List<CompanyResponse> companies = companyService.fetchAllCompaniesResponses();
        model.addAttribute("companies", companies);
        return "company/list";
    }
}
