CREATE SCHEMA `minions`;

USE `minions`;

-- p01
CREATE TABLE `minions` (
	`id` INT AUTO_INCREMENT,
    `name` VARCHAR(50),
    `age` INT,
    PRIMARY KEY (`id`));


CREATE TABLE `towns` (
	`town_id` INT AUTO_INCREMENT,
    `name` VARCHAR(50),
    PRIMARY KEY (`town_id`));

-- p02
ALTER TABLE `minions`
ADD COLUMN `town_id` INT,
ADD CONSTRAINT `fk_minions_towns`
FOREIGN KEY (`town_id`)
REFERENCES `towns` (`id`);

-- p03
INSERT INTO `towns` (`id`, `name`)
VALUES (1, "Sofia"),
	   (2, "Plovdiv"),
       (3, "Varna");
       
INSERT INTO `minions` (`id`, `name`, `age`, `town_id`)
VALUES (1, "Kevin", 22, 1),
	   (2, "Bob", 15, 3),
       (3, "Steward", NULL, 2);
 
 -- p04
TRUNCATE TABLE `minions`;

-- p05
DROP TABLE `minions`, `towns`;