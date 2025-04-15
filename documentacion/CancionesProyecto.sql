-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (x86_64)
--
-- Host: localhost    Database: musica_proyecto
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `canciones`
--

DROP TABLE IF EXISTS `canciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canciones` (
  `id_cancion` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `artista` varchar(255) NOT NULL,
  `album` varchar(255) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `anio_lanzamiento` int DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id_cancion`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `canciones_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canciones`
--

LOCK TABLES `canciones` WRITE;
/*!40000 ALTER TABLE `canciones` DISABLE KEYS */;
INSERT INTO `canciones` VALUES (1,'Bohemian Rhapsody','Queen','A Night at the Opera','Rock',1975,1),(2,'Imagine','John Lennon','Imagine','Rock',1971,2),(3,'Smells Like Teen Spirit','Nirvana','Nevermind','Grunge',1991,3),(4,'Shape of You','Ed Sheeran','Divide','Pop',2017,3),(5,'Billie Jean','Michael Jackson','Thriller','Pop',1983,2),(15,'Juan','Pepe','Papaya','Rock',1994,3),(16,'Vai','Alfa','Non so chi ha creato il mondo','',2024,2),(24,'Paralyzed','NF','Mansion','Hip Hop',2015,26),(25,'Not Afraid','Eminem','Recovery','Hip Hop',2010,28);
/*!40000 ALTER TABLE `canciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `correo` varchar(150) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Juan','Pérez','juan.perez@correo.com','clave1234'),(2,'María','López','maria.lopez@correo.com','clave2345'),(3,'Carlos','Gómez','carlos.gomez@ecorreo.com','clave3456'),(4,'Ana','Ramírez','ana.ramirez@correo.com','clave4567'),(10,'Luis','Torres','\'luis.torres@correo.com','clave5678'),(17,'Carlos','Rojas','caro@correo.com','caro12345'),(18,'Samuel','Hoyos','saho@correo.com','saho1234'),(19,'Paco','Duarte','padu@correo.com','padu1234'),(24,'Melissa','Arias','meari@correo.com','meari1234'),(25,'Angela','Rojas','anro@correo.com','anro1234'),(26,'Camilo','Torres','coto@correo.com','coto1234'),(27,'Diego','Nuñez','dienu@correo.com','dienu1234'),(28,'Laura','Perez','laupe@correo.com','laupe1234'),(29,'Pepe','Torres','peto@correo.com','peto1234'),(30,'Laura','Rojas','lauro@correo.com','lauro1234'),(31,'Laura','Nuñez','launu@correo.com','launu1234'),(32,'Pepe','Arias','peari@correo.com','peari1234'),(33,'Camila','Perez','cape@correo.com','cape1234'),(34,'Juan','Torres','jtorres@correo.com','jtorres1234'),(35,'Paquita','la del barrio','paqui@correo.com','paqui1234'),(36,'Juanita','Torres','juanito@correo.com','juanito1234'),(37,'Paco','Rojas','paro@correo.com','paro1234'),(38,'Claudia','Perez','claupe@correo.com','claupe1234'),(39,'María','Rojas','maro@correo.com','maro1234');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-15 20:32:23
