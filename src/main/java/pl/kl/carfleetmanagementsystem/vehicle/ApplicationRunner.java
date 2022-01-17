package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;
import pl.kl.carfleetmanagementsystem.auth.DbApplicationUserRepository;

import static pl.kl.carfleetmanagementsystem.security.ApplicationUserRole.ADMIN;
import static pl.kl.carfleetmanagementsystem.security.ApplicationUserRole.USER;

@RequiredArgsConstructor
@Component
public class ApplicationRunner implements CommandLineRunner {

    private final DbApplicationUserRepository dbApplicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (checkIfUserExists("admin")) {
            dbApplicationUserRepository.save(new ApplicationUser("admin", passwordEncoder.encode("admin1"), ADMIN.getGrantedAuthorities(), true, true, true, true));
        }
        if (checkIfUserExists("user")) {
            dbApplicationUserRepository.save(new ApplicationUser("user", passwordEncoder.encode("user1"), USER.getGrantedAuthorities(), true, true, true, true));
        }
    }

    private boolean checkIfUserExists(String username) {
        return dbApplicationUserRepository.getApplicationUserByUsername(username).isEmpty();
    }
}
