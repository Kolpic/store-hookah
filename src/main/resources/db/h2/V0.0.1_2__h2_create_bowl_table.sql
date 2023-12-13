CREATE TABLE bowl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    weight INT,
    brand VARCHAR(255),
    origin VARCHAR(255),
    material VARCHAR(255),
    color VARCHAR(255),
    price DOUBLE,
    image_binary LONGBLOB,
    finish VARCHAR(255),
    for_type VARCHAR(255),
    capacity INT
);