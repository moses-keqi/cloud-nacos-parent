SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_menu`;
CREATE TABLE `sec_menu` (
  `id` varchar(50) NOT NULL,
  `parent_id` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `permission_string` varchar(255) NOT NULL,
  `sort_index` int DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `disabled` tinyint NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `app_type` varchar(20) NOT NULL,
  `deleted` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sec_menu
-- ----------------------------
BEGIN;
INSERT INTO `sec_menu` VALUES ('1169127998652178434', '', 'pc菜单', 'pc', 1, '', '', 0, 'admin', 'admin', '2019-09-04 14:00:11', '', '', NULL, 'pc', NULL);
INSERT INTO `sec_menu` VALUES ('1169128813513170945', '1169526378566025217', '角色管理', 'pc:role:manage', 2, 'role', '', 0, 'admin', 'admin', '2019-09-04 14:03:25', '', '', NULL, 'pc', NULL);
INSERT INTO `sec_menu` VALUES ('1169128932719484930', '1169526378566025217', '资源管理', 'pc:system:resource', 3, 'code', '', 0, 'admin', 'admin', '2019-09-04 14:03:54', '', '', NULL, 'pc', NULL);
INSERT INTO `sec_menu` VALUES ('1169129156653375489', '1169526378566025217', '菜单管理', 'pc:system:menu', 4, 'menu', '', 0, 'admin', 'admin', '2019-09-04 14:04:47', '', '', NULL, 'pc', NULL);
INSERT INTO `sec_menu` VALUES ('1169130216667242497', '1169526378566025217', '选项配置', 'pc:system:dict', 5, 'option', '', 0, 'admin', 'admin', '2019-09-04 14:09:00', '', '', NULL, 'pc', NULL);
INSERT INTO `sec_menu` VALUES ('1169130316277768194', '1169526378566025217', '系统配置', 'pc:system:sysdict', 6, 'system', '', 0, 'admin', 'admin', '2019-09-04 14:09:23', '', '', NULL, 'pc', NULL);
INSERT INTO `sec_menu` VALUES ('1169526378566025217', '1169127998652178434', '系统管理', 'pc:system', 11, 'systemconfig', 'systemconfig', 0, 'admin', 'admin', '2019-09-05 16:23:12', '', '', NULL, 'pc', NULL);
INSERT INTO `sec_menu` VALUES ('573e82a8226f0e57b04cbbdea5bb901d', '1169526378566025217', '版本配置', 'pc:system:edition', 120, 'edition', NULL, 0, 'admin', 'admin', '2021-02-23 10:07:25', NULL, NULL, NULL, 'pc', 0);
INSERT INTO `sec_menu` VALUES ('6264c909e53fe64e519f7fd0bece7d8c', '1169526378566025217', '富文本配置', 'pc:system:editor', 200, 'editor', '', 0, 'admin', 'admin', '2021-03-02 11:07:51', NULL, NULL, NULL, 'pc', 0);
INSERT INTO `sec_menu` VALUES ('b962ee9f227600b9d49bf8adc9d57c12', '1169526378566025217', '贺卡配置', 'pc:system:card', 100, 'card', '', 0, 'admin', 'admin', '2021-02-22 17:54:17', NULL, NULL, NULL, 'pc', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
