INSERT IGNORE INTO restaurant (id, name)
VALUES (1, 'Restaurante 1'),
       (2, 'Restaurante 2'),
       (3, 'Restaurante 3'),
       (4, 'Restaurante 4'),
       (5, 'Restaurante 5');

INSERT IGNORE INTO product (id, name, description, price, restaurant_id)
VALUES (1, 'Asado',
        'Carne asada con papas fritas y ensalada de lechuga y tomate con vinagreta de aceite de oliva y limón', 10.0,
        1),
       (2, 'Sushi with Salmon and Avocado', 'Finest fish and veggies', 22.99, 1),
       (3, 'Schnitzel with Fries and Salad', 'A german specialty!', 16.5, 1),
       (4, 'Barbecue Burger with Bacon and Cheese', 'American, raw, meaty', 12.99, 1),
       (5, 'Green Bowl with Chicken and Avocado', 'Healthy...and green...', 18.99, 1),
       (6, 'Pasta Alfredo', 'Deliciosa pasta con salsa Alfredo cremosa', 14.99, 2),
       (7, 'Pizza Pepperoni', 'Pizza con pepperoni y queso derretido', 11.50, 2),
       (8, 'Ensalada César', 'Ensalada fresca con pollo a la parrilla y aderezo César', 9.99, 2),
       (9, 'Tacos de Carnitas', 'Tacos mexicanos rellenos de carnitas y guacamole', 13.75, 2),
       (10, 'Sopa de Tomate', 'Sopa caliente de tomate con crutones', 7.95, 2),
       (11, 'Rolls de Tempura', 'Rolls de sushi con tempura crujiente', 15.25, 3),
       (12, 'Pollo Teriyaki', 'Pollo a la parrilla con salsa teriyaki', 16.99, 3),
       (13, 'Tiramisú', 'Postre italiano de café y mascarpone', 8.50, 3),
       (14, 'Hamburguesa Vegetariana', 'Hamburguesa vegetariana con queso y aguacate', 10.99, 3),
       (15, 'Filete de Salmón a la Parrilla', 'Filete de salmón fresco a la parrilla', 19.50, 3);



INSERT IGNORE INTO customer (id, firstname, lastname, email, phone)
VALUES (1, 'Ana', 'García', 'ana@example.com', '+34 612345678'),
       (2, 'Luis', 'Martínez', 'luis@example.com', '+34 678901234'),
       (3, 'Elena', 'Fernández', 'elena@example.com', '+34 645678901'),
       (4, 'Pablo', 'López', 'pablo@example.com', '+34 612345612'),
       (5, 'María', 'Ramírez', 'maria@example.com', '+34 612345321');

INSERT IGNORE INTO customer_address (id, street, city, zip_code, country, customer_id)
VALUES (1, 'Calle Gran Vía 123', 'Madrid', '28001', 'España', 1),
       (2, 'Avenida Diagonal 456', 'Barcelona', '08029', 'España', 1),
       (3, 'Calle del Prado 789', 'Sevilla', '41001', 'España', 2),
       (4, 'Calle de Serrano 234', 'Madrid', '28006', 'España', 3),
       (5, 'Plaza Cataluña 1', 'Barcelona', '08002', 'España', 3),
       (6, 'Calle Triana 45', 'Sevilla', '41010', 'España', 4);
