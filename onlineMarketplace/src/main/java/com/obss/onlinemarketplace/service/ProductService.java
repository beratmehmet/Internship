package com.obss.onlinemarketplace.service;

import com.obss.onlinemarketplace.File.FileAttachment;
import com.obss.onlinemarketplace.File.FileAttachmentRepository;
import com.obss.onlinemarketplace.File.FileService;
import com.obss.onlinemarketplace.dto.ProductSubmitVM;
import com.obss.onlinemarketplace.exception.NotFoundException;
import com.obss.onlinemarketplace.model.Product;
import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.model.User;
import com.obss.onlinemarketplace.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    ProductRepository productRepository;

    UserService userService;

    FileAttachmentRepository fileAttachmentRepository;

    FileService fileService;

    SellerService sellerService;

    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, FileAttachmentRepository fileAttachmentRepository,
                          FileService fileService, UserService userService, SellerService sellerService) {
        this.productRepository = productRepository;
        this.fileAttachmentRepository = fileAttachmentRepository;
        this.fileService = fileService;
        this.userService = userService;
        this.sellerService = sellerService;
    }

    public void save(ProductSubmitVM productSubmitVM) {
        Seller seller = sellerService.findById(productSubmitVM.getSellerId());

        Product product = new Product();
        product.setContent(productSubmitVM.getContent());
        product.setName(productSubmitVM.getName());
        product.setPrice(productSubmitVM.getPrice());
        product.setSeller(seller);
        productRepository.save(product);

        Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository.findById(productSubmitVM.getAttachmentId());
        if (optionalFileAttachment.isPresent()) {
            FileAttachment fileAttachment = optionalFileAttachment.get();
            fileAttachment.setProduct(product);
            fileAttachmentRepository.save(fileAttachment);
        }
    }

    public Page<Product> getProducts(Pageable page,String search) {
        Specification<Product> specification = nameContains(search);
        return productRepository.findAll(specification,page);
    }

    public Page<Product> getProductsOfSeller(long id, Pageable page) {
        Seller inDB = sellerService.findById(id);
        return productRepository.findBySeller(inDB, page);
    }

    public Page<Product> getOldProducts(long id, long sellerId, Pageable page) {
        Specification<Product> specification = idLessThan(id);
        if (sellerId != 0) {
            Seller inDB = sellerService.findById(sellerId);
            specification = specification.and(sellerIs(inDB));
        }
        return productRepository.findAll(specification, page);
    }

    Specification<Product> idLessThan(long id) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("id"), id);
        };
    }

    Specification<Product> sellerIs(Seller seller) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("seller"), seller);
        };
    }

    Specification<Product> idGreaterThan(long id) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("id"), id);
        };
    }

    Specification<Product> nameContains(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+name.toLowerCase()+"%");
        });
    }

    public void delete(long id) {
        Product inDB = productRepository.getById(id);
        userService.deleteFavouritesOfUser(inDB);


        if (inDB.getFileAttachment() != null) {
            String fileName = inDB.getFileAttachment().getName();
            fileService.deleteAttachmentFile(fileName);
        }
        productRepository.deleteById(id);
    }

    public Product getProduct(long id) {
        Optional<Product> productOptional=productRepository.findById(id);
        if (productOptional.isPresent()){
            return productOptional.get();
        }else {
            throw new NotFoundException();
        }


    }

    public void updateProduct(long id, ProductSubmitVM productSubmitVM) {
        Product inDB = productRepository.getById(id);
        inDB.setName(productSubmitVM.getName());
        inDB.setContent(productSubmitVM.getContent());
        inDB.setPrice(productSubmitVM.getPrice());

        Seller seller = sellerService.findById(productSubmitVM.getSellerId());
        inDB.setSeller(seller);

        Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository.findById(productSubmitVM.getAttachmentId());
        if (optionalFileAttachment.isPresent()) {
            FileAttachment del = inDB.getFileAttachment();
            if (del!=null){
                fileAttachmentRepository.delete(del);
            }
            FileAttachment fileAttachment = optionalFileAttachment.get();
            fileAttachment.setProduct(inDB);
            fileAttachmentRepository.save(fileAttachment);
        }

        productRepository.save(inDB);
    }

    public List<Product> getFavouritesOfUser(User user){
        return productRepository.findByUser(user);
    }

    public void deleteFavouritesOfUser(User user){
        List<Product> products=getFavouritesOfUser(user);
        for (Product product:products
        ) {
            product.getUser().remove(user);

            productRepository.save(product);
        }
    }
}
