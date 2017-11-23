/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2017-11-23 17:41:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for learn_resource
-- ----------------------------
DROP TABLE IF EXISTS `learn_resource`;
CREATE TABLE `learn_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `title` varchar(100) DEFAULT NULL COMMENT '描述',
  `url` varchar(100) DEFAULT NULL COMMENT '地址链接',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1031 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of learn_resource
-- ----------------------------
INSERT INTO `learn_resource` VALUES ('999', '官方SpriongBoot例子', '官方SpriongBoot例子', 'https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples');
INSERT INTO `learn_resource` VALUES ('1000', '龙果学院', 'Spring Boot 教程系列学习', 'http://www.roncoo.com/article/detail/124661');
INSERT INTO `learn_resource` VALUES ('1001', '嘟嘟MD独立博客', 'Spring Boot干货系列', 'http://tengj.top/');
INSERT INTO `learn_resource` VALUES ('1002', '后端编程嘟', 'Spring Boot视频教程', 'http://www.toutiao.com/m1559096720023553/');
INSERT INTO `learn_resource` VALUES ('1029', 'qe', 'qwe', 'qwe');

-- ----------------------------
-- Table structure for user_
-- ----------------------------
DROP TABLE IF EXISTS `user_`;
CREATE TABLE `user_` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL COMMENT '是否启用',
  `id_passed` bit(1) NOT NULL COMMENT '是否通过身份验证',
  `mobile_passed` bit(1) NOT NULL COMMENT '是否通过手机验证',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `user_name` varchar(64) DEFAULT '' COMMENT '用户名(HXB+随机数)',
  `utm_source` varchar(128) DEFAULT NULL COMMENT '渠道,目前只有至信',
  `invite_uid` int(11) DEFAULT NULL COMMENT '直接邀请人userId',
  `intention` varchar(16) DEFAULT NULL COMMENT '借款或理财或系统用户',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `source` varchar(16) DEFAULT NULL COMMENT '注册来源',
  `source_value` varchar(32) DEFAULT NULL COMMENT '销售人员代码',
  `promotion` varchar(128) DEFAULT NULL COMMENT '推广来源',
  `account_id` int(11) DEFAULT NULL COMMENT '托管银行的账户id',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `ref_id` varchar(255) DEFAULT NULL COMMENT '外部渠道的user表唯一键',
  `invite_role` varchar(255) DEFAULT NULL COMMENT '用户角色(普通用户CUSTOMER,员工EMPLOYEE,销售SALES)',
  `invite_serial` varchar(255) DEFAULT NULL COMMENT '邀请码',
  `market_source` varchar(64) DEFAULT NULL COMMENT '市场来源  app端Android设备区分小米市场、华为市场等',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT 'mysql数据库乐观锁',
  `create_time` datetime DEFAULT NULL COMMENT '数据库创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '数据库更新时间',
  PRIMARY KEY (`user_id`),
  KEY `idx_user_source_value` (`source_value`)
) ENGINE=InnoDB AUTO_INCREMENT=1245020 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user_
-- ----------------------------
INSERT INTO `user_` VALUES ('136683', '', '', '', '2017-08-17 12:14:35', 'hxb01922536', 'from-website', '136689', 'LENDER', '13810101321', null, '201610200042', null, '20', null, null, 'EMPLOYEE', 'TMHYIH8HF', null, '1', null, null);
INSERT INTO `user_` VALUES ('136684', '', '', '', '2017-08-17 12:19:05', 'hxb27453677', 'from-website', '138387', 'LENDER', '18221111340', null, '201711130074', null, '8', null, null, 'EMPLOYEE', 'R3DQUJN87', null, '1', null, null);
INSERT INTO `user_` VALUES ('136685', '', '', '', '2017-08-17 12:26:29', 'hxb09020072', 'from-website', '136689', 'LENDER', '13693623948', null, '201703060184', null, '17', null, null, 'EMPLOYEE', '9WP2T03IP', null, '1', null, null);
SET FOREIGN_KEY_CHECKS=1;
