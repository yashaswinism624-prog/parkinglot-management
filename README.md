# parkinglot-management
java project
Parking Lot Management System (Java Console Project)
Included features
Abstract Vehicle class with Car, Bike, and Truck
Five parking slots
ArrayList for parked vehicles and HashMap for vehicle-number lookup
Array-based waiting queue with front and rear
Custom exceptions for full parking and missing vehicles
Parking fee calculation (minimum 1 hour)
Automatic promotion of the next waiting vehicle when a slot becomes free
Revenue report: total revenue, vehicles served, current occupancy, and waiting count
Cancel a vehicle's waiting request
Unique feature: automatic itemized parking receipt on vehicle exit
Rates
Car: Rs. 50/hour
Bike: Rs. 20/hour
Truck: Rs. 100/hour
Run in VS Code
Extract the ZIP folder.
Open the ParkingLotManagementSystem folder in VS Code.
Install the Extension Pack for Java if VS Code asks.
Make sure a JDK is installed (JDK 17 or later recommended).
Open Main.java.
Click Run above the main method, or open the VS Code terminal and run:
javac *.java
java Main
Menu
Park Vehicle
Exit Vehicle
Show Status
Revenue Report
Cancel Waiting
Close
Note: Revenue is stored in memory and resets when the program is closed.
