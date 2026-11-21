import java.util.Scanner;

public class Main {
    private static Vehicle createVehicle(Scanner sc) {
        System.out.println("Vehicle type: 1. Car (Rs.50/hr)  2. Bike (Rs.20/hr)  3. Truck (Rs.100/hr)");
        System.out.print("Choose type: ");
        int type = readInt(sc);
        System.out.print("Enter vehicle number: ");
        String number = sc.nextLine().trim();
        if (number.isEmpty()) {
            System.out.println("Vehicle number cannot be empty.");
            return null;
        }
        switch (type) {
            case 1: return new Car(number);
            case 2: return new Bike(number);
            case 3: return new Truck(number);
            default:
                System.out.println("Invalid vehicle type.");
                return null;
        }
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.nextLine();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot();
        boolean running = true;

        while (running) {
            System.out.println("\\n===== PARKING LOT MANAGEMENT SYSTEM =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Exit Vehicle");
            System.out.println("3. Show Status");
            System.out.println("4. Revenue Report");
            System.out.println("5. Cancel Waiting");
            System.out.println("6. Close");
            System.out.print("Enter choice: ");

            int choice = readInt(sc);
            try {
                switch (choice) {
                    case 1:
                        Vehicle vehicle = createVehicle(sc);
                        if (vehicle != null) parkingLot.park(vehicle);
                        break;
                    case 2:
                        System.out.print("Enter vehicle number to exit: ");
                        parkingLot.exit(sc.nextLine());
                        break;
                    case 3:
                        parkingLot.show();
                        break;
                    case 4:
                        parkingLot.revenueReport();
                        break;
                    case 5:
                        System.out.print("Enter vehicle number to cancel from waiting queue: ");
                        String number = sc.nextLine();
                        if (parkingLot.cancelWaiting(number)) {
                            System.out.println("Waiting request cancelled for " + number.toUpperCase() + ".");
                        } else {
                            System.out.println("Vehicle was not found in the waiting queue.");
                        }
                        break;
                    case 6:
                        running = false;
                        System.out.println("Parking system closed. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose 1 to 6.");
                }
            } catch (SlotNotAvailableException | VehicleNotFoundException e) {
                System.out.println("Notice: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("Notice: " + e.getMessage());
            }
        }
        sc.close();
    }
}
