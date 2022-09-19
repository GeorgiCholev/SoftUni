CREATE SCHEMA `movies`;

USE `movies`;

-- p12
CREATE TABLE `directors` (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`director_name`VARCHAR(50) NOT NULL,
`notes` TEXT);

INSERT INTO `directors` (`director_name`)
VALUES ("Abby Smith"),
	   ("Becky Baker"),
       ("Carla Tailor"),
       ("Diana Carpenter"),
       ("Eve Cook");
       
CREATE TABLE `genres` (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`genre_name` VARCHAR(50) NOT NULL,
`notes` TEXT);

INSERT INTO `genres` (`genre_name`)
VALUES ("action"), ("comedy"), ("romance"), 
("mystery"), ("documentary");

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`category_name` VARCHAR(50) NOT NULL,
`notes` TEXT);

INSERT INTO `categories` (`category_name`)
VALUES ("funny"), ("scary"),
("boring"), ("old"), ("favourite");

CREATE TABLE `movies` (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`title` VARCHAR(50) NOT NULL UNIQUE,
`director_id` INT NOT NULL,
`copyright_year` YEAR,
`length` INT,
`genre_id` INT NOT NULL,
`category_id` INT NOT NULL,
`rating` INT(1) UNSIGNED,
`notes` TEXT);

INSERT INTO `movies` (`title`, `director_id`, `genre_id`, `category_id`)
VALUES ("East Side Story", 1, 1, 1),
       ("Emperor of the Jewellery", 2, 2, 2),
       ("Rising Good", 3, 3, 3),
       ("The Sitting Dead", 4, 4, 4),
       ("Finding Merlin", 5, 5, 5);
       
DROP SCHEMA `movies`;