ALTER TABLE shopping_cart
    ADD FOREIGN KEY (product_id) REFERENCES product(id);