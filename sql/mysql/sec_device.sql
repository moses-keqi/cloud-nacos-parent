SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_device
-- ----------------------------
DROP TABLE IF EXISTS `sec_device`;
CREATE TABLE `sec_device` (
  `device_id` varchar(255) NOT NULL,
  `client` varchar(255) NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_name` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint NOT NULL,
  `id` varchar(50) NOT NULL,
  `user_agent` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_device_un` (`device_id`,`client`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sec_device
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
