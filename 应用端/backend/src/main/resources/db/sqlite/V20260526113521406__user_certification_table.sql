drop table if exists `user_certification`;
create table `user_certification` (
	user_certification_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	user_info_user_info_id_1	INTEGER	not null,
	driver_license_resource_key	varchar(255)	not null	default	'driver_license_resource_key',
	vehicle_license_resource_key	varchar(255)	not null	default	'vehicle_license_resource_key',
	taxi_license_resource_key	varchar(255)	not null	default	'taxi_license_resource_key',
	status_enum_cert_status_enum_id_1	INTEGER	not null,
	remark	varchar(512)	
);insert into	user_certification	(user_certification_id,user_info_user_info_id_1,status_enum_cert_status_enum_id_1,remark)	values	("1","1","2","");
