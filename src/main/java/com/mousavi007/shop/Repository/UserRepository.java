package com.mousavi007.shop.Repository;

import com.mousavi007.shop.Domain.Role;
import com.mousavi007.shop.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Long> countByRoles(Role role);
}
