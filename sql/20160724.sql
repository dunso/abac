-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 139.129.61.112    Database: demo
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `File`
--

DROP TABLE IF EXISTS `File`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `File` (
  `file_id` varchar(255) NOT NULL,
  `cloud_path` varchar(255) DEFAULT NULL,
  `exp_name` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `folder_id` varchar(255) DEFAULT NULL,
  `integrity_type` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `share_type` varchar(255) DEFAULT NULL,
  `upload_time` varchar(255) DEFAULT NULL,
  `folderId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `File`
--

LOCK TABLES `File` WRITE;
/*!40000 ALTER TABLE `File` DISABLE KEYS */;
INSERT INTO `File` VALUES ('11469281122636846','','pdf','06008724',NULL,'0','1001','0','2016-07-23','014692803741270'),('11469281477637803','','pdf','06215608',NULL,'0','1001','0','2016-07-23','014692803741270'),('123','123','ttt','123',NULL,'0','123','0','123','014635820080380');
/*!40000 ALTER TABLE `File` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `access_control`
--

DROP TABLE IF EXISTS `access_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_control` (
  `access_control_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`access_control_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_control`
--

LOCK TABLES `access_control` WRITE;
/*!40000 ALTER TABLE `access_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `access_control_strategy`
--

DROP TABLE IF EXISTS `access_control_strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_control_strategy` (
  `access_control_id` int(11) NOT NULL,
  `strategy_id` int(11) NOT NULL,
  PRIMARY KEY (`access_control_id`,`strategy_id`),
  KEY `FK_9x06udouw28bss9lt4c6gn603` (`strategy_id`),
  KEY `FK_m94bt7qu0v5g6v9kpa2oocs0n` (`access_control_id`),
  CONSTRAINT `FK_m94bt7qu0v5g6v9kpa2oocs0n` FOREIGN KEY (`access_control_id`) REFERENCES `access_control` (`access_control_id`),
  CONSTRAINT `FK_9x06udouw28bss9lt4c6gn603` FOREIGN KEY (`strategy_id`) REFERENCES `strategy` (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_control_strategy`
--

LOCK TABLES `access_control_strategy` WRITE;
/*!40000 ALTER TABLE `access_control_strategy` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_control_strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businessgroup`
--

DROP TABLE IF EXISTS `businessgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessgroup` (
  `id` varchar(255) NOT NULL,
  `admin_attrs` varchar(255) DEFAULT NULL,
  `admin_id` varchar(255) DEFAULT NULL,
  `ctime` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `storage_id` varchar(128) DEFAULT NULL,
  `u_attrs` varchar(255) DEFAULT NULL,
  `uids` varchar(255) DEFAULT NULL,
  `utime` varchar(255) DEFAULT NULL,
  `adminAttrs` varchar(255) DEFAULT NULL,
  `adminId` varchar(255) DEFAULT NULL,
  `storageId` varchar(128) DEFAULT NULL,
  `uAttrs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businessgroup`
--

LOCK TABLES `businessgroup` WRITE;
/*!40000 ALTER TABLE `businessgroup` DISABLE KEYS */;
INSERT INTO `businessgroup` VALUES ('8a7b31e55617e0a2015617ee8e640001','type=manage;department=项目管理组;project=大数据安全','2001','2016-07-23 21:25:25','大数据安全','8a7b31e55617e0a2015617ee8e5d0000','type=professor;project=大数据安全||type=manage;department=评审组;project=大数据安全||type=manage;department=立项组;project=大数据安全','1001,1002,1003,1004,2003,2002',NULL,NULL,NULL,NULL,NULL),('8a7b31e55617e0a2015617eeed8f0003','type=manage;department=专家管理组;duty=组长','2005','2016-07-23 21:25:49','专家管理','8a7b31e55617e0a2015617eeed8a0002','type=professor||type=manage;department=专家管理组;duty=组长||type=manage;department=专家管理组;duty=工作人员','1001,1002,1003,1004,1005,1006,1007,1008,1009,2005,2004',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `businessgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column_config`
--

DROP TABLE IF EXISTS `column_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `column_config` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(45) DEFAULT NULL,
  `column_display` varchar(45) DEFAULT NULL,
  `column_code` varchar(45) DEFAULT NULL,
  `column_name` varchar(45) DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  `flag_des` varchar(45) DEFAULT NULL,
  `is_valid` varchar(45) DEFAULT '1',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column_config`
--

LOCK TABLES `column_config` WRITE;
/*!40000 ALTER TABLE `column_config` DISABLE KEYS */;
INSERT INTO `column_config` VALUES (1,'user','pid','user_pid','pid',1,NULL,'1'),(2,'user','userId','userID','user_id',1,NULL,'1'),(3,'user','groups','groups','groups',1,NULL,'1'),(4,'user','password','password','password',1,NULL,'1'),(5,'user','role','roleNum','role',1,NULL,'1'),(6,'user','storageId','storageID','storage_id',1,NULL,'1'),(7,'user','type','type','type',1,NULL,'1'),(9,'user','userName','username','username',1,NULL,'1'),(10,'table1','table1Id','table1id','table1id',1,NULL,'1'),(11,'table1','groupId','column1','column1',1,NULL,'1'),(12,'table1','fileFolderId','column2','column2',1,NULL,'1'),(13,'table1','privilege','column3','column3',1,NULL,'1'),(14,'table2','table2id','table2id','table2id',0,'非权限','1'),(15,'table2','attrExpress','column1','column1',0,'非权限','1'),(16,'table2','createFloder','column2','column2',1,'权限','1'),(17,'table2','deleteFloder','column3','column3',1,'权限','1'),(18,'table2','renameFloder','column4','column4',1,'权限','1'),(19,'table2','moveFloder','column5','column5',1,'权限','1'),(20,'table2','uploadFile','column6','column6',1,'权限','1'),(21,'table2','downloadFile','column7','column7',1,'权限','1'),(22,'table2','deleteFile','column8','column8',1,'权限','1'),(23,'table2','renameFile','column9','column9',1,'权限','1'),(24,'table2','moveFile','column10','column10',1,'权限','1'),(25,'table2','operateWays','column11','column11',1,'权限','1'),(26,'table2','integrity','column12','column12',1,'权限','1'),(27,'teacher','id','id','teacher_id',1,NULL,'1'),(28,'teacher','age','age','age',1,NULL,'1'),(29,'teacher','project','courses','courses',1,NULL,'1'),(30,'teacher','department','department','department',1,NULL,'1'),(31,'teacher','duty','duty','duty',1,NULL,'1'),(32,'teacher','staffName','name','teachet_name',1,NULL,'1'),(33,'teacher','studyGroup','studyGroup','study_group',1,NULL,'1'),(34,'teacher','title','title','title',1,NULL,'1'),(35,'student','id','id','student_id',1,NULL,'1'),(36,'student','title','academy','academy',1,NULL,'1'),(37,'student','age','age','age',1,NULL,'1'),(38,'student','project','courses','courses',1,NULL,'1'),(39,'student','department','department','department',1,NULL,'1'),(40,'student','expertName','name','student_name',1,NULL,'1'),(41,'student','studyGroup','studygroup','study_group',1,NULL,'1'),(42,'student','teacherId','teacherID','teacher_id',0,NULL,'1'),(43,'table1','table2id','table2id','table2id',0,NULL,'1'),(44,'user','token','token','token',0,NULL,'1');
/*!40000 ALTER TABLE `column_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `file_id` varchar(255) NOT NULL,
  `cloud_path` varchar(255) DEFAULT NULL,
  `exp_name` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `folder_id` varchar(255) DEFAULT NULL,
  `integrity_type` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `share_type` varchar(255) DEFAULT NULL,
  `upload_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `folder_id` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `father_id` varchar(255) DEFAULT NULL,
  `integrity_type` varchar(255) DEFAULT NULL,
  `folder_name` varchar(255) DEFAULT NULL,
  `share_type` varchar(255) DEFAULT NULL,
  `storage_id` varchar(255) DEFAULT NULL,
  `storage_type` varchar(255) DEFAULT NULL,
  `whether_root` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`folder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES ('014634824062610','2016-05-17 18:53:26',NULL,'0','0','jerry','0','0','0','0'),('014634824656000','2016-05-17 18:54:25',NULL,'0','0','tom','0','0','0','0'),('014634825088290','2016-05-17 18:55:08',NULL,'0','0','sally','0','0','0','0'),('014634825316260','2016-05-17 18:55:31',NULL,'1','0','java','0','0','0','0'),('014634825502820','2016-05-17 18:55:50',NULL,'1','0','hadoop','0','0','0','0'),('014634826261380','2016-05-17 18:57:06',NULL,'1','0','tom','0','0','0','0'),('014692728854840','2016-07-23 19:21:25','1000','3','0','技术专家1','0','0','0','0'),('014692728854960','2016-07-23 19:21:25','1000','3','0','技术专家2','0','0','0','0'),('014692728854980','2016-07-23 19:21:25','1000','3','0','技术专家3','0','0','0','0'),('014692728855000','2016-07-23 19:21:25','1000','3','0','技术专家4','0','0','0','0'),('014692728855020','2016-07-23 19:21:25','1000','3','0','技术专家5','0','0','0','0'),('014692728855030','2016-07-23 19:21:25','1000','3','0','技术专家6','0','0','0','0'),('014692728855050','2016-07-23 19:21:25','1000','3','0','技术专家7','0','0','0','0'),('014692728855070','2016-07-23 19:21:25','1000','3','0','技术专家8','0','0','0','0'),('014692728855080','2016-07-23 19:21:25','1000','3','0','技术专家9','0','0','0','0'),('014692729214660','2016-07-23 19:22:01','1000','3','0','技术管理人员1','0','0','0','0'),('014692729214690','2016-07-23 19:22:01','1000','3','0','技术管理人员2','0','0','0','0'),('014692729214720','2016-07-23 19:22:01','1000','3','0','技术管理人员3','0','0','0','0'),('014692729214740','2016-07-23 19:22:01','1000','3','0','技术管理人员4','0','0','0','0'),('014692729214830','2016-07-23 19:22:01','1000','3','0','技术管理人员5','0','0','0','0'),('014692730566030','2016-07-23 19:24:16',NULL,'1','0','专家管理','0','0','0','0'),('014692796913320','2016-07-23 21:14:51','1001','014692728854840','0','ggg','0','0','0','0'),('014692803252040','2016-07-23 21:25:25',NULL,'1','0','大数据安全','0','0','0','0'),('014692803495690','2016-07-23 21:25:49',NULL,'1','0','专家管理','0','0','0','0'),('014692803741270','2016-07-23 21:26:14','2001','014692803252040','0','ooo','0','0','0','0');
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `free_group`
--

DROP TABLE IF EXISTS `free_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `free_group` (
  `fg_id` varchar(255) NOT NULL,
  `fg_manager` varchar(255) DEFAULT NULL,
  `fg_name` varchar(255) DEFAULT NULL,
  `fg_userlist` varchar(255) DEFAULT NULL,
  `storgeid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `free_group`
--

LOCK TABLES `free_group` WRITE;
/*!40000 ALTER TABLE `free_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `free_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `freegroup_file`
--

DROP TABLE IF EXISTS `freegroup_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `freegroup_file` (
  `fgfile_id` varchar(255) NOT NULL,
  `file_id` varchar(255) DEFAULT NULL,
  `folder_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fgfile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `freegroup_file`
--

LOCK TABLES `freegroup_file` WRITE;
/*!40000 ALTER TABLE `freegroup_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `freegroup_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `mess_id` varchar(255) NOT NULL,
  `fg_id` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mess_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space`
--

DROP TABLE IF EXISTS `space`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space` (
  `id` varchar(255) NOT NULL,
  `root_directory` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space`
--

LOCK TABLES `space` WRITE;
/*!40000 ALTER TABLE `space` DISABLE KEYS */;
INSERT INTO `space` VALUES ('4028810a54be58200154be5955aa0000','014634824062610','jerry',23),('4028810a54be58200154be5a3d450002','014634824656000','tom',23),('4028810a54be58200154be5ae6220004','014634825088290','sally',23),('4028810a54be58200154be5b3f320006','014634825316260','java',123),('4028810a54be58200154be5b88110008','014634825502820','hadoop',666),('4028810a54be58200154be5cb05e000a','014634826261380','tom',1000),('8a7b31e5560e605d0156177d08f70000','014692728854840','技术专家1',23),('8a7b31e5560e605d0156177d08fa0001','014692728854960','技术专家2',23),('8a7b31e5560e605d0156177d08fb0002','014692728854980','技术专家3',23),('8a7b31e5560e605d0156177d08fd0003','014692728855000','技术专家4',23),('8a7b31e5560e605d0156177d08ff0004','014692728855020','技术专家5',23),('8a7b31e5560e605d0156177d09000005','014692728855030','技术专家6',23),('8a7b31e5560e605d0156177d09020006','014692728855050','技术专家7',23),('8a7b31e5560e605d0156177d09040007','014692728855070','技术专家8',23),('8a7b31e5560e605d0156177d09050008','014692728855080','技术专家9',23),('8a7b31e5560e605d0156177d957c0012','014692729214660','技术管理人员1',23),('8a7b31e5560e605d0156177d957e0013','014692729214690','技术管理人员2',23),('8a7b31e5560e605d0156177d95810014','014692729214720','技术管理人员3',23),('8a7b31e5560e605d0156177d958a0015','014692729214740','技术管理人员4',23),('8a7b31e5560e605d0156177d958c0016','014692729214830','技术管理人员5',23),('8a7b31e5560e605d0156177eeb10001c','014692730089040','大数据安全',500),('8a7b31e5560e605d0156177fa56b001e','014692730566030','专家管理',500),('8a7b31e55617e0a2015617ee8e5d0000','014692803252040','大数据安全',500),('8a7b31e55617e0a2015617eeed8a0002','014692803495690','专家管理',500);
/*!40000 ALTER TABLE `space` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy`
--

DROP TABLE IF EXISTS `strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategy` (
  `strategy_id` int(11) NOT NULL AUTO_INCREMENT,
  `allow_create_floder` int(11) DEFAULT NULL,
  `allow_delete_file` int(11) DEFAULT NULL,
  `allow_delete_floder` int(11) DEFAULT NULL,
  `allow_download_file` int(11) DEFAULT NULL,
  `allow_rename_floder` int(11) DEFAULT NULL,
  `allow_upload_file` int(11) DEFAULT NULL,
  `integrity` int(11) DEFAULT NULL,
  `operate_ways` int(11) DEFAULT NULL,
  `property_expression` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy`
--

LOCK TABLES `strategy` WRITE;
/*!40000 ALTER TABLE `strategy` DISABLE KEYS */;
/*!40000 ALTER TABLE `strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` varchar(255) NOT NULL,
  `academy` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `courses` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `study_group` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('1001','技术菜鸟',25,'大数据安全','百度','技术专家1','大数据',NULL),('1002','技术主干',25,'大数据安全','百度','技术专家2','大数据',NULL),('1003','技术精英',25,'大数据安全','百度','技术专家3','大数据',NULL),('1004','技术菜鸟',26,'大数据安全','腾讯','技术专家4','大数据',NULL),('1005','技术主干',25,'云安全','腾讯','技术专家5','大数据',NULL),('1006','技术精英',25,'云安全','腾讯','技术专家6','大数据',NULL),('1007','技术菜鸟',23,'云安全','阿里','技术专家7','数据挖掘',NULL),('1008','技术主干',25,'云安全','阿里','技术专家8','数据挖掘',NULL),('1009','技术精英',24,'云安全','阿里','技术专家9','数据挖掘',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table1`
--

DROP TABLE IF EXISTS `table1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table1` (
  `table1id` int(11) NOT NULL AUTO_INCREMENT,
  `column1` int(11) DEFAULT NULL,
  `column2` varchar(255) DEFAULT NULL,
  `column3` varchar(255) DEFAULT NULL,
  `column4` varchar(255) DEFAULT NULL,
  `table2id` int(11) DEFAULT NULL,
  PRIMARY KEY (`table1id`),
  UNIQUE KEY `UK_12ecve3mxtlnutw3hu71dg6t3` (`table2id`),
  KEY `FK_12ecve3mxtlnutw3hu71dg6t3` (`table2id`),
  CONSTRAINT `FK_12ecve3mxtlnutw3hu71dg6t3` FOREIGN KEY (`table2id`) REFERENCES `table2` (`table2id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table1`
--

LOCK TABLES `table1` WRITE;
/*!40000 ALTER TABLE `table1` DISABLE KEYS */;
INSERT INTO `table1` VALUES (24,1,'014634826261380d',NULL,NULL,63),(32,1,'014692803741270d',NULL,NULL,71);
/*!40000 ALTER TABLE `table1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table2`
--

DROP TABLE IF EXISTS `table2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table2` (
  `table2id` int(11) NOT NULL AUTO_INCREMENT,
  `column1` varchar(255) DEFAULT NULL,
  `column10` varchar(255) DEFAULT NULL,
  `column11` varchar(255) DEFAULT NULL,
  `column12` varchar(255) DEFAULT NULL,
  `column2` varchar(255) DEFAULT NULL,
  `column3` varchar(255) DEFAULT NULL,
  `column4` varchar(255) DEFAULT NULL,
  `column5` varchar(255) DEFAULT NULL,
  `column6` varchar(255) DEFAULT NULL,
  `column7` varchar(255) DEFAULT NULL,
  `column8` varchar(255) DEFAULT NULL,
  `column9` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`table2id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table2`
--

LOCK TABLES `table2` WRITE;
/*!40000 ALTER TABLE `table2` DISABLE KEYS */;
INSERT INTO `table2` VALUES (63,'#projectId=\'1\'&(age>\'30\' $ ty pe=\'0\')$!userID = \'1\'#','0','0','0','1','0','0','1','0','0','1','0'),(71,'#userId=\'1001\'#','null','0','1','1','0','1','null','1','1','1','null');
/*!40000 ALTER TABLE `table2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_config`
--

DROP TABLE IF EXISTS `table_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL,
  `type_des` varchar(45) DEFAULT NULL,
  `db_display` varchar(45) DEFAULT NULL,
  `db_code` varchar(45) DEFAULT NULL,
  `db_name` varchar(45) DEFAULT NULL,
  `table_display` varchar(45) DEFAULT NULL,
  `table_code` varchar(45) DEFAULT NULL,
  `table_name` varchar(45) DEFAULT NULL,
  `is_valid` varchar(45) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_config`
--

LOCK TABLES `table_config` WRITE;
/*!40000 ALTER TABLE `table_config` DISABLE KEYS */;
INSERT INTO `table_config` VALUES (1,'1','用户基本信息表','demo','demo','demo','user','user','user','1'),(2,'2','访问控制表','demo','demo','demo','abac','table1','table1','1'),(3,'3','策略表','demo','demo','demo','policy','table2','table2','1'),(4,'4','老师表','demo','demo','demo','teacher','teacher','teacher','1'),(5,'4','学生表','demo','demo','demo','student','student','student','1'),(6,'5','表配置','demo','demo','demo','tableConfig','tableConfig','table_config','1'),(7,'6','列配置','demo','demo','demo','columnConfig','columnConfig','column_config','1'),(8,NULL,NULL,'demo',NULL,NULL,NULL,NULL,NULL,'1');
/*!40000 ALTER TABLE `table_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `courses` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `duty` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `study_group` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('2001',32,'大数据安全','项目管理组','组长','技术管理人员1',NULL,NULL),('2002',33,'大数据安全','立项组','部门经理','技术管理人员2',NULL,NULL),('2003',34,'大数据安全','评审组','组长','技术管理人员3',NULL,NULL),('2004',35,'数据挖掘','专家管理组','工作人员','技术管理人员4',NULL,NULL),('2005',38,'数据挖掘','专家管理组','组长','技术管理人员5',NULL,NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `token_id` varchar(255) NOT NULL,
  `dead_line` varchar(255) DEFAULT NULL,
  `deadLine` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `pid` varchar(255) NOT NULL,
  `groups` varchar(512) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `storage_id` varchar(128) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('8a7b31e5560e605d0156177d090d0009','8a7b31e5560e605d0156177eeb16001d,8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617ee8e640001,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d08f70000','0','1001','技术专家1'),('8a7b31e5560e605d0156177d091e000a','8a7b31e5560e605d0156177eeb16001d,8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617ee8e640001,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d08fa0001','0','1002','技术专家2'),('8a7b31e5560e605d0156177d091f000b','8a7b31e5560e605d0156177eeb16001d,8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617ee8e640001,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d08fb0002','0','1003','技术专家3'),('8a7b31e5560e605d0156177d091f000c','8a7b31e5560e605d0156177eeb16001d,8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617ee8e640001,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d08fd0003','0','1004','技术专家4'),('8a7b31e5560e605d0156177d091f000d','8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d08ff0004','0','1005','技术专家5'),('8a7b31e5560e605d0156177d091f000e','8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d09000005','0','1006','技术专家6'),('8a7b31e5560e605d0156177d091f000f','8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d09020006','0','1007','技术专家7'),('8a7b31e5560e605d0156177d091f0010','8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d09040007','0','1008','技术专家8'),('8a7b31e5560e605d0156177d091f0011','8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d09050008','0','1009','技术专家9'),('8a7b31e5560e605d0156177d958c0017','8a7b31e5560e605d0156177eeb16001d,8a7b31e55617e0a2015617ee8e640001','666666',0,'8a7b31e5560e605d0156177d957c0012','1','2001','技术管理人员1'),('8a7b31e5560e605d0156177d95930018','8a7b31e5560e605d0156177eeb16001d,8a7b31e55617e0a2015617ee8e640001','666666',0,'8a7b31e5560e605d0156177d957e0013','1','2002','技术管理人员2'),('8a7b31e5560e605d0156177d95940019','8a7b31e5560e605d0156177eeb16001d,8a7b31e55617e0a2015617ee8e640001','666666',0,'8a7b31e5560e605d0156177d95810014','1','2003','技术管理人员3'),('8a7b31e5560e605d0156177d9594001a','8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d958a0015','1','2004','技术管理人员4'),('8a7b31e5560e605d0156177d9594001b','8a7b31e5560e605d0156177fa570001f,8a7b31e55617e0a2015617eeed8f0003','666666',0,'8a7b31e5560e605d0156177d958c0016','1','2005','技术管理人员5');
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

-- Dump completed on 2016-07-24  2:43:12
