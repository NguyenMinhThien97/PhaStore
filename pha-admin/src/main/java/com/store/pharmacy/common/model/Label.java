package com.store.pharmacy.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LABEL")
public class Label implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="LABEL_CODE" , length = 16, nullable = false)
    private String labelCode;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="LANG", length = 4, nullable = false)
    private String lang;

    @Column(name="ENABLED")
    private boolean enabled;

    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name="CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
}
