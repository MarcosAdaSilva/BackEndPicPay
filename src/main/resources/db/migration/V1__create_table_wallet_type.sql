CREATE TABLE tb_wallet_type (
                                id BIGSERIAL PRIMARY KEY,
                                description VARCHAR(255) NOT NULL
);

-- Inserção dos dados iniciais na tabela tb_wallet_type
INSERT INTO tb_wallet_type (description) VALUES ('user');
INSERT INTO tb_wallet_type (description) VALUES ('merchant');