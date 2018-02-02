/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : shirospring

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-02-02 21:37:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(50) DEFAULT '' COMMENT '角色名称',
  `permission` varchar(50) DEFAULT '' COMMENT '角色权限',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '编辑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_permissions
-- ----------------------------
INSERT INTO `roles_permissions` VALUES ('1', 'admin', 'admin:*', '2018-02-01 22:34:46', '2018-02-01 22:34:46');
INSERT INTO `roles_permissions` VALUES ('2', 'user', 'user:*', '2018-02-01 22:34:46', '2018-02-01 22:34:46');
INSERT INTO `roles_permissions` VALUES ('3', 'dba', 'log:manage:*', '2018-02-22 09:25:09', '2018-02-22 09:25:13');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '编辑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'chench', '111111', '2018-02-01 22:34:46', '2018-02-01 22:34:46');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '编辑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', 'chench', 'admin', '2018-02-01 22:34:46', '2018-02-01 22:34:46');
INSERT INTO `user_roles` VALUES ('2', 'chench', 'user', '2018-02-01 22:34:46', '2018-02-01 22:34:46');
INSERT INTO `user_roles` VALUES ('3', 'chench', 'dba', '2018-02-02 08:58:39', '2018-02-02 08:58:39');
INSERT INTO `user_roles` VALUES ('4', 'chench', 'admin', '2018-02-02 10:05:20', '2018-02-02 10:05:20');
INSERT INTO `user_roles` VALUES ('5', 'chench', 'admin', '2018-02-02 15:58:41', '2018-02-02 15:58:41');
