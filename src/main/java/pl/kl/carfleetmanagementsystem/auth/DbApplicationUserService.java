package pl.kl.carfleetmanagementsystem.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kl.carfleetmanagementsystem.exception.ApplicationUserNotLoggedInException;

@Service("db")
@RequiredArgsConstructor
public class DbApplicationUserService implements UserDetailsService, LoggedInApplicationUserService {

    private final DbApplicationUserRepository dbApplicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dbApplicationUserRepository.getApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    @Override
    public ApplicationUser getLoggedInApplicationUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new ApplicationUserNotLoggedInException("Application User is not logged in");
        }
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof ApplicationUser) {
            final ApplicationUser applicationUser = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return dbApplicationUserRepository.getApplicationUserByUsername(applicationUser.getUsername())
                    .orElseThrow(() -> new ApplicationUserNotLoggedInException("Application User is not logged in"));
        }
        throw new ApplicationUserNotLoggedInException("Application User is not logged in");
    }
}
