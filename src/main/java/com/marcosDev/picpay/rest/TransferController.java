package com.marcosDev.picpay.rest;

import com.marcosDev.picpay.domain.dto.TransferDto;
import com.marcosDev.picpay.domain.entity.Transfer;
import com.marcosDev.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> trasnfer(@RequestBody  @Valid TransferDto dto) {
        var resp = transferService.transfer(dto);

        return ResponseEntity.ok(resp);

    }
}
