SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `id` varchar(50) NOT NULL,
  `org_type` varchar(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `phone_no` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `locked` tinyint DEFAULT NULL,
  `locked_time` datetime DEFAULT NULL,
  `expired` tinyint DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `deleted` tinyint DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `city_code` varchar(50) DEFAULT NULL,
  `branch_code` varchar(50) DEFAULT NULL,
  `district_code` varchar(50) DEFAULT NULL,
  `district_name` varchar(50) DEFAULT NULL,
  `mechanism_code` varchar(50) DEFAULT NULL,
  `mechanism_name` varchar(50) DEFAULT NULL,
  `entry_date` date DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `avatar_url` text,
  PRIMARY KEY (`id`),
  KEY `idx_sec_user_org_type` (`org_type`),
  KEY `idx_sec_user_locked` (`locked`),
  KEY `idx_sec_user_expired` (`expired`),
  KEY `idx_sec_user_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sec_user
-- ----------------------------
BEGIN;
INSERT INTO `sec_user` VALUES ('1183662961333968898', 'inside', 'admin', 'admin', NULL, NULL, NULL, '13052253110', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-15', NULL, 'https://pic3.zhimg.com/80/v2-0115da355a5abe2d455855aefc036586_1440w.jpg');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
