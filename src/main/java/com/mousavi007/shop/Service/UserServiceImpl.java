package com.mousavi007.shop.Service;

import com.mousavi007.shop.Domain.Role;
import com.mousavi007.shop.Domain.User;
import com.mousavi007.shop.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        return user.get();
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users=new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public User addUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existUserByEmail(String email) {

        return userRepository.existsByEmail(email);

    }

    @Override
    public Long countUserByRole(Role role) {

        return userRepository.countByRoles(role).get();

    }


}
