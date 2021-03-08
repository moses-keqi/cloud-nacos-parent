-- ----------------------------
-- Table structure for SYS_DICT_copy1
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SYS_DICT_copy1]') AND type IN ('U'))
	DROP TABLE [dbo].[SYS_DICT_copy1]
GO

CREATE TABLE [dbo].[SYS_DICT_copy1] (
  [ID] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NOT NULL,
  [CODE] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [NAME_CN] nvarchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [NAME_EN] nvarchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [DICT_VALUE] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [PARENT_ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [NODE_LEVEL] int DEFAULT NULL NULL,
  [SORT_ORDER] int DEFAULT NULL NULL,
  [REMARKS] nvarchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [ENABLED] bit  NOT NULL,
  [SYSTEM_FLAG] int  NOT NULL,
  [CREATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_TIME] datetime DEFAULT NULL NOT NULL,
  [UPDATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_TIME] datetime DEFAULT NULL NULL,
  [DELETED] bit  NOT NULL
)
GO


-- ----------------------------
-- Records of SYS_DICT_copy1
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'1172048919140999170', N'attachment_protect_base_path', N'受保护文件存储基本路径', N'attachment_base_path', N'/data/moses/private/', N'0', N'1', N'0', N'服务器附件存储基本路径大类，请勿随意更改', N'0', N'0', N'admin', N'admin ', N'2019-09-12 15:26:52.596', N'admin', N'admin', N'2021-02-22 11:43:57.873', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'1346759275325571079', N'aes256.enabled', N'AES256URL请求开关', N'aes256', N'false', N'0', N'2', N'1', N'AES256URL请求开关包含APP、H5、PC', N'0', N'1', N'admin', N'admin', N'2021-01-06 18:00:36.000', N'admin', N'admin', N'2021-01-06 18:00:41.000', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'1346759275325571080', N'aes256.app.enabled', N'app加密', N'aes256', N'true', N'1346759275325571079', N'2', N'2', N'AES256URL请求开关包含APP、H5、PC', N'0', N'1', N'admin', N'admin', N'2021-01-06 18:00:36.000', N'admin', N'admin', N'2021-01-06 18:00:41.000', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'1346759275325571081', N'aes256.h5.enabled', N'h5加密', N'aes256', N'false', N'1346759275325571079', N'2', N'3', N'AES256URL请求开关包含APP、H5、PC', N'0', N'1', N'admin', N'admin', N'2021-01-06 18:00:36.000', N'admin', N'admin', N'2021-01-06 18:00:41.000', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'1346759275325571082', N'aes256.pc.enabled', N'pc加密', N'aes256', N'false', N'1346759275325571079', N'2', N'4', N'AES256URL请求开关包含APP、H5、PC', N'0', N'1', N'admin', N'admin', N'2021-01-06 18:00:36.000', N'admin', N'admin', N'2021-01-06 18:00:41.000', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'3e18bb283ec0de8bc5d2d0f8e449b741', N'attachment_protect_types', N'受保护附件类型', NULL, N'attachment_protect_types', N'67f295b013fc94dd264fc67eba2ba7c7', N'1', N'0', NULL, N'0', N'1', N'admin', N'admin', N'2021-02-20 16:11:11.943', N'admin', N'admin', N'2021-02-20 16:14:25.486', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'5aab14aacaecd5ee8fdade798a2e54c4', N'cache.route.enable', N'缓存开关', NULL, N'true', N'0', N'1', N'1', N'缓存开关', N'0', N'1', N'admin', N'admin', N'2021-02-18 16:02:28.493', N'admin', N'admin', N'2021-02-19 14:53:36.283', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'6166f73482618b92b65df2daf4d42065', N'attachment_public_types', N'公开附件类型', NULL, N'attachment_public_types', N'67f295b013fc94dd264fc67eba2ba7c7', N'1', N'0', N'公开路径类型集合', N'0', N'1', N'admin', N'admin', N'2021-02-19 14:21:53.033', N'admin', N'admin', N'2021-02-20 16:10:04.490', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'67f295b013fc94dd264fc67eba2ba7c7', N'attachment_types', N'附件类型', NULL, N'ATTACHMENT_TYPES', N'0', NULL, N'0', NULL, N'0', N'1', N'admin', N'admin', N'2021-02-20 16:19:08.423', NULL, NULL, NULL, N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'a6555cec6e77ccbf9d18b955385af9f0', N'cache.route.date.day', N'缓存时长', NULL, N'7', N'0', N'1', N'0', N'缓存时长', N'0', N'1', N'admin', N'admin', N'2021-02-18 16:03:43.930', N'admin', N'admin', N'2021-02-19 14:53:29.826', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'b81e3706400882f5e6914aa260f83b77', N'cache.route.url', N'缓存路由地址', NULL, N'1', N'0', N'1', N'0', N'缓存路由地址', N'0', N'1', N'admin', N'admin', N'2021-02-18 15:41:58.156', N'admin', N'admin', N'2021-02-19 14:53:51.773', N'0')
GO

INSERT INTO [dbo].[SYS_DICT_copy1] VALUES (N'c1f286647dddb5afff90245470b1cfc5', N'moses.sms.enabled', N'短信开关', NULL, N'false', N'0', N'1', N'0', NULL, N'0', N'0', N'admin', N'admin', N'2021-03-01 14:06:21.203', NULL, NULL, NULL, N'0')
GO

COMMIT
GO


-- ----------------------------
-- Indexes structure for table SYS_DICT_copy1
-- ----------------------------
CREATE NONCLUSTERED INDEX [IDX_SYS_DICT_PARENT_ID_copy1]
ON [dbo].[SYS_DICT_copy1] (
  [PARENT_ID] ASC
)
GO

CREATE NONCLUSTERED INDEX [IDX_SYS_DICT_DISABLED_copy1]
ON [dbo].[SYS_DICT_copy1] (
  [DELETED] ASC
)
GO

CREATE NONCLUSTERED INDEX [IDX_SYS_DICT_DELETED_copy1]
ON [dbo].[SYS_DICT_copy1] (
  [DELETED] ASC
)
GO


-- ----------------------------
-- Uniques structure for table SYS_DICT_copy1
-- ----------------------------
ALTER TABLE [dbo].[SYS_DICT_copy1] ADD CONSTRAINT [UQ__SYS_DICT__AA1D4379F417A781] UNIQUE NONCLUSTERED ([CODE] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Primary Key structure for table SYS_DICT_copy1
-- ----------------------------
ALTER TABLE [dbo].[SYS_DICT_copy1] ADD CONSTRAINT [PK__SYS_DICT__ID_copy1] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

