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
	block	    varchar2(100)	NULL,
	userlike	    varchar2(100)	NULL
);

drop table users;
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

CREATE TABLE `��������` (
	`notice_num`	number	NOT NULL,
	`notice_title`	varchar2	NOT NULL,
	`notice_content`	varchar2	NULL,
	`notice_date`	date	NULL,
	`board_writer`	varchar2	NOT NULL
);

CREATE TABLE `��������` (
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

CREATE TABLE `�Ű��ϱ�` (
	`dec_num`	number	NOT NULL,
	`dec_content`	varchar2	NULL,
	`dec_why`	varchar2	NOT NULL,
	`dec_id`	varchar2	NOT NULL
);

CREATE TABLE `�޼���` (
	`msg_num`	number	NOT NULL,
	`msg_csv`	varchar2	NULL,
	`msg_nick`	varchar2	NOT NULL
);

ALTER TABLE `�Խ���` ADD CONSTRAINT `PK_�Խ���` PRIMARY KEY (
	`board_num`
);

ALTER TABLE `�����` ADD CONSTRAINT `PK_�����` PRIMARY KEY (
	`USER_ID`
);

ALTER TABLE `Ŀ�´�Ƽ` ADD CONSTRAINT `PK_Ŀ�´�Ƽ` PRIMARY KEY (
	`com_num`
);

ALTER TABLE `��������` ADD CONSTRAINT `PK_��������` PRIMARY KEY (
	`notice_num`
);

ALTER TABLE `��������` ADD CONSTRAINT `PK_��������` PRIMARY KEY (
	`qa_num`
);

ALTER TABLE `�Ű��ϱ�` ADD CONSTRAINT `PK_�Ű��ϱ�` PRIMARY KEY (
	`dec_num`
);

ALTER TABLE `�޼���` ADD CONSTRAINT `PK_�޼���` PRIMARY KEY (
	`msg_num`
);