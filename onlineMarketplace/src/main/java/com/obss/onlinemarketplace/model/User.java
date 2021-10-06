package com.obss.onlinemarketplace.model;

import com.obss.onlinemarketplace.annotation.UniqueUsername;
import com.obss.onlinemarketplace.dto.ProductVM;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UniqueUsername
    @NotNull(message = "{onlineMarketplace.constraint.username.NotNull.message}")
    @Size(min = 4, max = 255)
    private String username;

    @NotNull(message = "{onlineMarketplace.constraint.fullName.NotNull.message}")
    @Size(min = 4, max = 255)
    private String fullName;

    @NotNull
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{onlineMarketplace.constraints.Pattern.message}")
    private String password;

    @Email(message = "{onlineMarketplace.constraint.email.Email.message}")
    @NotNull(message = "{onlineMarketplace.constraint.email.NotNull.message}")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> role=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToMany
    Set<Seller> sellers = new HashSet<>();

    public void addRole(Role role){
        this.role.add(role);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role:role
             ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
