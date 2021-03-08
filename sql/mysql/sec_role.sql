SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `role_string` varchar(255) NOT NULL,
  `disabled` tinyint NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `app_type` varchar(20) NOT NULL,
  `deleted` tinyint NOT NULL,
  `desc_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sec_role_disabled` (`disabled`),
  KEY `idx_sec_role_org_type` (`app_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sec_role
-- ----------------------------
BEGIN;
INSERT INTO `sec_role` VALUES ('1172077184031367170', 'pc超级管理员', 'admin', 0, '1168723193085583361', 'admin', '2019-09-12 17:19:11', '1183662961333968898', 'admin', '2021-02-22 18:05:16', 'pc', 0, 'pc超级管理员');
INSERT INTO `sec_role` VALUES ('85aa15f575335415b33c2ef78d2e077c', 'pc随便测试员', 'pc-test', 0, '1183662961333968898', 'admin', '2021-02-19 15:14:36', NULL, NULL, NULL, 'pc', 0, 'pc随便测试员');
INSERT INTO `sec_role` VALUES ('98cc0a4469b1139c74e9a397ce860e1d', 'pc超级管理员请勿修改', 'pc:admin', 0, '1183662961333968898', 'admin', '2021-02-22 18:04:00', '1183662961333968898', 'admin', '2021-02-22 18:05:03', 'pc', 0, 'pc超级管理员请勿修改');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
