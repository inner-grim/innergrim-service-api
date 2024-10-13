
INSERT INTO membership (name) values ('NORMAL');
INSERT INTO membership (name) values ('VIP');

--INSERT INTO member (name, membership_id) values ('회원1', 1);
--INSERT INTO member (name, membership_id) values ('회원2', 2);

insert into member (membership_id, social_type, social_id, name, email, birth_date, gender, phone_number, ci, profile_image)
values (1,'kakao','00000001','테스트유저','test@test.com','20000101','male','01011112222','11111',null);
