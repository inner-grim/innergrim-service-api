
INSERT INTO role (name) values ('NORMAL');
INSERT INTO role (name) values ('VIP');
INSERT INTO role (name) values ('ADMIN');

insert into member (role_id,login_id,password,member_type,social_type, name, email, birth_date, phone_number)
values (1,'00000001','','user','kakao','테스트유저','test@test.com','20000101','01011112222');

insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number)
values (1,'innergrim_admin','password','admin','none','테스트유저','innergrim_admin@test.com','20000101','01011112222');

--insert into profile (member_id, nick_name, gender, profile_image)
--values (1,'test_nickname','male', null);
