/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 20/02/2023 19:22:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `link_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, 'xx超市', '13898982363', '2023-02-20 09:10:26', '2023-02-20 09:10:29', '测试', 1);
INSERT INTO `customers` VALUES (2, 'xx商城', '15623465123', '2023-02-20 09:10:51', '2023-02-20 09:10:55', '测试', 1);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_type_id` int(11) NULL DEFAULT NULL,
  `goods_cost_price` double NULL DEFAULT NULL,
  `goods_sale_price` double NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '可口可乐', 1, 1.5, 3.5, '2023-02-20 09:12:12', '2023-02-20 09:12:18', 1);
INSERT INTO `goods` VALUES (2, '百事可乐', 1, 1.5, 3.5, '2023-02-20 14:59:47', '2023-02-20 14:59:50', 1);

-- ----------------------------
-- Table structure for goods_supplier
-- ----------------------------
DROP TABLE IF EXISTS `goods_supplier`;
CREATE TABLE `goods_supplier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `supplier_id` int(11) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_supplier
-- ----------------------------
INSERT INTO `goods_supplier` VALUES (1, 2, 1, '2023-02-20 15:57:42', '2023-02-20 15:57:45', 1);

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`  (
  ` id` int(11) NOT NULL,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (` id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES (1, '饮品');

-- ----------------------------
-- Table structure for into_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `into_warehouse`;
CREATE TABLE `into_warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `supplier_id` int(11) NULL DEFAULT NULL,
  `goods_number` int(11) NULL DEFAULT NULL,
  `total_price` double NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of into_warehouse
-- ----------------------------
INSERT INTO `into_warehouse` VALUES (1, 1, 1, 100, 150, '2023-02-20 11:01:06', '2023-02-20 11:01:09', '测试数据', 0, 1);

-- ----------------------------
-- Table structure for into_warehouse_supplier
-- ----------------------------
DROP TABLE IF EXISTS `into_warehouse_supplier`;
CREATE TABLE `into_warehouse_supplier`  (
  `id` int(11) NOT NULL,
  `into_warehouse_id` int(11) NULL DEFAULT NULL,
  `supplier_id` int(11) NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of into_warehouse_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for out_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `out_warehouse`;
CREATE TABLE `out_warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `customers_id` int(11) NULL DEFAULT NULL,
  `goods_number` int(11) NULL DEFAULT NULL,
  `sale_total_price` double NULL DEFAULT NULL,
  `out_warehouse_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of out_warehouse
-- ----------------------------

-- ----------------------------
-- Table structure for out_warehouse_customers
-- ----------------------------
DROP TABLE IF EXISTS `out_warehouse_customers`;
CREATE TABLE `out_warehouse_customers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `out_warehouse_id` int(11) NULL DEFAULT NULL,
  `customers_id` int(11) NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of out_warehouse_customers
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `link_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '百事可乐供应商', '13786541256', 1);
INSERT INTO `supplier` VALUES (2, 'xx工厂', '18965411256', 1);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', '系统管理员', '2016-12-01 00:00:00', '2020-02-24 15:53:12', 1);
INSERT INTO `t_role` VALUES (2, '销售', '销售', '2016-12-01 00:00:00', '2020-02-24 15:53:18', 1);
INSERT INTO `t_role` VALUES (3, '客户经理', '客户经理', '2016-12-01 00:00:00', '2020-02-24 15:53:22', 1);
INSERT INTO `t_role` VALUES (14, '技术经理', '研发', '2017-06-30 14:50:24', '2020-02-24 15:53:25', 1);
INSERT INTO `t_role` VALUES (17, '人事', '人事', '2017-10-23 09:15:10', '2020-02-24 15:53:29', 1);
INSERT INTO `t_role` VALUES (18, '测试人员', NULL, '2020-11-10 14:34:00', '2020-11-10 14:34:00', 1);
INSERT INTO `t_role` VALUES (19, '测试经理', '测试经理', '2020-11-10 14:34:42', '2020-11-10 15:53:03', 0);
INSERT INTO `t_role` VALUES (20, '经理2号', '测试2', '2020-11-10 14:51:04', '2020-11-10 15:50:13', 0);
INSERT INTO `t_role` VALUES (21, 'xy', NULL, '2023-02-13 10:59:35', '2023-02-13 10:59:35', 1);
INSERT INTO `t_role` VALUES (22, '测试经理', NULL, '2023-02-13 11:01:48', '2023-02-13 11:01:48', 1);
INSERT INTO `t_role` VALUES (23, '大boss的', '阿萨德的', '2023-02-13 11:48:57', '2023-02-13 14:21:47', 0);
INSERT INTO `t_role` VALUES (24, '新一代', 'ad', '2023-02-13 12:40:04', '2023-02-13 12:40:04', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT 1,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (10, 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', '126@126.com', '13327792157', 1, '2016-12-01 12:05:49', '2023-02-13 21:32:45');
INSERT INTO `t_user` VALUES (42, 'scott', '4QrcOUm6Wau+VuBX8g+IPg==', 'scott', '234@126.com', '13327792157', 1, '2017-09-09 00:14:53', '2023-02-14 09:40:47');
INSERT INTO `t_user` VALUES (79, 'zhangsan', '4QrcOUm6Wau+VuBX8g+IPg==', NULL, 'zhangsan@163.com', '18876545687', 1, '2020-11-05 17:15:50', '2020-11-05 17:15:50');
INSERT INTO `t_user` VALUES (80, 'lisi', '4QrcOUm6Wau+VuBX8g+IPg==', 'lisi', 'lisi@163.com', '18876767890', 1, '2020-11-05 17:16:35', '2020-11-05 17:16:35');
INSERT INTO `t_user` VALUES (81, 'test', '4QrcOUm6Wau+VuBX8g+IPg==', '王五', 'testa@163.com', '15856787654', 1, '2020-11-05 17:33:21', '2020-11-05 19:43:41');
INSERT INTO `t_user` VALUES (82, 'aabb', '4QrcOUm6Wau+VuBX8g+IPg==', 'ab', 'ab@163.com', '13876545678', 1, '2020-11-05 19:44:30', '2020-11-05 20:12:52');
INSERT INTO `t_user` VALUES (85, 'test001', '4QrcOUm6Wau+VuBX8g+IPg==', '测试一号', 'test001@163.com', '13787654345', 0, '2020-11-10 03:35:05', '2020-11-10 03:38:25');
INSERT INTO `t_user` VALUES (86, 'test002', '4QrcOUm6Wau+VuBX8g+IPg==', '测试二号', 'test002@163.com', '13876545678', 0, '2020-11-10 03:36:03', '2020-11-10 03:36:03');
INSERT INTO `t_user` VALUES (87, 'zhangsan', '4QrcOUm6Wau+VuBX8g+IPg==', NULL, '123456@qq.com', '13797980203', 0, '2023-02-10 20:20:47', '2023-02-10 20:20:47');
INSERT INTO `t_user` VALUES (88, 'zha', '4QrcOUm6Wau+VuBX8g+IPg==', '123', '126@1267.com', '13797980365', 0, '2023-02-10 21:50:29', '2023-02-11 09:22:25');
INSERT INTO `t_user` VALUES (90, '薛洋', '4QrcOUm6Wau+VuBX8g+IPg==', '薛洋', '123@qq.com', '13797970383', 0, '2023-02-13 09:35:52', '2023-02-13 09:37:43');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 206 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (198, 80, 2, NULL, NULL);
INSERT INTO `t_user_role` VALUES (204, 10, 1, '2023-02-13 21:32:45', '2023-02-13 21:32:45');
INSERT INTO `t_user_role` VALUES (205, 42, 2, '2023-02-14 09:40:46', '2023-02-14 09:40:46');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `goods_number` int(11) NOT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, 1, 100, '2023-02-20 09:13:42', '2023-02-20 09:13:45', 1);

SET FOREIGN_KEY_CHECKS = 1;
