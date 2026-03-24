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

    void display() {
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    void showAllBookings(List<Reservation> history) {
        System.out.println("Booking History:");

        for (Reservation r : history) {
            r.display();
        }
    }

    void showSummary(List<Reservation> history) {
        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : history) {
            countMap.put(r.roomType,
                    countMap.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("Booking Summary:");
        for (String type : countMap.keySet()) {
            System.out.println(type + " : " + countMap.get(type));
        }
    }
}

class Main {
    public static void main(String[] args) {

        String appName = "Book My Stay App";
        String version = "Hotel Booking System v8.0";

        System.out.println("=====================================");
        System.out.println("        " + appName);
        System.out.println("=====================================");
        System.out.println("Version: " + version);
        System.out.println("-------------------------------------");

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("R101", "Alice", "Single Room"));
        history.addReservation(new Reservation("R102", "Bob", "Double Room"));
        history.addReservation(new Reservation("R103", "Charlie", "Single Room"));
        history.addReservation(new Reservation("R104", "David", "Suite Room"));

        BookingReportService report = new BookingReportService();

        report.showAllBookings(history.getAllReservations());

        System.out.println("-------------------------------------");

        report.showSummary(history.getAllReservations());

        System.out.println("-------------------------------------");
        System.out.println("System execution completed.");
    }
}