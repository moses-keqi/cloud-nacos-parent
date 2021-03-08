SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_about_version
-- ----------------------------
DROP TABLE IF EXISTS `sec_about_version`;
CREATE TABLE `sec_about_version` (
  `id` varchar(50) NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint NOT NULL,
  `version` varchar(10) DEFAULT NULL,
  `update_type` varchar(2) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `client` varchar(50) DEFAULT NULL,
  `md5_secret` varchar(50) DEFAULT NULL,
  `down_url` varchar(200) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `enabled` tinyint DEFAULT NULL,
  `zip_secret` varchar(100) NOT NULL,
  `route_code` varchar(50) DEFAULT NULL,
  `on_line` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sec_about_version
-- ----------------------------
BEGIN;
INSERT INTO `sec_about_version` VALUES ('1356489190035976194', 'system', 'system', '2021-02-02 14:27:39', 'admin', 'admin', '2021-02-25 11:33:10', 0, '1.001', '2', '随意更新', 'ios', 'e81ac154c235e17e36fcdabb487232d6', 'http://www.baidu.com', '测试产品', 1, 'psohnmdtuutczk7lvotqta==', NULL, 0);
INSERT INTO `sec_about_version` VALUES ('13564891900359761941', 'system', 'system', '2021-02-02 14:27:39', 'system', 'system', '2021-02-02 14:27:47', 0, '1.001', '2', '随意更新android', 'android', 'e81ac154c235e17e36fcdabb487232d6', 'http://www.baidu.com', '测试产品', 1, 'psohnmdtuutczk7lvotqta==', NULL, NULL);
INSERT INTO `sec_about_version` VALUES ('13564891900359761942', 'system', 'system', '2021-02-02 14:27:39', 'admin', 'admin', '2021-02-25 16:56:19', 0, '1.002', '2', 'h5随便包', 'h5', '99ca20ecba32615ac9026771ce0ecfa7', 'https://huisu.fulan.com.cn/update-h5/1.002/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', NULL, 1);
INSERT INTO `sec_about_version` VALUES ('1364422899871633410', 'admin', 'admin', '2021-02-24 11:53:00', 'admin', 'admin', '2021-02-25 15:59:23', 0, '1.003', '2', 'h5随便包', 'h5', '2c4c26d18ca8d185f0fa9f95c6aaa0c6', 'http://huisu.fulan.com.cn/update-h5/1.003/mobile.zip', '测试产品1', 0, 'sreezivlrhjnjk8gzukurarsqukhbnm8spmgt61zqrtxphnznkjjhz9ovqwaxu+s', 'ewe', 1);
INSERT INTO `sec_about_version` VALUES ('1364846912338956290', 'admin', 'admin', '2021-02-25 15:59:10', 'admin', 'admin', '2021-02-25 15:59:30', 0, '1.004', '2', '孙祥凯测试', 'h5', 'fafe52eac6892c80b951b8d5afb9b501', 'http://huisu.fulan.com.cn/update-h5/1.004/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
INSERT INTO `sec_about_version` VALUES ('1364850233145593857', 'admin', 'admin', '2021-02-25 16:11:00', 'admin', 'admin', '2021-02-25 16:11:00', 0, '1.005', '2', '孙祥凯测试', 'h5', '66514d9ef0dd4cf3157f7126ec43ee4e', 'http://huisu.fulan.com.cn/update-h5/1.005/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
INSERT INTO `sec_about_version` VALUES ('1364862733945704449', 'admin', 'admin', '2021-02-25 17:00:58', 'admin', 'admin', '2021-02-25 20:04:07', 0, '1.006', '2', '孙祥凯测试', 'h5', 'ce266200e9203ad82bd27ebf99ff9bfe', 'http://huisu.fulan.com.cn/update-h5/1.006/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
INSERT INTO `sec_about_version` VALUES ('1364864635211124737', 'admin', 'admin', '2021-02-25 17:08:15', 'admin', 'admin', '2021-02-25 20:08:16', 0, '1.007', '2', '孙祥凯测试', 'h5', '59c18614f15c9111f9cea7a0d3568b9c', 'http://huisu.fulan.com.cn/update-h5/1.007/mobile.zip', '测试产品1', 1, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
