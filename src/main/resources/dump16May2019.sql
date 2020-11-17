CREATE DATABASE  IF NOT EXISTS `forum_i` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `forum_i`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: forumv2
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL,
  `creationDate` date NOT NULL,
  `interestingAmount` bigint(20) NOT NULL,
  `levelId` int(11) NOT NULL,
  `negativeAmount` bigint(20) NOT NULL,
  `originAnswerId` bigint(20) DEFAULT NULL,
  `positiveAmount` bigint(20) NOT NULL,
  `text` varchar(200) DEFAULT NULL,
  `themes` varchar(200) DEFAULT NULL,
  `opinion_id` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj2q0ov4d8yv5swlgsji27k1sw` (`opinion_id`),
  KEY `FK93fojp840l24jbpno72bsggkj` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_user`
--

DROP TABLE IF EXISTS `answer_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_user` (
  `id` bigint(20) NOT NULL,
  `choice` varchar(255) NOT NULL,
  `answerId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrspf14cadbyvsjpk5g8ku4egf` (`answerId`),
  KEY `FK21gkp6lnxaaipci9uh8kvn8y0` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_user`
--

LOCK TABLES `answer_user` WRITE;
/*!40000 ALTER TABLE `answer_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(50) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa0b0tp1p1vnvlid88suctbcib` (`userId`,`authority`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'VERIFIED',2),(2,'VERIFIED',3),(3,'VERIFIED',4),(4,'UNVERIFIED',NULL),(5,'VERIFIED',5),(6,'VERIFIED',6),(7,'VERIFIED',7),(8,'VERIFIED',8),(9,'VERIFIED',9);
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidatosquevote`
--

DROP TABLE IF EXISTS `candidatosquevote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidatosquevote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `postulacion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKche1rts5uqe7r3j9m47bc0mwf` (`postulacion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidatosquevote`
--

LOCK TABLES `candidatosquevote` WRITE;
/*!40000 ALTER TABLE `candidatosquevote` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidatosquevote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'presidente'),(2,'senador'),(3,'diputado'),(4,'alcalde o relacionado'),(5,'otros');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoNemotecnico` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orderChoice` int(11) DEFAULT NULL,
  `rutaIcono` varchar(255) DEFAULT NULL,
  `tipo_evaluacion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfwrdt0vjxk1h0fjtl32fm88xv` (`tipo_evaluacion_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES (1,'p','positivo',1,'olymp-accordion-open-icon',1),(2,'n','negativo',2,'olymp-accordion-close-icon',1),(3,'i','interesante',3,'olymp-thunder-icon',1),(4,'d','descortés',1,NULL,2),(5,'d2','grosería',2,NULL,2),(6,'d3','violencia',3,NULL,2),(13,'c','colectivista',1,NULL,3),(14,'l','libertario',2,NULL,3),(9,'p','populista',1,'olymp-circus-icon',4),(11,'t','troll',2,'olymp-troll-icon',4),(12,'v','violento',4,'olymp-violent-icon',4);
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choicevote`
--

DROP TABLE IF EXISTS `choicevote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choicevote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `opinion_choice_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKksv71bm2jn7hd21my90dhy9e6` (`user_id`),
  KEY `FK2ph9j0hm4y291n9t13bxlov9k` (`opinion_choice_id`)
) ENGINE=MyISAM AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choicevote`
--

LOCK TABLES `choicevote` WRITE;
/*!40000 ALTER TABLE `choicevote` DISABLE KEYS */;
INSERT INTO `choicevote` VALUES (20,4,4),(18,4,1),(21,5,8),(22,5,15),(30,5,4),(26,5,18),(28,5,11),(32,2,26),(33,2,27),(34,2,25),(37,2,28),(63,2,38),(82,2,42),(85,2,44),(51,2,48),(84,2,45),(75,2,9),(77,2,2),(87,5,44),(90,5,116),(91,5,206),(92,5,248),(93,5,247),(94,5,249),(109,5,304),(116,5,376),(117,5,376),(118,5,391);
/*!40000 ALTER TABLE `choicevote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudadano`
--

DROP TABLE IF EXISTS `ciudadano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudadano` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoIdentificacion` varchar(255) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `fotoAvatar` varchar(255) DEFAULT NULL,
  `fotoAvatarDebate` varchar(255) DEFAULT NULL,
  `fotoAvatarMedium` varchar(255) DEFAULT NULL,
  `fotoAvatarSmall` varchar(255) DEFAULT NULL,
  `comunaDondeVoto_codigo` bigint(20) DEFAULT NULL,
  `direccionResidencia_id` bigint(20) DEFAULT NULL,
  `paisDondeVoto_id` bigint(20) DEFAULT NULL,
  `tipoIdentificacion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKecj9byikicercvf83acah2u8r` (`comunaDondeVoto_codigo`),
  KEY `FKs0mpwvewrsyarp78s52o43p0y` (`direccionResidencia_id`),
  KEY `FK94ymvbj97vn1dtywms6dkmtgi` (`paisDondeVoto_id`),
  KEY `FKdbxolhj7gfrx276hf9j4tr178` (`tipoIdentificacion_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudadano`
--

LOCK TABLES `ciudadano` WRITE;
/*!40000 ALTER TABLE `ciudadano` DISABLE KEYS */;
INSERT INTO `ciudadano` VALUES (1,NULL,NULL,'ae9136f8-da56-489d-9e45-e85de98635ac_1549963799791.jpg','ca67ff76-c8de-4fa3-98f7-7683211d33c7_1549963799791.jpg','0150811e-6094-498a-a4fc-66cf702654e1_1549963799791.jpg','da532fa5-0d09-4da7-b00b-0526ae6b8e38_1549963799791.jpg',NULL,NULL,NULL,NULL),(2,NULL,NULL,'4e6e1317-2091-45b3-a5f4-00c9b187c3bd_1550563720830.jpg','3c8b4eaf-0179-4f58-84b6-66e8b694a70f_1550563720830.jpg','ef3006a1-4f0e-4800-813d-cc1fd7760979_1550563720830.jpg','6950fc14-ff90-481e-8d75-20390e507274_1550563720830.jpg',NULL,NULL,NULL,NULL),(3,NULL,NULL,'7f0cbd08-e356-45f2-a5b3-eda6061a290a_1550566193129.jpg','8b87feba-82a3-4588-a301-bc8f52d2a1f9_1550566193129.jpg','ac88e400-4ced-4138-b7c6-76c93bf87af4_1550566193129.jpg','483dd10b-a288-49ea-8a30-72ccb5d132f6_1550566193129.jpg',NULL,NULL,NULL,NULL),(4,NULL,NULL,'879116b8-3ae2-483a-84f6-8208dc62be5b_1550566936443.jpg','401b1971-27b7-4eea-96c3-a232f8f9d5db_1550566936443.jpg','d4f70487-3af3-4c11-a8a0-56fe781afd72_1550566936443.jpg','853c1c29-feb4-4b6c-902e-bebe7dab728a_1550566936443.jpg',NULL,NULL,NULL,NULL),(5,NULL,NULL,'08f6b6db-4b71-46ce-b12e-1d49b36a8a55_1550566998512.jpg','2e6eace7-5466-4b36-95a7-3af397f354b7_1550566998512.jpg','3846be60-5ce4-485f-aae5-88cf0a8a8e55_1550566998512.jpg','7e075443-54e9-44eb-bae4-f07f8954be4b_1550566998512.jpg',NULL,NULL,NULL,NULL),(6,NULL,NULL,'7e9e8604-56d7-4de8-8af4-66e4b37c0bd0_1550567192276.jpg','bafcd017-ec3b-4e86-b410-dfb9ff254384_1550567192276.jpg','5ae14a58-3100-45a6-982a-ec86e6f2715b_1550567192276.jpg','2d8a6acf-df7f-42f6-b247-8bbb594de4da_1550567192276.jpg',NULL,NULL,NULL,NULL),(7,NULL,NULL,'87f64f0e-d9e1-4dcc-b370-992c5dc47d36_1550832480537.jpg','298a96b7-88bc-44fa-a244-fffbcb19205f_1550832480537.jpg','e95dee27-0fd3-4f8f-b6cd-fd605bc1d2e9_1550832480537.jpg','1aba5fc7-f78f-44f3-a53d-4471611aa671_1550832480537.jpg',NULL,NULL,NULL,NULL),(8,NULL,NULL,'dbbb8c25-60bd-4667-ad9c-04c0fe37bdd2_1555568426893.jpg','25a24292-74a2-42c4-b6b2-09e6f95e7adc_1555568426893.jpg','52e6f0e4-8c95-4d09-b33f-d7259bc4791d_1555568426893.jpg','6f09d7ed-0107-439d-ada3-3523df0ed1b3_1555568426893.jpg',NULL,NULL,NULL,NULL),(9,NULL,NULL,'f5b19f24-5b36-4fef-b7a0-b9449b038acd_1555568531035.jpg','f9f98a38-84ec-49dc-984e-5f1b015e99d7_1555568531035.jpg','1f1975d9-15fe-4420-871e-ff5a95fe5deb_1555568531035.jpg','c95b65e3-1a33-41d9-9792-2cb1913c8a77_1555568531035.jpg',NULL,NULL,NULL,NULL),(10,NULL,NULL,'b503c43f-1b62-4bd3-af60-fe945b2d3076_1555569962772.jpg','4062436e-8a6d-4402-a366-a8a15926896e_1555569962772.jpg','1d9a261b-56e8-45b7-be4f-077fe909d2db_1555569962772.jpg','db9937ca-c6a2-4773-9b65-36427aa1b590_1555569962772.jpg',NULL,NULL,NULL,NULL),(11,NULL,NULL,'41a74633-41dd-48ba-8ff7-8cd343097acd_1555570779999.jpg','70681559-f699-4f04-9112-6b6ff60656a7_1555570779999.jpg','c9b6e893-81eb-4323-82e0-850fea5ec61d_1555570779999.jpg','09cfb692-3637-42ef-967f-0efc1273f8cb_1555570779999.jpg',NULL,NULL,NULL,NULL),(12,NULL,NULL,'de813d38-b834-41d5-b1a2-c18e1d30b69f_1555571289599.jpg','35743fe6-6749-49a9-addd-4c16f450de56_1555571289599.jpg','e927d355-d99c-401e-a33a-e4449cd68c32_1555571289599.jpg','0bf331d7-93b7-4d9a-8d41-ebe6c580416b_1555571289599.jpg',NULL,NULL,NULL,NULL),(13,NULL,NULL,'d579d559-1c45-4d4d-9a29-89096c56f451_1555571441355.jpg','cdadf2db-dd7d-4364-966c-a92c97fb63bd_1555571441355.jpg','85c8a0f0-9863-45f5-8666-5a22b133d3d3_1555571441355.jpg','3a00df84-d945-4a72-a4bf-076452a7fe94_1555571441355.jpg',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ciudadano` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comuna`
--

DROP TABLE IF EXISTS `comuna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comuna` (
  `codigo` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `region_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKqmaqgr90koq4rag0yfe99p2ph` (`region_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna`
--

LOCK TABLES `comuna` WRITE;
/*!40000 ALTER TABLE `comuna` DISABLE KEYS */;
INSERT INTO `comuna` VALUES (15101,'Arica',15),(15102,'Camarones',15),(15201,'Putre',15),(15202,'General Lagos',15),(1101,'Iquique',1),(1402,'Camiña',1),(1403,'Colchane',1),(1404,'Huara',1),(1405,'Pica',1),(1401,'Pozo Almonte',1),(1107,'Alto Hospicio',1),(2101,'Antofagasta',2),(2102,'Mejillones',2),(2103,'Sierra Gorda',2),(2104,'Taltal',2),(2201,'Calama',2),(2202,'Ollagüe',2),(2203,'San Pedro de Atacama',2),(2301,'Tocopilla',2),(2302,'María Elena',2),(3101,'Copiapó',3),(3102,'Caldera',3),(3103,'Tierra Amarilla',3),(3201,'Chañaral',3),(3202,'Diego de Almagro',3),(3301,'Vallenar',3),(3302,'Alto del Carmen',3),(3303,'Freirina',3),(3304,'Huasco',3),(4101,'La Serena',4),(4102,'Coquimbo',4),(4103,'Andacollo',4),(4104,'La Higuera',4),(4105,'Paiguano',4),(4106,'Vicuña',4),(4201,'Illapel',4),(4202,'Canela',4),(4203,'Los Vilos',4),(4204,'Salamanca',4),(4301,'Ovalle',4),(4302,'Combarbalá',4),(4303,'Monte Patria',4),(4304,'Punitaqui',4),(4305,'Río Hurtado',4),(5101,'Valparaíso',5),(5102,'Casablanca',5),(5103,'Concón',5),(5104,'Juan Fernández',5),(5105,'Puchuncaví',5),(5801,'Quilpué',5),(5107,'Quintero',5),(5804,'Villa Alemana',5),(5109,'Viña del Mar',5),(5201,'Isla  de Pascua',5),(5301,'Los Andes',5),(5302,'Calle Larga',5),(5303,'Rinconada',5),(5304,'San Esteban',5),(5401,'La Ligua',5),(5402,'Cabildo',5),(5403,'Papudo',5),(5404,'Petorca',5),(5405,'Zapallar',5),(5501,'Quillota',5),(5502,'Calera',5),(5503,'Hijuelas',5),(5504,'La Cruz',5),(5802,'Limache',5),(5506,'Nogales',5),(5803,'Olmué',5),(5601,'San Antonio',5),(5602,'Algarrobo',5),(5603,'Cartagena',5),(5604,'El Quisco',5),(5605,'El Tabo',5),(5606,'Santo Domingo',5),(5701,'San Felipe',5),(5702,'Catemu',5),(5703,'Llaillay',5),(5704,'Panquehue',5),(5705,'Putaendo',5),(5706,'Santa María',5),(6101,'Rancagua',6),(6102,'Codegua',6),(6103,'Coinco',6),(6104,'Coltauco',6),(6105,'Doñihue',6),(6106,'Graneros',6),(6107,'Las Cabras',6),(6108,'Machalí',6),(6109,'Malloa',6),(6110,'Mostazal',6),(6111,'Olivar',6),(6112,'Peumo',6),(6113,'Pichidegua',6),(6114,'Quinta de Tilcoco',6),(6115,'Rengo',6),(6116,'Requínoa',6),(6117,'San Vicente',6),(6201,'Pichilemu',6),(6202,'La Estrella',6),(6203,'Litueche',6),(6204,'Marchihue',6),(6205,'Navidad',6),(6206,'Paredones',6),(6301,'San Fernando',6),(6302,'Chépica',6),(6303,'Chimbarongo',6),(6304,'Lolol',6),(6305,'Nancagua',6),(6306,'Palmilla',6),(6307,'Peralillo',6),(6308,'Placilla',6),(6309,'Pumanque',6),(6310,'Santa Cruz',6),(7101,'Talca',7),(7102,'Constitución',7),(7103,'Curepto',7),(7104,'Empedrado',7),(7105,'Maule',7),(7106,'Pelarco',7),(7107,'Pencahue',7),(7108,'Río Claro',7),(7109,'San Clemente',7),(7110,'San Rafael',7),(7201,'Cauquenes',7),(7202,'Chanco',7),(7203,'Pelluhue',7),(7301,'Curicó',7),(7302,'Hualañé',7),(7303,'Licantén',7),(7304,'Molina',7),(7305,'Rauco',7),(7306,'Romeral',7),(7307,'Sagrada Familia',7),(7308,'Teno',7),(7309,'Vichuquén',7),(7401,'Linares',7),(7402,'Colbún',7),(7403,'Longaví',7),(7404,'Parral',7),(7405,'Retiro',7),(7406,'San Javier',7),(7407,'Villa Alegre',7),(7408,'Yerbas Buenas',7),(8101,'Concepción',8),(8102,'Coronel',8),(8103,'Chiguayante',8),(8104,'Florida',8),(8105,'Hualqui',8),(8106,'Lota',8),(8107,'Penco',8),(8108,'San Pedro de la Paz',8),(8109,'Santa Juana',8),(8110,'Talcahuano',8),(8111,'Tomé',8),(8112,'Hualpén',8),(8201,'Lebu',8),(8202,'Arauco',8),(8203,'Cañete',8),(8204,'Contulmo',8),(8205,'Curanilahue',8),(8206,'Los Álamos',8),(8207,'Tirúa',8),(8301,'Los Ángeles',8),(8302,'Antuco',8),(8303,'Cabrero',8),(8304,'Laja',8),(8305,'Mulchén',8),(8306,'Nacimiento',8),(8307,'Negrete',8),(8308,'Quilaco',8),(8309,'Quilleco',8),(8310,'San Rosendo',8),(8311,'Santa Bárbara',8),(8312,'Tucapel',8),(8313,'Yumbel',8),(8314,'Alto Biobío',8),(8401,'Chillán',8),(8402,'Bulnes',8),(8403,'Cobquecura',8),(8404,'Coelemu',8),(8405,'Coihueco',8),(8406,'Chillán Viejo',8),(8407,'El Carmen',8),(8408,'Ninhue',8),(8409,'Ñiquén',8),(8410,'Pemuco',8),(8411,'Pinto',8),(8412,'Portezuelo',8),(8413,'Quillón',8),(8414,'Quirihue',8),(8415,'Ránquil',8),(8416,'San Carlos',8),(8417,'San Fabián',8),(8418,'San Ignacio',8),(8419,'San Nicolás',8),(8420,'Treguaco',8),(8421,'Yungay',8),(9101,'Temuco',9),(9102,'Carahue',9),(9103,'Cunco',9),(9104,'Curarrehue',9),(9105,'Freire',9),(9106,'Galvarino',9),(9107,'Gorbea',9),(9108,'Lautaro',9),(9109,'Loncoche',9),(9110,'Melipeuco',9),(9111,'Nueva Imperial',9),(9112,'Padre Las Casas',9),(9113,'Perquenco',9),(9114,'Pitrufquén',9),(9115,'Pucón',9),(9116,'Saavedra',9),(9117,'Teodoro Schmidt',9),(9118,'Toltén',9),(9119,'Vilcún',9),(9120,'Villarrica',9),(9121,'Cholchol',9),(9201,'Angol',9),(9202,'Collipulli',9),(9203,'Curacautín',9),(9204,'Ercilla',9),(9205,'Lonquimay',9),(9206,'Los Sauces',9),(9207,'Lumaco',9),(9208,'Purén',9),(9209,'Renaico',9),(9210,'Traiguén',9),(9211,'Victoria',9),(14101,'Valdivia',14),(14102,'Corral',14),(14202,'Futrono',14),(14201,'La Unión',14),(14203,'Lago Ranco',14),(14103,'Lanco',14),(14104,'Los Lagos',14),(14105,'Máfil',14),(14106,'Mariquina',14),(14107,'Paillaco',14),(14108,'Panguipulli',14),(14204,'Río Bueno',14),(10101,'Puerto Montt',10),(10102,'Calbuco',10),(10103,'Cochamó',10),(10104,'Fresia',10),(10105,'Frutillar',10),(10106,'Los Muermos',10),(10107,'Llanquihue',10),(10108,'Maullín',10),(10109,'Puerto Varas',10),(10201,'Castro',10),(10202,'Ancud',10),(10203,'Chonchi',10),(10204,'Curaco de Vélez',10),(10205,'Dalcahue',10),(10206,'Puqueldón',10),(10207,'Queilén',10),(10208,'Quellón',10),(10209,'Quemchi',10),(10210,'Quinchao',10),(10301,'Osorno',10),(10302,'Puerto Octay',10),(10303,'Purranque',10),(10304,'Puyehue',10),(10305,'Río Negro',10),(10306,'San Juan de la Costa',10),(10307,'San Pablo',10),(10401,'Chaitén',10),(10402,'Futaleufú',10),(10403,'Hualaihué',10),(10404,'Palena',10),(11101,'Coihaique',11),(11102,'Lago Verde',11),(11201,'Aisén',11),(11202,'Cisnes',11),(11203,'Guaitecas',11),(11301,'Cochrane',11),(11302,'Higgins',11),(11303,'Tortel',11),(11401,'Chile Chico',11),(11402,'Río Ibáñez',11),(12101,'Punta Arenas',12),(12102,'Laguna Blanca',12),(12103,'Río Verde',12),(12104,'San Gregorio',12),(12201,'Cabo de Hornos',12),(12202,'Antártica',12),(12301,'Porvenir',12),(12302,'Primavera',12),(12303,'Timaukel',12),(12401,'Natales',12),(12402,'Torres del Paine',12),(13101,'Santiago',13),(13102,'Cerrillos',13),(13103,'Cerro Navia',13),(13104,'Conchalí',13),(13105,'El Bosque',13),(13106,'Estación Central',13),(13107,'Huechuraba',13),(13108,'Independencia',13),(13109,'La Cisterna',13),(13110,'La Florida',13),(13111,'La Granja',13),(13112,'La Pintana',13),(13113,'La Reina',13),(13114,'Las Condes',13),(13115,'Lo Barnechea',13),(13116,'Lo Espejo',13),(13117,'Lo Prado',13),(13118,'Macul',13),(13119,'Maipú',13),(13120,'Ñuñoa',13),(13121,'Pedro Aguirre Cerda',13),(13122,'Peñalolén',13),(13123,'Providencia',13),(13124,'Pudahuel',13),(13125,'Quilicura',13),(13126,'Quinta Normal',13),(13127,'Recoleta',13),(13128,'Renca',13),(13129,'San Joaquín',13),(13130,'San Miguel',13),(13131,'San Ramón',13),(13132,'Vitacura',13),(13201,'Puente Alto',13),(13202,'Pirque',13),(13203,'San José de Maipo',13),(13301,'Colina',13),(13302,'Lampa',13),(13303,'Tiltil',13),(13401,'San Bernardo',13),(13402,'Buin',13),(13403,'Calera de Tango',13),(13404,'Paine',13),(13501,'Melipilla',13),(13502,'Alhué',13),(13503,'Curacaví',13),(13504,'María Pinto',13),(13505,'San Pedro',13),(13601,'Talagante',13),(13602,'El Monte',13),(13603,'Isla de Maipo',13),(13604,'Padre Hurtado',13),(13605,'Peñaflor',13);
/*!40000 ALTER TABLE `comuna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `comuna_codigo` bigint(20) DEFAULT NULL,
  `pais_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjeffcng67d4aeobgdyexyoall` (`comuna_codigo`),
  KEY `FK8932m2f5xdvduqloafwv6icr9` (`pais_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eleccion`
--

DROP TABLE IF EXISTS `eleccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eleccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipoEleccion` varchar(255) DEFAULT NULL,
  `pais_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgy8o0r3xp6id23u4orihosypc` (`pais_id`)
) ENGINE=MyISAM AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eleccion`
--

LOCK TABLES `eleccion` WRITE;
/*!40000 ALTER TABLE `eleccion` DISABLE KEYS */;
INSERT INTO `eleccion` VALUES (1,'Confirmed','2018-07-25','Pakistani National Assembly',NULL,185),(2,'Confirmed','2018-07-29','President',NULL,173),(3,'Second Round if needed','2018-07-29','President',NULL,173),(4,'Confirmed','2018-07-29','Cambodian National Assembly',NULL,17),(5,'Confirmed','2018-07-30','Zimbabwean National Assembly',NULL,224),(6,'Confirmed','2018-07-30','Zimbabwean Senate',NULL,224),(7,'Confirmed','2018-07-30','President',NULL,224),(8,'Confirmed','2018-07-30','Referendum',NULL,121),(9,'Confirmed','2018-09-02','Rwandan Chamber of Deputies',NULL,194),(10,'Confirmed','2018-09-09','Swedish Parliament',NULL,31),(11,'Tentative','2018-09-23','Referendum',NULL,71),(12,'Date not confirmed','2018-09-30','Swazi House of Assembly',NULL,79),(13,'Confirmed','2018-10-07','Brazilian Chamber of Deputies',NULL,60),(14,'Confirmed','2018-10-07','Bosnia and Herzegovina House of Representatives',NULL,59),(15,'Confirmed','2018-10-07','President',NULL,59),(16,'Confirmed','2018-10-07','Bosnia and Herzegovina House of Peoples',NULL,59),(17,'Confirmed','2018-10-07','Latvian Parliament',NULL,168),(18,'Confirmed','2018-10-07','President',NULL,60),(19,'Confirmed','2018-10-14','Luxembourg Chamber of Deputies',NULL,22),(20,'Confirmed','2018-10-20','Afgahni House of People',NULL,73),(21,'Second Round if needed','2018-10-28','President',NULL,60),(22,'Date not confirmed','2018-10-31','President',NULL,117),(23,'Date not confirmed','2018-10-31','President',NULL,153),(24,'Date not confirmed','2018-10-31','President',NULL,139),(25,'Tentative','2018-10-31','Referendum',NULL,172),(26,'Confirmed','2018-11-04','Referendum',NULL,85),(27,'Confirmed','2018-11-06','US House of Representatives',NULL,68),(28,'Confirmed','2018-11-06','Non-Voting Delegate to US House of Representatives',NULL,238),(29,'Confirmed','2018-11-06','US Senate',NULL,68),(30,'Confirmed','2018-11-06','Legislature of Guam',NULL,238),(31,'Confirmed','2018-11-18','Bissau-Guinean Peoples National Assembly',NULL,143),(32,'Confirmed','2018-11-24','Malagasy National Assembly',NULL,64),(33,'Confirmed','2018-11-24','President',NULL,64),(34,'Tentative','2018-11-25','Referendum',NULL,71),(35,'Tentative','2018-12-10','Libyan Council of Deputies ',NULL,228),(36,'Date not confirmed','2018-12-10','President',NULL,228),(37,'Confirmed','2018-12-23','Congolese National Assembly',NULL,118),(38,'Confirmed','2018-12-23','President',NULL,118),(39,'Second Round if needed','2018-12-24','President',NULL,64),(40,'Date not confirmed','2018-12-31','Togolese National Assembly',NULL,208),(41,'Date not confirmed','2018-12-31','Guinean National Assembly',NULL,141),(42,'Date not confirmed','2018-12-31','Sierra Leonean Parliament',NULL,197),(43,'Date not confirmed','2018-12-31','Equatorial Guinean House of Peoples Representatives',NULL,144),(44,'Date not confirmed','2018-12-31','Mauritanian National Assembly',NULL,178),(45,'Date not confirmed','2018-12-31','Malian National Assembly',NULL,173),(46,'Date not confirmed','2018-12-31','Equatorial Guinean Senate',NULL,144),(47,'Date not confirmed','2018-12-31','President',NULL,170),(48,'Date not confirmed','2018-12-31','Cameroonian National Assembly',NULL,117),(49,'Date not confirmed','2019-01-31','Bangladeshi National Parliament',NULL,109),(50,'Confirmed','2019-02-03','President',NULL,198),(51,'Confirmed','2019-02-16','President',NULL,66),(52,'Confirmed','2019-02-16','Nigerian Senate',NULL,66),(53,'Confirmed','2019-02-16','Nigerian House of Representatives',NULL,66),(54,'Date not confirmed','2019-02-28','President',NULL,195),(55,'Confirmed','2019-03-03','Estonian Parliament',NULL,133),(56,'Second Round if needed','2019-03-10','President',NULL,198),(57,'Confirmed','2019-03-31','President',NULL,217),(58,'Confirmed','2019-04-17','President',NULL,152),(59,'Confirmed','2019-04-17','Indonesian House of Representatives',NULL,152),(60,'Confirmed','2019-05-05','President',NULL,186),(61,'Confirmed','2019-05-21','Malawian National Assembly',NULL,180),(62,'Confirmed','2019-05-21','President',NULL,180),(63,'Confirmed','2019-06-15','Referendum',NULL,29),(64,'Date not confirmed','2019-12-31','Croatian Assembly',NULL,149),(65,'Date not confirmed','2019-12-31','Cook Islands Parliament',NULL,46),(66,'Date not confirmed','2019-12-31','Israeli Knesset',NULL,156),(67,'Date not confirmed','2020-05-30','Guyanese National Assembly',NULL,147),(68,'Date not confirmed','2020-11-06','President',NULL,209),(69,'Date not confirmed','2020-12-31','Sudanese National Assembly',NULL,75),(70,'Tentative','2021-01-31','President',NULL,191);
/*!40000 ALTER TABLE `eleccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinion`
--

DROP TABLE IF EXISTS `opinion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationDate` datetime NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `originOpinionId` bigint(20) DEFAULT NULL,
  `tags` text,
  `text` varchar(150) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `pais_id` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc65e2ixcnlhg5hvoayarc5jhm` (`pais_id`),
  KEY `FKik4n90v24m2hgskgjg0kcht2x` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinion`
--

LOCK TABLES `opinion` WRITE;
/*!40000 ALTER TABLE `opinion` DISABLE KEYS */;
INSERT INTO `opinion` VALUES (1,'2019-02-12 09:30:09',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'Test post',NULL,NULL,4),(2,'2019-02-12 12:24:33',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'Testing',NULL,NULL,5),(3,'2019-02-12 12:25:10',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'cost',NULL,NULL,5),(4,'2019-02-13 10:52:54',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'Economics',NULL,NULL,5),(5,'2019-02-22 11:37:35',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'22 Feb 2019',NULL,NULL,7),(6,'2019-02-22 13:50:17',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'ab sdjklfsf sdfsklfjkieosd fs\r\nkdjslkjlskdfjsklfjslf\r\n\r\n\r\nsjflsjfls\r\nsdfsfjsdklfj\r\nsfjslfjsklfjsdklfjskdlf\r\nsdfjdslfjslfs\r\nsfjslf\r\nsflsfjkls',NULL,NULL,2),(7,'2019-02-22 14:12:30',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'Multiple\r\n                             comment\r\n',NULL,NULL,2),(8,'2019-02-25 05:06:23',NULL,0,'Jaipur, Rajasthan, India',NULL,'test user,Ways Test,Ways Means,Mario','Post with tags',NULL,NULL,5),(9,'2019-03-01 04:27:22',NULL,0,'Jaipur, Rajasthan, India',NULL,NULL,'Hello',NULL,NULL,5),(10,'2019-03-01 06:09:08',NULL,0,'Jaipur, Rajasthan, India',NULL,'','sdfsdf',NULL,NULL,5),(11,'2019-03-01 06:10:03',NULL,0,'Jaipur, Rajasthan, India',NULL,'','fg',NULL,NULL,5),(12,'2019-03-01 06:11:45',NULL,0,'Jaipur, Rajasthan, India',NULL,'','jjkkjhhh',NULL,NULL,5),(13,'2019-03-01 06:11:56',NULL,0,'Jaipur, Rajasthan, India',NULL,'','aadsr',NULL,NULL,5),(14,'2019-03-01 06:12:01',NULL,0,'Jaipur, Rajasthan, India',NULL,'','sdf',NULL,NULL,5),(15,'2019-03-01 06:12:13',NULL,0,'Jaipur, Rajasthan, India',NULL,'','asdasd',NULL,NULL,5),(16,'2019-03-01 06:12:34',NULL,0,'Jaipur, Rajasthan, India',NULL,'','sdff',NULL,NULL,5),(18,'2019-03-01 09:29:21',NULL,0,'Jaipur, Rajasthan, India',NULL,'Mario,test user,William Alcdaddicaceg Letuchysky,','check',NULL,NULL,5),(19,'2019-03-01 09:30:31',NULL,0,'Jaipur, Rajasthan, India',NULL,'test user,William Alcdaddicaceg Letuchysky','ab',NULL,NULL,5),(20,'2019-03-06 11:32:51',NULL,0,'Jaipur, Rajasthan, India',NULL,'hello,hello,Create nw tag,create new tag,create new tag','lots of tags',NULL,NULL,5),(21,'2019-03-06 12:52:55',NULL,1,'Jaipur, Rajasthan, India',20,'I_am_trand','new comment',NULL,NULL,5),(22,'2019-03-08 05:31:21',NULL,0,'Jaipur, Rajasthan, India',NULL,'','hello world',NULL,NULL,5),(23,'2019-03-08 06:13:38',NULL,0,'Jaipur, Rajasthan, India',NULL,'','ajax',NULL,NULL,5),(24,'2019-03-08 06:14:14','a86e39fd-e083-4f1e-b678-b047d9c12c16_Chrysanthemum.jpg',0,'Jaipur, Rajasthan, India',NULL,'','Pic with ajax',NULL,NULL,5),(25,'2019-03-08 06:30:14','577bccb3-1cf4-4a0c-a3d9-6e9a9a605809_pervez.jpg',0,'Jaipur, Rajasthan, India',NULL,'am,I_am_trand','first post with image',NULL,NULL,5),(26,'2019-03-08 06:31:01',NULL,0,'Jaipur, Rajasthan, India',NULL,'','second post',NULL,NULL,5),(27,'2019-03-08 06:31:32','86e4e6f2-9bea-4ed8-8d87-df21a713b59b_Chrysanthemum.jpg',0,'Jaipur, Rajasthan, India',NULL,'create new tag','third post',NULL,NULL,5),(28,'2019-03-08 06:31:46',NULL,0,'Jaipur, Rajasthan, India',NULL,'','fourth post',NULL,NULL,5),(29,'2019-03-08 10:23:45',NULL,0,'Jaipur, Rajasthan, India',NULL,'I_am_trand','add new post',NULL,NULL,5),(30,'2019-03-08 10:27:31',NULL,0,'Jaipur, Rajasthan, India',NULL,'','recent post',NULL,NULL,5),(31,'2019-03-08 10:27:46',NULL,0,'Jaipur, Rajasthan, India',NULL,'','add new post',NULL,NULL,5),(32,'2019-03-08 14:18:46',NULL,0,'Jaipur, Rajasthan, India',NULL,'new tag,grate one','hello',NULL,NULL,5),(33,'2019-03-23 05:25:11',NULL,0,'Jaipur, Rajasthan, India',NULL,'','adasdadasdad',NULL,NULL,5),(34,'2019-03-23 05:26:44',NULL,0,'Jaipur, Rajasthan, India',NULL,'','gdfgdgdgdf',NULL,NULL,5),(35,'2019-03-23 05:26:54',NULL,1,'Jaipur, Rajasthan, India',32,'','gdfg',NULL,NULL,5),(36,'2019-03-23 06:06:57',NULL,1,'Jaipur, Rajasthan, India',33,'','hsdjhfue',NULL,NULL,5),(37,'2019-03-23 06:14:17',NULL,0,'Jaipur, Rajasthan, India',NULL,'ab','ahdba',NULL,NULL,5),(38,'2019-03-23 11:16:00',NULL,1,'Jaipur, Rajasthan, India',31,'','sdfsdfsdf',NULL,NULL,5),(39,'2019-03-23 11:34:51',NULL,1,'Jaipur, Rajasthan, India',31,'','dfgdfg',NULL,NULL,5),(40,'2019-03-23 11:35:26',NULL,1,'Jaipur, Rajasthan, India',31,'','sadasdd',NULL,NULL,5),(41,'2019-03-23 11:36:11',NULL,1,'Jaipur, Rajasthan, India',31,'','adsadasd',NULL,NULL,5),(42,'2019-03-23 11:38:14',NULL,1,'Jaipur, Rajasthan, India',31,'','asdasdad',NULL,NULL,5),(43,'2019-04-18 09:40:29',NULL,0,'',NULL,'','hello workd',NULL,NULL,5),(44,'2019-04-19 12:29:47',NULL,0,'',NULL,'','asdasdasd',NULL,NULL,5),(45,'2019-04-19 12:39:23',NULL,0,'',NULL,'','sdsfdfds',NULL,NULL,5),(46,'2019-04-22 05:57:22',NULL,1,'',NULL,'','my annswer for 45',NULL,NULL,5),(47,'2019-04-22 05:59:27',NULL,1,'',NULL,'','my answer for 45 two',NULL,NULL,5),(48,'2019-04-22 06:01:43',NULL,1,'',NULL,'','sdsfsdfsdfsdf',NULL,NULL,5),(49,'2019-04-22 06:07:51',NULL,1,'',45,'','My answer for 45 three',NULL,NULL,5),(50,'2019-04-24 04:32:28',NULL,0,'',NULL,'test1,test2','Jon is quoted in today‘s Times paper ‚Digital Economy‘ supplement on How AI Will change buyer behavious onine\r\n\r\n',NULL,NULL,5),(51,'2019-04-24 06:40:33','a4f11221-f285-49de-a01d-267f119fd125_1.png',0,'',NULL,'tag1,tage 2 ','Jon is quoted in today‘s Times paper ‚Digital Economy‘ supplement on How AI Will change buyer behavious onine\r\n\r\n',NULL,NULL,5),(52,'2019-04-27 09:29:00',NULL,0,'Rajasthan, India',NULL,'','New Post with location',NULL,NULL,5),(53,'2019-04-27 09:36:17',NULL,0,'Rajasthan, India',NULL,'','new Post',NULL,NULL,5),(54,'2019-04-27 09:36:31',NULL,0,'Rajasthan, India',NULL,'','new post 3',NULL,NULL,5),(55,'2019-04-27 10:16:30',NULL,1,'Rajasthan, India',53,'','new post answer',NULL,NULL,5),(56,'2019-04-27 10:18:25',NULL,1,'Rajasthan, India',53,'','new post answer 2',NULL,NULL,5),(57,'2019-04-27 10:18:56',NULL,1,'Rajasthan, India',53,'','new post answer 3',NULL,NULL,5),(58,'2019-04-27 11:23:23',NULL,1,'Rajasthan, India',53,'','asdasdadsfsdfsdsfdfsdfs',NULL,NULL,5),(59,'2019-04-27 11:32:26','543934b7-77f6-460d-80c3-bdfd892487f4_Chrysanthemum.jpg',0,'Rajasthan, India',NULL,'','This is test ',NULL,NULL,5),(60,'2019-04-27 11:33:05',NULL,1,'Rajasthan, India',59,'','hhhyytt',NULL,NULL,5),(61,'2019-04-27 13:21:10','b1798483-ac03-454c-93d8-434eae8ccc0f_pervez.jpg',0,'London, ON, Canada',NULL,'tags,fellow','Comment from debate page',NULL,NULL,5),(62,'2019-04-27 13:22:43','1dd090d4-5d99-4d9b-b7d9-e985212e3dcc_Chrysanthemum.jpg',1,'Kerala, India',61,'grap,drop','My Answer from debate page',NULL,NULL,5),(63,'2019-04-29 09:31:17',NULL,0,'Rajasthan, India',NULL,'tag1,tag2','Hello How are you?',NULL,NULL,5),(64,'2019-04-29 09:31:41',NULL,1,'',63,'','test1 for testing things',NULL,NULL,5),(65,'2019-04-29 09:31:54',NULL,1,'',63,'','testing comments',NULL,NULL,5),(66,'2019-04-29 09:32:12','dc2aaf6f-06bb-49d8-9074-1a78bbbbafec_1.png',1,'',63,'','test image',NULL,NULL,5),(67,'2019-04-29 09:35:08','0d407718-8a8b-487f-b5d3-04cd3b19728f_Hydrangeas.jpg',0,'',NULL,'test1,test2','ghhfghfghgf gfg',NULL,NULL,5),(68,'2019-04-29 09:45:41',NULL,1,'',67,'','dsdsad',NULL,NULL,5),(69,'2019-04-29 10:37:24','a82c65d8-8bde-4b65-b99d-12607a8a9793_gazebo-3-150x150.jpg',0,'Rajasthan, India',NULL,'tag','Lorem ipsum, or lipsum as it is sometimes known, is dummy text used in laying out print, graphic or web designs. The passage is attribut',NULL,NULL,5),(70,'2019-04-29 10:37:36','4c02fabd-10e4-4c69-b9d0-a5a6f1967a41_IMG-20190423-WA0002.jpg',0,'Rajasthan, India',NULL,'t1,t2','hi i am posting this through mobile app yo check the mobile side functionality',NULL,NULL,5),(71,'2019-04-29 11:27:33','cac23000-648c-43ab-ac04-9613a13e458b_Penguins.jpg',0,'Rajahmundry, Andhra Pradesh, India',NULL,'P1,p2','Testing all the updates Testing all the updates Testing all the updates Testing all the updates Testing all the updates',NULL,NULL,5),(72,'2019-04-29 11:31:07','d4717215-0abe-441c-9605-71f30042739b_IMG-20190423-WA0002.jpg',0,'',NULL,'m1,m2','testing through mobiletesting through mobiletesting through mobile',NULL,NULL,5),(73,'2019-05-04 10:45:17',NULL,0,'Jaipur, Rajasthan, India',NULL,'','Hello',NULL,NULL,5),(74,'2019-05-04 10:46:00',NULL,1,'Jaipur, Rajasthan, India',73,'','hello answer',NULL,NULL,5),(75,'2019-05-04 12:16:50',NULL,0,'',NULL,'','From mobile',NULL,NULL,5),(76,'2019-05-04 12:20:01',NULL,0,'Jaipur, Rajasthan, India',NULL,'Hell,hello','From mobile with image',NULL,NULL,5),(77,'2019-05-04 12:24:35','dce8ae6b-3e9c-409f-aa83-df358aeff718_15569727734303579956901710385333.jpg',0,'Jaipur, Rajasthan, India',NULL,'','Mobile with image1',NULL,NULL,5);
/*!40000 ALTER TABLE `opinion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinion_theme`
--

DROP TABLE IF EXISTS `opinion_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinion_theme` (
  `opinion_id` bigint(20) NOT NULL,
  `theme_id` bigint(20) NOT NULL,
  KEY `FKeuqjkd3vdowq1a2dekl8y5cfu` (`theme_id`),
  KEY `FKrllp2cfjix88oopfa6drlxetj` (`opinion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinion_theme`
--

LOCK TABLES `opinion_theme` WRITE;
/*!40000 ALTER TABLE `opinion_theme` DISABLE KEYS */;
INSERT INTO `opinion_theme` VALUES (1,1),(2,1),(3,1),(4,2),(5,1),(6,1),(7,1),(8,1),(9,8),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(45,5),(46,2),(47,2),(48,2),(49,2),(50,1),(51,1),(53,1),(55,1),(56,1),(57,1),(58,1),(59,1),(60,1),(61,4),(62,11),(63,3),(64,1),(65,1),(66,1),(67,2),(68,1),(69,1),(70,1),(71,1),(72,1),(73,1),(74,1),(75,1),(76,1),(77,1);
/*!40000 ALTER TABLE `opinion_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinion_topic`
--

DROP TABLE IF EXISTS `opinion_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinion_topic` (
  `opinion_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  KEY `FKhhu5lknjh6cxgbmp84eykvvqk` (`topic_id`),
  KEY `FK2f2py2rmq2rwsxlvrtbq99o0b` (`opinion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinion_topic`
--

LOCK TABLES `opinion_topic` WRITE;
/*!40000 ALTER TABLE `opinion_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `opinion_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinionchoice`
--

DROP TABLE IF EXISTS `opinionchoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinionchoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` bigint(20) NOT NULL,
  `choice_id` bigint(20) DEFAULT NULL,
  `opinionEvaluacion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4a2p48mvwaw0l22xkm4d6ni8i` (`choice_id`),
  KEY `FKtju2m4o0ejk71np5q3n3wiyc7` (`opinionEvaluacion_id`)
) ENGINE=MyISAM AUTO_INCREMENT=463 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinionchoice`
--

LOCK TABLES `opinionchoice` WRITE;
/*!40000 ALTER TABLE `opinionchoice` DISABLE KEYS */;
INSERT INTO `opinionchoice` VALUES (1,1,9,1),(2,1,11,1),(3,0,12,1),(4,2,1,2),(5,0,2,2),(6,0,3,2),(7,0,9,3),(8,1,11,3),(9,1,12,3),(10,0,1,4),(11,1,2,4),(12,0,3,4),(13,0,9,5),(14,0,11,5),(15,1,12,5),(16,0,1,6),(17,0,2,6),(18,1,3,6),(19,0,9,7),(20,0,11,7),(21,0,12,7),(22,0,1,8),(23,0,2,8),(24,0,3,8),(25,1,9,9),(26,1,11,9),(27,1,12,9),(28,1,1,10),(29,0,2,10),(30,0,3,10),(31,0,9,11),(32,0,11,11),(33,0,12,11),(34,0,1,12),(35,0,2,12),(36,0,3,12),(37,0,9,13),(38,1,11,13),(39,0,12,13),(40,0,1,14),(41,0,2,14),(42,1,3,14),(43,0,9,15),(44,2,11,15),(45,1,12,15),(46,0,1,16),(47,0,2,16),(48,1,3,16),(49,0,9,17),(50,0,11,17),(51,0,12,17),(52,0,1,18),(53,0,2,18),(54,0,3,18),(55,0,9,19),(56,0,11,19),(57,0,12,19),(58,0,1,20),(59,0,2,20),(60,0,3,20),(61,0,9,21),(62,0,11,21),(63,0,12,21),(64,0,1,22),(65,0,2,22),(66,0,3,22),(67,0,9,23),(68,0,11,23),(69,0,12,23),(70,0,1,24),(71,0,2,24),(72,0,3,24),(73,0,9,25),(74,0,11,25),(75,0,12,25),(76,0,1,26),(77,0,2,26),(78,0,3,26),(79,0,9,27),(80,0,11,27),(81,0,12,27),(82,0,1,28),(83,0,2,28),(84,0,3,28),(85,0,9,29),(86,0,11,29),(87,0,12,29),(88,0,1,30),(89,0,2,30),(90,0,3,30),(91,0,9,31),(92,0,11,31),(93,0,12,31),(94,0,1,32),(95,0,2,32),(96,0,3,32),(97,0,9,33),(98,0,11,33),(99,0,12,33),(100,0,1,34),(101,0,2,34),(102,0,3,34),(103,0,9,35),(104,0,11,35),(105,0,12,35),(106,0,1,36),(107,0,2,36),(108,0,3,36),(109,0,9,37),(110,0,11,37),(111,0,12,37),(112,0,1,38),(113,0,2,38),(114,0,3,38),(115,0,9,39),(116,1,11,39),(117,0,12,39),(118,0,1,40),(119,0,2,40),(120,0,3,40),(121,0,9,41),(122,0,11,41),(123,0,12,41),(124,0,1,42),(125,0,2,42),(126,0,3,42),(127,0,9,43),(128,0,11,43),(129,0,12,43),(130,0,1,44),(131,0,2,44),(132,0,3,44),(133,0,9,45),(134,0,11,45),(135,0,12,45),(136,0,1,46),(137,0,2,46),(138,0,3,46),(139,0,9,47),(140,0,11,47),(141,0,12,47),(142,0,1,48),(143,0,2,48),(144,0,3,48),(145,0,9,49),(146,0,11,49),(147,0,12,49),(148,0,1,50),(149,0,2,50),(150,0,3,50),(151,0,9,51),(152,0,11,51),(153,0,12,51),(154,0,1,52),(155,0,2,52),(156,0,3,52),(157,0,9,53),(158,0,11,53),(159,0,12,53),(160,0,1,54),(161,0,2,54),(162,0,3,54),(163,0,9,55),(164,0,11,55),(165,0,12,55),(166,0,1,56),(167,0,2,56),(168,0,3,56),(169,0,9,57),(170,0,11,57),(171,0,12,57),(172,0,1,58),(173,0,2,58),(174,0,3,58),(175,0,9,59),(176,0,11,59),(177,0,12,59),(178,0,1,60),(179,0,2,60),(180,0,3,60),(181,0,9,61),(182,0,11,61),(183,0,12,61),(184,0,1,62),(185,0,2,62),(186,0,3,62),(187,0,9,63),(188,0,11,63),(189,0,12,63),(190,0,1,64),(191,0,2,64),(192,0,3,64),(193,0,9,65),(194,0,11,65),(195,0,12,65),(196,0,1,66),(197,0,2,66),(198,0,3,66),(199,0,9,67),(200,0,11,67),(201,0,12,67),(202,0,1,68),(203,0,2,68),(204,0,3,68),(205,0,9,69),(206,1,11,69),(207,0,12,69),(208,0,1,70),(209,0,2,70),(210,0,3,70),(211,0,9,71),(212,0,11,71),(213,0,12,71),(214,0,1,72),(215,0,2,72),(216,0,3,72),(217,0,9,73),(218,0,11,73),(219,0,12,73),(220,0,1,74),(221,0,2,74),(222,0,3,74),(223,0,9,75),(224,0,11,75),(225,0,12,75),(226,0,1,76),(227,0,2,76),(228,0,3,76),(229,0,9,77),(230,0,11,77),(231,0,12,77),(232,0,1,78),(233,0,2,78),(234,0,3,78),(235,0,9,79),(236,0,11,79),(237,0,12,79),(238,0,1,80),(239,0,2,80),(240,0,3,80),(241,0,9,81),(242,0,11,81),(243,0,12,81),(244,0,1,82),(245,0,2,82),(246,0,3,82),(247,1,9,83),(248,1,11,83),(249,1,12,83),(250,0,1,84),(251,0,2,84),(252,0,3,84),(253,0,9,85),(254,0,11,85),(255,0,12,85),(256,0,1,86),(257,0,2,86),(258,0,3,86),(259,0,9,87),(260,0,11,87),(261,0,12,87),(262,0,1,88),(263,0,2,88),(264,0,3,88),(265,0,9,89),(266,0,11,89),(267,0,12,89),(268,0,1,90),(269,0,2,90),(270,0,3,90),(271,0,9,91),(272,0,11,91),(273,0,12,91),(274,0,1,92),(275,0,2,92),(276,0,3,92),(277,0,9,93),(278,0,11,93),(279,0,12,93),(280,0,1,94),(281,0,2,94),(282,0,3,94),(283,0,9,95),(284,0,11,95),(285,0,12,95),(286,0,1,96),(287,0,2,96),(288,0,3,96),(289,0,9,97),(290,0,11,97),(291,0,12,97),(292,0,1,98),(293,0,2,98),(294,0,3,98),(295,0,9,99),(296,0,11,99),(297,0,12,99),(298,0,1,100),(299,0,2,100),(300,0,3,100),(301,0,9,101),(302,0,11,101),(303,0,12,101),(304,1,1,102),(305,0,2,102),(306,0,3,102),(307,0,9,103),(308,0,11,103),(309,0,12,103),(310,0,1,104),(311,0,2,104),(312,0,3,104),(313,0,9,105),(314,0,11,105),(315,0,12,105),(316,0,1,106),(317,0,2,106),(318,0,3,106),(319,0,9,107),(320,0,11,107),(321,0,12,107),(322,0,1,108),(323,0,2,108),(324,0,3,108),(325,0,9,109),(326,0,11,109),(327,0,12,109),(328,0,1,110),(329,0,2,110),(330,0,3,110),(331,0,9,111),(332,0,11,111),(333,0,12,111),(334,0,1,112),(335,0,2,112),(336,0,3,112),(337,0,9,113),(338,0,11,113),(339,0,12,113),(340,0,1,114),(341,0,2,114),(342,0,3,114),(343,0,9,115),(344,0,11,115),(345,0,12,115),(346,0,1,116),(347,0,2,116),(348,0,3,116),(349,0,9,117),(350,0,11,117),(351,0,12,117),(352,0,1,118),(353,0,2,118),(354,0,3,118),(355,0,9,119),(356,0,11,119),(357,0,12,119),(358,0,1,120),(359,0,2,120),(360,0,3,120),(361,0,9,121),(362,0,11,121),(363,0,12,121),(364,0,1,122),(365,0,2,122),(366,0,3,122),(367,0,9,123),(368,0,11,123),(369,0,12,123),(370,0,1,124),(371,0,2,124),(372,0,3,124),(373,0,9,125),(374,0,11,125),(375,0,12,125),(376,1,1,126),(377,0,2,126),(378,0,3,126),(379,0,9,127),(380,0,11,127),(381,0,12,127),(382,0,1,128),(383,0,2,128),(384,0,3,128),(385,0,9,129),(386,0,11,129),(387,0,12,129),(388,0,1,130),(389,0,2,130),(390,0,3,130),(391,1,9,131),(392,0,11,131),(393,0,12,131),(394,0,1,132),(395,0,2,132),(396,0,3,132),(397,0,9,133),(398,0,11,133),(399,0,12,133),(400,0,1,134),(401,0,2,134),(402,0,3,134),(403,0,9,135),(404,0,11,135),(405,0,12,135),(406,0,1,136),(407,0,2,136),(408,0,3,136),(409,0,9,137),(410,0,11,137),(411,0,12,137),(412,0,1,138),(413,0,2,138),(414,0,3,138),(415,0,9,139),(416,0,11,139),(417,0,12,139),(418,0,1,140),(419,0,2,140),(420,0,3,140),(421,0,9,141),(422,0,11,141),(423,0,12,141),(424,0,1,142),(425,0,2,142),(426,0,3,142),(427,0,9,143),(428,0,11,143),(429,0,12,143),(430,0,1,144),(431,0,2,144),(432,0,3,144),(433,0,9,145),(434,0,11,145),(435,0,12,145),(436,0,1,146),(437,0,2,146),(438,0,3,146),(439,0,9,147),(440,0,11,147),(441,0,12,147),(442,0,1,148),(443,0,2,148),(444,0,3,148),(445,0,9,149),(446,0,11,149),(447,0,12,149),(448,0,1,150),(449,0,2,150),(450,0,3,150),(451,0,9,151),(452,0,11,151),(453,0,12,151),(454,0,1,152),(455,0,2,152),(456,0,3,152),(457,0,9,153),(458,0,11,153),(459,0,12,153),(460,0,1,154),(461,0,2,154),(462,0,3,154);
/*!40000 ALTER TABLE `opinionchoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinionevaluacion`
--

DROP TABLE IF EXISTS `opinionevaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinionevaluacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipoEvaluacion_id` bigint(20) DEFAULT NULL,
  `opinion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh21n6gsk4vmybksxoq96rwyy7` (`opinion_id`),
  KEY `FKngxc2tb0nfohxbus28vnx2qd3` (`tipoEvaluacion_id`)
) ENGINE=MyISAM AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinionevaluacion`
--

LOCK TABLES `opinionevaluacion` WRITE;
/*!40000 ALTER TABLE `opinionevaluacion` DISABLE KEYS */;
INSERT INTO `opinionevaluacion` VALUES (1,4,1),(2,1,1),(3,4,2),(4,1,2),(5,4,3),(6,1,3),(7,4,4),(8,1,4),(9,4,5),(10,1,5),(11,4,6),(12,1,6),(13,4,7),(14,1,7),(15,4,8),(16,1,8),(17,4,9),(18,1,9),(19,4,10),(20,1,10),(21,4,11),(22,1,11),(23,4,12),(24,1,12),(25,4,13),(26,1,13),(27,4,14),(28,1,14),(29,4,15),(30,1,15),(31,4,16),(32,1,16),(33,4,17),(34,1,17),(35,4,18),(36,1,18),(37,4,19),(38,1,19),(39,4,20),(40,1,20),(41,4,21),(42,1,21),(43,4,22),(44,1,22),(45,4,23),(46,1,23),(47,4,24),(48,1,24),(49,4,25),(50,1,25),(51,4,26),(52,1,26),(53,4,27),(54,1,27),(55,4,28),(56,1,28),(57,4,29),(58,1,29),(59,4,30),(60,1,30),(61,4,31),(62,1,31),(63,4,32),(64,1,32),(65,4,33),(66,1,33),(67,4,34),(68,1,34),(69,4,35),(70,1,35),(71,4,36),(72,1,36),(73,4,37),(74,1,37),(75,4,38),(76,1,38),(77,4,39),(78,1,39),(79,4,40),(80,1,40),(81,4,41),(82,1,41),(83,4,42),(84,1,42),(85,4,43),(86,1,43),(87,4,44),(88,1,44),(89,4,45),(90,1,45),(91,4,46),(92,1,46),(93,4,47),(94,1,47),(95,4,48),(96,1,48),(97,4,49),(98,1,49),(99,4,50),(100,1,50),(101,4,51),(102,1,51),(103,4,52),(104,1,52),(105,4,53),(106,1,53),(107,4,54),(108,1,54),(109,4,55),(110,1,55),(111,4,56),(112,1,56),(113,4,57),(114,1,57),(115,4,58),(116,1,58),(117,4,59),(118,1,59),(119,4,60),(120,1,60),(121,4,61),(122,1,61),(123,4,62),(124,1,62),(125,4,63),(126,1,63),(127,4,64),(128,1,64),(129,4,65),(130,1,65),(131,4,66),(132,1,66),(133,4,67),(134,1,67),(135,4,68),(136,1,68),(137,4,69),(138,1,69),(139,4,70),(140,1,70),(141,4,71),(142,1,71),(143,4,72),(144,1,72),(145,4,73),(146,1,73),(147,4,74),(148,1,74),(149,4,75),(150,1,75),(151,4,76),(152,1,76),(153,4,77),(154,1,77);
/*!40000 ALTER TABLE `opinionevaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `continent` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=240 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'TMP','Asia','East Timor'),(2,'PSE','Asia','Palestine'),(3,'ATA','Antarctica','Antarctica'),(4,'MNP','Oceania','Northern Mariana Islands'),(5,'PRI','North America','Puerto Rico'),(6,'ATG','North America','Antigua and Barbuda'),(7,'BHS','North America','Bahamas'),(8,'BLZ','North America','Belize'),(9,'BRB','North America','Barbados'),(10,'DNK','Europe','Denmark'),(11,'ESP','Europe','Spain'),(12,'GBR','Europe','United Kingdom'),(13,'GRD','North America','Grenada'),(14,'JAM','North America','Jamaica'),(15,'JOR','Asia','Jordan'),(16,'JPN','Asia','Japan'),(17,'KHM','Asia','Cambodia'),(18,'KNA','North America','Saint Kitts and Nevis'),(19,'LCA','North America','Saint Lucia'),(20,'LIE','Europe','Liechtenstein'),(21,'LSO','Africa','Lesotho'),(22,'LUX','Europe','Luxembourg'),(23,'MAR','Africa','Morocco'),(24,'MCO','Europe','Monaco'),(25,'NLD','Europe','Netherlands'),(26,'NOR','Europe','Norway'),(27,'NPL','Asia','Nepal'),(28,'NZL','Oceania','New Zealand'),(29,'PNG','Oceania','Papua New Guinea'),(30,'SLB','Oceania','Solomon Islands'),(31,'SWE','Europe','Sweden'),(32,'THA','Asia','Thailand'),(33,'TUV','Oceania','Tuvalu'),(34,'VCT','North America','Saint Vincent and the Grenadines'),(35,'KWT','Asia','Kuwait'),(36,'AUS','Oceania','Australia'),(37,'BEL','Europe','Belgium'),(38,'CAN','North America','Canada'),(39,'MYS','Asia','Malaysia'),(40,'BVT','Antarctica','Bouvet Island'),(41,'SJM','Europe','Svalbard and Jan Mayen'),(42,'AIA','North America','Anguilla'),(43,'BMU','North America','Bermuda'),(44,'CYM','North America','Cayman Islands'),(45,'FLK','South America','Falkland Islands'),(46,'GIB','Europe','Gibraltar'),(47,'IOT','Africa','British Indian Ocean Territory'),(48,'MSR','North America','Montserrat'),(49,'PCN','Oceania','Pitcairn'),(50,'SGS','Antarctica','South Georgia and the South Sandwich Islands'),(51,'SHN','Africa','Saint Helena'),(52,'TCA','North America','Turks and Caicos Islands'),(53,'VGB','North America','Virgin Islands, British'),(54,'UMI','Oceania','United States Minor Outlying Islands'),(55,'ARE','Asia','United Arab Emirates'),(56,'ARG','South America','Argentina'),(57,'AUT','Europe','Austria'),(58,'AZE','Asia','Azerbaijan'),(59,'BIH','Europe','Bosnia and Herzegovina'),(60,'BRA','South America','Brazil'),(61,'DEU','Europe','Germany'),(62,'FSM','Oceania','Micronesia, Federated States of'),(63,'IND','Asia','India'),(64,'MDG','Africa','Madagascar'),(65,'MEX','North America','Mexico'),(66,'NGA','Africa','Nigeria'),(67,'RUS','Europe','Russian Federation'),(68,'USA','North America','United States'),(69,'VEN','South America','Venezuela'),(70,'YUG','Europe','Yugoslavia'),(71,'CHE','Europe','Switzerland'),(72,'VAT','Europe','Holy See (Vatican City State)'),(73,'AFG','Asia','Afghanistan'),(74,'IRN','Asia','Iran'),(75,'SDN','Africa','Sudan'),(76,'BTN','Asia','Bhutan'),(77,'QAT','Asia','Qatar'),(78,'SAU','Asia','Saudi Arabia'),(79,'SWZ','Africa','Swaziland'),(80,'TON','Oceania','Tonga'),(81,'BHR','Asia','Bahrain'),(82,'BRN','Asia','Brunei'),(83,'OMN','Asia','Oman'),(84,'ATF','Antarctica','French Southern territories'),(85,'NCL','Oceania','New Caledonia'),(86,'PYF','Oceania','French Polynesia'),(87,'WLF','Oceania','Wallis and Futuna'),(88,'COK','Oceania','Cook Islands'),(89,'NIU','Oceania','Niue'),(90,'TKL','Oceania','Tokelau'),(91,'ABW','North America','Aruba'),(92,'ANT','North America','Netherlands Antilles'),(93,'ESH','Africa','Western Sahara'),(94,'GLP','North America','Guadeloupe'),(95,'GUF','South America','French Guiana'),(96,'MTQ','North America','Martinique'),(97,'REU','Africa','RÃ©union'),(98,'WSM','Oceania','Samoa'),(99,'AND','Europe','Andorra'),(100,'FRO','Europe','Faroe Islands'),(101,'GRL','North America','Greenland'),(102,'CHN','Asia','China'),(103,'AGO','Africa','Angola'),(104,'ALB','Europe','Albania'),(105,'ARM','Asia','Armenia'),(106,'BDI','Africa','Burundi'),(107,'BEN','Africa','Benin'),(108,'BFA','Africa','Burkina Faso'),(109,'BGD','Asia','Bangladesh'),(110,'BGR','Europe','Bulgaria'),(111,'BLR','Europe','Belarus'),(112,'BOL','South America','Bolivia'),(113,'BWA','Africa','Botswana'),(114,'CAF','Africa','Central African Republic'),(115,'CHL','South America','Chile'),(116,'CIV','Africa','CÃ´te dÂ’Ivoire'),(117,'CMR','Africa','Cameroon'),(118,'COD','Africa','Congo, The Democratic Republic of the'),(119,'COG','Africa','Congo'),(120,'COL','South America','Colombia'),(121,'COM','Africa','Comoros'),(122,'CPV','Africa','Cape Verde'),(123,'CRI','North America','Costa Rica'),(124,'CYP','Asia','Cyprus'),(125,'CZE','Europe','Czech Republic'),(126,'DJI','Africa','Djibouti'),(127,'DMA','North America','Dominica'),(128,'DOM','North America','Dominican Republic'),(129,'DZA','Africa','Algeria'),(130,'ECU','South America','Ecuador'),(131,'EGY','Africa','Egypt'),(132,'ERI','Africa','Eritrea'),(133,'EST','Europe','Estonia'),(134,'ETH','Africa','Ethiopia'),(135,'FIN','Europe','Finland'),(136,'FJI','Oceania','Fiji Islands'),(137,'FRA','Europe','France'),(138,'GAB','Africa','Gabon'),(139,'GEO','Asia','Georgia'),(140,'GHA','Africa','Ghana'),(141,'GIN','Africa','Guinea'),(142,'GMB','Africa','Gambia'),(143,'GNB','Africa','Guinea-Bissau'),(144,'GNQ','Africa','Equatorial Guinea'),(145,'GRC','Europe','Greece'),(146,'GTM','North America','Guatemala'),(147,'GUY','South America','Guyana'),(148,'HND','North America','Honduras'),(149,'HRV','Europe','Croatia'),(150,'HTI','North America','Haiti'),(151,'HUN','Europe','Hungary'),(152,'IDN','Asia','Indonesia'),(153,'IRL','Europe','Ireland'),(154,'IRQ','Asia','Iraq'),(155,'ISL','Europe','Iceland'),(156,'ISR','Asia','Israel'),(157,'ITA','Europe','Italy'),(158,'KAZ','Asia','Kazakstan'),(159,'KEN','Africa','Kenya'),(160,'KGZ','Asia','Kyrgyzstan'),(161,'KIR','Oceania','Kiribati'),(162,'KOR','Asia','South Korea'),(163,'LAO','Asia','Laos'),(164,'LBN','Asia','Lebanon'),(165,'LBR','Africa','Liberia'),(166,'LKA','Asia','Sri Lanka'),(167,'LTU','Europe','Lithuania'),(168,'LVA','Europe','Latvia'),(169,'MDA','Europe','Moldova'),(170,'MDV','Asia','Maldives'),(171,'MHL','Oceania','Marshall Islands'),(172,'MKD','Europe','Macedonia'),(173,'MLI','Africa','Mali'),(174,'MLT','Europe','Malta'),(175,'MMR','Asia','Myanmar'),(176,'MNG','Asia','Mongolia'),(177,'MOZ','Africa','Mozambique'),(178,'MRT','Africa','Mauritania'),(179,'MUS','Africa','Mauritius'),(180,'MWI','Africa','Malawi'),(181,'NAM','Africa','Namibia'),(182,'NER','Africa','Niger'),(183,'NIC','North America','Nicaragua'),(184,'NRU','Oceania','Nauru'),(185,'PAK','Asia','Pakistan'),(186,'PAN','North America','Panama'),(187,'PER','South America','Peru'),(188,'PHL','Asia','Philippines'),(189,'PLW','Oceania','Palau'),(190,'POL','Europe','Poland'),(191,'PRT','Europe','Portugal'),(192,'PRY','South America','Paraguay'),(193,'ROM','Europe','Romania'),(194,'RWA','Africa','Rwanda'),(195,'SEN','Africa','Senegal'),(196,'SGP','Asia','Singapore'),(197,'SLE','Africa','Sierra Leone'),(198,'SLV','North America','El Salvador'),(199,'SMR','Europe','San Marino'),(200,'SOM','Africa','Somalia'),(201,'STP','Africa','Sao Tome and Principe'),(202,'SUR','South America','Suriname'),(203,'SVK','Europe','Slovakia'),(204,'SVN','Europe','Slovenia'),(205,'SYC','Africa','Seychelles'),(206,'SYR','Asia','Syria'),(207,'TCD','Africa','Chad'),(208,'TGO','Africa','Togo'),(209,'TJK','Asia','Tajikistan'),(210,'TKM','Asia','Turkmenistan'),(211,'TTO','North America','Trinidad and Tobago'),(212,'TUN','Africa','Tunisia'),(213,'TUR','Asia','Turkey'),(214,'TWN','Asia','Taiwan'),(215,'TZA','Africa','Tanzania'),(216,'UGA','Africa','Uganda'),(217,'UKR','Europe','Ukraine'),(218,'URY','South America','Uruguay'),(219,'UZB','Asia','Uzbekistan'),(220,'VUT','Oceania','Vanuatu'),(221,'YEM','Asia','Yemen'),(222,'ZAF','Africa','South Africa'),(223,'ZMB','Africa','Zambia'),(224,'ZWE','Africa','Zimbabwe'),(225,'CUB','North America','Cuba'),(226,'PRK','Asia','North Korea'),(227,'VNM','Asia','Vietnam'),(228,'LBY','Africa','Libyan Arab Jamahiriya'),(229,'HKG','Asia','Hong Kong'),(230,'MAC','Asia','Macao'),(231,'MYT','Africa','Mayotte'),(232,'SPM','North America','Saint Pierre and Miquelon'),(233,'CCK','Oceania','Cocos (Keeling) Islands'),(234,'CXR','Oceania','Christmas Island'),(235,'HMD','Antarctica','Heard Island and McDonald Islands'),(236,'NFK','Oceania','Norfolk Island'),(237,'ASM','Oceania','American Samoa'),(238,'GUM','Oceania','Guam'),(239,'VIR','North America','Virgin Islands, U.S.');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidopolitico`
--

DROP TABLE IF EXISTS `partidopolitico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partidopolitico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `pais_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf22xf9dupr5ej414hqp5s639s` (`pais_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidopolitico`
--

LOCK TABLES `partidopolitico` WRITE;
/*!40000 ALTER TABLE `partidopolitico` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidopolitico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postulaciones`
--

DROP TABLE IF EXISTS `postulaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postulaciones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cargo_id` bigint(20) DEFAULT NULL,
  `ciudadano_id` bigint(20) DEFAULT NULL,
  `pais_id` bigint(20) DEFAULT NULL,
  `partidoPolitico_id` bigint(20) DEFAULT NULL,
  `eleccion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKigxy8aia2can166y5e6sqvose` (`cargo_id`),
  KEY `FKgj2gq74e6y6aqece7gnrb0vvm` (`ciudadano_id`),
  KEY `FKpjv50cb2j18dq2nkwbv3niqdu` (`pais_id`),
  KEY `FK8voe1beg9utn28p66e2amh7gj` (`partidoPolitico_id`),
  KEY `FKo54li1rbk3bxkb562y7tphm0` (`eleccion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postulaciones`
--

LOCK TABLES `postulaciones` WRITE;
/*!40000 ALTER TABLE `postulaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `postulaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `codigo` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (15,'De Arica y Parinacota'),(1,'De Tarapacá'),(2,'De Antofagasta'),(3,'De Atacama'),(4,'De Coquimbo'),(5,'De Valparaíso'),(6,'Del Libertador B. O Higgins'),(7,'Del Maule'),(8,'Del Bíobío'),(9,'De La Araucanía'),(14,'De Los Ríos'),(10,'De Los Lagos'),(11,'De Aisén del Gral. C. Ibáñez del Campo'),(12,'De Magallanes y de La Antártica Chilena'),(13,'Metropolitana de Santiago');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimiento`
--

DROP TABLE IF EXISTS `seguimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seguimiento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ciudadano_id` bigint(20) DEFAULT NULL,
  `ciudadanoSiguiendo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdyeyrupfpqkk9yvrr5moj2h9w` (`ciudadano_id`),
  KEY `FKeb1t1frbjrc4h4mvop9qqyo2q` (`ciudadanoSiguiendo_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimiento`
--

LOCK TABLES `seguimiento` WRITE;
/*!40000 ALTER TABLE `seguimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags_list`
--

DROP TABLE IF EXISTS `tags_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_svtu1oyfpowly3cnpip84rqn8` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags_list`
--

LOCK TABLES `tags_list` WRITE;
/*!40000 ALTER TABLE `tags_list` DISABLE KEYS */;
INSERT INTO `tags_list` VALUES (1,'hello'),(2,'Create nw tag'),(3,'create new tag'),(4,'create'),(5,'I_am_trand'),(6,'i'),(7,'am'),(8,'new tag'),(9,'grate one'),(10,''),(11,'ab'),(12,'check'),(13,'test1'),(14,'test2'),(15,'tag1'),(16,'tage 2 '),(17,'tags'),(18,'fellow'),(19,'grap'),(20,'drop'),(21,'tag2'),(22,'tag'),(23,'t1'),(24,'t2'),(25,'P1'),(26,'p2'),(27,'m1'),(28,'m2'),(29,'Hell');
/*!40000 ALTER TABLE `tags_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'Politics'),(2,'Economy and Business'),(3,'Personal Growth'),(4,'Financial Freedom'),(5,'Leiusure'),(6,'Science'),(7,'Ethic'),(8,'Society'),(9,'Art'),(10,'Religion'),(11,'Economic system'),(12,'Knowledge');
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoeleccion`
--

DROP TABLE IF EXISTS `tipoeleccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoeleccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoeleccion`
--

LOCK TABLES `tipoeleccion` WRITE;
/*!40000 ALTER TABLE `tipoeleccion` DISABLE KEYS */;
INSERT INTO `tipoeleccion` VALUES (1,'Presidencial'),(2,'Parlamentaria/Asamblea'),(3,'Senatorial'),(4,'De Diputados'),(5,'Alcaldía'),(6,'Otros');
/*!40000 ALTER TABLE `tipoeleccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoevaluacion`
--

DROP TABLE IF EXISTS `tipoevaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoevaluacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `sonTodosXOR` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoevaluacion`
--

LOCK TABLES `tipoevaluacion` WRITE;
/*!40000 ALTER TABLE `tipoevaluacion` DISABLE KEYS */;
INSERT INTO `tipoevaluacion` VALUES (1,'PNI method',''),(2,'negative emotion','\0'),(3,'bad politic','\0'),(4,'attitude','\0');
/*!40000 ALTER TABLE `tipoevaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoidentificacion`
--

DROP TABLE IF EXISTS `tipoidentificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoidentificacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoidentificacion`
--

LOCK TABLES `tipoidentificacion` WRITE;
/*!40000 ALTER TABLE `tipoidentificacion` DISABLE KEYS */;
INSERT INTO `tipoidentificacion` VALUES (1,'número único nacional'),(2,'pasaporte');
/*!40000 ALTER TABLE `tipoidentificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'Art'),(2,'Comics'),(3,'Culture'),(4,'Film'),(5,'Food'),(6,'Gaming'),(7,'Humor'),(8,'Lit'),(9,'Music'),(10,'Photography'),(11,'Social media'),(12,'Sports'),(13,'True crime'),(14,'Writing'),(15,'Industry'),(16,'Business'),(17,'Economy'),(18,'Entrepreneurship'),(19,'Freelancing'),(20,'Leadership'),(21,'Productivity'),(22,'Work'),(23,'Innovation '),(24,'Tech'),(25,'Artificial intelligence'),(26,'Cryptocurrency'),(27,'Cybersecurity'),(28,'Data science'),(29,'Digital design'),(30,'Javascript'),(31,'Math'),(32,'Neuroscience'),(33,'Programming'),(34,'Science'),(35,'Self-driving cars'),(36,'Software engineering'),(37,'Space'),(38,'Technology'),(39,'Life'),(40,'Family'),(41,'Health'),(42,'Mental health'),(43,'Parenting'),(44,'Personal finance'),(45,'Pets'),(46,'Psychedelics'),(47,'Psychology'),(48,'Relationships'),(49,'Self'),(50,'Sexuality'),(51,'Spirituality'),(52,'Travel'),(53,'Wellness'),(54,'Society'),(55,'Basic income'),(56,'Cities'),(57,'Education'),(58,'Environment'),(59,'Equality'),(60,'Future'),(61,'Gun control'),(62,'History'),(63,'LGBTQIA'),(64,'Media'),(65,'Philosophy'),(66,'Politics'),(67,'Race'),(68,'San Francisco'),(69,'Transportation'),(70,'Women'),(71,'World'),(72,'Marketing'),(73,'Creativity'),(74,'Drugs');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userconnection`
--

DROP TABLE IF EXISTS `userconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userconnection` (
  `userId` varchar(255) NOT NULL,
  `accessToken` varchar(512) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `providerId` varchar(255) DEFAULT NULL,
  `providerUserId` varchar(255) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `secret` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userconnection`
--

LOCK TABLES `userconnection` WRITE;
/*!40000 ALTER TABLE `userconnection` DISABLE KEYS */;
INSERT INTO `userconnection` VALUES ('wnmtest0sss@gmail.com','EAAQSApSWDNoBAHjWIp65cmZABZCkaQmSFqGTY54biDgVNCbLGuQpfi6ptO1tPm1f3NvXe3w3CdPcNeEtoBZBlmbXQP0LoxXiuGbIiAqWY3rwjlfDHAO286vsBSk9eYx7a8eDNlF2PLijo45Dx5ZAXF97gGqvOIRkJraVYGqKGAZDZD','Ways Means',1555305510390,'https://graph.facebook.com/v2.5/1214003355433853/picture',NULL,'facebook','1214003355433853',1,NULL,NULL),('wnmtest.apzczxi@gmail.com','AQXt7UCws0UDpelWjlfQU9U0nUntGT0MR6208Tow19q7N7JJ1t-6H1cUSvxh2witU801Vt3_jMOCrdgnfsXao2em0i4RjRNucTy9KxuQylgHbzt9nVzzunacK8uSHSq-ViXNyuRDW3nrb5vwjeh3dSj0L8_VlJYzNNC1x_6bZHvQ-IHk33pvlwD6xPX2xvrOve8tonVf7V3Xyy7ZB9PLMBOXusJn0eBF721CJaIWxKI4q2se0wRyK0LJdTlM8vH88eIOV2KVhkeW6_fzWN5YVYwgRb-nuXt4KR0QAl0Y0TDp1JW0PiK-xW55LHBisxICw5QIIFUkeK_dnNSA21-SPbcl3iXV1Q','Ways Test',1555747716482,'https://media.licdn.com/dms/image/C4E03AQEbUGzELK96Kw/profile-displayphoto-shrink_100_100/0?e=1556150400&v=beta&t=jTio_lYcVtr4SD1lwb3PNh_ronzXWvQZ1Z-rPVVCTYs','http://www.linkedin.com/in/ways-test-6b6a6a168','linkedin','ZPoY2qL-e2',1,NULL,NULL),('scphxpckel_1550636731@tfbnw.net','EAAQSApSWDNoBABMCr31e6ffjr643NIj9GXmCDLIjCeO9dESYIHjuVomkdf8cx5XAkbYEYiXr7RfsruYQGb9YULjJpJPZCZAxh7ADGYrcZAZAZApcRJflKLXc64kkpWHwb1LJZAZAZBXm2p6vvPqzNEKnGEQrZA09bbjUbAZB68iwLzSOhQ5zloDSem','William Alcdaddicaceg Letuchysky',1556016465453,'https://graph.facebook.com/v2.5/104949137319917/picture',NULL,'facebook','104949137319917',1,NULL,NULL),('wnmtest.api@gmail.com','ya29.GlvvBtn14DpWs2JkvmDlM4twhqQbn3GLUr967DPl0Yxy959QWFda0nUWTZ-KV78TqGcjRw45JaQKbAAdoal9n5egzH4RQI3JwBFLWSYQT8GWQiCDBxJm2T_z8-qL','Ways Test',1555575040660,'https://lh5.googleusercontent.com/-cs564iqKM_M/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rcuXJANva25ycMUL17e-FCfT8s4qA/mo/photo.jpg','https://plus.google.com/110767939380734940061','google','110767939380734940061',1,NULL,NULL);
/*!40000 ALTER TABLE `userconnection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(255) DEFAULT NULL,
  `email` varchar(250) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `resetPasswordCode` varchar(36) DEFAULT NULL,
  `verificationCode` varchar(36) DEFAULT NULL,
  `ciudadano_id` bigint(20) DEFAULT NULL,
  `filters` varchar(255) DEFAULT NULL,
  `themeFilters` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_1uax1y4xn5cino4q5sbuk8no0` (`resetPasswordCode`),
  KEY `FK7n0hxwbgnd05nyr0fgnfjj9he` (`ciudadano_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'@Mario','mcuadrai@gmail.com','','Mario','$2a$10$Gg867QHStaNiI0cFOpkQQ.FXfO6bfVcBdvIbZn/MrBJ3.U/Bq7Siu',NULL,NULL,NULL,NULL,NULL),(3,'@Catlos','carlos@gmail.com','','Carlos','$2a$10$XOmWLEiYMz9g2kCgnweCiu0UIeDGJ8WG1IaCG4CJ17/Cui5fRM4.K',NULL,NULL,NULL,NULL,NULL),(4,'@Ways','wnmtestss0@gmail.com','','Ways Means','$2a$10$gaDq3Wrm1sE4hTGNEl.E3OYx/wpWiy1xsXXReGDu2qiCbnpyq1VMm',NULL,'be5a4e27-560d-4079-a96c-adbf8e700f9e',1,NULL,NULL),(5,'@test','test@mailinator.com','','test user','$2a$10$1PDgtUYXxsdtDEYdExGx/uYiqw8332gDUwYqeVV68LVljV5acWFJ2',NULL,NULL,NULL,'',''),(6,'@Waysabc','wnmtest.apidsa@gmail.com','','Ways Test','$2a$10$c.eK2S2eoTW/hIPoYZr/Bes5.fFTP0mh0UzQj10dxAMt2oeeLww3W',NULL,'a908042b-276b-477b-8ef7-2a28e03d658a',2,NULL,NULL),(7,'@William','scphxpckel_1550636731@tfbnw.net','','William Alcdaddicaceg Letuchysky','$2a$10$MoCh0bErpSjQlv3tR1dp7OCLy4z.UyZ54V9i01Qn9Vxhs5cHRtm6G',NULL,'923ec479-538d-44f3-accc-924d4d2e18c3',7,NULL,NULL),(8,'@Ways82e7','wnmtest.apidfgdfgd@gmail.com','','Ways Test','$2a$10$tita6QKtKobECB7CzVtLG.T1R5HGCW.lh1jQCZcg6s8TDjzaRbX/2',NULL,'be853fb0-0590-499b-9572-9cfe20c5e58e',11,NULL,NULL),(9,'@Waysd139','wnmtest.api@gmail.com','','Ways Test','$2a$10$GXMr90gwq0zj8QXPLL8NH.zQikMXse390dU8dzqOYfmvkpZ7.VH7m',NULL,'6243d167-3983-4bc8-b22c-f789bda3699f',13,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallopinion`
--

DROP TABLE IF EXISTS `wallopinion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallopinion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `publishedDate` date NOT NULL,
  `opinion_id` bigint(20) DEFAULT NULL,
  `pais_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhfoxq6spt3p7ewj8qsmhl15c5` (`opinion_id`),
  KEY `FKdmwk9pimlw7yt54xs3l02u6ly` (`pais_id`),
  KEY `FK3478x831rj2bymovbvhgo7ix2` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallopinion`
--

LOCK TABLES `wallopinion` WRITE;
/*!40000 ALTER TABLE `wallopinion` DISABLE KEYS */;
/*!40000 ALTER TABLE `wallopinion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'forumv2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-16 10:21:45
