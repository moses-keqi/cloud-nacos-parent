
-- ----------------------------
-- Table structure for SEC_ROLE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SEC_ROLE]') AND type IN ('U'))
	DROP TABLE [dbo].[SEC_ROLE]
GO

CREATE TABLE [dbo].[SEC_ROLE] (
  [ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [NAME] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [ROLE_STRING] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [DISABLED] bit  NOT NULL,
  [CREATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_TIME] datetime  NOT NULL,
  [UPDATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_TIME] datetime DEFAULT NULL NULL,
  [APP_TYPE] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [DELETED] bit  NOT NULL,
  [DESC_] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO


-- ----------------------------
-- Records of SEC_ROLE
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[SEC_ROLE] VALUES (N'1172077184031367170', N'PC超级管理员', N'admin', N'0', N'1168723193085583361', N'admin', N'2019-09-12 17:19:11.473', N'1183662961333968898', N'admin', N'2021-02-22 18:05:16.416', N'pc', N'0', N'PC超级管理员')
GO

INSERT INTO [dbo].[SEC_ROLE] VALUES (N'85aa15f575335415b33c2ef78d2e077c', N'pc随便测试员', N'pc-test', N'0', N'1183662961333968898', N'admin', N'2021-02-19 15:14:36.450', NULL, NULL, NULL, N'pc', N'0', N'pc随便测试员')
GO

INSERT INTO [dbo].[SEC_ROLE] VALUES (N'98cc0a4469b1139c74e9a397ce860e1d', N'PC超级管理员请勿修改', N'pc:admin', N'0', N'1183662961333968898', N'admin', N'2021-02-22 18:03:59.630', N'1183662961333968898', N'admin', N'2021-02-22 18:05:02.953', N'pc', N'0', N'PC超级管理员请勿修改')
GO

COMMIT
GO


-- ----------------------------
-- Indexes structure for table SEC_ROLE
-- ----------------------------
CREATE NONCLUSTERED INDEX [IDX_SEC_ROLE_DISABLED]
ON [dbo].[SEC_ROLE] (
  [DISABLED] ASC
)
GO

CREATE NONCLUSTERED INDEX [IDX_SEC_ROLE_ORG_TYPE]
ON [dbo].[SEC_ROLE] (
  [APP_TYPE] ASC
)
GO


-- ----------------------------
-- Uniques structure for table SEC_ROLE
-- ----------------------------
ALTER TABLE [dbo].[SEC_ROLE] ADD CONSTRAINT [UQ__SEC_ROLE__0D2CDDF3D5B0703D] UNIQUE NONCLUSTERED ([ROLE_STRING] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Primary Key structure for table SEC_ROLE
-- ----------------------------
ALTER TABLE [dbo].[SEC_ROLE] ADD CONSTRAINT [PK__SEC_ROLE__3214EC273EFE1E55] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

