---删除表

alter table s_userprod
   drop constraint pk_s_userprod;

drop table s_userprod;

drop index idx_s_new_fishot;

drop index idx_s_news_fcate;

alter table s_news
   drop constraint pk_s_news;

drop table s_news;

alter table s_newsdetail
   drop constraint pk_s_newsdetail;

drop table s_newsdetail;

alter table s_position
   drop constraint pk_s_position;

drop table s_position;

alter table s_product
   drop constraint pk_s_product;

drop table s_product;

alter table s_user
   drop constraint pk_s_user;

drop table s_user;

---删除表空间

drop tablespace ts_bussdata;

drop tablespace ts_bussindex;

drop tablespace ts_systemdata;

drop tablespace ts_systemindex;

---删除用户
---删除依赖
--revoke all on TABLESPACE ts_bussdata  from taxsoft;
--revoke all on TABLESPACE ts_bussindex  from taxsoft;
--revoke all on TABLESPACE ts_systemdata  from taxsoft;
--revoke all on TABLESPACE ts_systemindex  from taxsoft;
revoke all on DATABASE postgres from taxsoft;
revoke all on schema public from taxsoft;
drop user taxsoft ;


