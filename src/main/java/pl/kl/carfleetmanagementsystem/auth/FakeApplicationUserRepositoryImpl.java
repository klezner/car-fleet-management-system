package pl.kl.carfleetmanagementsystem.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static pl.kl.carfleetmanagementsystem.security.ApplicationUserRole.ADMIN;
import static pl.kl.carfleetmanagementsystem.security.ApplicationUserRole.USER;

@Repository
@RequiredArgsConstructor
public class FakeApplicationUserRepositoryImpl implements FakeApplicationUserRepository {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser("admin", passwordEncoder.encode("admin1"), ADMIN.getGrantedAuthorities(), true, true, true, true),
                new ApplicationUser("user", passwordEncoder.encode("user1"), USER.getGrantedAuthorities(), true, true, true, true));

        return applicationUsers;
    }
}
