drop table if exists `kf_system_config`;
create table `kf_system_config` (
 	 `id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `name` 	 varchar(512) not null  ,
	 `chinese_name` 	 varchar(512)   ,
	 `description` 	 varchar(512)   ,
	 `content` 	 TEXT   ,
	 `remark` 	 varchar(512)   ,
	 `type` 	 varchar(512)   
 );


drop table if exists `user_info`;
create table `user_info` (
 	 `user_info_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `phone_number` 	 VARCHAR(64) not null  ,
	 `city` 	 varchar(512) not null  ,
	 `car_plate` 	 varchar(512) not null  ,
	 `car_type_enum_car_type_enum_id_1` 	 INTEGER not null  ,
	 `avatar_resource_key` 	 VARCHAR(255)   ,
	 `real_name` 	 varchar(512)   ,
	 `cert_status_enum_cert_status_enum_id_1` 	 INTEGER not null  ,
	 `active_days` 	 INTEGER   ,
	 `continue_active_days` 	 INTEGER   ,
	 `password` 	 VARCHAR(64)   
 );
insert into `user_info` (`phone_number`, `city`, `car_plate`, `car_type_enum_car_type_enum_id_1`, `avatar_resource_key`, `real_name`, `cert_status_enum_cert_status_enum_id_1`, `active_days`, `continue_active_days`, `password` ) values ("13888888888","上海","沪A12345","1","0","张三","2","36","5","0" );

drop table if exists `car_type_enum`;
create table `car_type_enum` (
 	 `car_type_enum_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `type_name` 	 varchar(512) not null  
 );
insert into `car_type_enum` (`type_name` ) values ("油车" );
insert into `car_type_enum` (`type_name` ) values ("新能源车" );

drop table if exists `cert_status_enum`;
create table `cert_status_enum` (
 	 `cert_status_enum_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `status` 	 varchar(512) not null  
 );
insert into `cert_status_enum` (`status` ) values ("未认证" );
insert into `cert_status_enum` (`status` ) values ("审核中" );
insert into `cert_status_enum` (`status` ) values ("已认证" );
insert into `cert_status_enum` (`status` ) values ("认证失败" );

drop table if exists `user_certification`;
create table `user_certification` (
 	 `user_certification_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `user_info_user_info_id_1` 	 INTEGER not null  ,
	 `driver_license_resource_key` 	 VARCHAR(255)   ,
	 `vehicle_license_resource_key` 	 VARCHAR(255)   ,
	 `taxi_license_resource_key` 	 VARCHAR(255)   ,
	 `status_enum_cert_status_enum_id_1` 	 INTEGER not null  ,
	 `remark` 	 varchar(512)   
 );
insert into `user_certification` (`user_info_user_info_id_1`, `driver_license_resource_key`, `vehicle_license_resource_key`, `taxi_license_resource_key`, `status_enum_cert_status_enum_id_1`, `remark` ) values ("1","0","0","0","2","" );

drop table if exists `order_suggestion`;
create table `order_suggestion` (
 	 `order_suggestion_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `area_name` 	 varchar(512) not null  ,
	 `order_count` 	 INTEGER not null  ,
	 `distance` 	 FLOAT not null  ,
	 `recommend_index` 	 FLOAT not null  ,
	 `suggest_direction` 	 varchar(512) not null  ,
	 `heat_level_enum_heat_level_enum_id_1` 	 INTEGER not null  
 );
insert into `order_suggestion` (`area_name`, `order_count`, `distance`, `recommend_index`, `suggest_direction`, `heat_level_enum_heat_level_enum_id_1` ) values ("人民广场","32","1.2","4.8","东南","1" );

drop table if exists `heat_level_enum`;
create table `heat_level_enum` (
 	 `heat_level_enum_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `level_name` 	 varchar(512) not null  ,
	 `color` 	 varchar(512) not null  
 );
insert into `heat_level_enum` (`level_name`, `color` ) values ("红","#FF3B30" );
insert into `heat_level_enum` (`level_name`, `color` ) values ("黄","#FFD600" );
insert into `heat_level_enum` (`level_name`, `color` ) values ("绿","#34C759" );

drop table if exists `toilet_parking`;
create table `toilet_parking` (
 	 `toilet_parking_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `name` 	 varchar(512) not null  ,
	 `latitude` 	 FLOAT not null  ,
	 `longitude` 	 FLOAT not null  ,
	 `has_parking` 	 BOOL not null  ,
	 `parking_count` 	 INTEGER   ,
	 `is_open` 	 BOOL not null  
 );
insert into `toilet_parking` (`name`, `latitude`, `longitude`, `has_parking`, `parking_count`, `is_open` ) values ("人民广场公厕","31.2304","121.4737","True","4","True" );

