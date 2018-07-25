CREATE DATABASE /*!32312 IF NOT EXISTS*/ `project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `project`;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `permission_type` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限的类别(URL:请求URL,APP:手机端UI,WEB:浏览器UI)',
  `permission_path` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限URL',
  `description` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `permission_url_UNIQUE` (`permission_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `auth_role`
--

DROP TABLE IF EXISTS `auth_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `description` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `name_UNIQUE` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_role_permission`
--

DROP TABLE IF EXISTS `auth_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录帐号',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'tom','2ffa62e7f38890fcbe9c52ef48e6bd67ef8a34a3f9d48b3b7ca0ed3d9ecbebc7','Tom James','15802889379','caoxile@126.com');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_role`
--

DROP TABLE IF EXISTS `auth_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

