
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(45)  NOT NULL COMMENT '登录帐号',
  `password` varchar(100)  NOT NULL COMMENT '密码',
  `phone` varchar(45)  DEFAULT NULL COMMENT '手机号',
  `email` varchar(45)  DEFAULT NULL COMMENT '邮箱',
  `status` char(1)  DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


INSERT INTO `auth_user` VALUES (1,'tom','2ffa62e7f38890fcbe9c52ef48e6bd67ef8a34a3f9d48b3b7ca0ed3d9ecbebc7','15802889379','caoxile@126.com','1');


DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `opertation` varchar(50) NOT NULL COMMENT '操作描述',
  `uri` varchar(100) NOT NULL COMMENT '请求API',
  `params` varchar(500) NOT NULL COMMENT '参数',
  `ip` varchar(20) NOT NULL COMMENT 'IP地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

COMMIT;
