CREATE DATABASE  IF NOT EXISTS `mf0223_3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mf0223_3`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: mf0223_3
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Entrante'),(2,'Primero'),(3,'Segundo'),(4,'Postre'),(5,'Bebida'),(6,'Ensalada'),(7,'Vegetariana'),(8,'Vegana'),(9,'Fitness'),(10,'Dietetica'),(11,'Único'),(12,'Bocadillo');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias_has_platos`
--

DROP TABLE IF EXISTS `categorias_has_platos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias_has_platos` (
  `categorias_id` bigint NOT NULL,
  `platos_id` bigint NOT NULL,
  PRIMARY KEY (`categorias_id`,`platos_id`),
  KEY `fk_categorias_has_platos_platos1_idx` (`platos_id`),
  KEY `fk_categorias_has_platos_categorias1_idx` (`categorias_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias_has_platos`
--

LOCK TABLES `categorias_has_platos` WRITE;
/*!40000 ALTER TABLE `categorias_has_platos` DISABLE KEYS */;
INSERT INTO `categorias_has_platos` VALUES (2,1),(3,1),(7,1),(8,1),(10,1),(11,1),(4,2),(5,2),(2,3),(3,3),(11,3),(12,3);
/*!40000 ALTER TABLE `categorias_has_platos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `origenes`
--

DROP TABLE IF EXISTS `origenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `origenes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `origenes`
--

LOCK TABLES `origenes` WRITE;
/*!40000 ALTER TABLE `origenes` DISABLE KEYS */;
INSERT INTO `origenes` VALUES (1,'España'),(2,'Rusia'),(3,'Sudamérica'),(4,'Italia'),(5,'Francia'),(6,'Portugal'),(7,'Inglaterra'),(8,'Norteamérica'),(9,'África'),(10,'Japón'),(11,'Alemania'),(12,'China'),(13,'Australia'),(14,'Universal');
/*!40000 ALTER TABLE `origenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platos`
--

DROP TABLE IF EXISTS `platos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text NOT NULL,
  `ingredientes` text NOT NULL,
  `receta` text NOT NULL,
  `calorias` int NOT NULL,
  `url_imagen` text NOT NULL,
  `origenes_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_platos_origenes_idx` (`origenes_id`),
  CONSTRAINT `fk_platos_origenes` FOREIGN KEY (`origenes_id`) REFERENCES `origenes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platos`
--

LOCK TABLES `platos` WRITE;
/*!40000 ALTER TABLE `platos` DISABLE KEYS */;
INSERT INTO `platos` VALUES (1,'Alcachofas al vapor','Si te apetece disfrutar de este delicioso alimento estás de suerte, pues desde unCOMO te explicamos cómo preparar alcachofas al vapor en unos sencillos pasos. ¡Toma nota!','<ul><li>- 2 alcachofas medianas</li><li>- 1 cucharita de harina</li><li>- 2 cucharaditas de aceite de oliva</li><li>- 1 diente de ajo</li><li>- Sal</li><li>- 1 limón</li><li>- 1 vaso de agua</li><li>- Perejil</li></ul>','<p><strong>1 </strong>Disfrutar del intenso sabor de la alcachofa es muy fácil, ya que existen recetas de lo más sencillas para disfrutar de un plato de alcachofas. Ya las quieras comer en salsa o como acompañamiento de algún otro plato, hoy te explicamos cómo preparar alcachofas al vapor en 15 minutos.</p><p>¿Quieres aprender cómo hacer alcachofa al vapor con salsa de perejil? El primer paso consistirá en preparar esta <strong>fresca y deliciosa salsa</strong>. Para ello, tendrás que preparar bien todos los ingredientes que te hemos mencionado; en el caso del ajo, es importante que lo lamines previamente.</p><p><strong>2 </strong>Mezcla la harina, el perejil, una pizca de sal, medio vaso de agua, el ajo picado y una gotita de aceite en un recipiente que no sea demasiado grande. Remueve bien todos los productos juntos.</p><p>Ya tendrás tu salsa preparada, así que solo tendrás que volcarla en la vaporera e introducirla en el microondas. Pon el <strong>microondas a máxima potencia</strong> durante un minuto y medio.</p><p><strong>3 </strong>Mientras tu salsa se calienta y coge forma, tendrás que preparar las alcachofas. Empieza quitando las hojas externas de la verdura y, a continuación, corta el rabo y las puntas.</p><p>Desde unCOMO te aconsejamos que <strong>frotes un limón sobre las alcachofas</strong> para evitar que estas queden ennegrecidas y pierdan sabor, no obstante, es un paso totalmente opcional.</p><p>Ahora te las apañas solo.</p>',250,'https://t1.uc.ltmcdn.com/images/9/7/3/como_preparar_alcachofas_al_vapor_50379_600.jpg',14),(3,'Tortilla de patatas','En las crónicas de Indias se tiene documentado que en 1519 ya se conocía la tortilla de huevo tanto en Europa por los conquistadores españoles.','<ul><li>- 8 huevos camperos o 10 huevos normales.</li><li>- 1 kg de patatas gallegas</li><li>- Aceite de oliva virgen extra para la fritura de las patatas</li><li>- 1 cebolla grande (opcional)</li><li>- Sal (al gusto)</li></ul>','<p>1. Pelamos las patatas, las lavamos para quitar restos de suciedad y muy importante, las secamos.</p><p>2. Cortamos en láminas semifinas, a mí no me gusta que se deshagan sino que al freírlas se tuesten un poco. Las colocamos en un bol grande, donde luego vamos a mezclar con el huevo y añadimos sal al gusto. Removemos bien y reservamos.</p><p>3. Elegimos nuestra sartén más grande y antiadherente. La ponemos al fuego y añadimos un buen aceite de oliva virgen extra. No tengáis miedo en gastaros un poco de dinero en aceite, le va a dar ese punto de sabor que distingue vuestra tortilla de las demás, podéis emplear muchas variedades: arbequina, picual, cornicabra, hojiblanca, royal… el que más os guste, pero de calidad.</p><p>4. Introducimos las patatas cortadas y ya saladas y dejamos que se cocinen durante aproximadamente veinte minutos a fuego bajo. El tema del grosor de las patatas también va a gustos. Hay quien prefiere cortarlas a trozos muy pequeños, en láminas muy finas que casi se rompan al freír y o más bien grandes.</p><p>5. Mientras se están friendo las patatas, en el bol donde luego vamos a echar las patatas batimos los huevos, reservamos. Pelamos la cebolla y cortamos lo más fino posible.</p><p>6. En otra sartén calentamos aceite de oliva y añadimos los trozos de cebolla. Pochamos hasta que tenga un color dorado, que tenga un punto de caramelización pero sin llegar a quemarse. La cebolla se hará antes que las patatas, así que escurrimos y añadimos al bol con el huevo batido.</p>',280,'https://www.recetasderechupete.com/wp-content/uploads/2020/11/Tortilla-de-patatas-4-768x530.jpg',1);
/*!40000 ALTER TABLE `platos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mf0223_3'
--

--
-- Dumping routines for database 'mf0223_3'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-09 20:35:11
