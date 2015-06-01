create database GameForum;
use GameForum;

create table Admin(
	Id 			int(10) not null primary key AUTO_INCREMENT,
	Name 		varchar(30),
	Passwd 		varchar(30)
);

create table GUser(
	ID 			int(10) not null primary key AUTO_INCREMENT,
	name 		varchar(255),
	passwd		varchar(30),
	gender 		Char(1),
	birthday 	date,
	email 		varchar(255),
	groupId		int(10) references UserGroup(ID),
	points		int(10),
	postNum		int(10),
	replyNum	int(10),
	signature	varchar(255),
	coinNum		int(10)
);
alter table GUser AUTO_INCREMENT=1000;

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
	releaseDate 	date,
	requirementID 	int(10) references Requirement(ID)
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
	authorId		int(10) references User(ID),
	type			varchar(10),
	shareNum		int(10),
	favouriteNum	int(10),
	postTime		datetime,
	SectionId		int(10) references Section(ID),
	Contents		varchar(255)	
);

create table Reply(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	PostID			int(10) references Post(ID),
	floor			int(10),
	userid			int(10) references User(ID),
	favouriteNum	int(10),
	postTime 		datetime,
	contents		varchar(255)
);

create table Section(
	ID 				int(10) not null primary key AUTO_INCREMENT,
	name			varchar(255),
	PostNum			int(10),
	OwnerType		varchar(6),
	OwnerID			int(10) references User(ID)
);

create table Message(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	Sender			int(10) references User(ID),
	Receiver		int(10) references User(ID),
	time			datetime,
	contents		varchar(255)
);

create table Favourites(
	ID 				int(20) not null primary key AUTO_INCREMENT,
	belong			int(10)	references User(ID),
	postID			int(10) references Post(ID)
);

create table Follow(
	ID				int(20) not null primary key AUTO_INCREMENT,
	fromID			int(10) references User(ID),
	targetID		int(10) references User(ID)
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