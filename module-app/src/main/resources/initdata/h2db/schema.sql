DROP TABLE IF EXISTS picture_diary;
DROP TABLE IF EXISTS membership;
DROP TABLE IF EXISTS member;

CREATE TABLE IF NOT EXISTS picture_diary (
    id bigint auto_increment primary key comment 'ID',
    create_date date not null comment '생성 일자',
    emotion_keyword text not null comment '감정 키워트',
    image_url varchar(255) not null comment '이미지 URL',
    content text not null comment '일기 내용',
    like_yn char(1) null comment '선호 여부',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS membership (
    id bigint auto_increment primary key comment 'ID',
    name varchar(255) not null comment '이름',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS member (
    id bigint auto_increment primary key comment 'ID',
    name varchar(255) not null comment '이름',
    membership_id bigint not null comment '멤버십',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;