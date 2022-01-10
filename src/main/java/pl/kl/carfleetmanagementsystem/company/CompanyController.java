package pl.kl.carfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.status.SetStatus;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController implements SetStatus {

    private final CompanyService companyService;

    @GetMapping
    public String getCompanyHomePage() {
        return "company/index";
    }

    @GetMapping("/form")
    public String getCompanyAddForm(Model model) {
        model.addAttribute("company", new CompanyRequest());
        return "company/add-form";
    }

    @PostMapping("/save")
    public String submitCompanyAddForm(CompanyRequest companyRequest) {
        companyService.saveNewCompany(companyRequest);
        return "redirect:/company/list";
    }

    @GetMapping("/list")
    public String getCompanyList(Model model) {
        final List<CompanyResponse> companies = companyService.fetchAllCompaniesResponses();
        model.addAttribute("companies", companies);
        return "company/list";
    }

    @GetMapping("/edit/{id}")
    public String getCompanyEditForm(Model model, @PathVariable(name = "id") Long id) {
        final CompanyRequest company = companyService.fetchCompanyRequest(id);
        model.addAttribute("company", company);
        return "company/edit-form";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitCompanyEditForm(CompanyRequest companyRequest) {
        companyService.saveEditedCompany(companyRequest);
        return "redirect:/company/list";
    }

    @GetMapping("{id}")
    public String getCompanyDetails(Model model, @PathVariable(name = "id") Long id) {
        final CompanyResponse company = companyService.fetchCompanyResponse(id);
        model.addAttribute("company", company);
        return "company/details";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCompany(@PathVariable(name = "id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/company/list";
    }

    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        companyService.setActive(id);
        return "redirect:/company/{id}";
    }

    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        companyService.setInactive(id);
        return "redirect:/company/{id}";
    }
}
