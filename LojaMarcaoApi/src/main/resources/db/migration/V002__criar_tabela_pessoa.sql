CREATE TABLE IF NOT EXISTS pessoa
(
    cod_pessoa BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nomePessoa VARCHAR(40) NOT NULL,
    ativo BOOLEAN,
    logradouro VARCHAR (30),
    numero VARCHAR (30),
    complemento VARCHAR (30),
    bairro VARCHAR (30),
    cep VARCHAR (30),
    cidade VARCHAR (30),
    estado VARCHAR (30),
    telefone VARCHAR (30) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;