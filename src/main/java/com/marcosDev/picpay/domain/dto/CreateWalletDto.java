package com.marcosDev.picpay.domain.dto;

import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.domain.entity.WalletType;

public record CreateWalletDto(String fullName,
                              String cpfCnpj,
                              String email,
                              String password,
                              WalletType.Enum walletType) {
    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()

        );
    }
}
