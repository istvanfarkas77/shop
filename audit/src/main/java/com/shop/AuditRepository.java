package com.shop;

import org.springframework.data.repository.CrudRepository;

interface AuditRepository extends CrudRepository<Audit, Long> {
}
