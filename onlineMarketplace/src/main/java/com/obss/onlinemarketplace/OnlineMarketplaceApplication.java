package com.obss.onlinemarketplace;

import com.obss.onlinemarketplace.dto.ProductSubmitVM;
import com.obss.onlinemarketplace.model.Role;
import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.model.User;
import com.obss.onlinemarketplace.repository.RoleRepository;
import com.obss.onlinemarketplace.service.ProductService;
import com.obss.onlinemarketplace.service.SellerService;
import com.obss.onlinemarketplace.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OnlineMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineMarketplaceApplication.class, args);
    }

    @Bean
    CommandLineRunner createInitialUsers(UserService userService, ProductService productService, SellerService sellerService, RoleRepository roleRepository) {
        return (args) -> {
            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setFullName("admin");
            userAdmin.setEmail("admin@gmail.com");
            Role adminRole = new Role("Role_Admin");
            roleRepository.save(adminRole);
            Role userRole = new Role("Role_User");
            roleRepository.save(userRole);
            userAdmin.setPassword("Password");

            Role role = roleRepository.findByName("Role_Admin");
            Role role1 = roleRepository.findByName("Role_User");
            userAdmin.addRole(role);
            userService.save(userAdmin);
            for (int i = 1; i <= 25; i++) {
                User user = new User();
                Seller seller = new Seller();
                seller.setName("seller" + i);
                user.setUsername("user" + i);
                user.setFullName("display" + i);
                user.setPassword("Password");
                user.setEmail("display" + i + "@gmail.com");
                user.addRole(role1);
                userService.save(user);
                sellerService.save(seller);

                for (int j = 1; j <= 3; j++) {
                    ProductSubmitVM product = new ProductSubmitVM();
                    product.setName("product" + i + "-" + j);
                    product.setContent("content" + i + "-" + j);
                    product.setPrice(i * 100);
                    product.setSellerId(i);
                    productService.save(product);
                }

            }


        };

    }


}
