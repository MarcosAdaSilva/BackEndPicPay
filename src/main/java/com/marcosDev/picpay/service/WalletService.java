package com.marcosDev.picpay.service;

import com.marcosDev.picpay.domain.dto.CreateWalletDto;
import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.exception.WalletDataAlreadyExistsException;
import com.marcosDev.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }

    @Transactional
    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}