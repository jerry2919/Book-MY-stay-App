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

class Main {   // ✅ Changed class name and removed "public"
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "Hotel Booking System v2.1";

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("=====================================");
        System.out.println("        " + appName);
        System.out.println("=====================================");
        System.out.println("Version: " + version);
        System.out.println("-------------------------------------");

        single.displayDetails();
        System.out.println("Available: " + singleAvailability);
        System.out.println("-------------------------------------");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailability);
        System.out.println("-------------------------------------");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailability);
        System.out.println("-------------------------------------");

        System.out.println("System execution completed.");
    }
}