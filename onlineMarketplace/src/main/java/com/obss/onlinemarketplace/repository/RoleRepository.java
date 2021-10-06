package com.obss.onlinemarketplace.repository;

import com.obss.onlinemarketplace.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName(String name);
}
