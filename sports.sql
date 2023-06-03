/*
 Navicat Premium Data Transfer

 Source Server         : SoftwareII
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : sports

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 03/06/2023 23:40:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_Id` int NOT NULL AUTO_INCREMENT,
  `AddressName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `money` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`address_Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (2, '篮球场', '0', '20');
INSERT INTO `address` VALUES (4, '乒乓球馆', '1', '50');
INSERT INTO `address` VALUES (5, '网球场', '1', '30');
INSERT INTO `address` VALUES (6, '1111', '0', '10');

-- ----------------------------
-- Table structure for borrowadd
-- ----------------------------
DROP TABLE IF EXISTS `borrowadd`;
CREATE TABLE `borrowadd`  (
  `borrow_add_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `AddressName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `borrowBegin` datetime NULL DEFAULT NULL,
  `borrowEnd` datetime NULL DEFAULT NULL,
  `money` decimal(10, 2) NULL DEFAULT NULL,
  `agree` int NULL DEFAULT NULL,
  PRIMARY KEY (`borrow_add_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowadd
-- ----------------------------
INSERT INTO `borrowadd` VALUES (2, 'admin', '乒乓球馆', '2023-06-13 01:02:02', '2023-06-14 01:02:02', 50.00, 0);

-- ----------------------------
-- Table structure for borrowequip
-- ----------------------------
DROP TABLE IF EXISTS `borrowequip`;
CREATE TABLE `borrowequip`  (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `eName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gameName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `eNum` int NULL DEFAULT NULL,
  `borrowBegin` datetime NULL DEFAULT NULL,
  `borrowEnd` datetime NULL DEFAULT NULL,
  `money` decimal(10, 2) NULL DEFAULT NULL,
  `agree` int NULL DEFAULT NULL,
  PRIMARY KEY (`borrow_id`) USING BTREE,
  INDEX `userId`(`username`) USING BTREE,
  INDEX `eId`(`eName`) USING BTREE,
  INDEX `gameId`(`gameName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowequip
-- ----------------------------
INSERT INTO `borrowequip` VALUES (9, 'admin', '篮球', NULL, 5, '2023-06-13 03:03:03', '2023-06-12 02:00:04', 30.00, 0);
INSERT INTO `borrowequip` VALUES (10, 'admin', '足球', NULL, 3, '2023-06-20 00:00:00', '2023-06-21 00:00:00', 50.00, 0);

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `EId` int NOT NULL AUTO_INCREMENT,
  `EName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Buyday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `count` int NULL DEFAULT NULL,
  `money` decimal(10, 2) NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  PRIMARY KEY (`EId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES (5, '篮球', '2023-06-21', 10, 10.00, 0);
INSERT INTO `equipment` VALUES (6, '只因', '2023-06-23', 30, 12.50, 0);
INSERT INTO `equipment` VALUES (7, '羽毛球拍', '2023-06-23', 0, 10.00, 1);

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game`  (
  `game_id` int NOT NULL AUTO_INCREMENT,
  `gameName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gameBeginTime` datetime NULL DEFAULT NULL,
  `gameEndTime` datetime NULL DEFAULT NULL,
  `gameAdd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gameEvent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gameEquip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `agree` int NULL DEFAULT NULL,
  PRIMARY KEY (`game_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES (3, '篮球赛', '2023-06-13 00:00:00', '2023-06-07 00:00:00', '篮球', '篮球111', '篮球', 0);
INSERT INTO `game` VALUES (8, '只因杯', '2023-06-11 01:01:02', '2023-06-04 02:01:02', '网球场', '只因', '只因球', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` int NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cardNum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'admin', '1234', '2023-05-27', NULL, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES (3, 'admin12330', '123456789', NULL, NULL, NULL, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
