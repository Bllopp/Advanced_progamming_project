-- Intialisaton script for the project's databases

-- For the User database which contains user's information and their role

CREATE DATABASE IF NOT EXISTS `user_APP`;

USE `user_APP`;

CREATE TABLE IF NOT EXISTS `user` (`userId`  int NOT NULL AUTO_INCREMENT, `name` VARCHAR(100) NOT NULL, `mail` VARCHAR(320) NOT NULL, `password` NOT NULL VARCHAR(30), PRIMARY KEY(`ID`))ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `role` (`userId` int not null, `role` varchar(50) not null, PRIMARY KEY (`userId`), constraint `FK_userId` FOREIGN KEY (`userId`) references `user`(`userId`)) ENGINE=INNODB;

-- For presentation database which contains presentations informations and the dates proposed by the student

CREATE DATABASE IF NOT EXISTS `presentation_APP`;
USE `presentation_APP`;

create table if not exists `presentation` (`presId` not null int auto_increment, `studentId` not null int, `mode` not null varchar(30), `teacherId` not null int, `tutorId` not null int, `finalDate` date, primary key (`id`), constraint `FK_teacherId` foreign key (`teacher`) references `user_APP`.`user`(`userId`), constraint `FK_tutorId` foreign key (`tutorId`) references `user_APP`,`user`(`userId`)) ENGINE=INNODB;

create table if not exists `presentationDates` (`presId` not null int, `date` date not null, `teacherVote` int, `tutorVote` int, primary key(`presId`,`date`), constraint `FK_presId` foreign key (`presId`) references `presentation`(`presId`) ) ENGINE=INNODB; 


-- For report database which contains report informations the validation of it 
CREATE DATABASE IF NOT EXISTS `report_APP`;

USE `report_APP`;

create table if not exists `report` (`studentId` not null int, primary key (`studentId`), `file` MEDIUMBLOB not null, teacherId not null int, constraint `FK_report_teacherId` foreign key (`teacherId`) references `user_APP`.`user`(`userId`), `teacherVote` int , `tutorId` not null int, constraint `FK_report_tutorId` foreign key (`tutorId`) references `user_APP`.`user`(`id`), `tutorVote` int, `uploadDate` not null date )ENGINE=INNODB;
