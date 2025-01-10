package com.marcosDev.picpay.domain.dto;

import com.marcosDev.picpay.domain.entity.Wallet;
import java.math.BigDecimal;

public record WalletResponseDto(
        Long id,
        String fullName,
        String cpfCnpj,
        String email,
        BigDecimal balance,
        String walletType,
        boolean transferAllowed
) {
    public static WalletResponseDto fromEntity(Wallet wallet) {
        return new WalletResponseDto(
                wallet.getId(),
                wallet.getFullName(),
                wallet.getCpfCnpj(),
                wallet.getEmail(),
                wallet.getBalance(),
                wallet.getWalletType().getDescription(),
                wallet.isTransferAllowedForWalletType()
        );
    }
}