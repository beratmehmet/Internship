package com.obss.onlinemarketplace.repository;

import com.obss.onlinemarketplace.model.Product;
import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findBySeller(Seller seller, Pageable page);
    List<Product> findByUser(User user);
}
