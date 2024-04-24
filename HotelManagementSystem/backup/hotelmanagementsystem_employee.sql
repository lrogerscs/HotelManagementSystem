CREATE DATABASE IF NOT EXISTS hotelmanagementsystem;
USE hotelmanagementsystem;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
	EmployeeID int NOT NULL,
    LoginID int DEFAULT NULL,
    Name varchar(255) DEFAULT NULL,
    Title varchar(255) DEFAULT NULL,
    Email varchar(255) DEFAULT NULL,
    Phone_number varchar(255) DEFAULT NULL,
    Address varchar(255) DEFAULT NULL,
    PRIMARY KEY (EmployeeID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO employee VALUES (1, 1, "Jane Doe", "Receptionist", "janedoe@hotel.com", "(123) 456-7890", "credit card", "123 Jane Lane, San Jose, CA USA");
    
    