package com.marcosDev.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class walletNotFoundExcepiton extends PicPayException {

    private Long walletId;

    public walletNotFoundExcepiton(Long walletid) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet no found");
        pb.setDetail("There is not wallet with id " + walletId + ".");

        return pb;

    }
}
