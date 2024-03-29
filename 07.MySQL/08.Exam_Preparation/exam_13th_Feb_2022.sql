CREATE DATABASE online_store;
USE online_store;

-- p01 Table Design
CREATE TABLE brands (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE categories (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE reviews (
	id INT PRIMARY KEY AUTO_INCREMENT,
    content TEXT,
    rating DECIMAL(10, 2) NOT NULL,
    picture_url VARCHAR(80) NOT NULL,
    published_at DATETIME NOT NULL
);

CREATE TABLE products (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    quantity_in_stock INT,
    `description` TEXT,
    brand_id INT NOT NULL,
    category_id INT NOT NULL,
    review_id INT NOT NULL,
    CONSTRAINT `fk_products_brands`
		FOREIGN KEY (brand_id)
        REFERENCES brands (id),
	CONSTRAINT `fk_products_categories`
		FOREIGN KEY (category_id)
        REFERENCES categories (id),
	CONSTRAINT `fk_products_reviews`
		FOREIGN KEY (review_id)
        REFERENCES reviews (id)
);

CREATE TABLE customers (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    phone VARCHAR(30) NOT NULL UNIQUE,
    address VARCHAR(60) NOT NULL,
    discount_card BOOLEAN NOT NULL DEFAULT(FALSE)
);

CREATE TABLE orders (
	id INT PRIMARY KEY AUTO_INCREMENT,
    order_datetime DATETIME NOT NULL,
    customer_id INT NOT NULL,
    CONSTRAINT `fk_orders_customers`
		FOREIGN KEY (customer_id)
        REFERENCES customers (id)
);

CREATE TABLE orders_products (
	order_id INT,
    product_id INT,
    CONSTRAINT `fk_ordersProducts_orders`
		FOREIGN KEY (order_id)
        REFERENCES orders (id),
	CONSTRAINT `fk_ordersProducts_products`
		FOREIGN KEY (product_id)
        REFERENCES products (id)
);

-- p02 Insert
INSERT INTO reviews (content, picture_url, published_at, rating)
	(SELECT
		left(description, 15) AS content,
		reverse(`name`) AS picture_url,
		'2010-10-10' AS published_at,
		round(price / 8, 2) AS rating
	FROM products
	WHERE id >= 5);

-- p03 Update
UPDATE products
SET quantity_in_stock = quantity_in_stock - 5
WHERE quantity_in_stock BETWEEN 60 AND 70;

-- p04 Delete
DELETE c
FROM customers AS c
LEFT JOIN orders AS o
  ON c.id = o.customer_id
WHERE o.id IS NULL;

-- p05 Categories
SELECT 
    id, `name`
FROM
    categories
ORDER BY `name` DESC;

-- p06 Quantity
SELECT 
    id, brand_id, `name`, quantity_in_stock
FROM
    products
WHERE
	price > 1000 AND quantity_in_stock < 30
ORDER BY quantity_in_stock , id;

-- p07 Review
SELECT 
    id, content, rating, picture_url, published_at
FROM
    reviews
WHERE
    content LIKE 'My%'
        AND CHAR_LENGTH(content) > 61
ORDER BY rating DESC;

-- p08 First customers
SELECT
	concat_ws(' ', first_name, last_name) AS 'full_name',
    address,
    o.order_datetime AS 'order_date'
FROM
	customers AS c
LEFT JOIN orders AS o
	ON c.id = o.customer_id
WHERE
	YEAR(order_datetime) <= 2018
ORDER BY full_name DESC;

-- p09 Best categories
SELECT
	count(p.id) AS 'items_count',
    c.`name`,
    sum(quantity_in_stock) AS 'total_quantity'
FROM
	products AS p
JOIN categories AS c
	ON p.category_id = c.id
GROUP BY p.category_id
ORDER BY items_count DESC, total_quantity ASC
LIMIT 5;

-- p10 Extract client cards count
DELIMITER $$
CREATE FUNCTION udf_customer_products_count(`name` VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE products_count INT;
    SET products_count := (
    (SELECT 
		count(p.id) AS 'total_products'
    FROM
		customers AS c
	JOIN orders AS o
      ON c.id = o.customer_id
	JOIN orders_products AS op
      ON o.id = op.order_id
	JOIN products AS p
      ON op.product_id = p.id
	WHERE c.first_name = `name`
	GROUP BY c.id
    ));
    RETURN products_count;
END$$

-- p11 Reduce Price
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50))
BEGIN
	UPDATE 
	products
	SET
		price = price * 0.7
	WHERE 
		review_id IN (
			SELECT id FROM reviews WHERE rating < 4
		) AND 
		category_id IN (
			SELECT id FROM categories WHERE `name` = category_name
	);
END$$
