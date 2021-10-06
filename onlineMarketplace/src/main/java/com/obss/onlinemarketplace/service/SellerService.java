package com.obss.onlinemarketplace.service;

import com.obss.onlinemarketplace.File.FileService;
import com.obss.onlinemarketplace.dto.SellerUpdateVM;
import com.obss.onlinemarketplace.exception.NotFoundException;
import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.repository.SellerRepository;
import org.hibernate.criterion.MatchMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class SellerService {
    SellerRepository sellerRepository;

    FileService fileService;

    UserService userService;

    public SellerService(SellerRepository sellerRepository, FileService fileService, UserService userService) {
        this.sellerRepository = sellerRepository;
        this.fileService = fileService;
        this.userService = userService;
    }

    public void save(Seller seller) {
        sellerRepository.save(seller);
    }

    public Seller findById(long id) {
        Seller inDB = sellerRepository.getById(id);
        if (inDB == null) {
            throw new NotFoundException();
        }
        return inDB;
    }

    public Page<Seller> getSellers(Pageable page, String search) {
        Specification<Seller> specification = nameContains(search);
        return sellerRepository.findAll(specification, page);
    }

    public Seller getSeller(long id) {
        return sellerRepository.getById(id);
    }

    public List<Seller> getSellersWOPage() {
        return sellerRepository.findAll(Sort.by("name"));
    }


    public void deleteSeller(long id) {
        Seller inDB = sellerRepository.getById(id);
        userService.deleteSellerFromBlacklists(inDB);
        fileService.deleteAllStoredFileForSeller(inDB);
        sellerRepository.delete(inDB);
    }

    public void updateSeller(long id, SellerUpdateVM sellerUpdateVM) {
        Seller inDB = sellerRepository.getById(id);
        inDB.setName(sellerUpdateVM.getName());
        sellerRepository.save(inDB);
    }

    Specification<Seller> nameContains(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        });
    }


}
