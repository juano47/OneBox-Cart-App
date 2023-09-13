INSERT IGNORE INTO product (id, name, description, price)
VALUES (1, 'Asado',
        'Carne asada con papas fritas y ensalada de lechuga y tomate con vinagreta de aceite de oliva y limón', 10.0),
       (2, 'Sushi with Salmon and Avocado', 'Finest fish and veggies', 22.99),
       (3, 'Schnitzel with Fries and Salad', 'A german specialty!', 16.5),
       (4, 'Barbecue Burger with Bacon and Cheese', 'American, raw, meaty', 12.99),
       (5, 'Green Bowl with Chicken and Avocado', 'Healthy...and green...', 18.99);

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
