import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void reduceAvailability(String roomType) {
        int count = inventory.get(roomType);
        if (count > 0) {
            inventory.put(roomType, count - 1);
        }
    }

    void displayInventory() {
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.offer(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }
}

class BookingService {
    private RoomInventory inventory;
    private Set<String> allocatedRoomIds = new HashSet<>();
    private HashMap<String, Set<String>> roomAllocations = new HashMap<>();
    private int counter = 1;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void processRequests(BookingRequestQueue queue) {
        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();

            int available = inventory.getAvailability(r.roomType);

            if (available > 0) {
                String roomId = generateRoomId(r.roomType);

                allocatedRoomIds.add(roomId);

                roomAllocations.putIfAbsent(r.roomType, new HashSet<>());
                roomAllocations.get(r.roomType).add(roomId);

                inventory.reduceAvailability(r.roomType);

                System.out.println("Confirmed: " + r.guestName +
                        " | " + r.roomType +
                        " | ID: " + roomId);
            } else {
                System.out.println("Failed: " + r.guestName +
                        " | " + r.roomType +
                        " (No rooms)");
            }
        }
    }

    private String generateRoomId(String type) {
        String id;
        do {
            id = type.substring(0, 2).toUpperCase() + counter++;
        } while (allocatedRoomIds.contains(id));
        return id;
    }
}

class Main {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));

        BookingService service = new BookingService(inventory);

        service.processRequests(queue);

        System.out.println("--------------------");
        inventory.displayInventory();
    }
}