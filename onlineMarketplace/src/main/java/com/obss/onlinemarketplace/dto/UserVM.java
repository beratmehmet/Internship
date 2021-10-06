package com.obss.onlinemarketplace.dto;

import com.obss.onlinemarketplace.model.Role;
import com.obss.onlinemarketplace.model.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserVM {

    private String username;

    private String fullName;

    private long id;

    private String email;

    private Set<Role> role;

    private Set<ProductVM> favs = new HashSet<>();

    private Set<SellerVM> blackList= new HashSet<>();

    public UserVM(User user){
        this.setFullName(user.getFullName());
        this.setUsername(user.getUsername());
        this.setId(user.getId());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
        this.setFavs(user.getProducts().stream().map(ProductVM::new).collect(Collectors.toSet()));
        this.setBlackList(user.getSellers().stream().map(SellerVM::new).collect(Collectors.toSet()));
    }
}
