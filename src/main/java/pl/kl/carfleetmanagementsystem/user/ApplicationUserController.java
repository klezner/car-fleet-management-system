package pl.kl.carfleetmanagementsystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;
import pl.kl.carfleetmanagementsystem.auth.LoggedInApplicationUserService;

@Controller
@RequestMapping("/user")
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
    @GetMapping("/profile")
    public String getUserProfilePage(Model model) {
        final ApplicationUser applicationUser = loggedInApplicationUserService.getLoggedInApplicationUser();
        final ApplicationUserResponse applicationUserResponse = applicationUserMapper.applicationUserToApplicationUserResponse(applicationUser);
        model.addAttribute("applicationUser", applicationUserResponse);
        return "user/profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/change-password")
    public String getUserPasswordChangeForm(Model model) {
        final ApplicationUser applicationUser = loggedInApplicationUserService.getLoggedInApplicationUser();
        final PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();
        passwordChangeRequest.setUsername(applicationUser.getUsername());
        model.addAttribute("passwordChange", passwordChangeRequest);
        return "user/password-change-form";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/update-password")
    public String submitUserPasswordChangeForm(PasswordChangeRequest passwordChangeRequest) {
        applicationUserService.updateUserPassword(passwordChangeRequest);
        return "redirect:/user/profile";
    }
}
