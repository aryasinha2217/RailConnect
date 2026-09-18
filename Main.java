package com.smartrail;

import com.smartrail.exception.InvalidInputException;
import com.smartrail.model.Passenger;
import com.smartrail.model.Ticket;
import com.smartrail.model.Train;
import com.smartrail.payment.CardPayment;
import com.smartrail.payment.PaymentMethod;
import com.smartrail.payment.UpiPayment;
import com.smartrail.repository.DataStore;
import com.smartrail.service.BookingService;
import com.smartrail.service.CancellationService;
import com.smartrail.service.PassengerService;
import com.smartrail.service.ReportService;
import com.smartrail.service.TrainService;
import com.smartrail.util.ConsoleUtils;
import com.smartrail.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DataStore dataStore = new DataStore();
    private static PassengerService passengerService = new PassengerService(dataStore);
    private static TrainService trainService = new TrainService(dataStore);
    private static BookingService bookingService = new BookingService(dataStore, trainService, passengerService);
    private static CancellationService cancellationService = new CancellationService(dataStore, trainService);
    private static ReportService reportService = new ReportService(dataStore);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            ConsoleUtils.printHeader("Smart Rail Reservation System");
            System.out.println("1. Passenger Management");
            System.out.println("2. Train Management");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View Bookings");
            System.out.println("6. Reports");
            System.out.println("7. Exit");
            
            int choice = ConsoleUtils.readInt(scanner, "Enter your choice: ");
            
            switch (choice) {
                case 1:
                    passengerMenu();
                    break;
                case 2:
                    trainMenu();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    cancelTicket();
                    break;
                case 5:
                    viewBookings();
                    break;
                case 6:
                    reportMenu();
                    break;
                case 7:
                    System.out.println("Exiting System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void passengerMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUtils.printHeader("Passenger Management");
            System.out.println("1. Add Passenger");
            System.out.println("2. View Passengers");
            System.out.println("3. Search Passenger");
            System.out.println("4. Update Passenger");
            System.out.println("5. Delete Passenger");
            System.out.println("6. Back");
            
            int choice = ConsoleUtils.readInt(scanner, "Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        addPassenger();
                        break;
                    case 2:
                        List<Passenger> passengers = passengerService.getAllPassengers();
                        if (passengers.isEmpty()) {
                            System.out.println("No passengers found.");
                        } else {
                            for (Passenger p : passengers) {
                                System.out.println(p);
                            }
                        }
                        break;
                    case 3:
                        System.out.print("Enter Passenger ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.println(passengerService.getPassenger(id));
                        break;
                    case 4:
                        updatePassenger();
                        break;
                    case 5:
                        System.out.print("Enter Passenger ID to delete: ");
                        String delId = scanner.nextLine().trim();
                        passengerService.deletePassenger(delId);
                        System.out.println("Passenger deleted successfully.");
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addPassenger() throws Exception {
        System.out.print("Enter Passenger ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        InputValidator.validateName(name);
        int age = ConsoleUtils.readInt(scanner, "Enter Age: ");
        InputValidator.validateAge(age);
        System.out.print("Enter Gender (M/F): ");
        String gender = scanner.nextLine().trim();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine().trim();
        InputValidator.validatePhone(phone);
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        InputValidator.validateEmail(email);

        Passenger p = new Passenger(id, name, age, gender, phone, email);
        passengerService.addPassenger(p);
        System.out.println("Passenger added successfully.");
    }

    private static void updatePassenger() throws Exception {
        System.out.print("Enter Passenger ID to update: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine().trim();
        InputValidator.validateName(name);
        System.out.print("Enter New Phone: ");
        String phone = scanner.nextLine().trim();
        InputValidator.validatePhone(phone);
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine().trim();
        InputValidator.validateEmail(email);

        passengerService.updatePassenger(id, name, phone, email);
        System.out.println("Passenger updated successfully.");
    }

    private static void trainMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUtils.printHeader("Train Management");
            System.out.println("1. View All Trains");
            System.out.println("2. Search Train by Number");
            System.out.println("3. Search Trains by Route");
            System.out.println("4. Back");
            
            int choice = ConsoleUtils.readInt(scanner, "Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        List<Train> trains = trainService.getAllTrains();
                        for (Train t : trains) System.out.println(t);
                        break;
                    case 2:
                        System.out.print("Enter Train Number: ");
                        String tNum = scanner.nextLine().trim();
                        System.out.println(trainService.getTrain(tNum));
                        break;
                    case 3:
                        System.out.print("Enter Source: ");
                        String src = scanner.nextLine().trim();
                        System.out.print("Enter Destination: ");
                        String dest = scanner.nextLine().trim();
                        List<Train> routeTrains = trainService.searchTrains(src, dest);
                        if (routeTrains.isEmpty()) {
                            System.out.println("No trains found on this route.");
                        } else {
                            for (Train t : routeTrains) System.out.println(t);
                        }
                        break;
                    case 4:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void bookTicket() {
        ConsoleUtils.printHeader("Book Ticket");
        try {
            System.out.print("Enter Passenger ID: ");
            String pId = scanner.nextLine().trim();
            passengerService.getPassenger(pId); // Validates if passenger exists

            System.out.print("Enter Train Number: ");
            String tNum = scanner.nextLine().trim();
            Train train = trainService.getTrain(tNum); // Validates if train exists

            System.out.println("Train found: " + train.getTrainName() + ". Fare: ₹" + train.getFare());
            
            System.out.println("Select Payment Method:");
            System.out.println("1. UPI");
            System.out.println("2. Card");
            int payChoice = ConsoleUtils.readInt(scanner, "Enter choice: ");
            
            PaymentMethod paymentMethod;
            if (payChoice == 1) {
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.nextLine().trim();
                paymentMethod = new UpiPayment(upiId);
            } else {
                System.out.print("Enter Card Number (16 digits): ");
                String cardNum = scanner.nextLine().trim();
                paymentMethod = new CardPayment(cardNum);
            }

            Ticket ticket = bookingService.bookTicket(pId, tNum, paymentMethod);
            if (ticket != null) {
                System.out.println("\n--- Booking Confirmed ---");
                System.out.println(ticket);
            }
        } catch (Exception e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }

    private static void cancelTicket() {
        ConsoleUtils.printHeader("Cancel Ticket");
        try {
            System.out.print("Enter PNR: ");
            String pnr = scanner.nextLine().trim();
            Ticket ticket = cancellationService.getTicket(pnr);
            System.out.println("Booking Details: \n" + ticket);
            System.out.print("Are you sure you want to cancel? (Y/N): ");
            String confirm = scanner.nextLine().trim();
            if (confirm.equalsIgnoreCase("Y")) {
                cancellationService.cancelTicket(pnr);
            } else {
                System.out.println("Cancellation aborted.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewBookings() {
        ConsoleUtils.printHeader("All Bookings");
        List<Ticket> tickets = bookingService.getAllBookings();
        if (tickets.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Ticket t : tickets) {
                System.out.println(t);
            }
        }
    }

    private static void reportMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUtils.printHeader("Reports");
            System.out.println("1. Booking Report");
            System.out.println("2. Train Occupancy Report");
            System.out.println("3. Passenger Report");
            System.out.println("4. Back");
            
            int choice = ConsoleUtils.readInt(scanner, "Enter your choice: ");
            switch (choice) {
                case 1:
                    reportService.generateBookingReport();
                    break;
                case 2:
                    reportService.generateTrainOccupancyReport();
                    break;
                case 3:
                    reportService.generatePassengerReport();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
