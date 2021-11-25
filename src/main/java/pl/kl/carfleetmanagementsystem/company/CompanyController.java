package pl.kl.carfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/form")
    public String getCompanyAddForm(Model model) {
        model.addAttribute("company", new CompanyRequest());
        return "company/add-form";
    }

    @PostMapping
    public String submitCompanyForm(CompanyRequest companyRequest) {
        companyService.saveCompany(companyRequest);
        return "redirect:/company/list";
    }

    @GetMapping("/list")
    public String getCompanyList(Model model) {
        final List<CompanyResponse> companies = companyService.fetchAllCompaniesResponses();
        model.addAttribute("companies", companies);
        return "company/list";
    }
}
