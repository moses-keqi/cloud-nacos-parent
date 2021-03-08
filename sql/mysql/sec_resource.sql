SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_resource
-- ----------------------------
DROP TABLE IF EXISTS `sec_resource`;
CREATE TABLE `sec_resource` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `resource_string` varchar(255) NOT NULL,
  `permission_string` varchar(255) NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `app_type` varchar(20) NOT NULL,
  `disabled` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sec_resource_org_type` (`app_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sec_resource
-- ----------------------------
BEGIN;
INSERT INTO `sec_resource` VALUES ('060b3f136a3e58cb8b16b570f173b086', '查询福利信息', '/mapi/agentwelfareinfodetails/findagentwelfareinfodetails', 'mapi:agentwelfareinfodetails:findagentwelfareinfodetails', '1183662961333968898', 'admin', '2021-03-02 11:11:04', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482593437106178', '菜单管理-修改菜单', '/mapi/security/menu/update', 'mapi:security:menu:update', '1168723193085583361', 'admin', '2019-10-08 16:12:56', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482597937594370', '菜单管理-创建菜单', '/mapi/security/menu/create', 'mapi:security:menu:create', '1168723193085583361', 'admin', '2019-10-08 16:12:57', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482599938277377', '菜单管理-删除菜单', '/mapi/security/menu/deletebyid', 'mapi:security:menu:deletebyid', '1168723193085583361', 'admin', '2019-10-08 16:12:57', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482601129459713', '菜单管理-根据id查询菜单', '/mapi/security/menu/findbyid', 'mapi:security:menu:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:12:58', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482605147602945', '菜单管理-查询权限树', '/mapi/security/menu/findmenu', 'mapi:security:menu:findmenu', '1168723193085583361', 'admin', '2019-10-08 16:12:59', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482606099709953', '菜单管理-删除资源', '/mapi/security/menu/deleteresourcebyid', 'mapi:security:menu:deleteresourcebyid', '1168723193085583361', 'admin', '2019-10-08 16:12:59', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482607114731522', '菜单管理-分页查询资源', '/mapi/security/menu/pageresourcebycondition', 'mapi:security:menu:pageresourcebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:00', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482610050744322', '菜单管理-保存菜单与资源关联', '/mapi/security/menu/savemenuwithresource', 'mapi:security:menu:savemenuwithresource', '1168723193085583361', 'admin', '2019-10-08 16:13:00', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482612080787458', '菜单管理-根据菜单id查询绑定的资源', '/mapi/security/menu/findresourcebymenuid', 'mapi:security:menu:findresourcebymenuid', '1168723193085583361', 'admin', '2019-10-08 16:13:00', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482613691400194', '菜单管理-根据菜单id与资源id删除菜单资源绑定关系', '/mapi/security/menu/deletebindbyresourceidandmenuid', 'mapi:security:menu:deletebindbyresourceidandmenuid', '1168723193085583361', 'admin', '2019-10-08 16:13:01', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482615721443330', '资源管理-列表-修改', '/mapi/security/resource/update', 'mapi:security:resource:update', '1168723193085583361', 'admin', '2019-10-08 16:13:01', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482616983928833', '资源管理-列表-删除', '/mapi/security/resource/delete', 'mapi:security:resource:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:01', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482619576008706', '资源管理-条件-添加', '/mapi/security/resource/create', 'mapi:security:resource:create', '1168723193085583361', 'admin', '2019-10-08 16:13:03', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482620641361922', '资源管理-条件-搜索', '/mapi/security/resource/pagebycondition', 'mapi:security:resource:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:02', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482621769629698', '资源管理-通过id查询', '/mapi/security/resource/findbyid', 'mapi:security:resource:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:03', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482622595907586', '资源管理-条件-启用禁用', '/mapi/security/resource/updatedisabled', 'mapi:security:resource:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:03', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482626811183106', '角色管理-列表-修改按钮', '/mapi/security/role/update', 'mapi:security:role:update', '1168723193085583361', 'admin', '2019-10-08 16:13:04', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482628245635073', '角色管理-列表-删除按钮', '/mapi/security/role/delete', 'mapi:security:role:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:04', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482629310988289', '角色管理-条件-新增按钮', '/mapi/security/role/create', 'mapi:security:role:create', '1168723193085583361', 'admin', '2019-10-08 16:13:04', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482630594445313', '角色管理-列表-分页查询', '/mapi/security/role/pagebycondition', 'mapi:security:role:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:05', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482632297332738', '角色管理-根据id查询', '/mapi/security/role/findbyid', 'mapi:security:role:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:06', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482633220079617', '角色管理-列表-禁用启用', '/mapi/security/role/updatedisabled', 'mapi:security:role:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:05', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482634163798018', '角色管理-列表-权限配置-查询权限树', '/mapi/security/role/findpermissiontree', 'mapi:security:role:findpermissiontree', '1168723193085583361', 'admin', '2019-10-08 16:13:06', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482635367563266', '角色管理-列表-权限配置-资源授权-保存', '/mapi/security/role/saverolewithpermission', 'mapi:security:role:saverolewithpermission', '1168723193085583361', 'admin', '2019-10-08 16:13:06', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482655277924354', '系统配置-列表-修改', '/mapi/system/sysdict/update', 'mapi:system:sysdict:update', '1168723193085583361', 'admin', '2019-10-08 16:13:11', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482656104202242', '系统配置-列表-删除', '/mapi/system/sysdict/delete', 'mapi:system:sysdict:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:11', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482657362493441', '系统配置-条件-新增字典', '/mapi/system/sysdict/create', 'mapi:system:sysdict:create', '1168723193085583361', 'admin', '2019-10-08 16:13:12', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482658192965634', '系统配置-列表-禁用启用', '/mapi/system/sysdict/updatedisabled', 'mapi:system:sysdict:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:11', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482658813722626', '系统配置-条件-搜索', '/mapi/system/sysdict/pagebycondition', 'mapi:system:sysdict:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:11', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482659468034050', '系统配置-通过id查询', '/mapi/system/sysdict/findbyid', 'mapi:system:sysdict:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:12', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482660340449281', '选项配置-列表-修改', '/mapi/system/optdict/update', 'mapi:system:optdict:update', '1168723193085583361', 'admin', '2019-10-08 16:13:12', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482661493882882', '选项配置-列表-删除', '/mapi/system/optdict/delete', 'mapi:system:optdict:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:12', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482662408241154', '选项配置-条件-新增', '/mapi/system/optdict/create', 'mapi:system:optdict:create', '1168723193085583361', 'admin', '2019-10-08 16:13:12', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482663519731713', '选项配置-列表-禁用启用', '/mapi/system/optdict/updatedisabled', 'mapi:system:optdict:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:13', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482664517976066', '选项配置-条件-搜索', '/mapi/system/optdict/pagebycondition', 'mapi:system:optdict:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:13', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('1181482665973399554', '选项配置-通过id查询', '/mapi/system/optdict/findbyid', 'mapi:system:optdict:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:13', '1183662961333968898', 'admin', '2021-02-19 15:12:23', 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('3d0e86d5fcde3e3f1fc72305f717118e', '新增版本', '/mapi/version/addversion', 'mapi:version:addversion', '1183662961333968898', 'admin', '2021-02-23 17:53:11', '1183662961333968898', 'admin', '2021-02-23 17:59:59', 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('7fa4a717f4eab9e5ff9c8b2af4921d38', '修改福利信息', 'mapi/agentwelfareinfodetails/updateagentwelfareinfodetails', 'mapi:agentwelfareinfodetails:updateagentwelfareinfodetails', '1183662961333968898', 'admin', '2021-03-02 11:11:33', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('8652213088102babecd877969233ce37', '删除贺卡模板', '/mapi/agentgreetingtemplate/delete', 'mapi:agentgreetingtemplate:delete', '1183662961333968898', 'admin', '2021-02-23 09:50:45', '1183662961333968898', 'admin', '2021-02-23 10:35:34', 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('88365d1b027781a444531cbb5a9d2a7d', '查询贺卡模板', '/mapi/agentgreetingtemplate/findpagebycondition', 'mapi:agentgreetingtemplate:findpagebycondition', '1183662961333968898', 'admin', '2021-02-23 09:51:22', '1183662961333968898', 'admin', '2021-02-23 10:34:35', 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('8b33f425bec7c735123ccf7ad940d543', '查询app版本', '/mapi/version/findversionappdescription', 'mapi:version:findversionappdescription', '1183662961333968898', 'admin', '2021-02-23 17:52:42', '1183662961333968898', 'admin', '2021-02-23 18:00:08', 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('a1a626d9e987933be645c67d20cbf02f', '禁用启用版本', '/mapi/version/updateversion', 'mapi:version:updateversion', '1183662961333968898', 'admin', '2021-02-23 17:53:50', '1183662961333968898', 'admin', '2021-02-23 17:59:51', 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('a9ff5a2229c990b263491c84729375c5', '添加贺卡模板', '/mapi/agentgreetingtemplate/add', 'mapi:agentgreetingtemplate:add', '1183662961333968898', 'admin', '2021-02-23 09:48:53', '1183662961333968898', 'admin', '2021-02-23 10:35:46', 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('ce2e62e1994a8ef541dc803062e6501c', '新增福利信息', '/mapi/agentwelfareinfodetails/addagentwelfareinfodetails', 'mapi:agentwelfareinfodetails:addagentwelfareinfodetails', '1183662961333968898', 'admin', '2021-03-02 11:10:20', NULL, NULL, NULL, 'api', 'pc', 0);
INSERT INTO `sec_resource` VALUES ('fc8aef6f1429b71207acde4037c515b5', '更新贺卡模板', '/mapi/agentgreetingtemplate/update', 'mapi:agentgreetingtemplate:update', '1183662961333968898', 'admin', '2021-02-23 09:51:54', '1183662961333968898', 'admin', '2021-02-23 10:34:26', 'api', 'pc', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
