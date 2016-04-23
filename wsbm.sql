# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: wsbm
# ------------------------------------------------------
# Server version 5.5.28

#
# Source for table dict
#

DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT,
  `d_type` varchar(255) DEFAULT NULL,
  `d_code` varchar(255) DEFAULT NULL,
  `d_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Dumping data for table dict
#

LOCK TABLES `dict` WRITE;
/*!40000 ALTER TABLE `dict` DISABLE KEYS */;
INSERT INTO `dict` VALUES (1,'school','11','xiaoqu1');
INSERT INTO `dict` VALUES (2,'course','1','math');
INSERT INTO `dict` VALUES (3,'course','2','chinese');
/*!40000 ALTER TABLE `dict` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table exam_room
#

DROP TABLE IF EXISTS `exam_room`;
CREATE TABLE `exam_room` (
  `e_id` int(11) NOT NULL AUTO_INCREMENT,
  `e_name` varchar(255) DEFAULT NULL,
  `e_num` int(11) DEFAULT NULL,
  `e_remain` int(11) DEFAULT NULL,
  `e_startTime` varchar(255) DEFAULT NULL,
  `e_endTime` varchar(255) DEFAULT NULL,
  `e_start` int(11) DEFAULT NULL,
  `e_school_id` int(11) DEFAULT NULL,
  `e_course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Dumping data for table exam_room
#

LOCK TABLES `exam_room` WRITE;
/*!40000 ALTER TABLE `exam_room` DISABLE KEYS */;
INSERT INTO `exam_room` VALUES (3,'kaochang1',50,49,'2016-03-26 19:25:00','2016-03-26 22:50:00',1,1,2);
INSERT INTO `exam_room` VALUES (5,'kaochang3',50,50,'2016-03-29 18:00:00','2016-03-29 18:55:00',1,1,2);
INSERT INTO `exam_room` VALUES (6,'kaochang4',60,59,'2016-03-26 16:55:00','2016-03-26 17:55:00',1,1,3);
INSERT INTO `exam_room` VALUES (8,'kaochang6',50,50,'2016-04-30 14:35:00','2016-04-30 23:35:00',1,1,2);
/*!40000 ALTER TABLE `exam_room` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table info
#

DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `idCard` varchar(255) DEFAULT NULL,
  `phone_1` varchar(255) DEFAULT NULL,
  `schoolAddress` varchar(255) DEFAULT NULL,
  `householdAddress` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `sclass` varchar(255) DEFAULT NULL,
  `homeAddress` varchar(255) DEFAULT NULL,
  `phone_2` varchar(255) DEFAULT NULL,
  `enter` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `teacher` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `R_ID` int(11) DEFAULT NULL,
  `R_Account` varchar(255) DEFAULT NULL,
  `R_Pwd` varchar(255) DEFAULT NULL,
  `R_Name` varchar(255) DEFAULT NULL,
  `R_Role` int(11) DEFAULT NULL,
  `Class` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table info
#

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
INSERT INTO `info` VALUES ('330326199301203619','cxjhihihi',NULL,'1993-01-20','\\9\\10\\52dea69a-c6da-466e-a13d-0035815453b1_IMG_4001.JPG','330326199301203619','17878787878','','??','',NULL,'','',1,2,1,'2016-03-20',NULL,NULL,NULL,NULL,NULL,'','330326199301203619');
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table mes_model
#

DROP TABLE IF EXISTS `mes_model`;
CREATE TABLE `mes_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `teacher` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Dumping data for table mes_model
#

LOCK TABLES `mes_model` WRITE;
/*!40000 ALTER TABLE `mes_model` DISABLE KEYS */;
INSERT INTO `mes_model` VALUES (1,'<p>ok</p>',2,NULL);
INSERT INTO `mes_model` VALUES (2,'<p>not ok</p>',1,NULL);
/*!40000 ALTER TABLE `mes_model` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table message
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_content` varchar(255) DEFAULT NULL,
  `m_to` varchar(255) DEFAULT NULL,
  `M_From` varchar(1) DEFAULT NULL,
  `m_globale` int(11) DEFAULT NULL,
  `m_time` varchar(255) DEFAULT NULL,
  `m_read` int(11) DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Dumping data for table message
#

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'<p>ok</p>','330326199301203619','1',0,'2016-03-20 21:03:20',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table role
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_account` varchar(255) DEFAULT NULL,
  `r_pwd` varchar(255) DEFAULT NULL,
  `r_name` varchar(255) DEFAULT NULL,
  `r_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Dumping data for table role
#

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'cxjhihihi','cxjhihihi','cxjhihihi',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ticket
#

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_stuId` varchar(255) DEFAULT NULL,
  `t_examId` varchar(255) DEFAULT NULL,
  `t_seat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Dumping data for table ticket
#

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'330326199301203619','3','1');
INSERT INTO `ticket` VALUES (2,'330326199301203619','6','1');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table time
#

DROP TABLE IF EXISTS `time`;
CREATE TABLE `time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `start_Time` varchar(255) DEFAULT NULL,
  `end_Time` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Dumping data for table time
#

LOCK TABLES `time` WRITE;
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` VALUES (3,'????','2016-03-11','2016-03-31',0);
/*!40000 ALTER TABLE `time` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table user
#

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('330326199301203619','cxjhihihi',NULL,'?','cxjhihihi',NULL,'1993-01-20');
INSERT INTO `user` VALUES ('330723199311080045','cxjhihihi',NULL,'?','cxjhihihi',NULL,'1993-11-08');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
