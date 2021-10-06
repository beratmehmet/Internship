package com.obss.onlinemarketplace.controller;

import com.obss.onlinemarketplace.annotation.CurrentUser;
import com.obss.onlinemarketplace.dto.*;
import com.obss.onlinemarketplace.model.User;
import com.obss.onlinemarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('Role_Admin')")
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse createUser(@Valid @RequestBody User user) {

        userService.save(user);
        return new GenericResponse("user created");
    }

    @PreAuthorize("hasAuthority('Role_Admin')")
    @GetMapping("/users")
    Page<UserVM> getUsers(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page, @CurrentUser User user){
        return  userService.getUsers(page,user,"").map(UserVM::new);
    }

    @PreAuthorize("hasAuthority('Role_Admin')")
    @PostMapping("/users/search")
    Page<UserVM> searchUsers(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page, @CurrentUser User user,
                             @RequestBody(required = false)Search search){
        return  userService.getUsers(page,user,search.getName()).map(UserVM::new);
    }

    @PreAuthorize(("hasAuthority('Role_Admin') or #username == principal.username"))
    @GetMapping("/users/{username}")
    UserVM getUser(@PathVariable String username){
        User user=userService.getByUsername(username);
        return new UserVM(user);
    }

    @PreAuthorize("hasAuthority('Role_Admin')  or #username == principal.username")
    @PutMapping("/users/{username}")
    ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateVM updatedUser,
                                 @PathVariable String username){

        User user =userService.updateUser(username,updatedUser);
        return ResponseEntity.ok(new UserVM(user));
    }

    @PreAuthorize("hasAuthority('Role_Admin') or #username == principal.username")
    @PutMapping("/users/{username}/change-password")
    GenericResponse changePassword(@Valid @RequestBody UserUpdatePassword password, @PathVariable String username){
        userService.changePassword(username,password.getPassword());
        return new GenericResponse("Password Changed");
    }

    @PreAuthorize(("hasAuthority('Role_Admin') or #username == principal.username"))
    @DeleteMapping("/users/{username}")
    GenericResponse deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return new GenericResponse("user deleted");
    }
    @PreAuthorize("#username == principal.username")
    @PostMapping("/users/{username}/favourites/{id:[0-9]+}")
    GenericResponse addFavourites(@PathVariable String username, @PathVariable long id){
        userService.addFavourites(username,id);
        return new GenericResponse("product added to favourites");
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/users/{username}/delete-favorite/{id:[0-9]+}")
    GenericResponse deleteFavorite(@PathVariable String username, @PathVariable long id){
        userService.deleteFavorite(username,id);
        return new GenericResponse("product removed from favorites");
    }

    @PreAuthorize("#username == principal.username")
    @PostMapping("/users/{username}/to-blacklist/{id:[0-9]+}")
    GenericResponse addSellerToBlacklist(@PathVariable String username, @PathVariable long id){
        userService.addSellerToBlacklist(username,id);
        return new GenericResponse("seller added to blacklist");
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/users/{username}/delete-from-blacklist/{id:[0-9]+}")
    GenericResponse deleteSellerFromBlacklist(@PathVariable String username, @PathVariable long id){
        userService.deleteSellerFromBlacklist(username,id);
        return new GenericResponse("seller removed from blacklist");
    }

}
