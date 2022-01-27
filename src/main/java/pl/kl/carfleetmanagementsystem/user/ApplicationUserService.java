package pl.kl.carfleetmanagementsystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;
import pl.kl.carfleetmanagementsystem.security.ApplicationUserRepository;
import pl.kl.carfleetmanagementsystem.validator.PasswordValidator;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository applicationUserRepository,
                                  PasswordEncoder passwordEncoder,
                                  @Qualifier("db") UserDetailsService userDetailsService) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public void updateUserPassword(PasswordChangeRequest passwordChangeRequest) {
        final ApplicationUser applicationUser = (ApplicationUser) userDetailsService.loadUserByUsername(passwordChangeRequest.getUsername());
        PasswordValidator.validatePasswordChange(
                passwordChangeRequest.getOldPassword(),
                passwordChangeRequest.getNewPassword(),
                passwordChangeRequest.getNewPasswordConfirm()
        );
        passwordEncoder.matches(passwordChangeRequest.getNewPassword(), applicationUser.getPassword());
        applicationUser.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
        applicationUserRepository.save(applicationUser);
    }
}
