package com.square.health.service.impl;

import com.square.health.model.Admin;
import com.square.health.repositoy.AdminRepository;
import com.square.health.util.enumutil.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin user = this.adminRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        return User.builder().username(user.getEmail()).password(user.getPassword()).authorities(RoleEnum.ROLE_ADMIN.name()).build();
    }
}