drop table if exists `message_center`;
create table `message_center` (
 	 `message_center_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `title` 	 varchar(512) not null  ,
	 `content` 	 TEXT not null  ,
	 `type_enum_message_type_enum_id_1` 	 INTEGER not null  ,
	 `is_read` 	 BOOL not null  ,
	 `create_time` 	 DATETIME  DEFAULT (CURRENT_TIMESTAMP) 
 );
insert into `message_center` (`title`, `content`, `type_enum_message_type_enum_id_1`, `is_read`, `create_time` ) values ("系统维护通知","平台将于明天凌晨2点进行维护。","1","","2025-01-01 00:00:00" );

drop table if exists `message_type_enum`;
create table `message_type_enum` (
 	 `message_type_enum_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `type_name` 	 varchar(512) not null  
 );
insert into `message_type_enum` (`type_name` ) values ("系统公告" );
insert into `message_type_enum` (`type_name` ) values ("平台通知" );
insert into `message_type_enum` (`type_name` ) values ("活动提醒" );

drop table if exists `wallet`;
create table `wallet` (
 	 `wallet_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `user_info_user_info_id_1` 	 INTEGER not null  ,
	 `balance` 	 DECIMAL(18,6) not null  ,
	 `member_type_enum_member_type_enum_id_1` 	 INTEGER not null  ,
	 `member_expire` 	 DATE   
 );
insert into `wallet` (`user_info_user_info_id_1`, `balance`, `member_type_enum_member_type_enum_id_1`, `member_expire` ) values ("1","100.5","2","2024-12-31" );

drop table if exists `member_type_enum`;
create table `member_type_enum` (
 	 `member_type_enum_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `type_name` 	 varchar(512) not null  
 );
insert into `member_type_enum` (`type_name` ) values ("月卡" );
insert into `member_type_enum` (`type_name` ) values ("季卡" );
insert into `member_type_enum` (`type_name` ) values ("年卡" );

drop table if exists `invite_record`;
create table `invite_record` (
 	 `invite_record_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `user_info_user_info_id_1` 	 INTEGER not null  ,
	 `invitee_phone` 	 VARCHAR(64) not null  ,
	 `invite_time` 	 DATETIME   
 );
insert into `invite_record` (`user_info_user_info_id_1`, `invitee_phone`, `invite_time` ) values ("1","13911112222","2025-01-01 00:00:00" );

drop table if exists `feedback`;
create table `feedback` (
 	 `feedback_id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `user_info_user_info_id_1` 	 INTEGER not null  ,
	 `content` 	 TEXT not null  ,
	 `create_time` 	 DATETIME  DEFAULT (CURRENT_TIMESTAMP) 
 );
insert into `feedback` (`user_info_user_info_id_1`, `content`, `create_time` ) values ("1","建议增加夜间模式","2025-01-01 00:00:00" );

drop table if exists `dynamic_api_setting`;
create table `dynamic_api_setting` (
 	 `id` 	 INTEGER PRIMARY KEY AUTOINCREMENT ,
	 `key_name` 	 VARCHAR(512)   ,
	 `description` 	 VARCHAR(512)   ,
	 `url` 	 VARCHAR(512)   ,
	 `token` 	 VARCHAR(512)   ,
	 `app_id` 	 VARCHAR(512)   ,
	 `api_key` 	 VARCHAR(512)   ,
	 `api_secret` 	 VARCHAR(512)   ,
	 `method` 	 VARCHAR(512)   ,
	 `body_type` 	 VARCHAR(512)   ,
	 `body_template` 	 VARCHAR(512)   ,
	 `header` 	 VARCHAR(512)   ,
	 `auth_type` 	 VARCHAR(512)   ,
	 `protocol` 	 VARCHAR(512)   ,
	 `data_path` 	 VARCHAR(512)   ,
	 `data_type` 	 VARCHAR(512)   
 );
