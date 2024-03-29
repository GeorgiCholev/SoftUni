CREATE DATABASE restaurant_db;
USE restaurant_db;

-- p01 Table Design
CREATE TABLE waiters (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(50),
    salary DECIMAL(10, 2)
);

CREATE TABLE `tables` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    floor INT NOT NULL,
    reserved TINYINT(1),
    capacity INT NOT NULL
);

CREATE TABLE orders (
	id INT PRIMARY KEY AUTO_INCREMENT,
    table_id INT NOT NULL,
    waiter_id INT NOT NULL,
    order_time TIME NOT NULL,
    payed_status TINYINT(1),
    CONSTRAINT `fk_orders_tables`
		FOREIGN KEY (table_id)
        REFERENCES `tables` (id),
	CONSTRAINT `fk_orders_waiters`
		FOREIGN KEY (waiter_id)
        REFERENCES waiters (id)
);

CREATE TABLE clients (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    card VARCHAR(50),
    review TEXT
);

CREATE TABLE orders_clients (
	order_id INT,
    client_id INT,
    CONSTRAINT `fk_map_to_orders`
		FOREIGN KEY (order_id)
        REFERENCES orders (id),
	CONSTRAINT `fk_map_to_clients`
		FOREIGN KEY (client_id)
        REFERENCES clients (id)
);

CREATE TABLE products (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL UNIQUE,
    `type` VARCHAR(30) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE orders_products (
	order_id INT,
    product_id INT,
    CONSTRAINT `fk_map_to_orders_from_products`
		FOREIGN KEY (order_id)
        REFERENCES `orders` (id),
	CONSTRAINT `fk_map_to_products_from_orders`
		FOREIGN KEY (product_id)
        REFERENCES products (id)
);

-- p02 Insert
INSERT INTO products (`name`, `type`, price)
(SELECT 
	concat(last_name, ' ', 'specialty') AS 'name',
    'Cocktail' AS 'type',
    ceil(salary * 0.01) AS 'price'
FROM
	waiters
WHERE id > 6
);

-- p03 Update
UPDATE orders SET table_id = table_id - 1
WHERE id BETWEEN 12 AND 23;

-- p04 Delete
DELETE w FROM waiters AS w
LEFT JOIN orders AS o
  ON w.id = o.waiter_id
WHERE o.id IS NULL;

-- p05 Clients
SELECT
	id, first_name, last_name, birthdate, card, review
FROM
	clients
ORDER BY birthdate DESC;

-- p06 Birthdate
SELECT
	first_name, last_name, birthdate, review
FROM
	clients
WHERE card IS NULL AND YEAR(birthdate) BETWEEN 1978 AND 1993
ORDER BY last_name DESC, id ASC
LIMIT 5;

-- p07 Accounts
SELECT
	concat(last_name, first_name, char_length(first_name), 'Restaurant') AS 'username',
    reverse(substring(email, 2, 12)) AS 'password'
FROM
	waiters
WHERE salary IS NOT NULL
ORDER BY `password` DESC;

-- p08 Top from menu
SELECT
	p.id, p.`name`,
    count(op.product_id) AS 'count'
FROM
	products AS p
LEFT JOIN orders_products AS op
  ON p.id = op.product_id
GROUP BY p.id
HAVING `count` >= 5
ORDER BY `count` DESC, `name` ASC;

-- p09 Availability
SELECT
	t.id AS 'table_id',
    t.capacity,
    count(client_id) AS 'count_clients',
    CASE
		WHEN (capacity > count(client_id)) THEN 'Free seats'
        WHEN (capacity = count(client_id)) THEN 'Full'
        ELSE 'Extra seats'
    END AS 'availabilty'
FROM
	`tables` AS t
JOIN orders AS o
  ON t.id = o.table_id
JOIN orders_clients AS oc
  ON o.id = oc.order_id
WHERE t.floor = 1
GROUP BY t.id
ORDER BY t.id DESC;

DELIMITER $$
-- p10 Extract bill
CREATE FUNCTION udf_client_bill(full_name VARCHAR(50))
RETURNS DECIMAL(19, 2)
DETERMINISTIC
BEGIN
	RETURN (
		SELECT
			sum(p.price) AS 'bill'
		FROM 
			clients AS c
		LEFT JOIN orders_clients AS oc
		  ON c.id = oc.client_id
		LEFT JOIN orders AS o
		  ON oc.order_id = o.id
		LEFT JOIN orders_products AS op
		  ON o.id = op.order_id
		LEFT JOIN products AS p
		  ON op.product_id = p.id
		WHERE concat_ws(' ', c.first_name, c.last_name) = full_name
		GROUP BY c.id
	);
END$$

-- p11 Happy hour
CREATE PROCEDURE udp_happy_hour(`type` VARCHAR(50))
BEGIN
	UPDATE products AS p SET price = price * 0.80
	WHERE price >= 10.00 AND p.`type` = `type`;
END$$
