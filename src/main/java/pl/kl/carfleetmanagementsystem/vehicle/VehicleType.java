package pl.kl.carfleetmanagementsystem.vehicle;

public enum VehicleType {
    CAR("Osobowy"),
    TRUCK("Ciężarowy"),
    OTHER("Inny");

    private final String commonName;

    VehicleType(String commonName) {
        this.commonName = commonName;
    }

    public String getCommonName() {
        return commonName;
    }
}
