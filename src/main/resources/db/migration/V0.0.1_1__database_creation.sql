CREATE TABLE hookah(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    brand VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    material VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE(5,2) NOT NULL,
    image_binary LONGBLOB NOT NULL,
    height INT NOT NULL,
    information VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE hmd(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    brand VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    material VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE(5,2) NOT NULL,
    image_binary LONGBLOB NOT NULL,
    type VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE bowl(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    brand VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    material VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE(5,2) NOT NULL,
    image_binary LONGBLOB NOT NULL,
    finish ENUM('GLAZED', 'UNGLAZED'),
    for_type VARCHAR(255),
    capacity INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE accessory(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    brand VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    material VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE(5,2) NOT NULL,
    image_binary LONGBLOB NOT NULL,
    information VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE tobacco(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    brand VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    material VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE(5,2) NOT NULL,
    image_binary LONGBLOB NOT NULL,
    quantity INT NOT NULL,
    flavour VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE charcoal(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    brand VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    material VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE(5,2) NOT NULL,
    image_binary LONGBLOB NOT NULL,
    size INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE base(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    brand VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    material VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE(5,2) NOT NULL,
    image_binary LONGBLOB NOT NULL,
    type VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user(
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    account_id INT,
    PRIMARY KEY (id)
);

CREATE TABLE account(
    id INT NOT NULL AUTO_INCREMENT,
    address VARCHAR(255) NOT NULL,
    credit_card_info VARCHAR(255) NOT NULL,
    shipping_info VARCHAR(255) NOT NULL,
    shopping_cart_id INT,
    PRIMARY KEY (id)
);

CREATE TABLE shopping_cart(
    id INT NOT NULL AUTO_INCREMENT,
    product_id INT,
    quantity INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE user
    ADD FOREIGN KEY (account_id) REFERENCES account(id);

ALTER TABLE account
    ADD FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id);