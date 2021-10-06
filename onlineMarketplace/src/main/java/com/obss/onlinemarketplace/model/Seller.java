package com.obss.onlinemarketplace.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Seller {
    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "{onlineMarketplace.constraint.fullName.NotNull.message}")
    private String name;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<Product> products;


}
