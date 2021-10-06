package com.obss.onlinemarketplace.dto;

import com.obss.onlinemarketplace.model.Seller;
import lombok.Data;

@Data
public class SellerVM {

    private long id;

    private String name;

    public SellerVM(Seller seller) {
        this.name = seller.getName();
        this.id=seller.getId();
    }
}
