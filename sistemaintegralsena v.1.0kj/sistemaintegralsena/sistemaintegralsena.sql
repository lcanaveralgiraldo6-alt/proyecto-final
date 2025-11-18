CREATE DATABASE  IF NOT EXISTS `sistemaintegralsena` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `sistemaintegralsena`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sistemaintegralsena
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `aprendiz`
--

DROP TABLE IF EXISTS `aprendiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aprendiz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `documento` varchar(50) NOT NULL,
  `ficha_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `documento` (`documento`),
  KEY `fk_aprendiz_ficha` (`ficha_id`),
  CONSTRAINT `fk_aprendiz_ficha` FOREIGN KEY (`ficha_id`) REFERENCES `ficha` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aprendiz`
--

LOCK TABLES `aprendiz` WRITE;
/*!40000 ALTER TABLE `aprendiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `aprendiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atencion_individual`
--

DROP TABLE IF EXISTS `atencion_individual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atencion_individual` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `observacion` text DEFAULT NULL,
  `plan_accion` text DEFAULT NULL,
  `aprendiz_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_atencion_aprendiz` (`aprendiz_id`),
  KEY `fk_atencion_usuario` (`usuario_id`),
  CONSTRAINT `fk_atencion_aprendiz` FOREIGN KEY (`aprendiz_id`) REFERENCES `aprendiz` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_atencion_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atencion_individual`
--

LOCK TABLES `atencion_individual` WRITE;
/*!40000 ALTER TABLE `atencion_individual` DISABLE KEYS */;
/*!40000 ALTER TABLE `atencion_individual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comite`
--

DROP TABLE IF EXISTS `comite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comite`
--

LOCK TABLES `comite` WRITE;
/*!40000 ALTER TABLE `comite` DISABLE KEYS */;
/*!40000 ALTER TABLE `comite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comite_aprendiz`
--

DROP TABLE IF EXISTS `comite_aprendiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comite_aprendiz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comite_id` bigint(20) NOT NULL,
  `aprendiz_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comite_id` (`comite_id`,`aprendiz_id`),
  KEY `fk_comiteap_aprendiz` (`aprendiz_id`),
  CONSTRAINT `fk_comiteap_aprendiz` FOREIGN KEY (`aprendiz_id`) REFERENCES `aprendiz` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comiteap_comite` FOREIGN KEY (`comite_id`) REFERENCES `comite` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comite_aprendiz`
--

LOCK TABLES `comite_aprendiz` WRITE;
/*!40000 ALTER TABLE `comite_aprendiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `comite_aprendiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ficha`
--

DROP TABLE IF EXISTS `ficha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ficha` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) NOT NULL,
  `programa` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ficha`
--

LOCK TABLES `ficha` WRITE;
/*!40000 ALTER TABLE `ficha` DISABLE KEYS */;
/*!40000 ALTER TABLE `ficha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMIN'),(2,'PSICOLOGA'),(3,'T_SOCIAL');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taller`
--

DROP TABLE IF EXISTS `taller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taller` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `objetivo` text NOT NULL,
  `fecha` date NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_taller_usuario` (`usuario_id`),
  CONSTRAINT `fk_taller_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taller`
--

LOCK TABLES `taller` WRITE;
/*!40000 ALTER TABLE `taller` DISABLE KEYS */;
/*!40000 ALTER TABLE `taller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taller_aprendiz`
--

DROP TABLE IF EXISTS `taller_aprendiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taller_aprendiz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taller_id` bigint(20) NOT NULL,
  `aprendiz_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `taller_id` (`taller_id`,`aprendiz_id`),
  KEY `fk_tallerap_aprendiz` (`aprendiz_id`),
  CONSTRAINT `fk_tallerap_aprendiz` FOREIGN KEY (`aprendiz_id`) REFERENCES `aprendiz` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_tallerap_taller` FOREIGN KEY (`taller_id`) REFERENCES `taller` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taller_aprendiz`
--

LOCK TABLES `taller_aprendiz` WRITE;
/*!40000 ALTER TABLE `taller_aprendiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `taller_aprendiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1,
  `rol` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Administrador Principal','admin1@sena.edu.co','$2a$10$lI8IqFHu.9Yz9sOrCEkD2.ywtEQnDNB.K1I1PbDpRIlIPIADLhGmW',1,'ADMIN'),(2,'Administrador Secundario','admin2@sena.edu.co','$2a$10$6leFXt06EUt3vDxzX75qTuJVMXRz2wYnFlcTwke9Y694INszVj9S6',1,'ADMIN'),(3,'Nala Giraldo','nala@asd.com','$2a$10$tUmY.FZPkoRS/eInAujTCOZSWIQ./4j.BV7e.xU1iqyG/fAjVueYe',1,'PSICOLOGA'),(4,'asd','asdfg@asd.com','$2a$10$rwZDgJQhuoCMp0ZeWL/mquw83FetVDK04CwYU6lrvc6.oD5yK6IWO',1,'T_SOCIAL');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `rol_id` (`rol_id`),
  CONSTRAINT `usuarios_roles_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `usuarios_roles_ibfk_2` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_roles`
--

LOCK TABLES `usuarios_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_roles` DISABLE KEYS */;
INSERT INTO `usuarios_roles` VALUES (1,1),(2,1),(3,2),(4,3);
/*!40000 ALTER TABLE `usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-17 18:43:50
