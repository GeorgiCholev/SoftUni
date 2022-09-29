USE `camp`;

-- p01  Mountains and Peaks
CREATE TABLE `mountains` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `peaks` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `mountain_id` INT NOT NULL,
    CONSTRAINT `fk_peak_mountain`
    FOREIGN KEY (`mountain_id`)
    REFERENCES `mountains`(`id`));
    
-- p02 Trip Organization
SELECT `driver_id`, `vehicle_type`,
        CONCAT_WS(' ', `first_name`, `last_name`) AS 'driver_name'
FROM `vehicles` v JOIN `campers` c
ON `v`.`driver_id` = `c`.`id`;     

-- p03 SoftUni Hiking
SELECT `starting_point` AS 'route_starting_point',
	   `end_point` AS 'route_ending_point',
       `leader_id`,
       CONCAT_WS(' ', `first_name`, `last_name`)
FROM `routes` r JOIN `campers` c
ON `r`.`leader_id` = `c`.`id`;  

-- p04 Delete Mountains
DROP TABLE `peaks`;

DROP TABLE `mountains`;

CREATE TABLE `mountains` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL);
    
CREATE TABLE `peaks` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `mountain_id` INT NOT NULL,
    CONSTRAINT `fk_peak_mountain`
    FOREIGN KEY (`mountain_id`)
    REFERENCES `mountains`(`id`)
    ON DELETE CASCADE);
    
-- p05 Project Management DB
CREATE TABLE `clients` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `client_name` VARCHAR(100));
    
CREATE TABLE `employees` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(30),
    `last_name` VARCHAR(30)); 