drop table if exists `cert_status_enum`;
create table `cert_status_enum` (
	cert_status_enum_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	status	varchar(512)	not null
);insert into	cert_status_enum	(cert_status_enum_id,status)	values	("1","未认证");
insert into	cert_status_enum	(cert_status_enum_id,status)	values	("2","审核中");
insert into	cert_status_enum	(cert_status_enum_id,status)	values	("3","已认证");
insert into	cert_status_enum	(cert_status_enum_id,status)	values	("4","认证失败");
