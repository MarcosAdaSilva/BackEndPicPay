package com.marcosDev.picpay.service;

import com.marcosDev.picpay.domain.client.NotificationClient;
import com.marcosDev.picpay.domain.entity.Transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer) {

        try {
            logger.info("Sending notification...");
            notificationClient.sendNotification(transfer);

            var resp = notificationClient.sendNotification(transfer);

            if (resp.getStatusCode().isError()) {
                logger.error("Error white sending notification, status code is not ok");
            }
        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }
}
