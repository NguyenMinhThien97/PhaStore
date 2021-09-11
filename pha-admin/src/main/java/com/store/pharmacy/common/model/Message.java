package com.store.pharmacy.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="MESSAGE_CODE")
    private String messageCode;

    @Column(name="TEXT")
    private String text;

    @Column(name="LANG")
    private String lang;

    @Column(name="ENABLED")
    private boolean enabled;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_AT")
    private LocalDate createdAt;

}
