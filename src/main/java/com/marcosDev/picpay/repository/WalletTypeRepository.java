package com.marcosDev.picpay.repository;

import com.marcosDev.picpay.domain.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
