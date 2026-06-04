drop table if exists `message_center`;
create table `message_center` (
	message_center_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	title	varchar(512)	not null,
	content	TEXT	not null,
	type_enum_message_type_enum_id_1	INTEGER	not null,
	is_read	BOOL	not null,
	create_time	DATETIME	
);insert into	message_center	(is_read,message_center_id,create_time,type_enum_message_type_enum_id_1,title,content)	values	("false","1","2024-07-01 12:00:00.0 00:00:00.0","1","系统维护通知","平台将于明天凌晨2点进行维护。");
