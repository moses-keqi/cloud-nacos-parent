
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(50) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name_cn` varchar(255) DEFAULT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `dict_value` varchar(255) NOT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `node_level` int DEFAULT NULL,
  `sort_order` int DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `enabled` tinyint NOT NULL,
  `system_flag` int NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_dict_parent_id_copy1` (`parent_id`),
  KEY `idx_sys_dict_disabled_copy1` (`deleted`),
  KEY `idx_sys_dict_deleted_copy1` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('1172048919140999170', 'attachment_protect_base_path', '受保护文件存储基本路径', 'attachment_base_path', '/data/moses/private/', '0', 1, 0, '服务器附件存储基本路径大类，请勿随意更改', 0, 0, 'admin', 'admin ', '2019-09-12 15:26:53', 'admin', 'admin', '2021-02-22 11:43:58', 0);
INSERT INTO `sys_dict` VALUES ('1346759275325571079', 'aes256.enabled', 'aes256url请求开关', 'aes256', 'false', '0', 2, 1, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
INSERT INTO `sys_dict` VALUES ('1346759275325571080', 'aes256.app.enabled', 'app加密', 'aes256', 'true', '1346759275325571079', 2, 2, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
INSERT INTO `sys_dict` VALUES ('1346759275325571081', 'aes256.h5.enabled', 'h5加密', 'aes256', 'false', '1346759275325571079', 2, 3, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
INSERT INTO `sys_dict` VALUES ('1346759275325571082', 'aes256.pc.enabled', 'pc加密', 'aes256', 'false', '1346759275325571079', 2, 4, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
INSERT INTO `sys_dict` VALUES ('3e18bb283ec0de8bc5d2d0f8e449b741', 'attachment_protect_types', '受保护附件类型', NULL, 'attachment_protect_types', '67f295b013fc94dd264fc67eba2ba7c7', 1, 0, NULL, 0, 1, 'admin', 'admin', '2021-02-20 16:11:12', 'admin', 'admin', '2021-02-20 16:14:25', 0);
INSERT INTO `sys_dict` VALUES ('5aab14aacaecd5ee8fdade798a2e54c4', 'cache.route.enable', '缓存开关', NULL, 'true', '0', 1, 1, '缓存开关', 0, 1, 'admin', 'admin', '2021-02-18 16:02:28', 'admin', 'admin', '2021-02-19 14:53:36', 0);
INSERT INTO `sys_dict` VALUES ('6166f73482618b92b65df2daf4d42065', 'attachment_public_types', '公开附件类型', NULL, 'attachment_public_types', '67f295b013fc94dd264fc67eba2ba7c7', 1, 0, '公开路径类型集合', 0, 1, 'admin', 'admin', '2021-02-19 14:21:53', 'admin', 'admin', '2021-02-20 16:10:04', 0);
INSERT INTO `sys_dict` VALUES ('67f295b013fc94dd264fc67eba2ba7c7', 'attachment_types', '附件类型', NULL, 'attachment_types', '0', NULL, 0, NULL, 0, 1, 'admin', 'admin', '2021-02-20 16:19:08', NULL, NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('a6555cec6e77ccbf9d18b955385af9f0', 'cache.route.date.day', '缓存时长', NULL, '7', '0', 1, 0, '缓存时长', 0, 1, 'admin', 'admin', '2021-02-18 16:03:44', 'admin', 'admin', '2021-02-19 14:53:30', 0);
INSERT INTO `sys_dict` VALUES ('b81e3706400882f5e6914aa260f83b77', 'cache.route.url', '缓存路由地址', NULL, '1', '0', 1, 0, '缓存路由地址', 0, 1, 'admin', 'admin', '2021-02-18 15:41:58', 'admin', 'admin', '2021-02-19 14:53:52', 0);
INSERT INTO `sys_dict` VALUES ('c1f286647dddb5afff90245470b1cfc5', 'moses.sms.enabled', '短信开关', NULL, 'false', '0', 1, 0, NULL, 0, 0, 'admin', 'admin', '2021-03-01 14:06:21', NULL, NULL, NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
