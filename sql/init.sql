USE zoj;

#########用户表
DROP TABLE IF EXISTS user;
create table user
(
    id            bigint auto_increment comment '主键id'
        primary key,
    username      varchar(256)                        null comment '用户昵称',
    gender        varchar(16)                         null comment '用户性别 保密 男 女',
    user_password varchar(256)                        null comment '用户密码',
    avatar_url    varchar(512)                        null comment '用户头像的url地址',
    user_profile  varchar(512)                        null comment '用户简介信息',
    email         varchar(256)                        null comment '用户邮箱',
    user_status   tinyint   default 0                 not null comment '用户状态 0-正常',
    user_role     tinyint   default 0                 not null comment '用户权限  0-普通用户  1-管理员',
    create_time   timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint   default 0                 not null comment ' 0-未删除'
);

######题目表
DROP TABLE IF EXISTS question;
CREATE TABLE question
(
    id bigint auto_increment comment '主键id'
        primary key,
    user_id         BIGINT                        not null comment '创建用户id',
    title           VARCHAR(256)                  null comment '题目标题',
    description     VARCHAR(512)                  null comment '题目描述',
    submit_count    INT         default 0         not null comment '题目提交数',
    accept_count    INT         default 0         not null comment '题目通过数',
    difficult       INT                           null comment '题目难度 0-简单 1-中等 2-困难',
    tags            VARCHAR(512)                          null comment '题目标签(JSON)',
    answer          TEXT                          null comment '题目答案',
    judge_config    TEXT                          null comment '判题配置（JSON),时间空间...',
    judge_case      TEXT                          null comment '判题用例(JSON)',
    create_time   timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint   default 0                 not null comment ' 0-未删除',
    INDEX idx_uid (user_id)
);

####标签表
DROP TABLE IF EXISTS tag;
CREATE TABLE tag
(
    id bigint auto_increment comment '主键id'
        primary key,
    name                    VARCHAR(128)        null comment '标签名',
    create_time   timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint   default 0                 not null comment ' 0-未删除'
);

####题目提交表
DROP TABLE IF EXISTS question_submit;
CREATE TABLE question_submit
(
    id bigint auto_increment comment '主键id'
        primary key,
    user_id         BIGINT                        not null comment '用户id',
    question_id     BIGINT                        not null comment '题目id',
    language        VARCHAR(128)                  not null comment '语言',
    code            TEXT                          not null comment '代码',
    status          INT         default 0         not null comment '状态 0-待判题 1-判题中 2-成功 3-失败',
    judge_info      TEXT                          null comment '判题信息JSON(时间、空间)',
    create_time   timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint   default 0                 not null comment ' 0-未删除',
    INDEX idx_user_id (user_id),
    INDEX idx_question_id (question_id)
);