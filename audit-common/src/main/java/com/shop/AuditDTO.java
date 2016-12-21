package com.shop;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class AuditDTO {

    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    String event;

    @Getter
    @Setter
    Timestamp createdAt;
}
