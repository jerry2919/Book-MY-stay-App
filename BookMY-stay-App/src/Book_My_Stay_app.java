import java.util.HashMap;

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

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    void displayInventory() {
        System.out.println("Current Room Availability:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

class Main {  
    public static void main(String[] args) {

        String appName = "Book My Stay App";
        String version = "Hotel Booking System v3.1";

        System.out.println("=====================================");
        System.out.println("        " + appName);
        System.out.println("=====================================");
        System.out.println("Version: " + version);
        System.out.println("-------------------------------------");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        single.displayDetails();
        System.out.println("-------------------------------------");

        doubleRoom.displayDetails();
        System.out.println("-------------------------------------");

        suite.displayDetails();
        System.out.println("-------------------------------------");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("-------------------------------------");

        inventory.updateAvailability("Single Room", 4);

        inventory.displayInventory();

        System.out.println("-------------------------------------");
        System.out.println("System execution completed.");
    }
}
