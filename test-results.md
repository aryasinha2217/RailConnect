# Test Results

| ID | Test Case | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| TC-01 | Add valid passenger | Passenger saved to CSV successfully | Passenger added without exceptions | PASS |
| TC-02 | Reject duplicate passenger | Throws `InvalidPassengerException` | Exception thrown and caught | PASS |
| TC-03 | Search passenger | Returns passenger object with correct name | Data matches exactly | PASS |
| TC-04 | Search train | Returns Train object | Object fetched correctly | PASS |
| TC-05 | Successful booking | Valid PNR generated, seat deducted | PNR received, payment processed | PASS |
| TC-06 | Invalid PNR Search | Throws `BookingNotFoundException` | Exception thrown and caught | PASS |
| TC-07 | Successful cancellation | Status changes to CANCELLED, seat restored | Status updated and verified | PASS |

> [!NOTE]  
> All tests were run using the automated `tests/SmartRailTest.java` runner and successfully verified against the core requirements.

**Test Summary: 7 Passed, 0 Failed.**
