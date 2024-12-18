
INSERT INTO role (name) values ('NORMAL');
INSERT INTO role (name) values ('VIP');
INSERT INTO role (name) values ('ADMIN');

insert into member (role_id, social_type, social_id, name, email, birth_date, phone_number, ci, nick_name, gender, profile_image)
values (1,'kakao','00000001','테스트유저','test@test.com','20000101','01011112222','11111', null, null, null);

insert into admin (role_id, login_id, social_id, name, email, birth_date, phone_number, ci, nick_name, gender, profile_image)
values (3,'innergrim_admin','00000001','테스트유저','test@test.com','20000101','01011112222','11111', null, null, null);



--insert into profile (member_id, nick_name, gender, profile_image)
--values (1,'test_nickname','male', null);
