package com.marcosDev.picpay.infra;

import org.springframework.http.ProblemDetail;

public class walletNotFoundExcepiton extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {
        return super.toProblemDetail();
    }
}
