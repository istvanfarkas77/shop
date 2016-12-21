package com.shop;

import org.apache.commons.collections.IteratorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class AuditService {

    @Autowired
    AuditRepository auditRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<AuditDTO> findAll() {
        List<Audit> audits = IteratorUtils.toList(auditRepository.findAll().iterator());

        return audits.stream().map(pojo -> modelMapper.map(pojo, AuditDTO.class)).collect(Collectors.toList());
    }

    public Audit save(Audit audit) {
        return auditRepository.save(audit);
    }
}
