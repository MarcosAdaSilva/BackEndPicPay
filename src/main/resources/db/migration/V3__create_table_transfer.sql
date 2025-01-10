CREATE TABLE tb_transfer (
                             id BIGSERIAL PRIMARY KEY,
                             wallet_sender_id BIGINT NOT NULL,
                             wallet_receiver_id BIGINT NOT NULL,
                             value NUMERIC(15, 2) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_wallet_sender FOREIGN KEY (wallet_sender_id)
                                 REFERENCES tb_wallet (id)
                                 ON DELETE RESTRICT ON UPDATE CASCADE,

                             CONSTRAINT fk_wallet_receiver FOREIGN KEY (wallet_receiver_id)
                                 REFERENCES tb_wallet (id)
                                 ON DELETE RESTRICT ON UPDATE CASCADE
);