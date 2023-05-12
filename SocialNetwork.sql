-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: socialnetwork
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
-- Table structure for table `auctions`
--

DROP TABLE IF EXISTS `auctions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auctions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bidding_user` int NOT NULL,
  `bidding_post` int NOT NULL,
  `bidding_price` decimal(10,0) NOT NULL,
  `bidding_status` tinyint NOT NULL DEFAULT '0',
  `biddingat` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_auctions_user_idx` (`bidding_user`),
  KEY `fk_auctions_post_idx` (`bidding_post`),
  CONSTRAINT `fk_auctions_post` FOREIGN KEY (`bidding_post`) REFERENCES `post` (`id`),
  CONSTRAINT `fk_auctions_user` FOREIGN KEY (`bidding_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (1,1,1,32,1,'2022-02-10 10:42:38'),(2,2,2,1000,0,'2022-02-15 14:21:50');
/*!40000 ALTER TABLE `auctions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorypost`
--

DROP TABLE IF EXISTS `categorypost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorypost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorypost`
--

LOCK TABLES `categorypost` WRITE;
/*!40000 ALTER TABLE `categorypost` DISABLE KEYS */;
INSERT INTO `categorypost` VALUES (1,'Food'),(2,'Electronics'),(3,'Fashion'),(4,'furniture'),(5,'Sports'),(6,'Apartment'),(7,'Plants');
/*!40000 ALTER TABLE `categorypost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `posted_at` datetime NOT NULL,
  `likes` int DEFAULT '0',
  `check_reported` tinyint NOT NULL,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkcmt_user_idx` (`user_id`),
  KEY `fktcmt_post_idx` (`post_id`),
  CONSTRAINT `fk_cmt_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fkt_cmt_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'hmmm','2022-01-11 11:02:09',0,0,2,1),(2,'cũng được ấy nha','2021-12-18 11:02:25',0,0,2,1),(3,'đẹp thật\n','2022-02-15 19:56:03',2,0,1,33),(4,'đẹp thật','2022-02-14 19:56:38',0,0,1,28),(5,'mắc quá','2022-02-18 19:56:56',1,0,1,19),(6,'Hơi mắc nhỉ','2022-02-13 20:24:40',0,0,1,26),(7,'\n','2022-02-12 20:24:44',0,0,1,26);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likecomment`
--

DROP TABLE IF EXISTS `likecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likecomment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `comment_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_comment_idx` (`user_id`),
  KEY `fk_likecmt_comment_idx` (`comment_id`),
  CONSTRAINT `fk_likecmt_comment` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`),
  CONSTRAINT `fk_user_comment` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likecomment`
--

LOCK TABLES `likecomment` WRITE;
/*!40000 ALTER TABLE `likecomment` DISABLE KEYS */;
INSERT INTO `likecomment` VALUES (2,1,5),(3,3,3),(4,1,3);
/*!40000 ALTER TABLE `likecomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likepost`
--

DROP TABLE IF EXISTS `likepost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likepost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fklike_user_idx` (`user_id`),
  KEY `fktlike_post_idx` (`post_id`),
  CONSTRAINT `fklike_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fktlike_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likepost`
--

LOCK TABLES `likepost` WRITE;
/*!40000 ALTER TABLE `likepost` DISABLE KEYS */;
INSERT INTO `likepost` VALUES (1,4,1),(4,3,2),(5,2,2);
/*!40000 ALTER TABLE `likepost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL,
  `receiver_user` int NOT NULL,
  `sender_user` int NOT NULL,
  `SendAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notify_receiver_idx` (`receiver_user`),
  KEY `fk_notify_sender_idx` (`sender_user`),
  CONSTRAINT `fk_notify_receiver` FOREIGN KEY (`receiver_user`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_notify_sender` FOREIGN KEY (`sender_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,1,4,4,'2022-01-18 10:39:26'),(2,1,4,1,'2022-01-18 10:40:14'),(3,1,4,1,'2022-01-10 10:40:21'),(4,4,4,1,'2022-01-20 10:42:38'),(5,5,1,4,'2022-01-18 10:44:13'),(6,2,4,2,'2022-01-18 11:02:09'),(7,2,4,2,'2022-01-18 11:02:25'),(8,1,3,3,'2022-01-18 11:15:33'),(9,1,3,2,'2022-01-18 14:21:34'),(10,4,3,2,'2021-12-18 14:21:50'),(11,2,3,1,'2021-12-18 19:56:03'),(12,2,3,1,'2021-12-23 19:56:38'),(13,2,4,1,'2021-12-11 19:56:56'),(14,3,1,1,'2022-02-11 19:57:05'),(15,3,1,1,'2022-02-11 19:57:11'),(16,3,1,3,'2022-02-10 19:57:35'),(17,3,1,1,'2022-02-10 19:59:26'),(18,2,4,1,'2022-02-10 20:24:40'),(19,2,4,1,'2022-02-10 20:24:44');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `posted_at` datetime NOT NULL,
  `likes` int DEFAULT '0',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '',
  `startprice` decimal(10,0) NOT NULL,
  `check_reported` tinyint NOT NULL,
  `user_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkpost_user_idx` (`user_id`),
  KEY `fkpost_cate_idx` (`category_id`),
  CONSTRAINT `fkpost_cate` FOREIGN KEY (`category_id`) REFERENCES `categorypost` (`id`),
  CONSTRAINT `fkpost_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Mình bán dừa nha','2021-12-22 10:39:14',1,'https://res.cloudinary.com/dzctkszri/image/upload/v1645155555/rcp6q0s23ojlulrnjpgp.jpg',30,0,4,1),(2,'Mình bán cây nha #Love','2021-12-23 11:15:12',2,'https://res.cloudinary.com/dzctkszri/image/upload/v1645157714/xm4lbedv9bxtrwkiqwfx.jpg',14,0,3,7),(3,'mình bán burger nha','2021-12-23 18:03:53',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645182236/xopj99y1hbibutvshnnp.jpg',11,0,3,1),(4,'mình bán này nha','2021-12-24 18:04:37',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645182278/j0n1l9q4pnbel2xm5ypo.jpg',13,0,3,1),(5,'nay có đồ ăn mới nè','2021-12-29 18:05:45',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645182346/qosoclf4ik5v4xt1rjuo.jpg',23,0,3,1),(6,'jolly bee nè mọi người','2022-01-10 18:06:26',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645182387/jy0pzn5pvlkkqtoqdkwk.jpg',30,0,3,1),(7,'mình có đồ món mới nè','2022-01-12 18:07:05',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645182427/zwhftk5w7xxtu9rx8wop.jpg',22,0,3,1),(8,'ghế ABC1 nè','2022-01-18 19:06:02',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645185963/t38rstnselmzbru8gr4w.jpg',103,0,3,4),(9,'Ghế ABC2 nè\r\n','2022-01-18 19:06:44',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645186005/acwsluhi8ocmmstfs2qk.jpg',110,0,3,4),(10,'Ghế ABC3 nè','2022-01-18 19:07:42',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645186063/tbmat2tshplekv6thtme.jpg',132,0,3,4),(11,'Ghế ABC4 nè','2022-01-18 19:08:15',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645186096/fzgir04j8pbbpuikt5xs.jpg',120,0,3,4),(12,'Bộ ghế xịn AAA nè mn','2022-01-18 19:09:05',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645186146/rjvycclavayzunotd0nn.jpg',320,0,3,4),(13,'mình có bán hoa này ','2022-01-18 19:29:38',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187350/zpiaizvwmfapkbgywrva.jpg',11,0,3,7),(14,'cam tươi mới','2022-01-18 19:32:05',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187527/omt1xtqfrloocueukkez.jpg',10,0,4,1),(15,'dưa tươi mới','2022-01-18 19:33:03',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187585/lqhcrndeednbq2pzbhak.jpg',15,0,4,1),(16,'xoài mới hái','2022-01-18 19:33:44',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187626/lhtjzkzthho7dql0nc9l.jpg',15,0,4,1),(17,'Mới thử tay làm món mới','2022-01-18 19:34:25',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187667/rv8qzykmofnztkbbvzuo.jpg',30,0,4,1),(18,'máy hàng cũ 1','2022-01-18 19:35:55',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187757/xsuh7ievmrsy76llhiuc.png',200,0,4,2),(19,'máy hàng cũ 2','2022-01-18 19:36:50',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187812/g4skbz3ipnennbtzu0ks.jpg',1000,0,4,2),(20,'máy hàng cũ 3','2022-01-18 19:37:30',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187851/joytwgyadodvy1lp4s4e.jpg',230,0,4,2),(21,'máy hàng cũ 4','2022-01-18 19:38:03',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187885/teguzdlgt6q4kul8zods.jpg',340,0,4,2),(22,'máy hàng cũ 5','2022-01-18 19:40:19',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645187999/nxuuhhnynpiullm46sxw.jpg',800,0,4,2),(23,'đồ thể thao nè','2022-02-11 19:40:46',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188047/rs6ye7zjws28eohl52sj.jpg',20,0,4,5),(24,'đồ thể thao nè','2022-02-11 19:41:07',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188069/glhdpeahij9a6vfnoztx.jpg',20,0,4,5),(25,'đồ thể thao nè','2022-02-11 19:41:42',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188104/fzg3iwplyebw91ovldy5.jpg',30,0,4,5),(26,'đồ thể thao nè','2022-02-11 19:42:28',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188149/nil1bgusqc14riqrye73.jpg',30,0,4,5),(27,'đồ thể thao nè','2022-02-11 19:43:04',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188186/sjnwro7x6mvmkrbuavwk.jpg',50,0,4,5),(28,'quần áo mới về','2022-02-11 19:44:36',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188277/wcjiwsscrqlpqu2hav4b.jpg',30,0,3,3),(29,'đồ mới về nè','2022-02-11 19:45:18',0,'',50,0,3,1),(30,'đồ mới về nè','2022-02-11 19:45:54',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188357/mjafa8sgjujnylllahkt.jpg',50,0,3,3),(31,'Form mới tạo','2022-02-11 19:46:30',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188392/sfl6eoruyvt5xha5if83.jpg',400,0,3,3),(32,'bộ mới giới hạn','2022-02-11 19:47:07',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188428/w3qqjnbugjsdx2wgdbyk.jpg',500,0,3,3),(33,'teen new fashion','2022-02-18 19:47:35',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645188456/lzot6syluy7paizy9we0.jpg',120,0,3,3),(34,'hàng mới có','2022-02-18 20:40:48',0,'https://res.cloudinary.com/dzctkszri/image/upload/v1645191650/qnfxq4yzkvm3w3jx3s7r.jpg',100,0,4,2);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportcomment`
--

DROP TABLE IF EXISTS `reportcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportcomment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_sender` int NOT NULL,
  `user_receiver` int NOT NULL,
  `comment_report` int NOT NULL,
  `reasonreport` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `check_verified` tinyint NOT NULL,
  `sendreport_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rpcmt_sender_idx` (`user_sender`),
  KEY `fk_rpcmt_receiver_idx` (`user_receiver`),
  KEY `fk_rpcmt_cmtreport_idx` (`comment_report`),
  CONSTRAINT `fk_rpcmt_cmtreport` FOREIGN KEY (`comment_report`) REFERENCES `comments` (`id`),
  CONSTRAINT `fk_rpcmt_receiver` FOREIGN KEY (`user_receiver`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_rpcmt_sender` FOREIGN KEY (`user_sender`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportcomment`
--

LOCK TABLES `reportcomment` WRITE;
/*!40000 ALTER TABLE `reportcomment` DISABLE KEYS */;
INSERT INTO `reportcomment` VALUES (1,3,2,3,'test thôi mà',0,'2022-02-10 19:57:50');
/*!40000 ALTER TABLE `reportcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportpost`
--

DROP TABLE IF EXISTS `reportpost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportpost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_sender` int NOT NULL,
  `user_receiver` int NOT NULL,
  `post_report` int NOT NULL,
  `reasonreport` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `check_verified` tinyint NOT NULL,
  `sendreport_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sender_user_idx` (`user_sender`),
  KEY `fk_receiver_user_idx` (`user_receiver`),
  KEY `fk_report_post_idx` (`post_report`),
  CONSTRAINT `fk_receiver_user` FOREIGN KEY (`user_receiver`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_report_post` FOREIGN KEY (`post_report`) REFERENCES `post` (`id`),
  CONSTRAINT `fk_sender_user` FOREIGN KEY (`user_sender`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportpost`
--

LOCK TABLES `reportpost` WRITE;
/*!40000 ALTER TABLE `reportpost` DISABLE KEYS */;
INSERT INTO `reportpost` VALUES (1,3,2,24,'Hàng này rởm quá, hàng kém chất lượng',0,'2022-02-10 19:49:26');
/*!40000 ALTER TABLE `reportpost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'male',
  `aboutme` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `birthday` date NOT NULL,
  `phonenumber` varchar(14) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '0',
  `u_role` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'an','$2a$10$gV0zkP5AZhfhNJX2cjNNjueg0MNgQ9P1PA/BzNVXy1ICklHrU7uXy','an@gmail.com','An Ngoc','https://res.cloudinary.com/dzctkszri/image/upload/v1645118509/ouooqcbee1mo58xhxzvn.jpg','male',NULL,'2021-05-13','136987452','ROLE_USER'),(2,'admin','$2a$10$r46aK77ayrOE2nG/n5B.U.sssW1kpUPk2vRkPWJsKhARmMi2SXgPa','admin@gmail.com','Admin','https://res.cloudinary.com/dzctkszri/image/upload/v1645118739/qjywl1ojmbzoszbe9fp3.jpg','male',NULL,'2000-01-01','136897542','ROLE_ADMIN'),(3,'trinh','$2a$10$HJGpJROuUbS59a2fp42faevzbgB9c3XrHBrWjrIlWlXT2yLHcv2ke','trinh@gmail.com','Kieu Trinh','https://res.cloudinary.com/dzctkszri/image/upload/v1645119047/irnqm8urma3nyb9wninb.jpg','female',NULL,'2021-03-04','369875895','ROLE_USER'),(4,'thao','$2a$10$dkchIJpJa6VxVdF/n/31Hu2MbOUfxtv4CwzQvvnBL70ILcsK.T.Iu','Thao@gmail.com','Thao','https://res.cloudinary.com/dzctkszri/image/upload/v1645155283/rxjwociauzdcbva1iqyz.jpg','female',NULL,'2022-02-02','697825064','ROLE_USER');
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

-- Dump completed on 2022-02-18 21:29:35
