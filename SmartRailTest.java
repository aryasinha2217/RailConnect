import com.smartrail.exception.*;
import com.smartrail.model.Passenger;
import com.smartrail.model.Ticket;
import com.smartrail.model.Train;
import com.smartrail.payment.UpiPayment;
import com.smartrail.repository.DataStore;
import com.smartrail.service.BookingService;
import com.smartrail.service.CancellationService;
import com.smartrail.service.PassengerService;
import com.smartrail.service.TrainService;

public class SmartRailTest {
    public static void main(String[] args) {
        System.out.println("Starting SmartRail Automated Tests...");
        
        DataStore dataStore = new DataStore();
        PassengerService passengerService = new PassengerService(dataStore);
        TrainService trainService = new TrainService(dataStore);
        BookingService bookingService = new BookingService(dataStore, trainService, passengerService);
        CancellationService cancellationService = new CancellationService(dataStore, trainService);

        int passed = 0;
        int failed = 0;

        // Test 1: Add Valid Passenger
        try {
            Passenger p = new Passenger("TEST01", "John Doe", 30, "M", "9876543210", "john@test.com");
            passengerService.addPassenger(p);
            System.out.println("[PASS] Add valid passenger");
            passed++;
        } catch (Exception e) {
            System.out.println("[FAIL] Add valid passenger: " + e.getMessage());
            failed++;
        }

        // Test 2: Reject Invalid/Duplicate Passenger
        try {
            Passenger p2 = new Passenger("TEST01", "Jane Doe", 25, "F", "1234567890", "jane@test.com");
            passengerService.addPassenger(p2);
            System.out.println("[FAIL] Reject duplicate passenger (Should have thrown exception)");
            failed++;
        } catch (InvalidPassengerException e) {
            System.out.println("[PASS] Reject duplicate passenger");
            passed++;
        } catch (Exception e) {
            System.out.println("[FAIL] Reject duplicate passenger (Wrong exception: " + e.getMessage() + ")");
            failed++;
        }

        // Test 3: Search Passenger
        try {
            Passenger found = passengerService.getPassenger("TEST01");
            if (found != null && found.getName().equals("John Doe")) {
                System.out.println("[PASS] Search passenger");
                passed++;
            } else {
                System.out.println("[FAIL] Search passenger (Wrong data)");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("[FAIL] Search passenger: " + e.getMessage());
            failed++;
        }

        // Test 4: Search Train
        try {
            Train t = trainService.getTrain("12001");
            if (t != null) {
                System.out.println("[PASS] Search train");
                passed++;
            } else {
                System.out.println("[FAIL] Search train");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("[FAIL] Search train: " + e.getMessage());
            failed++;
        }

        // Test 5: Successful Booking
        String bookedPnr = null;
        try {
            Ticket t = bookingService.bookTicket("TEST01", "12001", new UpiPayment("test@upi"));
            if (t != null && t.getStatus().equals("CONFIRMED")) {
                bookedPnr = t.getPnr();
                System.out.println("[PASS] Successful booking (PNR: " + bookedPnr + ")");
                passed++;
            } else {
                System.out.println("[FAIL] Successful booking");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("[FAIL] Successful booking: " + e.getMessage());
            failed++;
        }

        // Test 6: Invalid PNR Search
        try {
            cancellationService.getTicket("INVALID_PNR");
            System.out.println("[FAIL] Invalid PNR Search (Should have thrown exception)");
            failed++;
        } catch (BookingNotFoundException e) {
            System.out.println("[PASS] Invalid PNR Search");
            passed++;
        } catch (Exception e) {
            System.out.println("[FAIL] Invalid PNR Search (Wrong exception)");
            failed++;
        }

        // Test 7: Successful Cancellation
        if (bookedPnr != null) {
            try {
                cancellationService.cancelTicket(bookedPnr);
                Ticket t = cancellationService.getTicket(bookedPnr);
                if (t.getStatus().equals("CANCELLED")) {
                    System.out.println("[PASS] Successful cancellation");
                    passed++;
                } else {
                    System.out.println("[FAIL] Successful cancellation (Status not updated)");
                    failed++;
                }
            } catch (Exception e) {
                System.out.println("[FAIL] Successful cancellation: " + e.getMessage());
                failed++;
            }
        } else {
            System.out.println("[SKIP] Successful cancellation (No PNR available)");
        }

        // Cleanup test data
        try {
            passengerService.deletePassenger("TEST01");
        } catch (Exception e) {
            // ignore cleanup failure
        }

        System.out.println("\nTest Summary: " + passed + " Passed, " + failed + " Failed.");
    }
}
