package com.store.pharmacy.securities.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "USER")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID", length = 11, nullable = false)
    private String userId;

    @Column(name = "ROLE_CODE", length = 3, nullable = false)
    private int roleCode;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Column(name = "CREATED_BY")
    private String createBy;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
