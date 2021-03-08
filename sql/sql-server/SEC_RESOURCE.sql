
-- ----------------------------
-- Table structure for SEC_RESOURCE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SEC_RESOURCE]') AND type IN ('U'))
	DROP TABLE [dbo].[SEC_RESOURCE]
GO

CREATE TABLE [dbo].[SEC_RESOURCE] (
  [ID] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NOT NULL,
  [NAME] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [RESOURCE_STRING] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [PERMISSION_STRING] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_TIME] datetime  NOT NULL,
  [UPDATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [UPDATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_TIME] datetime DEFAULT NULL NULL,
  [TYPE] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [APP_TYPE] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [DISABLED] bit  NULL
)
GO


-- ----------------------------
-- Records of SEC_RESOURCE
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'060b3f136a3e58cb8b16b570f173b086', N'查询福利信息', N'/mapi/agentWelfareInfoDetails/findAgentWelfareInfoDetails', N'mapi:agentWelfareInfoDetails:findAgentWelfareInfoDetails', N'1183662961333968898', N'admin', N'2021-03-02 11:11:03.933', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482593437106178', N'菜单管理-修改菜单', N'/mapi/security/menu/update', N'mapi:security:menu:update', N'1168723193085583361', N'admin', N'2019-10-08 16:12:55.816', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482597937594370', N'菜单管理-创建菜单', N'/mapi/security/menu/create', N'mapi:security:menu:create', N'1168723193085583361', N'admin', N'2019-10-08 16:12:56.890', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482599938277377', N'菜单管理-删除菜单', N'/mapi/security/menu/deleteById', N'mapi:security:menu:deleteById', N'1168723193085583361', N'admin', N'2019-10-08 16:12:57.366', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482601129459713', N'菜单管理-根据ID查询菜单', N'/mapi/security/menu/findById', N'mapi:security:menu:findById', N'1168723193085583361', N'admin', N'2019-10-08 16:12:57.650', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482605147602945', N'菜单管理-查询权限树', N'/mapi/security/menu/findMenu', N'mapi:security:menu:findMenu', N'1168723193085583361', N'admin', N'2019-10-08 16:12:58.613', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482606099709953', N'菜单管理-删除资源', N'/mapi/security/menu/deleteResourceById', N'mapi:security:menu:deleteResourceById', N'1168723193085583361', N'admin', N'2019-10-08 16:12:58.836', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482607114731522', N'菜单管理-分页查询资源', N'/mapi/security/menu/pageResourceByCondition', N'mapi:security:menu:pageResourceByCondition', N'1168723193085583361', N'admin', N'2019-10-08 16:12:59.800', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482610050744322', N'菜单管理-保存菜单与资源关联', N'/mapi/security/menu/saveMenuWithResource', N'mapi:security:menu:saveMenuWithResource', N'1168723193085583361', N'admin', N'2019-10-08 16:12:59.780', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482612080787458', N'菜单管理-根据菜单Id查询绑定的资源', N'/mapi/security/menu/findResourceByMenuId', N'mapi:security:menu:findResourceByMenuId', N'1168723193085583361', N'admin', N'2019-10-08 16:13:00.263', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482613691400194', N'菜单管理-根据菜单Id与资源Id删除菜单资源绑定关系', N'/mapi/security/menu/deleteBindByResourceIdAndMenuId', N'mapi:security:menu:deleteBindByResourceIdAndMenuId', N'1168723193085583361', N'admin', N'2019-10-08 16:13:00.646', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482615721443330', N'资源管理-列表-修改', N'/mapi/security/resource/update', N'mapi:security:resource:update', N'1168723193085583361', N'admin', N'2019-10-08 16:13:01.133', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482616983928833', N'资源管理-列表-删除', N'/mapi/security/resource/delete', N'mapi:security:resource:delete', N'1168723193085583361', N'admin', N'2019-10-08 16:13:01.433', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482619576008706', N'资源管理-条件-添加', N'/mapi/security/resource/create', N'mapi:security:resource:create', N'1168723193085583361', N'admin', N'2019-10-08 16:13:02.500', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482620641361922', N'资源管理-条件-搜索', N'/mapi/security/resource/pageByCondition', N'mapi:security:resource:pageByCondition', N'1168723193085583361', N'admin', N'2019-10-08 16:13:02.306', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482621769629698', N'资源管理-通过ID查询', N'/mapi/security/resource/findById', N'mapi:security:resource:findById', N'1168723193085583361', N'admin', N'2019-10-08 16:13:02.573', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482622595907586', N'资源管理-条件-启用禁用', N'/mapi/security/resource/updateDisabled', N'mapi:security:resource:updateDisabled', N'1168723193085583361', N'admin', N'2019-10-08 16:13:02.770', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482626811183106', N'角色管理-列表-修改按钮', N'/mapi/security/role/update', N'mapi:security:role:update', N'1168723193085583361', N'admin', N'2019-10-08 16:13:03.776', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482628245635073', N'角色管理-列表-删除按钮', N'/mapi/security/role/delete', N'mapi:security:role:delete', N'1168723193085583361', N'admin', N'2019-10-08 16:13:04.120', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482629310988289', N'角色管理-条件-新增按钮', N'/mapi/security/role/create', N'mapi:security:role:create', N'1168723193085583361', N'admin', N'2019-10-08 16:13:04.370', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482630594445313', N'角色管理-列表-分页查询', N'/mapi/security/role/pageByCondition', N'mapi:security:role:pageByCondition', N'1168723193085583361', N'admin', N'2019-10-08 16:13:04.676', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482632297332738', N'角色管理-根据ID查询', N'/mapi/security/role/findById', N'mapi:security:role:findById', N'1168723193085583361', N'admin', N'2019-10-08 16:13:05.830', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482633220079617', N'角色管理-列表-禁用启用', N'/mapi/security/role/updateDisabled', N'mapi:security:role:updateDisabled', N'1168723193085583361', N'admin', N'2019-10-08 16:13:05.303', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482634163798018', N'角色管理-列表-权限配置-查询权限树', N'/mapi/security/role/findPermissionTree', N'mapi:security:role:findPermissionTree', N'1168723193085583361', N'admin', N'2019-10-08 16:13:05.526', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482635367563266', N'角色管理-列表-权限配置-资源授权-保存', N'/mapi/security/role/saveRoleWithPermission', N'mapi:security:role:saveRoleWithPermission', N'1168723193085583361', N'admin', N'2019-10-08 16:13:05.813', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482655277924354', N'系统配置-列表-修改', N'/mapi/system/sysdict/update', N'mapi:system:sysdict:update', N'1168723193085583361', N'admin', N'2019-10-08 16:13:10.563', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482656104202242', N'系统配置-列表-删除', N'/mapi/system/sysdict/delete', N'mapi:system:sysdict:delete', N'1168723193085583361', N'admin', N'2019-10-08 16:13:10.760', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482657362493441', N'系统配置-条件-新增字典', N'/mapi/system/sysdict/create', N'mapi:system:sysdict:create', N'1168723193085583361', N'admin', N'2019-10-08 16:13:11.600', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482658192965634', N'系统配置-列表-禁用启用', N'/mapi/system/sysdict/updateDisabled', N'mapi:system:sysdict:updateDisabled', N'1168723193085583361', N'admin', N'2019-10-08 16:13:11.256', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482658813722626', N'系统配置-条件-搜索', N'/mapi/system/sysdict/pageByCondition', N'mapi:system:sysdict:pageByCondition', N'1168723193085583361', N'admin', N'2019-10-08 16:13:11.403', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482659468034050', N'系统配置-通过ID查询', N'/mapi/system/sysdict/findById', N'mapi:system:sysdict:findById', N'1168723193085583361', N'admin', N'2019-10-08 16:13:11.560', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482660340449281', N'选项配置-列表-修改', N'/mapi/system/optdict/update', N'mapi:system:optdict:update', N'1168723193085583361', N'admin', N'2019-10-08 16:13:11.766', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482661493882882', N'选项配置-列表-删除', N'/mapi/system/optdict/delete', N'mapi:system:optdict:delete', N'1168723193085583361', N'admin', N'2019-10-08 16:13:12.430', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482662408241154', N'选项配置-条件-新增', N'/mapi/system/optdict/create', N'mapi:system:optdict:create', N'1168723193085583361', N'admin', N'2019-10-08 16:13:12.260', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482663519731713', N'选项配置-列表-禁用启用', N'/mapi/system/optdict/updateDisabled', N'mapi:system:optdict:updateDisabled', N'1168723193085583361', N'admin', N'2019-10-08 16:13:12.526', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482664517976066', N'选项配置-条件-搜索', N'/mapi/system/optdict/pageByCondition', N'mapi:system:optdict:pageByCondition', N'1168723193085583361', N'admin', N'2019-10-08 16:13:12.763', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'1181482665973399554', N'选项配置-通过ID查询', N'/mapi/system/optdict/findById', N'mapi:system:optdict:findById', N'1168723193085583361', N'admin', N'2019-10-08 16:13:13.113', N'1183662961333968898', N'admin', N'2021-02-19 15:12:23.176', N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'3d0e86d5fcde3e3f1fc72305f717118e', N'新增版本', N'/mapi/version/addVersion', N'mapi:version:addVersion', N'1183662961333968898', N'admin', N'2021-02-23 17:53:11.480', N'1183662961333968898', N'admin', N'2021-02-23 17:59:58.946', N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'7fa4a717f4eab9e5ff9c8b2af4921d38', N'修改福利信息', N'mapi/agentWelfareInfoDetails/updateAgentWelfareInfoDetails', N'mapi:agentWelfareInfoDetails:updateAgentWelfareInfoDetails', N'1183662961333968898', N'admin', N'2021-03-02 11:11:33.416', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'8652213088102babecd877969233ce37', N'删除贺卡模板', N'/mapi/agentGreetingTemplate/delete', N'mapi:agentGreetingTemplate:delete', N'1183662961333968898', N'admin', N'2021-02-23 09:50:45.436', N'1183662961333968898', N'admin', N'2021-02-23 10:35:33.786', N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'88365d1b027781a444531cbb5a9d2a7d', N'查询贺卡模板', N'/mapi/agentGreetingTemplate/findPageByCondition', N'mapi:agentGreetingTemplate:findPageByCondition', N'1183662961333968898', N'admin', N'2021-02-23 09:51:22.263', N'1183662961333968898', N'admin', N'2021-02-23 10:34:34.676', N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'8b33f425bec7c735123ccf7ad940d543', N'查询app版本', N'/mapi/version/findVersionAppDescription', N'mapi:version:findVersionAppDescription', N'1183662961333968898', N'admin', N'2021-02-23 17:52:42.106', N'1183662961333968898', N'admin', N'2021-02-23 18:00:07.826', N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'a1a626d9e987933be645c67d20cbf02f', N'禁用启用版本', N'/mapi/version/updateVersion', N'mapi:version:updateVersion', N'1183662961333968898', N'admin', N'2021-02-23 17:53:49.836', N'1183662961333968898', N'admin', N'2021-02-23 17:59:50.876', N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'a9ff5a2229c990b263491c84729375c5', N'添加贺卡模板', N'/mapi/agentGreetingTemplate/add', N'mapi:agentGreetingTemplate:add', N'1183662961333968898', N'admin', N'2021-02-23 09:48:53.243', N'1183662961333968898', N'admin', N'2021-02-23 10:35:45.763', N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'ce2e62e1994a8ef541dc803062e6501c', N'新增福利信息', N'/mapi/agentWelfareInfoDetails/addAgentWelfareInfoDetails', N'mapi:agentWelfareInfoDetails:addAgentWelfareInfoDetails', N'1183662961333968898', N'admin', N'2021-03-02 11:10:20.410', NULL, NULL, NULL, N'api', N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_RESOURCE] VALUES (N'fc8aef6f1429b71207acde4037c515b5', N'更新贺卡模板', N'/mapi/agentGreetingTemplate/update', N'mapi:agentGreetingTemplate:update', N'1183662961333968898', N'admin', N'2021-02-23 09:51:54.306', N'1183662961333968898', N'admin', N'2021-02-23 10:34:26.210', N'api', N'pc', N'0')
GO

COMMIT
GO


-- ----------------------------
-- Indexes structure for table SEC_RESOURCE
-- ----------------------------
CREATE NONCLUSTERED INDEX [IDX_SEC_RESOURCE_ORG_TYPE]
ON [dbo].[SEC_RESOURCE] (
  [APP_TYPE] ASC
)
GO


-- ----------------------------
-- Uniques structure for table SEC_RESOURCE
-- ----------------------------
ALTER TABLE [dbo].[SEC_RESOURCE] ADD CONSTRAINT [UQ__SEC_RESO__0A34891F74DFF428] UNIQUE NONCLUSTERED ([RESOURCE_STRING] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

ALTER TABLE [dbo].[SEC_RESOURCE] ADD CONSTRAINT [UQ__SEC_RESO__533E1957D4AEADFE] UNIQUE NONCLUSTERED ([PERMISSION_STRING] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Primary Key structure for table SEC_RESOURCE
-- ----------------------------
ALTER TABLE [dbo].[SEC_RESOURCE] ADD CONSTRAINT [PK__SEC_RESO__3214EC279D6E9A8B] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

