

-- ----------------------------
-- Table structure for SEC_ABOUT_VERSION
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SEC_ABOUT_VERSION]') AND type IN ('U'))
	DROP TABLE [dbo].[SEC_ABOUT_VERSION]
GO

CREATE TABLE [dbo].[SEC_ABOUT_VERSION] (
  [ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_TIME] datetime  NOT NULL,
  [UPDATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_NAME] nvarchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
  [UPDATE_TIME] datetime DEFAULT NULL NULL,
  [DELETED] bit DEFAULT ((0)) NOT NULL,
  [VERSION] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [UPDATE_TYPE] varchar(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [DESCRIPTION] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [CLIENT] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [MD5_SECRET] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [DOWN_URL] varchar(200) COLLATE Chinese_PRC_CI_AS  NULL,
  [PRODUCT_NAME] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [ENABLED] bit DEFAULT ((1)) NULL,
  [ZIP_SECRET] varchar(100) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [ROUTE_CODE] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [ON_LINE] bit  NULL
)
GO


-- ----------------------------
-- Records of SEC_ABOUT_VERSION
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'1356489190035976194', N'SYSTEM', N'SYSTEM', N'2021-02-02 14:27:39.000', N'admin', N'admin', N'2021-02-25 11:33:09.900', N'0', N'1.001', N'2', N'随意更新', N'ios', N'E81AC154C235E17E36FCDABB487232D6', N'http://www.baidu.com', N'测试产品', N'1', N'PSOhNmDTUUtCzk7lVOTqTA==', NULL, N'0')
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'13564891900359761941', N'SYSTEM', N'SYSTEM', N'2021-02-02 14:27:39.000', N'SYSTEM', N'SYSTEM', N'2021-02-02 14:27:47.000', N'0', N'1.001', N'2', N'随意更新android', N'android', N'E81AC154C235E17E36FCDABB487232D6', N'http://www.baidu.com', N'测试产品', N'1', N'PSOhNmDTUUtCzk7lVOTqTA==', NULL, NULL)
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'13564891900359761942', N'SYSTEM', N'SYSTEM', N'2021-02-02 14:27:39.000', N'admin', N'admin', N'2021-02-25 16:56:19.336', N'0', N'1.002', N'2', N'h5随便包', N'h5', N'99CA20ECBA32615AC9026771CE0ECFA7', N'https://huisu.fulan.com.cn/update-h5/1.002/mobile.zip', N'测试产品1', N'0', N'PSOhNmDTUUtCzk7lVOTqTA==', NULL, N'1')
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'1364422899871633410', N'admin', N'admin', N'2021-02-24 11:53:00.416', N'admin', N'admin', N'2021-02-25 15:59:23.253', N'0', N'1.003', N'2', N'h5随便包', N'h5', N'2C4C26D18CA8D185F0FA9F95C6AAA0C6', N'http://huisu.fulan.com.cn/update-h5/1.003/mobile.zip', N'测试产品1', N'0', N'sReEziVlrHJNjK8GZuKuRARSQUKhBNM8SPMGt61ZQrtXpHNznkJJHZ9ovQwAxu+S', N'ewe', N'1')
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'1364846912338956290', N'admin', N'admin', N'2021-02-25 15:59:09.900', N'admin', N'admin', N'2021-02-25 15:59:29.706', N'0', N'1.004', N'2', N'孙祥凯测试', N'h5', N'FAFE52EAC6892C80B951B8D5AFB9B501', N'http://huisu.fulan.com.cn/update-h5/1.004/mobile.zip', N'测试产品1', N'0', N'PSOhNmDTUUtCzk7lVOTqTA==', N'sx', N'1')
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'1364850233145593857', N'admin', N'admin', N'2021-02-25 16:10:59.990', N'admin', N'admin', N'2021-02-25 16:10:59.990', N'0', N'1.005', N'2', N'孙祥凯测试', N'h5', N'66514D9EF0DD4CF3157F7126EC43EE4E', N'http://huisu.fulan.com.cn/update-h5/1.005/mobile.zip', N'测试产品1', N'0', N'PSOhNmDTUUtCzk7lVOTqTA==', N'sx', N'1')
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'1364862733945704449', N'admin', N'admin', N'2021-02-25 17:00:57.736', N'admin', N'admin', N'2021-02-25 20:04:07.370', N'0', N'1.006', N'2', N'孙祥凯测试', N'h5', N'CE266200E9203AD82BD27EBF99FF9BFE', N'http://huisu.fulan.com.cn/update-h5/1.006/mobile.zip', N'测试产品1', N'0', N'PSOhNmDTUUtCzk7lVOTqTA==', N'sx', N'1')
GO

INSERT INTO [dbo].[SEC_ABOUT_VERSION] VALUES (N'1364864635211124737', N'admin', N'admin', N'2021-02-25 17:08:15.096', N'admin', N'admin', N'2021-02-25 20:08:16.406', N'0', N'1.007', N'2', N'孙祥凯测试', N'h5', N'59C18614F15C9111F9CEA7A0D3568B9C', N'http://huisu.fulan.com.cn/update-h5/1.007/mobile.zip', N'测试产品1', N'1', N'PSOhNmDTUUtCzk7lVOTqTA==', N'sx', N'1')
GO

COMMIT
GO


-- ----------------------------
-- Primary Key structure for table SEC_ABOUT_VERSION
-- ----------------------------
ALTER TABLE [dbo].[SEC_ABOUT_VERSION] ADD CONSTRAINT [PK__SEC_ABOU__3214EC27C64A1563] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

