package com.store.pharmacy.securities.model;

import com.store.pharmacy.client.validation.ValidBirthday;
import com.store.pharmacy.client.validation.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {

    @NotBlank(message = "MSG0017")
    private String roleName;

    private String userName;

    @NotBlank(message = "MSG0005")
    private String firstName;

    @NotBlank(message = "MSG0006")
    private String lastName;

    @Email(message = "MSG0009")
    private String email;

    @NotBlank(message = "MSG0018")
    @ValidBirthday(message = "MSG0007")
    private String dateOfBirth;

    @NotBlank(message = "MSG0004")
    @ValidPhoneNumber(message = "MSG0003")
    private String phoneNumber;

    private String address;

    private Boolean enabled;

    private String currentPassword;

    private String newPassword;
}
