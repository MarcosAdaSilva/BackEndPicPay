package com.marcosDev.picpay.rest;

import com.marcosDev.picpay.domain.dto.CreateWalletDto;
import com.marcosDev.picpay.domain.entity.Wallet;
import com.marcosDev.picpay.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class WalletController {
    private WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletDto dto){
        var wallet = walletService.createWallet(dto);
        return ResponseEntity.ok(wallet);
    }
}
