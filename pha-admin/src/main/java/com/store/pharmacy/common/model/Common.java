package com.store.pharmacy.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Common")
@IdClass(CommonId.class)
public class Common implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="COMMON_CODE")
    private String commonCode;

    @Id
    @Column(name="SEQUENCE_NO")
    private int sequenceNo;

    @Column(name="NAME")
    private String name;

    @Column(name="LANG")
    private String lang;

    @Column(name="ENABLED")
    private boolean enabled;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_AT")
    private LocalDate createdAt;
}
