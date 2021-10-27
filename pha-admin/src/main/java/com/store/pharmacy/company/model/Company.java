package com.store.pharmacy.company.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COMPANY")
public class Company  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_COMPANY")
    private long idCompany;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="TAX_CODE", length = 50, nullable = false)
    private String taxCode;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name="CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="UPDATED_BY")
    private String updatedBy;

    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;
}
