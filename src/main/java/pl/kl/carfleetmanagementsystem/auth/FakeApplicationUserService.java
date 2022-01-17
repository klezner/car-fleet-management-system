package pl.kl.carfleetmanagementsystem.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("fake")
public class FakeApplicationUserService implements UserDetailsService {

    private final FakeApplicationUserRepository fakeApplicationUserRepository;

    @Autowired
    public FakeApplicationUserService(FakeApplicationUserRepository fakeApplicationUserRepository) {
        this.fakeApplicationUserRepository = fakeApplicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return fakeApplicationUserRepository.selectApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
