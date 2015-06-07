drop database gameforum;
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
	HOS				varchar(255),
	HProcessor		varchar(255),
	HMemory			int(3),
	HGraphics		varchar(255),
	HHardDrive		varchar(20),
	HSoundCard		varchar(255),
	LOS				varchar(255),
	LProcessor		varchar(255),
	LMemory			int(3),
	LGraphics		varchar(255),
	LHardDrive		varchar(20),
	LSoundCard		varchar(255)
);
insert into Requirement value(1,'Windows','I7-3630',8,'GTX660M','120G SSD','SoundCard','Windows','I5-3630',2,'GTX440M','20G SSD','SoundCard');

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
	PostNum			int(10)  default 0,
	foreign key (requirementID) references Requirement(ID),
	foreign key (studioId) references Studio(ID)
);

create table Section(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	name			varchar(255),
	PostNum			int(10)  default 0,
	OwnerType		varchar(6),
	OwnerID			varchar(30),
	GameID			int(10),
	SectionPictureAddr varchar(255),
	foreign key (OwnerID) references GUser(account),
	foreign key (GameID) references Game(ID)
);

-- 用来添加游戏就添加相应的section
DELIMITER //
CREATE TRIGGER InsetSectionForGame AFTER INSERT ON Game 
FOR EACH ROW
BEGIN
	insert into Section(name,PostNum,OwnerType,OwnerID,GameID) value (new.name,0,null,null,new.ID);
END//
DELIMITER ;

insert into Game value (1,'真三国无双7',1,'PC','act','picture/sanguo7logo.jpg','2010-5-9',1,'真三国无双7真三国无双7真三国无双7',0);
insert into Game value (2,'仙剑奇侠传',1,'PC','rpg','picture/xianjianxiqizhuanglogo.jpg','2010-5-10',1,'仙剑奇侠传仙剑奇侠传仙剑奇侠传',0);
insert into Game value (3,'古墓丽影',1,'PC','avg','picture/gumuliyinglogo.jpg','2010-5-18',1,'古墓丽影古墓丽影古墓丽影',0);
insert into Game value (4,'三国志',1,'PC','slg','picture/sanguozhilogo.jpg','2010-5-18',1,'三国志三国志三国志',0);


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
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('1', '1', 'admin', '1', '0', '0', '2015-06-09 11:49:26', '1', '11111');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('2', '2', 'admin', '1', '0', '0', '2015-03-02 11:50:03', '1', '22222');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('3', '3', 'admin', '1', '0', '0', '2015-01-05 11:50:08', '1', '33333');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('4', '4', 'admin', '1', '0', '0', '2014-12-08 11:50:13', '1', '44444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('5', '5', 'admin', '1', '0', '0', '2015-07-15 11:50:41', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('6', '6', 'admin', '1', '0', '0', '2015-06-10 13:57:14', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('7', '123', 'admin', '1', '0', '0', '2015-06-12 13:57:17', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('8', '123', 'admin', '1', '0', '0', '2004-06-16 13:57:22', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('9', '123', 'admin', '1', '0', '0', '2015-07-23 13:57:32', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('10', '123', 'admin', '1', '0', '0', '2015-06-01 13:57:37', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('11', '123', 'admin', '1', '0', '0', '2015-05-12 13:57:40', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('12', '123', 'admin', '1', '0', '0', '2015-06-23 13:57:44', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('13', '123', 'admin', '1', '0', '0', '2015-06-24 13:57:47', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('14', '3', 'admin', '1', '0', '0', '2015-03-17 13:57:50', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('15', '123', 'admin', '1', '0', '0', '2015-04-20 13:57:55', '1', '4444444');


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


