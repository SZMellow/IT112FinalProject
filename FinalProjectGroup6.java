import java.util.*;

public class FinalProjectGroup6 {

 
    // GLOBAL ARRAYS (PLACEHOLDERS)
 
    static String[] roomNumbers;
    static String[] roomTypes;
    static String[] roomStatus;
    static double[] roomRates;

    static String[] reservationGuest;
    static String[] reservationRoom;
    static int[] reservationNights;

    public static void main(String[] args) {

        initializeRooms();
        initializeReservations();
        mainMenu();
    }

 
    // INITIALIZATION
 
    public static void initializeRooms() {
        // TODO: Create room arrays, fill room numbers, types, and rates
    }

    public static void initializeReservations() {
        // TODO: Initialize reservation arrays
    }

 
    // MAIN MENU
 
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Check Room Availability");
            System.out.println("2. Make New Reservation");
            System.out.println("3. Check-In Guest");
            System.out.println("4. Check-Out Guest / Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: checkRoomAvailability(); break;
                case 2: makeReservation(); break;
                case 3: checkInGuest(); break;
                case 4: checkOutGuest(); break;
                case 5: System.out.println("System Exit."); break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

 
    // SYSTEM FUNCTIONS (EMPTY)
 

    public static void checkRoomAvailability() {
        // TODO: Count & display available rooms by type
    }

    public static void makeReservation() {
        // TODO: Input guest name, room type, nights
        // TODO: Find available room
        // TODO: Reserve room and update status
    }

    public static void checkInGuest() {
        // TODO: Input guest name, room number
        // TODO: Verify status and update to Occupied
    }

    public static void checkOutGuest() {
        // TODO: Compute bill: subtotal + service fee + tax
        // TODO: Process payment and compute change
        // TODO: Reset room status to Available
    }

 
    // HELPER METHODS (PLACEHOLDERS)
 

    public static int findAvailableRoom(String type) {
        // TODO: Return index of first available room
        return -1;
    }

    public static void updateRoomStatus(int index, String newStatus) {
        // TODO: Change room status in the array
    }

    public static double calculateSubtotal(int roomIndex, int nights) {
        // TODO: Compute subtotal
        return 0.0;
    }

    public static boolean processPayment(double amountDue, double payment) {
        // TODO: Check sufficient payment
        return false;
    }

    public static double computeChange(double amountDue, double payment) {
        // TODO: Calculate change
        return 0.0;
    }
}
