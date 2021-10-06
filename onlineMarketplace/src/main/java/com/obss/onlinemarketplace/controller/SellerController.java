package com.obss.onlinemarketplace.controller;

import com.obss.onlinemarketplace.dto.*;
import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1.0")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @GetMapping("/sellers")
    Page<SellerVM> getSellers(Pageable page) {
        return sellerService.getSellers(page, "").map(SellerVM::new);
    }

    @PostMapping("/sellers/search")
    Page<SellerVM> searchSellers(Pageable page, @RequestBody(required = false) Search search) {
        return sellerService.getSellers(page, search.getName()).map(SellerVM::new);
    }

    @GetMapping("/sellerswopage")
    List<SellerVM> getSellersWOPage() {
        return sellerService.getSellersWOPage().stream().map(SellerVM::new).collect(Collectors.toList());
    }


    @GetMapping("/sellers/{id:[0-9]+}")
    SellerVM getSeller(@PathVariable long id) {
        Seller seller = sellerService.getSeller(id);
        return new SellerVM(seller);
    }

    @PreAuthorize("hasAuthority('Role_Admin')")
    @PostMapping("/sellers")
    GenericResponse createSeller(@Valid @RequestBody Seller seller) {
        sellerService.save(seller);
        return new GenericResponse("seller created");
    }

    @PreAuthorize("hasAuthority('Role_Admin')")
    @DeleteMapping("/sellers/{id:[0-9]+}")
    GenericResponse deleteSeller(@PathVariable long id) {
        sellerService.deleteSeller(id);
        return new GenericResponse("seller is removed");

    }

    @PreAuthorize("hasAuthority('Role_Admin')")
    @PutMapping("/sellers/{id:[0-9]+}")
    ResponseEntity<?> updateSeller(@PathVariable long id, @Valid @RequestBody SellerUpdateVM sellerUpdateVM) {
        sellerService.updateSeller(id, sellerUpdateVM);
        return ResponseEntity.ok("product updated");
    }
}
