/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : mishop

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2020-10-06 20:19:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `image_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `image_url` varchar(50) DEFAULT NULL,
  `image_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FK_have` (`product_id`),
  CONSTRAINT `FK_have` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_image
-- ----------------------------
INSERT INTO `t_image` VALUES ('1', '1', '/images/phone.jpg', '0');
INSERT INTO `t_image` VALUES ('2', '2', '/images/television.jpg', '0');
INSERT INTO `t_image` VALUES ('3', '3', '/images/computer.jpg', '0');
INSERT INTO `t_image` VALUES ('4', '4', '/images/aimusicbox.jpg', '0');
INSERT INTO `t_image` VALUES ('5', '5', '/images/blancecar.jpg', '0');
INSERT INTO `t_image` VALUES ('6', '6', '/images/router.jpg', '0');

-- ----------------------------
-- Table structure for t_model_data
-- ----------------------------
DROP TABLE IF EXISTS `t_model_data`;
CREATE TABLE `t_model_data` (
  `data_id` int(11) NOT NULL,
  `model_id` int(11) DEFAULT NULL,
  `data_value` varchar(10) NOT NULL,
  `selected` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`data_id`),
  KEY `FK_has` (`model_id`),
  CONSTRAINT `FK_has` FOREIGN KEY (`model_id`) REFERENCES `t_product_model` (`model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_model_data
-- ----------------------------
INSERT INTO `t_model_data` VALUES ('1', '1', '大', '0');
INSERT INTO `t_model_data` VALUES ('2', '1', '中', '0');
INSERT INTO `t_model_data` VALUES ('3', '1', '小', '0');
INSERT INTO `t_model_data` VALUES ('4', '2', '红色', '0');
INSERT INTO `t_model_data` VALUES ('5', '2', '黑色', '0');
INSERT INTO `t_model_data` VALUES ('6', '2', '黄色', '0');
INSERT INTO `t_model_data` VALUES ('7', '3', '低配', '0');
INSERT INTO `t_model_data` VALUES ('8', '3', '顶配', '0');
INSERT INTO `t_model_data` VALUES ('9', '4', '大', '0');
INSERT INTO `t_model_data` VALUES ('10', '4', '中', '0');
INSERT INTO `t_model_data` VALUES ('11', '4', '小', '0');
INSERT INTO `t_model_data` VALUES ('12', '5', '红色', '0');
INSERT INTO `t_model_data` VALUES ('13', '5', '绿色', '0');
INSERT INTO `t_model_data` VALUES ('14', '6', '低配', '0');
INSERT INTO `t_model_data` VALUES ('15', '6', '高配', '0');
INSERT INTO `t_model_data` VALUES ('16', '7', '黑色', '0');
INSERT INTO `t_model_data` VALUES ('17', '8', '低配', '0');
INSERT INTO `t_model_data` VALUES ('18', '8', '高配', '0');
INSERT INTO `t_model_data` VALUES ('19', '9', '大', '0');
INSERT INTO `t_model_data` VALUES ('20', '9', '小', '0');
INSERT INTO `t_model_data` VALUES ('21', '10', '高配', '0');
INSERT INTO `t_model_data` VALUES ('22', '11', '白色', '0');
INSERT INTO `t_model_data` VALUES ('23', '11', '黄色', '0');
INSERT INTO `t_model_data` VALUES ('24', '12', '低配', '0');
INSERT INTO `t_model_data` VALUES ('25', '12', '高配', '0');

