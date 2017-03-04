# MySQL-Front 5.1  (Build 2.7)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: 192.168.1.251    Database: spring_test
# ------------------------------------------------------
# Server version 5.5.17

#
# Source for table course
#

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `mark` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Source for table ld_homework
#

DROP TABLE IF EXISTS `ld_homework`;
CREATE TABLE `ld_homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL DEFAULT '0',
  `classId` int(11) NOT NULL DEFAULT '0',
  `lessionId` int(11) NOT NULL DEFAULT '0',
  `homeworkFilePath` varchar(255) NOT NULL DEFAULT '' COMMENT '作业路径',
  `homeworkFileName` varchar(255) DEFAULT NULL COMMENT '作业名',
  `starCount` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `negativeCount` int(11) NOT NULL DEFAULT '0' COMMENT '差评数量',
  `correctFlag` mediumint(9) NOT NULL DEFAULT '0' COMMENT '批改标志，0 未批改，1已批改',
  `BestFlag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是优秀作业',
  `createDate` date NOT NULL DEFAULT '0000-00-00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

#
# Source for table ld_homeworkfb
#

DROP TABLE IF EXISTS `ld_homeworkfb`;
CREATE TABLE `ld_homeworkfb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `myid` int(11) NOT NULL DEFAULT '0' COMMENT '批改的学员ID',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '被批注的学员ID',
  `homeworkId` int(11) NOT NULL DEFAULT '0' COMMENT '作业表ID',
  `levelFlag` char(1) DEFAULT 'A' COMMENT '为A，B，C，D 之一',
  `mark` varchar(255) DEFAULT '' COMMENT '200字以内）评语',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作业批注';

#
# Source for table orders
#

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL DEFAULT '0',
  `productName` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Source for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL DEFAULT '',
  `pwd` varchar(255) NOT NULL DEFAULT '',
  `classId` int(11) NOT NULL DEFAULT '0' COMMENT '班级id',
  `lessionId` int(11) NOT NULL DEFAULT '0' COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

#
# Source for table users
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
