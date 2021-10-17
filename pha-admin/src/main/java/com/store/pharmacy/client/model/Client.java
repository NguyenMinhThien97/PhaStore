package com.store.pharmacy.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_CLIENT", length = 11, nullable = false)
    private String idClient;

    @Column(name="ID_COMPANY")
    private long idCompany;

    @Column(name="USER_NAME", length = 100, nullable = false)
    private String userName;

    @Column(name="FIRST_NAME", length = 80, nullable = false)
    private String firstName;

    @Column(name="LAST_NAME", length = 80)
    private String lastName;

    @Column(name="EMAIL", length = 120)
    private String email;

    @Column(name="BIRTHDAY")
    private LocalDate birthday;

    @Column(name="PHONE_NUMBER", length = 12, nullable = false)
    private String phoneNumber;

    @Column(name="ADDRESS", nullable = false)
    private String address;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name="ENABLED")
    private boolean enabled;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="UPDATED_BY")
    private String updatedBy;

    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;
}
