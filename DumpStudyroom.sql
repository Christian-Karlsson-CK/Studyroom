CREATE DATABASE  IF NOT EXISTS `studyroom` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `studyroom`;
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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assigned_student`
--

LOCK TABLES `assigned_student` WRITE;
/*!40000 ALTER TABLE `assigned_student` DISABLE KEYS */;
INSERT INTO `assigned_student` VALUES (55,'amasp123','Jav01','Java 1','gukar123'),(56,'jejas123','Jav01','Java 1','gukar123'),(57,'jejas123','MATH01','Math 1','ersve123'),(58,'klfre123','MATH01','Math 1','ersve123'),(59,'amasp123','MATH01','Math 1','ersve123'),(60,'klfre123','Swe01','Swedish 1','gukar123');
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
  `exercise1` varchar(500) DEFAULT NULL,
  `exercise2` varchar(500) DEFAULT NULL,
  `exercise3` varchar(500) DEFAULT NULL,
  `examination` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`course_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('Jav01','Java 1','gukar123','2022-08-10','2022-12-28','50%','200','Can you give 3 primitive java data types?','What is inheritance?','What is polymorphism?','What is recursion?'),('MATH01','Math 1','ersve123','2022-08-10','2022-12-28','100%','400','Vad blir talet 1+1=?','Vad blir talet 22-23=?','Vad blir talet 10*10=?','Vad blir 2*(2+2)=?'),('Swe01','Swedish 1','gukar123','2022-08-10','2022-12-28','50%','200','Ange 3 substantiv.','Vad betyder adjektiv?','Ge exempel på pronomen.','Vad betyder adverb?');
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
  `student_answer1` varchar(500) DEFAULT NULL,
  `student_answer2` varchar(500) DEFAULT NULL,
  `student_answer3` varchar(500) DEFAULT NULL,
  `new_student_answer` varchar(45) DEFAULT NULL,
  `teacher_reply1` varchar(500) DEFAULT NULL,
  `teacher_reply2` varchar(500) DEFAULT NULL,
  `teacher_reply3` varchar(500) DEFAULT NULL,
  `new_teacher_reply` varchar(45) DEFAULT NULL,
  `examination_student_answer` varchar(500) DEFAULT NULL,
  `examination_teacher_reply` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_contents`
--

LOCK TABLES `course_contents` WRITE;
/*!40000 ALTER TABLE `course_contents` DISABLE KEYS */;
INSERT INTO `course_contents` VALUES (14,'Jav01','Java 1','amasp123','gukar123','int, boolean and char.','A class can inherit attributes and methods from another class.\r\nAn subclass can use the extends keyword to inherit from superclass.','Polymorphism means \"many forms\". It can be an inherited method that does different things depending on what class  gets its method called.','false','Correct. Well done!','Excellent!','Nice answer!','true','Its when a function calls itself to solve complicated problems.','Correct! Congratulations you finished the course.'),(15,'Jav01','Java 1','jejas123','gukar123','1. int\r\n2. boolean\r\n3. String','','','true',NULL,NULL,NULL,'false','',NULL),(16,'MATH01','Math 1','jejas123','ersve123','2','-1','100','true',NULL,NULL,NULL,'false','8',NULL),(17,'MATH01','Math 1','klfre123','ersve123','2','-1','','false','Correct!','Correct!','','true','',''),(18,'MATH01','Math 1','amasp123','ersve123','3','23','1000','false','Incorrect! please study more addition!','Incorrect! Please study more subtraction!','Answer is a little too big number, please study more multiplication.','true','',''),(19,'Swe01','Swedish 1','klfre123','gukar123','banan, bil, gran.','','','true',NULL,NULL,NULL,'false','',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (56,'jejas123','2022-08-18 22:34:36','Hey I need help with what is the difference between inheritance and polymorphism?','gukar123','true'),(57,'jejas123','2022-08-18 22:35:51','Hey can you please check on my math answers i finished all and the exam.','ersve123','false'),(58,'klfre123','2022-08-18 22:46:08','hej kan du förklara vad adjektiv betyder?','gukar123','false'),(59,'gukar123','2022-08-18 22:54:19','Inheritance is when a class inherit from another class with keyword extends.\r\nPolymorphism can be when different classes inherit from same superclass but a inherited method will execute differently depending on from which class the method get called.','jejas123','false'),(60,'gukar123','2022-08-18 22:54:37','Hope that helps!','jejas123','false'),(61,'ersve123','2022-08-18 22:58:47','Hey I see you have a hard time in math, do you need easier study resources?','amasp123','false');
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
INSERT INTO `user` VALUES ('------','-------','------','admin','admin','admin'),('641001-1234','Erik','Svensson','ersve123','123','teacher'),('871012-1234','Gunnar','Karlsson','gukar123','123','teacher'),('890123-1234','Jens','Jason','jejas123','123','student'),('911101-1234','Klas','Fredrikson','klfre123','123','student'),('930527-1234','Amanda','Asp','amasp123','123','student');
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

-- Dump completed on 2022-08-18 23:12:13
