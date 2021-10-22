package com.square.health.service.impl;

import com.square.health.model.Admin;
import com.square.health.repositoy.AdminRepository;
import com.square.health.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin getAdmin(String bloggerEmail) {
        return this.adminRepository.findByEmail(bloggerEmail);
    }
}