-- ----------------------------
-- Table structure for t_nav
-- ----------------------------
DROP TABLE IF EXISTS `t_nav`;
CREATE TABLE `t_nav` (
  `nav_id` int(11) NOT NULL AUTO_INCREMENT,
  `nav_name` char(5) DEFAULT NULL,
  `nav_path` varchar(50) NOT NULL,
  `nav_noselected_img` varchar(50) NOT NULL,
  `nav_selected_img` varchar(50) NOT NULL,
  `selected` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`nav_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_nav
-- ----------------------------
INSERT INTO `t_nav` VALUES ('1', '首页', '/', '../images/bar/home-noselected.png', '../images/bar/home-selected.png', '1');
INSERT INTO `t_nav` VALUES ('2', '分类', '/fenlei', '../images/bar/list-noselected.png', '../images/bar/list-selected.png', '0');
INSERT INTO `t_nav` VALUES ('3', '星球', '/earth', '../images/bar/earth-noselected.png', '../images/bar/earth-selected.png', '0');
INSERT INTO `t_nav` VALUES ('4', '购物车', '/shoppingcart', '../images/bar/shoppingcart-noselected.png', '../images/bar/shoppingcart-selected.png', '0');
INSERT INTO `t_nav` VALUES ('5', '我的', '/my', '../images/bar/my-noselected.png', '../images/bar/my-selected.png', '0');

-- ----------------------------
-- Table structure for t_pm_relationship
-- ----------------------------
DROP TABLE IF EXISTS `t_pm_relationship`;
CREATE TABLE `t_pm_relationship` (
  `pm_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `model_id` int(11) NOT NULL,
  PRIMARY KEY (`pm_id`),
  KEY `FK_hava` (`product_id`),
  KEY `FK_hava2` (`model_id`),
  CONSTRAINT `FK_hava` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`product_id`),
  CONSTRAINT `FK_hava2` FOREIGN KEY (`model_id`) REFERENCES `t_product_model` (`model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pm_relationship
-- ----------------------------
INSERT INTO `t_pm_relationship` VALUES ('1', '1', '1');
INSERT INTO `t_pm_relationship` VALUES ('2', '1', '2');
INSERT INTO `t_pm_relationship` VALUES ('3', '1', '3');
INSERT INTO `t_pm_relationship` VALUES ('4', '2', '1');
INSERT INTO `t_pm_relationship` VALUES ('5', '2', '2');
INSERT INTO `t_pm_relationship` VALUES ('6', '2', '3');
INSERT INTO `t_pm_relationship` VALUES ('7', '3', '1');
INSERT INTO `t_pm_relationship` VALUES ('8', '3', '2');
INSERT INTO `t_pm_relationship` VALUES ('9', '3', '3');
INSERT INTO `t_pm_relationship` VALUES ('10', '4', '2');
INSERT INTO `t_pm_relationship` VALUES ('11', '5', '2');
INSERT INTO `t_pm_relationship` VALUES ('12', '5', '3');
INSERT INTO `t_pm_relationship` VALUES ('13', '6', '2');

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `number` int(11) DEFAULT '1',
  `description` varchar(200) DEFAULT NULL,
  `selected` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '小米手机', '1500', '10', '这是一台手机', '0');
INSERT INTO `t_product` VALUES ('2', '小米电视', '4000', '1', '这是一台电视', '0');
INSERT INTO `t_product` VALUES ('3', '小米笔记本', '200', '1', '这是小米手表', '0');
INSERT INTO `t_product` VALUES ('4', '小米智能音箱', '1600', '1', '这是小米平衡车', '0');
INSERT INTO `t_product` VALUES ('5', '小米平衡车', '800', '1', '这是小米只能音箱', '0');
INSERT INTO `t_product` VALUES ('6', '小米路由器', '300', '1', '这是小米路由器', '0');

-- ----------------------------
-- Table structure for t_product_model
-- ----------------------------
DROP TABLE IF EXISTS `t_product_model`;
CREATE TABLE `t_product_model` (
  `model_id` int(11) NOT NULL,
  `model_name` varchar(20) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`model_id`),
  KEY `fk_product_prmodle` (`product_id`),
  CONSTRAINT `fk_product_prmodle` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_model
-- ----------------------------
INSERT INTO `t_product_model` VALUES ('1', '尺寸', '1');
INSERT INTO `t_product_model` VALUES ('2', '颜色', '1');
INSERT INTO `t_product_model` VALUES ('3', '配置', '1');
INSERT INTO `t_product_model` VALUES ('4', '尺寸', '2');
INSERT INTO `t_product_model` VALUES ('5', '颜色', '2');
INSERT INTO `t_product_model` VALUES ('6', '配置', '2');
INSERT INTO `t_product_model` VALUES ('7', '颜色', '3');
INSERT INTO `t_product_model` VALUES ('8', '配置', '3');
INSERT INTO `t_product_model` VALUES ('9', '尺寸', '4');
INSERT INTO `t_product_model` VALUES ('10', '配置', '4');
INSERT INTO `t_product_model` VALUES ('11', '颜色', '5');
INSERT INTO `t_product_model` VALUES ('12', '配置', '6');

-- ----------------------------
-- Table structure for t_shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `t_shoppingcart`;
CREATE TABLE `t_shoppingcart` (
  `shoppingcart_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) NOT NULL,
  `product_id` int(11) NOT NULL,
  `totalnumber` int(11) DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  `gift_id` int(11) DEFAULT '0',
  PRIMARY KEY (`shoppingcart_id`),
  KEY `FK_t_shoppingCart2` (`product_id`),
  KEY `FK_t_user` (`userid`),
  CONSTRAINT `FK_t_shoppingCart2` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`product_id`),
  CONSTRAINT `FK_t_user` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shoppingcart
-- ----------------------------
INSERT INTO `t_shoppingcart` VALUES ('5', '1', '1', '1', '1500', '0');
INSERT INTO `t_shoppingcart` VALUES ('6', '1', '5', '2', '1600', '0');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` varchar(50) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `mobile_phone` char(11) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `mobile_phone` (`mobile_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '张三', '123666', '202cb962ac59075b964b07152d234b70');

-- ----------------------------
-- Table structure for t_user_product_model_data
-- ----------------------------
DROP TABLE IF EXISTS `t_user_product_model_data`;
CREATE TABLE `t_user_product_model_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  `modelid` int(11) DEFAULT NULL,
  `modeldataid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_my_product` (`productid`),
  KEY `FK_my_model` (`modelid`),
  KEY `FK_my_modeldata` (`modeldataid`),
  KEY `FK_my_user` (`userid`),
  CONSTRAINT `FK_my_model` FOREIGN KEY (`modelid`) REFERENCES `t_product_model` (`model_id`),
  CONSTRAINT `FK_my_modeldata` FOREIGN KEY (`modeldataid`) REFERENCES `t_model_data` (`data_id`),
  CONSTRAINT `FK_my_product` FOREIGN KEY (`productid`) REFERENCES `t_product` (`product_id`),
  CONSTRAINT `FK_my_user` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_product_model_data
-- ----------------------------
INSERT INTO `t_user_product_model_data` VALUES ('6', '1', '1', '1', '1');
INSERT INTO `t_user_product_model_data` VALUES ('7', '1', '1', '2', '4');
INSERT INTO `t_user_product_model_data` VALUES ('8', '1', '1', '3', '7');
INSERT INTO `t_user_product_model_data` VALUES ('9', '1', '5', '11', '22');
