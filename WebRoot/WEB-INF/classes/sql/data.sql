drop database gameforum;
create database GameForum;
use GameForum;

create table UserGroup(
	name		varchar(30) not null primary key,
	permissions varchar(255)
);
insert into UserGroup values ('admin',null);
insert into UserGroup values ('С��',null);

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
	foreign key (groupName) references UserGroup(name) ON DELETE CASCADE ON UPDATE CASCADE
);
insert into Guser value ('admin','admin','admin','admin','M','1994-05-20','admin@qq.com','admin',0,0,0,'admin',0,null);
insert into Guser value ('zhangan','zhangan001','zhangan0520','zhangan','M','1994-05-20','zhangan@qq.com','С��',0,0,0,'zhangan',0,null);

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
insert into Requirement value(1,'Windows','I7-3630',8,'GTX660M','120G SSD','SoundCard111','Windows','I5-3630',2,'GTX440M','20G SSD','SoundCard222');

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
	foreign key (requirementID) references Requirement(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (studioId) references Studio(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Section(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	name			varchar(255),
	PostNum			int(10)  default 0,
	OwnerType		varchar(6),
	OwnerID			varchar(30),
	GameID			int(10),
	SectionPictureAddr varchar(255),
	foreign key (OwnerID) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (GameID) references Game(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ���������Ϸ�������Ӧ��section
drop trigger if exists InsetSectionForGame;
DELIMITER //
CREATE TRIGGER InsetSectionForGame AFTER INSERT ON Game 
FOR EACH ROW
BEGIN
	insert into Section(name,PostNum,OwnerType,OwnerID,GameID,SectionPictureAddr) value (new.name,0,'admin','admin',new.ID,concat(new.name,'header.jpg') );
END//
DELIMITER ;

INSERT INTO `gameforum`.`game` (`ID`, `name`, `studioId`, `platform`, `type`, `logoAddr`, `releaseDate`, `requirementID`, `description`, `PostNum`) VALUES ('1', '��������˫7', '1', 'PC', 'act', '��������˫7logo.jpg', '2010-05-09', '1', '��������˫7��������˫7��������˫7', '0');
INSERT INTO `gameforum`.`game` (`ID`, `name`, `studioId`, `platform`, `type`, `logoAddr`, `releaseDate`, `requirementID`, `description`, `PostNum`) VALUES ('2', '�ɽ�������', '1', 'PC', 'rpg', '�ɽ�������logo.jpg', '2010-05-10', '1', '�ɽ��������ɽ��������ɽ�������', '0');
INSERT INTO `gameforum`.`game` (`ID`, `name`, `studioId`, `platform`, `type`, `logoAddr`, `releaseDate`, `requirementID`, `description`, `PostNum`) VALUES ('3', '��Ĺ��Ӱ', '1', 'PC', 'avg', '��Ĺ��Ӱlogo.jpg', '2010-05-18', '1', '��Ĺ��Ӱ��Ĺ��Ӱ��Ĺ��Ӱ', '0');
INSERT INTO `gameforum`.`game` (`ID`, `name`, `studioId`, `platform`, `type`, `logoAddr`, `releaseDate`, `requirementID`, `description`, `PostNum`) VALUES ('4', '����־', '1', 'PC', 'slg', '����־logo.jpg', '2010-05-18', '1', '����־����־����־', '0');

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
	foreign key (sectionID) references Section(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (authorId) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('1', '1', 'admin', '1', '0', '0', '2015-06-09 11:49:26', '1', '11111');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('2', '2', 'admin', '1', '0', '0', '2015-03-02 11:50:03', '1', '22222');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('3', '3', 'admin', '1', '0', '0', '2015-01-05 11:50:08', '1', '33333');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('4', '4', 'admin', '1', '0', '0', '2014-12-08 11:50:13', '1', '44444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('6', '6', 'admin', '1', '0', '0', '2015-06-10 13:57:14', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('7', '123', 'admin', '1', '0', '0', '2015-06-12 13:57:17', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('8', '123', 'admin', '1', '0', '0', '2004-06-16 13:57:22', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('10', '123', 'admin', '1', '0', '0', '2015-06-01 13:57:37', '1', '4444444');
INSERT INTO `gameforum`.`post` (`ID`, `title`, `authorId`, `type`, `shareNum`, `favouriteNum`, `postTime`, `SectionId`, `Contents`) VALUES ('11', '123', 'admin', '1', '0', '0', '2015-05-12 13:57:40', '1', '4444444');
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
	foreign key (PostID) references Post(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (userId) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Message(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	Sender			varchar(30),
	Receiver		varchar(30),
	time			datetime,
	marked			CHAR(1),
	contents		varchar(255),
	foreign key (Sender) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (Receiver) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Favourites(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	belong			varchar(30),
	postID			int(10),
	foreign key (belong) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (postID) references Post(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- �����ղ��������post��favouriteNum
drop trigger if exists FavouritePostTrigger;
DELIMITER //
CREATE TRIGGER FavouritePostTrigger BEFORE INSERT ON favourites 
FOR EACH ROW
BEGIN
	UPDATE post SET post.favouriteNum = post.favouriteNum + 1 WHERE post.id=new.postID;
END//
DELIMITER ;

-- ����ȡ���ղ��������post��favouriteNum
drop trigger if exists unFavouritePostTrigger;
DELIMITER //
CREATE TRIGGER unFavouritePostTrigger AFTER DELETE ON favourites 
FOR EACH ROW
BEGIN
	UPDATE post SET post.favouriteNum = post.favouriteNum - 1 WHERE post.id=old.postID;
END//
DELIMITER ;

create table Follow(
	ID				int(20) not null primary key AUTO_INCREMENT,
	fromID			varchar(30) ,
	targetID		varchar(30) ,
	foreign key (fromID) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (targetID) references GUser(account) ON DELETE CASCADE ON UPDATE CASCADE
);


