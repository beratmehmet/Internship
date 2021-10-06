package com.obss.onlinemarketplace.dto;

import com.obss.onlinemarketplace.model.Seller;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ProductSubmitVM {
    @NotNull(message = "{onlineMarketplace.constraint.fullName.NotNull.message}")
    @Size(min = 4, max = 1000)
    private String name;

    @NotNull(message = "{onlineMarketplace.constraint.content.NotNull.message}")
    @Size(min = 5, max = 1000)
    private String content;

    @NotNull
    @Min(value = 1)
    private long price;

    private long attachmentId;

    private long sellerId;

}