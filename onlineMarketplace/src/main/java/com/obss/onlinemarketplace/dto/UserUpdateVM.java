package com.obss.onlinemarketplace.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class UserUpdateVM {

    @NotNull(message = "{onlineMarketplace.constraint.fullName.NotNull.message}")
    @Size(min = 4,max = 255)
    private String fullName;

    @Email(message = "{onlineMarketplace.constraint.email.Email.message}")
    @NotNull(message = "{onlineMarketplace.constraint.email.NotNull.message}")
    private String email;
}
