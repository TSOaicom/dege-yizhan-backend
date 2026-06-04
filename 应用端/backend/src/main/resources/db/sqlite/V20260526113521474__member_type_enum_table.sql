drop table if exists `member_type_enum`;
create table `member_type_enum` (
	member_type_enum_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	type_name	varchar(512)	not null
);insert into	member_type_enum	(type_name,member_type_enum_id)	values	("月卡","1");
insert into	member_type_enum	(type_name,member_type_enum_id)	values	("季卡","2");
insert into	member_type_enum	(type_name,member_type_enum_id)	values	("年卡","3");
