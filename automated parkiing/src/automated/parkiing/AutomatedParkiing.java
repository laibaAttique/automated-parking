
package fst.start.of.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException ;
import java.text.SimpleDateFormat;
// Abstract Vehicle class
class User {
    
    private String email;
    private String password;

    public User( String email, String password) {
      
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
}
abstract class Vehicle {
    private String licensePlate;
    private String type;
  

    public Vehicle(String licensePlate, String type) {
        this.licensePlate = licensePlate;
        this.type = type;
      
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

}
// Car class
class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, "Car");
    }   
}
// Motorcycle class
class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, "Motorcycle");
    }   
}
// ParkingSpot class
class ParkingSpot {
    private int spotNumber;
    private Vehicle parkedVehicle;
    private Date entryTime;
    private Date exitTime;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public boolean isOccupied() {
        return parkedVehicle != null;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        parkedVehicle = vehicle;
        entryTime = new Date(); // Record entry time
    }

    public void vacateSpot() {
        parkedVehicle = null;
        exitTime = new Date(); // Record exit time
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }
}
class ParkingLot {
    private int capacity;
    private ArrayList<ParkingSpot> parkingSpots;
    private ArrayList<Vehicle> parkedVehicles;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        parkingSpots = new ArrayList<>(capacity);
        parkedVehicles = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            parkingSpots.add(new ParkingSpot(i + 1));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to select a parking spot by your choice? (yes/no)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("Enter the spot number:");
            int spotNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (spotNumber >= 1 && spotNumber <= capacity) {
                ParkingSpot spot = parkingSpots.get(spotNumber - 1);
                if (!spot.isOccupied()) {
                    spot.parkVehicle(vehicle);
                    parkedVehicles.add(vehicle);
                    return true;
                }
            }
            System.out.println("Invalid spot number or spot is already occupied.");
        } else {
            for (ParkingSpot spot : parkingSpots) {
                if (!spot.isOccupied()) {
                    spot.parkVehicle(vehicle);
                    parkedVehicles.add(vehicle);
                    System.out.println("Vehicle parked successfully at spot number: " + spot.getSpotNumber());
                    return true;
                }
            }
        }
        System.out.println("No available parking spots.");
        return false;
    }

    public boolean vacateSpot(int spotNumber) {
        if (spotNumber >= 1 && spotNumber <= capacity) {
            ParkingSpot spot = parkingSpots.get(spotNumber - 1);
            if (spot.isOccupied()) {
                spot.vacateSpot();
                parkedVehicles.remove(spot.getParkedVehicle());
                return true;
            }
        }
        return false;
    }

    public int calculateAttendance() {
        int attendance = 0;
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getEntryTime() != null && spot.getExitTime() != null) {
                attendance++;
            }
        }
        return attendance;
    }

    public int getCapacity() {
        return capacity;
    }
}
class Reservation {
    private ParkingLot parkingLot;
    private Date reservationDateTime;
    private Date parkingStartDateTime;
    private int durationHours;
    private boolean selectParkingSpot;

    public Reservation(ParkingLot parkingLot, Date reservationDateTime, Date parkingStartDateTime, int durationHours,boolean selectParkingSpot) {
        this.parkingLot = parkingLot;
        this.reservationDateTime = reservationDateTime;
        this.parkingStartDateTime = parkingStartDateTime;
        this.durationHours = durationHours;
        this.selectParkingSpot = selectParkingSpot;
    }

    // Getters and setters for Reservation class attributes

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Date getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(Date reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public Date getParkingStartDateTime() {
        return parkingStartDateTime;
    }

    public void setParkingStartDateTime(Date parkingStartDateTime) {
        this.parkingStartDateTime = parkingStartDateTime;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }
     public boolean isSelectParkingSpot() {
        return selectParkingSpot;
    }

    public void setSelectParkingSpot(boolean selectParkingSpot) {
        this.selectParkingSpot = selectParkingSpot;
    }
    
}

public class FstStartOfProject {

  public static void main(String[] args) {
     
     
         Scanner scanner = new Scanner(System.in);
        System.out.println("log in");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
       
        // Store user details in a data structure (e.g., list or map)
        // For simplicity, let's assume we store user details in an ArrayList
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(email, password));

        System.out.println("User log in successfully!");
        ParkingLot parkingLot = new ParkingLot(10);
        

        System.out.println("Welcome to the Parking System");
        System.out.println("-----------------------------");

        System.out.print("Enter license plate number: ");
        String licensePlate = scanner.nextLine();

        // Loop until a valid vehicle type is entered
        String vehicleType;
        Vehicle vehicle;
        while (true) {
            System.out.print("Enter type of vehicle (Car/Motorcycle): ");
            vehicleType = scanner.nextLine();

            if (vehicleType.equalsIgnoreCase("Car")) {
                vehicle = new Car(licensePlate);
                break; // Exit the loop if the input is valid
            } else if (vehicleType.equalsIgnoreCase("Motorcycle")) {
                vehicle = new Motorcycle(licensePlate);
                break; // Exit the loop if the input is valid
            } else {
                System.out.println("Invalid vehicle type entered. Please enter 'Car' or 'Motorcycle'.");
            }
        }
        boolean parked = parkingLot.parkVehicle(vehicle);
                
        // Ask the user for the choice of reservation
    System.out.println("Do you want to make a reservation? (yes/no)");
    String choice = scanner.nextLine();
    boolean selectParkingSpot = choice.equalsIgnoreCase("yes");

    // Gather reservation details if user wants to make a reservation
    Reservation reservation = null;
    if (selectParkingSpot) {
        // Get reservation details from the user
        // For simplicity, let's assume reservation dates and duration are entered without validation
        System.out.print("Enter reservation start date and time (yyyy-MM-dd HH:mm): ");
        String startDateTimeStr = scanner.nextLine();
        Date startDateTime = null;
            try {
                startDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDateTimeStr);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter date in the format yyyy-MM-dd HH:mm.");
                return;
            }

        System.out.print("Enter duration of reservation (in hours): ");
         int durationHours = 0;
        try {
        durationHours = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                return;
        }
        reservation = new Reservation(parkingLot, new Date(), startDateTime, durationHours, selectParkingSpot);
    }
    // Check if a reservation is made and calculate payment
        if (reservation != null) {
            int paymentAmount = payment(vehicle.getType(), reservation.getDurationHours(),reservation.isSelectParkingSpot());
            System.out.println("Your parking fee is: Rs. " + paymentAmount);
        }
    System.out.println("-----------------------------");
    System.out.println("Thank you for using our Parking System.");
}

// Method to calculate payment based on vehicle type and duration hours
    public static int payment(String vehicleType, int durationHours,boolean selectParkingSpot) {
        int costPerHour = 0;
        if (vehicleType.equalsIgnoreCase("Car")) {
            costPerHour = 50; // Rs. 50 per hour for cars
        } else if (vehicleType.equalsIgnoreCase("Motorcycle")) {
            costPerHour = 100; // Rs. 100 per hour for motorcycles
        }
        // Additional charge if the user selected the parking spot
        int extraCharge = selectParkingSpot ? 50 : 0; // Rs. 50 extra if the user selected the parking spot

        return (costPerHour * durationHours) + extraCharge;
    }

}

    
