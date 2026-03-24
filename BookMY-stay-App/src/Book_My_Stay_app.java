import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class Service {
    String serviceName;
    double cost;

    Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> serviceMap = new HashMap<>();

    void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    void displayServices(String reservationId) {
        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        double total = 0;
        System.out.println("Services for Reservation " + reservationId + ":");

        for (Service s : services) {
            System.out.println("- " + s.serviceName + " : $" + s.cost);
            total += s.cost;
        }

        System.out.println("Total Add-On Cost: $" + total);
    }
}

class Main {
    public static void main(String[] args) {

        String appName = "Book My Stay App";
        String version = "Hotel Booking System v7.0";

        System.out.println("=====================================");
        System.out.println("        " + appName);
        System.out.println("=====================================");
        System.out.println("Version: " + version);
        System.out.println("-------------------------------------");

        Reservation r1 = new Reservation("R101", "Alice", "Single Room");

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService("R101", new Service("Breakfast", 20));
        manager.addService("R101", new Service("Airport Pickup", 50));
        manager.addService("R101", new Service("Extra Bed", 30));

        manager.displayServices("R101");

        System.out.println("-------------------------------------");
        System.out.println("System execution completed.");
    }
}