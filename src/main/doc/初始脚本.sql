---创建用户及赋权
create user taxsoft  password '123456' ;
grant all privileges on database postgres to taxsoft;
---创建表空间(位置需要提前创建)
create tablespace ts_bussdata
   owner postgres
   location 'd:/taxsoft/buss/data';

create tablespace ts_bussindex
   owner postgres
   location 'd:/taxsoft/buss/index';

create tablespace ts_systemdata
   owner postgres
   location 'd:/taxsoft/system/data';

create tablespace ts_systemindex
   owner postgres
   location 'd:/taxsoft/system/index';
---数据表

/*==============================================================*/
/* Table: s_userprod                                            */
/*==============================================================*/
create table s_userprod (
   fid                  numeric(10)          not null,
   fusercode            varchar(50)          null default '',
   fprodcode            varchar(50)          null,
   ftime                timestamp            null
)
tablespace ts_systemdata;

alter table s_userprod
   add constraint pk_s_userprod primary key (fid);

/*==============================================================*/
/* Table: s_news                                                */
/*==============================================================*/
create table s_news (
   fid                  numeric(10)          not null,
   fcatecode            varchar(50)          not null,
   ftitile              varchar(255)         null default '',
   fdesc                varchar(255)         null,
   fpicpath             varchar(255)         null,
   fcontent             text                 null,
   fdatetime            timestamp            null,
   fishot               bool                 null,
   fnoshow              bool                 null,
   ftime                timestamp            null
)
tablespace ts_systemdata;

alter table s_news
   add constraint pk_s_news primary key (fid);

/*==============================================================*/
/* Index: idx_s_news_fcate                                      */
/*==============================================================*/
create  index idx_s_news_fcate on s_news (
fcatecode
)
tablespace ts_systemindex;

/*==============================================================*/
/* Index: idx_s_new_fishot                                      */
/*==============================================================*/
create  index idx_s_new_fishot on s_news (
fishot
)
tablespace ts_systemindex;

/*==============================================================*/
/* Table: s_newsdetail                                          */
/*==============================================================*/
create table s_newsdetail (
   fid                  numeric(10)          not null,
   frow                 numeric(10)          not null,
   fcontent             varchar(255)         null default '',
   fpicproperty         varchar(50)          null,
   fpicname             varchar(255)         null,
   fpicpath             varchar(255)         null,
   fsmallpicproperty    varchar(50)          null,
   fsmallpicname        varchar(255)         null,
   fsmallpicpath        varchar(255)         null,
   fvideoproperty       varchar(50)          null,
   fvideoname           varchar(255)         null,
   fvideopath           varchar(255)         null,
   faudioproperty       varchar(50)          null,
   faudioname           varchar(255)         null,
   faudiopath           varchar(255)         null
)
tablespace ts_systemdata;

comment on column s_newsdetail.fid is
'主表FID';

comment on column s_newsdetail.frow is
'序号，前台布局会根据序号从上到下获取';

comment on column s_newsdetail.fpicproperty is
'后缀';

comment on column s_newsdetail.fpicpath is
'服务器本地存放路径，根目录用参数控制';

comment on column s_newsdetail.fsmallpicproperty is
'后缀';

comment on column s_newsdetail.fvideoproperty is
'后缀';

alter table s_newsdetail
   add constraint pk_s_newsdetail primary key (fid);

/*==============================================================*/
/* Table: s_position                                            */
/*==============================================================*/
create table s_position (
   fid                  numeric(10)          not null,
   fname                varchar(255)         null default '',
   farea                varchar(500)         null,
   fneednum             numeric(10)          null,
   fminpay              numeric(20,2)        null,
   fmaxpay              numeric(20,2)        null,
   fpotsdesc            text                 null,
   fpostreq             text                 null,
   fpublishdate         char(10)             null,
   fenddate             char(10)             null,
   ftime                timestamp            null
)
tablespace ts_systemdata;

comment on table s_position is
'招聘岗位表';

alter table s_position
   add constraint pk_s_position primary key (fid);

/*==============================================================*/
/* Table: s_product                                             */
/*==============================================================*/
create table s_product (
   fid                  numeric(10)          not null,
   fcatecode            varchar(50)          null,
   fname                varchar(255)         null default '',
   fpublishdate         char(10)             null,
   fcontent             text                 null,
   furl                 varchar(50)          null,
   fresourcepath        varchar(255)         null,
   ftime                timestamp            null
)
tablespace ts_systemdata;

comment on table s_product is
'产品表';

alter table s_product
   add constraint pk_s_product primary key (fid);

/*==============================================================*/
/* Table: s_user                                                */
/*==============================================================*/
create table s_user (
   fid                  numeric(10)          not null,
   fcode                varchar(50)          null default '',
   fname                varchar(255)         null default '',
   flogincode           varchar(50)          null default '',
   fpwd                 varchar(255)         null,
   faddress             varchar(255)         null,
   fuserorg             varchar(255)         null,
   fmobilenum           varchar(50)          null,
   ftelnumer            varchar(50)          null,
   femail               varchar(50)          null,
   ftime                timestamp            null
)
tablespace ts_systemdata;

comment on table s_user is
'客户表';

comment on column s_user.fpwd is
'MD5加密';

comment on column s_user.fmobilenum is
'格式验证  0-9以及符合';

comment on column s_user.femail is
'格式验证';

alter table s_user
   add constraint pk_s_user primary key (fid);