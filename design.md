# System Design

## Class Diagram

```mermaid
classDiagram
    class User {
        <<abstract>>
        #String name
        #String phone
        #String email
        +getRole() String
    }
    class Passenger {
        -String passengerId
        -int age
        -String gender
        +toCsv() String
    }
    class Admin {
        -String adminId
    }
    
    User <|-- Passenger
    User <|-- Admin
    
    class Train {
        -String trainNumber
        -String trainName
        -int totalSeats
        -int availableSeats
        -double fare
    }
    
    class Ticket {
        -String pnr
        -String passengerId
        -String trainNumber
        -int seatNumber
        -String status
    }
    
    class PaymentMethod {
        <<interface>>
        +processPayment(amount: double) boolean
    }
    
    class UpiPayment
    class CardPayment
    
    PaymentMethod <|.. UpiPayment
    PaymentMethod <|.. CardPayment
```

## Data/Storage Design
Data is stored locally to mimic a NoSQL/Document structure using plain CSV format. 

- **passengers.csv**: `passengerId,name,age,gender,phone,email`
- **trains.csv**: `trainNumber,trainName,source,destination,totalSeats,availableSeats,fare`
- **tickets.csv**: `pnr,passengerId,trainNumber,seatNumber,bookingDate,fare,status`

This design prioritizes simplicity and strict decoupling of persistence (`DataStore`) from the domain (`Services`).
