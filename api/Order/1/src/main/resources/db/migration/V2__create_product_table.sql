CREATE TABLE products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  price_id INT,
  FOREIGN KEY (user_id) REFERENCES USERS(id)
);

CREATE TABLE prices(
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT NOT NULL,
  price FLOAT NOT NULL ,
  FOREIGN KEY (product_id) REFERENCES PRODUCTS(id)
);

insert into products(user_id, name) VALUES (1, "test");

insert into prices (product_id, price) values (1, 2.5);

update products SET price_id = 1 WHERE id = 1;