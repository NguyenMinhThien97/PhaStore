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
    @Column(name="commonCode")
    private String commonCode;

    @Id
    @Column(name="SequenceNo")
    private int sequenceNo;

    @Column(name="name")
    private String name;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="createdBy")
    private String createdBy;

    @Column(name="createdAt")
    private LocalDate createdAt;
}
