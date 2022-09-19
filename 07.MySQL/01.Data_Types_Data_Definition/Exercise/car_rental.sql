CREATE SCHEMA `car_rental`;

USE `car_rental`;

-- p13
CREATE TABLE `categories` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`category` VARCHAR(100) NOT NULL,
`daily_rate` INT,
`weekly_rate` INT,
`monthly_rate` INT,
`weekend_rate` INT);

INSERT INTO `categories` (`category`)
VALUES ("sport"), ("family"), ("electric");

CREATE TABLE `cars` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`plate_number` VARCHAR(10) NOT NULL UNIQUE,
`make` VARCHAR(100),
`model` VARCHAR(100),
`car_year` YEAR,
`category_id` INT NOT NULL,
`doors` INT,
`picture` BLOB,
`car_condition` VARCHAR(100),
`available` BOOLEAN DEFAULT FALSE NOT NULL);

INSERT INTO `cars` (`plate_number`, `category_id`)
VALUES ("C3479MP", 1),
       ("B5671PM", 2),
       ("A3444PP", 3);
       
CREATE TABLE `employees` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(10) NOT NULL,
`title` VARCHAR(10) NOT NULL,
`notes` TEXT);

INSERT INTO `employees` (`first_name`, `last_name`, `title`)
VALUES ("Andrei", "Petrov", "driver"),
       ("Bobi", "Ivanov", "mechanic"),
       ("Ceco", "Georgiev", "manager");
       
CREATE TABLE `customers` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`driver_licence_number` INT NOT NULL UNIQUE,
`full_name` VARCHAR(100) NOT NULL,
`address` VARCHAR(100),
`city` VARCHAR(20),
`zip_code` INT,
`notes` TEXT);

INSERT INTO `customers` (`driver_licence_number`, `full_name`)
VALUES (76399, "Richard Thompson Smith"),
       (89354, "Andrei Simeonov Hadzhiev"),
       (90035, "Rodrico Enrique Fernandez");
       
CREATE TABLE `rental_orders` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`employee_id` INT NOT NULL,
`customer_id` INT NOT NULL,
`car_id` INT NOT NULL,
`car_condition` VARCHAR(50),
`tank_level` INT,
`kilometrage_start` INT NOT NULL,
`kilometrage_end` INT NOT NULL,
`total_kilometrage` INT,
`start_date` DATE NOT NULL,
`end_date` DATE NOT NULL,
`total_days` INT,
`rate_applied` BOOLEAN DEFAULT FALSE,
`tax_rate` INT,
`order_status` VARCHAR(20),
`notes` TEXT);

INSERT INTO `rental_orders` (`employee_id`, `customer_id`, `car_id`, 
							 `kilometrage_start`, `kilometrage_end`,
							 `start_date`, `end_date`)
 VALUES (1, 1, 1, 198896, 198465, '2022-12-24', '2022-12-27'),
	    (2, 2, 2, 204456, 204465, '2022-02-24', '2022-02-27'),
        (3, 3, 3, 304456, 304465, '2022-04-24', '2022-04-27');
        
DROP SCHEMA `car_rental`;