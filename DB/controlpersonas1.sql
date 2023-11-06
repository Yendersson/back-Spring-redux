-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: controlpersonas
-- ------------------------------------------------------
-- Server version	5.7.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit`
--

LOCK TABLES `audit` WRITE;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
INSERT INTO `audit` VALUES (1,'2023-11-06 16:22:52','UPDATE'),(2,'2023-11-06 16:26:45','UPDATE'),(3,'2023-11-06 17:12:43','CREATE'),(4,'2023-11-06 17:12:43','UPDATE'),(5,'2023-11-06 17:13:53','CREATE'),(6,'2023-11-06 17:13:53','DELETE');
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'http://localhost:3000/update/1','yendersoncolmenares67@gmail.com','Yendersons','Colmenares',NULL),(2,'https://reqres.in/img/faces/1-image.jpg','george.bluth@reqres.in','George','Bluth',NULL),(3,'https://reqres.in/img/faces/2-image.jpg','janet.weaver@reqres.in','Janet','Weaver',NULL),(4,'https://reqres.in/img/faces/3-image.jpg','emma.wong@reqres.in','Emma','Wong',NULL),(5,'https://reqres.in/img/faces/4-image.jpg','eve.holt@reqres.in','Eve','Holt',NULL),(6,'https://reqres.in/img/faces/5-image.jpg','charles.morris@reqres.in','Charles','Morris',NULL),(7,'https://reqres.in/img/faces/6-image.jpg','tracey.ramos@reqres.in','Tracey','Ramos',NULL),(10,'https://reqres.in/img/faces/8-image.jpg','iv@gmail.com','Ignacio','Vladimir',NULL),(11,'https://reqres.in/img/faces/7-image.jpg','michael.lawson@reqres.in','Michael','Lawson',NULL),(12,'https://reqres.in/img/faces/8-image.jpg','lindsay.ferguson@reqres.in','Lindsay','Ferguson',NULL),(13,'https://reqres.in/img/faces/9-image.jpg','tobias.funke@reqres.in','Tobias','Funke',NULL),(14,'https://reqres.in/img/faces/10-image.jpg','byron.fields@reqres.in','Byron','Fields',NULL),(15,'https://reqres.in/img/faces/11-image.jpg','george.edwards@reqres.in','George','Edwards',NULL),(16,'https://reqres.in/img/faces/12-image.jpg','rachel.howell@reqres.in','Rachel','Howell',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-06 17:41:28
