CREATE TABLE Board (
	board_num	number primary key	NOT NULL,
	board_title	varchar2	NULL,
	board_content	varchar2	NULL,
	board_date	date	NULL,
	board_original_filename	varchar2	NULL,
	board_rename_filename	varchar2	NULL,
	board_ref	number	NULL,
	board_reply_ref	number	NULL,
	board_reply_lev	number	NULL,
	board_reply_seq	number	NULL,
	board_writer	varchar2	NOT NULL
);

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
    login_ok varchar2(30) default 'N'
    
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

CREATE TABLE Community (
	`com_num`	number	NOT NULL,
	`com_title`	varchar2	NULL,
	`com_content`	varchar2	NULL,
	`com_date`	date	NULL,
	`com_original_file`	varchar2	NULL,
	`com_rename_file`	varchar2	NULL,
	`com_ref`	number	NULL,
	`com_reply_ref`	number	NULL,
	`com_reply_levc`	number	NULL,
	`com_reply_seq`	number	NULL,
	`open_num`	number	NULL,
	`open_title`	varchar2	NULL,
	`open_csv`	varchar2	NULL,
	`open_date`	date	NULL,
	`com_writer`	varchar2	NOT NULL
);

CREATE TABLE `공지사항` (
	`notice_num`	number	NOT NULL,
	`notice_title`	varchar2	NOT NULL,
	`notice_content`	varchar2	NULL,
	`notice_date`	date	NULL,
	`board_writer`	varchar2	NOT NULL
);

CREATE TABLE `질의응답` (
	`qa_num`	number	NOT NULL,
	`qa_title`	varchar2	NULL,
	`qa_content`	varchar2	NULL,
	`qa_date`	date	NULL,
	`qa_ref`	number	NULL,
	`qa_reply_ref`	number	NULL,
	`qa_reply_lev`	number	NULL,
	`qa_reply_seq`	number	NULL,
	`board_writer`	varchar2	NOT NULL
);

CREATE TABLE `신고하기` (
	`dec_num`	number	NOT NULL,
	`dec_content`	varchar2	NULL,
	`dec_why`	varchar2	NOT NULL,
	`dec_id`	varchar2	NOT NULL
);

CREATE TABLE `메세지` (
	`msg_num`	number	NOT NULL,
	`msg_csv`	varchar2	NULL,
	`msg_nick`	varchar2	NOT NULL
);

ALTER TABLE `게시판` ADD CONSTRAINT `PK_게시판` PRIMARY KEY (
	`board_num`
);

ALTER TABLE `사용자` ADD CONSTRAINT `PK_사용자` PRIMARY KEY (
	`USER_ID`
);

ALTER TABLE `커뮤니티` ADD CONSTRAINT `PK_커뮤니티` PRIMARY KEY (
	`com_num`
);

ALTER TABLE `공지사항` ADD CONSTRAINT `PK_공지사항` PRIMARY KEY (
	`notice_num`
);

ALTER TABLE `질의응답` ADD CONSTRAINT `PK_질의응답` PRIMARY KEY (
	`qa_num`
);

ALTER TABLE `신고하기` ADD CONSTRAINT `PK_신고하기` PRIMARY KEY (
	`dec_num`
);

ALTER TABLE `메세지` ADD CONSTRAINT `PK_메세지` PRIMARY KEY (
	`msg_num`
);