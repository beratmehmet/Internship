package com.obss.onlinemarketplace.repository;

import com.obss.onlinemarketplace.model.Product;
import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    Page<User> findByUsernameNot(String username, Pageable page);

    List<User> findByProducts(Product product);

    List<User> findBySellers(Seller seller);
}
