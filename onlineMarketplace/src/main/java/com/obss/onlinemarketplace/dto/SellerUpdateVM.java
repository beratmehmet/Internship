package com.obss.onlinemarketplace.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SellerUpdateVM {

    @NotNull(message = "{onlineMarketplace.constraint.fullName.NotNull.message}")
    @Size(min=4,max = 100)
    String name;
}
