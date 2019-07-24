REPLACE INTO `role` VALUES (1,'ADMIN');


-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, name, last_name)
VALUES (1, '$2a$10$lQ5Pdyu9oYMjaNpywd3ZQunJlNgphzqyt4shlji24Y8cmaaCC0fu.', 'ammar@hotmail.com', 'Ammar', 'Mohrat');

