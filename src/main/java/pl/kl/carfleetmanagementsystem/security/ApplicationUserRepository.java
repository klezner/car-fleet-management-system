package pl.kl.carfleetmanagementsystem.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kl.carfleetmanagementsystem.auth.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query("select a.username from ApplicationUser a where a.id =:id")
    String findApplicationUserUsernameById(Long id);
}
