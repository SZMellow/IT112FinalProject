import java.util.*;

public class FinalProjectGroup6 {

    // Constants
    static final int TOTAL_ROOMS = 30;
    static final int TOTAL_DAYS = 10;
    static final double SERVICE_FEE = 250.0;
    static final double TAX_RATE = 0.10;


    // ARRAYS storing room data and occupancy information
    // roomNumbers - Room IDs like S101, D201, etc.
    // roomTypes - Standard / Deluxe / Suite
    // roomRates - Price per night per room
    // roomStatus - Available / Booked / Occupied
    // occupancy - 10-day schedule storing guest names or null
    static String[] roomNumbers = new String[TOTAL_ROOMS];
    static String[] roomTypes = new String[TOTAL_ROOMS];
    static double[] roomRates = new double[TOTAL_ROOMS];
    static String[] roomStatus = new String[TOTAL_ROOMS]; // Available / Booked / Occupied
    static String[][] occupancy = new String[TOTAL_ROOMS][TOTAL_DAYS]; // guest names or null

    static Scanner sc = new Scanner(System.in);

    // MAIN Method
    public static void main(String[] args) {
        // Initializes room info and calendar, then opens main menu
        initializeRooms();
        initializeCalendar();
        System.out.println("Welcome to the Grand Hotel System!");
        mainMenu();
        System.out.println("Goodbye.");
    }


    // Initialization
    static void initializeRooms() {
        for (int i = 0; i < 15; i++) { // Standard
            roomNumbers[i] = "S" + (101 + i);
            roomTypes[i] = "Standard";
            roomRates[i] = 2500;
            roomStatus[i] = "Available";
        }
        for (int i = 0; i < 10; i++) { // Deluxe
            int idx = 15 + i;
            roomNumbers[idx] = "D" + (201 + i);
            roomTypes[idx] = "Deluxe";
            roomRates[idx] = 4000;
            roomStatus[idx] = "Available";
        }
        for (int i = 0; i < 5; i++) { // Suite
            int idx = 25 + i;
            roomNumbers[idx] = "T" + (301 + i);
            roomTypes[idx] = "Suite";
            roomRates[idx] = 8000;
            roomStatus[idx] = "Available";
        }
    }

    static void initializeCalendar() {
        for (int i = 0; i < TOTAL_ROOMS; i++) Arrays.fill(occupancy[i], null);
    }


    // Main Menu
    static void mainMenu() {

        //variable for the choice
        int choice;

        //do while loop to keep showing the menu
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Check Room Availability");
            System.out.println("2. Make New Reservation");
            System.out.println("3. Check-In Guest (Walk-in)");
            System.out.println("4. Check-Out Guest / Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = readInt(1, 5);

            // Switch menu options
            switch (choice) {
                case 1 -> checkRoomAvailability();
                case 2 -> makeReservation();
                case 3 -> checkInGuest();
                case 4 -> checkOutGuest();
            }
        } while (choice != 5);
    }

    // Check availability
    static void checkRoomAvailability() {
        // Displays room availability per type and optionally
        // shows a 10-day visual table
        System.out.println("\n-- CHECK ROOM AVAILABILITY --");
        int typeChoice = selectRoomType();
        String type = roomTypeFromChoice(typeChoice);

        int total = 0, availableOrBooked = 0;
        double price = 0;

        // Count rooms by type and check availability
        for (int i = 0; i < TOTAL_ROOMS; i++) {
            if (roomTypes[i].equals(type)) {
                total++;
                if (!roomStatus[i].equals("Occupied")) availableOrBooked++;
                price = roomRates[i];
            }
        }

        System.out.println("\nRoom Type: " + type);
        System.out.println("Total Rooms: " + total);
        System.out.println("Available/Booked: " + availableOrBooked);
        System.out.println("Price per Night: ₱" + price);

        // Optionally show calendar
        System.out.print("Show 10-day table? (y/n): ");
        if (sc.next().trim().equalsIgnoreCase("y")) printRoomTypeCalendar(type);
        sc.nextLine();
    }

    // Make reservation
    static void makeReservation() {
        System.out.println("\n-- MAKE NEW RESERVATION --");
        System.out.print("Guest Name: ");
        String guest = sc.nextLine().trim(); //reads the input and removes whitespaces

        int typeChoice = selectRoomType(); //gets room type as number from user
        String type = roomTypeFromChoice(typeChoice); //coverts room number into string

        printRoomTypeCalendar(type); //availability for selected room type

        System.out.print("Enter start day (1-10): ");
        int startDay = readInt(1, 10); //input ranges from day 1 to 10
        System.out.print("Enter number of nights: ");
        int nights = readInt(1, 10 - startDay + 1); //limits the number of nights so it doesn't exceed max days

        int roomIndex = findFirstAvailableRoomForDays(type, startDay - 1, nights); //checks the first available room according to the requested date
        if (roomIndex == -1) { //if no room is found
            System.out.println("No available " + type + " rooms for those days.");
            return; //exit
        }

        double rate = roomRates[roomIndex]; //get the rate of selected rooms
        System.out.println("Room Found: " + roomNumbers[roomIndex]);
        System.out.println("Reservation Fee: ₱" + (rate * nights)); //calculates the total cost

        assignRoomDays(roomIndex, startDay - 1, nights, guest); //markselected days for the room

        if (roomStatus[roomIndex].equals("Available")) roomStatus[roomIndex] = "Booked"; //change the status to booked if it is available

        System.out.println("--- RESERVATION SUMMARY ---");
        System.out.println("Guest: " + guest);
        System.out.println("Room: " + roomNumbers[roomIndex]);
        System.out.println("Type: " + type);
        System.out.println("Nights: " + nights);
    }

