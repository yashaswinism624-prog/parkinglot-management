public class Bike extends Vehicle {
    public Bike(String vehicleNumber) { super(vehicleNumber); }
    @Override public double rate() { return 20.0; }
    @Override public String getType() { return "Bike"; }
}
