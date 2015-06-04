drop database GameForum;
create database GameForum;
use GameForum;

create table Admin(
	account 	varchar(30) not null primary key,
	Name 		varchar(30),
	Passwd 		varchar(30)
);

create table GUser(
	account 	varchar(30) not null primary key,
	name 		varchar(255),
	nickName	varchar(30),
	passwd		varchar(30),
	gender 		Char(1),
	birthday 	date,
	email 		varchar(255),
	groupId		int(10) default 0 references UserGroup(ID) ,
	points		int(10)  default 0,
	postNum		int(10)  default 0,
	replyNum	int(10)  default 0,
	signature	varchar(255),
	coinNum		int(10)  default 0,
	portraitAddr varchar(255)
);

create table Item(
	ID 			int(10) not null primary key AUTO_INCREMENT,
	name 		varchar(30),
	price 		int(10),
	remain 		int(10),
	closetime 	datetime
);

create table Game(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	name 			varchar(255),
	studioId 		int(10) references Studio(ID),
	platform 		varchar(20),
	type 			varchar(30),
	logoAddr		varchar(255),
	releaseDate 	date,
	requirementID 	int(10) references Requirement(ID),
	sectionID		int(10) references Section(ID),
	OwnerID			varchar(30) references User(account)
);

create table Studio(
	ID 			int(10) not null primary key AUTO_INCREMENT,
	name 		varchar(255),
	location 	varchar(50),
	description varchar(255)
);

create table UserGroup(
	ID 			int(10) not null primary key AUTO_INCREMENT,
	name		varchar(30),
	permissions varchar(255)
);

create table Post(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	title			varchar(50),
	authorId		varchar(30) references User(account),
	type			varchar(10),
	shareNum		int(10)  default 0,
	favouriteNum	int(10)  default 0,
	postTime		datetime,
	SectionId		int(10) references Section(ID),
	Contents		varchar(255)	
);

create table Reply(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	PostID			int(10) references Post(ID),
	floor			int(10),
	userId			varchar(30) references User(account),
	favouriteNum	int(10)  default 0,
	postTime 		datetime,
	contents		varchar(255)
);

create table Section(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	name			varchar(255),
	PostNum			int(10)  default 0,
	OwnerType		varchar(6),
	OwnerID			varchar(30) references User(account)
);

create table Message(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	Sender			varchar(30) references User(account),
	Receiver		varchar(30) references User(account),
	time			datetime,
	marked			CHAR(1),
	contents		varchar(255)
);

create table Favourites(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	belong			varchar(30) references User(account),
	postID			varchar(30) references User(account)
);

create table Follow(
	ID				int(20) not null primary key AUTO_INCREMENT,
	fromID			varchar(30) references User(account),
	targetID		varchar(30) references User(account)
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