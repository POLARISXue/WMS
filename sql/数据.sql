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

 Date: 24/02/2023 18:53:44
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
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `link_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, '联盛超市', '张三风', '13898982363', '北京', '测试', '2023-02-20 09:10:26', '2023-02-20 09:10:29', 1);
INSERT INTO `customers` VALUES (2, '万达广场', '李四季', '15623465123', '上海', '测试', '2023-02-20 09:10:51', '2023-02-20 09:10:55', 1);
INSERT INTO `customers` VALUES (3, '好又多超市', '王五福', '13923456723', '杭州', '测试', '2023-02-22 15:52:23', '2023-02-22 15:52:26', 1);
INSERT INTO `customers` VALUES (4, '波司登旗舰店', '赵六顺', '18345239812', '九江', '测试', '2023-02-22 15:58:36', '2023-02-22 15:58:40', 1);
INSERT INTO `customers` VALUES (5, 'SKII旗舰店', '陈七七', '18345672389', '广州', '测试', '2023-02-22 16:00:27', '2023-02-22 16:00:29', 1);
INSERT INTO `customers` VALUES (6, '联想旗舰店', '卢八仙', '13567459812', '上海', '测试', '2023-02-22 16:01:44', '2023-02-22 16:01:49', 1);
INSERT INTO `customers` VALUES (7, '琳琅百货', '郑九牛', '17398345623', '武汉', '测试', '2023-02-22 16:05:49', '2023-02-22 16:05:53', 1);
INSERT INTO `customers` VALUES (8, '万象城', '薛十全', '18354763345', '荆门', '测试', '2023-02-22 16:09:45', '2023-02-22 16:09:47', 1);
INSERT INTO `customers` VALUES (9, '京东', '京小东', '17354830901', '北京', '测试', '2023-02-22 16:11:04', '2023-02-22 16:11:08', 1);
INSERT INTO `customers` VALUES (10, '淘宝', '淘小宝', '18654672289', '上海', '测试', '2023-02-22 16:13:18', '2023-02-22 16:13:21', 1);
INSERT INTO `customers` VALUES (11, '拼多多', '拼小多', '13567234512', '贵州', '测试', '2023-02-22 16:14:15', '2023-02-22 16:14:18', 1);
INSERT INTO `customers` VALUES (12, '唯品会', '唯一品', '13654237878', '兰州', '测试', '2023-02-22 16:16:00', '2023-02-22 16:16:03', 1);
INSERT INTO `customers` VALUES (13, '得物', '得二物', '18675439898', '上饶', '测试', '2023-02-22 16:17:31', '2023-02-22 16:17:33', 1);
INSERT INTO `customers` VALUES (14, 'iPhone旗舰店', '小苹果', '13987623423', '北京', NULL, '2023-02-22 19:43:46', '2023-02-22 19:43:48', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '可口可乐', 1, 1.5, 3.5, '2023-02-20 09:12:12', '2023-02-20 09:12:18', 1);
INSERT INTO `goods` VALUES (2, '百事可乐', 1, 1.5, 3.5, '2023-02-20 14:59:47', '2023-02-21 15:51:41', 1);
INSERT INTO `goods` VALUES (3, '冰红茶', 1, 1.5, 3, '2023-02-21 15:52:40', '2023-02-21 15:52:40', 1);
INSERT INTO `goods` VALUES (4, '特仑苏', 1, 2, 5, '2023-02-22 16:18:40', '2023-02-22 16:18:44', 1);
INSERT INTO `goods` VALUES (5, '友臣肉松饼', 2, 0.5, 1, '2023-02-22 16:19:36', '2023-02-22 16:19:39', 1);
INSERT INTO `goods` VALUES (6, '乐视薯片', 2, 1, 3.5, '2023-02-22 16:20:51', '2023-02-22 16:20:55', 1);
INSERT INTO `goods` VALUES (7, '统一泡面', 2, 2, 4, '2023-02-22 16:21:33', '2023-02-22 16:21:36', 1);
INSERT INTO `goods` VALUES (8, '麻辣王子', 2, 0.5, 1, '2023-02-22 16:22:03', '2023-02-22 16:22:07', 1);
INSERT INTO `goods` VALUES (9, '杯子', 3, 3.5, 9.9, '2023-02-22 16:22:57', '2023-02-22 16:23:00', 1);
INSERT INTO `goods` VALUES (10, '抽纸', 3, 1, 4, '2023-02-22 16:24:46', '2023-02-22 16:24:49', 1);
INSERT INTO `goods` VALUES (11, '怡宝', 1, 0.5, 2, '2023-02-22 16:25:51', '2023-02-22 16:25:53', 1);
INSERT INTO `goods` VALUES (12, '垃圾袋', 3, 0.3, 1, '2023-02-22 16:26:36', '2023-02-22 16:26:38', 1);
INSERT INTO `goods` VALUES (13, '牙刷', 3, 1, 4, '2023-02-22 16:27:04', '2023-02-22 16:27:06', 1);
INSERT INTO `goods` VALUES (14, '碗', 3, 1, 2, '2023-02-22 16:30:23', '2023-02-22 16:30:25', 1);
INSERT INTO `goods` VALUES (15, '电视机', 4, 2000, 4000, '2023-02-22 16:31:05', '2023-02-22 16:31:07', 1);
INSERT INTO `goods` VALUES (16, '联想电脑', 4, 2500, 4500, '2023-02-22 16:31:39', '2023-02-22 16:31:41', 1);
INSERT INTO `goods` VALUES (17, 'iPhone', 4, 2000, 5999, '2023-02-22 16:32:14', '2023-02-22 16:32:17', 1);
INSERT INTO `goods` VALUES (18, '电饭煲', 4, 200, 800, '2023-02-22 16:33:35', '2023-02-22 16:33:37', 1);
INSERT INTO `goods` VALUES (19, '烧水壶', 4, 10, 30, '2023-02-22 16:34:14', '2023-02-22 16:34:17', 1);
INSERT INTO `goods` VALUES (20, '插座', 4, 9, 29.9, '2023-02-22 16:34:48', '2023-02-22 16:34:51', 1);
INSERT INTO `goods` VALUES (21, '西装', 5, 1000, 2000, '2023-02-22 16:53:38', '2023-02-22 16:54:08', 1);
INSERT INTO `goods` VALUES (22, '鞋子', 5, 200, 500, '2023-02-22 16:53:41', '2023-02-22 16:54:12', 1);
INSERT INTO `goods` VALUES (23, '棉袄', 5, 200, 666, '2023-02-22 16:53:44', '2023-02-22 16:54:16', 1);
INSERT INTO `goods` VALUES (24, 'T恤', 5, 30, 100, '2023-02-22 16:53:47', '2023-02-22 16:54:19', 1);
INSERT INTO `goods` VALUES (25, '袜子', 5, 1, 5, '2023-02-22 16:53:50', '2023-02-22 16:54:21', 1);
INSERT INTO `goods` VALUES (26, '秋衣', 5, 30, 99, '2023-02-22 16:53:54', '2023-02-22 16:54:23', 1);
INSERT INTO `goods` VALUES (27, '口红', 6, 50, 100, '2023-02-22 16:53:56', '2023-02-22 20:59:30', 1);
INSERT INTO `goods` VALUES (28, '粉底液', 6, 80, 160, '2023-02-22 16:53:59', '2023-02-22 16:54:29', 1);
INSERT INTO `goods` VALUES (29, '卸妆水', 6, 30, 66, '2023-02-22 16:54:02', '2023-02-22 16:54:33', 1);
INSERT INTO `goods` VALUES (30, '散粉', 6, 35, 78, '2023-02-22 16:54:05', '2023-02-22 16:54:36', 1);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_supplier
-- ----------------------------
INSERT INTO `goods_supplier` VALUES (2, 1, 2, '2023-02-22 09:20:46', '2023-02-22 09:20:48');
INSERT INTO `goods_supplier` VALUES (3, 8, 3, '2023-02-22 09:21:00', '2023-02-22 09:21:03');
INSERT INTO `goods_supplier` VALUES (4, 3, 5, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (5, 4, 5, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (6, 5, 3, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (7, 6, 10, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (8, 7, 3, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (9, 9, 4, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (10, 10, 8, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (11, 11, 5, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (12, 12, 8, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (13, 13, 8, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (14, 14, 8, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (15, 15, 7, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (16, 16, 9, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (17, 17, 11, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (18, 18, 7, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (19, 19, 7, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (20, 20, 7, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (21, 21, 6, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (22, 22, 6, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (23, 23, 6, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (24, 24, 6, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (25, 25, 6, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (26, 26, 6, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (27, 27, 12, '2023-02-20 15:57:42', '2023-02-20 15:57:42');
INSERT INTO `goods_supplier` VALUES (28, 28, 12, '2023-02-22 18:06:27', '2023-02-22 18:06:35');
INSERT INTO `goods_supplier` VALUES (29, 29, 12, '2023-02-22 18:06:30', '2023-02-22 18:06:37');
INSERT INTO `goods_supplier` VALUES (30, 30, 12, '2023-02-22 18:06:33', '2023-02-22 18:06:39');
INSERT INTO `goods_supplier` VALUES (63, 1, 1, '2023-02-24 16:00:45', '2023-02-24 16:00:45');

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_valid` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES (1, '饮品', 1);
INSERT INTO `goods_type` VALUES (2, '食品', 1);
INSERT INTO `goods_type` VALUES (3, '日用品', 1);
INSERT INTO `goods_type` VALUES (4, '电器', 1);
INSERT INTO `goods_type` VALUES (5, '服饰', 1);
INSERT INTO `goods_type` VALUES (6, '化妆品', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of into_warehouse
-- ----------------------------
INSERT INTO `into_warehouse` VALUES (1, 1, 2, 100, 150, '2023-02-20 11:01:06', '2023-02-22 09:21:24', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (9, 2, 1, 10, 15, '2023-02-20 20:21:58', '2023-02-22 09:19:46', '1', 1, 1);
INSERT INTO `into_warehouse` VALUES (10, 7, 3, 10, 20, '2023-02-20 20:22:28', '2023-02-20 20:22:28', '0', 1, 1);
INSERT INTO `into_warehouse` VALUES (11, 2, 1, 10, 15, '2023-02-20 21:22:48', '2023-02-20 22:13:49', '1', 1, 1);
INSERT INTO `into_warehouse` VALUES (12, 3, 5, 100, 150, '2023-02-10 17:48:50', '2023-02-10 17:49:02', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (13, 17, 11, 100, 200000, '2023-02-10 17:49:54', '2023-02-10 17:49:59', '测试数据', 1, 1);
INSERT INTO `into_warehouse` VALUES (14, 4, 5, 100, 200, '2023-02-10 17:50:10', '2023-02-10 17:50:14', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (15, 6, 10, 100, 200, '2023-02-10 17:50:19', '2023-02-10 17:50:23', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (16, 5, 3, 120, 60, '2023-02-11 17:50:28', '2023-02-11 17:50:32', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (17, 6, 10, 100, 200, '2023-02-12 17:50:36', '2023-02-12 17:50:51', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (18, 7, 3, 150, 300, '2023-02-12 17:50:55', '2023-02-12 17:50:58', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (19, 8, 3, 100, 50, '2023-02-12 17:51:02', '2023-02-12 17:51:07', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (20, 9, 4, 100, 350, '2023-02-13 17:51:56', '2023-02-13 17:52:13', '测试数据', 1, 1);
INSERT INTO `into_warehouse` VALUES (21, 10, 8, 180, 180, '2023-02-13 17:52:18', '2023-02-13 17:52:22', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (22, 11, 5, 100, 100, '2023-02-14 17:52:30', '2023-02-14 17:52:34', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (23, 12, 8, 100, 30, '2023-02-14 17:52:37', '2023-02-14 17:52:41', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (24, 1, 5, 100, 100, '2023-02-15 17:52:44', '2023-02-15 17:52:48', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (25, 6, 3, 100, 100, '2023-02-15 17:52:53', '2023-02-15 17:52:57', '测试数据', 1, 1);
INSERT INTO `into_warehouse` VALUES (26, 15, 7, 20, 40000, '2023-02-15 17:53:00', '2023-02-15 17:53:04', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (27, 16, 9, 20, 50000, '2023-02-15 17:53:11', '2023-02-15 17:53:14', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (28, 17, 11, 20, 40000, '2023-02-17 17:53:20', '2023-02-17 17:53:49', '测试数据', 1, 1);
INSERT INTO `into_warehouse` VALUES (29, 18, 7, 50, 10000, '2023-02-17 17:53:53', '2023-02-17 17:53:57', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (30, 19, 7, 100, 1000, '2023-02-18 17:54:03', '2023-02-18 17:54:07', '测试数据', 1, 1);
INSERT INTO `into_warehouse` VALUES (31, 20, 7, 100, 900, '2023-02-20 17:54:14', '2023-02-20 17:54:19', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (32, 21, 6, 50, 50000, '2023-02-21 17:54:23', '2023-02-21 17:54:28', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (33, 22, 6, 100, 20000, '2023-02-22 17:54:31', '2023-02-22 17:54:41', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (34, 23, 6, 100, 20000, '2023-02-11 17:54:47', '2023-02-11 17:54:54', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (35, 24, 6, 100, 3000, '2023-02-12 17:54:59', '2023-02-12 17:55:05', '测试数据', 1, 1);
INSERT INTO `into_warehouse` VALUES (36, 25, 6, 100, 1000, '2023-02-14 17:55:09', '2023-02-14 17:55:16', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (37, 26, 6, 100, 3000, '2023-02-15 17:55:19', '2023-02-15 17:55:22', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (38, 27, 12, 200, 10000, '2023-02-15 17:55:26', '2023-02-15 17:55:30', '测试数据', 1, 1);
INSERT INTO `into_warehouse` VALUES (39, 28, 12, 100, 8000, '2023-02-18 17:55:34', '2023-02-18 17:55:40', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (40, 29, 12, 100, 3000, '2023-02-12 17:55:44', '2023-02-12 17:55:49', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (41, 4, 7, 100, 3500, '2023-02-15 17:55:52', '2023-02-15 17:55:56', '测试数据', 0, 1);
INSERT INTO `into_warehouse` VALUES (42, 27, 12, 100, 5000, '2023-02-14 17:56:00', '2023-02-14 17:56:03', '测试数据', 0, 1);

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
  `out_warehouse_state` int(255) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of out_warehouse
-- ----------------------------
INSERT INTO `out_warehouse` VALUES (1, 2, 1, 10, 35, 0, '12', '2023-02-01 09:31:03', '2023-02-01 14:54:01', 1);
INSERT INTO `out_warehouse` VALUES (2, 1, 1, 10, 35, 0, NULL, '2023-02-03 10:23:02', '2023-02-03 10:23:02', 1);
INSERT INTO `out_warehouse` VALUES (3, 1, 9, 10, 35, 1, '', '2023-02-04 10:29:28', '2023-02-04 15:07:03', 1);
INSERT INTO `out_warehouse` VALUES (4, 2, 2, 10, 35, 0, '2', '2023-02-06 11:26:00', '2023-02-06 11:26:00', 1);
INSERT INTO `out_warehouse` VALUES (5, 1, 10, 10, 35, 0, '23', '2023-02-07 11:28:34', '2023-02-07 11:28:34', 1);
INSERT INTO `out_warehouse` VALUES (6, 6, 3, 20, 70, 0, NULL, '2023-02-20 20:00:44', '2023-02-20 20:00:48', 1);
INSERT INTO `out_warehouse` VALUES (7, 7, 11, 25, 100, 0, NULL, '2023-02-12 20:00:52', '2023-02-12 20:00:56', 1);
INSERT INTO `out_warehouse` VALUES (8, 8, 3, 66, 66, 1, NULL, '2023-02-04 20:01:01', '2023-02-04 20:01:05', 1);
INSERT INTO `out_warehouse` VALUES (9, 9, 8, 20, 198, 0, NULL, '2023-02-18 20:01:09', '2023-02-18 20:01:13', 1);
INSERT INTO `out_warehouse` VALUES (10, 10, 2, 25, 100, 0, NULL, '2023-02-24 20:01:18', '2023-02-24 20:01:24', 1);
INSERT INTO `out_warehouse` VALUES (11, 11, 9, 55, 110, 0, NULL, '2023-02-25 20:01:36', '2023-02-25 20:01:42', 1);
INSERT INTO `out_warehouse` VALUES (12, 17, 9, 20, 80000, 0, NULL, '2023-02-12 20:01:46', '2023-02-12 20:01:49', 1);
INSERT INTO `out_warehouse` VALUES (13, 13, 11, 50, 200, 1, NULL, '2023-02-04 20:03:12', '2023-02-04 20:03:15', 1);
INSERT INTO `out_warehouse` VALUES (14, 14, 7, 88, 176, 0, NULL, '2023-02-11 20:02:58', '2023-02-11 20:03:01', 1);
INSERT INTO `out_warehouse` VALUES (15, 15, 2, 10, 40000, 0, NULL, '2023-02-13 20:03:19', '2023-02-13 20:03:22', 1);
INSERT INTO `out_warehouse` VALUES (16, 16, 6, 40, 180000, 0, NULL, '2023-02-03 20:03:26', '2023-02-03 20:03:30', 1);
INSERT INTO `out_warehouse` VALUES (17, 17, 2, 10, 59990, 0, NULL, '2023-02-10 20:04:35', '2023-02-10 20:04:40', 1);
INSERT INTO `out_warehouse` VALUES (18, 18, 9, 40, 32000, 1, NULL, '2023-02-17 20:05:43', '2023-02-17 20:05:36', 1);
INSERT INTO `out_warehouse` VALUES (19, 19, 11, 50, 1500, 0, NULL, '2023-02-01 20:06:53', '2023-02-01 20:06:57', 1);
INSERT INTO `out_warehouse` VALUES (20, 20, 7, 100, 2990, 0, NULL, '2023-02-18 20:04:24', '2023-02-18 20:04:30', 1);
INSERT INTO `out_warehouse` VALUES (21, 21, 12, 30, 60000, 1, NULL, '2023-02-05 20:07:00', '2023-02-05 20:07:03', 1);
INSERT INTO `out_warehouse` VALUES (22, 22, 13, 50, 25000, 0, NULL, '2023-02-15 20:05:17', '2023-02-15 20:05:22', 1);
INSERT INTO `out_warehouse` VALUES (23, 23, 12, 50, 33300, 0, NULL, '2023-02-03 20:06:43', '2023-02-03 20:06:47', 1);
INSERT INTO `out_warehouse` VALUES (24, 24, 10, 100, 9900, 0, NULL, '2023-02-21 20:07:06', '2023-02-21 20:07:11', 1);
INSERT INTO `out_warehouse` VALUES (25, 25, 10, 200, 1000, 0, NULL, '2023-02-02 20:04:04', '2023-02-02 20:04:09', 1);
INSERT INTO `out_warehouse` VALUES (26, 26, 26, 100, 9900, 0, NULL, '2023-02-22 20:07:21', '2023-02-22 20:07:24', 1);
INSERT INTO `out_warehouse` VALUES (27, 27, 10, 40, 4000, 1, NULL, '2023-02-05 20:04:12', '2023-02-05 20:04:16', 1);
INSERT INTO `out_warehouse` VALUES (28, 28, 10, 50, 80000, 0, NULL, '2023-02-09 20:05:47', '2023-02-09 20:05:52', 1);
INSERT INTO `out_warehouse` VALUES (29, 17, 9, 10, 59990, 0, NULL, '2023-02-12 20:07:27', '2023-02-12 20:07:31', 1);
INSERT INTO `out_warehouse` VALUES (30, 16, 2, 20, 90000, 1, NULL, '2023-02-02 20:02:29', '2023-02-02 20:02:34', 1);
INSERT INTO `out_warehouse` VALUES (31, 16, 8, 20, 90000, 0, NULL, '2023-02-19 20:06:32', '2023-02-19 20:06:38', 1);
INSERT INTO `out_warehouse` VALUES (32, 16, 9, 20, 90000, 0, NULL, '2023-02-19 20:03:38', '2023-02-19 20:03:42', 1);
INSERT INTO `out_warehouse` VALUES (33, 17, 8, 10, 59990, 0, NULL, '2023-02-18 20:06:22', '2023-02-18 20:06:27', 1);
INSERT INTO `out_warehouse` VALUES (34, 24, 2, 55, 5500, 0, NULL, '2023-02-18 20:02:38', '2023-02-18 20:02:41', 1);
INSERT INTO `out_warehouse` VALUES (35, 24, 13, 66, 6600, 0, NULL, '2023-02-16 20:03:47', '2023-02-16 20:03:52', 1);
INSERT INTO `out_warehouse` VALUES (36, 23, 2, 10, 6660, 0, NULL, '2023-02-09 20:03:56', '2023-02-09 20:04:00', 1);
INSERT INTO `out_warehouse` VALUES (37, 23, 4, 40, 26400, 1, NULL, '2023-02-09 20:02:00', '2023-02-09 20:02:05', 1);
INSERT INTO `out_warehouse` VALUES (38, 17, 14, 10, 59990, 0, NULL, '2023-02-18 20:06:14', '2023-02-18 20:06:17', 1);
INSERT INTO `out_warehouse` VALUES (39, 25, 10, 200, 1000, 0, NULL, '2023-02-14 20:02:20', '2023-02-14 20:02:24', 1);
INSERT INTO `out_warehouse` VALUES (40, 14, 10, 100, 200, 0, NULL, '2023-02-11 20:06:04', '2023-02-11 20:06:10', 1);
INSERT INTO `out_warehouse` VALUES (41, 13, 10, 50, 200, 1, NULL, '2023-02-10 20:04:51', '2023-02-10 20:04:47', 1);
INSERT INTO `out_warehouse` VALUES (42, 15, 8, 20, 80000, 0, NULL, '2023-02-19 20:02:08', '2023-02-19 20:02:14', 1);
INSERT INTO `out_warehouse` VALUES (43, 27, 13, 35, 3500, 0, NULL, '2023-02-20 20:05:57', '2023-02-20 20:06:00', 1);
INSERT INTO `out_warehouse` VALUES (44, 6, 1, 100, 350, 0, NULL, '2023-02-04 20:04:55', '2023-02-04 20:05:02', 1);
INSERT INTO `out_warehouse` VALUES (45, 22, 12, 50, 25000, 0, NULL, '2023-02-07 20:05:06', '2023-02-07 20:05:13', 1);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `link_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '百事可乐供应商', '张吉惟', '13786541256', '北京', '2023-02-22 17:02:22', '2023-02-24 16:00:46', 1);
INSERT INTO `supplier` VALUES (2, '可口可乐供应商', '林国瑞', '18965411256', '上海', '2023-02-22 17:02:36', '2023-02-22 17:03:10', 1);
INSERT INTO `supplier` VALUES (3, '食品加工厂', '江奕云', '13876763423', '广州', '2023-02-22 17:02:39', '2023-02-22 17:03:13', 1);
INSERT INTO `supplier` VALUES (4, '杯子制造工厂', '刘柏宏', '18546378765', '深圳', '2023-02-22 17:02:42', '2023-02-22 17:03:16', 1);
INSERT INTO `supplier` VALUES (5, '饮品加工厂', '雷进宝', '18723437621', '杭州', '2023-02-22 17:02:45', '2023-02-22 17:03:19', 1);
INSERT INTO `supplier` VALUES (6, '服装生产厂', '卢木仲', '13573623498', '武汉', '2023-02-22 17:02:48', '2023-02-22 17:03:22', 1);
INSERT INTO `supplier` VALUES (7, '电器加工厂', '李成白', '13526734563', '九江', '2023-02-22 17:02:51', '2023-02-22 17:03:25', 1);
INSERT INTO `supplier` VALUES (8, '百货加工厂', '王美珠', '15276763428', '上海', '2023-02-22 17:02:54', '2023-02-22 17:03:28', 1);
INSERT INTO `supplier` VALUES (9, '联想加工厂', '郭芳天', '13697632544', '郑州', '2023-02-22 17:02:57', '2023-02-22 17:03:30', 1);
INSERT INTO `supplier` VALUES (10, '乐视薯片供应商', '方一强', '18347289384', '荆门', '2023-02-22 17:03:00', '2023-02-22 17:03:33', 1);
INSERT INTO `supplier` VALUES (11, 'iPhone供应商', '林子帆', '13927362763', '宜昌', '2023-02-22 17:03:03', '2023-02-22 17:03:35', 1);
INSERT INTO `supplier` VALUES (12, '化妆品加工厂', '李秀风', '13898234589', '兰州', '2023-02-22 17:32:51', '2023-02-22 17:32:54', 1);
INSERT INTO `supplier` VALUES (13, '111', '玩啥呢', '13546895264', '北京的', '2023-02-24 14:43:45', '2023-02-24 14:43:57', 0);
INSERT INTO `supplier` VALUES (16, '我', '135', '13597956352', '12', '2023-02-24 16:06:05', '2023-02-24 16:06:05', 0);

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `module_style` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块样式',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `parent_id` int(11) NULL DEFAULT NULL,
  `parent_opt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(11) NULL DEFAULT NULL COMMENT '等级',
  `opt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `orders` int(11) NULL DEFAULT NULL,
  `is_valid` tinyint(4) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 192 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES (1, '系统管理', NULL, '#', -1, NULL, NULL, '10', NULL, 0, '2023-02-20 10:53:57', '2023-02-20 17:30:51');
INSERT INTO `t_module` VALUES (2, '采购计划管理', NULL, '#', 1, NULL, NULL, NULL, NULL, 0, '2023-02-20 11:35:29', '2023-02-20 17:30:48');
INSERT INTO `t_module` VALUES (166, '采购计划管理', '', NULL, -1, NULL, 0, '10', NULL, 1, '2023-02-20 17:28:07', '2023-02-20 17:32:32');
INSERT INTO `t_module` VALUES (167, '仓库管理', '', NULL, -1, NULL, 0, '20', NULL, 1, '2023-02-20 17:28:39', '2023-02-20 17:32:35');
INSERT INTO `t_module` VALUES (168, '销售管理', '', NULL, -1, NULL, 0, '30', NULL, 1, '2023-02-20 17:28:51', '2023-02-20 17:32:39');
INSERT INTO `t_module` VALUES (169, '供需商管理', '', NULL, -1, NULL, 0, '40', NULL, 1, '2023-02-20 17:29:14', '2023-02-20 17:32:41');
INSERT INTO `t_module` VALUES (170, '物品管理', '', NULL, -1, NULL, 0, '50', NULL, 1, '2023-02-20 17:29:25', '2023-02-20 17:32:45');
INSERT INTO `t_module` VALUES (171, '系统设置', '', NULL, -1, NULL, 0, '60', NULL, 1, '2023-02-20 17:31:14', '2023-02-20 17:32:49');
INSERT INTO `t_module` VALUES (172, '采购计划管理', '', '#shopping', 166, NULL, 1, '1010', NULL, 1, '2023-02-20 17:37:03', '2023-02-20 17:37:03');
INSERT INTO `t_module` VALUES (173, '库存管理', '', '#wareHouseNumber', 167, NULL, 1, '2010', NULL, 1, '2023-02-20 17:37:44', '2023-02-20 17:37:44');
INSERT INTO `t_module` VALUES (174, '销售计划管理', '', '#sale', 168, NULL, 1, '3010', NULL, 1, '2023-02-20 17:38:25', '2023-02-20 17:38:25');
INSERT INTO `t_module` VALUES (175, '供应商管理', '', '#supplier', 169, NULL, 1, '4010', NULL, 1, '2023-02-20 17:39:03', '2023-02-20 17:39:03');
INSERT INTO `t_module` VALUES (176, '需求商管理', '', '#customers', 169, NULL, 1, '4020', NULL, 1, '2023-02-20 17:39:35', '2023-02-20 17:39:35');
INSERT INTO `t_module` VALUES (177, '物品管理', '', '#goods', 170, NULL, 1, '5010', NULL, 1, '2023-02-20 17:39:57', '2023-02-20 17:39:57');
INSERT INTO `t_module` VALUES (178, '物品类型管理', '', '#goodsType', 170, NULL, 1, '5020', NULL, 1, '2023-02-20 17:40:54', '2023-02-20 17:40:54');
INSERT INTO `t_module` VALUES (179, '用户管理', '', '#user', 171, NULL, 1, '6010', NULL, 1, '2023-02-20 17:41:48', '2023-02-20 17:41:48');
INSERT INTO `t_module` VALUES (180, '角色管理', '', '#roles', 171, NULL, 1, '6020', NULL, 1, '2023-02-20 17:42:06', '2023-02-20 17:42:06');
INSERT INTO `t_module` VALUES (181, '菜单管理', '', '#menuManagement', 171, NULL, 1, '6030', NULL, 1, '2023-02-20 17:43:18', '2023-02-20 17:43:18');
INSERT INTO `t_module` VALUES (182, '测试228', '', NULL, 172, NULL, 2, '101001', NULL, 0, '2023-02-20 17:57:54', '2023-02-23 10:41:54');
INSERT INTO `t_module` VALUES (183, '搜索', '', NULL, 177, NULL, 2, '501001', NULL, 0, '2023-02-22 17:33:37', '2023-02-23 17:38:49');
INSERT INTO `t_module` VALUES (184, '添加商品', '', NULL, 177, NULL, 2, '501002', NULL, 1, '2023-02-22 17:34:02', '2023-02-22 17:34:02');
INSERT INTO `t_module` VALUES (185, '删除商品', '', NULL, 177, NULL, 2, '501003', NULL, 1, '2023-02-22 17:34:26', '2023-02-22 17:34:26');
INSERT INTO `t_module` VALUES (186, '修改商品', '', NULL, 177, NULL, 2, '501004', NULL, 1, '2023-02-22 17:34:42', '2023-02-22 17:34:42');
INSERT INTO `t_module` VALUES (187, '删除', '', NULL, 177, NULL, 2, '501005', NULL, 0, '2023-02-22 17:35:01', '2023-02-23 17:34:19');
INSERT INTO `t_module` VALUES (188, '增加', '', NULL, 172, NULL, 2, '101001', NULL, 1, '2023-02-23 10:42:21', '2023-02-23 10:42:21');
INSERT INTO `t_module` VALUES (190, '删除', '', NULL, 172, NULL, 2, '101002', NULL, 1, '2023-02-23 14:38:58', '2023-02-23 14:38:58');
INSERT INTO `t_module` VALUES (191, '搜索', '', NULL, 172, NULL, 2, '101003', NULL, 1, '2023-02-23 14:47:36', '2023-02-23 14:47:36');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `module_id` int(11) NULL DEFAULT NULL COMMENT '模块ID',
  `acl_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7726 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (7647, 1, 166, '10', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7648, 1, 172, '1010', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7649, 1, 188, '101001', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7650, 1, 190, '101002', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7651, 1, 191, '101003', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7652, 1, 167, '20', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7653, 1, 173, '2010', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7654, 1, 168, '30', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7655, 1, 174, '3010', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7656, 1, 169, '40', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7657, 1, 175, '4010', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7658, 1, 176, '4020', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7659, 1, 170, '50', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7660, 1, 177, '5010', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7662, 1, 184, '501002', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7663, 1, 185, '501003', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7664, 1, 186, '501004', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7666, 1, 178, '5020', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7667, 1, 171, '60', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7668, 1, 179, '6010', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7669, 1, 180, '6020', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7670, 1, 181, '6030', '2023-02-23 17:32:59', '2023-02-23 17:32:59');
INSERT INTO `t_permission` VALUES (7676, 2, 168, '30', '2023-02-24 17:20:20', '2023-02-24 17:20:20');
INSERT INTO `t_permission` VALUES (7677, 2, 174, '3010', '2023-02-24 17:20:20', '2023-02-24 17:20:20');
INSERT INTO `t_permission` VALUES (7724, 25, 167, '20', '2023-02-24 17:21:35', '2023-02-24 17:21:35');
INSERT INTO `t_permission` VALUES (7725, 25, 173, '2010', '2023-02-24 17:21:35', '2023-02-24 17:21:35');

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `t_role` VALUES (21, 'xy', NULL, '2023-02-13 10:59:35', '2023-02-22 17:08:05', 0);
INSERT INTO `t_role` VALUES (22, '测试经理', '张三2', '2023-02-13 11:01:48', '2023-02-24 17:20:55', 0);
INSERT INTO `t_role` VALUES (23, '大boss的', '阿萨德的', '2023-02-13 11:48:57', '2023-02-13 14:21:47', 0);
INSERT INTO `t_role` VALUES (24, '新一代', 'ad', '2023-02-13 12:40:04', '2023-02-13 12:40:04', 0);
INSERT INTO `t_role` VALUES (25, '仓库管理员', '张三', '2023-02-22 17:04:39', '2023-02-22 17:04:39', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (10, 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', '126@126.com', '13327792157', 1, '2016-12-01 12:05:49', '2023-02-13 21:32:45');
INSERT INTO `t_user` VALUES (42, 'scott', '4QrcOUm6Wau+VuBX8g+IPg==', 'scott', '234@126.com', '13327792157', 1, '2017-09-09 00:14:53', '2023-02-24 17:21:58');
INSERT INTO `t_user` VALUES (79, 'zhangsan', '4QrcOUm6Wau+VuBX8g+IPg==', NULL, 'zhangsan@163.com', '18876545687', 1, '2020-11-05 17:15:50', '2020-11-05 17:15:50');
INSERT INTO `t_user` VALUES (80, 'lisi', '4QrcOUm6Wau+VuBX8g+IPg==', 'lisi', 'lisi@163.com', '18876767890', 1, '2020-11-05 17:16:35', '2020-11-05 17:16:35');
INSERT INTO `t_user` VALUES (81, 'test', '4QrcOUm6Wau+VuBX8g+IPg==', '王五', 'testa@163.com', '15856787654', 1, '2020-11-05 17:33:21', '2023-02-23 20:44:04');
INSERT INTO `t_user` VALUES (82, 'aabb', '4QrcOUm6Wau+VuBX8g+IPg==', 'ab', 'ab@163.com', '13876545678', 0, '2020-11-05 19:44:30', '2020-11-05 20:12:52');
INSERT INTO `t_user` VALUES (85, 'test001', '4QrcOUm6Wau+VuBX8g+IPg==', '测试一号', 'test001@163.com', '13787654345', 0, '2020-11-10 03:35:05', '2020-11-10 03:38:25');
INSERT INTO `t_user` VALUES (86, 'test002', '4QrcOUm6Wau+VuBX8g+IPg==', '测试二号', 'test002@163.com', '13876545678', 0, '2020-11-10 03:36:03', '2020-11-10 03:36:03');
INSERT INTO `t_user` VALUES (87, 'zhangsan', '4QrcOUm6Wau+VuBX8g+IPg==', NULL, '123456@qq.com', '13797980203', 0, '2023-02-10 20:20:47', '2023-02-10 20:20:47');
INSERT INTO `t_user` VALUES (88, 'zha', '4QrcOUm6Wau+VuBX8g+IPg==', '123', '126@1267.com', '13797980365', 0, '2023-02-10 21:50:29', '2023-02-11 09:22:25');
INSERT INTO `t_user` VALUES (90, '薛洋', '4QrcOUm6Wau+VuBX8g+IPg==', '薛洋', '123@qq.com', '13797970383', 0, '2023-02-13 09:35:52', '2023-02-13 09:37:43');
INSERT INTO `t_user` VALUES (92, 'x', '4QrcOUm6Wau+VuBX8g+IPg==', 'as', '12@qq.com', '13986984356', 0, '2023-02-23 20:38:57', '2023-02-23 20:39:07');

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
) ENGINE = InnoDB AUTO_INCREMENT = 219 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (198, 80, 2, NULL, NULL);
INSERT INTO `t_user_role` VALUES (204, 10, 1, '2023-02-13 21:32:45', '2023-02-13 21:32:45');
INSERT INTO `t_user_role` VALUES (209, 81, 1, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (210, 81, 2, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (211, 81, 3, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (212, 81, 14, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (213, 81, 17, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (214, 81, 18, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (215, 81, 22, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (216, 81, 25, '2023-02-23 20:44:03', '2023-02-23 20:44:03');
INSERT INTO `t_user_role` VALUES (218, 42, 25, '2023-02-24 17:21:58', '2023-02-24 17:21:58');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `goods_number` int(11) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, 1, 510, '2023-02-20 09:13:42', '2023-02-24 11:46:20', 1);
INSERT INTO `warehouse` VALUES (2, 2, 500, '2023-02-20 20:21:58', '2023-02-22 09:19:45', 1);
INSERT INTO `warehouse` VALUES (3, 3, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (4, 4, 300, '2023-02-22 20:10:13', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (5, 5, 300, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (6, 6, 300, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (7, 7, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (8, 8, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (9, 9, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (10, 10, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (11, 11, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (12, 12, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (13, 13, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (14, 14, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (15, 15, 100, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (16, 16, 100, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (17, 17, 100, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (18, 18, 100, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (19, 19, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (20, 20, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (21, 21, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (22, 22, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (23, 23, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (24, 24, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (25, 25, 500, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (26, 26, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (27, 27, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (28, 28, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (29, 29, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);
INSERT INTO `warehouse` VALUES (30, 30, 200, '2023-02-01 20:10:39', '2023-02-02 20:10:39', 1);

SET FOREIGN_KEY_CHECKS = 1;
