drop table if exists `message_center`;
create table `message_center` (
	message_center_id	INT	 not null auto_increment,
	title	varchar(512)	not null,
	content	TEXT	not null,
	type_enum_message_type_enum_id_1	INT	not null,
	is_read	BOOL	not null,
	create_time	DATETIME	,
	primary key (message_center_id)
);insert into	message_center	(is_read,message_center_id,create_time,type_enum_message_type_enum_id_1,title,content)	values	(0,1,'2024-07-01 12:00:00',1,'系统维护通知','平台将于明天凌晨2点进行维护。');
