import java.util.*;

public class FinalProjectGroup6 {

    // ============================================================
    // GLOBAL ARRAYS (Required by PDF)
    // ============================================================
    static String[] roomNumbers = new String[30];
    static String[] roomTypes = new String[30];
    static double[] roomRates = new double[30];
    static String[] roomStatus = new String[30];  // Available / Booked / Occupied

    // 10-day occupancy calendar: 30 rooms × 10 days
    static String[][] occupancy = new String[30][10]; // stores guest names or null

    // ============================================================
    // MAIN METHOD
    // ============================================================
    public static void main(String[] args) {
        initializeRooms();
        initializeCalendar();
        mainMenu();
    }

    // ============================================================
    // INITIALIZATION
    // ============================================================
    public static void initializeRooms() {
        // TODO: Fill roomNumbers, roomTypes, roomRates, roomStatus
    }

    public static void initializeCalendar() {
        // TODO: Set occupancy[][] = null
    }

    // ============================================================
    // MAIN MENU
    // ============================================================
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== GRAND HOTEL SYSTEM ===");
            System.out.println("1. Check Room Availability");
            System.out.println("2. Make New Reservation");
            System.out.println("3. Check-In Guest (Walk-in)");
            System.out.println("4. Check-Out Guest / Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: checkRoomAvailability(); break;
                case 2: makeReservation(); break;
                case 3: checkInGuest(); break;
                case 4: checkOutGuest(); break;
                case 5: System.out.println("Thank you for using the system."); break;
                default: System.out.println("Invalid input.");
            }
        } while (choice != 5);
    }

    // ============================================================
    // PROCESS 1 — CHECK ROOM AVAILABILITY
    // ============================================================
    public static void checkRoomAvailability() {
        // TODO: Count available + booked rooms of a type
        // TODO: Print 10-day calendar
    }

    // ============================================================
    // PROCESS 2 — MAKE NEW RESERVATION
    // ============================================================
    public static void makeReservation() {
        // TODO: Guest inputs, day selection
        // TODO: Find first available room for selected days
        // TODO: Update roomStatus = "Booked"
        // TODO: Update occupancy[][]
        // TODO: Compute reservation fee
    }

    // ============================================================
    // PROCESS 3 — CHECK-IN (WALK-IN)
    // ============================================================
    public static void checkInGuest() {
        // TODO: Guest name, room type, room number, payment
        // TODO: Validate availability
        // TODO: Update roomStatus = "Occupied"
        // TODO: Fill occupancy[][]
    }

    // ============================================================
    // PROCESS 4 — CHECK-OUT + BILLING
    // ============================================================
    public static void checkOutGuest(Scanner sc) {
        // TODO: Compute subtotal, service fee, tax, total
        // TODO: Accept payment, compute change
        // TODO: Reset roomStatus = "Available"
        // TODO: Clear occupancy[][]

        System.out.println("Input Room Number for Check-Out: ");
        String verifyCheckout = sc.next();

        //Verify here

        System.out.println("Subtotal (Room Rate Only): ");
        System.out.println("Fixed Service Fee: ");
        System.out.println("Subtotal + Fee: ");
        System.out.println("Tax (10% of ₱8,250): ");
        System.out.println("Total Amount Due: ");
        System.out.println("Input Final Payment Amount: ");
        int paymentAmount = Integer.parseInt(sc.nextLine());

        System.out.println("Payment: " + " Received");
        int amountPaid = Integer.parseInt(sc.nextLine());

        int totalChange = amountPaid - totalAmountDue;
        System.out.println("Change Calculation: " + amountPaid + " - " + totalAmountDue +  " = " + totalChange);

        System.out.println("--- Final Bill / Receipt ---");
        System.out.println("Guest: " + x + " | " + "Room: " + y);
        System.out.println("TOTAL AMOUNT DUE: " + totalAmountDue);
        System.out.println("Amount Paid: " + amountPaid);
        System.out.println("**Change Due: " + totalChange +" **");
    }

    // ============================================================
    // HELPER METHODS
    // ============================================================
    public static boolean isRoomFreeForDays(int index, int startDay, int nights) {
        // TODO: check occupancy calendar
        return false;
    }

    public static void assignRoomDays(int index, int startDay, int nights, String guest) {
        // TODO: fill occupancy[][] with guest name
    }

    public static void clearRoomDays(int index) {
        // TODO: clear occupancy for given room
    }

    public static double calculateBill(int roomIndex, int nights) {
        // TODO: subtotal, service fee, tax
        return 0;
    }

    public static boolean processPayment(double due, double paid) {
        // TODO: return success or failure
        return false;
    }

    public static double change(double due, double paid) {
        // TODO: compute change
        return 0;
    }

}
