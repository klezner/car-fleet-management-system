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
    public void updateUserPassword(PasswordChangeUserRequest passwordChangeUserRequest) {
        final ApplicationUser applicationUser = (ApplicationUser) userDetailsService.loadUserByUsername(passwordChangeUserRequest.getUsername());
        PasswordValidator.validatePasswordChange(passwordChangeUserRequest.getOldPassword(),
                passwordChangeUserRequest.getNewPassword(),
                passwordChangeUserRequest.getNewPasswordConfirm()
        );
        passwordEncoder.matches(passwordChangeUserRequest.getNewPassword(), applicationUser.getPassword());
        applicationUser.setPassword(passwordEncoder.encode(passwordChangeUserRequest.getNewPassword()));
        applicationUserRepository.save(applicationUser);
    }

    public List<ApplicationUserSimpleResponse> fetchAllApplicationUserResponses() {
        final List<ApplicationUser> applicationUserEntities = fetchAllApplicationUsers();
        return applicationUserEntities.stream()
                .map(applicationUserMapper::mapApplicationUserToApplicationUserSimpleResponse)
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

    @Transactional
    public void saveNewUser(ApplicationUserRequest applicationUserRequest) {
        final ApplicationUser applicationUser = applicationUserMapper.mapApplicationUserRequestToApplicationUser(applicationUserRequest);
        applicationUserRepository.save(applicationUser);
    }

    public String fetchApplicationUserUsername(Long id) {
        return applicationUserRepository.findApplicationUserUsernameById(id);
    }

    public void updateUserPasswordByAdmin(PasswordChangeAdminRequest passwordChangeAdminRequest) {
        final ApplicationUser applicationUser = (ApplicationUser) userDetailsService.loadUserByUsername(passwordChangeAdminRequest.getUsername());
        applicationUser.setPassword(passwordEncoder.encode(passwordChangeAdminRequest.getNewPassword()));
        applicationUserRepository.save(applicationUser);
    }
}
