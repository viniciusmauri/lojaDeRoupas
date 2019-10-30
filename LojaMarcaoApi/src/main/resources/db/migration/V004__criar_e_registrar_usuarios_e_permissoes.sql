CREATE TABLE IF NOT EXISTS usuario(
    cod BIGINT(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS  permissao(
    cod BIGINT(20) PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS  usuario_permissao(
    cod_usuario BIGINT(20) NOT NULL,
    cod_permissao BIGINT(20) NOT NULL,
    PRIMARY KEY (cod_usuario, cod_permissao),
    FOREIGN KEY (cod_usuario) REFERENCES usuario(cod),
    FOREIGN KEY (cod_permissao) REFERENCES permissao(cod)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissao (cod, descricao) values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (cod, descricao) values (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (cod, descricao) values (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (cod, descricao) values (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao (cod, descricao) values (5, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (cod, descricao) values (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (cod, descricao) values (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (cod, descricao) values (8, 'ROLE_PESQUISAR_LANCAMENTO');

INSERT INTO usuario (cod, nome, email, senha) values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (cod, nome, email, senha) values (2, 'Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

-- admin
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 1);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 2);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 3);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 4);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 5);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 6);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 7);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (1, 8);

-- maria
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (2, 2);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (2, 5);
INSERT INTO usuario_permissao (cod_usuario, cod_permissao) values (2, 8);