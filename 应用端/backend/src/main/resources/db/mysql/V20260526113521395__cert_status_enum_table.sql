drop table if exists `cert_status_enum`;
create table `cert_status_enum` (
	cert_status_enum_id	INT	 not null auto_increment,
	status	varchar(512)	not null,
	primary key (cert_status_enum_id)
);insert into	cert_status_enum	(cert_status_enum_id,status)	values	("1","未认证");
insert into	cert_status_enum	(cert_status_enum_id,status)	values	("2","审核中");
insert into	cert_status_enum	(cert_status_enum_id,status)	values	("3","已认证");
insert into	cert_status_enum	(cert_status_enum_id,status)	values	("4","认证失败");
