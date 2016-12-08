CREATE TABLE orders(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL ,
  total_price FLOAT NOT NULL,
  time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES USERS(id)
);

CREATE TABLE orderItems(
  id INT PRIMARY KEY AUTO_INCREMENT,
  order_id INT NOT NULL ,
  product_id INT NOT NULL ,
  price_id INT NOT NULL ,
  quantity INT NOT NULL ,
  FOREIGN KEY (order_id) REFERENCES ORDERS(id),
  FOREIGN KEY (product_id) REFERENCES PRODUCTS(id),
  FOREIGN KEY (price_id) REFERENCES PRICES(id)
);

INSERT INTO orders(user_id, total_price) VALUES (1, 5);
INSERT INTO orderItems(order_id, product_id, price_id, quantity) VALUES (1, 1, 1, 2);