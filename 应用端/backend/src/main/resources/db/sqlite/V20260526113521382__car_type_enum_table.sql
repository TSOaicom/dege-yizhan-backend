drop table if exists `car_type_enum`;
create table `car_type_enum` (
	car_type_enum_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	type_name	varchar(512)	not null
);insert into	car_type_enum	(type_name,car_type_enum_id)	values	("油车","1");
insert into	car_type_enum	(type_name,car_type_enum_id)	values	("新能源车","2");
