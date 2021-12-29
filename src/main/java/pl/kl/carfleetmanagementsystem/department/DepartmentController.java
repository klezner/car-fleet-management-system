package pl.kl.carfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.company.CompanyResponse;
import pl.kl.carfleetmanagementsystem.company.CompanyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final CompanyService companyService;
    private final DepartmentService departmentService;

    @GetMapping
    public String getDepartmentHomePage() {
        return "department/index";
    }

    @GetMapping("/form")
    public String getDepartmentAddForm(Model model) {
        final List<CompanyResponse> companies = companyService.fetchAllCompaniesResponses();
        model.addAttribute("companies", companies);
        model.addAttribute("department", new DepartmentRequest());
        return "department/add-form";
    }

    @PostMapping("/save")
    public String submitDepartmentAddForm(DepartmentRequest departmentRequest) {
        departmentService.saveNewDepartment(departmentRequest);
        return "redirect:/department/list";
    }

    @GetMapping("/list")
    public String getDepartmentList(Model model) {
        final List<DepartmentResponse> departments = departmentService.fetchAllDepartmentsResponses();
        model.addAttribute("departments", departments);
        return "department/list";
    }

    @GetMapping("/edit/{id}")
    public String getDepartmentEditForm(Model model, @PathVariable(name = "id") Long id) {
        final DepartmentRequest department = departmentService.fetchDepartmentRequest(id);
        final List<CompanyResponse> companies = companyService.fetchAllCompaniesResponses();
        model.addAttribute("companies", companies);
        model.addAttribute("department", department);
        return "department/edit-form";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitDepartmentEditForm(DepartmentRequest departmentRequest) {
        departmentService.saveEditedDepartment(departmentRequest);
        return "redirect:/department/list";
    }

    @GetMapping("/{id}")
    public String getDepartmentDetails(Model model, @PathVariable(name = "id") Long id) {
        final DepartmentResponse department = departmentService.fetchDepartmentResponse(id);
        model.addAttribute("department", department);
        return "department/details";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteDepartment(@PathVariable(name = "id") Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/department/list";
    }

    @GetMapping("/active/{id}")
    public String setActive(@PathVariable(name = "id") Long id) {
        departmentService.setActive(id);
        return "redirect:/department/{id}";
    }

    @GetMapping("/inactive/{id}")
    public String setInactive(@PathVariable(name = "id") Long id) {
        departmentService.setInactive(id);
        return "redirect:/department/{id}";
    }
}
