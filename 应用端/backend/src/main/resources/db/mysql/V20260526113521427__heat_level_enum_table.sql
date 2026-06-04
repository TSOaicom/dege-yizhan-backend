drop table if exists `heat_level_enum`;
create table `heat_level_enum` (
	heat_level_enum_id	INT	 not null auto_increment,
	level_name	varchar(512)	not null,
	color	varchar(512)	not null,
	primary key (heat_level_enum_id)
);insert into	heat_level_enum	(level_name,color,heat_level_enum_id)	values	("红","#FF3B30","1");
insert into	heat_level_enum	(level_name,color,heat_level_enum_id)	values	("黄","#FFD600","2");
insert into	heat_level_enum	(level_name,color,heat_level_enum_id)	values	("绿","#34C759","3");
