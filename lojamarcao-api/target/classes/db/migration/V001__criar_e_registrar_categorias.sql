CREATE TABLE categoria(
    cod BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO categoria (nome) VALUES ('Blusas');
INSERT INTO categoria (nome) VALUES ('Calcas');
INSERT INTO categoria (nome) VALUES ('Shorts');
INSERT INTO categoria (nome) VALUES ('Saias');
INSERT INTO categoria (nome) VALUES ('Lingeries');
INSERT INTO categoria (nome) VALUES ('Camisas');
INSERT INTO categoria (nome) VALUES ('Blazers e Ternos');
INSERT INTO categoria (nome) VALUES ('Biquini');
INSERT INTO categoria (nome) VALUES ('Outros');

