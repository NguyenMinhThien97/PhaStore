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
@Table(name = "Client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="idClient")
    private long idClient;

    @Column(name="idCompany")
    private long idCompany;

    @Column(name="userName")
    private String userName;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="birthday")
    private String birthday;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="password")
    private String password;

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

    @ManyToOne
    private ClientCompany clientCompany;
}
