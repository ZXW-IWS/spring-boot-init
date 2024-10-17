DROP TABLE IF EXISTS user;
create table user
(
    id            bigint auto_increment comment '主键id'
        primary key,
    username      varchar(256)                        null comment '用户昵称',
    gender        varchar(16)                             null comment '用户性别 保密 男 女',
    user_password varchar(256)                        null comment '用户密码',
    avatar_url    varchar(512)                        null comment '用户头像的url地址',
    user_profile    varchar(512)                        null comment '用户简介信息',
    email         varchar(256)                        null comment '用户邮箱',
    user_status   tinyint   default 0                 not null comment '用户状态 0-正常',
    user_role     tinyint   default 0                 not null comment '用户权限  0-普通用户  1-管理员',
    create_time   timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint   default 0                 not null comment ' 0-未删除'
);