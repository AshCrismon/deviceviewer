/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : deviceviewer

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-08-06 17:41:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dv_device`
-- ----------------------------
DROP TABLE IF EXISTS `dv_device`;
CREATE TABLE `dv_device` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceName` varchar(50) NOT NULL DEFAULT '',
  `DeviceIPs` varchar(35) NOT NULL DEFAULT '',
  `DeviceType` varchar(30) NOT NULL DEFAULT '',
  `Occupier` varchar(25) NOT NULL DEFAULT '',
  `BeginTime` varchar(32) DEFAULT '',
  `EndTime` varchar(32) DEFAULT '',
  `IsOccupied` tinyint(1) NOT NULL DEFAULT '0',
  `Status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dv_device
-- ----------------------------
INSERT INTO `dv_device` VALUES ('1', 'anonymous', '8.46.47.101/102', '18000V1R1', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('2', 'anonymous', '8.46.47.103/104', '18000V1R1', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('3', 'anonymous', '8.46.47.194/195', '18000V1R1', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('4', 'anonymous', '8.46.47.132/133', '18000V1R1', 's00423985', '2017-08-06 13:45', '2017-08-06 21:45', '1', '1');
INSERT INTO `dv_device` VALUES ('5', 'anonymous', '8.46.47.145/146', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('6', 'anonymous', '8.46.47.157/158', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('7', 'anonymous', '8.46.47.182/183', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('8', 'anonymous', '8.46.47.168/169', '18000V2R2', 's1245651', '2017-08-10 09:30', '2017-08-13 05:35', '1', '1');
INSERT INTO `dv_device` VALUES ('9', 'anonymous', '8.46.47.171/172', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('10', 'anonymous', '8.46.47.182/183', '18000V2R2', 's1245651', '2017-08-18 15:30', '2017-08-19 10:50', '1', '1');
INSERT INTO `dv_device` VALUES ('11', 'anonymous', '8.46.47.191/192', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('12', 'anonymous', '8.46.47.221/222', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('13', 'anonymous', '8.46.47.242/243', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('14', 'anonymous', '8.46.47.56/57', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('15', 'anonymous', '8.46.47.145/146', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('16', 'anonymous', '8.46.47.157/158', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('17', 'anonymous', '8.46.47.182/183', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('18', 'anonymous', '8.46.47.168/169', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('19', 'anonymous', '8.46.47.171/172', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('20', 'anonymous', '8.46.47.182/183', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('21', 'anonymous', '8.46.47.191/192', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('22', 'anonymous', '8.46.47.221/222', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('23', 'anonymous', '8.46.47.242/243', '18000V2R2', '', '', '', '0', '1');
INSERT INTO `dv_device` VALUES ('24', 'anonymous', '8.46.47.56/57', '18000V2R2', '', '', '', '0', '1');

-- ----------------------------
-- Table structure for `dv_user`
-- ----------------------------
DROP TABLE IF EXISTS `dv_user`;
CREATE TABLE `dv_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT NULL,
  `Username` varchar(25) NOT NULL DEFAULT '',
  `Password` varchar(50) NOT NULL DEFAULT '',
  `Salt` varchar(32) NOT NULL DEFAULT '',
  `Status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dv_user
-- ----------------------------
INSERT INTO `dv_user` VALUES ('9', 'Yadi Sun', 's00423985', 'dNOcKyFUT5AAP6BUFbMcjf23eUQ=', 'Z+NdI4M/Q9x7zE56zWhbFA==', '1');
INSERT INTO `dv_user` VALUES ('10', 'zhangsan', 's1245651', 'sqGk5JZLMdF76BAlwkjH7zDaVCM=', 'yvDy/qBDBT606RLxXDFl/w==', '1');
