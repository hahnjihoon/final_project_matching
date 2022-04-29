
-- User DB Script------------------------------------------------------------------

CREATE TABLE Users (
	userid  	varchar2(100)	primary key,
	userpwd 	varchar2(200)	not NULL,
	username	varchar2(100)	not NULL,
	email   	varchar2(100)	not NULL,
	phone   	number      	not NULL,
	img_name	varchar2(100)	not NULL,
	myeyes	    varchar2(100)	not NULL,
	mynose	    varchar2(100)	not NULL,
	mymouth	    varchar2(100)	not NULL,
	nick    	varchar2(100)	not NULL,
	height  	number	        not NULL,
	gender  	varchar2(100)	not NULL,
	address    	varchar2(100)	not NULL,
	age     	number	        not NULL,
	hobby   	varchar2(100)	not NULL,
	figure  	varchar2(100)	not NULL,
	userjob	        varchar2(100)   NULL,
	img_name2	varchar2(100)	not NULL,
	eyes	    varchar2(100)	not NULL,
	nose	    varchar2(100)	not NULL,
	mouth	    varchar2(100)	not NULL,
	enroll_date	    date default sysdate,
    admin	    varchar2(100)	default 'N',
	block	    varchar2(100)   NULL,
	userlike	    varchar2(100)	NULL,
    lastmodified date default sysdate,
    login_ok varchar2(30) default 'Y'
    
);

drop table users;

insert into Users(userid, userpwd, username, email, phone, img_name, myeyes, mynose, mymouth, nick, height, gender, address, age, hobby, figure, userjob, img_name2, eyes, nose, mouth, enroll_date, admin, block, userlike) 
values('mynameiu', '123456ef', '이지은', 'iuemail@naver.com', '01012348888', 'img1.jpg', '작다', '보통', '크다', '아이유', '165', 'W' , '서울', '30', '노래', '슬림탄탄' , '가수', 'oimg1.jpg', '크다', '보통', '작다', '2022/04/26', 'N', null, null);

insert into Users(userid, userpwd, username, email, phone, img_name, myeyes, mynose, mymouth, nick, height, gender, address, age, hobby, figure, userjob, img_name2, eyes, nose, mouth, enroll_date, admin, block, userlike) 
values('mynameuin', '123456ef', '유인나', 'uinemail@naver.com', '01033338888', 'img3.jpg', '작다', '보통', '크다', '유인나나', '170', 'W' , '서울', '32', '노래', '슬림탄탄' , '배우', 'oimg3.jpg', '크다', '보통', '작다', '2022/04/26', 'N', null, null);

insert into Users(userid, userpwd, username, email, phone, img_name, myeyes, mynose, mymouth, nick, height, gender, address, age, hobby, figure, userjob, img_name2, eyes, nose, mouth, enroll_date, admin, block, userlike) 
values('hahnjihoon', '45678ef', '한지훈', 'hahnmail@naver.com', '01044448888', 'img2.jpg', '크다', '보통', '작다', '후니', '175', 'M' , '서울', '31', '독서', '슬림탄탄' , '개발자', 'oimg2.jpg', '작다', '보통', '크다', '2022/04/26', 'N', null, null);

insert into Users(userid, userpwd, username, email, phone, img_name, myeyes, mynose, mymouth, nick, height, gender, address, age, hobby, figure, userjob, img_name2, eyes, nose, mouth, enroll_date, admin, block, userlike) 
values('lchano', '3846ef', '이찬호', 'lchanq@naver.com', '01022283143', 'img4.jpg', '크다', '크다', '크다', '차노', '181', 'M' , '서울', '30', '독서', '슬림탄탄' , '개발자', 'oimg4.jpg', '크다', '크다', '크다', '2022/04/26', 'N', null, null);

insert into Users(userid, userpwd, username, email, phone, img_name, myeyes, mynose, mymouth, nick, height, gender, address, age, hobby, figure, userjob, img_name2, eyes, nose, mouth, enroll_date, admin, block, userlike) 
values('mynametae', '111111ef', '김태연', 'taemail@naver.com', '01011118888', 'img5.jpg', '크다', '크다', '크다', '태연', '160', 'W' , '서울', '32', '노래', '슬림탄탄' , '배우', 'oimg5.jpg', '크다', '크다', '크다', '2022/04/26', 'N', null, null);

