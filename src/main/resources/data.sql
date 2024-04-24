-- Adding users to the database --
INSERT INTO users (email, username, password, role)
VALUES ('john@example.com', 'john_doe', '$2a$10$WqKI50m01VAaZT5rygNIYOBG0W4PTewnKd5e3ov1BmhE2N6GC1Aba', 'ADMIN');

INSERT INTO users (email, username, password, role)
VALUES ('jane@example.com', 'jane_smith', '$2a$10$.44fAqygjJPvajSpM1dBm.lDK7fKecMpCjRHM3RL7aKcFj3VmyyI6', 'USER');

-- Adding products to the database --
INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Air Jordan 1 Retro High OG', 'Nike', 170.00, 10);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Yeezy Boost 350 V2', 'Adidas', 220.00, 8);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Air Force 1 Low', 'Nike', 90.00, 15);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Ultra Boost', 'Adidas', 180.00, 12);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Chuck Taylor All Star', 'Converse', 55.00, 20);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Old Skool', 'Vans', 60.00, 18);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Classic Leather', 'Reebok', 75.00, 15);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Gel-Kayano 27', 'ASICS', 160.00, 10);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Superstar', 'Adidas', 80.00, 22);

INSERT INTO products (product_name, brand_name, price, quantity_available)
VALUES ('Air Max 90', 'Nike', 120.00, 14);
