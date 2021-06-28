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
@Table(name = "Label")
public class Label implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="labelCode")
    private String labelCode;

    @Column(name="name")
    private String name;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="createdBy")
    private LocalDate createdBy;

    @Column(name="createdAt")
    private LocalDate createdAt;
}
