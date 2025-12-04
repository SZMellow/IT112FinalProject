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
        //Standard rooms
        public int[][] standardRooms = new int[15][10];
        //Deluxe rooms
        public int[][] deluxeRooms = new int[10][10];
        //Suite rooms
        public int[][] suiteRooms = new int[5][10];

        /*First array will indicate room number in respective room types
          Second array will indicate the day
          int values: 0 = Vacant 1 = Booked 2 = Occupied*/
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

          System.out.println("\n-- CHECK-OUT GUEST --");

        // Ask for the room number to process the check-out
        System.out.print("Enter Room Number: ");
        String rnum = sc.nextLine().trim();

        // Look for the matching room index
        int roomIndex = findRoomIndexByNumber(rnum);
        if (roomIndex == -1) {
            System.out.println("Invalid room.");
            return;
        }

        // Check if the room actually has someone staying in it
        String guest = findCurrentOccupant(roomIndex);
        if (guest == null) {
            System.out.println("Room has no occupant.");
            return;
        }

        // --- Billing computations ---

        // Count how many nights the guest stayed
        int nights = countGuestDays(roomIndex, guest);

        // Basic room charge
        double subtotal = roomRates[roomIndex] * nights;

        // Fixed service fee
        double fee = SERVICE_FEE;

        // Combine room charge + service fee
        double subandfee = subtotal + fee;

        // Compute 10% tax based on the above
        double taxed = subandfee * TAX_RATE;

        // Final amount the guest needs to pay
        double totalDue = subandfee + taxed;

        // --- Bill Breakdown Display ---
        System.out.println("\n--- Bill Calculations ---");
        System.out.println("Subtotal (Room rate only): ₱" + subtotal);
        System.out.println("Subtotal + Fixed Service Fee: ₱" + subandfee);
        System.out.println("Tax (10% of " + subandfee + "): ₱" + taxed);
        System.out.println("Total Amount Due: ₱" + subandfee + " + ₱" + taxed + " = ₱" + totalDue);

        // Ask the guest how much they're paying
        System.out.print("Input final payment amount: ₱");
        double paid = readDouble();
        System.out.println("Payment: ₱" + paid + " received.");

        // Show the change calculation
        System.out.println("Change calculation: ₱" + paid + " - ₱" + totalDue + " = ₱" + (paid - totalDue));

        // If they didn't pay enough, stop the check-out
        if (paid < totalDue) {
            System.out.println("Insufficient payment. Check-out failed.");
            return;
        }

        // --- Final Receipt ---
        System.out.println("\n--- Final Bill / Receipt ---");
        System.out.println("Guest: " + guest + " | Room: " + rnum);
        System.out.println("TOTAL AMOUNT DUE: ₱" + totalDue);
        System.out.println("Amount Paid: ₱" + paid);
        System.out.println("Change Due: ₱" + (paid - totalDue));

        // Clear the guest's stay info and free the room
        clearGuestFromRoom(roomIndex, guest);
        roomStatus[roomIndex] = "Available";

        // Confirm completion
        System.out.println("Check-out complete. Room " + rnum + " is now available.");
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


