-- user table
create table  `user`
(
       `id`              BIGINT not null comment '用户编号',
       `nation`          VARCHAR(5) comment '国别代码 86 中国',
       `mobile`          VARCHAR(20) comment '登录手机号',
       `password`        VARCHAR(60) comment '密码',
       `nick_name`       VARCHAR(30) comment '用户昵称',
       `head_image_url`  VARCHAR(100) comment '头像地址',
       `email`           VARCHAR(30) comment '邮箱',
       `sex`             TINYINT comment '性别 0 未知 1 男性 2 女性',
       `real_name`       VARCHAR(30) comment '真实姓名',
       `card_type`       TINYINT comment '证件类型 0 身份证 1 护照 2 军官证 3 港澳通行证 4 台湾通行证 9 其他证件',
       `card_no`         VARCHAR(20) comment '证件编号',
       `area_id`         INT comment '所在地区ID',
       `area`            VARCHAR(30) comment '所在区域',
       `address`         VARCHAR(50) comment '详细地址',
       `remark`          VARCHAR(100) comment '备注说明',
       `tags`            VARCHAR(100) comment '个性标签 如：小清新,时尚达人,帅气',
       `auditted`        TINYINT comment '实名认证 0未认证, 1待认证, 5未通过, 9已认证',
       `usable`          BIT comment '是否可用 0 禁用 1 可用',
       `created_date`    DATETIME comment '注册时间'
);
alter  table `user`
       add constraint `PK_user_id` primary key (`id`);
alter table `user` comment= '用户表';

-- user_role
create table  `user_role`
(
       `id`              BIGINT not null comment '编号',
       `user_id`         BIGINT comment '用户ID',
       `role_id`         BIGINT comment '角色ID',
       `created_date`    DATETIME comment '创建时间',
       `creator`         BIGINT comment '创建人员'
);
alter  table `user_role`
       add constraint `PK_user_role_id` primary key (`id`);
alter table `user_role` comment= '用户角色表';

-- role
create table  `role`
(
       `id`              BIGINT not null comment '编号',
       `name`            VARCHAR(30) comment '角色名称',
       `remark`          VARCHAR(100) comment '备注',
       `created_date`    INT comment '创建时间',
       `creator`         BIGINT comment '创建人员',
       `updated_date`    DATETIME comment '修改时间',
       `updater`         BIGINT comment '修改人员'
);
alter  table `role`
       add constraint `PK_role_id` primary key (`id`);
alter table `role` comment= '岗位角色表';


-- role_menu
create table  `role_menu`
(
       `id`              BIGINT not null comment '编号',
       `role_id`         BIGINT comment '角色Id',
       `menu_id`         BIGINT comment '菜单Id',
       `permissions`     VARCHAR(200) comment '菜单权限 10,12,15',
       `created_date`    DATETIME comment '创建时间',
       `creator`         BIGINT comment '创建人员'
);
alter  table `role_menu`
       add constraint `PK_role_menu_id` primary key (`id`);
alter table `role_menu` comment= '角色菜单-按钮表';


-- menu
create table  `menu`
(
       `id`              BIGINT not null comment '编号',
       `code`            VARCHAR(30) comment '模块码',
       `name`            VARCHAR(30) comment '名称',
       `is_leaf`         BIT default 0 comment '节点类型 0 菜单节点 1 页节点',
       `parent_id`       INT comment '父节点Id',
       `remark`          VARCHAR(100) comment '备注',
       `lft`             INT comment '左编号',
       `rgt`             INT comment '右编号',
       `level`           INT default 1 comment '等级',
       `created_date`    DATETIME comment '创建时间',
       `creator`         BIGINT comment '创建人员',
       `updated_date`    DATETIME comment '修改时间',
       `updater`         BIGINT comment '修改人员'
);
alter  table `menu`
       add constraint `PK_menu_id` primary key (`id`);
alter table `menu` comment= '菜单表';

-- menu_permission
create table  `menu_permission`
(
       `id`              BIGINT not null comment 'ID',
       `menu_id`         BIGINT comment '菜单Id',
       `code`            VARCHAR(50) comment '权限码',
       `name`            VARCHAR(30) comment '权限',
       `url`             VARCHAR(100) comment '链接地址',
       `remark`          VARCHAR(100) comment '备注',
       `creator`         BIGINT comment '创建人员',
       `created_date`    DATETIME comment '创建时间'
);
alter  table `menu_permission`
       add constraint `PK_menu_permission_id` primary key (`id`);
alter table `menu_permission` comment= '菜单-权限表';
