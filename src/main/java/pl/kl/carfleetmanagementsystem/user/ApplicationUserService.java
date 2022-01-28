package pl.kl.carfleetmanagementsystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;
import pl.kl.carfleetmanagementsystem.security.ApplicationUserRepository;
import pl.kl.carfleetmanagementsystem.validator.PasswordValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationUserService {

    private final ApplicationUserMapper applicationUserMapper;
    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public ApplicationUserService(ApplicationUserMapper applicationUserMapper,
                                  ApplicationUserRepository applicationUserRepository,
                                  PasswordEncoder passwordEncoder,
                                  @Qualifier("db") UserDetailsService userDetailsService) {
        this.applicationUserMapper = applicationUserMapper;
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public void updateUserPassword(PasswordChangeRequest passwordChangeRequest) {
        final ApplicationUser applicationUser = (ApplicationUser) userDetailsService.loadUserByUsername(passwordChangeRequest.getUsername());
        PasswordValidator.validatePasswordChange(passwordChangeRequest.getOldPassword(),
                passwordChangeRequest.getNewPassword(),
                passwordChangeRequest.getNewPasswordConfirm()
        );
        passwordEncoder.matches(passwordChangeRequest.getNewPassword(), applicationUser.getPassword());
        applicationUser.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
        applicationUserRepository.save(applicationUser);
    }

    public List<ApplicationUserSimpleResponse> fetchAllApplicationUserResponses() {
        final List<ApplicationUser> applicationUserEntities = fetchAllApplicationUsers();
        return applicationUserEntities.stream()
                .map(applicationUserMapper::applicationUserToApplicationUserSimpleResponse)
                .collect(Collectors.toList());
    }

    private List<ApplicationUser> fetchAllApplicationUsers() {
        return applicationUserRepository.findAll();
    }

    @Transactional
    public void setUserEnabled(Long id) {
        final ApplicationUser applicationUser = fetchApplicationUserById(id);
        applicationUser.setEnable();
        applicationUserRepository.save(applicationUser);
    }

    @Transactional
    public void setUserDisabled(Long id) {
        final ApplicationUser applicationUser = fetchApplicationUserById(id);
        applicationUser.setDisable();
        applicationUserRepository.save(applicationUser);
    }

    private ApplicationUser fetchApplicationUserById(Long id) {
        return applicationUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Application User with id: " + id + " not found"));
    }
}
