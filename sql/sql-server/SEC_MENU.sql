
-- ----------------------------
-- Table structure for SEC_MENU
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SEC_MENU]') AND type IN ('U'))
	DROP TABLE [dbo].[SEC_MENU]
GO

CREATE TABLE [dbo].[SEC_MENU] (
  [ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [PARENT_ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [NAME] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [PERMISSION_STRING] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [SORT_INDEX] int DEFAULT NULL NULL,
  [URL] nvarchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [ICON] nvarchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [DISABLED] bit  NOT NULL,
  [CREATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_TIME] datetime  NOT NULL,
  [UPDATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_TIME] datetime DEFAULT NULL NULL,
  [APP_TYPE] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [DELETED] bit DEFAULT ((0)) NULL
)
GO


-- ----------------------------
-- Records of SEC_MENU
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'1169127998652178434', N'', N'PC菜单', N'pc', N'1', N'', N'', N'0', N'admin', N'admin', N'2019-09-04 14:00:10.690', N'', N'', NULL, N'pc', NULL)
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'1169128813513170945', N'1169526378566025217', N'角色管理', N'pc:role:manage', N'2', N'role', N'', N'0', N'admin', N'admin', N'2019-09-04 14:03:25.160', N'', N'', NULL, N'pc', NULL)
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'1169128932719484930', N'1169526378566025217', N'资源管理', N'pc:system:resource', N'3', N'code', N'', N'0', N'admin', N'admin', N'2019-09-04 14:03:53.583', N'', N'', NULL, N'pc', NULL)
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'1169129156653375489', N'1169526378566025217', N'菜单管理', N'pc:system:menu', N'4', N'menu', N'', N'0', N'admin', N'admin', N'2019-09-04 14:04:46.973', N'', N'', NULL, N'pc', NULL)
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'1169130216667242497', N'1169526378566025217', N'选项配置', N'pc:system:dict', N'5', N'option', N'', N'0', N'admin', N'admin', N'2019-09-04 14:08:59.693', N'', N'', NULL, N'pc', NULL)
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'1169130316277768194', N'1169526378566025217', N'系统配置', N'pc:system:sysdict', N'6', N'system', N'', N'0', N'admin', N'admin', N'2019-09-04 14:09:23.446', N'', N'', NULL, N'pc', NULL)
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'1169526378566025217', N'1169127998652178434', N'系统管理', N'pc:system', N'11', N'systemConfig', N'systemConfig', N'0', N'admin', N'admin', N'2019-09-05 16:23:11.920', N'', N'', NULL, N'pc', NULL)
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'573e82a8226f0e57b04cbbdea5bb901d', N'1169526378566025217', N'版本配置', N'pc:system:edition', N'120', N'edition', NULL, N'0', N'admin', N'admin', N'2021-02-23 10:07:24.686', NULL, NULL, NULL, N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'6264c909e53fe64e519f7fd0bece7d8c', N'1169526378566025217', N'富文本配置', N'pc:system:editor', N'200', N'editor', N'', N'0', N'admin', N'admin', N'2021-03-02 11:07:50.686', NULL, NULL, NULL, N'pc', N'0')
GO

INSERT INTO [dbo].[SEC_MENU] VALUES (N'b962ee9f227600b9d49bf8adc9d57c12', N'1169526378566025217', N'贺卡配置', N'pc:system:card', N'100', N'card', N'', N'0', N'admin', N'admin', N'2021-02-22 17:54:17.433', NULL, NULL, NULL, N'pc', N'0')
GO

COMMIT
GO


-- ----------------------------
-- Primary Key structure for table SEC_MENU
-- ----------------------------
ALTER TABLE [dbo].[SEC_MENU] ADD CONSTRAINT [PK__SEC_MENU__3214EC2783D79A01] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

