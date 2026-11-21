public class Truck extends Vehicle {
    public Truck(String vehicleNumber) { super(vehicleNumber); }
    @Override public double rate() { return 100.0; }
    @Override public String getType() { return "Truck"; }
}
