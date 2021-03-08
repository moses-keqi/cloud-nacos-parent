-- ----------------------------
-- Table structure for SEC_USER_ROLE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SEC_USER_ROLE]') AND type IN ('U'))
	DROP TABLE [dbo].[SEC_USER_ROLE]
GO

CREATE TABLE [dbo].[SEC_USER_ROLE] (
  [USER_ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [ROLE_ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL
)
GO


-- ----------------------------
-- Records of SEC_USER_ROLE
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[SEC_USER_ROLE] VALUES (N'1183662961333968898', N'1172077184031367170')
GO

INSERT INTO [dbo].[SEC_USER_ROLE] VALUES (N'1183662961333968899', N'98cc0a4469b1139c74e9a397ce860e1d')
GO

COMMIT
GO


-- ----------------------------
-- Indexes structure for table SEC_USER_ROLE
-- ----------------------------
CREATE NONCLUSTERED INDEX [IDX_SEC_USER_ROLE_USER_ID]
ON [dbo].[SEC_USER_ROLE] (
  [USER_ID] ASC
)
GO

CREATE NONCLUSTERED INDEX [IDX_SEC_USER_ROLE_ROLE_ID]
ON [dbo].[SEC_USER_ROLE] (
  [ROLE_ID] ASC
)
GO

