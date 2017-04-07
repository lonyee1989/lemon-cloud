-- user
CREATE TABLE  `user`
(
       `id`              BIGINT NOT NULL COMMENT '用户编号',
       `nation`          VARCHAR(5) COMMENT '国别代码 86 中国',
       `mobile`          VARCHAR(20) COMMENT '登录手机号',
       `password`        VARCHAR(40) COMMENT '密码',
       `nick_name`       VARCHAR(30) COMMENT '用户昵称',
       `head_image_url`  VARCHAR(100) COMMENT '头像地址',
       `email`           VARCHAR(30) COMMENT '邮箱',
       `sex`             TINYINT COMMENT '性别 0 未知 1 男性 2 女性',
       `real_name`       VARCHAR(30) COMMENT '真实姓名',
       `card_type`       TINYINT COMMENT '证件类型 0 身份证 1 护照 2 军官证 3 港澳通行证 4 台湾通行证 9 其他证件',
       `card_no`         VARCHAR(20) COMMENT '证件编号',
       `area_id`         INT COMMENT '所在地区ID',
       `area`            VARCHAR(30) COMMENT '所在区域',
       `address`         VARCHAR(50) COMMENT '详细地址',
       `remark`          VARCHAR(100) COMMENT '备注说明',
       `tags`            VARCHAR(100) COMMENT '个性标签 如：小清新,时尚达人,帅气',
       `auditted`        TINYINT COMMENT '实名认证 0未认证, 1待认证, 5未通过, 9已认证',
       `usable`          BIT COMMENT '是否可用 0 禁用 1 可用',
       `created_date`    DATETIME COMMENT '注册时间'
);
ALTER  TABLE `user`
       ADD CONSTRAINT `PK_user_id` PRIMARY KEY (`id`);
ALTER TABLE `user` COMMENT= '用户表';

-- user_oauth
CREATE TABLE  `user_oauth`
(
       `id`              BIGINT NOT NULL COMMENT '编号',
       `user_id`         INT COMMENT '用户ID',
       `platform`        TINYINT COMMENT '第三方平台 0 微信 1 QQ 2 微博 3 csdn ',
       `openid`          VARCHAR(40) COMMENT '第三方ID',
       `access_token`    VARCHAR(40) COMMENT '授权码',
       `expires`         INT COMMENT '过期时长',
       `created_date`    DATETIME COMMENT '绑定时间'
);
ALTER  TABLE `user_oauth`
       ADD CONSTRAINT `PK_user_oauth_id` PRIMARY KEY (`id`);
ALTER TABLE `user_oauth` COMMENT= '第三方平台账号绑定';

-- user_access_record
CREATE TABLE  `user_access_record`
(
       `id`              BIGINT NOT NULL COMMENT '编号',
       `user_id`         BIGINT COMMENT '用户ID',
       `user_token`      VARCHAR(40) COMMENT '授权token',
       `client_ip`       VARCHAR(20) COMMENT '终端IP',
       `client_device`   VARCHAR(20) COMMENT '终端设备',
       `created_date`    DATETIME COMMENT '登录时间'
);
ALTER  TABLE `user_access_record`
       ADD CONSTRAINT `PK_user_access_record_id` PRIMARY KEY (`id`);
ALTER TABLE `user_access_record` COMMENT= '用户登录日志';

-- user_account
CREATE TABLE  `user_account`
(
       `id`              BIGINT NOT NULL COMMENT '编号',
       `user_id`         BIGINT COMMENT '用户ID',
       `account_type`    TINYINT COMMENT '账户类型 1:钱包 2:积分 3:红包 4:白条',
       `balance`         INT DEFAULT 0 COMMENT '账户余额 单位: 分',
       `freeze_balance`  INT DEFAULT 0 COMMENT '冻结金额',
       `used_balance`    INT DEFAULT 0 COMMENT '已消费金额',
       `usable`          BIT COMMENT '账号状态 0 冻结 1 激活',
       `created_date`    DATETIME COMMENT '开通时间'
);
ALTER  TABLE `user_account`
       ADD CONSTRAINT `PK_user_account_id` PRIMARY KEY (`id`);
ALTER TABLE `user_account` COMMENT= '用户账户表';

