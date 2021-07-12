package com.store.pharmacy.Client.model;

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
@Table(name = "ClientCompany")
public class ClientCompany  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="idClientCompany")
    private long idClientCompany;

    @Column(name="idCompany")
    private long idCompany;

    @Column(name="idClient")
    private long idClient;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="createdBy")
    private String createdBy;

    @Column(name="createdAt")
    private LocalDate createdAt;

    @Column(name="updatedBy")
    private String updatedBy;

    @Column(name="updatedAt")
    private LocalDate updatedAt;
}
