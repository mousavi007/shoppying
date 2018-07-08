package com.mousavi007.shop.Service;

import com.mousavi007.shop.Domain.Role;
import com.mousavi007.shop.Domain.User;

import java.util.Set;

public interface UserService {

    User getUser(Long id);
    Set<User> getAllUsers();
    User addUser(User user);
    boolean existUserByEmail(String email);
    Long countUserByRole(Role role);
}
