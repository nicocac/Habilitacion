CREATE DATABASE  IF NOT EXISTS `gestar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gestar`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gestar
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acopio`
--

DROP TABLE IF EXISTS `acopio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acopio` (
  `acopio_id` int(11) NOT NULL AUTO_INCREMENT,
  `acopio_fecha_alta` date DEFAULT NULL,
  `acopio_fecha_baja` date DEFAULT NULL,
  `acopio_fecha_ult_mod` date DEFAULT NULL,
  `acopio_usuario_alta` varchar(255) DEFAULT NULL,
  `acopio_usuario_baja` varchar(255) DEFAULT NULL,
  `acopio_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `acopio_cantidad_grano` int(11) DEFAULT NULL,
  `tipo_acopio_id` int(11) DEFAULT NULL,
  `tipo_estado_grano_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`acopio_id`),
  KEY `FK_bqt3lwbihqtx5axafb89iq1o7` (`tipo_acopio_id`),
  KEY `FK_au6hfju5orto6ylyljqjpo270` (`tipo_estado_grano_id`),
  CONSTRAINT `FK_au6hfju5orto6ylyljqjpo270` FOREIGN KEY (`tipo_estado_grano_id`) REFERENCES `tipo_estado_grano` (`te_grano_id`),
  CONSTRAINT `FK_bqt3lwbihqtx5axafb89iq1o7` FOREIGN KEY (`tipo_acopio_id`) REFERENCES `tipo_acopio` (`tipo_acopio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acopio`
--

LOCK TABLES `acopio` WRITE;
/*!40000 ALTER TABLE `acopio` DISABLE KEYS */;
/*!40000 ALTER TABLE `acopio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campania`
--

DROP TABLE IF EXISTS `campania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campania` (
  `cna_id` int(11) NOT NULL AUTO_INCREMENT,
  `cna_denominacion` varchar(255) DEFAULT NULL,
  `cna_fecha_alta` date DEFAULT NULL,
  `cna_fecha_baja` date DEFAULT NULL,
  `cna_fecha_fin_estimada` date DEFAULT NULL,
  `cna_fecha_fin_real` date DEFAULT NULL,
  `cna_fecha_inicio` date DEFAULT NULL,
  `cna_fecha_ult_mod` date DEFAULT NULL,
  `cna_usuario_alta` varchar(255) DEFAULT NULL,
  `cna_usuario_baja` varchar(255) DEFAULT NULL,
  `cna_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cna_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campania`
--

LOCK TABLES `campania` WRITE;
/*!40000 ALTER TABLE `campania` DISABLE KEYS */;
/*!40000 ALTER TABLE `campania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campo`
--

DROP TABLE IF EXISTS `campo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campo` (
  `cpo_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpo_cant_metros` int(11) DEFAULT NULL,
  `cpo_coordenadas` varchar(255) DEFAULT NULL,
  `cpo_fecha_alta` date DEFAULT NULL,
  `cpo_fecha_baja` date DEFAULT NULL,
  `cpo_fecha_ult_mod` date DEFAULT NULL,
  `cpo_nombre` varchar(255) DEFAULT NULL,
  `cpo_observaciones` varchar(255) DEFAULT NULL,
  `cpo_propietario` varchar(255) DEFAULT NULL,
  `cpo_ubicacion` varchar(255) DEFAULT NULL,
  `cpo_usuario_alta` varchar(255) DEFAULT NULL,
  `cpo_usuario_baja` varchar(255) DEFAULT NULL,
  `cpo_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cpo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campo`
--

LOCK TABLES `campo` WRITE;
/*!40000 ALTER TABLE `campo` DISABLE KEYS */;
/*!40000 ALTER TABLE `campo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_apellido` varchar(255) DEFAULT NULL,
  `cliente_cuit_cuil` varchar(255) DEFAULT NULL,
  `cliente_descripcion` varchar(255) DEFAULT NULL,
  `cliente_fecha_alta` date DEFAULT NULL,
  `cliente_fecha_baja` date DEFAULT NULL,
  `cliente_fecha_ult_mod` date DEFAULT NULL,
  `cliente_nombre` varchar(255) DEFAULT NULL,
  `cliente_usuario_alta` varchar(255) DEFAULT NULL,
  `cliente_usuario_baja` varchar(255) DEFAULT NULL,
  `cliente_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Fernández	','20-35789654-1',NULL,'2016-10-27',NULL,NULL,'Emilia','admin',NULL,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `cpa_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpa_cantidad_items` int(11) DEFAULT NULL,
  `cpa_fecha_alta` date DEFAULT NULL,
  `cpa_fecha_baja` date DEFAULT NULL,
  `cpa_fecha_compra` date DEFAULT NULL,
  `cpa_fecha_ult_mod` date DEFAULT NULL,
  `cpa_monto_total` decimal(19,2) DEFAULT NULL,
  `cpa_usuario_alta` varchar(255) DEFAULT NULL,
  `cpa_usuario_baja` varchar(255) DEFAULT NULL,
  `cpa_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cpa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compra`
--

