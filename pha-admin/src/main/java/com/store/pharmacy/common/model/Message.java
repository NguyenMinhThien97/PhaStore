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
    @Column(name="messageCode")
    private String messageCode;

    @Column(name="text")
    private String text;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="createdBy")
    private LocalDate createdBy;

    @Column(name="createdAt")
    private LocalDate createdAt;

}
