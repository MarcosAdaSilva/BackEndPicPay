package com.marcosDev.picpay.domain.entity;

import lombok.Getter;


@Getter
public enum WalletTypeEnum {

    USER(1L, "user"),
    MERCHANT(2L, "merchant");
    WalletTypeEnum(final Long id, final String description) {
        this.id = id;
        this.description = description;
    }
    private final Long id;
    private final String description;

    public WalletType get() {
        return new WalletType(id, description);
    }


}
