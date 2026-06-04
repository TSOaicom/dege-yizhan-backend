drop table if exists `car_type_enum`;
create table `car_type_enum` (
	car_type_enum_id	INT	 not null auto_increment,
	type_name	varchar(512)	not null,
	primary key (car_type_enum_id)
);insert into	car_type_enum	(type_name,car_type_enum_id)	values	("油车","1");
insert into	car_type_enum	(type_name,car_type_enum_id)	values	("新能源车","2");
