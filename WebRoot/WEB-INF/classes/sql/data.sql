drop database schedule;
create database schedule;
use schedule;

drop table if exists student;
create table student
(
  stu_id int(10) not null primary key AUTO_INCREMENT,
  name char(20),
  stu_sex char(6),
  stu_number varchar(10),
  stu_password varchar(10),
  stu_class varchar(10),
  stu_school varchar(128)
); 

drop table if exists teacher;
create table teacher
(
teacher_id int(10) not null primary key AUTO_INCREMENT,
name varchar(20),
teacher_sex varchar(6),
teacher_telephone varchar(16),
teacher_email varchar(32),
teacher_school varchar(128)
);

create table admin
(
  admin_id int(10) not null primary key AUTO_INCREMENT,
  name char(20),
  admin_password varchar(10),
  admin_email varchar(50),
  telephone varchar(20)
); 

drop table if exists course;
create table course
(
course_id int(10) not null primary key AUTO_INCREMENT,
course_name varchar(30),
teacher_name char(20) references teacher(name),
course_school char(10),
course_credit varchar(10),
course_max varchar(10),
semester varchar(10),
start_week varchar(10),
end_week varchar(10),
course_studentnum int(5),
course_time varchar(10),
classroom_name char(10) references classroom(classroom_name)
);

drop table if exists choose_course;
create table choose_course
(
choose_course_id int(10) not null primary key AUTO_INCREMENT,
course_name varchar(30),
stu_name char(20)
);
drop table if exists building;
create table building
(
  building_id int(10) not null primary key AUTO_INCREMENT,
  building_name varchar(32)
 );

drop table if exists classroom;
create table classroom
(
  classroom_id int(10) not null primary key AUTO_INCREMENT,
  building_name varchar(32) references building(building_name),
  classroom_name varchar(10)
); 

drop table if exists t_user;
create table t_user
(
   ID              int(3) not null auto_increment,
   name            varchar(20) not null,
   password        varchar(10) not null,
   primary key (ID)
);
insert into t_user values (1,'123124124125r1h','12345');
insert into admin values (1,'admin','admin','111','222');
