package pl.kl.carfleetmanagementsystem.user;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ApplicationUserFullResponse {
    private Long id;
    private String username;
    private String role;
    private List<String> permissions;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
}
