create database par2Ques10;

use par2Ques10;

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL
);

INSERT INTO products (product_name) VALUES ('Laptop');
INSERT INTO products (product_name) VALUES ('Smartphone');
INSERT INTO products (product_name) VALUES ('Tablet');
INSERT INTO products (product_name) VALUES ('Monitor');
INSERT INTO products (product_name) VALUES ('Keyboard');

select *from products;