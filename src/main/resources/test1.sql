/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : test1

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 07/27/2018 09:33:14 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_acl`
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '权限code',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限所在的权限模块的id',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '请求的url,可以填正则表达式',
  `type` tinyint(1) NOT NULL DEFAULT '3' COMMENT '类型,1:菜单,2:按钮,3:其他',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0:冻结 1:正常',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '权限模块层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限模块在当前层级下的顺序,有小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限';

-- ----------------------------
--  Table structure for `sys_aclmodule`
-- ----------------------------
DROP TABLE IF EXISTS `sys_aclmodule`;
CREATE TABLE `sys_aclmodule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限模块的id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '权限模块层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限模块在当前层级下的顺序,有小到大',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态,0:冻结,1:启用',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限模块';

-- ----------------------------
--  Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT ' 部门名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级部门的id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '部门在当前层级下的顺序,有小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
--  Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '权限更新类型 1:部门,2:用户,3:权限模块,4:权限,5:角色,6:角色用户关系,7角色权限关系',
  `target_id` int(11) NOT NULL COMMENT '基于type后指定的对象id',
  `old_value` text COMMENT '旧值',
  `new_value` text COMMENT '新值',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者ip',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '当前是否复原过 0:没有 1:复原过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '角色 1:管理员 2:其他备用',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '角色状态 0:冻结 1:正常',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
--  Table structure for `sys_role_acl`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次操作人的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色—权限';

-- ----------------------------
--  Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色—用户';

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `telephone` varchar(13) NOT NULL DEFAULT '' COMMENT '电话',
  `mail` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '密码',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '所在部门id',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0:冻结,1:正常,2:删除)',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

SET FOREIGN_KEY_CHECKS = 1;
