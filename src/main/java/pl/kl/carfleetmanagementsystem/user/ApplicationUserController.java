package pl.kl.carfleetmanagementsystem.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;
import pl.kl.carfleetmanagementsystem.auth.LoggedInApplicationUserService;

@Controller
@RequestMapping("/user")
public class ApplicationUserController {

    private final ApplicationUserMapper applicationUserMapper;
    private final UserDetailsService userDetailsService;
    private final LoggedInApplicationUserService loggedInApplicationUserService;

    public ApplicationUserController(ApplicationUserMapper applicationUserMapper,
                                     @Qualifier("db") UserDetailsService userDetailsService,
                                     LoggedInApplicationUserService loggedInApplicationUserService) {
        this.applicationUserMapper = applicationUserMapper;
        this.userDetailsService = userDetailsService;
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
    public String getUserPasswordChangePage(Model model) {
        final ApplicationUser applicationUser = loggedInApplicationUserService.getLoggedInApplicationUser();
        final PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();
        passwordChangeRequest.setUsername(applicationUser.getUsername());
        model.addAttribute("passwordChange", passwordChangeRequest);
        return "user/password-change-form";
    }
}
