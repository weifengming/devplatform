/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : devweb

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2020-04-02 07:30:51
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
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
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
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
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
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
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
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
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
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------

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
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
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
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(40) DEFAULT NULL,
  `head_img` varchar(255) DEFAULT NULL,
  `telphone` varchar(13) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `org_id` varchar(64) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `comment` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_by` varchar(64) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1236207834929143808', 'sysadmin', '系统管理员', '$2a$10$j3LbvJE.E3vkjL2vJxazKuWTPySudZuIfdR/LIVNoGo3YAFM61jKG', '123', null, '18888888888', '123@qq.com', null, '1', '系统管理员', '2020-03-28 23:14:00', null, '2020-03-07 08:23:35', null, '0');

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
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
