package com.store.pharmacy.Company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Company  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_COMPANY")
    private long idCompany;

    @Column(name="NAME")
    private String name;

    @Column(name="TAX_CODE")
    private String taxCode;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_AT")
    private LocalDate createdAt;

    @Column(name="UPDATED_BY")
    private String updatedBy;

    @Column(name="UPDATED_AT")
    private LocalDate updatedAt;
}
