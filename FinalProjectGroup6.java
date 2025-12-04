import java.util.*;

public class FinalProjectGroup6 {

    // Constants
    static final int TOTAL_ROOMS = 30;
    static final int TOTAL_DAYS = 10;
    static final double SERVICE_FEE = 250.0;
    static final double TAX_RATE = 0.10;


    // Room data
    static String[] roomNumbers = new String[TOTAL_ROOMS];
    static String[] roomTypes = new String[TOTAL_ROOMS];
    static double[] roomRates = new double[TOTAL_ROOMS];
    static String[] roomStatus = new String[TOTAL_ROOMS]; // Available / Booked / Occupied
    static String[][] occupancy = new String[TOTAL_ROOMS][TOTAL_DAYS]; // guest names or null

    static Scanner sc = new Scanner(System.in);

    // MAIN Method
    public static void main(String[] args) {
        initializeRooms();
        initializeCalendar();
        System.out.println("Welcome to the Grand Hotel System!");
        mainMenu();
        System.out.println("Goodbye.");
    }


    // Initialization
    static void initializeRooms() { //initializes room number, type, rates and status using for loops for each room type
        for (int i = 0; i < 15; i++) { // Standard
            //int i will indicate which standard room is initialized
            roomNumbers[i] = "S" + (101 + i);
            roomTypes[i] = "Standard";
            roomRates[i] = 2500;
            roomStatus[i] = "Available";
        }
        for (int i = 0; i < 10; i++) { // Deluxe
            //int idx will indicate which deluxe room is initialized, after initializing standard rooms
            int idx = 15 + i;
            roomNumbers[idx] = "D" + (201 + i);
            roomTypes[idx] = "Deluxe";
            roomRates[idx] = 4000;
            roomStatus[idx] = "Available";
        }
        for (int i = 0; i < 5; i++) { // Suite
            //int idx will indicate which suite room is initialized, after initializing standard and deluxe rooms
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
        int choice;
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Check Room Availability");
            System.out.println("2. Make New Reservation");
            System.out.println("3. Check-In Guest (Walk-in)");
            System.out.println("4. Check-Out Guest / Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = readInt(1, 5);

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
        System.out.println("\n-- CHECK ROOM AVAILABILITY --");
        int typeChoice = selectRoomType();
        String type = roomTypeFromChoice(typeChoice);

        int total = 0, availableOrBooked = 0;
        double price = 0;

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

        System.out.print("Show 10-day table? (y/n): ");
        if (sc.next().trim().equalsIgnoreCase("y")) printRoomTypeCalendar(type);
        sc.nextLine();
    }

    // Make reservation
    static void makeReservation() {
        System.out.println("\n-- MAKE NEW RESERVATION --");
        System.out.print("Guest Name: ");
        String guest = sc.nextLine().trim();

        int typeChoice = selectRoomType();
        String type = roomTypeFromChoice(typeChoice);

        printRoomTypeCalendar(type);

        System.out.print("Enter start day (1-10): ");
        int startDay = readInt(1, 10);
        System.out.print("Enter number of nights: ");
        int nights = readInt(1, 10 - startDay + 1);

        int roomIndex = findFirstAvailableRoomForDays(type, startDay - 1, nights);
        if (roomIndex == -1) {
            System.out.println("No available " + type + " rooms for those days.");
            return;
        }

        double rate = roomRates[roomIndex];
        System.out.println("Room Found: " + roomNumbers[roomIndex]);
        System.out.println("Reservation Fee: ₱" + (rate * nights));

        assignRoomDays(roomIndex, startDay - 1, nights, guest);

        if (roomStatus[roomIndex].equals("Available")) roomStatus[roomIndex] = "Booked";

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
        System.out.print("Enter Room Number: ");
        String rnum = sc.nextLine().trim();

        int roomIndex = findRoomIndexByNumber(rnum);
        if (roomIndex == -1) {
            System.out.println("Invalid room.");
            return;
        }

        String guest = findCurrentOccupant(roomIndex);
        if (guest == null) {
            System.out.println("Room has no occupant.");
            return;
        }

        int nights = countGuestDays(roomIndex, guest);
        double subtotal = roomRates[roomIndex] * nights;
        double fee = SERVICE_FEE;
        double taxed = (subtotal + fee) * TAX_RATE;
        double totalDue = subtotal + fee + taxed;

        System.out.println("--- BILLING ---");
        System.out.println("Guest: " + guest);
        System.out.println("Room: " + rnum);
        System.out.println("Subtotal: ₱" + subtotal);
        System.out.println("Service Fee: ₱" + fee);
        System.out.println("Tax: ₱" + taxed);
        System.out.println("TOTAL: ₱" + totalDue);

        System.out.print("Enter payment: ₱");
        double paid = readDouble();
        if (paid < totalDue) {
            System.out.println("Insufficient payment. Check-out failed.");
            return;
        }
        System.out.println("Payment successful. Change: ₱" + (paid - totalDue));

        clearGuestFromRoom(roomIndex, guest);
        roomStatus[roomIndex] = "Available";
        System.out.println("Check-out complete. Room " + rnum + " is now available.");
    }

    // Print calendar
    static void printRoomTypeCalendar(String type) {
        System.out.println("\n--- AVAILABILITY TABLE (" + type + ") ---");
        System.out.println("       Day1   Day2   Day3   Day4   Day5   Day6   Day7   Day8   Day9   Day10");

        for (int i = 0; i < TOTAL_ROOMS; i++) {
            if (!roomTypes[i].equals(type)) continue;
            System.out.printf("%-5s ", roomNumbers[i]);
            String occupiedGuest = findCurrentlyCheckedInGuest(i);

            for (int d = 0; d < TOTAL_DAYS; d++) {
                String cell = "";
                if (occupancy[i][d] != null) {
                    if (occupiedGuest != null && occupancy[i][d].equals(occupiedGuest))
                        cell = "O";
                    else
                        cell = "B";
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

