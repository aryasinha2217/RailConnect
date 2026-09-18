# SmartRail – Railway Reservation and Management System

## 1. Project Overview
SmartRail is a pure Java-based command-line interface (CLI) application for managing railway reservations. It allows admins to manage trains and users to manage their profiles, book tickets, and cancel bookings. It relies on standard Java without external databases, using CSV files for persistence.

## 2. Problem Statement
Managing train bookings manually is error-prone. SmartRail provides a straightforward, file-backed CLI system for small-scale demonstration of automated railway ticket reservations.

## 3. Objectives
- Provide a robust CLI interface.
- Store data persistently in text/CSV files.
- Demonstrate Core Java OOP concepts.

## 4. Features
- **Passenger Management**: Add, view, search, update, delete passengers.
- **Train Management**: Search trains by number/route, manage seat availability.
- **Ticket Booking**: Check availability, process mock payment, generate PNR, update seats.
- **Ticket Cancellation**: Verify PNR, update status, restore seat.
- **Reports**: View occupancy, revenue, and passenger statistics.

## 5. Functional Modules
1. `Passenger Management`
2. `Train Management`
3. `Ticket Booking`
4. `Ticket Cancellation`
5. `Reporting`

## 6. Java Concepts Demonstrated
- Classes and Objects (e.g. `Train`, `Ticket`)
- Encapsulation (private fields, getters/setters)
- Inheritance (`Passenger` and `Admin` extend `User`)
- Polymorphism (Overriding `getRole()` in `User`)
- Interfaces (`PaymentMethod`)
- Collections (`ArrayList`, `List`)
- File Handling (`BufferedReader`, `BufferedWriter`, `Files`)
- Custom Exceptions (`InvalidPassengerException`, `SeatUnavailableException`, etc.)
- Modular Package Structure.

## 7. Technologies Used
- Java SE (Standard Edition)
- Standard text I/O for storage (No MySQL)

## 8. Project Structure
- `src/com/smartrail/model/`: Data classes
- `src/com/smartrail/service/`: Business logic
- `src/com/smartrail/repository/`: File handling
- `src/com/smartrail/exception/`: Custom exceptions
- `src/com/smartrail/util/`: Validation and helpers
- `src/com/smartrail/payment/`: Payment interfaces
- `tests/`: Automated tests
- `docs/`: Markdown documentation
- `data/`: CSV files (generated at runtime)

## 9. Requirements
- JDK 8 or higher.
- Terminal / Command Prompt.

## 10. Installation
```bash
git clone <repository_url>
cd SmartRail
```

## 11. Compilation
From the project root:

**Windows (PowerShell) - Verified Environment:**
```powershell
javac -d out (Get-ChildItem -Path src, tests -Filter "*.java" -Recurse).FullName
```

**Linux / macOS:**
```bash
javac -d out $(find src -name "*.java")
```

## 12. Running the Project
From the project root:

**Windows (PowerShell) / Linux / macOS:**
```powershell
java -cp out com.smartrail.Main
```

## 13. Sample Usage
1. Launch application.
2. Select `1` for Passenger Management, add a passenger.
3. Select `3` for Book Ticket, enter your Passenger ID, and train number (e.g., `12001`).
4. View PNR confirmation.
5. Exit and restart the app to verify your booking is saved.

## 14. Testing
Run the automated test suite without a framework:

**Windows (PowerShell) / Linux / macOS:**
```powershell
java -cp out SmartRailTest
```

## 15. Exception Handling
Handled gracefully across all inputs:
- Custom exceptions (e.g., `SeatUnavailableException`).
- `InputValidator` checks strings and digits.
- Catch blocks in `Main.java` ensure the application does not crash.

## 16. Data Storage
Stored in `data/` directory:
- `passengers.csv`
- `trains.csv`
- `tickets.csv`
Application auto-generates this directory and loads sample trains if missing.

## 17. Future Enhancements
- GUI implementation using JavaFX.
- Migration to a relational database (JDBC/SQL).
- Multi-threading for concurrent bookings.

## 18. Author
University Evaluation Project
