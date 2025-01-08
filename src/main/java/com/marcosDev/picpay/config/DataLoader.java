package com.marcosDev.picpay.config;

import com.marcosDev.picpay.domain.entity.WalletType;
import com.marcosDev.picpay.repository.WalletTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @PostConstruct
    public void loadData() {
        if (walletTypeRepository.count() == 0) {
            walletTypeRepository.save(WalletType.Enum.USER.get());
            walletTypeRepository.save(WalletType.Enum.MERCHANT.get());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values())
                .forEach(walletType -> walletTypeRepository.save(walletType.get()));
    }
}