package com.obss.onlinemarketplace.service;

import com.obss.onlinemarketplace.dto.UserUpdateVM;
import com.obss.onlinemarketplace.exception.NotFoundException;
import com.obss.onlinemarketplace.model.Product;
import com.obss.onlinemarketplace.model.Role;
import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.model.User;
import com.obss.onlinemarketplace.repository.ProductRepository;
import com.obss.onlinemarketplace.repository.RoleRepository;
import com.obss.onlinemarketplace.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    ProductService productService;

    PasswordEncoder passwordEncoder;

    ProductRepository productRepository;

    SellerService sellerService;

    RoleRepository roleRepositoriy;

    public UserService(UserRepository userRepository, @Lazy ProductService productService, PasswordEncoder passwordEncoder,
                       ProductRepository productRepository, RoleRepository roleRepository,
                       @Lazy SellerService sellerService
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
        this.productRepository=productRepository;
        this.sellerService=sellerService;
        this.roleRepositoriy=roleRepository;

    }

    public void save(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = roleRepositoriy.findByName("Role_User");
        user.addRole(role);
        userRepository.save(user);
    }


    public Page<User> getUsers(Pageable page, User user, String search) {
        Specification<User> specification = nameContains(search);
        if (user != null) {
            Specification<User> specification2 = specification.and(userNameNot(user.getUsername()));
            return userRepository.findAll(specification2,page);
        }
        return userRepository.findAll(specification,page);
    }

    public User getByUsername(String username) {
        User inDB = userRepository.findByUsername(username);
        if(inDB==null){
            throw new NotFoundException();
        }
        return inDB;
    }

    public User updateUser(String username, UserUpdateVM updatedUser) {

        User inDB = getByUsername(username);
        inDB.setFullName(updatedUser.getFullName());
        inDB.setEmail(updatedUser.getEmail());
        return userRepository.save(inDB);

    }

    public void changePassword(String username, String password) {
        User inDB = getByUsername(username);
        inDB.setPassword(this.passwordEncoder.encode(password));
        userRepository.save(inDB);

    }

    public void deleteUser(String username) {
        User inDB = userRepository.findByUsername(username);
        productService.deleteFavouritesOfUser(inDB);
        userRepository.delete(inDB);

    }

    public void addFavourites(String username, long productId) {
        User user = userRepository.findByUsername(username);
        Product product = productService.getProduct(productId);
        List<Product> favourites=user.getProducts();
        favourites.add(product);
        user.setProducts(favourites);
        userRepository.save(user);
    }


    public List<User> getUserOfProduct(Product product){
        return userRepository.findByProducts(product);
    }

    public void deleteFavouritesOfUser(Product product){
        List<User> users=getUserOfProduct(product);
        for (User user:users
        ) {
            user.getProducts().remove(product);

            userRepository.save(user);
        }
    }

    public List<User> getSellerOfUserBlacklist(Seller seller){
        return userRepository.findBySellers(seller);
    }

    public void deleteSellerFromBlacklists(Seller seller){
        List<User> users = getSellerOfUserBlacklist(seller);
        for (User user:users
             ) {
            user.getSellers().remove(seller);
            userRepository.save(user);
        }
    }

    public void deleteFavorite(String username, long id) {
        User user = userRepository.findByUsername(username);
        Product product = productService.getProduct(id);
        user.getProducts().remove(product);
        userRepository.save(user);
    }

    public void addSellerToBlacklist(String username, long id) {
        User user = userRepository.findByUsername(username);
        Seller seller = sellerService.getSeller(id);

        user.getSellers().add(seller);

        userRepository.save(user);
    }


    public void deleteSellerFromBlacklist(String username, long id) {
        User user = userRepository.findByUsername(username);
        Seller seller = sellerService.getSeller(id);

        user.getSellers().remove(seller);
        userRepository.save(user);
    }

    Specification<User> nameContains(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%"+name.toLowerCase()+"%");
        });
    }

    Specification<User> userNameNot(String username){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.notLike(root.get("username"), username);
        });
    }


}
