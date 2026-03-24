import java.util.*;

abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: $" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 100.0);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 180.0);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 300.0);
    }
}

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayReservation() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    void displayQueue() {
        System.out.println("Booking Requests in Queue:");

        for (Reservation r : queue) {
            r.displayReservation();
        }
    }
}

class Main {
    public static void main(String[] args) {

        String appName = "Book My Stay App";
        String version = "Hotel Booking System v5.0";

        System.out.println("=====================================");
        System.out.println("        " + appName);
        System.out.println("=====================================");
        System.out.println("Version: " + version);
        System.out.println("-------------------------------------");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Alice", "Single Room"));
        requestQueue.addRequest(new Reservation("Bob", "Double Room"));
        requestQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        requestQueue.displayQueue();

        System.out.println("-------------------------------------");
        System.out.println("System execution completed.");
    }
}