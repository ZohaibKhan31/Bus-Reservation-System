-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: busdb
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `a_book`
--

DROP TABLE IF EXISTS `a_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `a_book` (
  `ID` bigint NOT NULL,
  `busId` bigint DEFAULT NULL,
  `busName` varchar(225) DEFAULT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `bookDate` date DEFAULT NULL,
  `emailId` varchar(225) DEFAULT NULL,
  `address` varchar(755) DEFAULT NULL,
  `noOfPerson` bigint DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `finalPrice` bigint DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_a_book` (`busId`),
  CONSTRAINT `FK_a_book` FOREIGN KEY (`busId`) REFERENCES `a_bus` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a_book`
--

LOCK TABLES `a_book` WRITE;
/*!40000 ALTER TABLE `a_book` DISABLE KEYS */;
INSERT INTO `a_book` VALUES (1,1,'ABC','Hariom','Mukati','9165415598','2019-06-21','Shubham@gmail.com','BDGIGIGD',1,0,1200,'root','root','2021-12-07 07:04:42','2019-06-20 13:02:53'),(2,2,'PQR','Hariom','Mukati','9165415598','2019-06-21','nyqaf@mailinator.net','BDGIGIGD',2,1300,2600,'root','root','2021-12-07 07:04:42','2019-06-20 13:05:16'),(3,1,'ABC','Hariom','Mukati','9165415598','2019-06-21','guviq@mailinator.net','BDGIGIGD',1,1200,1200,'root','root','2021-12-07 07:04:42','2019-06-20 13:07:37'),(4,2,'PQR','Hariom','Mukati','9165415598','2019-06-21','byti@mailinator.net','BDGIGIGD',1,1300,1300,'root','root','2021-12-07 07:04:42','2019-06-20 13:08:08'),(5,1,'ABC','bhupi','ram','8585858585','2021-05-26','bhupi@gmail.com','dsfs fsdf',1,1200,1200,'bhupi@gmail.com','bhupi@gmail.com','2021-12-07 07:04:42','2021-05-26 05:15:51'),(6,1,'ABC','raj','raj','8585858585','2021-05-26','Raj@gmail.com','fsg',1,1200,1200,'Raj@gmail.com','Raj@gmail.com','2021-12-07 07:04:42','2021-05-26 05:46:52');
/*!40000 ALTER TABLE `a_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `a_bus`
--

DROP TABLE IF EXISTS `a_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `a_bus` (
  `ID` bigint NOT NULL,
  `busNo` varchar(225) DEFAULT NULL,
  `busName` varchar(225) DEFAULT NULL,
  `fromCity` varchar(225) DEFAULT NULL,
  `toCity` varchar(225) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(775) DEFAULT NULL,
  `time` varchar(225) DEFAULT NULL,
  `travelDuraion` varchar(225) DEFAULT NULL,
  `ticketPrice` bigint DEFAULT NULL,
  `busStopName` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a_bus`
--

LOCK TABLES `a_bus` WRITE;
/*!40000 ALTER TABLE `a_bus` DISABLE KEYS */;
INSERT INTO `a_bus` VALUES (1,'5VJ52','ABC','Indore','Mumbai','2019-06-21','DG wr9f GIG isgifg','10:00 AM','60 Minute',1200,'Abc bus stop','Admin@gmail.com','Admin@gmail.com','2021-12-07 07:03:53','2019-06-20 04:39:27'),(2,'5VJ51','PQR','Indore','Dehli','2019-06-21','HOHO dvsoi srfo','11:00 AM','150 Minute',1300,'Pqr bus stop','Admin@gmail.com','Admin@gmail.com','2021-12-07 07:03:53','2019-06-20 04:41:42');
/*!40000 ALTER TABLE `a_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `a_user`
--

DROP TABLE IF EXISTS `a_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `a_user` (
  `ID` bigint NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `roleId` bigint DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a_user`
--

LOCK TABLES `a_user` WRITE;
/*!40000 ALTER TABLE `a_user` DISABLE KEYS */;
INSERT INTO `a_user` VALUES (1,'Admin','Admin','Admin@gmail.com','Admin@123',1,'root','root','2019-06-19 12:46:48','2019-06-19 12:46:51'),(2,'bhupi','ram','bhupi@gmail.com','Demo@123',2,'root','root','2021-05-26 05:15:36','2021-05-26 05:15:36'),(3,'Ram','asd','ram@gmail.com','Demo@123',2,'root','root','2021-05-26 05:19:07','2021-05-26 05:19:07'),(4,'Ramm','asddd','ramm@gmail.com','Demo@123',2,'root','root','2021-05-26 05:19:59','2021-05-26 05:20:16'),(5,'raj','raj','Raj@gmail.com','Demo@123',2,'root','root','2021-05-26 05:46:15','2021-05-26 05:46:15');
/*!40000 ALTER TABLE `a_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-08 17:32:38
