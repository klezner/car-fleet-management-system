package pl.kl.carfleetmanagementsystem.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FakeApplicationUserRepository {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
