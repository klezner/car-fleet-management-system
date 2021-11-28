package pl.kl.carfleetmanagementsystem.department;

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
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping()
    public String getDepartmentHomePage() {
        return "department/index";
    }

    @GetMapping("/form")
    public String getDepartmentAddForm(Model model) {
        model.addAttribute("department", new DepartmentRequest());
        return "department/add-form";
    }

    @PostMapping
    public String submitDepartmentForm(DepartmentRequest departmentRequest) {
        departmentService.saveDepartment(departmentRequest);
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
        model.addAttribute("department", department);
        return "department/edit-form";
    }

    @GetMapping("/{id}")
    public String getDepartmentDetails(Model model, @PathVariable(name = "id") Long id) {
        final DepartmentResponse department = departmentService.fetchDepartmentResponse(id);
        model.addAttribute("department", department);
        return "department/details";
    }

    @GetMapping("/delete/{id}")
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
