
INSERT INTO role (name) values ('NORMAL');
INSERT INTO role (name) values ('VIP');
INSERT INTO role (name) values ('ADMIN');

insert into member (role_id,login_id,password,member_type,social_type, name, email, birth_date, phone_number)
values (1,'00000001','$2a$10$SBsIQOFu9uf9qYe7ANuTmeaOEsVicZhwtDxE5xCBd0REoQVx8G9LO','user','kakao','테스트유저','test@test.com','20000101','01011112222');

insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number)
values (3,'INGM_Admin_00001','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','테스트유저','innergrim_admin@test.com','20000101','01011112222');

--insert into profile (member_id, nick_name, gender, profile_image)
--values (1,'test_nickname','male', null);
