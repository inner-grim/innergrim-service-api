DROP TABLE IF EXISTS picture_diary;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS member;
--DROP TABLE IF EXISTS admin;
--DROP TABLE IF EXISTS profile;

CREATE TABLE IF NOT EXISTS picture_diary (
    id bigint auto_increment primary key comment 'ID',
    title varchar(255) not null comment '제목',
    emotion_keyword varchar(255) not null comment '감정 키워트',
    image_url varchar(255) not null comment '이미지 URL',
    content text not null comment '일기 내용',
    like_yn char(1) null comment '선호 여부',
    status varchar(20) not null default 'active' comment '상태',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) comment '그림일기' CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS role (
    id bigint auto_increment primary key comment 'ID',
    role_type varchar(30) not null comment '역할 타입',
    status varchar(20) not null default 'active' comment '상태',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) comment '역할' CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS member (
    id bigint auto_increment primary key comment 'ID',
    role_id bigint not null comment '역할',
    login_id varchar(255) not null unique comment '로그인 ID',
    password varchar(255) not null comment '로그인 비밀번호',
    member_type varchar(20) not null comment '멤버 type',
    social_type varchar(20) not null comment '소셜 type',
    name varchar(50) null comment '이름',
    email varchar(255) null comment '이메일',
    birth_date varchar(8) null comment '생년월일',
    phone_number varchar(20) null comment '전화번호',
    ci varchar(255) null comment 'ci',
    nickname varchar(20) null comment '닉네임',
    gender varchar(20) null comment '성별',
    profile_image varchar(255) null comment '프로필 이미지',
    member_status varchar(50) not null comment '멤버 상태',
    status varchar(20) not null default 'active' comment '상태',
    created_at datetime null default current_timestamp comment '생성 시간',
    updated_at datetime null comment '수정 시간'
) comment '멤버' CHARSET=utf8mb4;

--CREATE TABLE IF NOT EXISTS admin (
--    id bigint auto_increment primary key comment 'ID',
--    role_id bigint not null comment '역할',
--    login_id varchar(50) not null comment '로그인 시 아이디',
--    password varchar(255) not null unique comment '로그인 시 비밀번호',
--    name varchar(50) not null comment '이름',
--    email varchar(255) null comment '이메일',
--    birth_date varchar(8) null comment '생년월일',
--    phone_number varchar(20) not null comment '전화번호',
--    status varchar(20) not null default 'active' comment '상태',
--    created_at datetime null default current_timestamp comment '생성 시간',
--    updated_at datetime null comment '수정 시간'
--) comment '관리자' CHARSET=utf8mb4;

--CREATE TABLE IF NOT EXISTS profile (
--    id bigint auto_increment primary key comment 'ID',
--    member_id bigint not null comment '멤버 ID',
--    nick_name varchar(255) not null comment '닉네임',
--    gender varchar(20) null comment '성별',
--    profile_image varchar(255) null comment '프로필 이미지',
--    status varchar(20) not null default 'active' comment '상태',
--    created_at datetime null default current_timestamp comment '생성 시간',
--    updated_at datetime null comment '수정 시간'
--) comment '프로필' CHARSET=utf8mb4;