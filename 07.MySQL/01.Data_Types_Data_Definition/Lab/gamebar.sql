CREATE SCHEMA gamebar;

USE gamebar;

-- p01
CREATE TABLE `gamebar`.`employees` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `categories` (
	`id` INT AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
    );
    
CREATE TABLE `products` (
	`id` INT AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `category_id` INT NOT NULL,
    PRIMARY KEY (`id`)
    );
    
-- p02
INSERT INTO `employees` (`first_name`, `last_name`) 
VALUES 
('Alex', 'Alexov'),
('Bobi', 'Bobiev'),
('Ceco', 'Cecov');

INSERT INTO `employees` (`first_name`, `last_name`)
VALUES
('Denis', 'Denisov');

-- p03
ALTER TABLE `employees`
ADD COLUMN `middle_name` VARCHAR(50);

-- p04
ALTER TABLE `products`
ADD CONSTRAINT `fk_id_product`
FOREIGN KEY (`category_id`)
REFERENCES `categories` (`id`);

-- p05
ALTER TABLE `employees`
CHANGE COLUMN `middle_name`
    `middle_name` VARCHAR(100);
    