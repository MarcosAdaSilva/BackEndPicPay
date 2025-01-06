package com.marcosDev.picpay.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_wallet-type")
public class WalletType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    public WalletType() {
    }
    public WalletType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

}
