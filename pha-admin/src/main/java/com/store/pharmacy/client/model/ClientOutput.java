package com.store.pharmacy.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientOutput {

    private long idClient;

    private long idCompany;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String phoneNumber;

    private String address;

    private boolean enabled;

    private String userId;

    private LocalDateTime updatedAt;
}