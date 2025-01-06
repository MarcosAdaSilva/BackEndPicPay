package com.marcosDev.picpay.service;

import com.marcosDev.picpay.domain.dto.CreateWalletDto;
import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.infra.WalletDataAlreadyExistsException;
import com.marcosDev.picpay.repository.WalletRepository;
import com.marcosDev.picpay.repository.WalletTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public Wallet createWallet(CreateWalletDto dto) {
        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }
        return walletRepository.save(dto.toWallet());
    }
}
