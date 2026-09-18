# SmartRail - Railway Reservation and Management System

## Goal Description
Build a pure Java CLI application for railway reservation and management. The application will support passenger management, train management, ticket booking, cancellation, and reporting. Data will be persisted in local CSV files. The project will demonstrate core Java OOP concepts, file handling, custom exceptions, and collections without relying on external databases or frameworks.

## User Review Required
> [!IMPORTANT]
> The project will be built entirely using standard Java libraries (Java SE). We will not use Maven or Gradle, and the build process will rely directly on `javac` and `java` commands as requested.

## Proposed Architecture
The application will follow a layered architecture:
- **Presentation Layer (CLI)**: Handles user interaction and menus.
- **Service Layer**: Contains business logic.
- **Repository / Data Access Layer**: Manages file I/O operations (CSV reading/writing).
- **Model Layer**: Represents data entities.
- **Exception Layer**: Custom exceptions for domain-specific errors.
- **Utility Layer**: Helper classes for input validation, formatting, and generating IDs.

## Proposed Changes

### Configuration and Setup
#### [NEW] .gitignore
Standard Java and IDE ignores, keeping data files.
#### [NEW] README.md
Comprehensive documentation on how to build, run, and test.
#### [NEW] statement.md
Problem statement and scope.

---
### Documentation
#### [NEW] docs/architecture.md
#### [NEW] docs/workflow.md
#### [NEW] docs/testing.md
#### [NEW] docs/design.md
Contains system design, Mermaid diagrams, and workflow details.

---
### Data Layer (Files)
#### [NEW] data/passengers.csv
#### [NEW] data/trains.csv
#### [NEW] data/tickets.csv
These will be created automatically by the app if they do not exist.

---
### Application Source Code
#### [NEW] src/com/smartrail/Main.java
Entry point and main menu loop.

#### [NEW] src/com/smartrail/model/User.java
Abstract base class.
#### [NEW] src/com/smartrail/model/Passenger.java
Extends User.
#### [NEW] src/com/smartrail/model/Admin.java
Extends User.
#### [NEW] src/com/smartrail/model/Train.java
#### [NEW] src/com/smartrail/model/Ticket.java

#### [NEW] src/com/smartrail/repository/DataStore.java
Handles file reading and writing.

#### [NEW] src/com/smartrail/service/PassengerService.java
#### [NEW] src/com/smartrail/service/TrainService.java
#### [NEW] src/com/smartrail/service/BookingService.java
#### [NEW] src/com/smartrail/service/CancellationService.java
#### [NEW] src/com/smartrail/service/ReportService.java

#### [NEW] src/com/smartrail/exception/InvalidPassengerException.java
#### [NEW] src/com/smartrail/exception/SeatUnavailableException.java
#### [NEW] src/com/smartrail/exception/BookingNotFoundException.java
#### [NEW] src/com/smartrail/exception/InvalidTrainException.java
#### [NEW] src/com/smartrail/exception/InvalidInputException.java

#### [NEW] src/com/smartrail/payment/PaymentMethod.java
Interface.
#### [NEW] src/com/smartrail/payment/UpiPayment.java
#### [NEW] src/com/smartrail/payment/CardPayment.java

#### [NEW] src/com/smartrail/util/InputValidator.java
#### [NEW] src/com/smartrail/util/PNRGenerator.java
#### [NEW] src/com/smartrail/util/ConsoleUtils.java

---
### Tests (Simple test scripts)
#### [NEW] tests/SmartRailTest.java
Simple test runner to validate core operations without JUnit.

## Verification Plan

### Automated Tests
- We will compile and run `tests/SmartRailTest.java` which will programmatically invoke service methods to simulate adding passengers, booking tickets, cancelling, and expecting custom exceptions when invalid inputs are provided.

### Manual Verification
1. Open terminal/PowerShell.
2. Compile using `javac -d out $(find src -name "*.java")` (or equivalent Windows command).
3. Run using `java -cp out com.smartrail.Main`.
4. Manually test all CLI menu options (1 through 7).
5. Verify persistence by adding a booking, exiting the app, starting it again, and checking the booking report.