select * from users
where userid='mynameiu' and userpwd='123456ef' and login_ok = 'N';

select A.img_name, A.nick, A.age, A.height, A.address, A.userjob, A.figure, A.hobby
from users A, (select userid, eyes, nose, mouth, gender from users where userid='hahnjihoon') B
where A.userid = (select distinct A.userid from users where A.myeyes = B.eyes and A.mynose = B.nose and A.mymouth = B.mouth and A.gender != B.gender)and rownum<=5;

select A.img_name, A.nick, A.age, A.height, A.address, A.userjob, A.figure, A.hobby
from users A, (select userid, gender from users where userid='hahnjihoon') B
where A.userid = (select distinct A.userid from users where A.gender != B.gender) and rownum<=5;
commit;


-- User DB Script END------------------------------------------------------------------

-- Community DB Script ------------------------------------------------------------------
CREATE TABLE Community (
	com_num	number	primary key,
	com_content	varchar2(100)	not NULL,
	com_date	date default sysdate,
	com_original_file	varchar2(100)	NULL,
	com_rename_file	varchar2(100)	NULL,
	com_ref	number	not NULL,
	com_reply_ref	number	not NULL,
	com_reply_levc	number	not NULL,
	com_reply_seq	number	not NULL,
	open_num	number	not NULL,
	open_title	varchar2(100)	not NULL,
	open_csv	varchar2(100)	not NULL,
	open_date	date default sysdate,
	com_writer	varchar2(100)	NOT NULL,
    com_id varchar2(100) unique,
    constraint fk_com_id foreign key(com_id) references users(userid)
);

CREATE SEQUENCE com_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 10000
NOCYCLE
NOCACHE
ORDER ;



------- Notice DB ----------------------------------------------------------
CREATE TABLE notice (
	notice_num	number	primary key,
	notice_title	varchar2(100)	NOT NULL,
	notice_content	varchar2(100)	NULL,
	notice_date	date default sysdate	NULL,
    notice_id varchar2(100) unique,
    constraint fk_notice_id foreign key(notice_id) references users(userid)
);
drop table users cascade constraints; 
drop table community cascade constraints; 
drop table qa cascade constraints; 
drop table notice cascade constraints; 
drop table message cascade constraints;
drop table declares cascade constraints; 

CREATE SEQUENCE not_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 10000
NOCYCLE
NOCACHE
ORDER ;

insert into notice(notice_id, notice_num, notice_title, notice_content, notice_date) values ('lchano', 1, '타이틀1', '내용1', '2022/04/26');

select nick from users where userid = 'lchano';
----  notice end ---------------------------

----  qa  DB -----------------------------------

CREATE TABLE qa (
	qa_num	number	primary key,
	qa_title	varchar2(100)	NULL,
	qa_content	varchar2(100)	NULL,
	qa_date	date default sysdate	NULL,
	qa_ref	number	NULL,
	qa_reply_ref	number	NULL,
	qa_reply_lev	number	NULL,
	qa_reply_seq	number	NULL,
    qa_id varchar2(100) unique,
    constraint fk_qa_nick foreign key(qa_id) references users(userid)
);

CREATE SEQUENCE qa_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 10000
NOCYCLE
NOCACHE
ORDER ;

---- qa DB END ----------------------------


------ declare db ---------------------------
CREATE TABLE declares (
	dec_num	number	primary key,
	dec_content	varchar2(100)	NULL,
	dec_why	varchar2(100)	NOT NULL,
	dec_id	varchar2(100)	NOT NULL,
    constraint fk_dec_nick foreign key(dec_id) references users(userid)
);

CREATE SEQUENCE dec_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 10000
NOCYCLE
NOCACHE
ORDER ;


----- message Db -----------------------------
CREATE TABLE message (
	msg_num	number	primary key,
	msg_csv	varchar2(100)	NULL,
	msg_nick	varchar2(100)	NOT NULL,
    msg_id varchar2(100) unique,
    constraint fk_msg_nick foreign key(msg_id) references users(userid)
);

CREATE SEQUENCE msg_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 10000
NOCYCLE
NOCACHE
ORDER ;

----- message Db end-----------------------------

select* from users;
select* from community;
select* from declares;
select* from qa;
select* from message;
select* from notice;


