CREATE TABLE tb_transfer (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             wallet_sender_id UUID,
                             wallet_receiver_id UUID,
                             value DECIMAL(19, 2),
                             FOREIGN KEY (wallet_sender_id) REFERENCES wallet(id),
                             FOREIGN KEY (wallet_receiver_id) REFERENCES wallet(id)
);
