/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : devweb

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2020-05-16 14:17:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `dict_name` varchar(100) DEFAULT NULL,
  `dict_value` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `order_num` bigint(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_fun`
-- ----------------------------
DROP TABLE IF EXISTS `sys_fun`;
CREATE TABLE `sys_fun` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `fun_name` varchar(100) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `order_num` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_fun
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_fun_action`
-- ----------------------------
DROP TABLE IF EXISTS `sys_fun_action`;
CREATE TABLE `sys_fun_action` (
  `id` varchar(64) NOT NULL,
  `fun_id` varchar(64) DEFAULT NULL,
  `funaction_name` varchar(100) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_fun_action
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` varchar(5000) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL,
  `fun_code` varchar(50) DEFAULT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `menu_name` varchar(100) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `order_num` bigint(4) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_org`
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) DEFAULT NULL COMMENT '一级机构为0',
  `org_name` varchar(100) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('121sfsfdds4g67jgtyg6', '341gdfgsgfds', '运营部', '100001', null, '2', '2020-05-04 17:51:49', null, '1970-01-01 10:00:00', null, '2');
INSERT INTO `sys_org` VALUES ('341gdfgsgfds', '0', '宏昌科技', '100000', null, '1', '2020-05-04 17:50:39', null, '2020-05-04 17:50:47', null, '2');
INSERT INTO `sys_org` VALUES ('34242sdfsfs', '0', '昌云软件', '200000', null, null, '2020-05-04 18:51:05', null, '1970-01-01 10:00:00', null, '2');
INSERT INTO `sys_org` VALUES ('sdsds5435v455yy', '341gdfgsgfds', '销售部', '100002', null, '3', '2020-05-04 17:52:40', null, '1970-01-01 10:00:00', null, '2');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_fun`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_fun`;
CREATE TABLE `sys_role_fun` (
  `id` varchar(64) NOT NULL,
  `fun_id` varchar(64) DEFAULT NULL,
  `role_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_fun
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(40) DEFAULT NULL,
  `head_img` varchar(255) DEFAULT NULL,
  `telphone` varchar(13) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `org_id` varchar(64) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  2：禁用   1：正常',
  `comment` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1236207834929143808', 'sysadmin', '系统管理员', '1', '$2a$10$j3LbvJE.E3vkjL2vJxazKuWTPySudZuIfdR/LIVNoGo3YAFM61jKG', '123', null, '18888888888', '123@qq.com', null, '1', '系统管理员', '2020-05-04 12:05:37', null, '2020-03-07 08:23:35', null, '2');
INSERT INTO `sys_user` VALUES ('1256904157000921088', '1', '1', '1', '$2a$10$0CWxdsd/BLBcaiLpQRcz5.xsbkIcVcf8R/VhUhjqHuOiPjPYuwLeO', null, null, '1', '1', '1', '1', '1', '2020-05-04 17:44:13', null, '2020-05-04 17:44:02', null, '2');
INSERT INTO `sys_user` VALUES ('1256904315537223680', '2', '2', '1', '$2a$10$7KjdU4d/NfmuCPMjxnWpoeAH2gAxSbpPNQPIrRN5yAHI8/7rTDlM2', null, null, '2', '2', '2', '1', '2', '2020-05-04 17:44:15', null, '2020-05-04 17:44:04', null, '2');
INSERT INTO `sys_user` VALUES ('1256904650150408192', '3', '3', '1', '$2a$10$zah1EYbo1q3OHjsxS6X1K.mY.DvpteoKh2cJ4uXY8w/kPSWmLKqp.', null, null, '3', '3', '3', '1', '3', '2020-05-04 17:44:16', null, '2020-05-04 17:44:00', null, '2');
INSERT INTO `sys_user` VALUES ('1256904983647907840', '5', '5', '1', '$2a$10$5b5dHPzQQTy78UvqB3y.XOPidJnkHfG.RAvYDrtlTDB4.evFaF6.y', null, null, '5', '5', '5', '1', '5', '2020-05-04 17:44:17', null, '2020-05-04 12:37:06', null, '2');
INSERT INTO `sys_user` VALUES ('1256905043672592384', '6', '7', '1', '$2a$10$Hpt6Foo/baElHkU6n2ORZ.4HIkV2gKtB76ZqhyFbMgb3EbAUvz.a2', null, null, '7', '7', '7', '1', '7', '2020-05-04 17:44:17', null, '2020-05-04 11:53:10', null, '2');
INSERT INTO `sys_user` VALUES ('1257242407645257728', '8', '33', '1', '$2a$10$64bum0AqKoBCNYefViAM4O/6e7o/.37HFm3o9W3y2YZwYR45vCj6O', null, null, '444', '7', '7', '1', '7', '2020-05-04 17:44:18', null, '2020-05-04 17:35:52', null, '2');
INSERT INTO `sys_user` VALUES ('1257244523478687744', '0', '0', '1', '$2a$10$ki.wtOCJfuxgXR7aSxH7l.29csRGDHhno5IapeUerxSbIqjRqwlqS', null, null, '15611784834', '123@qq.com', '中软', '1', '1111', '2020-05-04 17:43:48', null, null, null, '2');
INSERT INTO `sys_user` VALUES ('2456576575675675', 'www', 'www', '1', null, null, null, '15611234324', '276535342@qq.com', null, '1', null, '2020-05-04 13:03:51', null, '2020-05-04 12:36:35', null, '2');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `role_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_token`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expire_in` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT '1970-01-01 10:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
