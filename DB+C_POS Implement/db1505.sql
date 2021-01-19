-- MySQL dump 10.13  Distrib 5.1.73, for redhat-linux-gnu (i386)
--
-- Host: localhost    Database: 1505_db
-- ------------------------------------------------------
-- Server version	5.1.73-log

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
-- Table structure for table `B_Bill`
--

DROP TABLE IF EXISTS `B_Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Bill` (
  `Bill_Date` varchar(8) NOT NULL,
  `Bill_Idx` varchar(8) NOT NULL,
  `Bill_TableID` int(11) DEFAULT NULL,
  `Bill_Cash` double DEFAULT NULL,
  `Bill_Card` double DEFAULT NULL,
  `Bill_Balancedue` double DEFAULT NULL,
  `Bill_Employee` int(11) DEFAULT NULL,
  `Bill_Cust` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Bill`
--

LOCK TABLES `B_Bill` WRITE;
/*!40000 ALTER TABLE `B_Bill` DISABLE KEYS */;
INSERT INTO `B_Bill` VALUES ('20170516','1',3,25000,30000,0,1,6),('20170516','2',4,56500,0,0,3,4),('20170516','3',2,0,39000,0,5,8),('20170517','1',3,40000,16000,0,1,2),('20170517','2',8,41000,0,0,1,1),('20170517','3',5,34000,30000,0,3,5),('20170517','4',6,0,26500,0,5,10),('20170518','1',9,0,43500,0,1,2),('20170518','2',4,30000,22000,0,3,5),('20170518','3',2,30000,53000,0,5,6);
/*!40000 ALTER TABLE `B_Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Bill_Item`
--

DROP TABLE IF EXISTS `B_Bill_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Bill_Item` (
  `Bill_Date` varchar(8) NOT NULL DEFAULT '',
  `Bill_Idx` int(11) NOT NULL DEFAULT '0',
  `Bill_ItemID` int(11) NOT NULL DEFAULT '0',
  `Menu_Type` int(11) DEFAULT NULL,
  `Menu_ID` int(11) DEFAULT NULL,
  `Menu_Price` double DEFAULT NULL,
  `Menu_Count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Bill_Item`
--

LOCK TABLES `B_Bill_Item` WRITE;
/*!40000 ALTER TABLE `B_Bill_Item` DISABLE KEYS */;
INSERT INTO `B_Bill_Item` VALUES ('20170516',1,1,3,2,12500,5),('20170516',2,1,2,2,20000,4),('20170516',3,1,3,1,10000,5),('20170517',1,1,2,2,20000,6),('20170517',2,1,3,2,12500,5),('20170517',3,1,2,2,20000,6),('20170517',4,1,3,1,6000,3),('20170518',1,1,3,3,12500,5),('20170518',2,1,2,2,25000,5),('20170518',3,1,1,1,16000,4),('20170516',1,2,2,1,27500,5),('20170516',2,2,2,1,16500,3),('20170516',3,2,1,2,18000,6),('20170517',1,2,1,1,16000,4),('20170517',2,2,1,2,12000,4),('20170517',3,2,1,2,18000,6),('20170517',4,2,3,3,12500,5),('20170518',1,2,1,2,18000,6),('20170518',2,2,1,1,20000,5),('20170518',3,2,3,3,10000,4),('20170516',1,3,2,3,15000,3),('20170516',2,3,1,1,20000,5),('20170516',3,3,4,2,11000,11),('20170517',1,3,4,1,10000,10),('20170517',2,3,2,1,16500,3),('20170517',3,3,1,1,16000,4),('20170517',4,3,1,1,8000,2),('20170518',1,3,2,3,10000,2),('20170518',2,3,2,1,11000,2),('20170518',3,3,1,2,15000,5);
/*!40000 ALTER TABLE `B_Bill_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Buying`
--

DROP TABLE IF EXISTS `B_Buying`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Buying` (
  `Buying_Date` varchar(8) NOT NULL DEFAULT '',
  `Trader_ID` int(11) NOT NULL DEFAULT '0',
  `Buying_ID` int(11) NOT NULL DEFAULT '0',
  `Em_ID` int(11) DEFAULT NULL,
  `Foods_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Buying_Price` double DEFAULT NULL,
  `Buying_Count` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Buying`
--

LOCK TABLES `B_Buying` WRITE;
/*!40000 ALTER TABLE `B_Buying` DISABLE KEYS */;
INSERT INTO `B_Buying` VALUES ('20170515',1,1,7,1,1,1,30000,30),('20170515',1,2,7,2,1,1,30000,30),('20170515',1,3,7,3,1,1,3000,30),('20170515',1,4,7,4,1,2,2100,30),('20170515',1,5,7,5,1,2,2100,30),('20170515',1,6,7,6,1,2,2100,30),('20170515',1,7,7,7,1,2,2100,30),('20170515',2,8,7,8,2,1,1500,30),('20170515',2,9,7,9,2,1,900,30),('20170515',2,10,7,10,2,1,900,30),('20170515',2,11,7,11,2,1,6000,30),('20170515',2,12,7,12,2,1,900,30),('20170515',2,13,7,13,2,2,15000,30),('20170515',2,14,7,14,2,2,15000,30),('20170515',2,15,7,15,2,2,15000,30),('20170515',2,16,7,16,2,2,15000,30),('20170515',2,17,7,17,2,2,3000,30),('20170515',2,18,7,18,2,2,15000,30),('20170515',2,19,7,19,2,2,6000,30),('20170515',2,20,7,20,2,2,1500,30),('20170515',2,21,7,21,2,2,6000,30),('20170515',3,22,7,22,2,3,15000,30),('20170515',3,23,7,23,2,3,15000,30),('20170515',3,24,7,24,2,3,15000,30),('20170517',1,1,8,1,1,1,2940,3),('20170517',1,2,8,2,1,1,11110,11),('20170517',1,3,8,3,1,1,3330,37),('20170517',1,4,8,4,1,2,2080,32),('20170517',1,5,8,5,1,2,1820,26),('20170517',1,6,8,6,1,2,1725,23),('20170517',1,7,8,7,1,2,375,5),('20170517',2,8,8,8,1,2,1395,31),('20170517',2,9,8,9,2,1,1175,47),('20170517',2,10,8,10,2,1,375,15),('20170517',2,11,8,12,2,1,665,19),('20170517',2,12,8,13,2,2,7650,15),('20170517',2,13,8,14,2,2,7500,15),('20170517',2,14,8,15,2,2,7840,16),('20170517',2,15,8,16,2,2,13260,26),('20170517',2,16,8,17,2,2,990,11),('20170517',2,17,8,18,2,2,7840,16),('20170517',2,18,8,19,2,2,4600,23),('20170517',2,19,8,20,2,2,1265,23),('20170517',2,20,8,21,2,2,1800,10),('20170517',3,21,8,22,2,3,5000,10),('20170517',3,22,8,23,2,3,5500,11),('20170518',1,1,9,1,1,1,11880,12),('20170518',1,2,9,2,1,1,13260,13),('20170518',1,3,9,3,1,1,4200,42),('20170518',1,4,9,4,1,2,1680,24),('20170518',1,5,9,5,1,2,1885,29),('20170518',1,6,9,6,1,2,1275,17),('20170518',1,7,9,7,1,2,840,12),('20170518',2,8,9,8,2,1,920,23),('20170518',2,9,9,9,2,1,1080,36),('20170518',2,10,9,10,2,1,240,12),('20170518',2,11,9,12,2,1,690,23),('20170518',2,12,9,13,2,2,6240,12),('20170518',2,13,10,14,2,2,5880,12),('20170518',2,14,10,15,2,2,6630,13),('20170518',2,15,10,16,2,2,15080,29),('20170518',2,16,10,17,2,2,1300,13),('20170518',2,17,10,18,2,2,5335,11),('20170518',2,18,10,19,2,2,3060,17),('20170518',2,19,10,20,2,2,1020,17),('20170518',2,20,10,21,2,2,950,5);
/*!40000 ALTER TABLE `B_Buying` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Customer`
--

DROP TABLE IF EXISTS `B_Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Customer` (
  `Cust_ID` int(11) NOT NULL,
  `Cust_Name` varchar(45) DEFAULT NULL,
  `HP` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Customer`
--

LOCK TABLES `B_Customer` WRITE;
/*!40000 ALTER TABLE `B_Customer` DISABLE KEYS */;
INSERT INTO `B_Customer` VALUES (1,'robert','01014513738'),(2,'crise','01068436846'),(3,'mike','01086534986'),(4,'rachel','01025757654'),(5,'lucy','01074532445'),(6,'irrme','01032468735'),(7,'klas','01015647354'),(8,'kloy','01037513355'),(9,'delta','01064537533'),(10,'rune','01047752223');
/*!40000 ALTER TABLE `B_Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Employee`
--

DROP TABLE IF EXISTS `B_Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Employee` (
  `Em_ID` int(11) NOT NULL DEFAULT '0',
  `Em_TypeID` int(11) NOT NULL DEFAULT '0',
  `Em_Name` varchar(45) DEFAULT NULL,
  `HP` varchar(45) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Employee`
--

LOCK TABLES `B_Employee` WRITE;
/*!40000 ALTER TABLE `B_Employee` DISABLE KEYS */;
INSERT INTO `B_Employee` VALUES (1,1,'jone','01012123434'),(2,2,'bab','01045458787'),(3,1,'kimboksun','01097976868'),(4,2,'choimaua','01015153434'),(5,1,'kimchangsik','01044559988'),(6,2,'LE','01068747423'),(7,3,'junghwa','01076212843'),(8,3,'hani','01044327453'),(9,3,'solgee','01068518745'),(10,3,'hyerin','01022647787');
/*!40000 ALTER TABLE `B_Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Foods`
--

DROP TABLE IF EXISTS `B_Foods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Foods` (
  `Foods_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Foods_name` varchar(45) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Foods`
--

LOCK TABLES `B_Foods` WRITE;
/*!40000 ALTER TABLE `B_Foods` DISABLE KEYS */;
INSERT INTO `B_Foods` VALUES (1,1,1,'chicken meat'),(2,1,1,'pig meat'),(3,1,1,'egg'),(4,1,2,'green onion'),(5,1,2,'carrot'),(6,1,2,'cucumber'),(7,1,2,'chili'),(8,2,1,'gochujang'),(9,2,1,'chili powder'),(10,2,1,'sugar'),(11,2,1,'peperoncino'),(12,2,1,'salt'),(13,2,2,'tteok'),(14,2,2,'fish cake'),(15,2,2,'ramen'),(16,2,2,'rice'),(17,2,2,'flour'),(18,2,2,'soft tofu'),(19,2,2,'danmuji'),(20,2,2,'lavar'),(21,2,2,'kimchi'),(22,2,3,'coke'),(23,2,3,'sprit'),(24,2,3,'fanta');
/*!40000 ALTER TABLE `B_Foods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Foods_L`
--

DROP TABLE IF EXISTS `B_Foods_L`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Foods_L` (
  `L_ID` int(11) NOT NULL,
  `L_Name` varchar(45) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Foods_L`
--

LOCK TABLES `B_Foods_L` WRITE;
/*!40000 ALTER TABLE `B_Foods_L` DISABLE KEYS */;
INSERT INTO `B_Foods_L` VALUES (1,'argicultual produce'),(2,'processed goods');
/*!40000 ALTER TABLE `B_Foods_L` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Foods_M`
--

DROP TABLE IF EXISTS `B_Foods_M`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Foods_M` (
  `M_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) NOT NULL DEFAULT '0',
  `M_Name` varchar(45) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Foods_M`
--

LOCK TABLES `B_Foods_M` WRITE;
/*!40000 ALTER TABLE `B_Foods_M` DISABLE KEYS */;
INSERT INTO `B_Foods_M` VALUES (1,1,'meat'),(1,2,'spice'),(2,1,'vagetable'),(2,2,'complete'),(3,2,'drink');
/*!40000 ALTER TABLE `B_Foods_M` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Menu`
--

DROP TABLE IF EXISTS `B_Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Menu` (
  `Menu_TypeID` int(11) NOT NULL DEFAULT '0',
  `Menu_ID` int(11) NOT NULL DEFAULT '0',
  `Menu_Name` varchar(45) DEFAULT NULL,
  `Menu_Price` double DEFAULT NULL,
  `Menu_Used` int(11) DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Menu`
--

LOCK TABLES `B_Menu` WRITE;
/*!40000 ALTER TABLE `B_Menu` DISABLE KEYS */;
INSERT INTO `B_Menu` VALUES (1,1,'tteokbokki',4000,1),(1,2,'ramen',3000,1),(1,3,'extrem spicy ramen',3500,0),(2,1,'pork cutlet',5500,1),(2,2,'soft tofu stew',5000,1),(2,3,'chicken fried rice',5000,1),(3,1,'orignal kimbab',2000,1),(3,2,'kimchi kimbab',2500,1),(3,3,'spicy kimbab',2500,1),(4,2,'sprit',1000,1),(4,3,'fanta',1000,1);
/*!40000 ALTER TABLE `B_Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Menu_Type`
--

DROP TABLE IF EXISTS `B_Menu_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Menu_Type` (
  `Menu_TypeID` int(11) NOT NULL,
  `Menu_TypeName` varchar(45) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Menu_Type`
--

LOCK TABLES `B_Menu_Type` WRITE;
/*!40000 ALTER TABLE `B_Menu_Type` DISABLE KEYS */;
INSERT INTO `B_Menu_Type` VALUES (1,'carb snack'),(2,'food'),(3,'kimbob'),(4,'drink');
/*!40000 ALTER TABLE `B_Menu_Type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Recipe`
--

DROP TABLE IF EXISTS `B_Recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Recipe` (
  `Menu_Type` int(11) NOT NULL DEFAULT '0',
  `Menu_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Foods_ID` int(11) NOT NULL DEFAULT '0',
  `Foods_Count` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Recipe`
--

LOCK TABLES `B_Recipe` WRITE;
/*!40000 ALTER TABLE `B_Recipe` DISABLE KEYS */;
INSERT INTO `B_Recipe` VALUES (1,1,2,1,8,1),(1,1,2,1,9,1),(1,1,2,1,10,1),(1,1,2,2,13,1),(1,1,2,2,14,1),(1,2,1,2,4,1),(1,2,2,1,9,1),(1,2,2,2,15,1),(1,3,1,2,4,1),(1,3,2,1,9,1),(1,3,2,1,11,1),(1,3,2,2,15,1),(2,1,1,1,2,1),(2,1,1,1,3,1),(2,1,2,2,17,1),(2,2,1,2,4,1),(2,2,1,2,8,1),(2,2,2,1,9,1),(2,2,2,1,12,1),(2,2,2,2,18,1),(2,3,1,1,1,1),(2,3,1,1,3,1),(2,3,1,2,5,1),(2,3,2,1,12,1),(2,3,2,2,16,1),(3,1,1,1,3,1),(3,1,1,2,5,1),(3,1,1,2,6,1),(3,1,2,2,16,1),(3,1,2,2,19,1),(3,1,2,2,20,1),(3,2,1,1,3,1),(3,2,1,2,5,1),(3,2,1,2,6,1),(3,2,2,2,16,1),(3,2,2,2,19,1),(3,2,2,2,20,1),(3,2,2,2,21,1),(3,3,1,1,3,1),(3,3,1,2,5,1),(3,3,1,2,6,1),(3,3,1,2,7,1),(3,3,2,2,16,1),(3,3,2,2,19,1),(3,3,2,2,20,1),(4,1,2,3,22,1),(4,2,2,3,23,1),(4,3,2,3,24,1);
/*!40000 ALTER TABLE `B_Recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Res_Table`
--

DROP TABLE IF EXISTS `B_Res_Table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Res_Table` (
  `Table_ID` int(11) NOT NULL DEFAULT '0',
  `Persons` int(11) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Res_Table`
--

LOCK TABLES `B_Res_Table` WRITE;
/*!40000 ALTER TABLE `B_Res_Table` DISABLE KEYS */;
INSERT INTO `B_Res_Table` VALUES (1,2,0),(2,2,0),(3,2,0),(4,2,0),(5,2,0),(6,4,0),(7,4,0),(8,4,0),(9,4,0),(10,4,0);
/*!40000 ALTER TABLE `B_Res_Table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Stock`
--

DROP TABLE IF EXISTS `B_Stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Stock` (
  `Stock_Date` varchar(8) NOT NULL DEFAULT '',
  `Bill_Idx` int(11) NOT NULL DEFAULT '0',
  `Foods_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Stock_Price` double DEFAULT NULL,
  `Stock_Count` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Stock`
--

LOCK TABLES `B_Stock` WRITE;
/*!40000 ALTER TABLE `B_Stock` DISABLE KEYS */;
INSERT INTO `B_Stock` VALUES ('20170516',1,1,1,1,996,3),('20170516',1,2,1,1,1006.85185185185,5),('20170516',1,3,1,1,96.605504587156,13),('20170516',1,5,1,2,68.2941176470588,8),('20170516',1,6,1,2,72.8571428571429,5),('20170516',1,12,2,1,31.3194444444444,3),('20170516',1,16,2,2,509.882352941176,8),('20170516',1,17,2,2,97.962962962963,5),('20170516',1,19,2,2,195.142857142857,5),('20170516',1,20,2,2,54.0714285714286,5),('20170516',1,21,2,2,194.444444444444,5),('20170516',2,2,1,1,1006.85185185185,3),('20170516',2,3,1,1,96.605504587156,3),('20170516',2,4,1,2,68.1395348837209,4),('20170516',2,8,1,2,45.4166666666667,9),('20170516',2,9,2,1,27.9203539823009,9),('20170516',2,10,2,1,26.5789473684211,5),('20170516',2,12,2,1,31.3194444444444,4),('20170516',2,13,2,2,506.842105263158,5),('20170516',2,14,2,2,497.894736842105,5),('20170516',2,17,2,2,97.962962962963,3),('20170516',2,18,2,2,494.298245614035,4),('20170516',3,3,1,1,96.605504587156,5),('20170516',3,4,1,2,68.1395348837209,6),('20170516',3,6,1,2,72.8571428571429,5),('20170516',3,9,2,1,27.9203539823009,6),('20170516',3,15,2,2,499.491525423729,6),('20170516',3,16,2,2,509.882352941176,5),('20170516',3,19,2,2,195.142857142857,5),('20170516',3,20,2,2,54.0714285714286,5),('20170516',3,23,2,3,500,11),('20170517',1,4,1,2,68.1395348837209,6),('20170517',1,8,1,2,45.4166666666667,10),('20170517',1,9,2,1,27.9203539823009,10),('20170517',1,10,2,1,26.5789473684211,4),('20170517',1,12,2,1,31.3194444444444,4),('20170517',1,13,2,2,506.842105263158,4),('20170517',1,14,2,2,497.894736842105,4),('20170517',1,18,2,2,494.298245614035,6),('20170517',1,22,2,3,500,10),('20170517',2,2,1,1,1006.85185185185,3),('20170517',2,3,1,1,96.605504587156,8),('20170517',2,4,1,2,68.1395348837209,4),('20170517',2,5,1,2,68.2941176470588,5),('20170517',2,6,1,2,72.8571428571429,5),('20170517',2,9,2,1,27.9203539823009,4),('20170517',2,15,2,2,499.491525423729,4),('20170517',2,16,2,2,509.882352941176,5),('20170517',2,17,2,2,97.962962962963,3),('20170517',2,19,2,2,195.142857142857,5),('20170517',2,20,2,2,54.0714285714286,5),('20170517',2,21,2,2,194.444444444444,5),('20170517',3,4,1,2,68.1395348837209,12),('20170517',3,8,1,2,45.4166666666667,10),('20170517',3,9,2,1,27.9203539823009,16),('20170517',3,10,2,1,26.5789473684211,4),('20170517',3,12,2,1,31.3194444444444,6),('20170517',3,13,2,2,506.842105263158,4),('20170517',3,14,2,2,497.894736842105,4),('20170517',3,15,2,2,499.491525423729,6),('20170517',3,18,2,2,494.298245614035,6),('20170517',4,3,1,1,96.605504587156,8),('20170517',4,5,1,2,68.2941176470588,8),('20170517',4,6,1,2,72.8571428571429,8),('20170517',4,7,1,2,70.531914893617,5),('20170517',4,8,2,1,45.4166666666667,2),('20170517',4,9,2,1,27.9203539823009,2),('20170517',4,10,2,1,26.5789473684211,2),('20170517',4,13,2,2,506.842105263158,2),('20170517',4,14,2,2,497.894736842105,2),('20170517',4,16,2,2,509.882352941176,8),('20170517',4,19,2,2,195.142857142857,8),('20170517',4,20,2,2,54.0714285714286,8),('20170518',1,1,1,1,996,6),('20170518',1,3,1,1,96.605504587156,9),('20170518',1,4,1,2,68.1395348837209,2),('20170518',1,5,1,2,68.2941176470588,9),('20170518',1,6,1,2,72.8571428571429,3),('20170518',1,7,1,2,70.531914893617,3),('20170518',1,9,2,1,27.9203539823009,2),('20170518',1,12,2,1,31.3194444444444,6),('20170518',1,15,2,2,499.491525423729,2),('20170518',1,16,2,2,509.882352941176,9),('20170518',1,19,2,2,195.142857142857,3),('20170518',1,20,2,2,54.0714285714286,3),('20170518',2,2,1,1,1006.85185185185,5),('20170518',2,3,1,1,96.605504587156,10),('20170518',2,5,1,2,68.2941176470588,5),('20170518',2,6,1,2,72.8571428571429,5),('20170518',2,8,2,1,45.4166666666667,3),('20170518',2,9,2,1,27.9203539823009,3),('20170518',2,10,2,1,26.5789473684211,3),('20170518',2,13,2,2,506.842105263158,3),('20170518',2,14,2,2,497.894736842105,3),('20170518',2,16,2,2,509.882352941176,5),('20170518',2,17,2,2,97.962962962963,5),('20170518',2,19,2,2,195.142857142857,5),('20170518',2,20,2,2,54.0714285714286,5),('20170518',2,21,2,2,194.444444444444,5),('20170518',3,1,1,1,996,4),('20170518',3,2,1,1,1006.85185185185,6),('20170518',3,3,1,1,96.605504587156,10),('20170518',3,4,1,2,68.1395348837209,6),('20170518',3,5,1,2,68.2941176470588,4),('20170518',3,8,1,2,45.4166666666667,6),('20170518',3,9,2,1,27.9203539823009,6),('20170518',3,12,2,1,31.3194444444444,10),('20170518',3,16,2,2,509.882352941176,4),('20170518',3,17,2,2,97.962962962963,6),('20170518',3,18,2,2,494.298245614035,6),('20170519',1,1,1,1,996,2),('20170519',1,3,1,1,96.605504587156,7),('20170519',1,4,1,2,68.1395348837209,6),('20170519',1,5,1,2,68.2941176470588,7),('20170519',1,6,1,2,72.8571428571429,5),('20170519',1,7,1,2,70.531914893617,5),('20170519',1,9,2,1,27.9203539823009,6),('20170519',1,12,2,1,31.3194444444444,2),('20170519',1,15,2,2,499.491525423729,6),('20170519',1,16,2,2,509.882352941176,7),('20170519',1,19,2,2,195.142857142857,5),('20170519',1,20,2,2,54.0714285714286,5),('20170519',2,2,1,1,1006.85185185185,2),('20170519',2,3,1,1,96.605504587156,2),('20170519',2,4,1,2,68.1395348837209,5),('20170519',2,8,1,2,45.4166666666667,10),('20170519',2,9,2,1,27.9203539823009,10),('20170519',2,10,2,1,26.5789473684211,5),('20170519',2,12,2,1,31.3194444444444,5),('20170519',2,13,2,2,506.842105263158,5),('20170519',2,14,2,2,497.894736842105,5),('20170519',2,17,2,2,97.962962962963,2),('20170519',2,18,2,2,494.298245614035,5),('20170519',3,3,1,1,96.605504587156,4),('20170519',3,4,1,2,68.1395348837209,5),('20170519',3,5,1,2,68.2941176470588,4),('20170519',3,6,1,2,72.8571428571429,4),('20170519',3,7,1,2,70.531914893617,4),('20170519',3,8,2,1,45.4166666666667,4),('20170519',3,9,2,1,27.9203539823009,9),('20170519',3,10,2,1,26.5789473684211,4),('20170519',3,13,2,2,506.842105263158,4),('20170519',3,14,2,2,497.894736842105,4),('20170519',3,15,2,2,499.491525423729,5),('20170519',3,16,2,2,509.882352941176,4),('20170519',3,19,2,2,195.142857142857,4),('20170519',3,20,2,2,54.0714285714286,4);
/*!40000 ALTER TABLE `B_Stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `B_Trader`
--

DROP TABLE IF EXISTS `B_Trader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `B_Trader` (
  `Trader_ID` int(11) NOT NULL,
  `Trader_Name` varchar(45) DEFAULT NULL,
  `HP` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `B_Trader`
--

LOCK TABLES `B_Trader` WRITE;
/*!40000 ALTER TABLE `B_Trader` DISABLE KEYS */;
INSERT INTO `B_Trader` VALUES (1,'sinan','01022222222'),(2,'hwanhwa','01033333333'),(3,'samsung','01077777777');
/*!40000 ALTER TABLE `B_Trader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `bookid` int(11) NOT NULL,
  `bookname` varchar(50) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (1,'ì¶•êµ¬ì˜ ì—­ì‚¬','êµ¿ìŠ¤í¬ì¸ ',7000),(2,'ì¶•êµ¬ ì•„ëŠ” ì—¬ìž','ë‚˜ë¬´ìˆ˜',13000),(3,'ì¶•êµ¬ì˜ ì´í•´','ëŒ€í•œë¯¸ë””ì–´',22000),(4,'ê³¨í”„ ë°”ì´ë¸”','ëŒ€í•œë¯¸ë””ì–´',35000),(5,'í”¼ê²¨ êµë³¸','êµ¿ìŠ¤í¬ì¸ ',8000),(6,'ì—­ë„ ë‹¨ê³„ë³„ ê¸°ìˆ ','êµ¿ìŠ¤í¬ì¸ ',6000),(7,'ì•¼êµ¬ì˜ ì¶”ì–µ','ì´ìƒë¯¸ë””ì–´',20000),(8,'ì•¼êµ¬ë¥¼ ë¶€íƒí•´','ì´ìƒë¯¸ë””ì–´',13000),(9,'ì˜¬ë¦¼í”½ ì´ì•¼ê¸°','ì‚¼ì„±ë‹¹',7500),(10,'Olympic Champions','Pearson',13000);
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cinema`
--

DROP TABLE IF EXISTS `Cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cinema` (
  `thea_id` int(11) NOT NULL DEFAULT '0',
  `cine_id` int(11) NOT NULL DEFAULT '0',
  `title` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  PRIMARY KEY (`thea_id`,`cine_id`),
  KEY `lid` (`cine_id`),
  CONSTRAINT `Cinema_ibfk_1` FOREIGN KEY (`thea_id`) REFERENCES `Theater` (`thea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cinema`
--

LOCK TABLES `Cinema` WRITE;
/*!40000 ALTER TABLE `Cinema` DISABLE KEYS */;
INSERT INTO `Cinema` VALUES (1,1,'ì–´ë ¤ìš´ ì˜í™”',16500,48),(3,1,'ë©‹ì§„ ì˜í™”',8250,120),(3,2,'ìž¬ë°ŒëŠ” ì˜í™”',8800,110);
/*!40000 ALTER TABLE `Cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cust`
--

DROP TABLE IF EXISTS `Cust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cust` (
  `custid` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`custid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cust`
--

LOCK TABLES `Cust` WRITE;
/*!40000 ALTER TABLE `Cust` DISABLE KEYS */;
INSERT INTO `Cust` VALUES (1,'ë°•ì§€ì„±','ì˜êµ­ ë§¨ì²´ìŠ¤íƒ€','000-5000-0001'),(2,'ê¹€ì—°ì•„','ëŒ€í•œë¯¼êµ­ ì„œìš¸','000-6000-0001'),(3,'ìž¥ë¯¸ëž€','ëŒ€í•œë¯¼êµ­ ê°•ì›ë„','000-7000-0001'),(4,'ì¶”ì‹ ìˆ˜','ë¯¸êµ­ í´ë¦¬ë¸”ëžœë“œ','000-8000-0001'),(5,'ë°•ì„¸ë¦¬','ëŒ€í•œë¯¼êµ­ ëŒ€ì „',NULL);
/*!40000 ALTER TABLE `Cust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `cust_id` int(11) NOT NULL,
  `name` varchar(15) DEFAULT NULL,
  `addr` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (3,'í™ê¸¸ë™','ê°•ë‚¨'),(4,'ê¹€ì² ìˆ˜','ìž ì‹¤'),(9,'ë°•ì˜í¬','ê°•ë‚¨');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `N_Bill`
--

DROP TABLE IF EXISTS `N_Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `N_Bill` (
  `Bill_Date` varchar(8) NOT NULL DEFAULT '',
  `Bill_Idx` int(11) NOT NULL DEFAULT '0',
  `Bill_TableID` int(11) DEFAULT NULL,
  `Bill_Cash` double DEFAULT NULL,
  `Bill_Card` double DEFAULT NULL,
  `Bill_Balancedue` double DEFAULT NULL,
  `Bill_Employee` int(11) DEFAULT NULL,
  `Bill_Cust` int(11) DEFAULT NULL,
  PRIMARY KEY (`Bill_Date`,`Bill_Idx`),
  KEY `Bill` (`Bill_Date`,`Bill_Idx`),
  KEY `Bill_Date` (`Bill_Date`,`Bill_Idx`),
  KEY `Bill_Date_2` (`Bill_Date`,`Bill_Idx`),
  KEY `Bill_Idx` (`Bill_Idx`),
  KEY `Bill_Employee` (`Bill_Employee`),
  KEY `Bill_TableID` (`Bill_TableID`),
  KEY `Bill_Cust` (`Bill_Cust`),
  CONSTRAINT `N_Bill_ibfk_1` FOREIGN KEY (`Bill_Date`) REFERENCES `N_Bill_Item` (`Bill_Date`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `N_Bill_ibfk_2` FOREIGN KEY (`Bill_Idx`) REFERENCES `N_Bill_Item` (`Bill_Idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `N_Bill`
--

LOCK TABLES `N_Bill` WRITE;
/*!40000 ALTER TABLE `N_Bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `N_Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `N_Bill_Item`
--

DROP TABLE IF EXISTS `N_Bill_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `N_Bill_Item` (
  `Bill_Date` varchar(8) NOT NULL,
  `Bill_Idx` int(11) NOT NULL,
  `Bill_ItemID` int(11) NOT NULL DEFAULT '0',
  `Menu_Type` int(11) DEFAULT NULL,
  `Menu_ID` int(11) DEFAULT NULL,
  `Menu_Price` double DEFAULT NULL,
  `Menu_Count` int(11) DEFAULT NULL,
  PRIMARY KEY (`Bill_ItemID`,`Bill_Date`,`Bill_Idx`),
  KEY `Item` (`Bill_Date`,`Bill_Idx`),
  KEY `Bill_Date` (`Bill_Date`,`Bill_Idx`),
  KEY `Menu_ID` (`Menu_ID`),
  KEY `Menu_Type` (`Menu_Type`),
  KEY `Bill_Idx` (`Bill_Idx`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `N_Bill_Item`
--

LOCK TABLES `N_Bill_Item` WRITE;
/*!40000 ALTER TABLE `N_Bill_Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `N_Bill_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `orderid` int(11) NOT NULL DEFAULT '0',
  `custid` int(11) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `saleprice` int(11) DEFAULT NULL,
  `orderdate` date DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `custid` (`custid`),
  KEY `bookid` (`bookid`),
  CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`custid`) REFERENCES `Cust` (`custid`),
  CONSTRAINT `Orders_ibfk_2` FOREIGN KEY (`bookid`) REFERENCES `Book` (`bookid`),
  CONSTRAINT `Orders_ibfk_3` FOREIGN KEY (`bookid`) REFERENCES `Book` (`bookid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (1,1,1,6000,'2013-07-01'),(2,1,3,21000,'2013-07-03'),(3,2,5,8000,'2013-07-03'),(4,3,6,6000,'2013-07-04'),(5,4,7,20000,'2013-07-05'),(6,1,2,12000,'2013-07-07'),(7,4,8,13000,'2013-07-07'),(8,3,10,12000,'2013-07-08'),(9,2,10,7000,'2013-07-09'),(10,3,8,13000,'2013-07-10');
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `thea_id` int(11) NOT NULL DEFAULT '0',
  `cine_id` int(11) NOT NULL DEFAULT '0',
  `cust_id` int(11) NOT NULL DEFAULT '0',
  `sead_id` int(11) DEFAULT NULL,
  `resoldate` date DEFAULT NULL,
  PRIMARY KEY (`thea_id`,`cine_id`,`cust_id`),
  KEY `cust_id` (`cust_id`),
  KEY `cine_id` (`cine_id`),
  CONSTRAINT `Reservation_ibfk_1` FOREIGN KEY (`thea_id`) REFERENCES `Theater` (`thea_id`),
  CONSTRAINT `Reservation_ibfk_2` FOREIGN KEY (`cust_id`) REFERENCES `Customer` (`cust_id`),
  CONSTRAINT `Reservation_ibfk_3` FOREIGN KEY (`cine_id`) REFERENCES `Cinema` (`cine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (1,1,9,48,'2013-09-01'),(3,1,4,16,'2013-09-01'),(3,2,3,15,'2013-09-01');
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Bill`
--

DROP TABLE IF EXISTS `T_Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Bill` (
  `Bill_Date` varchar(8) NOT NULL DEFAULT '',
  `Bill_Idx` int(11) NOT NULL DEFAULT '0',
  `Bill_TableID` int(11) DEFAULT NULL,
  `Bill_Cash` int(11) DEFAULT '0',
  `Bill_Card` int(11) DEFAULT '0',
  `Bill_Balancedue` int(11) DEFAULT '0',
  `Bill_Employee` int(11) DEFAULT NULL,
  `Bill_Cust` int(11) DEFAULT '0',
  PRIMARY KEY (`Bill_Date`,`Bill_Idx`),
  KEY `Bill_TableID` (`Bill_TableID`),
  KEY `Bill_Employee` (`Bill_Employee`),
  KEY `Bill_Cust` (`Bill_Cust`),
  KEY `idx3` (`Bill_Idx`),
  KEY `idx4` (`Bill_Date`),
  CONSTRAINT `T_Bill_ibfk_4` FOREIGN KEY (`Bill_TableID`) REFERENCES `T_Res_table` (`Table_ID`),
  CONSTRAINT `T_Bill_ibfk_5` FOREIGN KEY (`Bill_Employee`) REFERENCES `T_Employee` (`Em_ID`),
  CONSTRAINT `T_Bill_ibfk_6` FOREIGN KEY (`Bill_Cust`) REFERENCES `T_Customer` (`Cust_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Bill`
--

LOCK TABLES `T_Bill` WRITE;
/*!40000 ALTER TABLE `T_Bill` DISABLE KEYS */;
INSERT INTO `T_Bill` VALUES ('20170516',1,3,25000,30000,0,1,6),('20170516',2,4,17500,39000,0,3,4),('20170516',3,2,24500,14500,0,5,8),('20170517',1,3,2500,4500,49000,1,2),('20170517',2,8,41000,0,0,1,1),('20170517',3,5,45000,9000,10000,3,5),('20170517',4,6,2500,24000,0,5,10),('20170518',1,9,4500,36000,0,1,2),('20170518',2,4,25000,31000,0,3,5),('20170518',3,2,12500,25800,2700,5,6),('20170519',1,7,36500,26500,0,1,5),('20170519',2,2,0,55000,0,1,4),('20170519',3,6,53500,0,0,2,3),('20170610',1,3,25000,5000,0,5,5),('20170610',2,5,0,0,8000,5,5),('20170610',3,7,17500,0,0,5,5),('20170610',4,6,7500,0,0,5,5),('20170612',1,7,0,0,0,5,5),('20170612',2,8,0,0,0,5,5);
/*!40000 ALTER TABLE `T_Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Bill_Item`
--

DROP TABLE IF EXISTS `T_Bill_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Bill_Item` (
  `Bill_Date` varchar(8) NOT NULL DEFAULT '',
  `Bill_Idx` int(11) NOT NULL DEFAULT '0',
  `Bill_ItemID` int(11) NOT NULL DEFAULT '0',
  `Menu_Type` int(11) DEFAULT NULL,
  `Menu_ID` int(11) DEFAULT NULL,
  `Menu_Price` int(11) DEFAULT NULL,
  `Menu_Count` int(11) DEFAULT NULL,
  PRIMARY KEY (`Bill_Date`,`Bill_Idx`,`Bill_ItemID`),
  KEY `idx1` (`Bill_Idx`),
  KEY `idx2` (`Bill_Date`),
  CONSTRAINT `T_Bill_Item_ibfk_1` FOREIGN KEY (`Bill_Date`) REFERENCES `T_Bill` (`Bill_Date`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `T_Bill_Item_ibfk_2` FOREIGN KEY (`Bill_Idx`) REFERENCES `T_Bill` (`Bill_Idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Bill_Item`
--

LOCK TABLES `T_Bill_Item` WRITE;
/*!40000 ALTER TABLE `T_Bill_Item` DISABLE KEYS */;
INSERT INTO `T_Bill_Item` VALUES ('20170516',1,1,3,2,2500,5),('20170516',1,2,2,1,5500,5),('20170516',1,3,2,3,5000,3),('20170516',2,1,2,2,5000,4),('20170516',2,2,2,1,5500,3),('20170516',2,3,1,1,4000,5),('20170516',3,1,3,1,2000,5),('20170516',3,2,1,2,3000,6),('20170516',3,3,4,2,1000,11),('20170517',1,1,2,2,5000,6),('20170517',1,2,1,1,4000,4),('20170517',1,3,4,1,1000,10),('20170517',2,1,3,2,2500,5),('20170517',2,2,1,2,3000,4),('20170517',2,3,2,1,5500,3),('20170517',3,1,2,2,5000,6),('20170517',3,2,1,2,3000,6),('20170517',3,3,1,1,4000,4),('20170517',4,1,3,1,2000,3),('20170517',4,2,3,3,2500,5),('20170517',4,3,1,1,4000,2),('20170518',1,1,3,3,2500,5),('20170518',1,2,1,2,3000,6),('20170518',1,3,2,3,5000,2),('20170518',2,1,2,2,5000,5),('20170518',2,2,1,1,4000,5),('20170518',2,3,2,1,5500,2),('20170518',3,1,1,1,4000,4),('20170518',3,2,3,3,2500,4),('20170518',3,3,1,2,3000,5),('20170519',1,1,2,3,5000,5),('20170519',1,2,2,1,5500,6),('20170519',1,3,3,2,2500,2),('20170519',2,1,2,2,5000,5),('20170519',2,2,1,1,4000,5),('20170519',2,3,2,2,5000,2),('20170519',3,1,1,1,4000,4),('20170519',3,2,3,3,2500,4),('20170519',3,3,2,1,5500,5),('20170610',1,1,4,3,1000,5),('20170610',1,2,2,3,5000,5),('20170610',2,1,3,1,2000,4),('20170610',3,1,1,3,3500,5),('20170610',4,1,3,3,2500,3),('20170612',1,1,3,3,2500,5),('20170612',2,1,4,3,1000,2);
/*!40000 ALTER TABLE `T_Bill_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Buying`
--

DROP TABLE IF EXISTS `T_Buying`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Buying` (
  `Buying_Date` varchar(8) NOT NULL DEFAULT '',
  `Trader_ID` int(11) NOT NULL DEFAULT '0',
  `Buying_ID` int(11) NOT NULL DEFAULT '0',
  `Em_ID` int(11) DEFAULT NULL,
  `Foods_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Buying_Price` double DEFAULT NULL,
  `Buying_Count` int(11) DEFAULT NULL,
  PRIMARY KEY (`Buying_Date`,`Buying_ID`,`Trader_ID`,`Foods_ID`),
  KEY `Trader_ID` (`Trader_ID`),
  KEY `Foods_ID` (`Foods_ID`),
  KEY `L_ID` (`L_ID`),
  KEY `M_ID` (`M_ID`),
  KEY `Em_ID` (`Em_ID`),
  CONSTRAINT `T_Buying_ibfk_1` FOREIGN KEY (`Trader_ID`) REFERENCES `T_Trader` (`Trader_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Buying_ibfk_2` FOREIGN KEY (`Foods_ID`) REFERENCES `T_Foods` (`Foods_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Buying_ibfk_3` FOREIGN KEY (`L_ID`) REFERENCES `T_Foods_L` (`L_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Buying_ibfk_4` FOREIGN KEY (`M_ID`) REFERENCES `T_Foods_M` (`M_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Buying_ibfk_5` FOREIGN KEY (`Em_ID`) REFERENCES `T_Employee` (`Em_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Buying`
--

LOCK TABLES `T_Buying` WRITE;
/*!40000 ALTER TABLE `T_Buying` DISABLE KEYS */;
INSERT INTO `T_Buying` VALUES ('20170515',1,1,7,1,1,1,30000,30),('20170515',1,2,7,2,1,1,30000,30),('20170515',1,3,7,3,1,1,3000,30),('20170515',1,4,7,4,1,2,2100,30),('20170515',1,5,7,5,1,2,2100,30),('20170515',1,6,7,6,1,2,2100,30),('20170515',1,7,7,7,1,2,2100,30),('20170515',2,8,7,8,2,1,1500,30),('20170515',2,9,7,9,2,1,900,30),('20170515',2,10,7,10,2,1,900,30),('20170515',2,11,7,11,2,1,6000,30),('20170515',2,12,7,12,2,1,900,30),('20170515',2,13,7,13,2,2,15000,30),('20170515',2,14,7,14,2,2,15000,30),('20170515',2,15,7,15,2,2,15000,30),('20170515',2,16,7,16,2,2,15000,30),('20170515',2,17,7,17,2,2,3000,30),('20170515',2,18,7,18,2,2,15000,30),('20170515',2,19,7,19,2,2,6000,30),('20170515',2,20,7,20,2,2,1500,30),('20170515',2,21,7,21,2,2,6000,30),('20170515',3,22,7,22,2,3,15000,30),('20170515',3,23,7,23,2,3,15000,30),('20170515',3,24,7,24,2,3,15000,30),('20170517',1,1,8,1,1,1,2940,3),('20170517',1,2,8,2,1,1,11110,11),('20170517',1,3,8,3,1,1,3330,37),('20170517',1,4,8,4,1,2,2080,32),('20170517',1,5,8,5,1,2,1820,26),('20170517',1,6,8,6,1,2,1725,23),('20170517',1,7,8,7,1,2,375,5),('20170517',2,8,8,8,1,2,1395,31),('20170517',2,9,8,9,2,1,1175,47),('20170517',2,10,8,10,2,1,375,15),('20170517',2,11,8,12,2,1,665,19),('20170517',2,12,8,13,2,2,7650,15),('20170517',2,13,8,14,2,2,7500,15),('20170517',2,14,8,15,2,2,7840,16),('20170517',2,15,8,16,2,2,13260,26),('20170517',2,16,8,17,2,2,990,11),('20170517',2,17,8,18,2,2,7840,16),('20170517',2,18,8,19,2,2,4600,23),('20170517',2,19,8,20,2,2,1265,23),('20170517',2,20,8,21,2,2,1800,10),('20170517',3,21,8,22,2,3,5000,10),('20170517',3,22,8,23,2,3,5500,11),('20170518',1,1,9,1,1,1,11880,12),('20170518',1,2,9,2,1,1,13260,13),('20170518',1,3,9,3,1,1,4200,42),('20170518',1,4,9,4,1,2,1680,24),('20170518',1,5,9,5,1,2,1885,29),('20170518',1,6,9,6,1,2,1275,17),('20170518',1,7,9,7,1,2,840,12),('20170518',2,8,9,8,2,1,920,23),('20170518',2,9,9,9,2,1,1080,36),('20170518',2,10,9,10,2,1,240,12),('20170518',2,11,9,12,2,1,690,23),('20170518',2,12,9,13,2,2,6240,12),('20170518',2,13,10,14,2,2,5880,12),('20170518',2,14,10,15,2,2,6630,13),('20170518',2,15,10,16,2,2,15080,29),('20170518',2,16,10,17,2,2,1300,13),('20170518',2,17,10,18,2,2,5335,11),('20170518',2,18,10,19,2,2,3060,17),('20170518',2,19,10,20,2,2,1020,17),('20170518',2,20,10,21,2,2,950,5),('20170609',1,1,7,23,2,3,800,5),('20170609',2,2,9,24,2,3,700,15),('20170610',1,1,10,24,2,3,700,2);
/*!40000 ALTER TABLE `T_Buying` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Customer`
--

DROP TABLE IF EXISTS `T_Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Customer` (
  `Cust_ID` int(11) NOT NULL,
  `Cust_Name` varchar(45) DEFAULT NULL,
  `HP` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Cust_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Customer`
--

LOCK TABLES `T_Customer` WRITE;
/*!40000 ALTER TABLE `T_Customer` DISABLE KEYS */;
INSERT INTO `T_Customer` VALUES (1,'robert','01014513738'),(2,'crise','01068436846'),(3,'mike','01086534986'),(4,'rachel','01025757654'),(5,'lucy','01074532445'),(6,'irrme','01032468735'),(7,'klas','01015647354'),(8,'kloy','01037513355'),(9,'delta','01064537533'),(10,'rune','01047752223');
/*!40000 ALTER TABLE `T_Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Employee`
--

DROP TABLE IF EXISTS `T_Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Employee` (
  `Em_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Em_TypeID` int(11) NOT NULL DEFAULT '0',
  `Em_Name` varchar(45) DEFAULT NULL,
  `HP` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Em_ID`),
  KEY `Em_ID` (`Em_ID`,`Em_TypeID`),
  KEY `Em_TypeID` (`Em_TypeID`),
  CONSTRAINT `T_Employee_ibfk_1` FOREIGN KEY (`Em_TypeID`) REFERENCES `T_Private_Type` (`Em_TypeID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Employee`
--

LOCK TABLES `T_Employee` WRITE;
/*!40000 ALTER TABLE `T_Employee` DISABLE KEYS */;
INSERT INTO `T_Employee` VALUES (1,2,'jone','01012123434'),(2,2,'bab','01045458787'),(3,1,'kimboksun','01097976868'),(4,2,'choimaua','01015153434'),(5,1,'kimchangsik','01044559988'),(6,2,'LE','01068747423'),(7,3,'junghwa','01076212843'),(8,3,'hani','01044327453'),(9,3,'solgee','01068518745'),(10,3,'hyerin','01022647787');
/*!40000 ALTER TABLE `T_Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Foods`
--

DROP TABLE IF EXISTS `T_Foods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Foods` (
  `Foods_ID` int(11) NOT NULL AUTO_INCREMENT,
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Foods_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Foods_ID`),
  KEY `M_ID` (`M_ID`),
  KEY `L_ID` (`L_ID`),
  CONSTRAINT `T_Foods_ibfk_1` FOREIGN KEY (`M_ID`) REFERENCES `T_Foods_M` (`M_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Foods_ibfk_2` FOREIGN KEY (`L_ID`) REFERENCES `T_Foods_L` (`L_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Foods`
--

LOCK TABLES `T_Foods` WRITE;
/*!40000 ALTER TABLE `T_Foods` DISABLE KEYS */;
INSERT INTO `T_Foods` VALUES (1,1,1,'chicken meat'),(2,1,1,'pig meat'),(3,1,1,'egg'),(4,1,2,'green onion'),(5,1,2,'carrot'),(6,1,2,'cucumber'),(7,1,2,'chili'),(8,2,1,'gochujang'),(9,2,1,'chili powder'),(10,2,1,'sugar'),(11,2,1,'peperoncino'),(12,2,1,'salt'),(13,2,2,'tteok'),(14,2,2,'fish cake'),(15,2,2,'ramen'),(16,2,2,'rice'),(17,2,2,'flour'),(18,2,2,'soft tofu'),(19,2,2,'danmuji'),(20,2,2,'lavar'),(21,2,2,'kimchi'),(22,2,3,'coke'),(23,2,3,'sprit'),(24,2,3,'fanta');
/*!40000 ALTER TABLE `T_Foods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Foods_L`
--

DROP TABLE IF EXISTS `T_Foods_L`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Foods_L` (
  `L_ID` int(11) NOT NULL,
  `L_Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`L_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Foods_L`
--

LOCK TABLES `T_Foods_L` WRITE;
/*!40000 ALTER TABLE `T_Foods_L` DISABLE KEYS */;
INSERT INTO `T_Foods_L` VALUES (1,'argicultual produce'),(2,'processed goods');
/*!40000 ALTER TABLE `T_Foods_L` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Foods_M`
--

DROP TABLE IF EXISTS `T_Foods_M`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Foods_M` (
  `M_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) NOT NULL DEFAULT '0',
  `M_Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`M_ID`,`L_ID`),
  KEY `L_ID` (`L_ID`),
  CONSTRAINT `T_Foods_M_ibfk_1` FOREIGN KEY (`L_ID`) REFERENCES `T_Foods_L` (`L_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Foods_M`
--

LOCK TABLES `T_Foods_M` WRITE;
/*!40000 ALTER TABLE `T_Foods_M` DISABLE KEYS */;
INSERT INTO `T_Foods_M` VALUES (1,1,'meat'),(1,2,'spice'),(2,1,'vagetable'),(2,2,'complete'),(3,2,'drink');
/*!40000 ALTER TABLE `T_Foods_M` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Menu`
--

DROP TABLE IF EXISTS `T_Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Menu` (
  `Menu_TypeID` int(11) NOT NULL DEFAULT '0',
  `Menu_ID` int(11) NOT NULL DEFAULT '0',
  `Menu_Name` varchar(45) DEFAULT NULL,
  `Menu_Price` double DEFAULT NULL,
  `Menu_Used` int(11) DEFAULT '1',
  PRIMARY KEY (`Menu_TypeID`,`Menu_ID`),
  KEY `Menu_ID` (`Menu_ID`),
  KEY `Menu_ID_2` (`Menu_ID`),
  KEY `Menu_ID_3` (`Menu_ID`),
  CONSTRAINT `T_Menu_ibfk_1` FOREIGN KEY (`Menu_TypeID`) REFERENCES `T_Menu_Type` (`Menu_TypeID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Menu`
--

LOCK TABLES `T_Menu` WRITE;
/*!40000 ALTER TABLE `T_Menu` DISABLE KEYS */;
INSERT INTO `T_Menu` VALUES (1,1,'',4000,1),(1,2,'ramen',3000,1),(1,3,'extrem spicy ramen',3500,0),(2,1,'pork cutlet',5500,1),(2,2,'soft tofu stew',5000,1),(2,3,'chicken fried rice',5000,1),(3,1,'orignal kimbab',2000,1),(3,2,'kimchi kimbab',2500,1),(3,3,'spicy kimbab',2500,1),(4,1,'coke',1000,1),(4,2,'sprit',1000,1),(4,3,'fanta',1000,1),(4,4,'water',1000,1);
/*!40000 ALTER TABLE `T_Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Menu_Type`
--

DROP TABLE IF EXISTS `T_Menu_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Menu_Type` (
  `Menu_TypeID` int(11) NOT NULL,
  `Menu_TypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Menu_TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Menu_Type`
--

LOCK TABLES `T_Menu_Type` WRITE;
/*!40000 ALTER TABLE `T_Menu_Type` DISABLE KEYS */;
INSERT INTO `T_Menu_Type` VALUES (1,'carb snack'),(2,'food'),(3,'kimbob'),(4,'drink');
/*!40000 ALTER TABLE `T_Menu_Type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Private_Type`
--

DROP TABLE IF EXISTS `T_Private_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Private_Type` (
  `Em_TypeID` int(11) NOT NULL,
  `Em_TypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Em_TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Private_Type`
--

LOCK TABLES `T_Private_Type` WRITE;
/*!40000 ALTER TABLE `T_Private_Type` DISABLE KEYS */;
INSERT INTO `T_Private_Type` VALUES (1,'hall'),(2,'kitchen'),(3,'buy');
/*!40000 ALTER TABLE `T_Private_Type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Recipe`
--

DROP TABLE IF EXISTS `T_Recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Recipe` (
  `Menu_Type` int(11) NOT NULL DEFAULT '0',
  `Menu_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Foods_ID` int(11) NOT NULL DEFAULT '0',
  `Foods_Count` int(11) DEFAULT '1',
  PRIMARY KEY (`Menu_Type`,`Menu_ID`,`Foods_ID`),
  KEY `Menu_ID` (`Menu_ID`),
  KEY `Menu_ID_2` (`Menu_ID`),
  KEY `L_ID` (`L_ID`),
  KEY `M_ID` (`M_ID`),
  KEY `Foods_ID` (`Foods_ID`),
  CONSTRAINT `T_Recipe_ibfk_2` FOREIGN KEY (`L_ID`) REFERENCES `T_Foods_L` (`L_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Recipe_ibfk_3` FOREIGN KEY (`M_ID`) REFERENCES `T_Foods_M` (`M_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Recipe_ibfk_4` FOREIGN KEY (`Foods_ID`) REFERENCES `T_Foods` (`Foods_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Recipe_ibfk_5` FOREIGN KEY (`Menu_ID`) REFERENCES `T_Menu` (`Menu_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `T_Recipe_ibfk_6` FOREIGN KEY (`Menu_Type`) REFERENCES `T_Menu` (`Menu_TypeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Recipe`
--

LOCK TABLES `T_Recipe` WRITE;
/*!40000 ALTER TABLE `T_Recipe` DISABLE KEYS */;
INSERT INTO `T_Recipe` VALUES (1,1,2,1,8,1),(1,1,2,1,9,1),(1,1,2,1,10,1),(1,1,2,2,13,1),(1,1,2,2,14,1),(1,2,1,2,4,1),(1,2,2,1,9,1),(1,2,2,2,15,1),(1,3,1,2,4,1),(1,3,2,1,9,1),(1,3,2,1,11,1),(1,3,2,2,15,1),(2,1,1,1,2,1),(2,1,1,1,3,1),(2,1,2,2,17,1),(2,2,1,2,4,1),(2,2,1,2,8,1),(2,2,2,1,9,1),(2,2,2,1,12,1),(2,2,2,2,18,1),(2,3,1,1,1,1),(2,3,1,1,3,1),(2,3,1,2,5,1),(2,3,2,1,12,1),(2,3,2,2,16,1),(3,1,1,1,3,1),(3,1,1,2,5,1),(3,1,1,2,6,1),(3,1,2,2,16,1),(3,1,2,2,19,1),(3,1,2,2,20,1),(3,2,1,1,3,1),(3,2,1,2,5,1),(3,2,1,2,6,1),(3,2,2,2,16,1),(3,2,2,2,19,1),(3,2,2,2,20,1),(3,2,2,2,21,1),(3,3,1,1,3,1),(3,3,1,2,5,1),(3,3,1,2,6,1),(3,3,1,2,7,1),(3,3,2,2,16,1),(3,3,2,2,19,1),(3,3,2,2,20,1),(4,1,2,3,22,1),(4,2,2,3,23,1),(4,3,2,3,24,1);
/*!40000 ALTER TABLE `T_Recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Res_table`
--

DROP TABLE IF EXISTS `T_Res_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Res_table` (
  `Table_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Persons` int(11) DEFAULT NULL,
  `Status` int(11) DEFAULT '0',
  PRIMARY KEY (`Table_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Res_table`
--

LOCK TABLES `T_Res_table` WRITE;
/*!40000 ALTER TABLE `T_Res_table` DISABLE KEYS */;
INSERT INTO `T_Res_table` VALUES (1,2,0),(2,2,0),(3,2,0),(4,2,0),(5,2,0),(6,4,0),(7,4,0),(8,4,0),(9,4,0),(10,4,0);
/*!40000 ALTER TABLE `T_Res_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Stock`
--

DROP TABLE IF EXISTS `T_Stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Stock` (
  `Stock_Date` varchar(8) NOT NULL DEFAULT '',
  `Bill_Idx` int(11) NOT NULL DEFAULT '0',
  `Foods_ID` int(11) NOT NULL DEFAULT '0',
  `L_ID` int(11) DEFAULT NULL,
  `M_ID` int(11) DEFAULT NULL,
  `Stock_Price` double DEFAULT NULL,
  `Stock_Count` int(11) DEFAULT NULL,
  PRIMARY KEY (`Stock_Date`,`Bill_Idx`,`Foods_ID`),
  KEY `M_ID` (`M_ID`),
  KEY `L_ID` (`L_ID`),
  KEY `Foods_ID` (`Foods_ID`),
  KEY `Bill_Idx` (`Bill_Idx`),
  CONSTRAINT `T_Stock_ibfk_1` FOREIGN KEY (`M_ID`) REFERENCES `T_Foods_M` (`M_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Stock_ibfk_2` FOREIGN KEY (`L_ID`) REFERENCES `T_Foods_L` (`L_ID`) ON UPDATE CASCADE,
  CONSTRAINT `T_Stock_ibfk_3` FOREIGN KEY (`Foods_ID`) REFERENCES `T_Foods` (`Foods_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Stock`
--

LOCK TABLES `T_Stock` WRITE;
/*!40000 ALTER TABLE `T_Stock` DISABLE KEYS */;
INSERT INTO `T_Stock` VALUES ('20170516',1,1,1,1,996,3),('20170516',1,2,1,1,1006.85185185185,5),('20170516',1,3,1,1,96.605504587156,13),('20170516',1,5,1,2,68.2941176470588,8),('20170516',1,6,1,2,72.8571428571429,5),('20170516',1,12,2,1,31.3194444444444,3),('20170516',1,16,2,2,509.882352941176,8),('20170516',1,17,2,2,97.962962962963,5),('20170516',1,19,2,2,195.142857142857,5),('20170516',1,20,2,2,54.0714285714286,5),('20170516',1,21,2,2,194.444444444444,5),('20170516',2,2,1,1,1006.85185185185,3),('20170516',2,3,1,1,96.605504587156,3),('20170516',2,4,1,2,68.1395348837209,4),('20170516',2,8,1,2,45.4166666666667,9),('20170516',2,9,2,1,27.9203539823009,9),('20170516',2,10,2,1,26.5789473684211,5),('20170516',2,12,2,1,31.3194444444444,4),('20170516',2,13,2,2,506.842105263158,5),('20170516',2,14,2,2,497.894736842105,5),('20170516',2,17,2,2,97.962962962963,3),('20170516',2,18,2,2,494.298245614035,4),('20170516',3,3,1,1,96.605504587156,5),('20170516',3,4,1,2,68.1395348837209,6),('20170516',3,6,1,2,72.8571428571429,5),('20170516',3,9,2,1,27.9203539823009,6),('20170516',3,15,2,2,499.491525423729,6),('20170516',3,16,2,2,509.882352941176,5),('20170516',3,19,2,2,195.142857142857,5),('20170516',3,20,2,2,54.0714285714286,5),('20170516',3,23,2,3,500,11),('20170517',1,4,1,2,68.1395348837209,6),('20170517',1,8,1,2,45.4166666666667,10),('20170517',1,9,2,1,27.9203539823009,10),('20170517',1,10,2,1,26.5789473684211,4),('20170517',1,12,2,1,31.3194444444444,4),('20170517',1,13,2,2,506.842105263158,4),('20170517',1,14,2,2,497.894736842105,4),('20170517',1,18,2,2,494.298245614035,6),('20170517',1,22,2,3,500,10),('20170517',2,2,1,1,1006.85185185185,3),('20170517',2,3,1,1,96.605504587156,8),('20170517',2,4,1,2,68.1395348837209,4),('20170517',2,5,1,2,68.2941176470588,5),('20170517',2,6,1,2,72.8571428571429,5),('20170517',2,9,2,1,27.9203539823009,4),('20170517',2,15,2,2,499.491525423729,4),('20170517',2,16,2,2,509.882352941176,5),('20170517',2,17,2,2,97.962962962963,3),('20170517',2,19,2,2,195.142857142857,5),('20170517',2,20,2,2,54.0714285714286,5),('20170517',2,21,2,2,194.444444444444,5),('20170517',3,4,1,2,68.1395348837209,12),('20170517',3,8,1,2,45.4166666666667,10),('20170517',3,9,2,1,27.9203539823009,16),('20170517',3,10,2,1,26.5789473684211,4),('20170517',3,12,2,1,31.3194444444444,6),('20170517',3,13,2,2,506.842105263158,4),('20170517',3,14,2,2,497.894736842105,4),('20170517',3,15,2,2,499.491525423729,6),('20170517',3,18,2,2,494.298245614035,6),('20170517',4,3,1,1,96.605504587156,8),('20170517',4,5,1,2,68.2941176470588,8),('20170517',4,6,1,2,72.8571428571429,8),('20170517',4,7,1,2,70.531914893617,5),('20170517',4,8,2,1,45.4166666666667,2),('20170517',4,9,2,1,27.9203539823009,2),('20170517',4,10,2,1,26.5789473684211,2),('20170517',4,13,2,2,506.842105263158,2),('20170517',4,14,2,2,497.894736842105,2),('20170517',4,16,2,2,509.882352941176,8),('20170517',4,19,2,2,195.142857142857,8),('20170517',4,20,2,2,54.0714285714286,8),('20170518',1,1,1,1,996,6),('20170518',1,3,1,1,96.605504587156,9),('20170518',1,4,1,2,68.1395348837209,2),('20170518',1,5,1,2,68.2941176470588,9),('20170518',1,6,1,2,72.8571428571429,3),('20170518',1,7,1,2,70.531914893617,3),('20170518',1,9,2,1,27.9203539823009,2),('20170518',1,12,2,1,31.3194444444444,6),('20170518',1,15,2,2,499.491525423729,2),('20170518',1,16,2,2,509.882352941176,9),('20170518',1,19,2,2,195.142857142857,3),('20170518',1,20,2,2,54.0714285714286,3),('20170518',2,2,1,1,1006.85185185185,5),('20170518',2,3,1,1,96.605504587156,10),('20170518',2,5,1,2,68.2941176470588,5),('20170518',2,6,1,2,72.8571428571429,5),('20170518',2,8,2,1,45.4166666666667,3),('20170518',2,9,2,1,27.9203539823009,3),('20170518',2,10,2,1,26.5789473684211,3),('20170518',2,13,2,2,506.842105263158,3),('20170518',2,14,2,2,497.894736842105,3),('20170518',2,16,2,2,509.882352941176,5),('20170518',2,17,2,2,97.962962962963,5),('20170518',2,19,2,2,195.142857142857,5),('20170518',2,20,2,2,54.0714285714286,5),('20170518',2,21,2,2,194.444444444444,5),('20170518',3,1,1,1,996,4),('20170518',3,2,1,1,1006.85185185185,6),('20170518',3,3,1,1,96.605504587156,10),('20170518',3,4,1,2,68.1395348837209,6),('20170518',3,5,1,2,68.2941176470588,4),('20170518',3,8,1,2,45.4166666666667,6),('20170518',3,9,2,1,27.9203539823009,6),('20170518',3,12,2,1,31.3194444444444,10),('20170518',3,16,2,2,509.882352941176,4),('20170518',3,17,2,2,97.962962962963,6),('20170518',3,18,2,2,494.298245614035,6),('20170519',1,1,1,1,996,2),('20170519',1,3,1,1,96.605504587156,7),('20170519',1,4,1,2,68.1395348837209,6),('20170519',1,5,1,2,68.2941176470588,7),('20170519',1,6,1,2,72.8571428571429,5),('20170519',1,7,1,2,70.531914893617,5),('20170519',1,9,2,1,27.9203539823009,6),('20170519',1,12,2,1,31.3194444444444,2),('20170519',1,15,2,2,499.491525423729,6),('20170519',1,16,2,2,509.882352941176,7),('20170519',1,19,2,2,195.142857142857,5),('20170519',1,20,2,2,54.0714285714286,5),('20170519',2,2,1,1,1006.85185185185,2),('20170519',2,3,1,1,96.605504587156,2),('20170519',2,4,1,2,68.1395348837209,5),('20170519',2,8,1,2,45.4166666666667,10),('20170519',2,9,2,1,27.9203539823009,10),('20170519',2,10,2,1,26.5789473684211,5),('20170519',2,12,2,1,31.3194444444444,5),('20170519',2,13,2,2,506.842105263158,5),('20170519',2,14,2,2,497.894736842105,5),('20170519',2,17,2,2,97.962962962963,2),('20170519',2,18,2,2,494.298245614035,5),('20170519',3,3,1,1,96.605504587156,4),('20170519',3,4,1,2,68.1395348837209,5),('20170519',3,5,1,2,68.2941176470588,4),('20170519',3,6,1,2,72.8571428571429,4),('20170519',3,7,1,2,70.531914893617,4),('20170519',3,8,2,1,45.4166666666667,4),('20170519',3,9,2,1,27.9203539823009,9),('20170519',3,10,2,1,26.5789473684211,4),('20170519',3,13,2,2,506.842105263158,4),('20170519',3,14,2,2,497.894736842105,4),('20170519',3,15,2,2,499.491525423729,5),('20170519',3,16,2,2,509.882352941176,4),('20170519',3,19,2,2,195.142857142857,4),('20170519',3,20,2,2,54.0714285714286,4);
/*!40000 ALTER TABLE `T_Stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Trader`
--

DROP TABLE IF EXISTS `T_Trader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_Trader` (
  `Trader_ID` int(11) NOT NULL,
  `Trader_Name` varchar(45) DEFAULT NULL,
  `HP` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Trader_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Trader`
--

LOCK TABLES `T_Trader` WRITE;
/*!40000 ALTER TABLE `T_Trader` DISABLE KEYS */;
INSERT INTO `T_Trader` VALUES (1,'sinan','01022222222'),(2,'hwanhwa','01033333333'),(3,'samsung','01077777777');
/*!40000 ALTER TABLE `T_Trader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Theater`
--

DROP TABLE IF EXISTS `Theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Theater` (
  `thea_id` int(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `addr` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`thea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Theater`
--

LOCK TABLES `Theater` WRITE;
/*!40000 ALTER TABLE `Theater` DISABLE KEYS */;
INSERT INTO `Theater` VALUES (1,'ë¡¯ë°','ìž ì‹¤'),(2,'ë©”ê°€','ê°•ë‚¨'),(3,'ëŒ€í•œ','ìž ì‹¤');
/*!40000 ALTER TABLE `Theater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bid` int(11) NOT NULL,
  `title` varchar(30) DEFAULT NULL,
  `publisher` varchar(30) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'database','mac',30000),(2,'c','hanbit',20000),(3,'c++','mac',25000),(4,'java','hanbit',30000),(5,'network','hanbit',40000);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `cid` int(11) NOT NULL,
  `cname` varchar(30) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `pid` (`pid`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `professor` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'ìžë£Œêµ¬ì¡°',50000,2),(2,'ë°ì´í„°ë² ì´ìŠ¤',105000,3),(3,'ì•ˆë“œë¡œì´ë“œ',65000,1),(4,'íšŒë¡œë§ì´ë¡ ',70000,5),(5,'í•©ì°½',55000,5),(6,'ìžë°”',85000,4),(7,'ì»´í“¨í„°êµ¬ì¡°',45000,3),(8,'ìš´ì˜ì²´ì œ',90000,3),(9,'ìš´ì˜ì²´ì œ',90000,1),(10,'ì»´í“¨í„°ë„¤íŠ¸ì›Œí¬',60000,1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cust`
--

DROP TABLE IF EXISTS `cust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cust` (
  `cid` int(11) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `addr` varchar(20) DEFAULT NULL,
  `hp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cust`
--

LOCK TABLES `cust` WRITE;
/*!40000 ALTER TABLE `cust` DISABLE KEYS */;
INSERT INTO `cust` VALUES (1,'hong','anyang','010-444-3434'),(2,'kang','seoul','010-354-3434'),(3,'lee','anyang','010-243-5644'),(4,'han','seoul','010-465-4545'),(5,'park','suwon',NULL);
/*!40000 ALTER TABLE `cust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depart`
--

DROP TABLE IF EXISTS `depart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `depart` (
  `did` int(11) NOT NULL,
  `dname` varchar(30) DEFAULT NULL,
  `hp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depart`
--

LOCK TABLES `depart` WRITE;
/*!40000 ALTER TABLE `depart` DISABLE KEYS */;
INSERT INTO `depart` VALUES (1,'ì»´í“¨í„°ê³µí•™ê³¼','010-1111-1234'),(2,'ì •ë³´í†µì‹ ê³µí•™ê³¼','010-2222-1234'),(3,'ì˜ì–´ì˜ë¬¸í•™ê³¼','010-3333-1234'),(4,'ìˆ˜í•™êµìœ¡ê³¼','010-4444-1234'),(5,'ìŒì•…í•™ë¶€','010-5555-1234'),(6,'êµ­ì–´êµ­ë¬¸í•™ê³¼','010-6666-1234');
/*!40000 ALTER TABLE `depart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myBook`
--

DROP TABLE IF EXISTS `myBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `myBook` (
  `bid` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myBook`
--

LOCK TABLES `myBook` WRITE;
/*!40000 ALTER TABLE `myBook` DISABLE KEYS */;
INSERT INTO `myBook` VALUES (1,10000),(2,20000),(3,NULL);
/*!40000 ALTER TABLE `myBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newBook`
--

DROP TABLE IF EXISTS `newBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newBook` (
  `bid` int(11) NOT NULL,
  `bookName` varchar(30) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newBook`
--

LOCK TABLES `newBook` WRITE;
/*!40000 ALTER TABLE `newBook` DISABLE KEYS */;
INSERT INTO `newBook` VALUES (1,'JAVA','mac',35000),(2,'database','hanbit',25000),(3,'datastructure','mac',35000),(4,'C#','infinity',39000),(5,'C++','infinity',31000),(6,'C','mac',NULL);
/*!40000 ALTER TABLE `newBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newCust`
--

DROP TABLE IF EXISTS `newCust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newCust` (
  `cid` int(11) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `addr` varchar(20) DEFAULT NULL,
  `hp` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newCust`
--

LOCK TABLES `newCust` WRITE;
/*!40000 ALTER TABLE `newCust` DISABLE KEYS */;
INSERT INTO `newCust` VALUES (1,'hong','anyang','010-444-3434'),(2,'kang','seoul','010-354-3434'),(3,'lee','anyang','010-243-5644'),(4,'han','seoul','010-465-4545');
/*!40000 ALTER TABLE `newCust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `oid` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `odate` date DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,'2015-03-15'),(2,1,2,'2015-03-15'),(3,2,2,'2015-03-16'),(4,2,3,'2015-03-16'),(5,3,5,'2015-04-01');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders2`
--

DROP TABLE IF EXISTS `orders2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders2` (
  `oid` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `odate` date DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `cid` (`cid`),
  KEY `bid` (`bid`),
  CONSTRAINT `orders2_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `cust` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders2_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders2`
--

LOCK TABLES `orders2` WRITE;
/*!40000 ALTER TABLE `orders2` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `pid` int(11) NOT NULL,
  `pname` varchar(15) DEFAULT NULL,
  `did` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `did` (`did`),
  CONSTRAINT `professor_ibfk_1` FOREIGN KEY (`did`) REFERENCES `depart` (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'ì¶”ì‹ ìˆ˜',1),(2,'ê¹€ì—°ì•„',2),(3,'í™ê¸¸ë™',3),(4,'ê¹€ì˜í¬',4),(5,'ìž¥ë¯¸ëž€',5);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regist`
--

DROP TABLE IF EXISTS `regist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regist` (
  `rid` int(11) NOT NULL,
  `sid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `sid` (`sid`),
  KEY `cid` (`cid`),
  CONSTRAINT `regist_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `regist_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regist`
--

LOCK TABLES `regist` WRITE;
/*!40000 ALTER TABLE `regist` DISABLE KEYS */;
INSERT INTO `regist` VALUES (1,1,2),(2,3,5),(3,4,2),(4,3,1),(5,2,1),(6,5,1),(7,2,4),(8,1,3),(9,5,2),(10,1,2),(11,6,8),(12,7,5),(13,4,5),(14,8,9),(15,7,6),(16,8,9),(17,8,7),(18,6,4),(19,7,7),(20,5,4);
/*!40000 ALTER TABLE `regist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sid` int(11) NOT NULL,
  `sname` varchar(15) DEFAULT NULL,
  `did` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `did` (`did`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`did`) REFERENCES `depart` (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'êµ­ì •ì›',1),(2,'í•œì´ìŠ¬',5),(3,'ë°•ë¬¸ìˆ˜',4),(4,'ë§ˆë™ì„',4),(5,'ìµœë‚˜ë‹ˆ',3),(6,'ê¹€ë‘˜ë¦¬',2),(7,'êµ­ê¸°ì›',4),(8,'ë°˜ì§€ì•„',3),(9,'êµ­ì˜ìˆ˜',4),(10,'ì¶”í•­ì•„',5);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summer`
--

DROP TABLE IF EXISTS `summer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summer` (
  `sid` int(11) DEFAULT NULL,
  `class` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summer`
--

LOCK TABLES `summer` WRITE;
/*!40000 ALTER TABLE `summer` DISABLE KEYS */;
INSERT INTO `summer` VALUES (100,'fortran',20000),(150,'pascal',15000),(200,'c',10000),(250,'fortran',30000),(NULL,'java',20000);
/*!40000 ALTER TABLE `summer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summer_class`
--

DROP TABLE IF EXISTS `summer_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summer_class` (
  `class` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summer_class`
--

LOCK TABLES `summer_class` WRITE;
/*!40000 ALTER TABLE `summer_class` DISABLE KEYS */;
INSERT INTO `summer_class` VALUES ('fortran',20000),('pascal',15000),('c',10000),('java',30000);
/*!40000 ALTER TABLE `summer_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summer_enroll`
--

DROP TABLE IF EXISTS `summer_enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summer_enroll` (
  `sid` int(11) DEFAULT NULL,
  `class` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summer_enroll`
--

LOCK TABLES `summer_enroll` WRITE;
/*!40000 ALTER TABLE `summer_enroll` DISABLE KEYS */;
INSERT INTO `summer_enroll` VALUES (100,'fortran'),(150,'pascal'),(250,'fortran');
/*!40000 ALTER TABLE `summer_enroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `vw_AvgBuyingPrice`
--

DROP TABLE IF EXISTS `vw_AvgBuyingPrice`;
/*!50001 DROP VIEW IF EXISTS `vw_AvgBuyingPrice`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_AvgBuyingPrice` (
 `Foods_ID` tinyint NOT NULL,
  `Total_BuyingPrice` tinyint NOT NULL,
  `Total_BuyingCount` tinyint NOT NULL,
  `Stock_Price` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vw_order_info`
--

DROP TABLE IF EXISTS `vw_order_info`;
/*!50001 DROP VIEW IF EXISTS `vw_order_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_order_info` (
 `cname` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `price` tinyint NOT NULL,
  `odate` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vw_remained_Foods`
--

DROP TABLE IF EXISTS `vw_remained_Foods`;
/*!50001 DROP VIEW IF EXISTS `vw_remained_Foods`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_remained_Foods` (
 `Foods_ID` tinyint NOT NULL,
  `remained_Foods_Amount` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vw_tot_menu_count`
--

DROP TABLE IF EXISTS `vw_tot_menu_count`;
/*!50001 DROP VIEW IF EXISTS `vw_tot_menu_count`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_tot_menu_count` (
 `Menu_Type` tinyint NOT NULL,
  `Menu_ID` tinyint NOT NULL,
  `tot_Menu_Count` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vw_usedFoods`
--

DROP TABLE IF EXISTS `vw_usedFoods`;
/*!50001 DROP VIEW IF EXISTS `vw_usedFoods`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_usedFoods` (
 `Foods_ID` tinyint NOT NULL,
  `usedFoods_Amount` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vw_AvgBuyingPrice`
--

/*!50001 DROP TABLE IF EXISTS `vw_AvgBuyingPrice`*/;
/*!50001 DROP VIEW IF EXISTS `vw_AvgBuyingPrice`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`db1505`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_AvgBuyingPrice` AS select `T_Buying`.`Foods_ID` AS `Foods_ID`,sum(`T_Buying`.`Buying_Price`) AS `Total_BuyingPrice`,sum(`T_Buying`.`Buying_Count`) AS `Total_BuyingCount`,(sum(`T_Buying`.`Buying_Price`) / sum(`T_Buying`.`Buying_Count`)) AS `Stock_Price` from `T_Buying` group by `T_Buying`.`Foods_ID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_order_info`
--

/*!50001 DROP TABLE IF EXISTS `vw_order_info`*/;
/*!50001 DROP VIEW IF EXISTS `vw_order_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`db1505`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_order_info` AS select `cust`.`cname` AS `cname`,`book`.`title` AS `title`,`book`.`price` AS `price`,`orders`.`odate` AS `odate` from ((`cust` join `orders`) join `book`) where ((`cust`.`cid` = `orders`.`cid`) and (`book`.`bid` = `orders`.`bid`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_remained_Foods`
--

/*!50001 DROP TABLE IF EXISTS `vw_remained_Foods`*/;
/*!50001 DROP VIEW IF EXISTS `vw_remained_Foods`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`db1505`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_remained_Foods` AS select `vw_AvgBuyingPrice`.`Foods_ID` AS `Foods_ID`,(`vw_AvgBuyingPrice`.`Total_BuyingCount` - `vw_usedFoods`.`usedFoods_Amount`) AS `remained_Foods_Amount` from (`vw_usedFoods` join `vw_AvgBuyingPrice` on((`vw_usedFoods`.`Foods_ID` = `vw_AvgBuyingPrice`.`Foods_ID`))) where (`vw_AvgBuyingPrice`.`Foods_ID` = `vw_usedFoods`.`Foods_ID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_tot_menu_count`
--

/*!50001 DROP TABLE IF EXISTS `vw_tot_menu_count`*/;
/*!50001 DROP VIEW IF EXISTS `vw_tot_menu_count`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`db1505`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_tot_menu_count` AS select `T_Bill_Item`.`Menu_Type` AS `Menu_Type`,`T_Bill_Item`.`Menu_ID` AS `Menu_ID`,sum(`T_Bill_Item`.`Menu_Count`) AS `tot_Menu_Count` from `T_Bill_Item` group by `T_Bill_Item`.`Menu_Type`,`T_Bill_Item`.`Menu_ID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_usedFoods`
--

/*!50001 DROP TABLE IF EXISTS `vw_usedFoods`*/;
/*!50001 DROP VIEW IF EXISTS `vw_usedFoods`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`db1505`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_usedFoods` AS select `T_Recipe`.`Foods_ID` AS `Foods_ID`,sum(`T_Bill_Item`.`Menu_Count`) AS `usedFoods_Amount` from (`T_Recipe` join `T_Bill_Item` on(((`T_Recipe`.`Menu_Type` = `T_Bill_Item`.`Menu_Type`) and (`T_Recipe`.`Menu_ID` = `T_Bill_Item`.`Menu_ID`)))) where ((`T_Bill_Item`.`Menu_Type` = `T_Recipe`.`Menu_Type`) and (`T_Bill_Item`.`Menu_ID` = `T_Recipe`.`Menu_ID`)) group by `T_Recipe`.`Foods_ID` order by `T_Recipe`.`Foods_ID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-16 13:03:09