-- user_trade
CREATE TABLE  `user_trade`
(
       `id`              BIGINT NOT NULL COMMENT '交易流水号',
       `account_id`      BIGINT COMMENT '账户ID',
       `subject`         VARCHAR(64) COMMENT '交易主题',
       `order_type`      VARCHAR(4000) COMMENT '订单类型',
       `order_id`        BIGINT COMMENT '订单编号',
       `trade_type`      VARCHAR(2) COMMENT '交易类型 IN 收入 OT 支出 BK 退款 ET 提现',
       `transaction_id`  VARCHAR(50) COMMENT '支付平台订单号',
       `amount`          INT DEFAULT 0 COMMENT '消费金额 单位:分 大于 0',
       `balance`         INT DEFAULT 0 COMMENT '账户余额',
       `open_id`         VARCHAR(50) COMMENT '用户标识ID 第三方ID',
       `trade_time`      DATETIME COMMENT '交易起始时间',
       `expire_time`     DATETIME COMMENT '交易结束时间',
       `pay_flow`        TINYINT COMMENT '交易流程 -1:超时取消 0:处理中 1:交易成功 2：交易失败',
       `client_ip`       VARCHAR(20) COMMENT '终端IP',
       `client_device`   VARCHAR(5) COMMENT '终端设备类型',
       `remark`          VARCHAR(200) COMMENT '备注信息',
       `creator`         INT COMMENT '创建人员',
       `created_date`    DATETIME COMMENT '创建时间',
       `updater`         INT COMMENT '修改人员',
       `updated_date`    DATETIME COMMENT '修改时间'
);
ALTER  TABLE `user_trade`
       ADD CONSTRAINT `PK_user_trade_id` PRIMARY KEY (`id`);
ALTER TABLE `user_trade` COMMENT= '交易记录表';

-- redpacket_rule
CREATE TABLE  `redpacket_rule`
(
       `id`              BIGINT NOT NULL COMMENT '编号',
       `subject`         VARCHAR(50) COMMENT '主题',
       `start_time`      DATETIME COMMENT '活动开始时间',
       `end_time`        DATETIME COMMENT '活动结束时间',
       `area_range`      VARCHAR(100) COMMENT '领取范围 111101',
       `expire_time`     DATETIME COMMENT '红包过期时间',
       `redpacket_type`  TINYINT DEFAULT 0 COMMENT '红包类型 0 随机红包 1 固定红包（最小==最大）',
       `min_amount`      INT DEFAULT 0 COMMENT '最小红包 100 单位:分',
       `min_amount_num`  INT DEFAULT 0 COMMENT '最小红包份数 500',
       `max_amount`      INT DEFAULT 0 COMMENT '最大红包 10000',
       `max_amount_num`  INT DEFAULT 0 COMMENT '最大红包份数 10',
       `publish_sum`     INT DEFAULT 0 COMMENT '总发放份数 10000',
       `receive_sum`     INT DEFAULT 0 COMMENT '已发放份数',
       `predict_amount`  INT DEFAULT 0 COMMENT '预计花费金额',
       `actual_amount`   INT DEFAULT 0 COMMENT '实际花费金额',
       `day_limit`       INT DEFAULT 0 COMMENT '每日限领数量',
       `user_limit`      INT DEFAULT 0 COMMENT '每人限领数量',
       `user_day_limit`  INT DEFAULT 0 COMMENT '每人每日限领数量',
       `usable`          BIT DEFAULT 0 COMMENT '活动状态 0 关闭 1 开启',
       `remark`          VARCHAR(100) COMMENT '备注说明',
       `creator`         BIGINT COMMENT '创建人员',
       `created_date`    DATETIME COMMENT '创建时间',
       `updater`         BIGINT COMMENT '修改人员',
       `updated_date`    DATETIME COMMENT '修改时间'
);
ALTER  TABLE `redpacket_rule`
       ADD CONSTRAINT `PK_redpacket_rule_id` PRIMARY KEY (`id`);
ALTER TABLE `redpacket_rule` COMMENT= '红包规则表';

-- score_rule
CREATE TABLE  `score_rule`
(
       `id`              BIGINT NOT NULL COMMENT '编号',
       `subject`         VARCHAR(50) COMMENT '主题',
       `score`           INT DEFAULT 0 COMMENT '单次积分',
       `publish_sum`     INT DEFAULT 0 COMMENT '总发放份数',
       `receive_sum`     INT DEFAULT 0 COMMENT '已发放份数',
       `predict_score`   INT DEFAULT 0 COMMENT '预计花费积分',
       `actual_score`    INT DEFAULT 0 COMMENT '实际花费积分',
       `user_limit`      INT DEFAULT 0 COMMENT '每人限领数量',
       `user_day_limit`  INT DEFAULT 0 COMMENT '每人每日限领数量',
       `usable`          BIT DEFAULT 0 COMMENT '活动状态 0 关闭 1 开启',
       `remark`          VARCHAR(100) COMMENT '备注说明',
       `creator`         BIGINT COMMENT '创建人员',
       `created_date`    DATETIME COMMENT '创建时间',
       `updater`         BIGINT COMMENT '修改人员',
       `updated_date`    DATETIME COMMENT '修改时间'
);
ALTER  TABLE `score_rule`
       ADD CONSTRAINT `PK_score_rule_id` PRIMARY KEY (`id`);
ALTER TABLE `score_rule` COMMENT= '积分规则表';

