package com.marcosDev.picpay.service;

import com.marcosDev.picpay.domain.dto.TransferDto;
import com.marcosDev.picpay.domain.entity.Transfer;
import com.marcosDev.picpay.repository.TransferRepository;
import com.marcosDev.picpay.repository.WalletRepository;
import com.marcosDev.picpay.repository.WalletTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;


    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    public Transfer transfer(TransferDto dto) {

        walletRepository.findById(dto.payer())
                .orElseThrow(-> new walletNotFoundExcepiton())
        return null;
    }
}
