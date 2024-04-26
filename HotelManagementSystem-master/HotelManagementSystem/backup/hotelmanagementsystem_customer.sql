CREATE DATABASE IF NOT EXISTS hotelmanagementsystem;
USE hotelmanagementsystem;

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
	CustomerID int NOT NULL,
    RoomID int DEFAULT NULL,
    Name varchar(255) DEFAULT NULL,
    Date_of_birth DATE DEFAULT NULL,
    Age int DEFAULT NULL,
    Email varchar(255) DEFAULT NULL,
    Phone_number varchar(255) DEFAULT NULL,
    Payment_method varchar(255) DEFAULT NULL,
    Address varchar(255) DEFAULT NULL,
    PRIMARY KEY (CustomerID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO customer VALUES (1, 1, "John Doe", '1980-01-01', 44, "johndoe@hotel.com", "(123) 456-7890", "credit card", "123 John Lane, San Jose, CA USA");
INSERT INTO customer VALUES
(2, 2, 'Alice Smith', '1992-05-15', 32, 'alice.smith@example.com', '(987) 654-3210', 'debit card', '456 Maple Street, Seattle, WA, USA'),
(3, 3, 'Bob Johnson', '1985-11-30', 39, 'bob.johnson@example.com', '(111) 222-3333', 'cash', '789 Oak Avenue, New York, NY, USA'),
(4, 4, 'Emily Brown', '1976-08-20', 48, 'emily.brown@example.com', '(444) 555-6666', 'credit card', '101 Pine Street, Chicago, IL, USA'),
(5, 5, 'Michael Davis', '1990-03-10', 34, 'michael.davis@example.com', '(777) 888-9999', 'paypal', '202 Elm Street, Los Angeles, CA, USA'),
(6, 6, 'Sarah Wilson', '1988-12-25', 36, 'sarah.wilson@example.com', '(222) 333-4444', 'credit card', '303 Birch Street, Miami, FL, USA'),
(7, 7, 'David Martinez', '1982-09-05', 42, 'david.martinez@example.com', '(555) 666-7777', 'debit card', '404 Cedar Street, Houston, TX, USA'),
(8, 8, 'Jessica Taylor', '1979-04-17', 45, 'jessica.taylor@example.com', '(999) 888-7777', 'cash', '505 Oak Street, San Francisco, CA, USA'),
(9, 9, 'Christopher Clark', '1995-07-08', 29, 'christopher.clark@example.com', '(333) 222-1111', 'credit card', '606 Pine Street, Boston, MA, USA'),
(10, 10, 'Amanda Rodriguez', '1983-02-28', 41, 'amanda.rodriguez@example.com', '(666) 555-4444', 'paypal', '707 Maple Street, Dallas, TX, USA');

    