package com.marcosDev.picpay.rest;

import com.marcosDev.picpay.domain.dto.CreateWalletDto;
import com.marcosDev.picpay.domain.dto.WalletResponseDto;
import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.service.WalletService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto) {
        logger.info("...Rest para criar um novo wallet na tabela...");
        var wallet = walletService.createWallet(dto);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/wallets")
    public ResponseEntity<List<WalletResponseDto>> getAllWallets() {
        logger.info("...Rest para buscar todas as wallets...");
        var wallets = walletService.findAllWallets()
                .stream()
                .map(WalletResponseDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(wallets);
    }
}