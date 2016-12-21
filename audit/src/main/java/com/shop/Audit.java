package com.shop;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "T_AUDIT")
class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String event;

    @Column
    @Getter
    @Setter
    private Timestamp createdAt;

    public Audit() {
    }

    public Audit(String event) {
        this.event = event;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(id).append(event).append(createdAt).build();
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
