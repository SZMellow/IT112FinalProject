Final Group Project for IT112 (Computer Programming 1)

Person
Module
Main Tasks
1 - Room Inventory : Initialize rooms, manage array, update statuses
2 - Main Menu : Display and handle menu navigation
3 - Room Availability : Count & display available rooms
4 - Reservations : Booking logic, assign rooms, compute reservation fee
5 - Check-In : Guest walk-in, verify room, update to Occupied
6 - Check-Out & Billing : Final bill computation, change room to Available
7 - Payment System : Payment validation for all modules

1. Jose
2. a
3. a
4. a
5. a
6. Elvin
7. a


                   ┌──────────────────┐
                   │      START       │
                   └───────┬──────────┘
                           │
                 ┌─────────▼──────────┐
                 │  Initialize Arrays │
                 └─────────┬──────────┘
                           │
                 ┌─────────▼──────────┐
                 │   Display Menu     │
                 └─────────┬──────────┘
                           │
           ┌───────────────┼─────────────────────────┐
           │               │                         │
   ┌───────▼───────┐ ┌─────▼──────────┐     ┌────────▼─────────┐
   │ 1. Check      │ │ 2. Make        │     │ 3. Check-In      │
   │ Availability  │ │ Reservation    │     │ Guest            │
   └───────┬───────┘ └─────┬──────────┘     └────────┬─────────┘
           │               │                         │
   ┌───────▼───────┐ ┌──────▼──────────┐      ┌────────▼─────────┐
   │Input Type     │ │Input: Name, Type│      │Input: Name, Type,│
   │(Standard etc.)│ │Nights           │      │Room Number       │
   └───────┬───────┘ └──────┬──────────┘      └────────┬─────────┘
           │                │                          │
   ┌───────▼───────┐  ┌──────▼─────────┐       ┌────────▼─────────┐
   │Count Rooms    │  │Find Available  │       │Verify Room Status│
   │& Display Info │  │Room            │       └────────┬─────────┘
   └───────┬───────┘  └──────┬─────────┘                │
           │                 │                          │
           │         ┌───────▼─────────┐         ┌──────▼──────────┐
           │         │Mark as Booked   │         │Mark as Occupied │
           │         │Store Reservation│         └──────┬──────────┘
           │         └───────┬─────────┘                │
           │                 │                          │
           └─────────────────┴──────────────────────────┘
                           │
                   ┌───────▼─────────┐
                   │4. Check-Out     │
                   └───────┬─────────┘
                           │
               ┌───────────▼────────────┐
               │Input Room Number, Pay  │
               └───────────┬────────────┘
                           │
               ┌───────────▼────────────┐
               │Compute: Subtotal, Tax, │
               │Service Fee, Total Due  │
               └───────────┬────────────┘
                           │
               ┌───────────▼────────────┐
               │Process Payment         │
               └───────────┬────────────┘
                           │
               ┌───────────▼────────────┐
               │Reset Room Status       │
               │to Available            │
               └───────────┬────────────┘
                           │
               ┌───────────▼────────────┐
               │Return to Main Menu?    │
               └───────┬────────┬───────┘
                       │Yes      │No
                       │         │
             ┌─────────▼───┐   ┌▼────────┐
             │  Main Menu  │   │  END    │
             └─────────────┘   └─────────┘

