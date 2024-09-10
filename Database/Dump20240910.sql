-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: abc_restaurant
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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `restaurant_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (2,'Cool Drinks',1001),(3,'Hot Drinks',1001),(4,'Snacks',1001),(5,'Beverages',1001),(6,'Fruit Juice',1002),(7,'Snacks',1002),(8,'Foods',1003),(9,'Cool Drinks',1003);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contactNumber` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Muthukuda Arachchilage','Annette','annette.ma19@gmail.com','944/116A, Morakelewaththa','0705840810','12345','2024-08-13 05:50:58'),(2,'Jayakodi Arachchilage','Isuranga','isuranga@gmail.com','Thalammehera, Pannala','070584456','1234','2024-08-13 09:02:01'),(3,'Muthukuda Arachchilage','Sihina','sihina@gmail.com','No.34, Koralewaththa','070584458','1234','2024-08-13 09:05:00'),(4,'Hetti Arachchilage','Apasra','apsara@gmail.com','16A, Morakelewaththa','0705840845','1234','2024-08-13 10:30:14'),(5,'Arachchilage','Vihansa','vihansa@gmail.com','Makandura','0705874569','12345','2024-08-13 12:45:09'),(6,'Muthukuda ','Annette','annette@gmail.com','Morakelewaththa','0705840845','12345','2024-08-13 12:56:06'),(8,'Kaveesha','Nethmini','kavi@gmail.com','Pannala','0712345987','12345','2024-08-23 02:22:20'),(9,'Swetha','Ishadini','swetha@gmail.com','Pannala','0701234567','12345','2024-08-23 03:41:02'),(10,'Pathum','Fernando','pathum@gmail.com','Seeduwa','0712345678','12345','2024-08-23 04:18:18'),(11,'Ann','Cristina','ann@gmail.com','Dankotuw','0705840810','12345','2024-08-23 05:24:43'),(12,'Gayan','Harshana','gayan@gmail.com','116A,Korelewaththa','0701345678','12345','2024-08-23 05:31:31'),(13,'Vihara','Sithumini','vihara@gmail.com','944/116A, Morakelewaththa','0705840810','12345','2024-08-23 12:08:36'),(14,'Gihani','Lakshika','gihani@gmail.com','Kurunegala','0704512369','12345','2024-08-26 00:29:59'),(15,'Samodya','Rathnayeka','samodya@gmail.com','Colombo','0705840810','12345','2024-08-26 00:44:38'),(16,'Manumi','Siyathra','manumi@gmail.com','Makandura','0702345678','12345','2024-08-28 15:01:29'),(17,'Muthukuda Arachchilage','Rasika','rasika@gmail.com','Makandura','0705841000','12345','2024-09-05 06:17:41'),(18,'Muthukuda Arachchilage','Annette','annettecristina185@gmail.com','Makandura, Gonavila','0701234567','12345','2024-09-05 09:24:11');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilities`
--

DROP TABLE IF EXISTS `facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facilities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `location` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilities`
--

