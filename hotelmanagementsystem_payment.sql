CREATE DATABASE IF NOT EXISTS hotelmanagementsystem;
USE hotelmanagementsystem;

DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
	PaymentID int NOT NULL,
    CustomerID int DEFAULT NULL,
    RoomID int DEFAULT NULL,
    Method varchar(255) DEFAULT NULL,
    Amount double DEFAULT NULL,
    Pay_date DATE DEFAULT NULL,
    PRIMARY KEY (PaymentID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO payment VALUES (1, 1, 1, "credit card", 200.00, "2024-02-01");