package pl.kl.carfleetmanagementsystem.user;

import org.springframework.beans.factory.annotation.Qualifier;
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

    @GetMapping("/profile")
    public String getUserProfilePage(Model model) {
        final ApplicationUser applicationUser = loggedInApplicationUserService.getLoggedInApplicationUser();
        final ApplicationUserResponse applicationUserResponse = applicationUserMapper.applicationUserToApplicationUserResponse(applicationUser);
        model.addAttribute("applicationUser", applicationUserResponse);
        return "user/profile";
    }
}
