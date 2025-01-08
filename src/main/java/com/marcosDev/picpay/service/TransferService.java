package com.marcosDev.picpay.service;

import com.marcosDev.picpay.domain.dto.TransferDto;
import com.marcosDev.picpay.domain.entity.Transfer;
import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.infra.InsufficientBalanceException;
import com.marcosDev.picpay.infra.TransferNotAllowedForWalletTypeException;
import com.marcosDev.picpay.infra.TransferNotAuthorizedException;
import com.marcosDev.picpay.infra.walletNotFoundExcepiton;
import com.marcosDev.picpay.repository.TransferRepository;
import com.marcosDev.picpay.repository.WalletRepository;
import com.marcosDev.picpay.repository.WalletTypeRepository;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;

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

    public Transfer transfer(TransferDto trasferdto) {

       var sender =  walletRepository.findById(trasferdto.payer())
                .orElseThrow(() -> new walletNotFoundExcepiton(trasferdto.payer()));

        var receiver =  walletRepository.findById(trasferdto.payee())
                .orElseThrow(() -> new walletNotFoundExcepiton(trasferdto.payee()));

        validateTransfer(trasferdto, sender);

        sender.debit(trasferdto.value());
        receiver.credit(trasferdto.value());



        return null;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }
        if (!sender.isBalancerEqualOrGreatherThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }
        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
