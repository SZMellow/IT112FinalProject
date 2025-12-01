tyimport java.util.*;

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
        Scanner sc = new Scanner(System.in);

        int totalDays = 10;
        int standardRooms = 15;
        int deluxeRooms = 10;
        int suiteRooms = 5;

        int totalRooms = standardRooms + deluxeRooms + suiteRooms;

        String[][] room = new String[totalRooms][totalDays];
        String status = "Available";

        for (int i = 0; i < totalRooms; i++) {
            for (int j = 0; j < totalDays; j++) {
                room[i][j] = status;
            }
        }

        String[] roomNames = new String[totalRooms];
        for (int i = 0; i < standardRooms; i++) {
            roomNames[i] = "S" + (101 + i);
        }
        for (int i = 0; i < deluxeRooms; i++) {
            roomNames[standardRooms + i] = "D" + (101 + i);
        }
        for (int i = 0; i < suiteRooms; i++) {
            roomNames[standardRooms + deluxeRooms + i] = "T" + (101 + i);
        }

        System.out.print("Input Guest Name: ");
        String name = sc.next();

        System.out.print("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");
        int roomType = sc.nextInt();

        System.out.print("Input number of days: ");
        int daysToReserve = sc.nextInt();

        int[] selectedDays = new int[daysToReserve];
        for (int d = 0; d < daysToReserve; d++) {
            System.out.print("Date " + (d + 1) + ": Day ");
            selectedDays[d] = sc.nextInt() - 1; // convert to 0-indexed
        }

        int startRow = 0, endRow = 0;
        String type = "";
        int fee = 0;

        if (roomType == 1) {
            startRow = 0;
            endRow = standardRooms;
            type = "Standard";
            fee = 2500;
        } else if (roomType == 2) {
            startRow = standardRooms;
            endRow = standardRooms + deluxeRooms;
            type = "Deluxe";
            fee = 4000;
        } else if (roomType == 3) {
            startRow = standardRooms + deluxeRooms;
            endRow = totalRooms;
            type = "Suite";
            fee = 8000;
        } else {
            System.out.println("Invalid room type!");
        }

        boolean reserved = false;
        for (int i = startRow; i < endRow; i++) {
            boolean available = true;
            for (int day : selectedDays) {
                if (!room[i][day].equals("Available")) {
                    available = false;
                    break;
                }
            }
            if (available) {
                for (int day : selectedDays) {
                    room[i][day] = "Reserved";
                }
                reserved = true;
                
                System.out.println("Processing Reservation...");
                System.out.println("Found: " + roomNames[i]);
                System.out.println("Reservation Fee (Room Rate Only): P" + fee + "/night * " + daysToReserve + " nights = P" + (fee * daysToReserve));
                System.out.println("---Reservation Summary---");
                System.out.println("Guest Name: " + name);
                System.out.println("Room Type: " + type);
                System.out.println("Room Number Assigned: " + roomNames[i]);
                System.out.println("Nights Booked: " + daysToReserve);
                System.out.println("Update Status: Room " + roomNames[i] + " is now set to 'Booked' by " + name + ".");
                break;
            }
        }

        if (!reserved) {
            System.out.println("Sorry! No rooms available for all selected days.");
        }
    }

    // ============================================================
    // PROCESS 3 — CHECK-IN (WALK-IN)
    // ============================================================
    public static void checkInGuest() {
        // TODO: Guest name, room type, room number, payment

        // init of variables we gonna use
        Scanner sc = new Scanner(System.in);
        String roomTypeName = "empty";
        int startIndex;
        int endIndex;
        int roomFee = 0;

        // get users name and room type
        System.out.print("Input Guest Name (Walk-in): ");
        String guestName = sc.nextLine();
        System.out.print("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");
        int roomType = Integer.parseInt(sc.nextLine());
        
        // setting the room descr, starting and ending point of finding rooms based sa user input and checking if its valid choice otherwise make user reenter
        while (true) {
            if (roomType == 1) { 
                roomTypeName = "Standard";
                roomFee = 2500;
                startIndex = 0;
                endIndex = 15;
                break;
            } else if (roomType == 2)  {
                roomTypeName = "Deluxe";
                roomFee = 4000;
                startIndex = 15;
                endIndex = 25;
                break;
                } else if (roomType == 3) {
                roomTypeName = "Suite";
                roomFee = 8000;
                startIndex = 25;
                endIndex = 30;
                break;
            } else {
                System.out.println("Error! Please re-enter a valid room type.");
                System.out.print("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");
                roomType = sc.nextInt();
            }
        }

        // get users nights booked
        System.out.print("Input Nights Booked: ");
        int nights = sc.nextInt();;
        
        // checking if user does not input negative or nights higher than 10
        while (nights < 1 || nights > 10) {
            System.out.println("Error! Please re enter a valid number of nights to be booked.");
            System.out.print("Input Nights Booked: ");
            nights = sc.nextInt();
            }

        // prints text saying its checking if rooms are avail (idk nasa sample output)
        System.out.println("Processing Walk-in Check-In... Checking for available " + roomTypeName + " rooms (₱" + roomFee + "/night)...");

        // init of vars to  be used in the algo in finding avail rooms
        int roomIndex = -1; // placeholder -1 is not avalid index for arrays so it means no room is found yet
        int startDay = 0; 

        // TODO: Validate availability
        // main algo for finding if there are avail rooms
        for (int i = startIndex; i < endIndex; i++) {
            if (roomStatus[i].equals("Available") && isRoomFreeForDays(i, startDay, nights)) {
                // if room is avail, we store the index in roomIndex
                roomIndex = i;
                break;
            }
        }

        // after checking and still no free room, roomIndex will stay -1 and this triggers
        if (roomIndex == -1) {
            System.out.println("No " + roomTypeName + " room available for " + nights + " nights.");
            return;
        }

        // init of the rooms details
        String roomNum = roomNumbers[roomIndex];
        int totalPayment = roomFee * nights;

        // prints that a room is found
        System.out.println("Room Found: " + roomNum);

        // init of variables in payment algo
        int paid;
        int choice;

        // main algo for payment
        while (true) {
        System.out.print("Input Payment (Room Only, " + roomFee + " * " + nights + "): ");
        paid = sc.nextInt();

        // if user is rich, then break out of the while loop
        if (paid >= totalPayment) {
            System.out.println("Payment Successful.");
            break;
        }

        // if user is broke, then ask if he wants to re enter his payment, otherwise return to main menu
        System.out.println("Insufficient funds! You need to pay atleast ₱" + totalPayment);
        System.out.print("Do you wish to continue the payment? (1. Yes, 2. No): ");
        choice = sc.nextInt();
        if (choice == 2) {
            System.out.println("Check-in canceled.");
            return;
        }
    }
        
        // TODO: Update roomStatus = "Occupied"
        // sets the roomstatus to occupied
        roomStatus[roomIndex] = "Occupied";

        // TODO: Fill occupancy[][]
        // sets all the nights inputed by user to be registrerd in his name
        for (int d = 0; d < nights; d++) {
        occupancy[roomIndex][d] = guestName;
    }

        // print the status of the rooms to occupied
        System.out.println("Update Status: Room " + roomNum + " is now set to 'Occupied' by " + guestName + ".");
        System.out.println("--- Check-In Successful ---");
        // (nights > 1 ? "s." : ".") checks  kung the user's nights are more than 1, it adds a "s" if it is, else stay as "night"
        System.out.println("Guest " + guestName + " is now occupying Room " + roomNum + " for " + nights + " night" + (nights > 1 ? "s." : "."));
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






