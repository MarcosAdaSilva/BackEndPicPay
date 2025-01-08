CREATE TABLE tb_wallet (
    id SERIAL PRIMARY KEY, -- Chave primária com autoincremento
    full_name VARCHAR(255) NOT NULL, -- Nome completo do usuário
    cpf_cnpj VARCHAR(20) UNIQUE NOT NULL, -- CPF ou CNPJ único e obrigatório
    email VARCHAR(255) UNIQUE NOT NULL, -- E-mail único e obrigatório
    password VARCHAR(255) NOT NULL, -- Senha do usuário
    balance NUMERIC(15, 2) DEFAULT 0.00, -- Saldo com valor padrão de 0.00
    wallet_type_id INTEGER NOT NULL, -- Chave estrangeira para tb_wallet_type

    -- Definição da chave estrangeira
    CONSTRAINT fk_wallet_type FOREIGN KEY (wallet_type_id)
        REFERENCES tb_wallet_type (id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);