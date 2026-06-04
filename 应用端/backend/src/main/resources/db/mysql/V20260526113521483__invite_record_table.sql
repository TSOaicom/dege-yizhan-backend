drop table if exists `invite_record`;
create table `invite_record` (
	invite_record_id	INT	 not null auto_increment,
	user_info_user_info_id_1	INT	not null,
	invitee_phone	VARCHAR(64)	not null,
	invite_time	DATETIME	,
	primary key (invite_record_id)
);insert into	invite_record	(user_info_user_info_id_1,invite_record_id,invitee_phone,invite_time)	values	("1","1","13911112222","2024-07-01 10:00:00");
