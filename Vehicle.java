import java.time.LocalDateTime;

public abstract class Vehicle {
    private final String vehicleNumber;
    private final LocalDateTime entryTime;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber.trim().toUpperCase();
        this.entryTime = LocalDateTime.now();
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public abstract double rate();
    public abstract String getType();
}