insert into `dynamic_api_setting` (`key_name`, `description`, `url`, `token`, `app_id`, `api_key`, `api_secret`, `method`, `body_type`, `body_template`, `header`, `auth_type`, `protocol`, `data_path`, `data_type` ) values ("word2pic","AI文本生成图片","http://1.94.150.175:8087/api/word2pic","sk-op0c6Onq2Hp2WFbNoo6OAO82OKwwEhaXgfqYKJsvvJo=","","","","post","template",'{"input":"${{text}}"}','{"Authorization": "Bearer ${{token}}", "Content-Type": "application/json"}',"codeflying","http","","" );
insert into `dynamic_api_setting` (`key_name`, `description`, `url`, `token`, `app_id`, `api_key`, `api_secret`, `method`, `body_type`, `body_template`, `header`, `auth_type`, `protocol`, `data_path`, `data_type` ) values ("pic2word","AI图片识别理解","http://1.94.150.175:8087/api/pic2word","sk-op0c6Onq2Hp2WFbNoo6OAO82OKwwEhaXgfqYKJsvvJo=","","","","post","template",'{"input":"请详细描述一下图中有什么？如果图片中是文本，请将文本内容原样输出。","content":"${{content}}"}','{"Authorization": "Bearer ${{token}}", "Content-Type": "application/json"}',"codeflying","http","","" );
insert into `dynamic_api_setting` (`key_name`, `description`, `url`, `token`, `app_id`, `api_key`, `api_secret`, `method`, `body_type`, `body_template`, `header`, `auth_type`, `protocol`, `data_path`, `data_type` ) values ("text2text","AI文本生成文本","http://1.94.150.175:8087/llm/chat-block","sk-op0c6Onq2Hp2WFbNoo6OAO82OKwwEhaXgfqYKJsvvJo=","","","","post","","",'{"Authorization": "Bearer ${{token}}", "Content-Type": "application/json"}',"codeflying","http","","" );
insert into `dynamic_api_setting` (`key_name`, `description`, `url`, `token`, `app_id`, `api_key`, `api_secret`, `method`, `body_type`, `body_template`, `header`, `auth_type`, `protocol`, `data_path`, `data_type` ) values ("tts","文本生成语音","http://1.94.150.175:8087/api/text2speech","sk-op0c6Onq2Hp2WFbNoo6OAO82OKwwEhaXgfqYKJsvvJo=","","","","post","template",'{"content":"${{text}}"}','{"Authorization": "Bearer ${{token}}", "Content-Type": "application/json"}',"codeflying","http","","" );
insert into `dynamic_api_setting` (`key_name`, `description`, `url`, `token`, `app_id`, `api_key`, `api_secret`, `method`, `body_type`, `body_template`, `header`, `auth_type`, `protocol`, `data_path`, `data_type` ) values ("amap_geo","高德-将详细的结构化地址转换为经纬度坐标","http://1.94.150.175:8087/mcp/amap?method=maps_geo","sk-op0c6Onq2Hp2WFbNoo6OAO82OKwwEhaXgfqYKJsvvJo=","","","","post","template",'{"city":"${{city}}","address":"${{address}}"}','{"Authorization": "Bearer ${{token}}", "Content-Type": "application/json"}',"codeflying","http","$.data.content[0].text","json" );
insert into `dynamic_api_setting` (`key_name`, `description`, `url`, `token`, `app_id`, `api_key`, `api_secret`, `method`, `body_type`, `body_template`, `header`, `auth_type`, `protocol`, `data_path`, `data_type` ) values ("amap_regeocode","高德-经纬度坐标转换为行政区划地址信息","http://1.94.150.175:8087/mcp/amap?method=maps_regeocode","sk-op0c6Onq2Hp2WFbNoo6OAO82OKwwEhaXgfqYKJsvvJo=","","","","post","template",'{"location":"${{location}}"}','{"Authorization": "Bearer ${{token}}", "Content-Type": "application/json"}',"codeflying","http","$.data.content[0].text","json" );
insert into `dynamic_api_setting` (`key_name`, `description`, `url`, `token`, `app_id`, `api_key`, `api_secret`, `method`, `body_type`, `body_template`, `header`, `auth_type`, `protocol`, `data_path`, `data_type` ) values ("amap_direction_driving","高德-驾车路径规划","http://1.94.150.175:8087/mcp/amap?method=maps_direction_driving","sk-op0c6Onq2Hp2WFbNoo6OAO82OKwwEhaXgfqYKJsvvJo=","","","","post","template",'{"origin":"${{origin}}","destination":"${{destination}}"}','{"Authorization": "Bearer ${{token}}", "Content-Type": "application/json"}',"codeflying","http","$.data.content[0].text","json" );

drop table if exists `login`;

    create table `login` (
        `login_id`      INTEGER PRIMARY KEY AUTOINCREMENT,
        `relevance_id`  VARCHAR ,
        `password`      VARCHAR ,
        `user_name`     VARCHAR ,
        `relevance_table`       VARCHAR ,
        `phone_number`  VARCHAR ,
        `wx_open_id`    VARCHAR
    );
    
    insert into `login` (`relevance_id`,`password`,`user_name`,`relevance_table`,`phone_number`,`wx_open_id` ) values ("","$2a$10$bOrRjyHCMT2DUnXoVuFWNO1dSd6Zj/fyjS0TETmcD1ZXlR/BGklc2","admin","","admin","" );
    
    insert into `login` (`relevance_id`,`password`,`user_name`,`relevance_table`,`phone_number`,`wx_open_id` ) values ("1","$2a$10$bOrRjyHCMT2DUnXoVuFWNO1dSd6Zj/fyjS0TETmcD1ZXlR/BGklc2","test","userInfo","18852718858","" );
