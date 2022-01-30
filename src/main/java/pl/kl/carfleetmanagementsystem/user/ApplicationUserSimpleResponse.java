package pl.kl.carfleetmanagementsystem.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationUserSimpleResponse {
    private Long id;
    private String username;
    private String role;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
}
