package com.store.pharmacy.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MESSAGE")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="MESSAGE_CODE", length = 16, nullable = false)
    private String messageCode;

    @Column(name="TEXT", nullable = false)
    private String text;

    @Column(name="LANG", length = 4, nullable = false)
    private String lang;

    @Column(name="ENABLED")
    private boolean enabled;

    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name="CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

}
