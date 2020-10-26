/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : cs_admin_db

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 17/10/2020 17:36:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dept`;
CREATE TABLE `t_sys_dept` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `name` varchar(45) NOT NULL COMMENT '部门名称',
  `pid` varchar(64) NOT NULL DEFAULT '0' COMMENT '父级编号',
  `pids` varchar(128) DEFAULT NULL COMMENT '祖先结点id集',
  `state` int(11) DEFAULT NULL COMMENT '0：正常\n1：失效\n',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `mail` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `area_code` varchar(64) DEFAULT NULL COMMENT '社区代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统部门表';

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_dept` VALUES ('1', '系统组织', '0', '0', 0, '部门管理者', NULL, NULL, '2020-03-24 17:31:17', '2020-06-04 15:11:54', '');
INSERT INTO `t_sys_dept` VALUES ('c94c01e1a5b6c63b43c53f9acd43a78a', '普通数据', '1', '0,1', 0, NULL, NULL, NULL, '2020-10-17 16:55:45', '2020-10-17 16:55:45', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `name` varchar(45) NOT NULL COMMENT '名称',
  `pid` varchar(64) DEFAULT '0' COMMENT '父菜单结点\n约定：最大3级',
  `type` varchar(2) DEFAULT 'M' COMMENT 'M:正常菜单\nD:目录 B:按钮',
  `path` varchar(128) DEFAULT NULL COMMENT '路径，请使用相对根路径',
  `component` varchar(45) DEFAULT NULL COMMENT 'vue组件地址，默认添加@/views/前缀',
  `icon` varchar(20) DEFAULT NULL COMMENT 'antd icon名',
  `state` int(11) DEFAULT '0' COMMENT '0:有效\n1:无效',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort` int(11) DEFAULT '0' COMMENT '菜单排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统目录表';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_menu` VALUES ('1', '系统管理', '0', 'D', '/sys', NULL, 'setting', 0, '2020-03-24 17:31:17', '2020-04-28 16:29:53', 50);
INSERT INTO `t_sys_menu` VALUES ('15', '菜单新增', '2', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 1);
INSERT INTO `t_sys_menu` VALUES ('16', '菜单修改', '2', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 2);
INSERT INTO `t_sys_menu` VALUES ('17', '菜单删除', '2', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 3);
INSERT INTO `t_sys_menu` VALUES ('2', '菜单管理', '1', 'M', '/sys/menu', 'sys/menu', 'menu', 0, '2020-03-24 17:31:17', '2020-04-16 15:37:40', 1);
INSERT INTO `t_sys_menu` VALUES ('3', '角色管理', '1', 'M', '/sys/role', 'sys/role', 'solution', 0, '2020-03-24 17:31:17', '2020-04-16 15:37:44', 2);
INSERT INTO `t_sys_menu` VALUES ('31', '角色新增', '3', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 1);
INSERT INTO `t_sys_menu` VALUES ('32', '角色修改', '3', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 2);
INSERT INTO `t_sys_menu` VALUES ('34', '角色删除', '3', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 3);
INSERT INTO `t_sys_menu` VALUES ('37', '部门管理', '1', 'M', '/sys/dept', 'sys/dept', 'deployment-unit', 0, '2020-03-24 17:31:17', '2020-04-16 15:37:48', 3);
INSERT INTO `t_sys_menu` VALUES ('38', '部门查询', '37', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 0);
INSERT INTO `t_sys_menu` VALUES ('39', '部门新增', '37', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 1);
INSERT INTO `t_sys_menu` VALUES ('40', '部门修改', '37', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 2);
INSERT INTO `t_sys_menu` VALUES ('41', '部门删除', '37', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 3);
INSERT INTO `t_sys_menu` VALUES ('42', '角色查询', '3', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 0);
INSERT INTO `t_sys_menu` VALUES ('44', '菜单查询', '2', 'B', NULL, NULL, NULL, 0, '2020-03-24 17:31:17', '2020-03-24 17:31:17', 0);
INSERT INTO `t_sys_menu` VALUES ('89', '用户管理', '1', 'M', '/sys/user', 'sys/user', 'user', 0, '2020-04-16 15:21:01', '2020-04-16 15:37:32', 0);
INSERT INTO `t_sys_menu` VALUES ('90', '用户查询', '89', 'B', NULL, NULL, NULL, 0, '2020-04-16 15:21:25', '2020-04-16 15:21:25', 0);
INSERT INTO `t_sys_menu` VALUES ('91', '用户编辑', '89', 'B', NULL, NULL, NULL, 0, '2020-04-16 15:29:47', '2020-04-16 15:29:47', 1);
INSERT INTO `t_sys_menu` VALUES ('93', '数据分配', '89', 'B', NULL, NULL, NULL, 0, '2020-04-16 15:31:16', '2020-04-16 15:34:24', 3);
INSERT INTO `t_sys_menu` VALUES ('94', '角色分配', '89', 'B', NULL, NULL, NULL, 1, '2020-04-16 15:33:25', '2020-05-18 17:09:04', 2);
INSERT INTO `t_sys_menu` VALUES ('97', '停用启用', '89', 'B', NULL, NULL, NULL, 0, '2020-04-16 15:34:14', '2020-04-16 15:34:31', 4);
INSERT INTO `t_sys_menu` VALUES ('98', '重置密码', '89', 'B', NULL, NULL, NULL, 0, '2020-04-16 15:34:58', '2020-04-16 15:34:58', 4);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_perm`;
CREATE TABLE `t_sys_perm` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `resource_id` varchar(64) NOT NULL COMMENT '资源id，如菜单id、部门id',
  `perm_name` varchar(30) DEFAULT NULL COMMENT '权限标识名',
  `perm_type` varchar(10) NOT NULL COMMENT '权限类型，现有DEPT部门、MENU菜单',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `resource_id` (`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of t_sys_perm
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_perm` VALUES ('1', NULL, '1', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('100', NULL, '93', 'user:auth_data', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('101', NULL, '94', 'user:auth_role', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('102', NULL, '97', 'user:update', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('103', NULL, '98', 'user:reset_pwd', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('104', NULL, '99', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('107', NULL, '102', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('114', NULL, '111', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('121', NULL, '123', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('122', NULL, '124', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('32', NULL, '35', 'menu:list', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('33', NULL, '15', 'menu:add', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('34', NULL, '16', 'menu:edit', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('35', NULL, '17', 'menu:remove', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('36', NULL, '2', 'menu:view', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('37', NULL, '3', 'role:view', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('38', NULL, '42', 'role:list', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('39', NULL, '31', 'role:add', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('40', NULL, '32', 'role:edit', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('41', NULL, '34', 'role:remove', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('42', NULL, '37', 'dept:view', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('43', NULL, '38', 'dept:list', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('44', NULL, '39', 'dept:add', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('45', NULL, '40', 'dept:edit', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('46', NULL, '41', 'dept:remove', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('49', NULL, '44', 'menu:list', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('50', NULL, '45', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('51', NULL, '16', NULL, 'DEPT');
INSERT INTO `t_sys_perm` VALUES ('52', NULL, '18', NULL, 'DEPT');
INSERT INTO `t_sys_perm` VALUES ('53', NULL, '17', NULL, 'DEPT');
INSERT INTO `t_sys_perm` VALUES ('54', NULL, '46', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('55', NULL, '47', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('56', NULL, '48', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('57', NULL, '49', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('58', NULL, '50', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('59', NULL, '51', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('60', NULL, '52', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('61', NULL, '53', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('62', NULL, '54', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('63', NULL, '55', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('64', NULL, '56', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('65', NULL, '57', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('66', NULL, '58', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('67', NULL, '59', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('68', NULL, '60', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('69', NULL, '61', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('70', NULL, '62', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('71', NULL, '63', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('74', NULL, '66', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('75', NULL, '67', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('76', NULL, '68', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('77', NULL, '69', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('78', NULL, '70', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('79', NULL, '71', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('80', NULL, '72', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('81', NULL, '73', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('82', NULL, '74', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('83', NULL, '75', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('84', NULL, '76', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('86', NULL, '78', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('90', NULL, '82', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('92', NULL, '84', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('94', NULL, '86', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('95', NULL, '87', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('96', NULL, '88', NULL, 'MENU');
INSERT INTO `t_sys_perm` VALUES ('97', NULL, '89', 'user:view', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('98', NULL, '90', 'user:list', 'MENU');
INSERT INTO `t_sys_perm` VALUES ('99', NULL, '91', 'user:edit', 'MENU');
COMMIT;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `role_name` varchar(45) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(45) DEFAULT NULL COMMENT '角色编码',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` int(11) DEFAULT '0' COMMENT '状态，0：正常，1：停用',
  `dept_perm_type` char(1) DEFAULT 'P' COMMENT '部门权限类型，A:所有 P:部分',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_role` VALUES ('1', 'mz', '管理员', '2020-03-24 17:31:17', 0, 'P');
INSERT INTO `t_sys_role` VALUES ('3', 'guest', '访客', '2020-03-24 17:31:17', 0, 'P');
COMMIT;

-- ----------------------------
-- Table structure for t_sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_perm`;
CREATE TABLE `t_sys_role_perm` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `perm_id` varchar(64) NOT NULL COMMENT '权限编号',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色权限关联表';

-- ----------------------------
-- Records of t_sys_role_perm
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_role_perm` VALUES ('00c075fdb133f1f91df57757dee5f723', '5f68a6501fa30880d10e0ec6fd5987f0', '118', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('0135fae2e70c3c30e9a993ead47d750a', '5f68a6501fa30880d10e0ec6fd5987f0', '60', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('01aad9016c0390d70c1b2c2423b5bb94', '1', '119', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('0346301c313581a20f9677c731e9f825', '9928f308f93d5de0a9343796cb3bf213', '77', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('038e20b15a8e546b1acd316adcaf117f', '1', '1ef960b9ede37fabb5d56890be102091', '2020-06-22 17:48:29');
INSERT INTO `t_sys_role_perm` VALUES ('03ef392ae330b4dbf705ffa69a88028f', '9928f308f93d5de0a9343796cb3bf213', '83', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('040305e05fc4d52b8440dd9086185197', '9928f308f93d5de0a9343796cb3bf213', '39', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('0601933321279efca545ab3c31ba5e50', '9928f308f93d5de0a9343796cb3bf213', '603b8c102ed42c5d93ca0553cb79cc33', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('088febfd8a99fc379b032e8ec38055b0', '1', '111', '2020-05-22 21:07:51');
INSERT INTO `t_sys_role_perm` VALUES ('0898b948859904bec61fb1d9fd5e2c49', '1', '120', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('08bb02174144a6b6982a873ddc2c7503', '57ba7c794b5e771f1513703dc35c2666', '94', '2020-06-11 16:41:32');
INSERT INTO `t_sys_role_perm` VALUES ('08e8e47c1de882640dbf71d25226367b', '9928f308f93d5de0a9343796cb3bf213', '4c49efe3819eac09ccaf7e3329d2235b', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('09c384bc560dd8a66f86016946c69a91', '9928f308f93d5de0a9343796cb3bf213', 'f2abfe9444b98748097faaa813e18651', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('09e7db9f54459bcc48d0d3143bfabf8c', '1', '71', '2020-05-22 21:07:50');
INSERT INTO `t_sys_role_perm` VALUES ('0aa58ab52194268525d3931d74af6bb5', '9928f308f93d5de0a9343796cb3bf213', '117', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('0ba3b0e96b1dd92748af4348e98ee734', '9928f308f93d5de0a9343796cb3bf213', '42', '2020-06-11 16:08:11');
INSERT INTO `t_sys_role_perm` VALUES ('0e81ee561f0071e39145e93822021ceb', '9928f308f93d5de0a9343796cb3bf213', '103', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('0ed16cfe1ee26747d2bb133b6567a073', '9928f308f93d5de0a9343796cb3bf213', '122', '2020-06-11 16:08:03');
INSERT INTO `t_sys_role_perm` VALUES ('101', '1', '98', '2020-04-21 10:12:27');
INSERT INTO `t_sys_role_perm` VALUES ('102', '1', '99', '2020-04-21 10:12:27');
INSERT INTO `t_sys_role_perm` VALUES ('103', '1', '101', '2020-04-21 10:12:27');
INSERT INTO `t_sys_role_perm` VALUES ('104', '1', '100', '2020-04-21 10:12:27');
INSERT INTO `t_sys_role_perm` VALUES ('105', '1', '102', '2020-04-21 10:12:27');
INSERT INTO `t_sys_role_perm` VALUES ('106', '1', '103', '2020-04-21 10:12:28');
INSERT INTO `t_sys_role_perm` VALUES ('107', '1', '97', '2020-04-21 10:12:28');
INSERT INTO `t_sys_role_perm` VALUES ('107aca29e6a9721521a6d3bd3bfd6c0d', '1', '108', '2020-05-22 21:07:51');
INSERT INTO `t_sys_role_perm` VALUES ('108', '1', '49', '2020-04-21 10:12:28');
INSERT INTO `t_sys_role_perm` VALUES ('109', '1', '33', '2020-04-21 10:12:28');
INSERT INTO `t_sys_role_perm` VALUES ('110', '1', '34', '2020-04-21 10:12:28');
INSERT INTO `t_sys_role_perm` VALUES ('111', '1', '35', '2020-04-21 10:12:28');
INSERT INTO `t_sys_role_perm` VALUES ('11111', '1', '11111', '2020-09-02 14:40:32');
INSERT INTO `t_sys_role_perm` VALUES ('112', '1', '36', '2020-04-21 10:12:28');
INSERT INTO `t_sys_role_perm` VALUES ('113', '1', '38', '2020-04-21 10:12:29');
INSERT INTO `t_sys_role_perm` VALUES ('114', '1', '39', '2020-04-21 10:12:29');
INSERT INTO `t_sys_role_perm` VALUES ('115', '1', '40', '2020-04-21 10:12:29');
INSERT INTO `t_sys_role_perm` VALUES ('116', '1', '41', '2020-04-21 10:12:29');
INSERT INTO `t_sys_role_perm` VALUES ('117', '1', '37', '2020-04-21 10:12:29');
INSERT INTO `t_sys_role_perm` VALUES ('118', '1', '43', '2020-04-21 10:12:29');
INSERT INTO `t_sys_role_perm` VALUES ('119', '1', '44', '2020-04-21 10:12:29');
INSERT INTO `t_sys_role_perm` VALUES ('120', '1', '45', '2020-04-21 10:12:30');
INSERT INTO `t_sys_role_perm` VALUES ('121', '1', '46', '2020-04-21 10:12:30');
INSERT INTO `t_sys_role_perm` VALUES ('122', '1', '42', '2020-04-21 10:12:30');
INSERT INTO `t_sys_role_perm` VALUES ('123', '1', '47', '2020-04-21 10:12:30');
INSERT INTO `t_sys_role_perm` VALUES ('123456', '1', 'a56736d2d41d7c74e274edf80d197221', '2020-09-04 14:23:35');
INSERT INTO `t_sys_role_perm` VALUES ('123457', '1', 'd73584d2cb1509a59ce060ecf205e96b', '2020-09-04 14:23:35');
INSERT INTO `t_sys_role_perm` VALUES ('123458', '1', '01f3e39c30e63127116bfa66a773389b', '2020-09-04 14:23:35');
INSERT INTO `t_sys_role_perm` VALUES ('127', '1', '86', '2020-04-21 10:12:41');
INSERT INTO `t_sys_role_perm` VALUES ('128', '1', '85', '2020-04-21 10:12:41');
INSERT INTO `t_sys_role_perm` VALUES ('129', '1', '66', '2020-04-21 10:12:41');
INSERT INTO `t_sys_role_perm` VALUES ('13958b359206383884028c7d737545ee', '1', '1a9f8bf4e2c65058a2555b6dc6bc65c3', '2020-06-22 17:48:29');
INSERT INTO `t_sys_role_perm` VALUES ('140', '1', '48', '2020-04-21 15:30:22');
INSERT INTO `t_sys_role_perm` VALUES ('14ca7c0b1334885a3b8404cc4901a11c', '9928f308f93d5de0a9343796cb3bf213', '31772ac9552a5bc3f98bd799eb6d3613', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('160f2abea998c2bbdb28851b999524f7', '9928f308f93d5de0a9343796cb3bf213', '49325a039446b3b8de897de128a3d5f5', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('182', '1', '98', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('183', '1', '99', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('184', '1', '101', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('185', '1', '100', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('186', '1', '102', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('187', '1', '103', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('188', '1', '97', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('189', '1', '1', '2020-04-27 16:56:25');
INSERT INTO `t_sys_role_perm` VALUES ('18e1e4ebb13b3b61a98f0b2e91d8d753', '9928f308f93d5de0a9343796cb3bf213', '78', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('1a281afeb4ecae991ea6ddb148e1f726', '9928f308f93d5de0a9343796cb3bf213', '66', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('1fe6706bbedd4f90871527dae93aec78', '9928f308f93d5de0a9343796cb3bf213', 'c3b3e5008ae5b337269fe723f76d0b77', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('205176cc40d83054ec97918f269e7a39', '1', '00cf3ce314c13c127e6d3a3f19d5cf38', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('2101d0e09fc0aeaa9c31add76480a6e7', '5f68a6501fa30880d10e0ec6fd5987f0', '84', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('2190d46b30d0cecbcd426e45d3386caf', '9928f308f93d5de0a9343796cb3bf213', '74', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('21dd355496d1bc9532c9ff9a0b3eb0d8', '9928f308f93d5de0a9343796cb3bf213', '0c28be14940a9f219eb64f1c7b0ab508', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('225a3f46997a67f17de6d833a742c62f', '9928f308f93d5de0a9343796cb3bf213', '38', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('24488db0c2dd724d7fff60aecb0224c7', '9928f308f93d5de0a9343796cb3bf213', '93', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('24aeda53b8e139c84e55046d02caf2b6', '1', '76', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('251606b5c2ca202a4023ca08545f11b1', '1', '106', '2020-05-22 21:07:51');
INSERT INTO `t_sys_role_perm` VALUES ('25d02337a73f32510932abf73d4aaa94', '1', '122', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('266310559bdf64af1df7a912476b84ce', '9928f308f93d5de0a9343796cb3bf213', '109', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('2860cb4277faee942c78c24de982b4a7', '9928f308f93d5de0a9343796cb3bf213', '87', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('28e08e6fb1f980ad3280be98d3545e8d', '1', '96772d0bd8e1db894665ce6bd5446c50', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('295b12e7a01b7aa55a5075851b093773', '5f68a6501fa30880d10e0ec6fd5987f0', '112', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('2c4c966561f6767140b152bbbb12b09b', '1', '8214ba1a46a31551478e6845fea67305', '2020-05-28 16:55:09');
INSERT INTO `t_sys_role_perm` VALUES ('2c5347996bb39916e59fddb2d0b3619e', '1', '115', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('2c87a6b62a97f63d03c296ef771f1c79', '5f68a6501fa30880d10e0ec6fd5987f0', '65', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('2cc8014d1beb0727c697ca31c0c188e6', '9928f308f93d5de0a9343796cb3bf213', '86', '2020-06-11 16:08:03');
INSERT INTO `t_sys_role_perm` VALUES ('2ce85428a8aeb0a1edf189e6f4a3dd2f', '1', '112', '2020-05-22 21:07:50');
INSERT INTO `t_sys_role_perm` VALUES ('2d2c678b32dba045fcdb55151273b0c9', '1', '1551bce6d50dff2378a4fbf07f660060', '2020-06-22 17:48:29');
INSERT INTO `t_sys_role_perm` VALUES ('2de2483f1dc69b176ba71f88f631bfb1', '57ba7c794b5e771f1513703dc35c2666', '60', '2020-06-19 09:31:39');
INSERT INTO `t_sys_role_perm` VALUES ('2ebdb947d8bcd20f63dbb8afb56d5120', '9928f308f93d5de0a9343796cb3bf213', 'feaec49c353876a5e11d5c61a2415755', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('2f0b0d90fec106ff7593a86b5df350c0', '1', '95', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('33f8cb1e933db1758db822d1fed13d44', '9928f308f93d5de0a9343796cb3bf213', '35', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('33faa486bc627ab0e4cb7fe35e745587', '9928f308f93d5de0a9343796cb3bf213', '91', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('3405c2858576a286a06b8b7598edde66', '9928f308f93d5de0a9343796cb3bf213', '34', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('358ba9657091bb5cf86c0d45c99ce1b8', '9928f308f93d5de0a9343796cb3bf213', 'ff0fca3e23d374715fee0c71cb2d6f84', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('363f5932423d184b2ce58fb2ba888f02', '1', '4c49efe3819eac09ccaf7e3329d2235b', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('364a8c28ce410ef6e83dd9f9eaedc8e0', '9928f308f93d5de0a9343796cb3bf213', '72', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('36be049d9bb964a74f6d7c9beee6e315', '9928f308f93d5de0a9343796cb3bf213', '118', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('36e7c557b95ddcf04ba8bb3ebbfd2cf9', '5f68a6501fa30880d10e0ec6fd5987f0', '117', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('3889ba32fb325baa8b914fe3fc2f38ad', '9928f308f93d5de0a9343796cb3bf213', '114', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('391addf918a7f233eb42c20422724785', '9928f308f93d5de0a9343796cb3bf213', '79', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('393b45673ee3d19df1ae23bff6902e4a', '1', '80', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('3a13918c3a340448d4131797bb36cbc1', '9928f308f93d5de0a9343796cb3bf213', '111', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('3a1659eeabbda764979058764c149ca9', '9928f308f93d5de0a9343796cb3bf213', '33', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('3f017690afc5054743ee9cc7052d3e14', '1', 'a49fc59289b63ec28cfef9661393864e', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('3fa96bd5d319e5d5adeb8ea4c9401f54', '9928f308f93d5de0a9343796cb3bf213', 'e7ebdede89a6128aefec601c253c7d67', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('4040c97496d4275a5be0306a4dbe4a88', '1', '75', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('40b172b6e596b0fb4c2b6d9deebd0ed2', '9928f308f93d5de0a9343796cb3bf213', '75', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('40e3d32e56eb4197ca49cd7015ada57b', '5f68a6501fa30880d10e0ec6fd5987f0', '115', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('415905eb9b34a9fb90c5d4b0de75ef29', '9928f308f93d5de0a9343796cb3bf213', '113', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('45', '1', '32', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('452df193817a87cc44f55eb8fbb4c1d9', '5f68a6501fa30880d10e0ec6fd5987f0', '119', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('46', '1', '33', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('46a7bb894c1e5a211932c96caa4791cf', '9928f308f93d5de0a9343796cb3bf213', '150339e66b0e211a00b9a72fd5aba838', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('47', '1', '34', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('4728787ac746e649a7c24f12bb164658', '1', '68', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('47dee557f6fcc3e498fbb82b44bab395', '9928f308f93d5de0a9343796cb3bf213', '43', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('48', '1', '35', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('480132a33c88f1c95d4dfab090c519a5', '1', '110', '2020-05-22 21:07:51');
INSERT INTO `t_sys_role_perm` VALUES ('48ef1545f2f36194906027b07550553f', '9928f308f93d5de0a9343796cb3bf213', '46', '2020-06-11 16:08:11');
INSERT INTO `t_sys_role_perm` VALUES ('49', '1', '38', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('4ae4f0eae29e283a8d2a0c4610eef515', '1', '826deed9a1ed84edf01a4f983773fe2d', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('4ae83682085cb49dcff33c174da4c337', '9928f308f93d5de0a9343796cb3bf213', '106', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('4b35f60ff9f633728d99cede4f49164a', '1', '49325a039446b3b8de897de128a3d5f5', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('4b4bdf41e9d385c4449c1e8855caa6a3', '9928f308f93d5de0a9343796cb3bf213', '105', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('4caf583eac9be7e049e463444203da44', '9928f308f93d5de0a9343796cb3bf213', '98', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('4f1796c064b1df86f6d41a04ce4c7e58', '1', '84bcf5ff3a116e333ef8b6d6221ec80c', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('4f43028fccbb4e7ab4818f7b13029d79', '1', '45072a0978db8802cf8c0d88d28f73e8', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('50', '1', '39', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('502d1db54ce424536e537f3e54ce483a', '9928f308f93d5de0a9343796cb3bf213', '102', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('51', '1', '40', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('52', '1', '41', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('520ff350260600d35f61136821cca0a8', '9928f308f93d5de0a9343796cb3bf213', '62', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('528b3223050303f97467c0ded9998e44', '9928f308f93d5de0a9343796cb3bf213', '65', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('54d8e1d9add1c307ae9a57dd048274ac', '9928f308f93d5de0a9343796cb3bf213', '61', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('55', '1', '43', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('5547169668ad8b385b9a24d9bfebab05', '1', '61', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('55b361f550a8e00f07b13de69940a398', '1', '118', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('5657079420bebf4e5f62cd7f340c9e7e', '1', 'ff0fca3e23d374715fee0c71cb2d6f84', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('57c6917b4266c1a5f25567f0c18a4125', '5f68a6501fa30880d10e0ec6fd5987f0', '109', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('580b7a19f3a002f27853f1e138acdb6a', '1', '306a9795ae65f27d0065d1ea0ad9297a', '2020-05-29 10:20:50');
INSERT INTO `t_sys_role_perm` VALUES ('59dd76b83f564da87e8174babb13fa77', '5f68a6501fa30880d10e0ec6fd5987f0', '101', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('5a060af249b533243739652ad2df45b6', '57ba7c794b5e771f1513703dc35c2666', '112', '2020-06-19 09:31:39');
INSERT INTO `t_sys_role_perm` VALUES ('5e402a321e3ed47d3ae5d336f4fd6e4d', '1', '89', '2020-05-28 16:56:11');
INSERT INTO `t_sys_role_perm` VALUES ('5e9d904d75a727ef8a1c16d850df127c', '5f68a6501fa30880d10e0ec6fd5987f0', '73', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('5f7f56ccd9c4b8b13f474b19a34229cf', '1', '70', '2020-05-22 21:07:51');
INSERT INTO `t_sys_role_perm` VALUES ('5fc7debc609cc0d251c01b10c12c623a', '9928f308f93d5de0a9343796cb3bf213', 'a49fc59289b63ec28cfef9661393864e', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('61', '1', '36', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('62', '1', '37', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('63', '1', '42', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('64', '1', '47', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('65d0e9b70a56219940d6a03c321bfced', '1', '84', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('66', '1', '49', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('67cac827e1e19ff70df5cf57f0628872', '1', '62', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('6b2dbd9048e09e5665d4ba4d3bf926fd', '1', '88', '2020-05-28 16:56:12');
INSERT INTO `t_sys_role_perm` VALUES ('6b37d430e51b879fa8ae737da21e248d', '1', 'e4ac8cf2a0c7ca5ab9dc7820a0c3358e', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('6d2d0cf79f7fdec50bc3960ecb5f286d', '57ba7c794b5e771f1513703dc35c2666', '68', '2020-06-11 16:41:32');
INSERT INTO `t_sys_role_perm` VALUES ('6dcfbda29238a7566900183e60516005', '9928f308f93d5de0a9343796cb3bf213', '119', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('6ff13fec163cf4a89bd2dda0712a6476', '9928f308f93d5de0a9343796cb3bf213', '94', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('6fff431d1804877a4594c5456dea8201', '9928f308f93d5de0a9343796cb3bf213', '44', '2020-06-11 16:08:11');
INSERT INTO `t_sys_role_perm` VALUES ('70', '1', '50', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('70e8cc4af1eb715e2a15e06209dcc419', '9928f308f93d5de0a9343796cb3bf213', '88', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('73', '1', '53', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('73b0c4d3e59707753124da4f48c8099a', '57ba7c794b5e771f1513703dc35c2666', '70', '2020-06-19 09:31:38');
INSERT INTO `t_sys_role_perm` VALUES ('7460b03e5d058996edb3e86582013961', '1', '79', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('75', '1', '51', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('76', '1', '52', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('77', '1', '48', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('770924ccb8913207ce2dd22c5ca94f39', '1', '150339e66b0e211a00b9a72fd5aba838', '2020-05-29 17:14:12');
INSERT INTO `t_sys_role_perm` VALUES ('779d8d789ff55f74475d7b80fabacf47', '9928f308f93d5de0a9343796cb3bf213', '97', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('78310b52b72164530641ee7850afb1eb', '57ba7c794b5e771f1513703dc35c2666', '93', '2020-06-11 16:41:31');
INSERT INTO `t_sys_role_perm` VALUES ('7cec37b7d5b457c2aacdee98665a8b1e', '1', '81', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('7d6db63d3440c4f52d5aecd776dba68e', '9928f308f93d5de0a9343796cb3bf213', '76', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('7f3e040d522991947d426f6d44fdeed0', '9928f308f93d5de0a9343796cb3bf213', '71', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('80', '1', '44', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('81', '1', '45', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('811eff7aaef45db6ae772a05054bb71e', '5f68a6501fa30880d10e0ec6fd5987f0', '106', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('82', '1', '46', '2020-03-24 17:31:17');
INSERT INTO `t_sys_role_perm` VALUES ('85b7d03d425f07c4ec903d48c3a42bd8', '1', '82', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('86a363446bd64fc147799323d0e6465b', '5f68a6501fa30880d10e0ec6fd5987f0', '110', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('8741bd422e84a43d41a0f93a1428f8ac', '5f68a6501fa30880d10e0ec6fd5987f0', '113', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('87bab653fa339a1b2d381ff9fe85dc1e', '1', '92', '2020-05-28 16:56:11');
INSERT INTO `t_sys_role_perm` VALUES ('8b5e8c94dc1c3980a8d0f3b91659a2ce', '9928f308f93d5de0a9343796cb3bf213', '1', '2020-06-11 16:08:11');
INSERT INTO `t_sys_role_perm` VALUES ('8b7abd302cd9808a37f8475e9ecb2535', '9928f308f93d5de0a9343796cb3bf213', '43376c0adaed31ad56e65fe1ac36d5cc', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('8cd36b667b934d2b1da31e55ef81cb8e', '1', '113', '2020-05-22 21:07:50');
INSERT INTO `t_sys_role_perm` VALUES ('8fe94a4706dc9c675ffb4f7aacdb4541', '5f68a6501fa30880d10e0ec6fd5987f0', '120', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('900ae7beef1facc0305d23a2ceb7e316', '9928f308f93d5de0a9343796cb3bf213', '73', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('911444daeacf0e5923e68bb48facecb7', '1', 'e7ebdede89a6128aefec601c253c7d67', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('9376dc2d56e3bb206df32f946d3d24fa', '1', '105', '2020-05-22 21:07:50');
INSERT INTO `t_sys_role_perm` VALUES ('9479f97082fa2830709ffe9be3b12ec2', '9928f308f93d5de0a9343796cb3bf213', '60', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('94df5194b966ea9589ed6cccf0edcab8', '9928f308f93d5de0a9343796cb3bf213', '63', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('94e70eba9c3a034753a674a7a422f18d', '9928f308f93d5de0a9343796cb3bf213', '108', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('94e87d9e5ec886551f7789daa92dc030', '5f68a6501fa30880d10e0ec6fd5987f0', '71', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('95292870a24083e7a357898a50c97f44', '1', '93', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('982efc163020db450a3e9ef6a30b218b', '9928f308f93d5de0a9343796cb3bf213', '115', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('9b7267d4edc954c98a56eecadc152680', '9928f308f93d5de0a9343796cb3bf213', '32cc63f93fdd0c15d4f871a58478a5c9', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('9cd543aded0a926c36567b1b50f62db2', '1', '91', '2020-05-28 16:56:11');
INSERT INTO `t_sys_role_perm` VALUES ('9d78af73f53c02e58cbf30c717636bd2', '1', '87', '2020-05-28 16:56:11');
INSERT INTO `t_sys_role_perm` VALUES ('9eb472c327b950f2daf946b8a7b79daf', '9928f308f93d5de0a9343796cb3bf213', '95', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('9f6fe550cc6c074e01e2669c0a3c380f', '1', '94', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('a032ff40566f98cffa41f16cb41a54f9', '9928f308f93d5de0a9343796cb3bf213', '40', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('a2541dfdfb680ceab59974ddb7853f77', '1', '08f38fad3414795e6b3ccd6b77e705cb', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('a4056346bde7db3413cbf150c9dd537b', '5f68a6501fa30880d10e0ec6fd5987f0', '61', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('a8b6cfe865bcd8b4086795617da51967', '5f68a6501fa30880d10e0ec6fd5987f0', '114', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('a9432968f3cc4e39b96c09bbd85b941f', '1', '77', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('a9a0926253ae81e213346471235a7a6e', '5f68a6501fa30880d10e0ec6fd5987f0', '60e30239441256f79eb570a159306169', '2020-05-18 15:41:44');
INSERT INTO `t_sys_role_perm` VALUES ('aa183fc8463925444c32d70ea9cf2576', '9928f308f93d5de0a9343796cb3bf213', '37', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('aa35bc27005e6a9eb10f1ac251aa7934', '1', '31772ac9552a5bc3f98bd799eb6d3613', '2020-05-23 15:50:17');
INSERT INTO `t_sys_role_perm` VALUES ('aaf48d44c6518257c954da9873a36f9b', '9928f308f93d5de0a9343796cb3bf213', '36', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('afdc948d6099d4d4f5ef7282efefd042', '9928f308f93d5de0a9343796cb3bf213', '85', '2020-06-11 16:08:03');
INSERT INTO `t_sys_role_perm` VALUES ('b0a1964ee03851265595f45eccd25231', '1', '72', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('b1441b6010bd279c0047836b90f1c88a', '9928f308f93d5de0a9343796cb3bf213', '00cf3ce314c13c127e6d3a3f19d5cf38', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('b196cb99b6943554422b26c88b17258a', '5f68a6501fa30880d10e0ec6fd5987f0', '108', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('b21e4318436fa3cdb126c0c2303fff87', '57ba7c794b5e771f1513703dc35c2666', '71', '2020-06-19 09:31:39');
INSERT INTO `t_sys_role_perm` VALUES ('b3c82cd925698b7b69ebe2c0d8a09d6d', '9928f308f93d5de0a9343796cb3bf213', '49', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('b40feb30501ac8a4fcd586fedcd02661', '9928f308f93d5de0a9343796cb3bf213', '110', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('b65085b6c56b8a3aa26e59418b5e6d51', '1', '60', '2020-05-22 21:07:51');
INSERT INTO `t_sys_role_perm` VALUES ('b6b35cc6d35d75ba52abf99d7fc609e8', '9928f308f93d5de0a9343796cb3bf213', '70', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('b7a893720aced286e4c05c1c0217bed6', '9928f308f93d5de0a9343796cb3bf213', '8214ba1a46a31551478e6845fea67305', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('b89605e2441dd7b6b6cdb3f9cfe68218', '9928f308f93d5de0a9343796cb3bf213', '100', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('ba13c3cea5c4183b172b30f79107a87a', '5f68a6501fa30880d10e0ec6fd5987f0', '83', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('bb12ef7a45b4e39c5783a1512553c7bb', '9928f308f93d5de0a9343796cb3bf213', '90', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('bc3b587e85eed3e9dafad27db2c3dc7b', '1', '78', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('bcc07d4eee9adbbdb6cda69c495776d5', '1', 'd8a485dc34ca488e334916a39883cb79', '2020-06-22 17:48:29');
INSERT INTO `t_sys_role_perm` VALUES ('bd4b726afdb973a5e314627d3d6bbfce', '9928f308f93d5de0a9343796cb3bf213', '826deed9a1ed84edf01a4f983773fe2d', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('bd727b7c7a51d670984d87ee8638c955', '9928f308f93d5de0a9343796cb3bf213', 'bfe3037184af5d476fac81af98e35df0', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('bf666c00b3d7f27e65553c246b157474', '9928f308f93d5de0a9343796cb3bf213', '84bcf5ff3a116e333ef8b6d6221ec80c', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('bfea09562165b46545ff5be47376206c', '9928f308f93d5de0a9343796cb3bf213', '80', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('c065e5894749b8b7575ed2a48025b84b', '9928f308f93d5de0a9343796cb3bf213', 'fc02b38e8dc0e0305510ba4a8a785830', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('c160edd9f0120cad716f87fbd3d50950', '57ba7c794b5e771f1513703dc35c2666', 'f2abfe9444b98748097faaa813e18651', '2020-06-19 09:31:39');
INSERT INTO `t_sys_role_perm` VALUES ('c412a07bac165782cc96432fbb1998f1', '5f68a6501fa30880d10e0ec6fd5987f0', '111', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('c5313aa0378bc10f466e93fae395e170', '9928f308f93d5de0a9343796cb3bf213', '3542db370cb6becb23e146e541c16771', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('c5c505243b68a416fb78ab01e092e705', '1', 'f2abfe9444b98748097faaa813e18651', '2020-06-22 17:48:29');
INSERT INTO `t_sys_role_perm` VALUES ('c69db3f0e29e3ada53eeeabbd70a065e', '1', '109', '2020-05-22 21:07:51');
INSERT INTO `t_sys_role_perm` VALUES ('c75a96cec2566d548b39aa9e69dc5692', '1', '43376c0adaed31ad56e65fe1ac36d5cc', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('c7f9975009e63b58f448ee36ad434f23', '1', '117', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('c80e35364adb7de7e13c0465c9b3dca5', '1', '0c28be14940a9f219eb64f1c7b0ab508', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('c9483d37decb0f9369e22e134341a029', '57ba7c794b5e771f1513703dc35c2666', '113', '2020-06-19 09:31:39');
INSERT INTO `t_sys_role_perm` VALUES ('ca77ca8f19ebba813f4fe460ad2871eb', '9928f308f93d5de0a9343796cb3bf213', '84', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('cb10a9aa2ccc9fbc4e50f74327cc53d1', '1', '855030a7948ba499e9500f655b1d1898', '2020-06-03 11:38:02');
INSERT INTO `t_sys_role_perm` VALUES ('ce269d7c77f354226b4212772c79175d', '9928f308f93d5de0a9343796cb3bf213', '64', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('cede63deb7afd8eea6a90d90b06559d7', '9928f308f93d5de0a9343796cb3bf213', '855030a7948ba499e9500f655b1d1898', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('cffd81a89d86c5a41686b83684fba830', '9928f308f93d5de0a9343796cb3bf213', '92', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('d082b691b157e8c254bfd1d8b935956d', '9928f308f93d5de0a9343796cb3bf213', '96772d0bd8e1db894665ce6bd5446c50', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('d2e84491aaf0791722a2b78501c664c1', '5f68a6501fa30880d10e0ec6fd5987f0', '72', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('d3415fa1ab0ddfcf4b86c0c8e19b1736', '9928f308f93d5de0a9343796cb3bf213', '82', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('d6758577a40872752b51d8c982063c0e', '9928f308f93d5de0a9343796cb3bf213', '41', '2020-06-11 16:08:10');
INSERT INTO `t_sys_role_perm` VALUES ('d704fd5d2f576bdd4747da3def56adf1', '9928f308f93d5de0a9343796cb3bf213', '116', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('d8a5fb92b7b2b48b04f854af20467db5', '9928f308f93d5de0a9343796cb3bf213', 'de2057867405e2321adb298efda5bc11', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('d94aaa5669451d443630585245e335cd', '9928f308f93d5de0a9343796cb3bf213', 'd09ac634f8f08b6bae5cbf097a6784f7', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('da26dd3c8c7dcb4d0b26bf1dfc17a288', '1', '90', '2020-05-28 16:56:12');
INSERT INTO `t_sys_role_perm` VALUES ('da4cb97ca53a7f1ba7d55e579038c7fe', '1', '65', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('db1b48a427bf7c0104210673cca6d0bc', '1', '9b86edef37a8c883413f2ec95b584be7', '2020-05-25 15:04:52');
INSERT INTO `t_sys_role_perm` VALUES ('dd95d0bdbe1136a721bb26c7c2c0af6e', '9928f308f93d5de0a9343796cb3bf213', '120', '2020-06-11 16:08:07');
INSERT INTO `t_sys_role_perm` VALUES ('deb63ac22b54dbdde255ec7aaf208d82', '9928f308f93d5de0a9343796cb3bf213', '89', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('e064c936392ad3e9e574c99444024e35', '1', '74', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('e14f605c6d4fe2ed5d5c247cd3c5d222', '9928f308f93d5de0a9343796cb3bf213', '68', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('e349afd3fcf0b4953b4953d74e9a4821', '9928f308f93d5de0a9343796cb3bf213', '99', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('e61bac9964fe164a9780a236405a78fb', '1', '603b8c102ed42c5d93ca0553cb79cc33', '2020-05-23 15:47:42');
INSERT INTO `t_sys_role_perm` VALUES ('e6480ffeacfdbd927392eccb591f0642', '57ba7c794b5e771f1513703dc35c2666', '95', '2020-06-11 16:41:32');
INSERT INTO `t_sys_role_perm` VALUES ('e758332c77637f27bc9b1b3b45387b58', '57ba7c794b5e771f1513703dc35c2666', '105', '2020-06-19 09:31:38');
INSERT INTO `t_sys_role_perm` VALUES ('e77967e6c89a0d823da581e52580de0d', '9928f308f93d5de0a9343796cb3bf213', '00f4601efc80a560b36503214aa99c62', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('e86e1eac012a59888a57a2297905f9e9', '9928f308f93d5de0a9343796cb3bf213', '112', '2020-06-11 16:08:05');
INSERT INTO `t_sys_role_perm` VALUES ('e8b37d2d6e56fb29ee141d50d18957ba', '1', '64', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('e90ce1220381ada3721ffb24a6c0a18d', '9928f308f93d5de0a9343796cb3bf213', '45', '2020-06-11 16:08:11');
INSERT INTO `t_sys_role_perm` VALUES ('eac83a8d3da17427227b812ce7e19514', '57ba7c794b5e771f1513703dc35c2666', 'feaec49c353876a5e11d5c61a2415755', '2020-06-11 16:41:32');
INSERT INTO `t_sys_role_perm` VALUES ('eb5cf0a03352b062162d6bc3ac318af0', '1', '116', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('eca800f7f67986768f1a514c44cd8b10', '9928f308f93d5de0a9343796cb3bf213', '08f38fad3414795e6b3ccd6b77e705cb', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('ecbe662b1876fc737f33149e230268cd', '1', '114', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('ed59f6df914ea459881dc5665983eafa', '1', '83', '2020-05-22 21:08:56');
INSERT INTO `t_sys_role_perm` VALUES ('edffe7e09ce1973fca1f5ed265bcc078', '1', 'bfe3037184af5d476fac81af98e35df0', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('ef7e4322989e3b00b3e89a08a568b85b', '1', '73', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('f26e52f9b7c0a61b4d336b20f4619437', '1', '3542db370cb6becb23e146e541c16771', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('f36f496f53c47d37682bb8ae7728f02e', '9928f308f93d5de0a9343796cb3bf213', '81', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('f5d483a5462b773cd0972667e13a6e3e', '1', '32cc63f93fdd0c15d4f871a58478a5c9', '2020-06-05 11:25:36');
INSERT INTO `t_sys_role_perm` VALUES ('f73b4e50a822195ea1139789eef5005e', '1', '08a60f362543aaffefbb428fc948b147', '2020-05-23 15:31:46');
INSERT INTO `t_sys_role_perm` VALUES ('f757ba16c05afb293874231d4396d984', '9928f308f93d5de0a9343796cb3bf213', '45072a0978db8802cf8c0d88d28f73e8', '2020-06-11 16:08:08');
INSERT INTO `t_sys_role_perm` VALUES ('f8b52b9080d349499cbbb1217a73dc20', '1', '63', '2020-05-22 21:08:57');
INSERT INTO `t_sys_role_perm` VALUES ('f991b7d303a6ad89c66233e6932f8ef6', '5f68a6501fa30880d10e0ec6fd5987f0', '105', '2020-05-18 15:36:01');
INSERT INTO `t_sys_role_perm` VALUES ('fabf129bacbc46c958831c51057ee9aa', '5f68a6501fa30880d10e0ec6fd5987f0', '70', '2020-05-18 15:36:04');
INSERT INTO `t_sys_role_perm` VALUES ('facd44b007992e7ee6235be62e2069ae', '9928f308f93d5de0a9343796cb3bf213', '67', '2020-06-11 16:08:04');
INSERT INTO `t_sys_role_perm` VALUES ('fae6a6181f2dd455311c8546b3074e68', '9928f308f93d5de0a9343796cb3bf213', 'e4ac8cf2a0c7ca5ab9dc7820a0c3358e', '2020-06-11 16:08:06');
INSERT INTO `t_sys_role_perm` VALUES ('fc1c9060bcb3111de1faf51824955c63', '1', '67', '2020-05-28 16:56:12');
INSERT INTO `t_sys_role_perm` VALUES ('fc6a19704844b6705843b32ef948aba6', '9928f308f93d5de0a9343796cb3bf213', '08a60f362543aaffefbb428fc948b147', '2020-06-11 16:08:09');
INSERT INTO `t_sys_role_perm` VALUES ('ffa864b7ea94fffe18b96d89088caece', '5f68a6501fa30880d10e0ec6fd5987f0', '116', '2020-05-18 15:36:04');
COMMIT;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `nick` varchar(50) DEFAULT NULL COMMENT '昵称',
  `salt` varchar(64) DEFAULT NULL COMMENT '密码盐',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `state` int(11) DEFAULT '0' COMMENT '0：有效\n1：无效',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `id_card` varchar(64) DEFAULT NULL COMMENT '身份证',
  `dept_id` varchar(64) NOT NULL COMMENT '部门编号',
  `email` varchar(300) DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `phone` varchar(100) DEFAULT NULL COMMENT '办公电话',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别 1、男  2、女',
  `sign` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `wx_openid` varchar(100) DEFAULT NULL COMMENT '绑定的微信号',
  `mobile_imei` varchar(100) DEFAULT NULL COMMENT '绑定的手机串号',
  `user_type` varchar(16) DEFAULT NULL COMMENT '用户类型',
  `ref_code` varchar(64) DEFAULT NULL COMMENT '用户类型引用编号',
  `ref_name` varchar(100) DEFAULT NULL COMMENT '用户类型引用姓名',
  `mgr_type` char(1) DEFAULT NULL COMMENT '管理员类型（0非管理员 1系统管理员  2二级管理员）',
  `pwd_security_level` decimal(1,0) DEFAULT NULL COMMENT '密码安全级别（0初始 1很弱 2弱 3安全 4很安全）',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `pwd_update_record` varchar(1000) DEFAULT NULL COMMENT '密码修改记录',
  `pwd_question` varchar(200) DEFAULT NULL COMMENT '密保问题',
  `pwd_question_answer` varchar(200) DEFAULT NULL COMMENT '密保问题答案',
  `pwd_question_2` varchar(200) DEFAULT NULL COMMENT '密保问题2',
  `pwd_question_answer_2` varchar(200) DEFAULT NULL COMMENT '密保问题答案2',
  `pwd_question_3` varchar(200) DEFAULT NULL COMMENT '密保问题3',
  `pwd_question_answer_3` varchar(200) DEFAULT NULL COMMENT '密保问题答案3',
  `pwd_quest_update_date` datetime DEFAULT NULL COMMENT '密码问题修改时间',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `freeze_date` datetime DEFAULT NULL COMMENT '冻结时间',
  `freeze_cause` varchar(200) DEFAULT NULL COMMENT '冻结原因',
  `user_weight` decimal(8,0) DEFAULT '0' COMMENT '用户权重（降序）',
  `status` char(1) DEFAULT NULL COMMENT '状态（0正常 1删除 2停用 3冻结）',
  `corp_code` varchar(64) DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) DEFAULT 'JeeSite' COMMENT '租户名称',
  `extend_s1` varchar(500) DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `area_code` varchar(64) DEFAULT NULL COMMENT '区域代码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_name_unique` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_user` VALUES ('1', 'mz', '736d8af9e93eae47edbbc4460caa5970', '超级管理员', 'c47b9e5af3923c4ace502f70cb511be1', '2020-03-24 17:31:17', '2020-06-03 23:19:34', 0, 'https://www.zhsq010.com/images/mz/avatar/mz.jpg', NULL, '1', NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '0', 'JeeSite', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_user_role` VALUES ('1', '1', '1', '2020-03-24 17:31:17');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
