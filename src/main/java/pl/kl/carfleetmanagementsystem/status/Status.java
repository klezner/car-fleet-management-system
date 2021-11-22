package pl.kl.carfleetmanagementsystem.status;

public enum Status {
    ACTIVE("Aktywny"),
    INACTIVE("Nieaktywny");

    private final String commonName;

    Status(String commonName) {
        this.commonName = commonName;
    }

    public String getCommonName() {
        return commonName;
    }
}
