/*
 Navicat MySQL Data Transfer

 Source Server         : MySQLConnection
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : warehousedatabase

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 22/04/2021 20:16:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `Client_ID_uindex`(`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ordertable
-- ----------------------------
DROP TABLE IF EXISTS `ordertable`;
CREATE TABLE `ordertable`  (
  `ID` int NOT NULL AUTO_INCREMENT,
  `clientID` int NOT NULL,
  `product` int NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `order_ID_uindex`(`ID`) USING BTREE,
  INDEX `order_client_ID_fk`(`clientID`) USING BTREE,
  INDEX `order_product_ID_fk`(`product`) USING BTREE,
  CONSTRAINT `order_client_ID_fk` FOREIGN KEY (`clientID`) REFERENCES `client` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_product_ID_fk` FOREIGN KEY (`product`) REFERENCES `product` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `product_ID_uindex`(`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
