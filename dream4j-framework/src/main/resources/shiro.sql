create database dream4j;

use dream4j;

create table t_user
(
    id varchar(64) primary key,
    username varchar(64) not null,
    password varchar(64) not null,
    enabled int not null,
    createDate datetime
);

create table t_role
(
    id varchar(64) primary key,
    name varchar(64),
    description varchar(200)
);

create table t_permission
(
    id varchar(64) primary key,
    token varchar(64) not null,
    url varchar(200) not null,
    description varchar(200)
);

create table t_user_role
(
    id varchar(64) primary key,
    role_id varchar(64) not null,
    user_id varchar(64) not null
);

create table t_role_permission
(
    id varchar(64) primary key,
    role_id varchar(64) not null,
    permission_id varchar(64) not null
);

insert into t_user (id, username, password, enabled, createDate)
values ('1', 'admin', 'admin', '1', NOW())
, ('2', 'test', 'test', '1', NOW());

insert into t_role (id, name, description)
values ('1', 'admin', '超级管理员')
, ('2', 'general', '普通人员');

insert into t_permission (id, token, url, description)
values ('1', '123456', '/user/profile', '查看用户信息');

insert into t_user_role (id, role_id, user_id)
values ('1', '1', '1')
, ('2', '2', '2');

insert into t_role_permission (id, role_id, permission_id)
values ('1', '1', '1');


