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
    
    