LOCK TABLES `facilities` WRITE;
/*!40000 ALTER TABLE `facilities` DISABLE KEYS */;
INSERT INTO `facilities` VALUES (1,'Outdoor Seating','Comfortable patio & garden seating for guests who prefer to dine outdoors, surrounded by greenery','outdoor dine in.jpg','Kurunegala'),(2,'Indoor Dining Area','Spacious and elegantly furnished indoor dining space offering a cozy atmosphere.','indoor dine in.jpg','Colombo'),(3,'Private Dining Rooms','Exclusive rooms available for private gatherings, parties, or business meetings, equipped with necessary amenities','private-dining-room.jpg','Colombo'),(4,'Bar Area','A section of the restaurant that serves alcoholic beverages, cocktails, and specialized drinks','Bar area.jpg','Negambo'),(7,'Play Area','Play area for kids below 10 years','play area.jpg','Colombo');
/*!40000 ALTER TABLE `facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery`
--

DROP TABLE IF EXISTS `gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (1,'Crab curry','crab curry.jpg'),(2,'brinjal curry','brinjal.jpg'),(3,'Dishes','dishes.jpg');
/*!40000 ALTER TABLE `gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_items`
--

DROP TABLE IF EXISTS `menu_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `description` text,
  `category_id` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `menu_items_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_items`
--

LOCK TABLES `menu_items` WRITE;
/*!40000 ALTER TABLE `menu_items` DISABLE KEYS */;
INSERT INTO `menu_items` VALUES (2,'Orange Juice',420.00,'made with oranges ',2,'Orange-Juice.jpeg'),(3,'Coffee',300.00,'Cappuccino ',3,'coffee.jpeg'),(4,'Orange Juice',420.00,'Made with natural orange',6,'Orange-Juice.jpeg'),(6,'Falooda',400.00,'Coffee rose-flavored dessert drink with many layers of different elements such as jelly, basil seeds, vermicelli, rose syrup, sweetened milk, ice cream, and the topping of nuts and rose petals. ',9,'falooda.jpg'),(7,'Set Menu',450.00,'Rice with four couriers including chicken, fish or egg',8,'dishes.jpg');
/*!40000 ALTER TABLE `menu_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `offer_name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `discount_percentage` decimal(5,2) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `image` varchar(255) NOT NULL,
  `restaurant_id` int DEFAULT NULL,
  `apply_to_all_restaurants` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `offers_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'Pizza Offer','Get 20% off to your favorite pizza on this week. ',20.00,'2024-08-31 08:55:00','2024-09-07 08:55:00','Pizza offer.png',NULL,1),(2,'Special Offer','Special offer this menu',40.00,'2024-08-31 06:00:00','2024-08-31 23:59:00','Special Offer.png',1001,0),(4,'Hot Weekend Offer','Weekend Offer for the chicken burger',10.00,'2024-08-31 06:00:00','2024-09-06 23:59:00','Hot Weekend Offer.png',NULL,1);
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `promo_id` int NOT NULL AUTO_INCREMENT,
  `promo_code` varchar(50) NOT NULL,
  `description` text,
  `discount` decimal(5,2) NOT NULL,
  `validFrom` datetime DEFAULT NULL,
  `validTo` datetime DEFAULT NULL,
  PRIMARY KEY (`promo_id`),
  UNIQUE KEY `promo_code` (`promo_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `queries`
--

DROP TABLE IF EXISTS `queries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `queries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `query_text` text,
  `query_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `response_text` text,
  `response_date` timestamp NULL DEFAULT NULL,
  `staff_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Pending',
  PRIMARY KEY (`id`),
  KEY `reservation_id` (`reservation_id`),
  KEY `customer_id` (`customer_id`),
  KEY `staff_id` (`staff_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `queries_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`),
  CONSTRAINT `queries_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `queries_ibfk_3` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`),
  CONSTRAINT `queries_ibfk_4` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `queries`
--

LOCK TABLES `queries` WRITE;
/*!40000 ALTER TABLE `queries` DISABLE KEYS */;
INSERT INTO `queries` VALUES (1,4,3,'Muthukuda Arachchilage Sihina','I have a query regarding the service of staff. I\'m not satisfy with the service. ','2024-09-03 12:49:51','Thank you for your feedback. We will address this issue.','2024-09-03 13:18:41',9,NULL,'Answered'),(2,5,3,'Muthukuda Arachchilage Sihina','I\'m not satisfy with the service','2024-09-03 13:26:26','Thank you for your feedback. We will address this issue.','2024-09-05 13:53:59',9,1,'Answered'),(3,11,3,'Muthukuda Arachchilage Sihina','Not satisfied.','2024-09-03 13:59:48',NULL,NULL,NULL,NULL,'Pending'),(4,15,11,'Ann Cristina','Satisfied with the service','2024-09-05 04:35:45',NULL,NULL,NULL,NULL,'Pending'),(5,5,3,'Muthukuda Arachchilage Sihina','Why my reservation is cancelled ','2024-09-05 12:30:42',NULL,NULL,NULL,NULL,'Pending'),(6,16,18,'Muthukuda Arachchilage Annette','I\'m not satisfied with service.','2024-09-05 13:35:53',NULL,NULL,NULL,NULL,'Pending'),(7,15,11,'Ann Cristina','I have a concern about the service quality.','2024-09-06 14:28:47',NULL,NULL,NULL,NULL,'Pending');
/*!40000 ALTER TABLE `queries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `report_name` varchar(100) NOT NULL,
  `report_date` date NOT NULL,
  `report_content` text,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `restaurant_id` int DEFAULT NULL,
  `reservation_date` timestamp NULL DEFAULT NULL,
  `reservation_type` enum('Dine-In','Takeaway','Delivery') NOT NULL,
  `number_of_guests` int DEFAULT NULL,
  `additional_facilities` text,
  `status` enum('Pending','Confirmed','Cancelled') DEFAULT 'Pending',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `customer_name` varchar(100) DEFAULT NULL,
  `customer_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,14,1002,'2024-08-27 13:30:00','Dine-In',3,NULL,'Confirmed','2024-08-26 00:30:34',NULL,NULL),(2,14,1002,'2024-08-27 13:30:00','Dine-In',3,NULL,'Confirmed','2024-08-26 00:32:37',NULL,NULL),(3,15,1002,'2024-08-27 13:30:00','Dine-In',4,NULL,'Confirmed','2024-08-26 00:45:16',NULL,NULL),(4,3,1002,'2024-08-27 13:30:00','Dine-In',3,NULL,'Confirmed','2024-08-26 04:30:20',NULL,NULL),(5,3,1001,'2024-08-27 13:30:00','Dine-In',3,NULL,'Cancelled','2024-08-26 04:33:09',NULL,NULL),(6,3,1001,'2024-08-28 14:02:00','Dine-In',3,NULL,'Confirmed','2024-08-27 09:04:43',NULL,NULL),(7,11,1001,'2024-08-28 13:53:00','Dine-In',5,'Private Dining Rooms','Confirmed','2024-08-27 09:48:22',NULL,NULL),(8,1,1002,'2024-08-28 13:23:00','Dine-In',4,'Outdoor Seating','Confirmed','2024-08-27 12:23:18','Muthukuda Arachchilage Annette','annette.ma19@gmail.com'),(9,1,1001,'2024-08-28 14:30:00','Dine-In',2,'Outdoor Seating','Confirmed','2024-08-27 15:51:34','Muthukuda Arachchilage Annette','annette.ma19@gmail.com'),(10,1,1001,'2024-08-31 14:30:00','Dine-In',4,'Indoor Dining Area','Confirmed','2024-08-27 16:02:25','Muthukuda Arachchilage Annette','annette.ma19@gmail.com'),(11,3,1002,'2024-09-04 13:30:00','Dine-In',3,'Outdoor Seating','Confirmed','2024-09-03 03:36:51','Muthukuda Arachchilage Sihina','sihina@gmail.com'),(12,3,1002,'2024-09-04 13:30:00','Dine-In',3,'Outdoor Seating','Pending','2024-09-03 03:37:48','Muthukuda Arachchilage Sihina','sihina@gmail.com'),(13,3,1002,'2024-09-04 12:50:00','Dine-In',10,'Bar Area','Pending','2024-09-03 07:47:00','Muthukuda Arachchilage Sihina','sihina@gmail.com'),(14,3,1001,'2024-09-06 15:22:00','Dine-In',5,'Outdoor Seating','Pending','2024-09-05 03:22:18','Muthukuda Arachchilage Sihina','sihina@gmail.com'),(15,11,1001,'2024-09-07 14:00:00','Dine-In',3,'Private Dining Rooms','Confirmed','2024-09-05 04:34:36','Ann Cristina','ann@gmail.com'),(16,18,1001,'2024-09-06 14:00:00','Dine-In',3,'Indoor Dining Area','Confirmed','2024-09-05 09:35:56','Muthukuda Arachchilage Annette','annettecristina185@gmail.com'),(17,3,1001,'2024-09-07 13:20:00','Dine-In',3,'','Pending','2024-09-06 09:19:07','Muthukuda Arachchilage Sihina','sihina@gmail.com'),(18,11,1003,'2024-09-07 13:20:00','Dine-In',5,'','Pending','2024-09-06 09:24:00','Ann Cristina','ann@gmail.com'),(19,11,1003,'2024-09-08 12:00:00','Dine-In',16,'','Confirmed','2024-09-06 09:37:37','Ann Cristina','ann@gmail.com'),(20,11,1003,'2024-09-09 12:00:00','Dine-In',12,'','Pending','2024-09-06 10:17:47','Ann Cristina','ann@gmail.com'),(21,11,1003,'2024-09-09 12:00:00','Dine-In',12,'','Pending','2024-09-06 10:20:15','Ann Cristina','ann@gmail.com'),(22,11,1003,'2024-09-07 13:30:00','Dine-In',3,'','Pending','2024-09-06 10:26:17','Ann Cristina','ann@gmail.com'),(23,11,1003,'2024-09-08 13:00:00','Dine-In',7,'','Pending','2024-09-06 11:54:28','Ann Cristina','ann@gmail.com'),(24,3,1003,'2024-09-10 12:00:00','Dine-In',16,'','Cancelled','2024-09-10 11:17:45','Muthukuda Arachchilage Sihina','sihina@gmail.com');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `capacity` int NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurants`
--

LOCK TABLES `restaurants` WRITE;
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` VALUES (1001,'ABC Restaurant Colombo','Colombo',10),(1002,'ABC Restaurant Kandy','Kandy',10),(1003,'ABC Restaurant Negombo','Negombo',20),(1004,'ABC Restaurant Kurunegala','Kurunegala',50);
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `service_name` varchar(100) NOT NULL,
  `description` text,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (2001,'Dine-In Service','Full-service dining experience where guests can enjoy meals in the restaurant','dine in.jpg'),(2002,'Takeaway Service','Option for customers to order meals for pickup to enjoy at home or elsewhere','take away.jpg');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `position` varchar(50) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `restaurant_id` int DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_restaurant_staff` (`restaurant_id`),
  CONSTRAINT `fk_restaurant_staff` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Annette','12345','Restaurant Manager','2024-08-14 10:23:52',1001),(2,'Samadhi','12345','Assistant Manager','2024-08-14 11:13:08',1001),(3,'Kamal','12345','Head of Event Coordinator','2024-08-14 12:31:24',1003),(9,'Ravindu','12345','Accountant','2024-08-15 05:49:22',1002),(10,'Pramod','12345','Event Coordinator','2024-08-18 08:36:52',1002),(14,'Sujeewa','12345','Restaurant Manager','2024-09-06 14:58:30',1003);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-10 21:13:29