DROP TABLE IF EXISTS `detalle_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_compra` (
  `cpd_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpd_cantidad` decimal(19,2) DEFAULT NULL,
  `cpdCpaId` int(11) DEFAULT NULL,
  `cpd_fecha_alta` date DEFAULT NULL,
  `cpd_fecha_baja` date DEFAULT NULL,
  `cpd_fecha_ult_mod` date DEFAULT NULL,
  `cpdInsId` int(11) DEFAULT NULL,
  `cpd_observaciones` varchar(255) DEFAULT NULL,
  `cpd_precio` decimal(19,2) DEFAULT NULL,
  `cpd_usuario_alta` varchar(255) DEFAULT NULL,
  `cpd_usuario_baja` varchar(255) DEFAULT NULL,
  `cpd_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `idx` int(11) DEFAULT NULL,
  `cpd_cpa_id` int(11) DEFAULT NULL,
  `cpd_ins_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpd_id`),
  KEY `FK_kewmcb3div2gk8ckuwsc28q0e` (`cpd_cpa_id`),
  KEY `FK_8t6x8n43twrrr9r5ovnxn3h3v` (`cpd_ins_id`),
  CONSTRAINT `FK_8t6x8n43twrrr9r5ovnxn3h3v` FOREIGN KEY (`cpd_ins_id`) REFERENCES `insumo` (`ins_id`),
  CONSTRAINT `FK_kewmcb3div2gk8ckuwsc28q0e` FOREIGN KEY (`cpd_cpa_id`) REFERENCES `compra` (`cpa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compra`
--

LOCK TABLES `detalle_compra` WRITE;
/*!40000 ALTER TABLE `detalle_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_egreso_acopio`
--

DROP TABLE IF EXISTS `detalle_egreso_acopio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_egreso_acopio` (
  `detalle_egreso_id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle_egreso_cantidad` bigint(20) DEFAULT NULL,
  `detalle_egreso_fecha_alta` date DEFAULT NULL,
  `detalle_egreso_fecha_baja` date DEFAULT NULL,
  `detalle_egreso_fecha_ult_mod` date DEFAULT NULL,
  `detalle_egreso_observaciones` varchar(255) DEFAULT NULL,
  `detalle_egreso_precio` double DEFAULT NULL,
  `detalle_egreso_usuario_alta` varchar(255) DEFAULT NULL,
  `detalle_egreso_usuario_baja` varchar(255) DEFAULT NULL,
  `detalle_egreso_usuario_utl_mod` varchar(255) DEFAULT NULL,
  `egreso_acopio_id` int(11) DEFAULT NULL,
  `tipo_acopio_id` int(11) DEFAULT NULL,
  `tipo_grano_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`detalle_egreso_id`),
  KEY `FK_imf759qp6ru4nkwcklmyv4cys` (`egreso_acopio_id`),
  KEY `FK_r7onpss2jlw0d1c48xtshhwbq` (`tipo_acopio_id`),
  KEY `FK_6lyvussxcxk6uuyr7hxkul6sn` (`tipo_grano_id`),
  CONSTRAINT `FK_6lyvussxcxk6uuyr7hxkul6sn` FOREIGN KEY (`tipo_grano_id`) REFERENCES `tipo_grano` (`tgr_id`),
  CONSTRAINT `FK_imf759qp6ru4nkwcklmyv4cys` FOREIGN KEY (`egreso_acopio_id`) REFERENCES `egreso_acopio` (`egreso_id`),
  CONSTRAINT `FK_r7onpss2jlw0d1c48xtshhwbq` FOREIGN KEY (`tipo_acopio_id`) REFERENCES `tipo_acopio` (`tipo_acopio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_egreso_acopio`
--

LOCK TABLES `detalle_egreso_acopio` WRITE;
/*!40000 ALTER TABLE `detalle_egreso_acopio` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_egreso_acopio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_ingreso`
--

DROP TABLE IF EXISTS `detalle_ingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_ingreso` (
  `detalle_ingreso_id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle_ingreso_cantidad` bigint(20) DEFAULT NULL,
  `detalle_ingreso_fecha_alta` date DEFAULT NULL,
  `detalle_ingreso_fecha_baja` date DEFAULT NULL,
  `detalle_ingreso_fecha_ult_mod` date DEFAULT NULL,
  `detalle_ingreso_observaciones` varchar(255) DEFAULT NULL,
  `detalle_ingreso_precio` double DEFAULT NULL,
  `detalle_ingreso_usuario_alta` varchar(255) DEFAULT NULL,
  `detalle_ingreso_usuario_baja` varchar(255) DEFAULT NULL,
  `detalle_ingreso_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `ingreso_insumo_id` int(11) DEFAULT NULL,
  `insumo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`detalle_ingreso_id`),
  KEY `FK_fsd7u1domrpdvxhibjgcwp0qk` (`ingreso_insumo_id`),
  KEY `FK_t981dp53p6cjiecln2dr4r17x` (`insumo_id`),
  CONSTRAINT `FK_fsd7u1domrpdvxhibjgcwp0qk` FOREIGN KEY (`ingreso_insumo_id`) REFERENCES `ingreso_insumo` (`ingreso_id`),
  CONSTRAINT `FK_t981dp53p6cjiecln2dr4r17x` FOREIGN KEY (`insumo_id`) REFERENCES `insumo` (`ins_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_ingreso`
--

LOCK TABLES `detalle_ingreso` WRITE;
/*!40000 ALTER TABLE `detalle_ingreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_ingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_laboreo`
--

DROP TABLE IF EXISTS `detalle_laboreo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_laboreo` (
  `dbo_id` int(11) NOT NULL AUTO_INCREMENT,
  `dbo_cantidad` int(11) DEFAULT NULL,
  `dbo_fecha_alta` date DEFAULT NULL,
  `dbo_fecha_baja` date DEFAULT NULL,
  `dbo_fecha_ult_mod` date DEFAULT NULL,
  `dboInsId` int(11) DEFAULT NULL,
  `dboLboId` int(11) DEFAULT NULL,
  `dboMaqId` int(11) DEFAULT NULL,
  `dbo_observaciones` varchar(255) DEFAULT NULL,
  `dbo_usuario_alta` varchar(255) DEFAULT NULL,
  `dbo_usuario_baja` varchar(255) DEFAULT NULL,
  `dbo_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `dbo_ins_id` int(11) NOT NULL,
  `dbo_lbo_id` int(11) NOT NULL,
  `dbo_maq_id` int(11) DEFAULT NULL,
  `dbo_tipo_grano_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbo_id`),
  KEY `FK_1blvb77pbvj6iia1y9a32oh4f` (`dbo_ins_id`),
  KEY `FK_a89apli3pyqqutt6olqn666fv` (`dbo_lbo_id`),
  KEY `FK_a51u8d4a2gwsth8yqdi8k2de` (`dbo_maq_id`),
  KEY `FK_ljfat7ubk6hrqgub5frmom24e` (`dbo_tipo_grano_id`),
  CONSTRAINT `FK_1blvb77pbvj6iia1y9a32oh4f` FOREIGN KEY (`dbo_ins_id`) REFERENCES `insumo` (`ins_id`),
  CONSTRAINT `FK_a51u8d4a2gwsth8yqdi8k2de` FOREIGN KEY (`dbo_maq_id`) REFERENCES `maquinaria` (`maq_id`),
  CONSTRAINT `FK_a89apli3pyqqutt6olqn666fv` FOREIGN KEY (`dbo_lbo_id`) REFERENCES `laboreo` (`lbo_id`),
  CONSTRAINT `FK_ljfat7ubk6hrqgub5frmom24e` FOREIGN KEY (`dbo_tipo_grano_id`) REFERENCES `tipo_grano` (`tgr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_laboreo`
--

LOCK TABLES `detalle_laboreo` WRITE;
/*!40000 ALTER TABLE `detalle_laboreo` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_laboreo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_solicitud_insumo`
--

DROP TABLE IF EXISTS `detalle_solicitud_insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_solicitud_insumo` (
  `dsi_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpdCpaId` int(11) DEFAULT NULL,
  `cpdInsId` int(11) DEFAULT NULL,
  `dsi_cantidad` decimal(19,2) DEFAULT NULL,
  `dsi_fecha_alta` date DEFAULT NULL,
  `dsi_fecha_baja` date DEFAULT NULL,
  `dsi_fecha_ult_mod` date DEFAULT NULL,
  `dsi_observaciones` varchar(255) DEFAULT NULL,
  `dsi_precio` decimal(19,2) DEFAULT NULL,
  `dsi_usuario_alta` varchar(255) DEFAULT NULL,
  `dsi_usuario_baja` varchar(255) DEFAULT NULL,
  `dsi_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `insumo_id` int(11) DEFAULT NULL,
  `solicitud_insumo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dsi_id`),
  KEY `FK_hl6mybrvui331rceet25c1ey0` (`insumo_id`),
  KEY `FK_hn7rrmfkxaffyg5242kqaqraw` (`solicitud_insumo_id`),
  CONSTRAINT `FK_hl6mybrvui331rceet25c1ey0` FOREIGN KEY (`insumo_id`) REFERENCES `insumo` (`ins_id`),
  CONSTRAINT `FK_hn7rrmfkxaffyg5242kqaqraw` FOREIGN KEY (`solicitud_insumo_id`) REFERENCES `solicitud_insumo` (`si_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_solicitud_insumo`
--

LOCK TABLES `detalle_solicitud_insumo` WRITE;
/*!40000 ALTER TABLE `detalle_solicitud_insumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_solicitud_insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso_acopio`
--

DROP TABLE IF EXISTS `egreso_acopio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso_acopio` (
  `egreso_id` int(11) NOT NULL AUTO_INCREMENT,
  `egreso_cantidad_total` int(11) DEFAULT NULL,
  `egreso_fecha` date DEFAULT NULL,
  `egreso_fecha_alta` date DEFAULT NULL,
  `egreso_fecha_baja` date DEFAULT NULL,
  `egreso_fecha_ult_mod` date DEFAULT NULL,
  `egreso_usuario_alta` varchar(255) DEFAULT NULL,
  `egreso_usuario_baja` varchar(255) DEFAULT NULL,
  `egreso_usuario_utl_mod` varchar(255) DEFAULT NULL,
  `egreso_motivo` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `transporte_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`egreso_id`),
  KEY `FK_60wvpxwuocvycqqaxcvjh5ik7` (`cliente_id`),
  KEY `FK_c69uyi7q85x16os1hpeqeofni` (`transporte_id`),
  CONSTRAINT `FK_60wvpxwuocvycqqaxcvjh5ik7` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`),
  CONSTRAINT `FK_c69uyi7q85x16os1hpeqeofni` FOREIGN KEY (`transporte_id`) REFERENCES `transporte` (`transporte_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso_acopio`
--

LOCK TABLES `egreso_acopio` WRITE;
/*!40000 ALTER TABLE `egreso_acopio` DISABLE KEYS */;
/*!40000 ALTER TABLE `egreso_acopio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `empresa_id` int(11) NOT NULL AUTO_INCREMENT,
  `empresa_descripcion` varchar(255) DEFAULT NULL,
  `empresa_fecha_alta` date DEFAULT NULL,
  `empresa_fecha_baja` date DEFAULT NULL,
  `empresa_fecha_ult_mod` date DEFAULT NULL,
  `empresa_nombre` varchar(255) DEFAULT NULL,
  `empresa_usuario_alta` varchar(255) DEFAULT NULL,
  `empresa_usuario_baja` varchar(255) DEFAULT NULL,
  `empresa_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`empresa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingreso_acopio`
--

DROP TABLE IF EXISTS `ingreso_acopio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingreso_acopio` (
  `ingreso_id` int(11) NOT NULL AUTO_INCREMENT,
  `ingreso_estado` varchar(255) DEFAULT NULL,
  `ingreso_cantidad_total` int(11) DEFAULT NULL,
  `ingreso_fecha` date DEFAULT NULL,
  `ingreso_fecha_alta` date DEFAULT NULL,
  `ingreso_fecha_baja` date DEFAULT NULL,
  `ingreso_fecha_ult_mod` date DEFAULT NULL,
  `ingreso_usuario_alta` varchar(255) DEFAULT NULL,
  `ingreso_usuario_baja` varchar(255) DEFAULT NULL,
  `ingreso_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `campania_id` int(11) DEFAULT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  `lote_id` int(11) DEFAULT NULL,
  `tipo_acopio_id` int(11) DEFAULT NULL,
  `tipo_grano_id` int(11) DEFAULT NULL,
  `tipo_laboreo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ingreso_id`),
  KEY `FK_28ro579y7i6xxyp8xpm4apfwv` (`campania_id`),
  KEY `FK_daecoe2gpwf8dgqk2dtpaejei` (`empresa_id`),
  KEY `FK_kiayc5yj9ibsok3m1o29rqg83` (`lote_id`),
  KEY `FK_rk3ehbfmgseahbo8v414jo8sh` (`tipo_acopio_id`),
  KEY `FK_7rqt3bnc5u0vjfpkm3o8f8d4v` (`tipo_grano_id`),
  KEY `FK_rrhr5aibjl4mb1rugracyajig` (`tipo_laboreo_id`),
  CONSTRAINT `FK_28ro579y7i6xxyp8xpm4apfwv` FOREIGN KEY (`campania_id`) REFERENCES `campania` (`cna_id`),
  CONSTRAINT `FK_7rqt3bnc5u0vjfpkm3o8f8d4v` FOREIGN KEY (`tipo_grano_id`) REFERENCES `tipo_grano` (`tgr_id`),
  CONSTRAINT `FK_daecoe2gpwf8dgqk2dtpaejei` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`empresa_id`),
  CONSTRAINT `FK_kiayc5yj9ibsok3m1o29rqg83` FOREIGN KEY (`lote_id`) REFERENCES `lote` (`lte_id`),
  CONSTRAINT `FK_rk3ehbfmgseahbo8v414jo8sh` FOREIGN KEY (`tipo_acopio_id`) REFERENCES `tipo_acopio` (`tipo_acopio_id`),
  CONSTRAINT `FK_rrhr5aibjl4mb1rugracyajig` FOREIGN KEY (`tipo_laboreo_id`) REFERENCES `tipo_laboreo` (`tpo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso_acopio`
--

LOCK TABLES `ingreso_acopio` WRITE;
/*!40000 ALTER TABLE `ingreso_acopio` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingreso_acopio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingreso_insumo`
--

DROP TABLE IF EXISTS `ingreso_insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingreso_insumo` (
  `ingreso_id` int(11) NOT NULL AUTO_INCREMENT,
  `ingreso_cantidad_items` int(11) DEFAULT NULL,
  `ingreso_fecha` date DEFAULT NULL,
  `ingreso_fecha_alta` date DEFAULT NULL,
  `ingreso_fecha_baja` date DEFAULT NULL,
  `ingreso_fecha_ult_mod` date DEFAULT NULL,
  `ingreso_nro_remito` int(11) DEFAULT NULL,
  `ingreso_usuario_alta` varchar(255) DEFAULT NULL,
  `ingreso_usuario_baja` varchar(255) DEFAULT NULL,
  `ingreso_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ingreso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso_insumo`
--

LOCK TABLES `ingreso_insumo` WRITE;
/*!40000 ALTER TABLE `ingreso_insumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingreso_insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumo`
--

DROP TABLE IF EXISTS `insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insumo` (
  `ins_id` int(11) NOT NULL,
  `ins_descripcion` varchar(255) DEFAULT NULL,
  `ins_fecha_alta` date DEFAULT NULL,
  `ins_fecha_baja` date DEFAULT NULL,
  `ins_fecha_ult_mod` date DEFAULT NULL,
  `ins_nombre` varchar(255) DEFAULT NULL,
  `ins_unidad_medida` varchar(255) DEFAULT NULL,
  `ins_usuario_alta` varchar(255) DEFAULT NULL,
  `ins_usuario_baja` varchar(255) DEFAULT NULL,
  `ins_usuario_utl_mod` varchar(255) DEFAULT NULL,
  `ins_tin_id` int(11) DEFAULT NULL,
  `ins_stock` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ins_id`),
  KEY `FK_kqkjn9y49q1tu6j2j6sxs68kb` (`ins_tin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumo`
--

LOCK TABLES `insumo` WRITE;
/*!40000 ALTER TABLE `insumo` DISABLE KEYS */;
INSERT INTO `insumo` VALUES (1,'Nafta premium','2016-06-30',NULL,NULL,'Combustible','Litros','admin',NULL,NULL,4,1500.00),(2,'Abono Orgánico','2016-11-14',NULL,NULL,'Abono','Kilos','admin',NULL,NULL,3,100.00),(3,'Fertilizante','2016-11-14',NULL,NULL,'Fertilizante','Litros','admin',NULL,NULL,3,2.00),(4,'Herbicida ','2016-11-14',NULL,NULL,'Herbicida','Litros','admin',NULL,NULL,3,2.00),(5,'Hojas A4 500 haojas Xerox','2016-11-14',NULL,NULL,'Resmas de hojas','Resma','admin',NULL,NULL,2,5.00),(6,'Toner hp laserjet p1102w','2016-11-14',NULL,NULL,'Toner','Unidad','admin',NULL,NULL,1,2.00),(7,'Distintos tipos de lapicera','2016-11-14',NULL,NULL,'Lapicera','Unidad','admin',NULL,NULL,2,15.00),(8,'Carpeta archivadora','2016-11-14',NULL,NULL,'Carpeta ','Unidad','admin',NULL,NULL,2,8.00),(9,'Monitor 32\'\'','2016-11-14',NULL,NULL,'Monitor','Unidad','admin',NULL,NULL,1,2.00);
/*!40000 ALTER TABLE `insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboreo`
--

DROP TABLE IF EXISTS `laboreo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboreo` (
  `lbo_id` int(11) NOT NULL AUTO_INCREMENT,
  `lbo_descripcion` varchar(255) DEFAULT NULL,
  `lbo_fecha_alta` date DEFAULT NULL,
  `lbo_fecha_baja` date DEFAULT NULL,
  `lbo_fecha_hora_fin` date DEFAULT NULL,
  `lbo_fecha_hora_inicio` date DEFAULT NULL,
  `lbo_fecha_ult_mod` date DEFAULT NULL,
  `lbo_usuario_alta` varchar(255) DEFAULT NULL,
  `lbo_usuario_baja` varchar(255) DEFAULT NULL,
  `lbo_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `tipo_grano_id` int(11) DEFAULT NULL,
  `lbo_tlbo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`lbo_id`),
  KEY `FK_85jvke25fybyysu2fhsf9xujc` (`tipo_grano_id`),
  KEY `FK_pqbss57tm1au8itphuyoxe14p` (`lbo_tlbo_id`),
  CONSTRAINT `FK_85jvke25fybyysu2fhsf9xujc` FOREIGN KEY (`tipo_grano_id`) REFERENCES `tipo_grano` (`tgr_id`),
  CONSTRAINT `FK_pqbss57tm1au8itphuyoxe14p` FOREIGN KEY (`lbo_tlbo_id`) REFERENCES `tipo_laboreo` (`tpo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboreo`
--

LOCK TABLES `laboreo` WRITE;
/*!40000 ALTER TABLE `laboreo` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboreo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboreo_lote_campania`
--

DROP TABLE IF EXISTS `laboreo_lote_campania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboreo_lote_campania` (
  `llc_id` int(11) NOT NULL AUTO_INCREMENT,
  `llc_fecha_alta` date DEFAULT NULL,
  `llc_fecha_baja` date DEFAULT NULL,
  `llc_fecha_ult_mod` date DEFAULT NULL,
  `llcLboId` int(11) NOT NULL,
  `llcLcpId` int(11) NOT NULL,
  `llc_usuario_alta` varchar(255) DEFAULT NULL,
  `llc_usuario_baja` varchar(255) DEFAULT NULL,
  `llc_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `llc_lbo_id` int(11) NOT NULL,
  `llc_lcp_id` int(11) NOT NULL,
  PRIMARY KEY (`llc_id`),
  KEY `FK_gpqi2mm06dgic4bro70simc1f` (`llc_lbo_id`),
  KEY `FK_9oj1k5s070l783buicrr0g1r5` (`llc_lcp_id`),
  CONSTRAINT `FK_9oj1k5s070l783buicrr0g1r5` FOREIGN KEY (`llc_lcp_id`) REFERENCES `lote_campania` (`lcp_id`),
  CONSTRAINT `FK_gpqi2mm06dgic4bro70simc1f` FOREIGN KEY (`llc_lbo_id`) REFERENCES `laboreo` (`lbo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboreo_lote_campania`
--

LOCK TABLES `laboreo_lote_campania` WRITE;
/*!40000 ALTER TABLE `laboreo_lote_campania` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboreo_lote_campania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lote`
--

DROP TABLE IF EXISTS `lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lote` (
  `lte_id` int(11) NOT NULL AUTO_INCREMENT,
  `lte_cant_metros` int(11) DEFAULT NULL,
  `lte_denominacion` varchar(255) DEFAULT NULL,
  `lte_fecha_alta` date DEFAULT NULL,
  `lte_fecha_baja` date DEFAULT NULL,
  `lte_fecha_desde` date DEFAULT NULL,
  `lte_fecha_hasta` date DEFAULT NULL,
  `lte_fecha_ult_mod` date DEFAULT NULL,
  `lte_ubicacion` varchar(255) DEFAULT NULL,
  `lte_usuario_alta` varchar(255) DEFAULT NULL,
  `lte_usuario_baja` varchar(255) DEFAULT NULL,
  `lte_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `campo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`lte_id`),
  KEY `FK_5og2gsi9iogb6ii81x7dt7c8d` (`campo_id`),
  CONSTRAINT `FK_5og2gsi9iogb6ii81x7dt7c8d` FOREIGN KEY (`campo_id`) REFERENCES `campo` (`cpo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lote`
--

LOCK TABLES `lote` WRITE;
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lote_campania`
--

DROP TABLE IF EXISTS `lote_campania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lote_campania` (
  `lcp_id` int(11) NOT NULL AUTO_INCREMENT,
  `lcpCnaId` int(11) NOT NULL,
  `lcp_fecha_alta` date DEFAULT NULL,
  `lcp_fecha_baja` date DEFAULT NULL,
  `lcp_fecha_fin` date DEFAULT NULL,
  `lcp_fecha_inicio` date DEFAULT NULL,
  `lcp_fecha_ult_mod` date DEFAULT NULL,
  `lcpLteId` int(11) NOT NULL,
  `lcp_usuario_alta` varchar(255) DEFAULT NULL,
  `lcp_usuario_baja` varchar(255) DEFAULT NULL,
  `lcp_usuario_ult_mod` varchar(255) DEFAULT NULL,
  `lcp_cna_id` int(11) DEFAULT NULL,
  `lcp_lte_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`lcp_id`),
  KEY `FK_og7ueh3gxcwhciphsdnv0gb7s` (`lcp_cna_id`),
  KEY `FK_97ylso0un63p98hgh1boklh8v` (`lcp_lte_id`),
  CONSTRAINT `FK_97ylso0un63p98hgh1boklh8v` FOREIGN KEY (`lcp_lte_id`) REFERENCES `lote` (`lte_id`),
  CONSTRAINT `FK_og7ueh3gxcwhciphsdnv0gb7s` FOREIGN KEY (`lcp_cna_id`) REFERENCES `campania` (`cna_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lote_campania`
--

LOCK TABLES `lote_campania` WRITE;
/*!40000 ALTER TABLE `lote_campania` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote_campania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maquinaria`
--

DROP TABLE IF EXISTS `maquinaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maquinaria` (
  `maq_id` int(11) NOT NULL AUTO_INCREMENT,
  `maq_anio_fabricacion` varchar(255) DEFAULT NULL,
  `maq_descripcion` varchar(255) DEFAULT NULL,
  `maq_fecha_alta` date DEFAULT NULL,
  `maq_fecha_baja` date DEFAULT NULL,
  `maq_fecha_ult_mod` date DEFAULT NULL,
  `maq_marca` varchar(255) DEFAULT NULL,
  `maq_modelo` varchar(255) DEFAULT NULL,
  `maq_nombre` varchar(255) DEFAULT NULL,
  `maqTestadoId` int(11) DEFAULT NULL,
  `maqTmaqId` int(11) DEFAULT NULL,
  `maq_usuario_alta` varchar(255) DEFAULT NULL,
  `maq_usuario_baja` varchar(255) DEFAULT NULL,
  `maq_usuario_utl_mod` varchar(255) DEFAULT NULL,
  `maq_testado_id` int(11) DEFAULT NULL,
  `maq_tmaq_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`maq_id`),
  KEY `FK_knxb3ey62rl2h4hrk9b8uckce` (`maq_testado_id`),
  KEY `FK_2jo40fi9ebum8t5vxu6osei7f` (`maq_tmaq_id`),
  CONSTRAINT `FK_2jo40fi9ebum8t5vxu6osei7f` FOREIGN KEY (`maq_tmaq_id`) REFERENCES `tipo_maquinaria` (`tma_id`),
  CONSTRAINT `FK_knxb3ey62rl2h4hrk9b8uckce` FOREIGN KEY (`maq_testado_id`) REFERENCES `tipo_estado_maquinaria` (`te_ma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maquinaria`
--

LOCK TABLES `maquinaria` WRITE;
/*!40000 ALTER TABLE `maquinaria` DISABLE KEYS */;
INSERT INTO `maquinaria` VALUES (1,NULL,'Fumigadora ','2016-11-13',NULL,NULL,'Agrin Metal','LIMITED 3000','Fumigadora Agrin Metal',NULL,NULL,'admin',NULL,NULL,2,4),(2,NULL,'Surcos a 70','2016-11-13',NULL,NULL,'Agrometal','Agrometal 10','Sembradora a 70 surcos',NULL,NULL,'admin',NULL,NULL,2,3),(3,NULL,'Surcos a 52','2016-11-13',NULL,NULL,'Agrometal','Agrometal 13','Sembradora a 52 surcos',NULL,NULL,'admin',NULL,NULL,3,3),(4,NULL,'Tractor Valmet 1180','2016-11-13',NULL,NULL,'Valmet','1180','Tractor Valmet 1180',NULL,NULL,'admin',NULL,NULL,2,1),(5,NULL,'Tractor Valmet 985','2016-11-13',NULL,NULL,'Valmet','985','Tractor Valmet 985',NULL,NULL,'admin',NULL,NULL,2,1),(6,NULL,'Tractor Fiat 780','2016-11-13',NULL,NULL,'Fiat','780','Tractor Fiat 780',NULL,NULL,'admin',NULL,NULL,2,1),(7,NULL,'Tractor Zanello 100','2016-11-13',NULL,NULL,'Zanello','100','Tractor Zanello 100',NULL,NULL,'admin',NULL,NULL,1,1),(8,NULL,'Desmalezadora MTD','2016-11-13','2016-11-14',NULL,'MTD','MTD','Desmalezadora MTD',NULL,NULL,'admin','admin',NULL,4,1),(9,NULL,'Tolva Autodescargable','2016-11-13','2016-11-14',NULL,'Agromec','','Tolva Autodescargable',NULL,NULL,'admin','admin',NULL,4,6),(10,NULL,'Chimango 14 M x 165 mm','2016-11-13',NULL,NULL,'Agromec','','Chimango',NULL,NULL,'admin',NULL,NULL,2,6),(11,NULL,'Tanque 1000 Litros Gas Oil ','2016-11-13',NULL,NULL,'','','Tanque Gas Oil',NULL,NULL,'admin',NULL,NULL,2,6),(12,NULL,'Silo chapa 100 Tn','2016-11-13',NULL,NULL,'','','Silo',NULL,NULL,'admin',NULL,NULL,2,6);
/*!40000 ALTER TABLE `maquinaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos_stock`
--

DROP TABLE IF EXISTS `movimientos_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimientos_stock` (
  `mos_id` int(11) NOT NULL AUTO_INCREMENT,
  `mos_cantidad` int(11) DEFAULT NULL,
  `mos_fecha_alta` date DEFAULT NULL,
  `mos_fecha_baja` date DEFAULT NULL,
  `mos_fecha_ult_mod` date DEFAULT NULL,
  `mos_sin_id` int(11) DEFAULT NULL,
  `mos_tipo_movimiento` varchar(255) DEFAULT NULL,
  `mos_usuario_alta` varchar(255) DEFAULT NULL,
  `mos_usuario_baja` varchar(255) DEFAULT NULL,
  `mos_usuario_utl_mod` varchar(255) DEFAULT NULL,
  `stockInsumoByMosSinId` int(11) NOT NULL,
  PRIMARY KEY (`mos_id`),
  KEY `FK_9aricm8x7hpipgblhtx603pxd` (`stockInsumoByMosSinId`),
  CONSTRAINT `FK_9aricm8x7hpipgblhtx603pxd` FOREIGN KEY (`stockInsumoByMosSinId`) REFERENCES `stock_insumo` (`sin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos_stock`
--

LOCK TABLES `movimientos_stock` WRITE;
/*!40000 ALTER TABLE `movimientos_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimientos_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_insumo`
--

DROP TABLE IF EXISTS `solicitud_insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitud_insumo` (
  `si_id` int(11) NOT NULL AUTO_INCREMENT,
  `si_cantidad_items` int(11) DEFAULT NULL,
  `si_estado` varchar(255) DEFAULT NULL,
  `si_fecha_alta` date DEFAULT NULL,
  `si_fecha_baja` date DEFAULT NULL,
  `si_fecha_compra` date DEFAULT NULL,
  `si_fecha_ult_mod` date DEFAULT NULL,
  `si_monto_total` decimal(19,2) DEFAULT NULL,
  `si_nro_remito` int(11) DEFAULT NULL,
  `si_nro_solicitud` int(11) DEFAULT NULL,
  `si_usuario_alta` varchar(255) DEFAULT NULL,
  `si_usuario_baja` varchar(255) DEFAULT NULL,
  `si_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`si_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_insumo`
--

LOCK TABLES `solicitud_insumo` WRITE;
/*!40000 ALTER TABLE `solicitud_insumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud_insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_insumo`
--

DROP TABLE IF EXISTS `stock_insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_insumo` (
  `sin_id` int(11) NOT NULL AUTO_INCREMENT,
  `sin_fecha_alta` date DEFAULT NULL,
  `sin_fecha_baja` date DEFAULT NULL,
  `sin_fecha_ult_mod` date DEFAULT NULL,
  `sin_ins_id` int(11) DEFAULT NULL,
  `sin_total` int(11) DEFAULT NULL,
  `sin_usuario_alta` varchar(255) DEFAULT NULL,
  `sin_usuario_baja` varchar(255) DEFAULT NULL,
  `sin_usuario_utl_mod` varchar(255) DEFAULT NULL,
  `insumoBySinInsId` int(11) NOT NULL,
  PRIMARY KEY (`sin_id`),
  KEY `FK_8nq9uhua8232u0gvby1ddf28b` (`insumoBySinInsId`),
  CONSTRAINT `FK_8nq9uhua8232u0gvby1ddf28b` FOREIGN KEY (`insumoBySinInsId`) REFERENCES `insumo` (`ins_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_insumo`
--

LOCK TABLES `stock_insumo` WRITE;
/*!40000 ALTER TABLE `stock_insumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_acopio`
--

DROP TABLE IF EXISTS `tipo_acopio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_acopio` (
  `tipo_acopio_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_acopio_descripcion` varchar(255) DEFAULT NULL,
  `tipo_acopio_fecha_alta` date DEFAULT NULL,
  `tipo_acopio__fecha_baja` date DEFAULT NULL,
  `tipo_acopio_fecha_ult_mod` date DEFAULT NULL,
  `tipo_acopio_nombre` varchar(255) DEFAULT NULL,
  `tipo_acopio_usuario_alta` varchar(255) DEFAULT NULL,
  `tipo_acopio__usuario_baja` varchar(255) DEFAULT NULL,
  `tipo_acopio__usuario_utl_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tipo_acopio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_acopio`
--

LOCK TABLES `tipo_acopio` WRITE;
/*!40000 ALTER TABLE `tipo_acopio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_acopio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_estado_grano`
--

DROP TABLE IF EXISTS `tipo_estado_grano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_estado_grano` (
  `te_grano_id` int(11) NOT NULL AUTO_INCREMENT,
  `te_grano_descripcion` varchar(255) DEFAULT NULL,
  `te_grano_fecha_alta` date DEFAULT NULL,
  `te_grano_fecha_baja` date DEFAULT NULL,
  `te_grano_fecha_ult_mod` date DEFAULT NULL,
  `te_grano_nombre` varchar(255) DEFAULT NULL,
  `te_grano_usuario_alta` varchar(255) DEFAULT NULL,
  `te_grano_usuario_baja` varchar(255) DEFAULT NULL,
  `te_grano_usuario_utl_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`te_grano_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_estado_grano`
--

LOCK TABLES `tipo_estado_grano` WRITE;
/*!40000 ALTER TABLE `tipo_estado_grano` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_estado_grano` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_estado_maquinaria`
--

DROP TABLE IF EXISTS `tipo_estado_maquinaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_estado_maquinaria` (
  `te_ma_id` int(11) NOT NULL AUTO_INCREMENT,
  `te_ma_descripcion` varchar(255) DEFAULT NULL,
  `te_ma_fecha_alta` date DEFAULT NULL,
  `te_ma_fecha_baja` date DEFAULT NULL,
  `te_ma_fecha_ult_mod` date DEFAULT NULL,
  `te_ma_nombre` varchar(255) DEFAULT NULL,
  `te_ma_usuario_alta` varchar(255) DEFAULT NULL,
  `te_ma_usuario_baja` varchar(255) DEFAULT NULL,
  `te_ma_usuario_utl_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`te_ma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_estado_maquinaria`
--

LOCK TABLES `tipo_estado_maquinaria` WRITE;
/*!40000 ALTER TABLE `tipo_estado_maquinaria` DISABLE KEYS */;
INSERT INTO `tipo_estado_maquinaria` VALUES (1,'Maquinaría que ya no se encuentra en posiblilidad de uso ya que se ha vendido',NULL,NULL,NULL,'Vendido',NULL,NULL,NULL),(2,'Maquinaría Disponible para su uso',NULL,NULL,NULL,'Disponible',NULL,NULL,NULL),(3,'Maquinaría que se encuentra en mantenimiento',NULL,NULL,NULL,'No disponible',NULL,NULL,NULL),(4,'Maquinaría obsoleta',NULL,NULL,NULL,'Desuso',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tipo_estado_maquinaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_grano`
--

DROP TABLE IF EXISTS `tipo_grano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_grano` (
  `tgr_id` int(11) NOT NULL AUTO_INCREMENT,
  `tgr_descripcion` varchar(255) DEFAULT NULL,
  `tgr_fecha_alta` date DEFAULT NULL,
  `tgr_fecha_baja` date DEFAULT NULL,
  `tgr_fecha_ult_mod` date DEFAULT NULL,
  `tgr_nombre` varchar(255) DEFAULT NULL,
  `tgr_usuario_alta` varchar(255) DEFAULT NULL,
  `tgr_usuario_baja` varchar(255) DEFAULT NULL,
  `tgr_usuario_utl_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tgr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_grano`
--

LOCK TABLES `tipo_grano` WRITE;
/*!40000 ALTER TABLE `tipo_grano` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_grano` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_insumo`
--

DROP TABLE IF EXISTS `tipo_insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_insumo` (
  `tin_id` int(11) NOT NULL AUTO_INCREMENT,
  `tin_descripcion` varchar(255) DEFAULT NULL,
  `tin_fecha_alta` date DEFAULT NULL,
  `tin_fecha_baja` date DEFAULT NULL,
  `tin_fecha_ult_mod` date DEFAULT NULL,
  `tin_nombre` varchar(255) DEFAULT NULL,
  `tin_usuario_alta` varchar(255) DEFAULT NULL,
  `tin_usuario_baja` varchar(255) DEFAULT NULL,
  `tin_usuario_utl_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_insumo`
--

LOCK TABLES `tipo_insumo` WRITE;
/*!40000 ALTER TABLE `tipo_insumo` DISABLE KEYS */;
INSERT INTO `tipo_insumo` VALUES (1,'Material tecnológico','2016-11-14',NULL,NULL,'Informáticos','admin',NULL,NULL),(2,'Material de oficina','2016-11-14',NULL,NULL,'Oficina','admin',NULL,NULL),(3,'Insumos necesarios para el cultivo','2016-11-14',NULL,NULL,'Agrícolas','admin',NULL,NULL),(4,'Material Combustible','2016-11-14',NULL,NULL,'Combustible','admin',NULL,NULL),(5,'Insumo necesario para alguna maquinaría','2016-11-14',NULL,NULL,'Repuestos','admin',NULL,NULL),(6,'diversos insumos necesarios','2016-11-14',NULL,NULL,'Otros','admin',NULL,NULL);
/*!40000 ALTER TABLE `tipo_insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_laboreo`
--

DROP TABLE IF EXISTS `tipo_laboreo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_laboreo` (
  `tpo_id` int(11) NOT NULL AUTO_INCREMENT,
  `tpo_descripcion` varchar(255) DEFAULT NULL,
  `tpo_fecha_alta` date DEFAULT NULL,
  `tpo_fecha_baja` date DEFAULT NULL,
  `tpo_fecha_ult_mod` date DEFAULT NULL,
  `tpo_nombre` varchar(255) DEFAULT NULL,
  `tpo_usuario_alta` varchar(255) DEFAULT NULL,
  `tpo_usuario_baja` varchar(255) DEFAULT NULL,
  `tpo_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tpo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_laboreo`
--

LOCK TABLES `tipo_laboreo` WRITE;
/*!40000 ALTER TABLE `tipo_laboreo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_laboreo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_maquinaria`
--

DROP TABLE IF EXISTS `tipo_maquinaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_maquinaria` (
  `tma_id` int(11) NOT NULL AUTO_INCREMENT,
  `tma_descripcion` varchar(255) DEFAULT NULL,
  `tma_fecha_alta` date DEFAULT NULL,
  `tma_fecha_baja` date DEFAULT NULL,
  `tma_fecha_ult_mod` date DEFAULT NULL,
  `tma_nombre` varchar(255) DEFAULT NULL,
  `tma_usuario_alta` varchar(255) DEFAULT NULL,
  `tma_usuario_baja` varchar(255) DEFAULT NULL,
  `tma_usuario_utl_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_maquinaria`
--

LOCK TABLES `tipo_maquinaria` WRITE;
/*!40000 ALTER TABLE `tipo_maquinaria` DISABLE KEYS */;
INSERT INTO `tipo_maquinaria` VALUES (1,'Vehículo que se mueve por sus propios medios','2016-10-27',NULL,NULL,'Autopropulsado','admin',NULL,NULL),(2,'Vehículo capaz de realizar labores de recolección de productos agrícolas','2016-11-13',NULL,NULL,'Cosechadora','admin',NULL,NULL),(3,'Máquina agrícola destinada siembra de granos','2016-11-13',NULL,NULL,'Sembradora','admin',NULL,NULL),(4,'Máquina agrícola encargada de fumigar zonas de terreno','2016-11-13',NULL,NULL,'Fumigadora','admin',NULL,NULL),(5,'Máquina agricola utilizada para realizar distintos tipos de laboreos en una zona de terreno','2016-11-13',NULL,NULL,'Labranza','admin',NULL,NULL),(6,'Medios utilizados para almacenar o transportar granos cosechados','2016-11-13',NULL,NULL,'Almacenaje','admin',NULL,NULL),(7,'Diversos tipos de herramientas necesarias para complementar el laboreo','2016-11-13',NULL,NULL,'Herramientas','admin',NULL,NULL),(8,'Otros ','2016-11-13',NULL,NULL,'Varios','admin',NULL,NULL);
/*!40000 ALTER TABLE `tipo_maquinaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_medio`
--

DROP TABLE IF EXISTS `tipo_medio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_medio` (
  `ti_medio_id` int(11) NOT NULL AUTO_INCREMENT,
  `ti_medio_descripcion` varchar(255) DEFAULT NULL,
  `ti_medio_fecha_alta` date DEFAULT NULL,
  `ti_medio_fecha_baja` date DEFAULT NULL,
  `ti_medio_fecha_ult_mod` date DEFAULT NULL,
  `ti_medio_nombre` varchar(255) DEFAULT NULL,
  `ti_medio_usuario_alta` varchar(255) DEFAULT NULL,
  `ti_medio_usuario_baja` varchar(255) DEFAULT NULL,
  `ti_medio_usuario_utl_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ti_medio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_medio`
--

LOCK TABLES `tipo_medio` WRITE;
/*!40000 ALTER TABLE `tipo_medio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_medio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporte`
--

DROP TABLE IF EXISTS `transporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transporte` (
  `transporte_id` int(11) NOT NULL AUTO_INCREMENT,
  `transporte_descripcion` varchar(255) DEFAULT NULL,
  `transporte_fecha_alta` date DEFAULT NULL,
  `transporte_fecha_baja` date DEFAULT NULL,
  `transporte_fecha_ult_mod` date DEFAULT NULL,
  `transporte_nombre` varchar(255) DEFAULT NULL,
  `transporte_usuario_alta` varchar(255) DEFAULT NULL,
  `transporte_usuario_baja` varchar(255) DEFAULT NULL,
  `transporte_usuario_ult_mod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transporte_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporte`
--

LOCK TABLES `transporte` WRITE;
/*!40000 ALTER TABLE `transporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `transporte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-14 21:41:12
