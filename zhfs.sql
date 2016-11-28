/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : zhfs

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2016-11-24 18:05:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu` (
  `MENU_ID` varchar(40) NOT NULL default '' COMMENT '菜单ID',
  `MENU_NAME` varchar(50) default NULL COMMENT '菜单名称',
  `PARENT_ID` varchar(40) default '' COMMENT '上级菜单ID',
  `MENU_URL` varchar(50) default NULL COMMENT '菜单URL',
  `ORDER_NO` int(20) default NULL COMMENT '排序号',
  `PERMITION` varchar(3) NOT NULL COMMENT '菜单的访问权限 0：普通用户 1：管理员',
  PRIMARY KEY  (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='èœå•';

-- ----------------------------
-- Records of s_menu
-- ----------------------------
INSERT INTO `s_menu` VALUES ('1000', '系统管理', '0', 'none', '1', '0,1');
INSERT INTO `s_menu` VALUES ('1001', '菜单管理', '1000', '/Menu.html', '1', '1');
INSERT INTO `s_menu` VALUES ('1002', '用户管理', '1000', '/User.html', '2', '1');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` int(5) NOT NULL COMMENT '角色标志位(0位普通用户,1位管理员)',
  `roleName` varchar(50) NOT NULL COMMENT '用户角色名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('0', '普通用户');
INSERT INTO `s_role` VALUES ('1', '管理员');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` int(20) NOT NULL auto_increment COMMENT '主键 id',
  `accountNumber` char(20) NOT NULL COMMENT '账号',
  `nickName` varchar(40) NOT NULL default '' COMMENT '昵称',
  `password` char(40) NOT NULL default '' COMMENT '密码',
  `role` int(2) NOT NULL default '0' COMMENT '用户类型：1 admin 0 普通用户',
  `enable` char(10) NOT NULL default '' COMMENT '账户是否可用 是  “可用”  否 “禁用”',
  `createDate` varchar(40) NOT NULL COMMENT '创建时间',
  `e_mail` char(100) default '',
  `image` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', '管理员', 'ee1d06b4c938d49529b9347f1c148852', '1', '可用', '2016-08-09', 'baijw12@lzu.edu.cn', '598bc9db-97e0-40e4-bf0d-5a11a6c27933.jpg');
