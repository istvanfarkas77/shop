package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
class AuditJmsProducerService {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Destination destination;

    public void sendAuditEvent(final String event) {
        this.jmsMessagingTemplate.convertAndSend(destination, event);
    }
}
