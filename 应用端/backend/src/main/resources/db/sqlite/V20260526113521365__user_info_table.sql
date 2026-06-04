drop table if exists `user_info`;
create table `user_info` (
	user_info_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	phone_number	VARCHAR(64)	not null,
	city	varchar(512)	not null,
	car_plate	varchar(512)	not null,
	car_type_enum_car_type_enum_id_1	INTEGER	not null,
	avatar_resource_key	varchar(255)		default	'avatar_resource_key',
	real_name	varchar(512)	,
	cert_status_enum_cert_status_enum_id_1	INTEGER	not null,
	active_days	INTEGER	,
	continue_active_days	INTEGER	,
	password	VARCHAR(64)	
);insert into	user_info	(car_plate,cert_status_enum_cert_status_enum_id_1,city,phone_number,real_name,continue_active_days,user_info_id,car_type_enum_car_type_enum_id_1,active_days)	values	("沪A12345","2","上海","13888888888","张三","5","1","1","36");
