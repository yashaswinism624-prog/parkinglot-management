public class Car extends Vehicle {
    public Car(String vehicleNumber) { super(vehicleNumber); }
    @Override public double rate() { return 50.0; }
    @Override public String getType() { return "Car"; }
}
