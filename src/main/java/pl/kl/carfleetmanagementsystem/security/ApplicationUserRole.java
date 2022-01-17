package pl.kl.carfleetmanagementsystem.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.kl.carfleetmanagementsystem.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(CAR_WORKSHOP_READ, COMPANY_READ, DEPARTMENT_READ, FLEET_CARD_READ, REFUELING_CREATE,
            REFUELING_READ, REFUELING_UPDATE, REPAIR_CREATE, REPAIR_READ, REPAIR_UPDATE, TRIP_CREATE, TRIP_READ,
            TRIP_UPDATE, VEHICLE_READ)),
    ADMIN(Sets.newHashSet(CAR_WORKSHOP_CREATE, CAR_WORKSHOP_READ, CAR_WORKSHOP_UPDATE, CAR_WORKSHOP_DELETE,
            CAR_WORKSHOP_SET_STATUS, COMPANY_CREATE, COMPANY_READ, COMPANY_UPDATE, COMPANY_DELETE, COMPANY_SET_STATUS,
            DEPARTMENT_CREATE, DEPARTMENT_READ, DEPARTMENT_UPDATE, DEPARTMENT_DELETE, DEPARTMENT_SET_STATUS,
            FLEET_CARD_CREATE, FLEET_CARD_READ, FLEET_CARD_UPDATE, FLEET_CARD_DELETE, FLEET_CARD_SET_STATUS,
            REFUELING_CREATE, REFUELING_READ, REFUELING_UPDATE, REFUELING_DELETE, REPAIR_CREATE, REPAIR_READ,
            REPAIR_UPDATE, REPAIR_DELETE, TRIP_CREATE, TRIP_READ, TRIP_UPDATE, TRIP_DELETE, VEHICLE_CREATE,
            VEHICLE_READ, VEHICLE_UPDATE, VEHICLE_DELETE, VEHICLE_SET_STATUS));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        final Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
