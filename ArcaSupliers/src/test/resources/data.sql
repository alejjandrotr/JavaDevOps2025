-- data.sql

-- TRUNCATE statements are database-specific and might not be supported by all databases.
-- Using DELETE FROM instead for better compatibility.
-- Also, TRUNCATE requires specific privileges in some databases.
DELETE FROM `store`;
DELETE FROM `address`;
DELETE FROM `supplier`;  -- Add truncate for supplier table

-- The ID is auto-incremented, so i dont need to set.
INSERT INTO `address` (`id`, `city`, `country`, `street`) VALUES (500, 'Santiago de Chile', 'Chile', 'Av 25');

INSERT INTO `supplier` ( `id`, `address`, `description`, `email`, `first_purchase`, `is_active`, `name`, `sitio_web`, `telefono` ) VALUES ( 500, 'X', 'Descripcion', 'test@test.com', NULL, 1, 'Proveedor', 'www.proveedor.com', '04154551' );

-- Insert data into the store table.
INSERT INTO `store` (`id`,`name`, `supplier_id`, `address_id`) VALUES (500, 'Tienda Ejemplo', 500, 500);  -- Corrected supplier ID, set the foreign key to 1.
