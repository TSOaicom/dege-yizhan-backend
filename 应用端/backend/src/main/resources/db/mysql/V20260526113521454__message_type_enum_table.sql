drop table if exists `message_type_enum`;
create table `message_type_enum` (
	message_type_enum_id	INT	 not null auto_increment,
	type_name	varchar(512)	not null,
	primary key (message_type_enum_id)
);insert into	message_type_enum	(type_name,message_type_enum_id)	values	("系统公告","1");
insert into	message_type_enum	(type_name,message_type_enum_id)	values	("平台通知","2");
insert into	message_type_enum	(type_name,message_type_enum_id)	values	("活动提醒","3");
