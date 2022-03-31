/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : miyoustore

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 03/01/2022 19:45:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `aid` int NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `uid` int NOT NULL COMMENT '关联用户表id',
  `addres` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '地址',
  `shoujianName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收件人',
  `sjrPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收件人电话',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (3, 2, '广东省', '李四', '18111111111');
INSERT INTO `address` VALUES (4, 1, '广东省广州市黄浦区光宝园区', '李四', '17777777777');
INSERT INTO `address` VALUES (12, 2, '广东省', 'aa', '12222222222');
INSERT INTO `address` VALUES (16, 1, '广东省', 'afadf', '18888888888');
INSERT INTO `address` VALUES (22, 1, '广东省广州市黄浦区光宝园区', '王五', '17777777777');
INSERT INTO `address` VALUES (23, 3, '广东省罗定市', '张三', '13333333333');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `bid` int NOT NULL AUTO_INCREMENT COMMENT 'bannerID',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类型',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'bannerimg路径',
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, '蜜柚', '/static/images/banner/banner01.png');
INSERT INTO `banner` VALUES (2, '蜜柚', '/static/images/banner/banner02.png');
INSERT INTO `banner` VALUES (3, '蜜柚', '/static/images/banner/banner03.png');

-- ----------------------------
-- Table structure for cars
-- ----------------------------
DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars`  (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `gid` int NOT NULL COMMENT '商品id',
  `sjid` int NOT NULL COMMENT '商家id',
  `price` float(10, 2) NOT NULL COMMENT '商品价格',
  `num` int NOT NULL COMMENT '商品数量',
  `dpname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '店铺名称',
  `goods_ms` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品描述',
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品图片',
  `uid` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cars
-- ----------------------------
INSERT INTO `cars` VALUES (4, 1, 1, 19.99, 3, '蜜柚专卖店1', '超甜红肉蜜柚1', '/static/images/shops/shopItem.png', 1);
INSERT INTO `cars` VALUES (9, 9, 3, 29.99, 1, '蜜柚直售店', '超甜蜜柚33', '/static/images/shops/shopsItem03.png', 1);

-- ----------------------------
-- Table structure for classify
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify`  (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类路由',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classify
-- ----------------------------
INSERT INTO `classify` VALUES (1, '蜜柚', '/miyou');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `sid` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `sjid` int NOT NULL COMMENT '商家id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品描述',
  `price` float NOT NULL COMMENT '商品单价',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片地址',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品分类',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 1, '超甜红肉蜜柚1', 19.99, '/static/images/shops/shopItem.png', '蜜柚');
INSERT INTO `goods` VALUES (2, 1, '超甜蜜柚2', 29.98, '/static/images/shops/shopItem01.png', '蜜柚');
INSERT INTO `goods` VALUES (3, 1, '超甜蜜柚3', 9.98, '/static/images/shops/shopItem02.png', '蜜柚');
INSERT INTO `goods` VALUES (4, 2, '超甜蜜柚21', 19.98, '/static/images/shops/shopItem04.png', '蜜柚');
INSERT INTO `goods` VALUES (5, 2, '超甜蜜柚22', 19.99, '/static/images/shops/shopItem05.png', '蜜柚');
INSERT INTO `goods` VALUES (6, 2, '超甜蜜柚23', 9.98, '/static/images/shops/shopItem06.png', '蜜柚');
INSERT INTO `goods` VALUES (7, 3, '超甜蜜柚31', 19.9, '/static/images/shops/shopItem07.png', '蜜柚');
INSERT INTO `goods` VALUES (8, 3, '超甜蜜柚32', 29.99, '/static/images/shops/shopsItem.png', '蜜柚');
INSERT INTO `goods` VALUES (9, 3, '超甜蜜柚33', 29.99, '/static/images/shops/shopsItem03.png', '蜜柚');
INSERT INTO `goods` VALUES (12, 1, 'sj1蜜柚22', 29.99, 'MiYouStore_data\\shoplist\\c4aca20e-6abb-4583-b13e-b40e3a2dcf53.png', NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int NOT NULL COMMENT '用户id',
  `sjid` int NOT NULL COMMENT '商家id',
  `dpname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '店铺名称',
  `gid` int NOT NULL COMMENT '商品id',
  `goods_ms` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品描述',
  `price` float(10, 2) NOT NULL COMMENT '商品单价',
  `num` int NOT NULL COMMENT '商品数量',
  `aid` int NOT NULL COMMENT '地址id',
  `sjr_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收件人',
  `sjr_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收件人电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收件人地址',
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品图片地址',
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (10, 1, 1, '蜜柚专卖店1', 1, '超甜红肉蜜柚1', 19.99, 3, 22, '王五', '17777777777', '广东省广州市黄浦区光宝园区', '/static/images/shops/shopItem.png', '2021年10月23日 22:26:39');
INSERT INTO `orders` VALUES (11, 1, 3, '蜜柚直售店', 9, '超甜蜜柚33', 29.99, 1, 22, '王五', '17777777777', '广东省广州市黄浦区光宝园区', '/static/images/shops/shopsItem03.png', '2021年10月23日 22:26:39');

