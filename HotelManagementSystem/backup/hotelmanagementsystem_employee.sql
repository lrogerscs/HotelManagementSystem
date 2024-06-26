CREATE DATABASE  IF NOT EXISTS `hotelmanagementsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotelmanagementsystem`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: hotelmanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EmployeeID` int NOT NULL,
  `HotelID` int DEFAULT NULL,
  `LoginID` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,1,'Emp1','Jane Doe','Receptionist','janedoe@hotel.com','(123) 456-7890','123 Jane Lane, San Jose, CA, 95123'),
(2,2,'Employee2','John Smith','Housekeeping','johnsmith@hotel.com','(987) 654-3210','456 Maple Street, Seattle, WA, 45741'),
(3,2,'Employee3','Emily Johnson','Chef','emily.johnson@hotel.com','(111) 222-3333','789 Oak Avenue, New York, NY, 04741'),
(4,2,'Employee4','Michael Brown','Front Desk Manager','michael.brown@hotel.com','(444) 555-6666','101 Pine Street, Chicago, IL, 56472'),
(5,2,'Employee5','Sarah Davis','Concierge','sarah.davis@hotel.com','(777) 888-9999','202 Elm Street, Los Angeles, CA, 91234'),
(6,2,'Employee6','David Wilson','Bellhop','david.wilson@hotel.com','(222) 333-4444','303 Birch Street, Miami, FL, 85213'),
(7,1,'Employee7','Jessica Martinez','Housekeeping Supervisor','jessica.martinez@hotel.com','(555) 666-7777','404 Cedar Street, Houston, TX, 42175'),
(8,1,'Employee8','Christopher Taylor','Event Planner','christopher.taylor@hotel.com','(999) 888-7777','505 Oak Street, San Francisco, CA, 91425'),
(9,1,'Employee9','Amanda Clark','Security Officer','amanda.clark@hotel.com','(333) 222-1111','606 Pine Street, Boston, MA, 65572'),
(10,1,'Employee10','Daniel Rodriguez','Maintenance Technician','daniel.rodriguez@hotel.com','(666) 555-4444','707 Maple Street, Dallas, TX, 57471'),
(46,3,'the','Jenna Robert','Cleaner','jennar@hotel.com','(123)-123-123','1 Jenna R. Street, San Francisco, CA, 91425'),
(1000,1,'Admin','George G. Admin','Manager','george@hotel.com','(321)-321-321','1 George Way, San Jose, CA, 95124');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-27 21:59:07
