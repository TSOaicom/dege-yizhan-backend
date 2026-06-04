show tables;

drop table if exists static_resources;
create table if not exists static_resources
(
    resource_id              INT
    primary key AUTO_INCREMENT,
    resource_name            VARCHAR(255) ,
    resource_path            VARCHAR(500) not null,
    related_table_name       VARCHAR(255),
    related_table_key        INTEGER,
    relate_table_column_name varchar(255)
);

drop table if exists category;
create table if not exists category
(
    id                     INT primary key AUTO_INCREMENT, -- id
    name                   varchar(255) not null,             -- 列别名称
    parent_id              integer     ,             -- 父级类别id
    create_time            DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time            DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    relevance_table        varchar(255),                      -- 表分类
    relevance_table_column varchar(255)                       -- 分类字段
);