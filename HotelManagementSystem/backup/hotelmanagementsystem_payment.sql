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
INSERT INTO payment VALUES
(2, 2, 2, 'cash', 150.00, '2024-02-02'),
(3, 3, 3, 'credit card', 300.00, '2024-02-03'),
(4, 4, 4, 'debit card', 250.00, '2024-02-04'),
(5, 5, 5, 'paypal', 400.00, '2024-02-05'),
(6, 6, 6, 'credit card', 350.00, '2024-02-06'),
(7, 7, 7, 'cash', 100.00, '2024-02-07'),
(8, 8, 8, 'credit card', 200.00, '2024-02-08'),
(9, 9, 9, 'paypal', 450.00, '2024-02-09'),
(10, 10, 10, 'cash', 50.00, '2024-02-10');
