drop table if exists `feedback`;
create table `feedback` (
	feedback_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	user_info_user_info_id_1	INTEGER	not null,
	content	TEXT	not null,
	create_time	DATETIME	
);insert into	feedback	(user_info_user_info_id_1,create_time,feedback_id,content)	values	("1","2024-07-01 09:00:00.0 00:00:00.0","1","建议增加夜间模式");
