CREATE TABLE canchas (
    id_cancha BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    id_recinto BIGINT NOT NULL,
    precio_hora DOUBLE NOT NULL
);