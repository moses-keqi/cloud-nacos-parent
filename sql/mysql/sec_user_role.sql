SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_user_role`;
CREATE TABLE `sec_user_role` (
  `user_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  KEY `idx_sec_user_role_user_id` (`user_id`),
  KEY `idx_sec_user_role_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sec_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sec_user_role` VALUES ('1183662961333968898', '1172077184031367170');
INSERT INTO `sec_user_role` VALUES ('1183662961333968899', '98cc0a4469b1139c74e9a397ce860e1d');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
