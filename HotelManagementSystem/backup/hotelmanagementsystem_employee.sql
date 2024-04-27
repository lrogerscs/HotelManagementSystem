CREATE DATABASE IF NOT EXISTS hotelmanagementsystem;
USE hotelmanagementsystem;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
	EmployeeID int NOT NULL,
	HotelID int DEFAULT NULL,
    LoginID varchar(255) DEFAULT NULL,
    Name varchar(255) DEFAULT NULL,
    Title varchar(255) DEFAULT NULL,
    Email varchar(255) DEFAULT NULL,
    Phone_number varchar(255) DEFAULT NULL,
    Address varchar(255) DEFAULT NULL,
    PRIMARY KEY (EmployeeID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO employee VALUES (1, 1, "Jane Doe", "Receptionist", "janedoe@hotel.com", "(123) 456-7890", "credit card", "123 Jane Lane, San Jose, CA USA");
INSERT INTO employee VALUES
(2, 2, 'John Smith', 'Housekeeping', 'johnsmith@hotel.com', '(987) 654-3210', '456 Maple Street, Seattle, WA, USA'),
(3, 3, 'Emily Johnson', 'Chef', 'emily.johnson@hotel.com', '(111) 222-3333', '789 Oak Avenue, New York, NY, USA'),
(4, 4, 'Michael Brown', 'Front Desk Manager', 'michael.brown@hotel.com', '(444) 555-6666', '101 Pine Street, Chicago, IL, USA'),
(5, 5, 'Sarah Davis', 'Concierge', 'sarah.davis@hotel.com', '(777) 888-9999', '202 Elm Street, Los Angeles, CA, USA'),
(6, 6, 'David Wilson', 'Bellhop', 'david.wilson@hotel.com', '(222) 333-4444', '303 Birch Street, Miami, FL, USA'),
(7, 7, 'Jessica Martinez', 'Housekeeping Supervisor', 'jessica.martinez@hotel.com', '(555) 666-7777', '404 Cedar Street, Houston, TX, USA'),
(8, 8, 'Christopher Taylor', 'Event Planner', 'christopher.taylor@hotel.com', '(999) 888-7777', '505 Oak Street, San Francisco, CA, USA'),
(9, 9, 'Amanda Clark', 'Security Officer', 'amanda.clark@hotel.com', '(333) 222-1111', '606 Pine Street, Boston, MA, USA'),
(10, 10, 'Daniel Rodriguez', 'Maintenance Technician', 'daniel.rodriguez@hotel.com', '(666) 555-4444', '707 Maple Street, Dallas, TX, USA');

    