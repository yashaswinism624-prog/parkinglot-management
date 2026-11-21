import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final ArrayList<Vehicle> parkedVehicles = new ArrayList<>();
    private final HashMap<String, Vehicle> vehicleMap = new HashMap<>();
    private final WaitingQueue waitingQueue = new WaitingQueue(50);
    private final int cap = 5;
    private double totalRevenue = 0.0;
    private int vehiclesServed = 0;

    public void park(Vehicle vehicle) throws SlotNotAvailableException {
        String number = vehicle.getVehicleNumber();
        if (vehicleMap.containsKey(number) || waitingQueue.contains(number)) {
            System.out.println("This vehicle number is already parked or waiting.");
            return;
        }
        if (parkedVehicles.size() >= cap) {
            waitingQueue.add(vehicle);
            throw new SlotNotAvailableException("Parking is full. " + number + " has been added to the waiting queue.");
        }
        parkedVehicles.add(vehicle);
        vehicleMap.put(number, vehicle);
        System.out.println(vehicle.getType() + " " + number + " parked successfully.");
    }

    public void exit(String vehicleNumber) throws VehicleNotFoundException {
        String number = vehicleNumber.trim().toUpperCase();
        Vehicle vehicle = vehicleMap.get(number);
        if (vehicle == null) throw new VehicleNotFoundException("Vehicle " + number + " was not found in the parking lot.");

        LocalDateTime exitTime = LocalDateTime.now();
        long minutes = Math.max(0, Duration.between(vehicle.getEntryTime(), exitTime).toMinutes());
        long hours = Math.max(1, (long) Math.ceil(minutes / 60.0));
        double fee = hours * vehicle.rate();

        parkedVehicles.remove(vehicle);
        vehicleMap.remove(number);
        totalRevenue += fee;
        vehiclesServed++;

        printReceipt(vehicle, exitTime, hours, fee);

        if (!waitingQueue.isEmpty()) {
            Vehicle next = waitingQueue.remove();
            parkedVehicles.add(next);
            vehicleMap.put(next.getVehicleNumber(), next);
            System.out.println("Next waiting vehicle " + next.getVehicleNumber() + " (" + next.getType() + ") has been moved into the available slot.");
        }
    }

    private void printReceipt(Vehicle vehicle, LocalDateTime exitTime, long hours, double fee) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("\\n========== PARKING RECEIPT ==========");
        System.out.println("Vehicle number : " + vehicle.getVehicleNumber());
        System.out.println("Vehicle type   : " + vehicle.getType());
        System.out.println("Entry time     : " + vehicle.getEntryTime().format(fmt));
        System.out.println("Exit time      : " + exitTime.format(fmt));
        System.out.println("Billable hours : " + hours);
        System.out.printf("Rate per hour  : Rs. %.2f%n", vehicle.rate());
        System.out.printf("Total fee      : Rs. %.2f%n", fee);
        System.out.println("=====================================\\n");
    }

    public boolean cancelWaiting(String vehicleNumber) {
        return waitingQueue.cancel(vehicleNumber);
    }

    public void show() {
        System.out.println("\\n========== PARKING STATUS ==========");
        System.out.println("Occupied slots: " + parkedVehicles.size() + "/" + cap);
        if (parkedVehicles.isEmpty()) {
            System.out.println("No vehicles currently parked.");
        } else {
            for (Vehicle v : parkedVehicles) {
                System.out.printf("%-8s | %-15s | Rs. %.2f/hour | Entry: %s%n",
                    v.getType(), v.getVehicleNumber(), v.rate(),
                    v.getEntryTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        }
        waitingQueue.show();
        System.out.println("====================================");
    }

    public void revenueReport() {
        System.out.println("\\n========== REVENUE REPORT ==========");
        System.out.printf("Total revenue collected : Rs. %.2f%n", totalRevenue);
        System.out.println("Vehicles served         : " + vehiclesServed);
        System.out.println("Current occupancy       : " + parkedVehicles.size() + "/" + cap);
        System.out.println("Waiting vehicles        : " + waitingQueue.size());
        System.out.println("====================================");
    }
}
