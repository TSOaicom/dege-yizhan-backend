drop table if exists `wallet`;
create table `wallet` (
	wallet_id	INTEGER PRIMARY KEY AUTOINCREMENT,
	user_info_user_info_id_1	INTEGER	not null,
	balance	DECIMAL(18,6)	not null,
	member_type_enum_member_type_enum_id_1	INTEGER	not null,
	member_expire	DATE	
);insert into	wallet	(wallet_id,user_info_user_info_id_1,balance,member_expire,member_type_enum_member_type_enum_id_1)	values	("1","1","100.5","2024-12-31","2");
