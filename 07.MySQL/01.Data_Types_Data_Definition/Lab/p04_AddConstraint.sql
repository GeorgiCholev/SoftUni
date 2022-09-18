
USE `gamebar`;
ALTER TABLE `products`
ADD CONSTRAINT `fk_id_product`
	FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`);