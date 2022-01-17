package pl.kl.carfleetmanagementsystem.security;

public enum ApplicationUserPermission {
    CAR_WORKSHOP_CREATE("carworkshop:create"),
    CAR_WORKSHOP_READ("carworkshop:read"),
    CAR_WORKSHOP_UPDATE("carworkshop:update"),
    CAR_WORKSHOP_DELETE("carworkshop:delete"),
    CAR_WORKSHOP_SET_STATUS("carworkshop:set_status"),
    COMPANY_CREATE("company:create"),
    COMPANY_READ("company:read"),
    COMPANY_UPDATE("company:update"),
    COMPANY_DELETE("company:delete"),
    COMPANY_SET_STATUS("company:set_status"),
    DEPARTMENT_CREATE("department:create"),
    DEPARTMENT_READ("department:read"),
    DEPARTMENT_UPDATE("department:update"),
    DEPARTMENT_DELETE("department:delete"),
    DEPARTMENT_SET_STATUS("department:set_status"),
    FLEET_CARD_CREATE("fleetcard:create"),
    FLEET_CARD_READ("fleetcard:read"),
    FLEET_CARD_UPDATE("fleetcard:update"),
    FLEET_CARD_DELETE("fleetcard:delete"),
    FLEET_CARD_SET_STATUS("fleetcard:set_status"),
    REFUELING_CREATE("refueling:create"),
    REFUELING_READ("refueling:read"),
    REFUELING_UPDATE("refueling:update"),
    REFUELING_DELETE("refueling:delete"),
    REPAIR_CREATE("repair:create"),
    REPAIR_READ("repair:read"),
    REPAIR_UPDATE("repair:update"),
    REPAIR_DELETE("repair:delete"),
    TRIP_CREATE("trip:create"),
    TRIP_READ("trip:read"),
    TRIP_UPDATE("trip:update"),
    TRIP_DELETE("trip:delete"),
    VEHICLE_CREATE("trip:create"),
    VEHICLE_READ("trip:read"),
    VEHICLE_UPDATE("trip:update"),
    VEHICLE_DELETE("trip:delete"),
    VEHICLE_SET_STATUS("trip:set_status");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
