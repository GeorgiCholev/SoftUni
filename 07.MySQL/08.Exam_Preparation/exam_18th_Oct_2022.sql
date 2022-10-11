CREATE DATABASE softuni_stores_system;
USE softuni_stores_system;

-- p01 Table Design
CREATE TABLE pictures (
	id INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(100) NOT NULL,
    added_on DATETIME NOT NULL
);

CREATE TABLE categories (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE products (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE,
    best_before DATE,
    price DECIMAL(10, 2) NOT NULL,
    `description` TEXT,
    category_id INT NOT NULL,
    picture_id INT NOT NULL,
    CONSTRAINT `fk_products_picture`
		FOREIGN KEY (picture_id)
        REFERENCES pictures (id),
	CONSTRAINT `fk_products_categories`
		FOREIGN KEY (category_id)
        REFERENCES categories (id)
);

CREATE TABLE towns (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE addresses (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    town_id INT NOT NULL,
    CONSTRAINT `fk_addresses_towns`
		FOREIGN KEY (town_id)
        REFERENCES towns (id)
);

CREATE TABLE stores (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE,
    rating FLOAT NOT NULL,
    has_parking BOOLEAN DEFAULT FALSE,
    address_id INT NOT NULL,
    CONSTRAINT `fk_stores_addresses`
		FOREIGN KEY (address_id)
        REFERENCES addresses (id)
);

CREATE TABLE products_stores (
	product_id INT NOT NULL,
    store_id INT NOT NULL,
    CONSTRAINT `pk_composite`
		PRIMARY KEY (product_id, store_id),
	CONSTRAINT `fk_productStores_products`
		FOREIGN KEY (product_id)
        REFERENCES products (id),
	CONSTRAINT `fk_productStores_stores`
		FOREIGN KEY (store_id)
        REFERENCES stores (id)
);

CREATE TABLE employees (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(15) NOT NULL,
    middle_name CHAR(1),
    last_name VARCHAR(20) NOT NULL,
    salary DECIMAL(19, 2) DEFAULT(0.00),
	hire_date DATE NOT NULL,
    manager_id INT,
    store_id INT NOT NULL,
    CONSTRAINT `fk_manager_self_reference`
		FOREIGN KEY (manager_id)
        REFERENCES employees (id),
	CONSTRAINT `fl_employees_stores`
		FOREIGN KEY (store_id)
        REFERENCES stores (id)
);

-- p02 Insert
INSERT INTO products_stores (product_id, store_id)
(SELECT
	p.id AS 'product_id',
    '1' AS 'store_id'
 FROM
	products AS p
 LEFT JOIN products_stores AS pc
   ON p.id = pc.product_id
 WHERE pc.store_id IS NULL
);

-- p03 Update
UPDATE
	employees
SET
	manager_id = 3, salary = salary - 500
    WHERE 
		YEAR(hire_date) >= 2003 AND store_id NOT IN (
			SELECT id FROM stores WHERE `name` IN ('Cardguard', 'Veribet')
);

-- p04 Delete
DELETE FROM
	employees
WHERE
	manager_id IS NOT NULL AND salary >= 6000;
    
-- p05 Employees
SELECT
	first_name, middle_name, last_name, salary, hire_date
FROM
	employees
ORDER BY hire_date DESC;

-- p06 Products with old pictures
SELECT
	pr.`name` AS 'product_name',
    pr.price, pr.best_before,
    concat(LEFT(pr.`description`, 10), '...') AS 'short_description',
    pic.url
FROM
	products AS pr
JOIN pictures AS pic
  ON pr.picture_id = pic.id
WHERE
	char_length(pr.`description`) > 100
    AND YEAR(pic.added_on) < 2019
    AND pr.price > 20
ORDER BY pr.price DESC;

-- p07 Counts of products in stores and their average
SELECT
	s.`name`,
    count(p.id) AS 'product_count',
    round(avg(p.price), 2) AS 'avg'
FROM
	stores AS s
LEFT JOIN products_stores AS pc
  ON s.id = pc.store_id
LEFT JOIN products AS p
  ON pc.product_id = p.id
GROUP BY s.id
ORDER BY product_count DESC, `avg` DESC, s.id ASC;

-- p08 Specific employee
SELECT
	concat_ws(' ', e.first_name, e.last_name) AS 'Full_name',
    s.`name` AS 'Store_name',
    a.`name` AS 'Address',
    e.salary
FROM
	employees AS e
JOIN stores AS s
  ON e.store_id = s.id
JOIN addresses AS a
  ON s.address_id = a.id
WHERE
	e.salary < 4000 
    AND a.`name` LIKE '%5%'
    AND char_length(s.`name`) > 8
    AND e.last_name LIKE '%n';
    
-- p09 Find all information of stores
SELECT
	REVERSE(s.`name`) AS 'reversed_name',
    concat(upper(t.`name`), '-', a.`name`) AS 'full_address',
    count(e.id) AS 'employees_count'
FROM
	stores AS s
JOIN addresses AS a
  ON s.address_id = a.id
JOIN towns AS t
  ON a.town_id = t.id
JOIN employees AS e
  ON s.id = e.store_id
GROUP BY s.id
ORDER BY full_address ASC;

-- p10 Find full name of top paid employee by store name
DELIMITER $$
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS VARCHAR(80)
DETERMINISTIC
BEGIN
	DECLARE full_info_top_paid_employee VARCHAR(80);
    DECLARE store_to_inspect_id INT;
    SET store_to_inspect_id := (
		SELECT id FROM stores WHERE `name` = store_name
	);
    SET full_info_top_paid_employee := (
		SELECT 
			concat(
				concat_ws(' ', first_name, concat(middle_name, '.'), last_name),
				' works in store for ',
				TIMESTAMPDIFF(YEAR, hire_date, '2020-10-18'),
				' years'
			) AS 'full_info'
		FROM
			employees
		WHERE store_id = store_to_inspect_id
        ORDER BY salary DESC
        LIMIT 1
    );
    RETURN full_info_top_paid_employee;
END$$

-- p11 Update product price by address
CREATE PROCEDURE udp_update_product_price(address_name VARCHAR(50))
BEGIN
	UPDATE products AS p
		JOIN products_stores AS ps
		ON ps.product_id = p.id
    		JOIN stores AS s
    		ON ps.store_id = s.id
    		JOIN addresses AS a
    		ON s.address_id = a.id
	SET p.price = IF (left(address_name, 1) = '0', p.price + 100, p.price + 200)
	WHERE a.`name` = address_name;
END$$
