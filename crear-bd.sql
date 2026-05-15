DROP DATABASE IF EXISTS minitienda;
CREATE DATABASE minitienda;

\c minitienda

DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    correo       VARCHAR(100) PRIMARY KEY,
    password     VARCHAR(100) NOT NULL,
    tipo_tarjeta VARCHAR(50),
    num_tarjeta  VARCHAR(50)
);

CREATE TABLE pedidos (
    id              SERIAL PRIMARY KEY,
    correo_usuario  VARCHAR(100) NOT NULL,
    importe         DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_pedidos_usuarios
        FOREIGN KEY (correo_usuario)
        REFERENCES usuarios(correo)
        ON DELETE RESTRICT
);
