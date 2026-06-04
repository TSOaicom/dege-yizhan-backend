drop table if exists `invite_record`;
create table `invite_record` (
	invite_record_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	user_info_user_info_id_1	INTEGER	not null,
	invitee_phone	VARCHAR(64)	not null,
	invite_time	DATETIME	
);insert into	invite_record	(user_info_user_info_id_1,invite_record_id,invitee_phone,invite_time)	values	("1","1","13911112222","2024-07-01 10:00:00.0 00:00:00.0");
