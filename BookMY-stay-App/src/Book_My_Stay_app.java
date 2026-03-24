import java.util.*;

class BookingSystem {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> counters = new HashMap<>();

    BookingSystem() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        counters.put("Single", 1);
        counters.put("Double", 1);
        counters.put("Suite", 1);
    }

    public synchronized void bookRoom(String guestName, String roomType) {

        if (inventory.get(roomType) > 0) {

            String roomId = roomType + "-" + counters.get(roomType);
            counters.put(roomType, counters.get(roomType) + 1);

            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Booking confirmed for Guest: " + guestName +
                    ", Room ID: " + roomId);

        } else {
            System.out.println("Booking failed for " + guestName + " (No rooms)");
        }
    }

    void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class BookingTask extends Thread {

    private BookingSystem system;
    private String guestName;
    private String roomType;

    BookingTask(BookingSystem system, String guestName, String roomType) {
        this.system = system;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void run() {
        system.bookRoom(guestName, roomType);
    }
}

class Main {
    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        BookingSystem system = new BookingSystem();

        Thread t1 = new BookingTask(system, "Abhi", "Single");
        Thread t2 = new BookingTask(system, "Vanmathi", "Double");
        Thread t3 = new BookingTask(system, "Kural", "Suite");
        Thread t4 = new BookingTask(system, "Subha", "Single");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.displayInventory();
    }
}