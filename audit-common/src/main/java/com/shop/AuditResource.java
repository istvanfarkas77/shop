package com.shop;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface AuditResource {

    @RequestMapping("/audit")
    List<AuditDTO> findAll();

    @RequestMapping(value = "/audit/{event}", method = RequestMethod.POST)
    void save(@PathVariable("event") String auditEvent);
}
