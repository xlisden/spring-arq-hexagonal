INSERT INTO products (id, name, description, price, image, created_at)
VALUES (1, 'Product 1', 'Description 1', 199.99, 'image.png', CURRENT_TIMESTAMP);

INSERT INTO users (id, email, firstname, lastname, password, role)
VALUES (2,'descartable@wos.com','descartable','vulnerable','$2a$10$Sirv3UQDrREZ9yur68Y1POCBsYA2BbQjgU2QDJ5DpTodpPummNNsO','ADMIN')