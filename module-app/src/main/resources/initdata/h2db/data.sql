
INSERT INTO membership (name) values ('NORMAL');
INSERT INTO membership (name) values ('VIP');

insert into member (membership_id, social_type, social_id, name, email, birth_date, phone_number, ci)
values (1,'kakao','00000001','테스트유저','test@test.com','20000101','01011112222','11111');

insert into profile (member_id, nick_name, gender, profile_image)
values (1,'test_nickname','male', null);
