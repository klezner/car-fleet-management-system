package pl.kl.carfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    @GetMapping()
    public String getDepartmentHomePage() {
        return "department/index";
    }
}
