# Parking Lot Management System (Java Console Project)

## Included features
- Abstract `Vehicle` class with `Car`, `Bike`, and `Truck`
- Five parking slots
- `ArrayList` for parked vehicles and `HashMap` for vehicle-number lookup
- Array-based waiting queue with `front` and `rear`
- Custom exceptions for full parking and missing vehicles
- Parking fee calculation (minimum 1 hour)
- Automatic promotion of the next waiting vehicle when a slot becomes free
- Revenue report: total revenue, vehicles served, current occupancy, and waiting count
- Cancel a vehicle's waiting request
- **Unique feature: automatic itemized parking receipt** on vehicle exit

## Rates
- Car: Rs. 50/hour
- Bike: Rs. 20/hour
- Truck: Rs. 100/hour

## Run in VS Code
1. Extract the ZIP folder.
2. Open the `ParkingLotManagementSystem` folder in VS Code.
3. Install the **Extension Pack for Java** if VS Code asks.
4. Make sure a JDK is installed (JDK 17 or later recommended).
5. Open `Main.java`.
6. Click **Run** above the `main` method, or open the VS Code terminal and run:

```bash
javac *.java
java Main
```

## Menu
1. Park Vehicle
2. Exit Vehicle
3. Show Status
4. Revenue Report
5. Cancel Waiting
6. Close

Note: Revenue is stored in memory and resets when the program is closed.
