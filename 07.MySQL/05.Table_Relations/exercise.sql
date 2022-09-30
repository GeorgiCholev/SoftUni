CREATE DATABASE `exc_table_relations`;

USE `exc_table_relations`; 

-- p01 One-To-One Relationship
CREATE TABLE `passports` (
	`passport_id` INT PRIMARY KEY AUTO_INCREMENT,
    `passport_number` VARCHAR(20) UNIQUE NOT NULL
);

INSERT INTO `passports` (`passport_id`, `passport_number`)
VALUES (101, 'N34FG21B'), (102, 'K65LO4R7'), (103, 'ZE657QP2');

CREATE TABLE `people` (
	`person_id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20),
    `salary` DECIMAL(7, 2),
    `passport_id` INT UNIQUE,
    CONSTRAINT `fk_people_passports`
    FOREIGN KEY (`passport_id`)
    REFERENCES `passports`(`passport_id`)
);

INSERT INTO `people` (`first_name`, `salary`, `passport_id`)
VALUES ('Roberto', 43300.00, 102),
       ('Tom', 56100.00, 103), 
       ('Yana', 60200.00, 101);
       
-- p02 One-To-Many Relationship
CREATE TABLE `manufacturers` (
	`manufacturer_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20),
    `established_on` DATE);
    
INSERT INTO `manufacturers` (`name`, `established_on`)
VALUES ('BMW', '1916-03-01'),
	   ('Tesla', '2003-01-01'),
       ('Lada', '1966-05-01');
       
CREATE TABLE `models` (
	`model_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20),
    `manufacturer_id` INT NOT NULL,
    CONSTRAINT `fk_models_manufacturers`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `manufacturers`(`manufacturer_id`)
);

INSERT INTO `models` (`model_id`, `name`, `manufacturer_id`)
VALUES (101, 'X1', 1), (102, 'i6', 1), (103, 'Model S', 2), 
	   (104, 'Model X', 2), (105, 'Model 3', 2), (106, 'Nova', 3);
       
-- p03 Many-To-Many Relationship
CREATE TABLE `exams` (
	`exam_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20)
);

INSERT INTO `exams` (`exam_id`, `name`)
VALUES (101, 'Spring MVC'), (102, 'Neo4j'), (103, 'Oracle 11g');

CREATE TABLE `students` (
	`student_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20)
);

INSERT INTO `students` (`name`)
VALUES ('Mila'), ('Toni'), ('Ron');

CREATE TABLE `students_exams` (
	`student_id` INT NOT NULL,
    `exam_id` INT NOT NULL,
    CONSTRAINT `pk_student_exams`
    PRIMARY KEY (`student_id`, `exam_id`),
    CONSTRAINT `fk_stud_exams_students`
    FOREIGN KEY (`student_id`)
    REFERENCES `students`(`student_id`),
    CONSTRAINT `fk_stud_exams_exams`
    FOREIGN KEY (`exam_id`)
    REFERENCES `exams`(`exam_id`)
);

INSERT INTO `students_exams` (`student_id`, `exam_id`)
VALUES (1, 101), (1, 102), (2, 101), (3, 103), (2, 102), (2, 103);

-- p04 Self-Referencing
CREATE TABLE `teachers` (
	`teacher_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `manager_id` INT
);

INSERT INTO `teachers` (`teacher_id`, `name`, `manager_id`)
VALUES (101, 'John', NULL), (102, 'Maya', 106), (103, 'Silvia', 106),
	   (104, 'Ted', 105), (105, 'Mark', 101), (106, 'Greta', 101);
       
ALTER TABLE `teachers`
ADD CONSTRAINT `fk_manager_teacher`
FOREIGN KEY (`manager_id`)
REFERENCES `teachers`(`teacher_id`);

-- p05 Online Store Database
CREATE TABLE `item_types` (
	`item_type_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

CREATE TABLE `items` (
	`item_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `item_type_id` INT(11),
    CONSTRAINT `fk_items_item_types`
    FOREIGN KEY (`item_type_id`)
    REFERENCES `item_types`(`item_type_id`)
);

CREATE TABLE `cities` (
	`city_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(50)
);

CREATE TABLE `customers` (
	`customer_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `birthday` DATE,
    `city_id` INT(11),
    CONSTRAINT `fk_customers_cities`
    FOREIGN KEY (`city_id`)
    REFERENCES `cities`(`city_id`)
);

CREATE TABLE `orders` (
	`order_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `customer_id` INT(11),
    CONSTRAINT `fk_orders_customers`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`customer_id`)
);

CREATE TABLE `order_items` (
	`order_id` INT(11) NOT NULL,
    `item_id` INT(11) NOT NULL,
    CONSTRAINT `pk_order_and_item`
    PRIMARY KEY (`order_id`, `item_id`),
    CONSTRAINT `fk_orderItems_items`
    FOREIGN KEY (`order_id`)
    REFERENCES `items`(`item_id`),
    CONSTRAINT `fk_orderItems_orders`
    FOREIGN KEY (`item_id`)
    REFERENCES `orders`(`order_id`)
);

-- p06 Peaks in Rila
USE `geography`;

SELECT `m`.`mountain_range`, `p`.`peak_name`,
`p`.`elevation` AS 'peak_elevation'
FROM `mountains` m JOIN `peaks` p
ON `m`.`id` = `p`.`mountain_id`
WHERE `m`.`mountain_range` = 'Rila'
ORDER BY `peak_elevation` DESC;