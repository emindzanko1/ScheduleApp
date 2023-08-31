CREATE DATABASE  IF NOT EXISTS `freedb_RPR Projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `freedb_RPR Projekat`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPR Projekat
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `Schedule`
--

DROP TABLE IF EXISTS `Schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Schedule` (
  `Schedule_ID` int NOT NULL AUTO_INCREMENT,
  `User_ID` int DEFAULT NULL,
  `ScheduleName` varchar(100) NOT NULL,
  PRIMARY KEY (`Schedule_ID`),
  KEY `UserID_idx` (`User_ID`),
  CONSTRAINT `User_ID` FOREIGN KEY (`User_ID`) REFERENCES `User` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Schedule`
--

LOCK TABLES `Schedule` WRITE;
/*!40000 ALTER TABLE `Schedule` DISABLE KEYS */;
INSERT INTO `Schedule` VALUES (6,9,'eminov'),(7,10,'aminin'),(8,11,'elmin'),(9,10,'Aminin raspored'),(10,10,'Volim te'),(11,10,'Zeljeznicar'),(12,10,'amina'),(13,10,'mina'),(14,10,'mina'),(15,10,'amina'),(16,10,'amina'),(17,10,'Amina'),(18,10,'Moj raspored'),(19,10,'Amina'),(20,10,'Amina'),(21,10,'amina'),(22,10,'Aminino'),(23,10,''),(24,10,''),(25,10,'s'),(26,10,'s'),(27,10,'s'),(28,10,'amina'),(29,10,'asffag'),(30,10,'asffag'),(31,10,'amina'),(32,10,'an'),(33,10,'amina'),(34,10,'amina'),(35,10,'amina'),(36,10,'amina'),(37,10,'amina'),(38,10,'amina'),(39,10,'amina'),(40,10,'amina'),(41,22,'Emin'),(42,22,'amina'),(43,10,'s'),(44,10,'amina'),(45,10,'amina'),(46,10,'amina'),(47,10,'amina'),(48,10,'amina'),(49,10,'amina'),(50,10,'s'),(51,10,'amina'),(52,10,'amina'),(53,10,'amina'),(54,10,'amina'),(55,10,'amina'),(56,10,'amina'),(57,10,'amina'),(58,10,'amina'),(59,10,'amina'),(60,10,'Location'),(61,10,'amina'),(62,10,'amina'),(63,10,'Aminin raspored'),(64,10,'Amina raspored'),(65,10,'amina'),(66,10,'amina'),(67,10,'amina'),(68,10,'amina');
/*!40000 ALTER TABLE `Schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ScheduleItem`
--

DROP TABLE IF EXISTS `ScheduleItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ScheduleItem` (
  `Item_ID` int NOT NULL AUTO_INCREMENT,
  `Schedule_ID` int DEFAULT NULL,
  `DayOfWeek` varchar(15) DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
  `EventName` varchar(100) DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Item_ID`),
  KEY `ScheduleID_idx` (`Schedule_ID`),
  CONSTRAINT `Schedule_ID` FOREIGN KEY (`Schedule_ID`) REFERENCES `Schedule` (`Schedule_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ScheduleItem`
--

LOCK TABLES `ScheduleItem` WRITE;
/*!40000 ALTER TABLE `ScheduleItem` DISABLE KEYS */;
INSERT INTO `ScheduleItem` VALUES (1,6,'friday','00:00:09','00:00:10','proba','a2'),(2,37,'1','00:00:01','00:00:01','1','1'),(3,42,'amina','00:00:09','00:00:10','amina','amina'),(4,43,'','00:00:09','00:00:10','asfa','asfas'),(5,48,'monday','09:00:00','11:00:00','sgsdg','sgssg'),(6,49,'Monday','09:00:00','10:00:00','ss','ss'),(7,50,'Monday','09:00:00','10:00:00','Event','Location'),(8,51,'Monday','09:00:00','10:00:00','',''),(9,53,'Monday','09:00:00','10:00:00','Event','Location'),(10,54,'Monday','09:10:00','10:10:00','e','e'),(11,55,'Tuesday','09:00:00','10:10:00','Event','Location'),(12,55,'Monday','09:10:00','10:10:00','Novi event','Location'),(13,55,'Monday','09:50:00','10:30:00','Event','Loka'),(14,56,'Monday','09:10:00','10:00:00','aa','aa'),(15,58,'Friday','09:00:00','10:00:00','Event','Location'),(16,58,'Friday','09:30:00','10:00:00','Event','Location'),(17,58,'Monday','09:00:00','10:30:00','Event','Location'),(18,58,'Tuesday','09:00:00','10:00:00','Event','Location'),(19,58,'Wednesday','09:00:00','10:00:00','Location','Event'),(20,58,'Thursday','09:00:00','10:00:00','Location','Event'),(21,59,'Friday','09:00:00','10:00:00','Event','Location'),(22,59,'Friday','09:00:00','10:00:00','Proba','proba'),(23,61,'Friday','09:10:00','10:00:00','event','Location'),(24,62,'Friday','09:45:00','10:00:00','RPR usmeni','A1'),(25,62,'Friday','10:30:00','11:00:00','PROBAeminaaaaa','asgfa'),(26,63,'Monday','09:00:00','10:00:00','Event','Location'),(27,63,'Friday','10:30:00','11:45:00','Petak hehe','HEHE'),(28,63,'Friday','08:00:00','10:00:00','Sortiraj','agadsgdag'),(29,64,'Tuesday','09:30:00','15:00:00','Amina frizura','Frizerski salon Sabic'),(30,65,'Wednesday','09:00:00','13:00:00','Event','Location'),(31,68,'Friday','10:00:00','12:00:00','13:00','2121');
/*!40000 ALTER TABLE `ScheduleItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `User_ID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `HashedPassword` char(64) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) NOT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (9,'edz','em','emin','dzanko'),(10,'amina','amina','amina','but'),(11,'elma','elma','elma','elma'),(12,'hilmo','hilmo','hilmo','hilmo'),(13,'eeeee','eeeeee','eeee','eeee'),(14,'abutur1','abutur1','Amina','Buturovic'),(15,'122112','212e412','Emin','Dzanko'),(16,'eeeee','eeeeee','e','e'),(17,'eeeeee','eeeeee','eeee','eee'),(18,'asfas','fasfasfsa','e','e'),(21,'weafaga','dasgdasgdasg','ee','ee'),(22,'nime1','nime1','emin','dzdz'),(23,'amina1','asfs21ga','Amina','Bu'),(24,'edzanko11','54321','Emin','Dzanko'),(25,'edzanko11','54321','Emin','Dzanko'),(26,'edzanko11','54321','Emin','Dzanko'),(27,'edzanko11','54321','Emin','Dzanko');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-31 12:15:56
