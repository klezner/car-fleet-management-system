package pl.kl.carfleetmanagementsystem.workshop;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workshop")
public class WorkshopController {

    private final WorkshopService workshopService;

    @PreAuthorize("hasAuthority('workshop:read')")
    @GetMapping
    public String getWorkshopHomePage() {
        return "workshop/index";
    }

    @PreAuthorize("hasAuthority('workshop:create')")
    @GetMapping("/add")
    public String getWorkshopAddForm(Model model) {
        model.addAttribute("workshop", new WorkshopRequest());
        return "workshop/add-form";
    }

    @PreAuthorize("hasAuthority('workshop:create')")
    @PostMapping("/save")
    public String submitWorkshopAddForm(WorkshopRequest workshopRequest) {
        workshopService.saveNewWorkshop(workshopRequest);
        return "redirect:/workshop/list";
    }

    @PreAuthorize("hasAuthority('workshop:read')")
    @GetMapping("/list")
    public String getWorkshopList(Model model) {
        final List<WorkshopResponse> workshops = workshopService.fetchAllWorkshopsResponses();
        model.addAttribute("workshops", workshops);
        return "workshop/list";
    }

    @PreAuthorize("hasAuthority('workshop:read')")
    @GetMapping("/{id}")
    public String getWorkshopDetails(Model model, @PathVariable(name = "id") Long id) {
        final WorkshopResponse workshop = workshopService.fetchWorkshopResponse(id);
        model.addAttribute("workshop", workshop);
        return "workshop/details";
    }

    @PreAuthorize("hasAuthority('workshop:update')")
    @GetMapping("/edit/{id}")
    public String getWorkshopEditForm(Model model, @PathVariable(name = "id") Long id) {
        final WorkshopRequest workshop = workshopService.fetchWorkshopRequest(id);
        model.addAttribute("workshop", workshop);
        return "workshop/edit-form";
    }

    @PreAuthorize("hasAuthority('workshop:update')")
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitWorkshopEditForm(WorkshopRequest workshop) {
        workshopService.saveEditedWorkshop(workshop);
        return "redirect:/workshop/list";
    }

    @PreAuthorize("hasAuthority('workshop:delete')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteWorkshop(@PathVariable(name = "id") Long id) {
        workshopService.deleteWorkshop(id);
        return "redirect:/workshop/list";
    }

    @PreAuthorize("hasAuthority('workshop:set_status')")
    @GetMapping("/active/{id}")
    public String setActive(@PathVariable(name = "id") Long id) {
        workshopService.setActive(id);
        return "redirect:/workshop/{id}";
    }

    @PreAuthorize("hasAuthority('workshop:set_status')")
    @GetMapping("/inactive/{id}")
    public String setInactive(@PathVariable(name = "id") Long id) {
        workshopService.setInactive(id);
        return "redirect:/workshop/{id}";
    }
}
