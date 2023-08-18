/*
SQLyog Community Edition- MySQL GUI v5.22a
Host - 5.0.19-nt : Database - securedata
*********************************************************************
Server version : 5.0.19-nt
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `securedata`;

USE `securedata`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `login_history` */

DROP TABLE IF EXISTS `login_history`;

CREATE TABLE `login_history` (
  `id` bigint(20) NOT NULL auto_increment,
  `uname` varchar(100) default NULL,
  `login_date` date default NULL,
  `login_time` time default NULL,
  `logout_time` time default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login_history` */

insert  into `login_history`(`id`,`uname`,`login_date`,`login_time`,`logout_time`) values (1,'umesh','2023-02-09','15:58:48',NULL),(2,'umesh','2023-02-09','16:01:14',NULL),(3,'umesh','2023-02-09','16:08:08',NULL);

/*Table structure for table `registration` */

DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration` (
  `userid` varchar(50) NOT NULL,
  `password` varchar(250) default NULL,
  `secretkey` varchar(25) default NULL,
  `name` varchar(100) default NULL,
  `age` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `reg_date` datetime default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `registration` */

insert  into `registration`(`userid`,`password`,`secretkey`,`name`,`age`,`address`,`reg_date`) values ('umesh','123','123','umesh','19','kjfsldakjfsa','2023-02-09 15:58:25');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
