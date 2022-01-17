package pl.kl.carfleetmanagementsystem.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsernameAndPaswordAuthenticationRequest {
    private String username;
    private String password;
}
