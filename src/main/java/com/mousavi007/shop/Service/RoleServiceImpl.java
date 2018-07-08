package com.mousavi007.shop.Service;

import com.mousavi007.shop.Domain.Role;
import com.mousavi007.shop.Repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {


    final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(Integer id) {
        return roleRepository.getOne(id);
    }
}
