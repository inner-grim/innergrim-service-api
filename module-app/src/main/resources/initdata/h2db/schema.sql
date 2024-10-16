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
    status varchar(20) not null default 'active' comment '상태',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS membership (
    id bigint auto_increment primary key comment 'ID',
    name varchar(255) not null comment '이름',
    status varchar(20) not null default 'active' comment '상태',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS member (
    id bigint auto_increment primary key comment 'ID',
    membership_id bigint not null comment '멤버십',
    social_type varchar(20) not null comment '소셜 type',
    social_id varchar(255) not null unique comment '소셜 ID',
    name varchar(255) null comment '이름',
    email varchar(255) null comment '이메일',
    birth_date varchar(8) null comment '생년월일',
    gender varchar(20) null comment '성별',
    phone_number varchar(20) null comment '전화번호',
    ci varchar(255) null comment 'ci',
    profile_image varchar(255) null comment '프로필 이미지',
    block_yn char not null default 'N' comment '정지 여부',
    status varchar(20) not null default 'active' comment '상태',
    created_at datetime null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) CHARSET=utf8mb4;