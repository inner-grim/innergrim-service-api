
INSERT INTO role (name) values ('NORMAL');
INSERT INTO role (name) values ('VIP');
INSERT INTO role (name) values ('ADMIN');

insert into member (role_id,login_id,password,member_type,social_type, name, email, birth_date, phone_number, member_status)
values (1,'00000001','$2a$10$SBsIQOFu9uf9qYe7ANuTmeaOEsVicZhwtDxE5xCBd0REoQVx8G9LO','user','kakao','테스트유저','test1@test.com','20000101','01011112222','normal');
insert into member (role_id,login_id,password,member_type,social_type, name, email, birth_date, phone_number, member_status)
values (1,'00000002','$2a$10$SBsIQOFu9uf9qYe7ANuTmeaOEsVicZhwtDxE5xCBd0REoQVx8G9LO','user','kakao','테스트유저','test2@test.com','20000101','01011112222','normal');

--- ID : INGM_Admin_0000n ~~ 순으로 진행
--    - 7명 필요 / 모두 다 마스터 권한 부여(mvp 기한 내 특별한 권한 제한 X)
--- 비번 : innergrim1234!@
insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number, member_status)
values (3,'INGM_Admin_00001','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','관리자1','innergrim_admin@test.com','20000101','01011112222','normal');
insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number, member_status)
values (3,'INGM_Admin_00002','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','관리자2','innergrim_admin@test.com','20000101','01011112222','normal');
insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number, member_status)
values (3,'INGM_Admin_00003','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','관리자3','innergrim_admin@test.com','20000101','01011112222','normal');
insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number, member_status)
values (3,'INGM_Admin_00004','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','관리자4','innergrim_admin@test.com','20000101','01011112222','normal');
insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number, member_status)
values (3,'INGM_Admin_00005','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','관리자5','innergrim_admin@test.com','20000101','01011112222','normal');
insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number, member_status)
values (3,'INGM_Admin_00006','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','관리자6','innergrim_admin@test.com','20000101','01011112222','normal');
insert into member (role_id,login_id,password,member_type,social_type,name,email,birth_date,phone_number, member_status)
values (3,'INGM_Admin_00007','$2a$10$VOEAo5yKfLjCOMrfsnjn7eN59K2NMBNGUNzKkKBgrWPKHEko9hzvO','admin','none','관리자6','innergrim_admin@test.com','20000101','01011112222','normal');


