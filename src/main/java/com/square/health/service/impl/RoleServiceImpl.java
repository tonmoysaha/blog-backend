package com.square.health.service.impl;

import com.square.health.model.Role;
import com.square.health.repositoy.RoleRepository;
import com.square.health.service.RoleService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRole(String roleName) throws JSONException {
        Optional<Role> byRole = this.roleRepository.findByRole(roleName);
        if (byRole.isPresent())
            return byRole.get();
        Role role1 = new Role();
        role1.setRole(roleName);
        return this.roleRepository.save(role1);
    }
}
