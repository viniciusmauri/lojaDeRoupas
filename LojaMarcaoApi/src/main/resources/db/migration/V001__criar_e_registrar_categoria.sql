CREATE TABLE IF NOT EXISTS categoria
(
    cod  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;


INSERT INTO categoria (nome) VALUES ('Camisa');
INSERT INTO categoria (nome) VALUES ('Blusa');
INSERT INTO categoria (nome) VALUES ('Short');
INSERT INTO categoria (nome) VALUES ('Saia');
INSERT INTO categoria (nome) VALUES ('Calça');
INSERT INTO categoria (nome) VALUES ('Lingeries');
INSERT INTO categoria (nome) VALUES ('Biquini');
INSERT INTO categoria (nome) VALUES ('Outros');

