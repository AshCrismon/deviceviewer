/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : deviceviewer

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-08-20 17:28:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dv_device`
-- ----------------------------
DROP TABLE IF EXISTS `dv_device`;
CREATE TABLE `dv_device` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceName` varchar(50) NOT NULL DEFAULT '',
  `ControllerIPs` varchar(35) NOT NULL DEFAULT '',
  `DeviceType` varchar(30) NOT NULL DEFAULT '',
  `Occupier` varchar(25) NOT NULL DEFAULT '',
  `BeginTime` varchar(32) DEFAULT '',
  `EndTime` varchar(32) DEFAULT '',
  `IsOccupied` tinyint(1) NOT NULL DEFAULT '0',
  `Status` tinyint(1) NOT NULL DEFAULT '1',
  `DeviceHostIPs` varchar(35) NOT NULL DEFAULT '',
  `Note` varchar(50) DEFAULT '',
  `ControllerAccount` varchar(35) DEFAULT '',
  `HostAccount` varchar(35) DEFAULT '',
  `DeviceGroup` varchar(35) DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dv_device
-- ----------------------------
INSERT INTO `dv_device` VALUES ('1', 'anonymous吞吞吐吐拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖', '8.46.47.101/102', '18000V1R1单阵列单阵列高端存储系统9999999', '', '', '', '0', '1', '8.46.12.34', 'CI', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('2', 'anonymous', '8.46.47.103/104', '18000V1R1', '', '', '', '0', '1', '8.46.12.34', 'CI', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('3', 'anonymous', '8.46.47.194/195', '18000V1R1', '', '', '', '0', '1', '8.46.12.34', 'CI', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('4', 'anonymous', '8.46.47.132/133', '18000V1R1', '', '', '', '0', '1', '8.46.12.34', 'CI', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('5', 'anonymous', '8.46.47.145/146', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', 'CI', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('6', 'anonymous', '8.46.47.157/158', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('7', 'anonymous', '8.46.47.182/183', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('8', 'anonymous', '8.46.47.168/169', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('9', 'anonymous', '8.46.47.171/172', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('10', 'anonymous', '8.46.47.182/183', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('11', 'anonymous', '8.46.47.191/192', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('12', 'anonymous', '8.46.47.221/222', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('13', 'anonymous', '8.46.47.242/243', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('14', 'anonymous', '8.46.47.56/57', '18000V2R2', 's00423985', '2017-08-20 17:20', '2017-08-20 17:30', '1', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('15', 'anonymous', '8.46.47.145/146', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('16', 'anonymous', '8.46.47.157/158', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('17', 'anonymous', '8.46.47.182/183', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('18', 'anonymous', '8.46.47.168/169', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('19', 'anonymous', '8.46.47.171/172', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('20', 'anonymous', '8.46.47.182/183', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('21', 'anonymous', '8.46.47.191/192', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('22', 'anonymous', '8.46.47.221/222', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('23', 'anonymous', '8.46.47.242/243', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');
INSERT INTO `dv_device` VALUES ('24', 'anonymous', '8.46.47.56/57', '18000V2R2', '', '', '', '0', '1', '8.46.12.34', '', 'admin/Storage@21st', 'root/huawei123', 'xve');

-- ----------------------------
-- Table structure for `dv_log`
-- ----------------------------
DROP TABLE IF EXISTS `dv_log`;
CREATE TABLE `dv_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceId` int(11) NOT NULL,
  `Username` varchar(25) NOT NULL,
  `ActionType` int(1) NOT NULL,
  `BeginTime` varchar(32) DEFAULT '',
  `EndTime` varchar(32) DEFAULT '',
  `CreateTime` varchar(32) NOT NULL,
  `Status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dv_log
-- ----------------------------
INSERT INTO `dv_log` VALUES ('1', '1', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('2', '2', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('3', '3', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('4', '4', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('5', '5', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('6', '6', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('7', '7', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('8', '8', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('9', '9', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('10', '10', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('11', '11', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('12', '12', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('13', '13', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('14', '14', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('15', '15', 's00423985', '1', '2017-08-19 21:26', '2017-08-19 23:00', '2017-08-19 21:33', '1');
INSERT INTO `dv_log` VALUES ('16', '7', 's00423985', '1', '', '', '2017-08-20 16:32', '1');
INSERT INTO `dv_log` VALUES ('17', '1', 's00423985', '1', '2017-08-20 16:55', '2017-08-21 16:55', '2017-08-20 16:58', '1');
INSERT INTO `dv_log` VALUES ('18', '1', 's00423985', '1', '', '', '2017-08-20 16:59', '1');
INSERT INTO `dv_log` VALUES ('19', '1', 's00423985', '1', '2017-08-20 17:00', '2017-08-21 17:05', '2017-08-20 17:01', '1');
INSERT INTO `dv_log` VALUES ('20', '1', 's00423985', '0', '', '', '2017-08-20 17:01', '1');
INSERT INTO `dv_log` VALUES ('21', '6', 's00423985', '1', '2017-08-20 17:05', '2017-08-22 17:05', '2017-08-20 17:02', '1');
INSERT INTO `dv_log` VALUES ('22', '6', 's00423985', '0', '', '', '2017-08-20 17:23', '1');
INSERT INTO `dv_log` VALUES ('23', '14', 's00423985', '1', '2017-08-20 17:20', '2017-08-20 17:30', '2017-08-20 17:23', '1');
INSERT INTO `dv_log` VALUES ('24', '6', 's1245651', '1', '2017-08-20 17:20', '2017-08-21 17:20', '2017-08-20 17:24', '1');
INSERT INTO `dv_log` VALUES ('25', '6', 's1245651', '0', '', '', '2017-08-20 17:26', '1');

-- ----------------------------
-- Table structure for `dv_user`
-- ----------------------------
DROP TABLE IF EXISTS `dv_user`;
CREATE TABLE `dv_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT '',
  `Username` varchar(25) NOT NULL DEFAULT '',
  `Password` varchar(50) NOT NULL DEFAULT '',
  `Salt` varchar(32) NOT NULL DEFAULT '',
  `Status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dv_user
-- ----------------------------
INSERT INTO `dv_user` VALUES ('9', 'Yadi Sun', 's00423985', 'dNOcKyFUT5AAP6BUFbMcjf23eUQ=', 'Z+NdI4M/Q9x7zE56zWhbFA==', '1');
INSERT INTO `dv_user` VALUES ('10', 'zhangsan', 's1245651', 'sqGk5JZLMdF76BAlwkjH7zDaVCM=', 'yvDy/qBDBT606RLxXDFl/w==', '1');
INSERT INTO `dv_user` VALUES ('11', 'yadi.sun', '123456', 'lxNNDA5vahciWyIttcAzFcG3bA4=', '3hLQH+ymdRXqC2Wy6y2Rsg==', '1');
