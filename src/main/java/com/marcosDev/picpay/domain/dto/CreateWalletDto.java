package com.marcosDev.picpay.domain.dto;

import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.domain.entity.WalletTypeEnum;

public record CreateWalletDto(String fullName,
                              String cpfCnpj,
                              String email,
                              String password,
                              WalletTypeEnum walletTypeEnum) {
    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletTypeEnum.get()

        );
    }
}
