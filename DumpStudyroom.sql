-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: studyroom
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `assigned_student`
--

DROP TABLE IF EXISTS `assigned_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assigned_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `coursecode` varchar(45) NOT NULL,
  `course` varchar(45) NOT NULL,
  `teacher_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assigned_student`
--

LOCK TABLES `assigned_student` WRITE;
/*!40000 ALTER TABLE `assigned_student` DISABLE KEYS */;
INSERT INTO `assigned_student` VALUES (35,'brdee123','MATH01','Math 1','gukar123'),(36,'brdee123','ENG01','English 1','gukar123'),(37,'edyge123','MATH01','Math 1','gukar123'),(38,'edyge123','SWE01','Swedish 1','jokar123'),(52,'edyge123','ENG01','English 1','gukar123');
/*!40000 ALTER TABLE `assigned_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_code_id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `teacher_id` varchar(45) NOT NULL,
  `start_date` varchar(45) NOT NULL,
  `end_date` varchar(45) NOT NULL,
  `pace` varchar(45) NOT NULL,
  `points` varchar(45) NOT NULL,
  `exercise1` varchar(45) DEFAULT NULL,
  `exercise2` varchar(45) DEFAULT NULL,
  `exercise3` varchar(45) DEFAULT NULL,
  `examination` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`course_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('ENG01','English 1','gukar123','2022-10-10','2022-12-28','50%','100','','','',''),('MATH01','Math 1','gukar123','2022-10-10','2022-12-28','50%','100','1+1\r\n2+2\r\n3+3\r\n4+4\r\n5+5','1+1','1+1','1+1'),('SWE01','Swedish 1','jokar123','2022-10-10','2022-11-15','100%','200',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_contents`
--

DROP TABLE IF EXISTS `course_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_contents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coursecode` varchar(45) NOT NULL,
  `course` varchar(45) NOT NULL,
  `student_username` varchar(45) DEFAULT NULL,
  `teacher_username` varchar(45) DEFAULT NULL,
  `student_answer1` varchar(45) DEFAULT NULL,
  `student_answer2` varchar(45) DEFAULT NULL,
  `student_answer3` varchar(45) DEFAULT NULL,
  `new_student_answer` varchar(45) DEFAULT NULL,
  `teacher_reply1` varchar(45) DEFAULT NULL,
  `teacher_reply2` varchar(45) DEFAULT NULL,
  `teacher_reply3` varchar(45) DEFAULT NULL,
  `new_teacher_reply` varchar(45) DEFAULT NULL,
  `examination_student_answer` varchar(45) DEFAULT NULL,
  `examination_teacher_reply` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_contents`
--

LOCK TABLES `course_contents` WRITE;
/*!40000 ALTER TABLE `course_contents` DISABLE KEYS */;
INSERT INTO `course_contents` VALUES (6,'MATH01','Math 1','brdee123','gukar123','11','12123','123123',NULL,NULL,NULL,NULL,NULL,'123123',NULL),(7,'ENG01','English 1','brdee123','gukar123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'MATH01','Math 1','edyge123','gukar123','123213123','','','fasle',NULL,NULL,NULL,NULL,'',NULL),(9,'SWE01','Swedish 1','edyge123','jokar123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'ENG01','English 1','edyge123','gukar123',NULL,NULL,NULL,'false',NULL,NULL,NULL,'false',NULL,NULL),(13,'ENG01','English 1','gukar123','gukar123',NULL,NULL,NULL,'false',NULL,NULL,NULL,'false',NULL,NULL);
/*!40000 ALTER TABLE `course_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `idmessage` int NOT NULL AUTO_INCREMENT,
  `creator_id` varchar(45) NOT NULL,
  `create_date` datetime NOT NULL,
  `message` text NOT NULL,
  `recipient_id` varchar(45) NOT NULL,
  `is_read` varchar(45) NOT NULL,
  PRIMARY KEY (`idmessage`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (36,'gukar123','2022-08-11 16:32:38','Hey your behind on Your math do you need any help?','brdee123','true'),(37,'brdee123','2022-08-11 16:49:18','No im fine i\'ll soon turn it in. thank you.','gukar123','true'),(38,'brdee123','2022-08-11 17:08:37','Bye','gukar123','true'),(39,'gukar123','2022-08-11 18:19:25','cya','brdee123','true'),(40,'brdee123','2022-08-11 18:29:52','byesss','gukar123','true'),(41,'gukar123','2022-08-11 18:30:19','','brdee123','true'),(42,'gukar123','2022-08-11 18:30:26','123123','brdee123','true'),(43,'gukar123','2022-08-11 18:39:41','123123123123','brdee123','true'),(44,'brdee123','2022-08-11 18:41:04','123123','gukar123','true');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `personal_id` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` varchar(45) NOT NULL,
  PRIMARY KEY (`personal_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('000000-0000','-','-','admin','123','admin'),('1964-10-10','Jonas','Karlsson','jokar123','123','teacher'),('1987-04-26','Gunnar','Karlsson','gukar123','123','teacher'),('1991-01-01','Edvin','Ygeson','edyge123','123','student'),('1995-10-6','Bran','Deep','brdee123','123','student');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-12 13:14:45
