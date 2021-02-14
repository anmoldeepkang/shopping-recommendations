DROP TABLE IF EXISTS order_line;
DROP TABLE IF EXISTS user_order;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS product;

CREATE TABLE order_line (
  order_line_id varchar(50) NOT NULL,
  order_id varchar(50) NOT NULL,
  product_id varchar(50) NOT NULL,
  rating int(2) DEFAULT NULL
);

--
-- Dumping data for table order_line
--

INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('1', '1', '1', 5);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('10', '3', '4', 4);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('2', '1', '2', 7);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('3', '1', '5', 2);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('4', '1', '6', 9);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('5', '2', '1', 4);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('6', '2', '4', 5);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('7', '2', '6', 2);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('8', '3', '2', 7);
INSERT INTO order_line (order_line_id, order_id, product_id, rating) VALUES('9', '3', '6', 9);

-- --------------------------------------------------------

--
-- Table structure for table product
--

CREATE TABLE product (
  product_id varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  category varchar(50) NOT NULL
);

--
-- Dumping data for table product
--

INSERT INTO product (product_id, name, category) VALUES('1', 'Chair', 'Furniture');
INSERT INTO product (product_id, name, category) VALUES('2', 'Table', 'Furniture');
INSERT INTO product (product_id, name, category) VALUES('3', 'Toyota', 'Automobile');
INSERT INTO product (product_id, name, category) VALUES('4', 'Chrysler', 'Automobile');
INSERT INTO product (product_id, name, category) VALUES('5', 'Laptop', 'Electronics');
INSERT INTO product (product_id, name, category) VALUES('6', 'iPad', 'Electronics');

-- --------------------------------------------------------

--
-- Table structure for table user
--

CREATE TABLE "user" (
  user_id varchar(50) NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(500) NOT NULL,
  name varchar(50) NOT NULL
);

--
-- Dumping data for table user
--

INSERT INTO user (user_id, username, password, name) VALUES('1', 'anmoldeep', '$2a$10$XCa6Am4RxPbYD4g.gej8Mud4GnJGDOmoLTwEGgFfriEXx9TvEkdU6', 'Anmoldeep');
INSERT INTO user (user_id, username, password, name) VALUES('2', 'jack', 'anmoldeep123', 'Jack');
INSERT INTO user (user_id, username, password, name) VALUES('3', 'tom', 'anmoldeep123', 'Tom');

-- --------------------------------------------------------

--
-- Table structure for table user_order
--

CREATE TABLE user_order (
  order_id varchar(50) NOT NULL,
  user_id varchar(50) NOT NULL,
  order_time timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);

--
-- Dumping data for table user_order
--

INSERT INTO user_order (order_id, user_id, order_time) VALUES('1', '1', '2021-02-12 20:02:43');
INSERT INTO user_order (order_id, user_id, order_time) VALUES('2', '2', '2021-02-12 20:02:43');
INSERT INTO user_order (order_id, user_id, order_time) VALUES('3', '3', '2021-02-12 20:02:43');

-- --------------------------------------------------------

--
-- Table structure for table wishlist
--

CREATE TABLE wishlist (
  id varchar(50) NOT NULL,
  user_id varchar(50) NOT NULL,
  product_id varchar(50) NOT NULL
);

--
-- Dumping data for table wishlist
--

INSERT INTO wishlist (id, user_id, product_id) VALUES('1', '1', '3');
INSERT INTO wishlist (id, user_id, product_id) VALUES('2', '3', '6');

--
-- Indexes for dumped tables
--

--
-- Indexes for table order_line
--
ALTER TABLE order_line
  ADD PRIMARY KEY (order_line_id);

--
-- Indexes for table product
--
ALTER TABLE product
  ADD PRIMARY KEY (product_id);

--
-- Indexes for table user
--
ALTER TABLE "user"
  ADD PRIMARY KEY (user_id);

--
-- Indexes for table user_order
--
ALTER TABLE user_order
  ADD PRIMARY KEY (order_id);

--
-- Indexes for table wishlist
--
ALTER TABLE wishlist
  ADD PRIMARY KEY (id);

--
-- Constraints for dumped tables
--

--
-- Constraints for table order_line
--
ALTER TABLE order_line
  ADD CONSTRAINT order_line_ibfk_1 FOREIGN KEY (order_id) REFERENCES user_order (order_id);
ALTER TABLE order_line
  ADD CONSTRAINT order_line_ibfk_2 FOREIGN KEY (product_id) REFERENCES product (product_id);

--
-- Constraints for table user_order
--
ALTER TABLE user_order
  ADD CONSTRAINT user_order_ibfk_1 FOREIGN KEY (user_id) REFERENCES user (user_id);

--
-- Constraints for table wishlist
--
ALTER TABLE wishlist
  ADD CONSTRAINT wishlist_ibfk_1 FOREIGN KEY (user_id) REFERENCES user (user_id);
ALTER TABLE wishlist
  ADD CONSTRAINT wishlist_ibfk_2 FOREIGN KEY (product_id) REFERENCES product (product_id);
COMMIT;

