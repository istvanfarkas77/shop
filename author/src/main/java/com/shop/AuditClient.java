package com.shop;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("audit-service")
public interface AuditClient extends AuditResource {
}
