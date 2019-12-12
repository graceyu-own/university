drop table if exists `user`;
create table if not exists `user`(
    id int primary key auto_increment comment '用户ID',
    status tinyint default 1 comment '用户状态\n1: 正常\n2: 锁定\n3: 冻结'
);

drop table if exists `user_login`;
create table if not exists `user_login`(
    id int primary key auto_increment comment '记录ID',
    uid int comment '用户ID',
    identifier varchar(30) comment '用户登录标识',
    credential varchar(30) comment '用户登录凭证'
);

drop table if exists `user_info`;
create table if not exists `user_info`(
    id int primary key auto_increment comment '记录ID',
    uid int comment '用户ID',
    nickname varchar(30) comment '用户名',
    email varchar(30) comment '用户邮箱',
    phone varchar(11) comment '用户手机号',
    age tinyint comment '用户年龄',
    location varchar(40) comment '用户当前位置',
    birthday timestamp comment '用户出生日期'
);

drop table if exists `role`;
create table if not exists `role`(
     id int primary key auto_increment comment '角色ID',
     name varchar(20) unique comment '角色名',
     tag varchar(50) comment '角色记录'
);

insert into role (name, tag) values ('普通用户', 'user');
insert into role (name, tag) values ('管理员', 'admin');

drop table if exists `permission`;
create table if not exists `permission`(
    id int primary key auto_increment comment '权限ID',
    name varchar(20) unique comment '权限名',
    tag varchar(50) comment '权限记录'
);

insert into permission (id, name, tag) VALUES (100, 'admin管理角色', 'admin.manager.role');
insert into permission (id, name, tag) VALUES (101, 'admin管理权限', 'admin.manager.permission');

drop table if exists `user_role`;
create table if not exists `user_role`(
    id int primary key auto_increment comment '记录ID',
    uid int comment '用户ID',
    rid int comment '角色ID'
);

drop table if exists `role_permission`;
create table if not exists `role_permission`(
    id int primary key auto_increment comment '记录ID',
    rid int comment '角色ID',
    pid int comment '权限ID'
);