    // Walk-in check-in
    static void checkInGuest() {
        System.out.println("\n-- CHECK-IN GUEST --");
        System.out.print("Guest Name: ");
        String guest = sc.nextLine().trim();

        int typeChoice = selectRoomType();
        String type = roomTypeFromChoice(typeChoice);

        System.out.print("Enter start day (1-10): ");
        int startDay = readInt(1, 10);
        System.out.print("Enter nights: ");
        int nights = readInt(1, 10 - startDay + 1);

        int roomIndex = findFirstAvailableRoomForDays(type, startDay - 1, nights);
        if (roomIndex == -1) {
            System.out.println("No available rooms of that type.");
            return;
        }

        double due = roomRates[roomIndex] * nights;
        System.out.println("Room available: " + roomNumbers[roomIndex]);
        System.out.println("Payment required: ₱" + due);

        assignRoomDays(roomIndex, startDay - 1, nights, guest);
        roomStatus[roomIndex] = "Occupied";

        System.out.println("CHECK-IN SUCCESSFUL. Guest " + guest + " now occupies " + roomNumbers[roomIndex]);
    }

    // Check-out + billing
    static void checkOutGuest() {

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

    // Print calendar
    static void printRoomTypeCalendar(String type) {
        System.out.println("\n--- AVAILABILITY TABLE (" + type + ") ---");

        // Header
        System.out.printf("%-8s", "Room");
        for (int d = 1; d <= TOTAL_DAYS; d++) {
            System.out.printf("%-7s", "Day" + d);
        }
        System.out.println();

        // Rows
        for (int i = 0; i < TOTAL_ROOMS; i++) {
            if (!roomTypes[i].equals(type)) continue;

            System.out.printf("%-8s", roomNumbers[i]);

            String occupiedGuest = findCurrentlyCheckedInGuest(i);

            for (int d = 0; d < TOTAL_DAYS; d++) {
                String cell = "";
                if (occupancy[i][d] != null) {
                    if (occupiedGuest != null && occupancy[i][d].equals(occupiedGuest))
                        cell = "O"; // occupied
                    else
                        cell = "B"; // booked
                }
                System.out.printf("%-7s", cell);
            }
            System.out.println();
        }
    }

    static String findCurrentlyCheckedInGuest(int roomIndex) {
        if (!roomStatus[roomIndex].equals("Occupied")) return null;
        for (int d = 0; d < TOTAL_DAYS; d++)
            if (occupancy[roomIndex][d] != null) return occupancy[roomIndex][d];
        return null;
    }

    // Helper methods / Sub methods
    static int selectRoomType() {
        System.out.println("\nSelect Room Type:");
        System.out.println("1. Standard (₱2500)");
        System.out.println("2. Deluxe (₱4000)");
        System.out.println("3. Suite (₱8000)");
        System.out.print("Choice: ");
        return readInt(1, 3);
    }

    static String roomTypeFromChoice(int c) {
        return switch (c) {
            case 1 -> "Standard";
            case 2 -> "Deluxe";
            default -> "Suite";
        };
    }

    static int findRoomIndexByNumber(String num) {
        for (int i = 0; i < TOTAL_ROOMS; i++)
            if (roomNumbers[i].equalsIgnoreCase(num)) return i;
        return -1;
    }

    static int findFirstAvailableRoomForDays(String type, int start, int nights) {
        for (int i = 0; i < TOTAL_ROOMS; i++) {
            if (!roomTypes[i].equals(type)) continue;
            if (roomStatus[i].equals("Occupied")) continue;
            boolean free = true;
            for (int d = start; d < start + nights; d++)
                if (occupancy[i][d] != null) { free = false; break; }
            if (free) return i;
        }
        return -1;
    }

    static void assignRoomDays(int index, int start, int nights, String guest) {
        for (int d = start; d < start + nights; d++)
            occupancy[index][d] = guest;
    }

    static String findCurrentOccupant(int roomIndex) {
        for (int d = 0; d < TOTAL_DAYS; d++)
            if (occupancy[roomIndex][d] != null) return occupancy[roomIndex][d];
        return null;
    }

    static int countGuestDays(int roomIndex, String guest) {
        int count = 0;
        for (int d = 0; d < TOTAL_DAYS; d++)
            if (guest.equals(occupancy[roomIndex][d])) count++;
        return count;
    }

    static void clearGuestFromRoom(int index, String guest) {
        for (int d = 0; d < TOTAL_DAYS; d++)
            if (guest.equals(occupancy[index][d])) occupancy[index][d] = null;
    }

    static int readInt(int min, int max) {
        while (true) {
            try {
                int x = sc.nextInt();
                sc.nextLine();
                if (x < min || x > max) throw new Exception();
                return x;
            } catch (Exception e) {
                System.out.print("Invalid. Enter number (" + min + "-" + max + "): ");
                sc.nextLine();
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                double x = sc.nextDouble();
                sc.nextLine();
                return x;
            } catch (Exception e) {
                System.out.print("Invalid amount. Try again: ");
                sc.nextLine();
            }
        }
    }
}


