CREATE SCHEMA `people`;

USE `people`;

-- p07
CREATE TABLE `people` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` MEDIUMBLOB,
`height` DOUBLE(3, 2),
`weight` DOUBLE(5, 2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT);

INSERT INTO `people` (`name`, `height`, `weight`, `gender`, `birthdate`)
	VALUES ('Asen', 1.67, 102.05, 'm', '1964-06-13'),
		   ('Bobi', 1.77, 101.15, 'm', '1974-05-12'),
           ('Vili', 1.87, 100.25, 'f', '1984-04-11'),
           ('Gogo', 1.97, 100.35, 'm', '1994-03-10'),
           ('Didi', 2.07, 100.45, 'f', '2014-02-09');
           
-- p08
CREATE TABLE `users` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) UNIQUE NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` MEDIUMBLOB,
`last_login_time` TIME,
`is_deleted` BOOLEAN);

INSERT INTO `users` (`username`, `password`, `last_login_time`)
VALUES ("shrekFan342022", "d3str0yer13", '23:59:59'),
	   ("pinocchio22017", "3nsl4ver433", '17:44:16'),
       ("snowWhite67378", "c0nQu3r3r22", '00:04:56'),
       ("kingCharming13", "m4gnific3nt", '04:04:34'),
       ("bigMerman58033", "0c3anH3ir78", '09:15:13');
       
-- p09
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD PRIMARY KEY `pk_users` (`id`, `username`);

-- p10
ALTER TABLE `users`
MODIFY COLUMN `last_login_time` DATETIME DEFAULT NOW();

-- p11
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`),
ADD UNIQUE (`username`);

DROP SCHEMA `people`;