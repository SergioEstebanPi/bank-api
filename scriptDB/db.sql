-- ---- 0. Creacion DB
-- CREATE DATABASE IF NOT EXISTS devsu;

DROP DATABASE devsu;
CREATE DATABASE devsu; 
USE devsu;

-- ---- 1. LIMPIEZA TABLAS ------

-- DROP TABLE IF EXISTS persona;
-- DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;


-- ---- 2. CREACION TABLAS ------

-- persona
/*
CREATE TABLE persona (
  id INT(10) NOT NULL AUTO_INCREMENT,
  dni INT(10) NOT NULL,
  nombre VARCHAR(80),
  genero VARCHAR(80),
  nacimiento INT(10) NOT NULL,
  direccion VARCHAR(200),
  telefono VARCHAR(80),
  PRIMARY KEY (id)
);
*/

-- cliente
/*
CREATE TABLE cliente (
  id INT(10) NOT NULL AUTO_INCREMENT,
  contrasena VARCHAR(80),
  estado INT(1),
  PRIMARY KEY (id)
);
*/

-- cliente2
CREATE TABLE IF NOT EXISTS cliente (
  id INT(10) NOT NULL AUTO_INCREMENT,
  dni INT(10) NOT NULL,
  nombre VARCHAR(80),
  genero CHAR(1),
  nacimiento DATE,
  direccion VARCHAR(200),
  telefono VARCHAR(80),
  contrasena VARCHAR(80),
  estado INT(1),
  PRIMARY KEY (id)
);

-- VALUES (1, '0000000001', 'Jose Lema', 'M', '1990-08-10', 'Otavalo sn y principal', '098254785', '1234', 1);

-- cuenta
CREATE TABLE IF NOT EXISTS cuenta (
  id INT(10) NOT NULL AUTO_INCREMENT,
  id_cliente INT(10) NOT NULL,
  numero VARCHAR(80),
  tipo VARCHAR(80),
  saldo_inicial DECIMAL(10, 2) NOT NULL DEFAULT 0,
  saldo DECIMAL(10, 2) NOT NULL DEFAULT 0,
  estado INT(1),
  PRIMARY KEY (id),
  FOREIGN KEY (id_cliente) REFERENCES cliente (id) ON DELETE CASCADE
);

-- movimiento
CREATE TABLE IF NOT EXISTS movimiento (
  id INT(10) NOT NULL AUTO_INCREMENT,
  id_cuenta INT(10) NOT NULL,
  fecha DATETIME,
  tipo VARCHAR(80),
  valor DECIMAL(10, 2) NOT NULL DEFAULT 0,
  saldo DECIMAL(10, 2) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (id_cuenta) REFERENCES cuenta (id) ON DELETE CASCADE
);


-- ---- 3. INSERTS ------

-- CASOS DE USO
-- 1. Creacion de cliente

INSERT INTO cliente (id, dni, nombre, genero, nacimiento, direccion, telefono, contrasena, estado)
VALUES (1, '0000000001', 'Jose Lema', 'M', '1990-08-10', 'Otavalo sn y principal', '098254785', '1234', 1);


INSERT INTO cliente (id, dni, nombre, genero, nacimiento, direccion, telefono, contrasena, estado)
VALUES (2, '0000000002', 'Marianela Montalvo', 'F', '1990-08-10', 'Amazonas y NNUU', '097548965', '5678', 1);

INSERT INTO cliente (id, dni, nombre, genero, nacimiento, direccion, telefono, contrasena, estado)
VALUES (3, '0000000003', 'Juan Osorio', 'M', '1990-08-10', '13 junio y Equinoccial', '098874587', '1234', 1);

-- 2. Creacion de Cuenta de Usuario

INSERT INTO cuenta (id, id_cliente, numero, tipo, saldo_inicial, saldo, estado)
VALUES (1, 1, '478758', 'Ahorro', 2000, 2000, 1);

INSERT INTO cuenta (id, id_cliente, numero, tipo, saldo_inicial, saldo, estado)
VALUES (2, 2, '225487', 'Corriente', 100, 100, 1);

INSERT INTO cuenta (id, id_cliente, numero, tipo, saldo_inicial, saldo, estado)
VALUES (3, 3, '495878', 'Ahorro', 0, 0, 1);

INSERT INTO cuenta (id, id_cliente, numero, tipo, saldo_inicial, saldo, estado)
VALUES (4, 2, '496825', 'Ahorro', 540, 540, 1);

-- 3. Creacion una nueva Cuenta Corriente para Jose Lema

INSERT INTO cuenta (id, id_cliente, numero, tipo, saldo_inicial, saldo, estado)
VALUES (5, 1, '585545', 'Corriente', 1000, 1000, 1);

-- 4. Realizar los siguientes movimientos

-- 4.1
INSERT INTO movimiento (id, id_cuenta, fecha, tipo, valor, saldo)
VALUES (1, 1, '2023-11-06 22:18:40', 'Retiro de 575', -575, 1425);

UPDATE cuenta
SET saldo = 1425
WHERE id = 1
AND id_cliente = 1;

-- 4.2
INSERT INTO movimiento (id, id_cuenta, fecha, tipo, valor, saldo)
VALUES (2, 2, '2023-11-06 22:18:40', 'Deposito de 600', 600, 700);

UPDATE cuenta
SET saldo = 700
WHERE id = 2
AND id_cliente = 2;

-- 4.3
INSERT INTO movimiento (id, id_cuenta, fecha, tipo, valor, saldo)
VALUES (3, 3, '2023-11-06 22:18:40', 'Deposito de 150', 150, 150);

UPDATE cuenta
SET saldo = 150
WHERE id = 3
AND id_cliente = 3;

-- 4.4
INSERT INTO movimiento (id, id_cuenta, fecha, tipo, valor, saldo)
VALUES ( 4, 4, '2023-11-06 22:18:40', 'Retiro de 540', -540, 0);

UPDATE cuenta
SET saldo = 0
WHERE id = 4
AND id_cliente = 4;


--