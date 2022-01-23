package pl.kl.carfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('company:read')")
    @GetMapping
    public String getCompanyHomePage() {
        return "company/index";
    }

    @PreAuthorize("hasAuthority('company:create')")
    @GetMapping("/add")
    public String getCompanyAddForm(Model model) {
        model.addAttribute("company", new CompanyRequest());
        return "company/add-form";
    }

    @PreAuthorize("hasAuthority('company:create')")
    @PostMapping("/save")
    public String submitCompanyAddForm(CompanyRequest companyRequest) {
        companyService.saveNewCompany(companyRequest);
        return "redirect:/company/list";
    }

    @PreAuthorize("hasAnyAuthority('company:read')")
    @GetMapping("/list")
    public String getCompanyList(Model model) {
        final List<CompanyResponse> companies = companyService.fetchAllCompaniesResponses();
        model.addAttribute("companies", companies);
        return "company/list";
    }

    @PreAuthorize("hasAnyAuthority('company:update')")
    @GetMapping("/edit/{id}")
    public String getCompanyEditForm(Model model, @PathVariable(name = "id") Long id) {
        final CompanyRequest company = companyService.fetchCompanyRequest(id);
        model.addAttribute("company", company);
        return "company/edit-form";
    }

    @PreAuthorize("hasAnyAuthority('company:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitCompanyEditForm(CompanyRequest companyRequest) {
        companyService.saveEditedCompany(companyRequest);
        return "redirect:/company/list";
    }

    @PreAuthorize("hasAnyAuthority('company:read')")
    @GetMapping("{id}")
    public String getCompanyDetails(Model model, @PathVariable(name = "id") Long id) {
        final CompanyResponse company = companyService.fetchCompanyResponse(id);
        model.addAttribute("company", company);
        return "company/details";
    }

    @PreAuthorize("hasAnyAuthority('company:delete')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCompany(@PathVariable(name = "id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/company/list";
    }

    @PreAuthorize("hasAnyAuthority('company:set_status')")
    @GetMapping("/active/{id}")
    @Override
    public String setActive(@PathVariable(name = "id") Long id) {
        companyService.setActive(id);
        return "redirect:/company/{id}";
    }

    @PreAuthorize("hasAnyAuthority('company:set_status')")
    @GetMapping("/inactive/{id}")
    @Override
    public String setInactive(@PathVariable(name = "id") Long id) {
        companyService.setInactive(id);
        return "redirect:/company/{id}";
    }
}
