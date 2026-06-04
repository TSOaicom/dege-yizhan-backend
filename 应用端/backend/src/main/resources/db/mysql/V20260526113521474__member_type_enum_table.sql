drop table if exists `member_type_enum`;
create table `member_type_enum` (
	member_type_enum_id	INT	 not null auto_increment,
	type_name	varchar(512)	not null,
	primary key (member_type_enum_id)
);insert into	member_type_enum	(type_name,member_type_enum_id)	values	("月卡","1");
insert into	member_type_enum	(type_name,member_type_enum_id)	values	("季卡","2");
insert into	member_type_enum	(type_name,member_type_enum_id)	values	("年卡","3");
