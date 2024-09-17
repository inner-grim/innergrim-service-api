DROP TABLE IF EXISTS membership;

CREATE TABLE IF NOT EXISTS membership (
    id bigint auto_increment primary key comment 'ID',
    name varchar(255) not null comment '이름',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;

DROP TABLE IF EXISTS member;

CREATE TABLE IF NOT EXISTS member (
    id bigint auto_increment primary key comment 'ID',
    name varchar(255) not null comment '이름',
    membership_id bigint not null comment '멤버십',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;