-- ----------------------------
-- Table structure for shangjia_user
-- ----------------------------
DROP TABLE IF EXISTS `shangjia_user`;
CREATE TABLE `shangjia_user`  (
  `sjid` int NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `sj_user` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商家账号',
  `sj_password` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `sj_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '店铺名称',
  PRIMARY KEY (`sjid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangjia_user
-- ----------------------------
INSERT INTO `shangjia_user` VALUES (1, 'sj1111', '123456', '蜜柚专卖店1');
INSERT INTO `shangjia_user` VALUES (2, 'sj2222', '123456', '蜜柚旗舰店2');
INSERT INTO `shangjia_user` VALUES (3, 'sj3333', '123456', '蜜柚直售店');
INSERT INTO `shangjia_user` VALUES (4, 'sj4444', '123456', '蜜柚44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `name` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户昵称',
  `sex` int NULL DEFAULT NULL COMMENT '性别（1表示男，0表示女）',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户电话',
  `date` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`uid`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '111111', '123456', '张四', 0, 100, '18316308305', '2021年10月08日 21:58:48');
INSERT INTO `user` VALUES (2, 'zhangsan', '123', NULL, NULL, NULL, '13633338889', '2021年10月08日 21:58:48');
INSERT INTO `user` VALUES (3, '222222', '123456', '张三', 1, 19, '12311111111', '2021年10月08日 21:58:48');
INSERT INTO `user` VALUES (4, '333333', '123456', '李四', 0, 18, '18888888888', '2021年10月08日 22:07:21');
INSERT INTO `user` VALUES (5, '444444', '123456', '44', 1, 18, '12345678911', '2021年10月10日 17:44:16');
INSERT INTO `user` VALUES (6, '555555', '123456', '666', 1, 18, '66666666666', '2021年10月10日 18:17:48');
INSERT INTO `user` VALUES (7, '777777', '123456', '7', 1, 18, '77777777777', '2021年10月10日 18:36:48');

-- ----------------------------
-- Table structure for userimg
-- ----------------------------
DROP TABLE IF EXISTS `userimg`;
CREATE TABLE `userimg`  (
  `u_img_id` int NOT NULL AUTO_INCREMENT COMMENT 'banner图片ID',
  `uid` int NOT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片名',
  `bid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`u_img_id`, `uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userimg
-- ----------------------------
INSERT INTO `userimg` VALUES (8, 1, '微信图片_20210529211227.png', '/MiYouStore_data/userImg/023795eb-cf9f-48ef-b687-7da455471b7a.png');
INSERT INTO `userimg` VALUES (9, 3, '微信图片_20210529211227.png', '/MiYouStore_data/userImg/edb7a9b2-d550-427d-a01f-e5ee1e2b76d7.png');

SET FOREIGN_KEY_CHECKS = 1;
