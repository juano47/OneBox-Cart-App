INSERT IGNORE INTO product (id, name, description, price)
VALUES (1, 'Smartphone XYZ-200', 'Potente smartphone con cámara de alta resolución.', 599.99),
       (2, 'Laptop Ultrabook Pro', 'Laptop ligera y potente con pantalla táctil.', 1099.99),
       (3, 'Tablet NexusTab 10', 'Tablet con pantalla HD y gran capacidad de almacenamiento.', 349.99),
       (4, 'Auriculares SoundWave', 'Auriculares inalámbricos con cancelación de ruido.', 199.99),
       (5, 'Smart TV VisionPlus 55"', 'Televisor inteligente con resolución 4K y aplicaciones integradas.', 799.99),
       (6, 'Cámara Fotográfica EOS-500', 'Cámara DSLR con lentes intercambiables y alta calidad de imagen.', 899.99),
       (7, 'Altavoz Bluetooth BassBoost', 'Altavoz portátil con potente sonido y bajos profundos.', 79.99),
       (8, 'Monitor Curvo Gamer 27"', 'Monitor curvo diseñado para una experiencia de juego inmersiva.', 499.99),
       (9, 'Robot Aspiradora SmartClean', 'Robot inteligente que limpia automáticamente tu hogar.', 249.99),
       (10, 'Smartwatch PulseTrack', 'Reloj inteligente con seguimiento de actividad y notificaciones.', 149.99);

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
