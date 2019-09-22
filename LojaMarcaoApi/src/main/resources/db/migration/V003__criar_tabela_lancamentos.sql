CREATE TABLE IF NOT EXISTS lancamento (
                            cod_lancamento BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                            descricao VARCHAR(50) NOT NULL,
                            data_vencimento DATE NOT NULL,
                            data_pagamento DATE,
                            valor DECIMAL(10,2) NOT NULL,
                            observacao VARCHAR(100),
                            tipo VARCHAR(20) NOT NULL,
                            cod_categoria BIGINT(20) NOT NULL,
                            cod_pessoa BIGINT(20) NOT NULL,
                            FOREIGN KEY (cod_categoria) REFERENCES categoria(cod),
                            FOREIGN KEY (cod_pessoa) REFERENCES pessoa(cod_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;