ALTER TABLE `shopping_cart`
    DROP COLUMN `product_id`;
ALTER TABLE `shopping_cart`
    DROP COLUMN `quantity`;
ALTER TABLE `shopping_cart`
    ADD product_id INT;