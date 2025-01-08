package com.marcosDev.picpay.service;

import com.marcosDev.picpay.domain.dto.TransferDto;
import com.marcosDev.picpay.domain.entity.Transfer;
import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.exception.InsufficientBalanceException;
import com.marcosDev.picpay.exception.TransferNotAllowedForWalletTypeException;
import com.marcosDev.picpay.exception.TransferNotAuthorizedException;
import com.marcosDev.picpay.exception.walletNotFoundExcepiton;
import com.marcosDev.picpay.repository.TransferRepository;
import com.marcosDev.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
    @Transactional
    public Transfer transfer(TransferDto trasferdto) {

       var sender =  walletRepository.findById(trasferdto.payer())
                .orElseThrow(() -> new walletNotFoundExcepiton(trasferdto.payer()));

        var receiver =  walletRepository.findById(trasferdto.payee())
                .orElseThrow(() -> new walletNotFoundExcepiton(trasferdto.payee()));

        validateTransfer(trasferdto, sender);

        sender.debit(trasferdto.value());
        receiver.credit(trasferdto.value());

        var transfer = new Transfer(sender, receiver, trasferdto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult)); //Notificação de realização de tranferencia.


        return transferResult;
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
