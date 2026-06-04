drop table if exists `feedback`;
create table `feedback` (
	feedback_id	INT	 not null auto_increment,
	user_info_user_info_id_1	INT	not null,
	content	TEXT	not null,
	create_time	DATETIME	,
	primary key (feedback_id)
);insert into	feedback	(user_info_user_info_id_1,create_time,feedback_id,content)	values	("1","2024-07-01 09:00:00","1","建议增加夜间模式");
