package com.obss.onlinemarketplace.repository;

import com.obss.onlinemarketplace.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SellerRepository extends JpaRepository<Seller,Long>, JpaSpecificationExecutor<Seller> {
}
