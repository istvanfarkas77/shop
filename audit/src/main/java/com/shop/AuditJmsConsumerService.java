package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
class AuditJmsConsumerService {

    @Autowired
    AuditService auditService;

    @JmsListener(destination = "sample.queue")
    public void onMessage(String message) {
        auditService.save(new Audit(message));
    }
}
