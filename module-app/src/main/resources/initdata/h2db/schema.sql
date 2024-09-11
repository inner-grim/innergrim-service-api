DROP TABLE IF EXISTS tb_sample;
DROP TABLE IF EXISTS tb_user;

CREATE TABLE IF NOT EXISTS tb_sample (
	id bigint auto_increment primary key comment 'ID',
	sample_text varchar(255) not null comment '임시 글자'
) CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS tb_user (
    id bigint auto_increment primary key comment 'ID',
    name varchar(255) not null comment '이름',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=UTF8;