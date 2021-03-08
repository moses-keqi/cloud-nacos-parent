
-- ----------------------------
-- Table structure for SEC_DEVICE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[SEC_DEVICE]') AND type IN ('U'))
	DROP TABLE [dbo].[SEC_DEVICE]
GO

CREATE TABLE [dbo].[SEC_DEVICE] (
  [DEVICE_ID] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CLIENT] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [USERNAME] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_NAME] nvarchar(100) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CREATE_TIME] datetime  NOT NULL,
  [UPDATE_BY] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [UPDATE_NAME] nvarchar(100) COLLATE Chinese_PRC_CI_AS  NULL,
  [UPDATE_TIME] datetime  NULL,
  [DELETED] bit DEFAULT ((0)) NOT NULL,
  [ID] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [USER_AGENT] varchar(200) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

-- ----------------------------
-- Indexes structure for table SEC_DEVICE
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [IDX_DEVICE_UN]
ON [dbo].[SEC_DEVICE] (
  [DEVICE_ID] ASC,
  [CLIENT] ASC,
  [USERNAME] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table SEC_DEVICE
-- ----------------------------
ALTER TABLE [dbo].[SEC_DEVICE] ADD CONSTRAINT [PK__SYS_DEVI__3214EC278C9A3401] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

