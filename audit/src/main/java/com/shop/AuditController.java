package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class AuditController implements AuditResource {

    AuditJmsProducerService auditJmsProducerService;
    AuditService auditService;

    @Autowired
    public AuditController(AuditJmsProducerService auditJmsProducerService, AuditService auditService) {
        this.auditJmsProducerService = auditJmsProducerService;
        this.auditService = auditService;
    }

    @Override
    public List<AuditDTO> findAll() {
        return auditService.findAll();
    }

    @Override
    public void save(@PathVariable("event") String auditEvent) {
        auditJmsProducerService.sendAuditEvent(auditEvent);
    }
}
