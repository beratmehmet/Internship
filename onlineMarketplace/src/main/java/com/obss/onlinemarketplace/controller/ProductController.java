package com.obss.onlinemarketplace.controller;

import com.obss.onlinemarketplace.dto.GenericResponse;
import com.obss.onlinemarketplace.dto.ProductSubmitVM;
import com.obss.onlinemarketplace.dto.ProductVM;
import com.obss.onlinemarketplace.dto.Search;
import com.obss.onlinemarketplace.model.Product;

import com.obss.onlinemarketplace.service.ProductService;
import com.obss.onlinemarketplace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("/api/1.0")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('Role_Admin')")
    @PostMapping("/products")
    GenericResponse saveProduct(@Valid @RequestBody ProductSubmitVM productSubmitVM) {
        productService.save(productSubmitVM);
        return new GenericResponse("Product is saved");
    }

    @GetMapping("/products")
    Page<ProductVM> getProducts(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return productService.getProducts(page, "").map(ProductVM::new);
    }

    @PostMapping("/products/search")
    Page<ProductVM> SearchProducts(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
                                   @RequestBody(required = false) Search search) {
        return productService.getProducts(page, search.getName()).map(ProductVM::new);
    }


    @GetMapping("/product/{id:[0-9]+}")
    ProductVM getProduct(@PathVariable long id) {
        Product product = productService.getProduct(id);
        return new ProductVM(product);
    }

    @GetMapping({"/products/{id:[0-9]+}", "/sellers/{optionalSellerId:[0-9]+}/products/{id:[0-9]+}"})
    ResponseEntity<?> getProductesRelative(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
                                           @PathVariable(required = false) Optional<Long> optionalSellerId,
                                           @PathVariable long id) {
        long sellerId = 0;
        if (optionalSellerId.isPresent()) {
            sellerId = optionalSellerId.get();
        }

        return ResponseEntity.ok(productService.getOldProducts(id, sellerId, page).map(ProductVM::new));
    }

    @GetMapping("/sellers/{id:[0-9]+}/products")
    Page<ProductVM> getSellerProducts(@PathVariable long id, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {

        return productService.getProductsOfSeller(id, page).map(ProductVM::new);
    }

    @PreAuthorize("hasAuthority('Role_Admin')")
    @PutMapping("/products/{id:[0-9]+}")
    ResponseEntity<?> updateProduct(@PathVariable long id, @Valid @RequestBody ProductSubmitVM productSubmitVM) {
        productService.updateProduct(id, productSubmitVM);
        return ResponseEntity.ok("product updated");
    }

    @PreAuthorize("hasAuthority('Role_Admin')")
    @DeleteMapping("/products/{id:[0-9]+}")
    GenericResponse deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return new GenericResponse("Product Removed");
    }


}
