drop table if exists `order_suggestion`;
create table `order_suggestion` (
	order_suggestion_id	INT	 not null auto_increment,
	area_name	varchar(512)	not null,
	order_count	INT	not null,
	distance	FLOAT	not null,
	recommend_index	FLOAT	not null,
	suggest_direction	varchar(512)	not null,
	heat_level_enum_heat_level_enum_id_1	INT	not null,
	primary key (order_suggestion_id)
);insert into	order_suggestion	(area_name,order_suggestion_id,distance,order_count,heat_level_enum_heat_level_enum_id_1,suggest_direction,recommend_index)	values	("人民广场","1","1.2","32","1","东南","4.8");
