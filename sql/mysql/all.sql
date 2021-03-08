
set names utf8mb4;
set foreign_key_checks = 0;

-- ----------------------------
-- table structure for sec_about_version
-- ----------------------------
drop table if exists `sec_about_version`;
create table `sec_about_version` (
  `id` varchar(50) not null,
  `create_by` varchar(50) not null,
  `create_name` varchar(50) not null,
  `create_time` datetime not null,
  `update_by` varchar(50) default null,
  `update_name` varchar(50) default null,
  `update_time` datetime default null,
  `deleted` tinyint not null,
  `version` varchar(10) default null,
  `update_type` varchar(2) default null,
  `description` varchar(50) default null,
  `client` varchar(50) default null,
  `md5_secret` varchar(50) default null,
  `down_url` varchar(200) default null,
  `product_name` varchar(255) default null,
  `enabled` tinyint default null,
  `zip_secret` varchar(100) not null,
  `route_code` varchar(50) default null,
  `on_line` tinyint default null,
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_about_version
-- ----------------------------
begin;
insert into `sec_about_version` values ('1356489190035976194', 'system', 'system', '2021-02-02 14:27:39', 'admin', 'admin', '2021-02-25 11:33:10', 0, '1.001', '2', '随意更新', 'ios', 'e81ac154c235e17e36fcdabb487232d6', 'http://www.baidu.com', '测试产品', 1, 'psohnmdtuutczk7lvotqta==', null, 0);
insert into `sec_about_version` values ('13564891900359761941', 'system', 'system', '2021-02-02 14:27:39', 'system', 'system', '2021-02-02 14:27:47', 0, '1.001', '2', '随意更新android', 'android', 'e81ac154c235e17e36fcdabb487232d6', 'http://www.baidu.com', '测试产品', 1, 'psohnmdtuutczk7lvotqta==', null, null);
insert into `sec_about_version` values ('13564891900359761942', 'system', 'system', '2021-02-02 14:27:39', 'admin', 'admin', '2021-02-25 16:56:19', 0, '1.002', '2', 'h5随便包', 'h5', '99ca20ecba32615ac9026771ce0ecfa7', 'https://huisu.fulan.com.cn/update-h5/1.002/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', null, 1);
insert into `sec_about_version` values ('1364422899871633410', 'admin', 'admin', '2021-02-24 11:53:00', 'admin', 'admin', '2021-02-25 15:59:23', 0, '1.003', '2', 'h5随便包', 'h5', '2c4c26d18ca8d185f0fa9f95c6aaa0c6', 'http://huisu.fulan.com.cn/update-h5/1.003/mobile.zip', '测试产品1', 0, 'sreezivlrhjnjk8gzukurarsqukhbnm8spmgt61zqrtxphnznkjjhz9ovqwaxu+s', 'ewe', 1);
insert into `sec_about_version` values ('1364846912338956290', 'admin', 'admin', '2021-02-25 15:59:10', 'admin', 'admin', '2021-02-25 15:59:30', 0, '1.004', '2', '孙祥凯测试', 'h5', 'fafe52eac6892c80b951b8d5afb9b501', 'http://huisu.fulan.com.cn/update-h5/1.004/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
insert into `sec_about_version` values ('1364850233145593857', 'admin', 'admin', '2021-02-25 16:11:00', 'admin', 'admin', '2021-02-25 16:11:00', 0, '1.005', '2', '孙祥凯测试', 'h5', '66514d9ef0dd4cf3157f7126ec43ee4e', 'http://huisu.fulan.com.cn/update-h5/1.005/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
insert into `sec_about_version` values ('1364862733945704449', 'admin', 'admin', '2021-02-25 17:00:58', 'admin', 'admin', '2021-02-25 20:04:07', 0, '1.006', '2', '孙祥凯测试', 'h5', 'ce266200e9203ad82bd27ebf99ff9bfe', 'http://huisu.fulan.com.cn/update-h5/1.006/mobile.zip', '测试产品1', 0, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
insert into `sec_about_version` values ('1364864635211124737', 'admin', 'admin', '2021-02-25 17:08:15', 'admin', 'admin', '2021-02-25 20:08:16', 0, '1.007', '2', '孙祥凯测试', 'h5', '59c18614f15c9111f9cea7a0d3568b9c', 'http://huisu.fulan.com.cn/update-h5/1.007/mobile.zip', '测试产品1', 1, 'psohnmdtuutczk7lvotqta==', 'sx', 1);
commit;

-- ----------------------------
-- table structure for sec_device
-- ----------------------------
drop table if exists `sec_device`;
create table `sec_device` (
  `device_id` varchar(255) not null,
  `client` varchar(255) not null,
  `user_name` varchar(50) character set utf8mb4 collate utf8mb4_0900_ai_ci not null,
  `create_by` varchar(50) not null,
  `create_name` varchar(100) not null,
  `create_time` datetime not null,
  `update_by` varchar(50) default null,
  `update_name` varchar(100) default null,
  `update_time` datetime default null,
  `deleted` tinyint not null,
  `id` varchar(50) not null,
  `user_agent` varchar(200) default null,
  primary key (`id`),
  unique key `idx_device_un` (`device_id`,`client`,`user_name`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_device
-- ----------------------------
begin;
commit;

-- ----------------------------
-- table structure for sec_menu
-- ----------------------------
drop table if exists `sec_menu`;
create table `sec_menu` (
  `id` varchar(50) not null,
  `parent_id` varchar(50) not null,
  `name` varchar(255) not null,
  `permission_string` varchar(255) not null,
  `sort_index` int default null,
  `url` varchar(255) default null,
  `icon` varchar(255) default null,
  `disabled` tinyint not null,
  `create_by` varchar(50) not null,
  `create_name` varchar(50) not null,
  `create_time` datetime not null,
  `update_by` varchar(50) default null,
  `update_name` varchar(50) default null,
  `update_time` datetime default null,
  `app_type` varchar(20) not null,
  `deleted` tinyint default null,
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_menu
-- ----------------------------
begin;
insert into `sec_menu` values ('1169127998652178434', '', 'pc菜单', 'pc', 1, '', '', 0, 'admin', 'admin', '2019-09-04 14:00:11', '', '', null, 'pc', null);
insert into `sec_menu` values ('1169128813513170945', '1169526378566025217', '角色管理', 'pc:role:manage', 2, 'role', '', 0, 'admin', 'admin', '2019-09-04 14:03:25', '', '', null, 'pc', null);
insert into `sec_menu` values ('1169128932719484930', '1169526378566025217', '资源管理', 'pc:system:resource', 3, 'code', '', 0, 'admin', 'admin', '2019-09-04 14:03:54', '', '', null, 'pc', null);
insert into `sec_menu` values ('1169129156653375489', '1169526378566025217', '菜单管理', 'pc:system:menu', 4, 'menu', '', 0, 'admin', 'admin', '2019-09-04 14:04:47', '', '', null, 'pc', null);
insert into `sec_menu` values ('1169130216667242497', '1169526378566025217', '选项配置', 'pc:system:dict', 5, 'option', '', 0, 'admin', 'admin', '2019-09-04 14:09:00', '', '', null, 'pc', null);
insert into `sec_menu` values ('1169130316277768194', '1169526378566025217', '系统配置', 'pc:system:sysdict', 6, 'system', '', 0, 'admin', 'admin', '2019-09-04 14:09:23', '', '', null, 'pc', null);
insert into `sec_menu` values ('1169526378566025217', '1169127998652178434', '系统管理', 'pc:system', 11, 'systemconfig', 'systemconfig', 0, 'admin', 'admin', '2019-09-05 16:23:12', '', '', null, 'pc', null);
insert into `sec_menu` values ('573e82a8226f0e57b04cbbdea5bb901d', '1169526378566025217', '版本配置', 'pc:system:edition', 120, 'edition', null, 0, 'admin', 'admin', '2021-02-23 10:07:25', null, null, null, 'pc', 0);
insert into `sec_menu` values ('6264c909e53fe64e519f7fd0bece7d8c', '1169526378566025217', '富文本配置', 'pc:system:editor', 200, 'editor', '', 0, 'admin', 'admin', '2021-03-02 11:07:51', null, null, null, 'pc', 0);
insert into `sec_menu` values ('b962ee9f227600b9d49bf8adc9d57c12', '1169526378566025217', '贺卡配置', 'pc:system:card', 100, 'card', '', 0, 'admin', 'admin', '2021-02-22 17:54:17', null, null, null, 'pc', 0);
commit;

-- ----------------------------
-- table structure for sec_menu_resource
-- ----------------------------
drop table if exists `sec_menu_resource`;
create table `sec_menu_resource` (
  `menu_id` varchar(50) not null,
  `resource_id` varchar(50) not null,
  key `idx_sec_menu_resource_menu_id` (`menu_id`),
  key `idx_sec_menu_resource_resource_id` (`resource_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_menu_resource
-- ----------------------------
begin;
insert into `sec_menu_resource` values ('1169128813513170945', '1181482635367563266');
insert into `sec_menu_resource` values ('1169128813513170945', '1181482634163798018');
insert into `sec_menu_resource` values ('1169128813513170945', '1181482633220079617');
insert into `sec_menu_resource` values ('1169128813513170945', '1181482632297332738');
insert into `sec_menu_resource` values ('1169128813513170945', '1181482630594445313');
insert into `sec_menu_resource` values ('1169128813513170945', '1181482629310988289');
insert into `sec_menu_resource` values ('1169128813513170945', '1181482628245635073');
insert into `sec_menu_resource` values ('1169128813513170945', '1181482626811183106');
insert into `sec_menu_resource` values ('1169128932719484930', '1181482622595907586');
insert into `sec_menu_resource` values ('1169128932719484930', '1181482621769629698');
insert into `sec_menu_resource` values ('1169128932719484930', '1181482620641361922');
insert into `sec_menu_resource` values ('1169128932719484930', '1181482619576008706');
insert into `sec_menu_resource` values ('1169128932719484930', '1181482616983928833');
insert into `sec_menu_resource` values ('1169128932719484930', '1181482615721443330');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482613691400194');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482612080787458');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482610050744322');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482607114731522');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482606099709953');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482605147602945');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482601129459713');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482599938277377');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482597937594370');
insert into `sec_menu_resource` values ('1169129156653375489', '1181482593437106178');
insert into `sec_menu_resource` values ('1169130216667242497', '1181482664517976066');
insert into `sec_menu_resource` values ('1169130216667242497', '1181482663519731713');
insert into `sec_menu_resource` values ('1169130216667242497', '1181482662408241154');
insert into `sec_menu_resource` values ('1169130216667242497', '1181482661493882882');
insert into `sec_menu_resource` values ('1169130216667242497', '1181482660340449281');
insert into `sec_menu_resource` values ('1169130216667242497', '1181482665973399554');
insert into `sec_menu_resource` values ('1169130316277768194', '1181482659468034050');
insert into `sec_menu_resource` values ('1169130316277768194', '1181482658813722626');
insert into `sec_menu_resource` values ('1169130316277768194', '1181482658192965634');
insert into `sec_menu_resource` values ('1169130316277768194', '1181482657362493441');
insert into `sec_menu_resource` values ('1169130316277768194', '1181482656104202242');
insert into `sec_menu_resource` values ('1169130316277768194', '1181482655277924354');
insert into `sec_menu_resource` values ('b962ee9f227600b9d49bf8adc9d57c12', 'fc8aef6f1429b71207acde4037c515b5');
insert into `sec_menu_resource` values ('b962ee9f227600b9d49bf8adc9d57c12', '88365d1b027781a444531cbb5a9d2a7d');
insert into `sec_menu_resource` values ('b962ee9f227600b9d49bf8adc9d57c12', '8652213088102babecd877969233ce37');
insert into `sec_menu_resource` values ('b962ee9f227600b9d49bf8adc9d57c12', 'a9ff5a2229c990b263491c84729375c5');
insert into `sec_menu_resource` values ('6264c909e53fe64e519f7fd0bece7d8c', '7fa4a717f4eab9e5ff9c8b2af4921d38');
insert into `sec_menu_resource` values ('6264c909e53fe64e519f7fd0bece7d8c', '060b3f136a3e58cb8b16b570f173b086');
insert into `sec_menu_resource` values ('6264c909e53fe64e519f7fd0bece7d8c', 'ce2e62e1994a8ef541dc803062e6501c');
insert into `sec_menu_resource` values ('573e82a8226f0e57b04cbbdea5bb901d', 'a1a626d9e987933be645c67d20cbf02f');
insert into `sec_menu_resource` values ('573e82a8226f0e57b04cbbdea5bb901d', '3d0e86d5fcde3e3f1fc72305f717118e');
insert into `sec_menu_resource` values ('573e82a8226f0e57b04cbbdea5bb901d', '8b33f425bec7c735123ccf7ad940d543');
commit;

-- ----------------------------
-- table structure for sec_resource
-- ----------------------------
drop table if exists `sec_resource`;
create table `sec_resource` (
  `id` varchar(50) not null,
  `name` varchar(255) not null,
  `resource_string` varchar(255) not null,
  `permission_string` varchar(255) not null,
  `create_by` varchar(50) not null,
  `create_name` varchar(50) not null,
  `create_time` datetime not null,
  `update_by` varchar(50) default null,
  `update_name` varchar(50) default null,
  `update_time` datetime default null,
  `type` varchar(20) not null,
  `app_type` varchar(20) not null,
  `disabled` tinyint default null,
  primary key (`id`),
  key `idx_sec_resource_org_type` (`app_type`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_resource
-- ----------------------------
begin;
insert into `sec_resource` values ('060b3f136a3e58cb8b16b570f173b086', '查询福利信息', '/mapi/agentwelfareinfodetails/findagentwelfareinfodetails', 'mapi:agentwelfareinfodetails:findagentwelfareinfodetails', '1183662961333968898', 'admin', '2021-03-02 11:11:04', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482593437106178', '菜单管理-修改菜单', '/mapi/security/menu/update', 'mapi:security:menu:update', '1168723193085583361', 'admin', '2019-10-08 16:12:56', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482597937594370', '菜单管理-创建菜单', '/mapi/security/menu/create', 'mapi:security:menu:create', '1168723193085583361', 'admin', '2019-10-08 16:12:57', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482599938277377', '菜单管理-删除菜单', '/mapi/security/menu/deletebyid', 'mapi:security:menu:deletebyid', '1168723193085583361', 'admin', '2019-10-08 16:12:57', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482601129459713', '菜单管理-根据id查询菜单', '/mapi/security/menu/findbyid', 'mapi:security:menu:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:12:58', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482605147602945', '菜单管理-查询权限树', '/mapi/security/menu/findmenu', 'mapi:security:menu:findmenu', '1168723193085583361', 'admin', '2019-10-08 16:12:59', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482606099709953', '菜单管理-删除资源', '/mapi/security/menu/deleteresourcebyid', 'mapi:security:menu:deleteresourcebyid', '1168723193085583361', 'admin', '2019-10-08 16:12:59', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482607114731522', '菜单管理-分页查询资源', '/mapi/security/menu/pageresourcebycondition', 'mapi:security:menu:pageresourcebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:00', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482610050744322', '菜单管理-保存菜单与资源关联', '/mapi/security/menu/savemenuwithresource', 'mapi:security:menu:savemenuwithresource', '1168723193085583361', 'admin', '2019-10-08 16:13:00', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482612080787458', '菜单管理-根据菜单id查询绑定的资源', '/mapi/security/menu/findresourcebymenuid', 'mapi:security:menu:findresourcebymenuid', '1168723193085583361', 'admin', '2019-10-08 16:13:00', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482613691400194', '菜单管理-根据菜单id与资源id删除菜单资源绑定关系', '/mapi/security/menu/deletebindbyresourceidandmenuid', 'mapi:security:menu:deletebindbyresourceidandmenuid', '1168723193085583361', 'admin', '2019-10-08 16:13:01', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482615721443330', '资源管理-列表-修改', '/mapi/security/resource/update', 'mapi:security:resource:update', '1168723193085583361', 'admin', '2019-10-08 16:13:01', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482616983928833', '资源管理-列表-删除', '/mapi/security/resource/delete', 'mapi:security:resource:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:01', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482619576008706', '资源管理-条件-添加', '/mapi/security/resource/create', 'mapi:security:resource:create', '1168723193085583361', 'admin', '2019-10-08 16:13:03', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482620641361922', '资源管理-条件-搜索', '/mapi/security/resource/pagebycondition', 'mapi:security:resource:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:02', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482621769629698', '资源管理-通过id查询', '/mapi/security/resource/findbyid', 'mapi:security:resource:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:03', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482622595907586', '资源管理-条件-启用禁用', '/mapi/security/resource/updatedisabled', 'mapi:security:resource:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:03', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482626811183106', '角色管理-列表-修改按钮', '/mapi/security/role/update', 'mapi:security:role:update', '1168723193085583361', 'admin', '2019-10-08 16:13:04', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482628245635073', '角色管理-列表-删除按钮', '/mapi/security/role/delete', 'mapi:security:role:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:04', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482629310988289', '角色管理-条件-新增按钮', '/mapi/security/role/create', 'mapi:security:role:create', '1168723193085583361', 'admin', '2019-10-08 16:13:04', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482630594445313', '角色管理-列表-分页查询', '/mapi/security/role/pagebycondition', 'mapi:security:role:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:05', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482632297332738', '角色管理-根据id查询', '/mapi/security/role/findbyid', 'mapi:security:role:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:06', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482633220079617', '角色管理-列表-禁用启用', '/mapi/security/role/updatedisabled', 'mapi:security:role:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:05', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482634163798018', '角色管理-列表-权限配置-查询权限树', '/mapi/security/role/findpermissiontree', 'mapi:security:role:findpermissiontree', '1168723193085583361', 'admin', '2019-10-08 16:13:06', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482635367563266', '角色管理-列表-权限配置-资源授权-保存', '/mapi/security/role/saverolewithpermission', 'mapi:security:role:saverolewithpermission', '1168723193085583361', 'admin', '2019-10-08 16:13:06', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482655277924354', '系统配置-列表-修改', '/mapi/system/sysdict/update', 'mapi:system:sysdict:update', '1168723193085583361', 'admin', '2019-10-08 16:13:11', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482656104202242', '系统配置-列表-删除', '/mapi/system/sysdict/delete', 'mapi:system:sysdict:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:11', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482657362493441', '系统配置-条件-新增字典', '/mapi/system/sysdict/create', 'mapi:system:sysdict:create', '1168723193085583361', 'admin', '2019-10-08 16:13:12', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482658192965634', '系统配置-列表-禁用启用', '/mapi/system/sysdict/updatedisabled', 'mapi:system:sysdict:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:11', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482658813722626', '系统配置-条件-搜索', '/mapi/system/sysdict/pagebycondition', 'mapi:system:sysdict:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:11', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482659468034050', '系统配置-通过id查询', '/mapi/system/sysdict/findbyid', 'mapi:system:sysdict:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:12', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482660340449281', '选项配置-列表-修改', '/mapi/system/optdict/update', 'mapi:system:optdict:update', '1168723193085583361', 'admin', '2019-10-08 16:13:12', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482661493882882', '选项配置-列表-删除', '/mapi/system/optdict/delete', 'mapi:system:optdict:delete', '1168723193085583361', 'admin', '2019-10-08 16:13:12', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482662408241154', '选项配置-条件-新增', '/mapi/system/optdict/create', 'mapi:system:optdict:create', '1168723193085583361', 'admin', '2019-10-08 16:13:12', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482663519731713', '选项配置-列表-禁用启用', '/mapi/system/optdict/updatedisabled', 'mapi:system:optdict:updatedisabled', '1168723193085583361', 'admin', '2019-10-08 16:13:13', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482664517976066', '选项配置-条件-搜索', '/mapi/system/optdict/pagebycondition', 'mapi:system:optdict:pagebycondition', '1168723193085583361', 'admin', '2019-10-08 16:13:13', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('1181482665973399554', '选项配置-通过id查询', '/mapi/system/optdict/findbyid', 'mapi:system:optdict:findbyid', '1168723193085583361', 'admin', '2019-10-08 16:13:13', '1183662961333968898', 'admin', '2021-02-19 15:12:23', 'api', 'pc', 0);
insert into `sec_resource` values ('3d0e86d5fcde3e3f1fc72305f717118e', '新增版本', '/mapi/version/addversion', 'mapi:version:addversion', '1183662961333968898', 'admin', '2021-02-23 17:53:11', '1183662961333968898', 'admin', '2021-02-23 17:59:59', 'api', 'pc', 0);
insert into `sec_resource` values ('7fa4a717f4eab9e5ff9c8b2af4921d38', '修改福利信息', 'mapi/agentwelfareinfodetails/updateagentwelfareinfodetails', 'mapi:agentwelfareinfodetails:updateagentwelfareinfodetails', '1183662961333968898', 'admin', '2021-03-02 11:11:33', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('8652213088102babecd877969233ce37', '删除贺卡模板', '/mapi/agentgreetingtemplate/delete', 'mapi:agentgreetingtemplate:delete', '1183662961333968898', 'admin', '2021-02-23 09:50:45', '1183662961333968898', 'admin', '2021-02-23 10:35:34', 'api', 'pc', 0);
insert into `sec_resource` values ('88365d1b027781a444531cbb5a9d2a7d', '查询贺卡模板', '/mapi/agentgreetingtemplate/findpagebycondition', 'mapi:agentgreetingtemplate:findpagebycondition', '1183662961333968898', 'admin', '2021-02-23 09:51:22', '1183662961333968898', 'admin', '2021-02-23 10:34:35', 'api', 'pc', 0);
insert into `sec_resource` values ('8b33f425bec7c735123ccf7ad940d543', '查询app版本', '/mapi/version/findversionappdescription', 'mapi:version:findversionappdescription', '1183662961333968898', 'admin', '2021-02-23 17:52:42', '1183662961333968898', 'admin', '2021-02-23 18:00:08', 'api', 'pc', 0);
insert into `sec_resource` values ('a1a626d9e987933be645c67d20cbf02f', '禁用启用版本', '/mapi/version/updateversion', 'mapi:version:updateversion', '1183662961333968898', 'admin', '2021-02-23 17:53:50', '1183662961333968898', 'admin', '2021-02-23 17:59:51', 'api', 'pc', 0);
insert into `sec_resource` values ('a9ff5a2229c990b263491c84729375c5', '添加贺卡模板', '/mapi/agentgreetingtemplate/add', 'mapi:agentgreetingtemplate:add', '1183662961333968898', 'admin', '2021-02-23 09:48:53', '1183662961333968898', 'admin', '2021-02-23 10:35:46', 'api', 'pc', 0);
insert into `sec_resource` values ('ce2e62e1994a8ef541dc803062e6501c', '新增福利信息', '/mapi/agentwelfareinfodetails/addagentwelfareinfodetails', 'mapi:agentwelfareinfodetails:addagentwelfareinfodetails', '1183662961333968898', 'admin', '2021-03-02 11:10:20', null, null, null, 'api', 'pc', 0);
insert into `sec_resource` values ('fc8aef6f1429b71207acde4037c515b5', '更新贺卡模板', '/mapi/agentgreetingtemplate/update', 'mapi:agentgreetingtemplate:update', '1183662961333968898', 'admin', '2021-02-23 09:51:54', '1183662961333968898', 'admin', '2021-02-23 10:34:26', 'api', 'pc', 0);
commit;

-- ----------------------------
-- table structure for sec_role
-- ----------------------------
drop table if exists `sec_role`;
create table `sec_role` (
  `id` varchar(50) not null,
  `name` varchar(255) not null,
  `role_string` varchar(255) not null,
  `disabled` tinyint not null,
  `create_by` varchar(50) not null,
  `create_name` varchar(50) not null,
  `create_time` datetime not null,
  `update_by` varchar(50) default null,
  `update_name` varchar(50) default null,
  `update_time` datetime default null,
  `app_type` varchar(20) not null,
  `deleted` tinyint not null,
  `desc_` varchar(255) default null,
  primary key (`id`),
  key `idx_sec_role_disabled` (`disabled`),
  key `idx_sec_role_org_type` (`app_type`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_role
-- ----------------------------
begin;
insert into `sec_role` values ('1172077184031367170', 'pc超级管理员', 'admin', 0, '1168723193085583361', 'admin', '2019-09-12 17:19:11', '1183662961333968898', 'admin', '2021-02-22 18:05:16', 'pc', 0, 'pc超级管理员');
insert into `sec_role` values ('85aa15f575335415b33c2ef78d2e077c', 'pc随便测试员', 'pc-test', 0, '1183662961333968898', 'admin', '2021-02-19 15:14:36', null, null, null, 'pc', 0, 'pc随便测试员');
insert into `sec_role` values ('98cc0a4469b1139c74e9a397ce860e1d', 'pc超级管理员请勿修改', 'pc:admin', 0, '1183662961333968898', 'admin', '2021-02-22 18:04:00', '1183662961333968898', 'admin', '2021-02-22 18:05:03', 'pc', 0, 'pc超级管理员请勿修改');
commit;

-- ----------------------------
-- table structure for sec_role_menu
-- ----------------------------
drop table if exists `sec_role_menu`;
create table `sec_role_menu` (
  `role_id` varchar(50) not null,
  `menu_id` varchar(50) not null,
  key `idx_sec_rule_menu_role_id` (`role_id`),
  key `idx_sec_rule_menu_menu_id` (`menu_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_role_menu
-- ----------------------------
begin;
insert into `sec_role_menu` values ('1172077184031367170', '1169127998652178434');
insert into `sec_role_menu` values ('1172077184031367170', '1169128813513170945');
insert into `sec_role_menu` values ('1172077184031367170', '1169128932719484930');
insert into `sec_role_menu` values ('1172077184031367170', '1169129156653375489');
insert into `sec_role_menu` values ('1172077184031367170', '1169130216667242497');
insert into `sec_role_menu` values ('1172077184031367170', '1169130316277768194');
insert into `sec_role_menu` values ('1172077184031367170', '1169526378566025217');
insert into `sec_role_menu` values ('85aa15f575335415b33c2ef78d2e077c', '1169130316277768194');
insert into `sec_role_menu` values ('85aa15f575335415b33c2ef78d2e077c', '1169526378566025217');
insert into `sec_role_menu` values ('85aa15f575335415b33c2ef78d2e077c', '1169128813513170945');
insert into `sec_role_menu` values ('85aa15f575335415b33c2ef78d2e077c', '1169130216667242497');
insert into `sec_role_menu` values ('85aa15f575335415b33c2ef78d2e077c', '1169129156653375489');
insert into `sec_role_menu` values ('85aa15f575335415b33c2ef78d2e077c', '1169128932719484930');
insert into `sec_role_menu` values ('85aa15f575335415b33c2ef78d2e077c', '1169127998652178434');
insert into `sec_role_menu` values ('1172077184031367170', 'b962ee9f227600b9d49bf8adc9d57c12');
insert into `sec_role_menu` values ('1172077184031367170', '573e82a8226f0e57b04cbbdea5bb901d');
insert into `sec_role_menu` values ('1172077184031367170', '6264c909e53fe64e519f7fd0bece7d8c');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', '1169127998652178434');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', '1169128813513170945');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', '1169128932719484930');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', '1169129156653375489');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', '1169130216667242497');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', '1169130316277768194');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', '1169526378566025217');
insert into `sec_role_menu` values ('98cc0a4469b1139c74e9a397ce860e1d', 'b962ee9f227600b9d49bf8adc9d57c12');
commit;

-- ----------------------------
-- table structure for sec_role_resource
-- ----------------------------
drop table if exists `sec_role_resource`;
create table `sec_role_resource` (
  `role_id` varchar(50) not null,
  `resource_id` varchar(50) not null,
  key `idx_sec_role_resource_role_id` (`role_id`),
  key `idx_sec_role_resource_resource_id` (`resource_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_role_resource
-- ----------------------------
begin;
insert into `sec_role_resource` values ('1172077184031367170', '1181482635367563266');
insert into `sec_role_resource` values ('1172077184031367170', '1181482634163798018');
insert into `sec_role_resource` values ('1172077184031367170', '1181482633220079617');
insert into `sec_role_resource` values ('1172077184031367170', '1181482632297332738');
insert into `sec_role_resource` values ('1172077184031367170', '1181482630594445313');
insert into `sec_role_resource` values ('1172077184031367170', '1181482629310988289');
insert into `sec_role_resource` values ('1172077184031367170', '1181482628245635073');
insert into `sec_role_resource` values ('1172077184031367170', '1181482626811183106');
insert into `sec_role_resource` values ('1172077184031367170', '1181482622595907586');
insert into `sec_role_resource` values ('1172077184031367170', '1181482621769629698');
insert into `sec_role_resource` values ('1172077184031367170', '1181482620641361922');
insert into `sec_role_resource` values ('1172077184031367170', '1181482619576008706');
insert into `sec_role_resource` values ('1172077184031367170', '1181482616983928833');
insert into `sec_role_resource` values ('1172077184031367170', '1181482615721443330');
insert into `sec_role_resource` values ('1172077184031367170', '1181482613691400194');
insert into `sec_role_resource` values ('1172077184031367170', '1181482612080787458');
insert into `sec_role_resource` values ('1172077184031367170', '1181482610050744322');
insert into `sec_role_resource` values ('1172077184031367170', '1181482607114731522');
insert into `sec_role_resource` values ('1172077184031367170', '1181482606099709953');
insert into `sec_role_resource` values ('1172077184031367170', '1181482605147602945');
insert into `sec_role_resource` values ('1172077184031367170', '1181482601129459713');
insert into `sec_role_resource` values ('1172077184031367170', '1181482599938277377');
insert into `sec_role_resource` values ('1172077184031367170', '1181482597937594370');
insert into `sec_role_resource` values ('1172077184031367170', '1181482593437106178');
insert into `sec_role_resource` values ('1172077184031367170', '1181482664517976066');
insert into `sec_role_resource` values ('1172077184031367170', '1181482663519731713');
insert into `sec_role_resource` values ('1172077184031367170', '1181482662408241154');
insert into `sec_role_resource` values ('1172077184031367170', '1181482661493882882');
insert into `sec_role_resource` values ('1172077184031367170', '1181482660340449281');
insert into `sec_role_resource` values ('1172077184031367170', '1181482665973399554');
insert into `sec_role_resource` values ('1172077184031367170', '1181482659468034050');
insert into `sec_role_resource` values ('1172077184031367170', '1181482658813722626');
insert into `sec_role_resource` values ('1172077184031367170', '1181482658192965634');
insert into `sec_role_resource` values ('1172077184031367170', '1181482657362493441');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482659468034050');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482658813722626');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482658192965634');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482657362493441');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482656104202242');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482655277924354');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482665973399554');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482660340449281');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482661493882882');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482662408241154');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482663519731713');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482664517976066');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482593437106178');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482597937594370');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482599938277377');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482605147602945');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482601129459713');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482606099709953');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482607114731522');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482610050744322');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482612080787458');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482613691400194');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482616983928833');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482615721443330');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482619576008706');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482620641361922');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482621769629698');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482622595907586');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482626811183106');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482628245635073');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482629310988289');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482630594445313');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482632297332738');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482633220079617');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482634163798018');
insert into `sec_role_resource` values ('85aa15f575335415b33c2ef78d2e077c', '1181482635367563266');
insert into `sec_role_resource` values ('1172077184031367170', '1181482656104202242');
insert into `sec_role_resource` values ('1172077184031367170', '1181482655277924354');
insert into `sec_role_resource` values ('1172077184031367170', '88365d1b027781a444531cbb5a9d2a7d');
insert into `sec_role_resource` values ('1172077184031367170', '8652213088102babecd877969233ce37');
insert into `sec_role_resource` values ('1172077184031367170', 'a9ff5a2229c990b263491c84729375c5');
insert into `sec_role_resource` values ('1172077184031367170', 'a1a626d9e987933be645c67d20cbf02f');
insert into `sec_role_resource` values ('1172077184031367170', '3d0e86d5fcde3e3f1fc72305f717118e');
insert into `sec_role_resource` values ('1172077184031367170', '8b33f425bec7c735123ccf7ad940d543');
insert into `sec_role_resource` values ('1172077184031367170', '7fa4a717f4eab9e5ff9c8b2af4921d38');
insert into `sec_role_resource` values ('1172077184031367170', '060b3f136a3e58cb8b16b570f173b086');
insert into `sec_role_resource` values ('1172077184031367170', 'ce2e62e1994a8ef541dc803062e6501c');
insert into `sec_role_resource` values ('1172077184031367170', 'fc8aef6f1429b71207acde4037c515b5');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482635367563266');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482634163798018');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482633220079617');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482632297332738');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482630594445313');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482629310988289');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482628245635073');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482626811183106');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482622595907586');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482621769629698');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482620641361922');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482619576008706');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482616983928833');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482615721443330');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482613691400194');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482612080787458');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482610050744322');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482607114731522');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482606099709953');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482605147602945');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482601129459713');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482599938277377');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482597937594370');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482593437106178');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482664517976066');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482663519731713');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482662408241154');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482661493882882');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482660340449281');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482665973399554');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482659468034050');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482658813722626');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482658192965634');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482657362493441');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482656104202242');
insert into `sec_role_resource` values ('98cc0a4469b1139c74e9a397ce860e1d', '1181482655277924354');
commit;

-- ----------------------------
-- table structure for sec_user
-- ----------------------------
drop table if exists `sec_user`;
create table `sec_user` (
  `id` varchar(50) not null,
  `org_type` varchar(20) not null,
  `name` varchar(255) default null,
  `username` varchar(255) default null,
  `password` varchar(255) default null,
  `salt` varchar(255) default null,
  `sex` varchar(20) default null,
  `phone_no` varchar(50) default null,
  `email` varchar(255) default null,
  `locked` tinyint default null,
  `locked_time` datetime default null,
  `expired` tinyint default null,
  `expire_time` datetime default null,
  `deleted` tinyint default null,
  `create_by` varchar(50) default null,
  `create_name` varchar(50) default null,
  `create_time` datetime default null,
  `update_by` varchar(50) default null,
  `update_name` varchar(50) default null,
  `update_time` datetime default null,
  `city_code` varchar(50) default null,
  `branch_code` varchar(50) default null,
  `district_code` varchar(50) default null,
  `district_name` varchar(50) default null,
  `mechanism_code` varchar(50) default null,
  `mechanism_name` varchar(50) default null,
  `entry_date` date default null,
  `birthday` date default null,
  `avatar_url` text,
  primary key (`id`),
  key `idx_sec_user_org_type` (`org_type`),
  key `idx_sec_user_locked` (`locked`),
  key `idx_sec_user_expired` (`expired`),
  key `idx_sec_user_deleted` (`deleted`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_user
-- ----------------------------
begin;
insert into `sec_user` values ('1183662961333968898', 'inside', 'admin', 'admin', null, null, null, '13052253110', null, null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, '2019-03-15', null, 'https://pic3.zhimg.com/80/v2-0115da355a5abe2d455855aefc036586_1440w.jpg');
commit;

-- ----------------------------
-- table structure for sec_user_role
-- ----------------------------
drop table if exists `sec_user_role`;
create table `sec_user_role` (
  `user_id` varchar(50) not null,
  `role_id` varchar(50) not null,
  key `idx_sec_user_role_user_id` (`user_id`),
  key `idx_sec_user_role_role_id` (`role_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sec_user_role
-- ----------------------------
begin;
insert into `sec_user_role` values ('1183662961333968898', '1172077184031367170');
insert into `sec_user_role` values ('1183662961333968899', '98cc0a4469b1139c74e9a397ce860e1d');
commit;

-- ----------------------------
-- table structure for sys_attachment
-- ----------------------------
drop table if exists `sys_attachment`;
create table `sys_attachment` (
  `id` varchar(50) not null,
  `object_id` varchar(50) not null,
  `old_name` varchar(255) not null,
  `new_name` varchar(255) not null,
  `type_` varchar(50) not null,
  `file_type` varchar(50) not null,
  `storage_type` int not null,
  `thumbnail_url` varchar(255) default null,
  `path_` varchar(255) default null,
  `url_` varchar(255) default null,
  `position` varchar(50) default null,
  `sort_index` int default null,
  `create_by` varchar(50) not null,
  `create_name` varchar(50) not null,
  `create_time` datetime not null,
  `update_by` varchar(50) default null,
  `update_name` varchar(50) default null,
  `update_time` datetime default null,
  `deleted` tinyint not null,
  `bucket_name` varchar(255) default null,
  key `idx_sys_attachment_id` (`id`),
  key `idx_sys_attachment_object_id` (`object_id`),
  key `idx_sys_attachment_type_` (`type_`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sys_attachment
-- ----------------------------
begin;
commit;

-- ----------------------------
-- table structure for sys_dict
-- ----------------------------
drop table if exists `sys_dict`;
create table `sys_dict` (
  `id` varchar(50) not null,
  `code` varchar(255) not null,
  `name_cn` varchar(255) default null,
  `name_en` varchar(255) default null,
  `dict_value` varchar(255) not null,
  `parent_id` varchar(50) default null,
  `node_level` int default null,
  `sort_order` int default null,
  `remarks` varchar(255) default null,
  `enabled` tinyint not null,
  `system_flag` int not null,
  `create_by` varchar(50) not null,
  `create_name` varchar(50) not null,
  `create_time` datetime not null,
  `update_by` varchar(50) default null,
  `update_name` varchar(50) default null,
  `update_time` datetime default null,
  `deleted` tinyint not null,
  primary key (`id`),
  key `idx_sys_dict_parent_id_copy1` (`parent_id`),
  key `idx_sys_dict_disabled_copy1` (`deleted`),
  key `idx_sys_dict_deleted_copy1` (`deleted`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- ----------------------------
-- records of sys_dict
-- ----------------------------
begin;
insert into `sys_dict` values ('1172048919140999170', 'attachment_protect_base_path', '受保护文件存储基本路径', 'attachment_base_path', '/data/moses/private/', '0', 1, 0, '服务器附件存储基本路径大类，请勿随意更改', 0, 0, 'admin', 'admin ', '2019-09-12 15:26:53', 'admin', 'admin', '2021-02-22 11:43:58', 0);
insert into `sys_dict` values ('1346759275325571079', 'aes256.enabled', 'aes256url请求开关', 'aes256', 'false', '0', 2, 1, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
insert into `sys_dict` values ('1346759275325571080', 'aes256.app.enabled', 'app加密', 'aes256', 'true', '1346759275325571079', 2, 2, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
insert into `sys_dict` values ('1346759275325571081', 'aes256.h5.enabled', 'h5加密', 'aes256', 'false', '1346759275325571079', 2, 3, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
insert into `sys_dict` values ('1346759275325571082', 'aes256.pc.enabled', 'pc加密', 'aes256', 'false', '1346759275325571079', 2, 4, 'aes256url请求开关包含app、h5、pc', 0, 1, 'admin', 'admin', '2021-01-06 18:00:36', 'admin', 'admin', '2021-01-06 18:00:41', 0);
insert into `sys_dict` values ('3e18bb283ec0de8bc5d2d0f8e449b741', 'attachment_protect_types', '受保护附件类型', null, 'attachment_protect_types', '67f295b013fc94dd264fc67eba2ba7c7', 1, 0, null, 0, 1, 'admin', 'admin', '2021-02-20 16:11:12', 'admin', 'admin', '2021-02-20 16:14:25', 0);
insert into `sys_dict` values ('5aab14aacaecd5ee8fdade798a2e54c4', 'cache.route.enable', '缓存开关', null, 'true', '0', 1, 1, '缓存开关', 0, 1, 'admin', 'admin', '2021-02-18 16:02:28', 'admin', 'admin', '2021-02-19 14:53:36', 0);
insert into `sys_dict` values ('6166f73482618b92b65df2daf4d42065', 'attachment_public_types', '公开附件类型', null, 'attachment_public_types', '67f295b013fc94dd264fc67eba2ba7c7', 1, 0, '公开路径类型集合', 0, 1, 'admin', 'admin', '2021-02-19 14:21:53', 'admin', 'admin', '2021-02-20 16:10:04', 0);
insert into `sys_dict` values ('67f295b013fc94dd264fc67eba2ba7c7', 'attachment_types', '附件类型', null, 'attachment_types', '0', null, 0, null, 0, 1, 'admin', 'admin', '2021-02-20 16:19:08', null, null, null, 0);
insert into `sys_dict` values ('a6555cec6e77ccbf9d18b955385af9f0', 'cache.route.date.day', '缓存时长', null, '7', '0', 1, 0, '缓存时长', 0, 1, 'admin', 'admin', '2021-02-18 16:03:44', 'admin', 'admin', '2021-02-19 14:53:30', 0);
insert into `sys_dict` values ('b81e3706400882f5e6914aa260f83b77', 'cache.route.url', '缓存路由地址', null, '1', '0', 1, 0, '缓存路由地址', 0, 1, 'admin', 'admin', '2021-02-18 15:41:58', 'admin', 'admin', '2021-02-19 14:53:52', 0);
insert into `sys_dict` values ('c1f286647dddb5afff90245470b1cfc5', 'moses.sms.enabled', '短信开关', null, 'false', '0', 1, 0, null, 0, 0, 'admin', 'admin', '2021-03-01 14:06:21', null, null, null, 0);
commit;

set foreign_key_checks = 1;
