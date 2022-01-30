package pl.kl.carfleetmanagementsystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;
import pl.kl.carfleetmanagementsystem.auth.LoggedInApplicationUserService;
import pl.kl.carfleetmanagementsystem.security.ApplicationUserRole;

import java.util.List;

@Controller
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;
    private final ApplicationUserMapper applicationUserMapper;
    private final LoggedInApplicationUserService loggedInApplicationUserService;

    @Autowired
    public ApplicationUserController(ApplicationUserService applicationUserService,
                                     ApplicationUserMapper applicationUserMapper,
                                     @Qualifier("db") LoggedInApplicationUserService loggedInApplicationUserService) {
        this.applicationUserService = applicationUserService;
        this.applicationUserMapper = applicationUserMapper;
        this.loggedInApplicationUserService = loggedInApplicationUserService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/user/profile")
    public String getUserProfilePage(Model model) {
        final ApplicationUser applicationUser = loggedInApplicationUserService.getLoggedInApplicationUser();
        final ApplicationUserFullResponse applicationUserFullResponse = applicationUserMapper.mapApplicationUserToApplicationUserFullResponse(applicationUser);
        model.addAttribute("applicationUser", applicationUserFullResponse);
        return "user/profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/user/change-password")
    public String getUserPasswordChangeForm(Model model) {
        final ApplicationUser applicationUser = loggedInApplicationUserService.getLoggedInApplicationUser();
        final PasswordChangeUserRequest passwordChangeUserRequest = new PasswordChangeUserRequest();
        passwordChangeUserRequest.setUsername(applicationUser.getUsername());
        model.addAttribute("passwordChange", passwordChangeUserRequest);
        return "user/password-change-form";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/user/update-password", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitUserPasswordChangeForm(PasswordChangeUserRequest passwordChangeUserRequest) {
        applicationUserService.updateUserPassword(passwordChangeUserRequest);
        return "redirect:/user/profile";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getApplicationUserList(Model model) {
        final List<ApplicationUserSimpleResponse> applicationUsers = applicationUserService.fetchAllApplicationUserResponses();
        model.addAttribute("applicationUsers", applicationUsers);
        return "user/list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/enable-user/{id}")
    public String setUserEnabled(@PathVariable(name = "id") Long id) {
        applicationUserService.setUserEnabled(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/disable-user/{id}")
    public String setUserDisabled(@PathVariable(name = "id") Long id) {
        applicationUserService.setUserDisabled(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/add-user")
    public String getUserAddForm(Model model) {
        final ApplicationUserRole[] userRoles = ApplicationUserRole.values();
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("applicationUser", new ApplicationUserRequest());
        return "user/add-form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/user-save")
    public String submitUserAddForm(ApplicationUserRequest applicationUserRequest) {
        applicationUserService.saveNewUser(applicationUserRequest);
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/change-password/{id}")
    public String getUserPasswordChangeByAdminForm(@PathVariable(name = "id") Long id, Model model) {
        final String username = applicationUserService.fetchApplicationUserUsername(id);
        final PasswordChangeAdminRequest passwordChangeAdminRequest = new PasswordChangeAdminRequest();
        passwordChangeAdminRequest.setUsername(username);
        model.addAttribute("passwordChange", passwordChangeAdminRequest);
        return "user/password-change-admin-form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin/update-password", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submitUserPasswordChangeByAdminForm(PasswordChangeAdminRequest passwordChangeAdminRequest) {
        applicationUserService.updateUserPasswordByAdmin(passwordChangeAdminRequest);
        return "redirect:/admin";
    }
}
