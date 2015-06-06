 drop database GameForum;
create database GameForum;
use GameForum;

create table UserGroup(
	name		varchar(30) not null primary key,
	permissions varchar(255)
);
insert into UserGroup values ('admin',null);
insert into UserGroup values ('小白',null);

create table GUser(
	account 	varchar(30) not null primary key,
	name 		varchar(255),
	nickName	varchar(30),
	passwd		varchar(30),
	gender 		Char(1),
	birthday 	date,
	email 		varchar(255),
	groupName	varchar(30),
	points		int(10)  default 0,
	postNum		int(10)  default 0,
	replyNum	int(10)  default 0,
	signature	varchar(255),
	coinNum		int(10)  default 0,
	portraitAddr varchar(255),
	foreign key (groupName) references UserGroup(name)
);
insert into Guser value ('admin','admin','admin','admin','M','1994-05-20','admin@qq.com','admin',0,0,0,'admin',0,null);

create table Item(
	ID 			int(10) not null primary key AUTO_INCREMENT,
	name 		varchar(30),
	price 		int(10),
	remain 		int(10),
	closetime 	datetime
);

create table Requirement(
	ID				int(20) not null primary key AUTO_INCREMENT,
	OS				varchar(255),
	Processor		varchar(255),
	Memory			int(3),
	Graphics		varchar(255),
	HardDrive		varchar(20),
	SoundCard		varchar(255)
);
insert into Requirement value(1,'Windows','I7-3630',8,'GTX660M','120G SSD','SoundCard');

create table Studio(
	ID 			int(10) not null primary key AUTO_INCREMENT,
	name 		varchar(255),
	location 	varchar(50),
	description varchar(255)
);
insert into Studio value (1,'Ubisoft','America','hehe');

create table Game(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	name 			varchar(255),
	studioId 		int(10),
	platform 		varchar(20),
	type 			varchar(30),
	logoAddr		varchar(255),
	releaseDate 	date,
	requirementID 	int(10),
	description 	varchar(255),
	foreign key (requirementID) references Requirement(ID),
	foreign key (studioId) references Studio(ID)
);

insert into Game value (1,'真三国无双7',1,'PC','ACT','picture/sanguo7logo.jpg','2010-5-9',1,'真三国无双7真三国无双7真三国无双7');
insert into Game value (2,'仙剑奇侠传',1,'PC','RPG','picture/xianjianxiqizhuanglogo.jpg','2010-5-10',1,'仙剑奇侠传仙剑奇侠传仙剑奇侠传');
insert into Game value (3,'古墓丽影',1,'PC','AVG','picture/gumuliyinglogo.jpg','2010-5-18',1,'古墓丽影古墓丽影古墓丽影');
insert into Game value (4,'三国志',1,'PC','SLG','picture/sanguozhilogo.jpg','2010-5-18',1,'三国志三国志三国志');

create table Section(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	name			varchar(255),
	PostNum			int(10)  default 0,
	OwnerType		varchar(6),
	OwnerID			varchar(30),
	GameID			int(10),
	foreign key (OwnerID) references GUser(account),
	foreign key (GameID) references Game(ID)
);

create table Post(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	title			varchar(50),
	authorId		varchar(30) ,
	type			varchar(10),
	shareNum		int(10)  default 0,
	favouriteNum	int(10)  default 0,
	postTime		datetime,
	SectionId		int(10),
	Contents		varchar(255),
	foreign key (sectionID) references Section(ID),
	foreign key (authorId) references GUser(account)
);

create table Reply(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	PostID			int(10),
	floor			int(10),
	userId			varchar(30),
	favouriteNum	int(10)  default 0,
	postTime 		datetime,
	contents		varchar(255),
	foreign key (PostID) references Post(ID),
	foreign key (userId) references GUser(account)
);

create table Message(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	Sender			varchar(30),
	Receiver		varchar(30),
	time			datetime,
	marked			CHAR(1),
	contents		varchar(255),
	foreign key (Sender) references GUser(account),
	foreign key (Receiver) references GUser(account)
);

create table Favourites(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	belong			varchar(30),
	postID			int(10),
	foreign key (belong) references GUser(account),
	foreign key (postID) references Post(ID)
);

create table Follow(
	ID				int(20) not null primary key AUTO_INCREMENT,
	fromID			varchar(30) ,
	targetID		varchar(30) ,
	foreign key (fromID) references GUser(account),
	foreign key (targetID) references GUser(account)
);

-- 用来添加游戏就添加相应的section
DELIMITER //
CREATE TRIGGER InsetSectionForGame BEFORE INSERT ON Game 
FOR EACH ROW
BEGIN
	insert into Section(name,PostNum,OwnerType,OwnerID,GameID) value (Game.name,0,null,null,Game.ID);
END//
DELIMITER ;
