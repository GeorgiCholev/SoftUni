CREATE DATABASE stc;
USE stc;

-- p01 Table Design
CREATE TABLE addresses (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL
);

CREATE TABLE categories (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL
);

CREATE TABLE cars (
	id INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(20) NOT NULL,
    model VARCHAR(20),
    `year` INT NOT NULL DEFAULT(0),
    mileage INT DEFAULT(0),
    `condition` CHAR(1) NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT `fk_cars_categories`
		FOREIGN KEY (category_id)
        REFERENCES categories (id)
);

CREATE TABLE clients (
	id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE courses (
	id INT PRIMARY KEY AUTO_INCREMENT,
    from_address_id INT NOT NULL,
    `start` DATETIME NOT NULL,
    bill DECIMAL(10, 2) DEFAULT(10),
    car_id INT NOT NULL,
    client_id INT NOT NULL,
    CONSTRAINT `fk_courses_addresses`
		FOREIGN KEY (from_address_id)
        REFERENCES addresses (id),
    CONSTRAINT `fk_courses_cars`
		FOREIGN KEY (car_id)
        REFERENCES cars (id),
    CONSTRAINT `fk_courses_clients`
		FOREIGN KEY (client_id)
        REFERENCES clients (id)
);

CREATE TABLE drivers (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    age INT NOT NULL,
    rating FLOAT DEFAULT(5.5)
);

CREATE TABLE cars_drivers (
	car_id INT NOT NULL,
    driver_id INT NOT NULL,
    CONSTRAINT `pk_cars_drivers`
		PRIMARY KEY (car_id, driver_id),
	CONSTRAINT `fk_carsDrivers_cars`
		FOREIGN KEY (car_id)
        REFERENCES cars (id),
	CONSTRAINT `fk_carsDrivers_drivers`
		FOREIGN KEY (driver_id)
        REFERENCES drivers (id)
);

DROP DATABASE stc;

-- p02 Insert
INSERT INTO clients (full_name, phone_number)
(SELECT
	concat_ws(' ', first_name, last_name) AS 'full_name',
    concat('(088) 9999', (id * 2)) AS 'phone_number'
FROM
	drivers
WHERE 
	id BETWEEN 10 AND 20
);

-- p03 Update
UPDATE
	cars
SET
	`condition` = 'C'
WHERE
	(mileage > 800000 OR mileage IS NULL) 
    AND 
    (`year` <= 2010)
    AND NOT
    (make = 'Mercedes-Benz');
    
-- p04 Delete
DELETE cl FROM clients AS cl
        LEFT JOIN
    courses AS co ON cl.id = co.client_id 
WHERE
    CHAR_LENGTH(cl.full_name) > 3
    AND co.id IS NULL;
    
-- p05 Cars
SELECT
	make, model, `condition`
FROM
	cars;
    
-- p06 Drivers and Cars
SELECT
	d.first_name, d.last_name, c.make, c.model, c.mileage
FROM
	drivers AS d
JOIN cars_drivers AS cd
  ON d.id = cd.driver_id
JOIN cars AS c
  ON cd.car_id = c.id
WHERE
	c.mileage IS NOT NULL
ORDER BY c.mileage DESC, d.first_name ASC;

-- p07 Number of courses for each car
SELECT 
	c.id, c.make, c.mileage,
    count(co.id) AS 'count_of_courses',
    round(avg(co.bill), 2) AS 'avg_bill'
FROM cars AS c
LEFT JOIN courses AS co
  ON c.id = co.car_id
GROUP BY c.id
HAVING NOT count_of_courses = 2
ORDER BY count_of_courses DESC, c.id ASC;

-- p08 Regular clients
SELECT 
	cl.full_name,
    count(cars.id) AS 'count_of_cars',
    sum(co.bill) AS 'total_sum'
FROM
	clients AS cl
JOIN courses AS co
  ON cl.id = co.client_id
JOIN cars
  ON co.car_id = cars.id
GROUP BY cl.id
HAVING cl.full_name LIKE '_a%' 
	AND count_of_cars > 1
ORDER BY cl.full_name;

-- p09 Full information of courses
SELECT
	a.`name`,
    IF (HOUR(co.`start`) BETWEEN 6 AND 20, 'Day', 'Night') AS 'day_time',
    co.bill,
    cl.full_name,
    cars.make,
    cars.model,
    cat.`name` AS 'category_name'
FROM
	addresses AS a
JOIN courses AS co
  ON a.id = co.from_address_id
JOIN clients AS cl
  ON co.client_id = cl.id
JOIN cars
  ON co.car_id = cars.id
JOIN categories AS cat
  ON cars.category_id = cat.id
ORDER BY co.id;

DELIMITER $$
-- p10 Find all courses by client’s phone number
CREATE FUNCTION udf_courses_by_client(phone_num VARCHAR(20))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE courses_count_by_phone_number INT;
    SET courses_count_by_phone_number := (
	SELECT 
		count(co.id) AS 'courses_count'
    FROM
		clients AS cl
	LEFT JOIN courses AS co
      ON cl.id = co.client_id
	GROUP BY cl.phone_number
    HAVING cl.phone_number = phone_num
    );
    RETURN courses_count_by_phone_number;
END$$

-- p11 Full info for address
CREATE PROCEDURE udp_courses_by_address(address_name VARCHAR(100))
BEGIN
	SELECT
	a.`name`,
    cl.full_name AS 'full_names',
    CASE
		WHEN co.bill <= 20 THEN 'Low'
        WHEN co.bill BETWEEN 21 AND 30 THEN 'Medium'
        WHEN co.bill > 30 THEN 'High'
    END AS 'level_of_bill',
    cars.make,
    cars.`condition`,
    cat.`name` AS 'cat_name'
	FROM
	addresses AS a
	JOIN courses AS co
	  ON a.id = co.from_address_id
	JOIN clients AS cl
	  ON co.client_id = cl.id
	JOIN cars
	  ON co.car_id = cars.id
	JOIN categories AS cat
	  ON cars.category_id = cat.id
	WHERE a.`name` = address_name
	ORDER BY cars.make, full_names;
END$$