# Testing Strategy

## Overview
Due to the constraints of not using external frameworks like Maven or Gradle, testing is handled using a custom test runner: `SmartRailTest.java`. 

## Test Scenarios Covered
1. **Adding Valid Passenger**: Tests adding a normal record to persistence.
2. **Rejecting Duplicate Passenger**: Verifies that `InvalidPassengerException` is thrown when inserting an existing ID.
3. **Searching Entities**: Retrieves passenger and train details.
4. **Successful Booking**: Integrates `PassengerService`, `TrainService`, and `BookingService`. Deducts seats, simulates payment, returns PNR.
5. **Invalid PNR Search**: Verifies `BookingNotFoundException`.
6. **Cancellation**: Integrates `CancellationService`. Reverts seat count and updates status.

## Manual Testing Checklist
- [ ] Compile and run without errors.
- [ ] Test menus 1 through 7 manually for crashes (inputting Strings instead of Integers).
- [ ] Verify CSV generation in `data/`.
- [ ] Exit and restart app, checking if old bookings persist.
