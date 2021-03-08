
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment` (
  `id` varchar(50) NOT NULL,
  `object_id` varchar(50) NOT NULL,
  `old_name` varchar(255) NOT NULL,
  `new_name` varchar(255) NOT NULL,
  `type_` varchar(50) NOT NULL,
  `file_type` varchar(50) NOT NULL,
  `storage_type` int NOT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `path_` varchar(255) DEFAULT NULL,
  `url_` varchar(255) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `sort_index` int DEFAULT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint NOT NULL,
  `bucket_name` varchar(255) DEFAULT NULL,
  KEY `idx_sys_attachment_id` (`id`),
  KEY `idx_sys_attachment_object_id` (`object_id`),
  KEY `idx_sys_attachment_type_` (`type_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
