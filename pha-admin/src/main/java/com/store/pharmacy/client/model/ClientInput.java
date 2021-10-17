package com.store.pharmacy.client.model;

import com.store.pharmacy.client.validation.ValidBirthday;
import com.store.pharmacy.client.validation.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInput {
    private long idCompany;

    @Size(max = 50, message = "MSG0022.50")
    private String userName;

    @NotBlank(message = "MSG0020")
    @Size(max = 50, message = "MSG0021.3.50")
    private String firstName;

    @NotBlank(message = "MSG0020")
    @Size(max = 50, message = "MSG0022.50")
    private String lastName;

    @Email(message = "MSG0024")
    private String email;

    @ValidBirthday
    private String birthday;

    @NotBlank(message = "MSG0020")
    @ValidPhoneNumber(message = "MSG0003")
    private String phoneNumber;

    @NotBlank(message = "MSG0020")
    @Size(max = 255, message = "MSG0022.255")
    private String address;

    private boolean enabled;
}
