package com.obss.onlinemarketplace.model;

import com.obss.onlinemarketplace.File.FileAttachment;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "{onlineMarketplace.constraint.fullName.NotNull.message}")
    @Column(length = 1000)
    private String name;

    @NotNull(message = "{onlineMarketplace.constraint.content.NotNull.message}")
    @Column(length = 1000)
    private String content;

    @NotNull
    private long price;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> user;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private FileAttachment fileAttachment;

    @NotNull
    @ManyToOne
    private Seller seller;

}

