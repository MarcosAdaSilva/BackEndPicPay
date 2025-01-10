CREATE TABLE tb_wallet (
                           id BIGSERIAL PRIMARY KEY,
                           full_name VARCHAR(255) NOT NULL,
                           cpf_cnpj VARCHAR(20) UNIQUE NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL,
                           password VARCHAR(255) NOT NULL,
                           balance NUMERIC(15, 2) DEFAULT 0.00,
                           wallet_type_id BIGINT NOT NULL,

                           CONSTRAINT fk_wallet_type FOREIGN KEY (wallet_type_id)
                               REFERENCES tb_wallet_type (id)
                               ON DELETE RESTRICT ON UPDATE CASCADE
);