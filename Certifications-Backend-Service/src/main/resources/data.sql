insert into Certification(id,name,url,price, version) values (10000,'My certification','cert@ibm.com',200.50, 0);
insert into Certification(id,name,url, price, createdBy, createdDate, lastModifiedBy, lastModifiedDate, version) values (10001,'Java 11','https://oracle.com', 200.50, 'Thats me', sysdate, 'Thats me', sysdate, 0);


insert into certification(id,name,url, price, currency, version) values (10003,'Spring Boot','https://spring.io', 44, 'EUR', 0);
insert into certification(id,name,url, price, version) values (10004,'SAP Admin','https://spa.com', null,0);	
insert into certification(id,name,url, price, version) values (10005,'Oracle 12c Admin','https://oracle.com', 200,0);

--test deleteCertification
insert into certification(id,name,url,price, version) values (10030,'Delete certification','delete@ibm.com',200.50, 0);
insert into skill(id,name,desc) values (20030, 'Test skill','Test description');
insert into certification_skill(certification_id, skill_id) values (10030,20030);
--insert into user(id,name,surname,email) values (10030,'Should','Remain','remain@ibm.com');
insert into voucher(id,state,vouchercode,validUntil, certification_id) values (50030,'NEW','Test voucher',sysdate,10030);

insert into certification(id,name,url, price, version) values (h2seq.nextval,'Spring','https://spring.io', 100.50,0);

--roles
-- INSERT INTO roles(id, name) VALUES(3, 'ROLE_STUDENT');
-- INSERT INTO roles(id, name) VALUES(2, 'ROLE_MANAGER');
-- INSERT INTO roles(id, name) VALUES(1, 'ROLE_ADMIN');


--voucher
insert into voucher(id,state,vouchercode,validUntil,certification_id) values (10020,'NEW','VC1234',sysdate,10001);
insert into voucher(id,state,vouchercode,validUntil,certification_id) values (10021,'NEW','VC1235',sysdate,10003);

--skill
insert into skill(id,name,desc) values (20001, 'Java 11 developer','Java 11 developer');
insert into skill(id,name,desc) values (20002, 'Java test specialist','Java test specialist');
insert into skill(id,name,desc) values (20003, 'SAP administration','SAP administration');
insert into skill(id,name,desc) values (20004, 'Oracle database 12c administrator','Oracle database 12c administrator');

--certification-skill
insert into certification_skill(certification_id, skill_id) values (10003,20001);
insert into certification_skill(certification_id, skill_id) values (10003,20002);
insert into certification_skill(certification_id, skill_id) values (10001,20002);

-- update voucher set user_id=10012 where id = 10020;