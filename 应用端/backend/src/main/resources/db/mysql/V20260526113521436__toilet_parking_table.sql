drop table if exists `toilet_parking`;
create table `toilet_parking` (
	toilet_parking_id	INT	 not null auto_increment,
	name	varchar(512)	not null,
	latitude	FLOAT	not null,
	longitude	FLOAT	not null,
	has_parking	BOOL	not null,
	parking_count	INT	,
	is_open	BOOL	not null,
	primary key (toilet_parking_id)
);insert into	toilet_parking	(has_parking,is_open,toilet_parking_id,latitude,name,parking_count,longitude)	values	("true","true","1","31.2304","人民广场公厕","4","121.4737");
