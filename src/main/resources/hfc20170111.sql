-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: www.dbdbd.cn    Database: hfc
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `appreciate`
--

DROP TABLE IF EXISTS `appreciate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appreciate` (
  `id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` text,
  `logo` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appreciate`
--

LOCK TABLES `appreciate` WRITE;
/*!40000 ALTER TABLE `appreciate` DISABLE KEYS */;
/*!40000 ALTER TABLE `appreciate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appreciate_category`
--

DROP TABLE IF EXISTS `appreciate_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appreciate_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appreciate_category`
--

LOCK TABLES `appreciate_category` WRITE;
/*!40000 ALTER TABLE `appreciate_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `appreciate_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appreciate_comment`
--

DROP TABLE IF EXISTS `appreciate_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appreciate_comment` (
  `id` bigint(20) NOT NULL,
  `appreciate_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `content` text,
  `is_deleted` int(11) DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `delete_reason` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `user_nick` varchar(255) DEFAULT NULL,
  `answer_user_nick` varchar(255) DEFAULT NULL,
  `answer_user_id` bigint(20) DEFAULT NULL,
  `answer_content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appreciate_comment`
--

LOCK TABLES `appreciate_comment` WRITE;
/*!40000 ALTER TABLE `appreciate_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appreciate_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appreciate_knowledge`
--

DROP TABLE IF EXISTS `appreciate_knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appreciate_knowledge` (
  `id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` text,
  `logo` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` varchar(45) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appreciate_knowledge`
--

LOCK TABLES `appreciate_knowledge` WRITE;
/*!40000 ALTER TABLE `appreciate_knowledge` DISABLE KEYS */;
/*!40000 ALTER TABLE `appreciate_knowledge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cultural_finance`
--

DROP TABLE IF EXISTS `cultural_finance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cultural_finance` (
  `id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cultural_finance`
--

LOCK TABLES `cultural_finance` WRITE;
/*!40000 ALTER TABLE `cultural_finance` DISABLE KEYS */;
/*!40000 ALTER TABLE `cultural_finance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cultural_finance_category`
--

DROP TABLE IF EXISTS `cultural_finance_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cultural_finance_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cultural_finance_category`
--

LOCK TABLES `cultural_finance_category` WRITE;
/*!40000 ALTER TABLE `cultural_finance_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `cultural_finance_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hf_artist`
--

DROP TABLE IF EXISTS `hf_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hf_artist` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `top` float DEFAULT '0',
  `intro` text,
  `detail` mediumtext,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hf_artist`
--

LOCK TABLES `hf_artist` WRITE;
/*!40000 ALTER TABLE `hf_artist` DISABLE KEYS */;
/*!40000 ALTER TABLE `hf_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hf_artist_interview`
--

DROP TABLE IF EXISTS `hf_artist_interview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hf_artist_interview` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` text,
  `logo` varchar(255) DEFAULT NULL,
  `reporter` varchar(45) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hf_artist_interview`
--

LOCK TABLES `hf_artist_interview` WRITE;
/*!40000 ALTER TABLE `hf_artist_interview` DISABLE KEYS */;
/*!40000 ALTER TABLE `hf_artist_interview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hf_artist_lecture`
--

DROP TABLE IF EXISTS `hf_artist_lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hf_artist_lecture` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` text,
  `logo` varchar(45) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hf_artist_lecture`
--

LOCK TABLES `hf_artist_lecture` WRITE;
/*!40000 ALTER TABLE `hf_artist_lecture` DISABLE KEYS */;
/*!40000 ALTER TABLE `hf_artist_lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hf_artist_recommend`
--

DROP TABLE IF EXISTS `hf_artist_recommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hf_artist_recommend` (
  `id` bigint(20) NOT NULL,
  `artist_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hf_artist_recommend`
--

LOCK TABLES `hf_artist_recommend` WRITE;
/*!40000 ALTER TABLE `hf_artist_recommend` DISABLE KEYS */;
/*!40000 ALTER TABLE `hf_artist_recommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_activity`
--

DROP TABLE IF EXISTS `member_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_activity` (
  `id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_activity`
--

LOCK TABLES `member_activity` WRITE;
/*!40000 ALTER TABLE `member_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_activity_category`
--

DROP TABLE IF EXISTS `member_activity_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_activity_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_activity_category`
--

LOCK TABLES `member_activity_category` WRITE;
/*!40000 ALTER TABLE `member_activity_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_activity_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` text,
  `logo` varchar(255) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  `content` mediumtext,
  `click_count` int(11) DEFAULT NULL,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_category`
--

DROP TABLE IF EXISTS `news_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_category`
--

LOCK TABLES `news_category` WRITE;
/*!40000 ALTER TABLE `news_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_mode`
--

DROP TABLE IF EXISTS `news_mode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_mode` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_mode`
--

LOCK TABLES `news_mode` WRITE;
/*!40000 ALTER TABLE `news_mode` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_mode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_recommend`
--

DROP TABLE IF EXISTS `news_recommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_recommend` (
  `id` bigint(20) NOT NULL,
  `news_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_recommend`
--

LOCK TABLES `news_recommend` WRITE;
/*!40000 ALTER TABLE `news_recommend` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_recommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `research_report`
--

DROP TABLE IF EXISTS `research_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `research_report` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `appendix_url` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `research_report`
--

LOCK TABLES `research_report` WRITE;
/*!40000 ALTER TABLE `research_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `research_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summit`
--

DROP TABLE IF EXISTS `summit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summit` (
  `id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summit`
--

LOCK TABLES `summit` WRITE;
/*!40000 ALTER TABLE `summit` DISABLE KEYS */;
/*!40000 ALTER TABLE `summit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summit_category`
--

DROP TABLE IF EXISTS `summit_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summit_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `top` float DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summit_category`
--

LOCK TABLES `summit_category` WRITE;
/*!40000 ALTER TABLE `summit_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `summit_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` text,
  `logo` varchar(255) DEFAULT NULL,
  `content` mediumtext,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `top` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_comment`
--

DROP TABLE IF EXISTS `topic_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_comment` (
  `id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `content` text,
  `is_deleted` int(11) DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `delete_reason` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `user_nick` varchar(255) DEFAULT NULL,
  `answer_user_nick` varchar(255) DEFAULT NULL,
  `answer_user_id` bigint(20) DEFAULT NULL,
  `answer_content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_comment`
--

LOCK TABLES `topic_comment` WRITE;
/*!40000 ALTER TABLE `topic_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `weixin_id` varchar(45) DEFAULT NULL,
  `qq_id` varchar(45) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `nick_name` varchar(45) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `receipt_address` text,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2017-01-13 17:04:45
