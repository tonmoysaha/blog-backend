package com.square.health.service.impl;

import com.square.health.dto.AdminDto;
import com.square.health.model.Admin;
import com.square.health.model.Blogger;
import com.square.health.model.Post;
import com.square.health.repositoy.AdminRepository;
import com.square.health.service.AdminService;
import com.square.health.service.RoleService;
import com.square.health.util.Converter;
import com.square.health.util.KeyWord;
import com.square.health.util.PasswordUtil;
import com.square.health.util.Utility;
import com.square.health.util.enumutil.RoleEnum;
import com.square.health.util.enumutil.StatusEnum;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    Utility utility;

    @Override
    public Admin getAdmin(String bloggerEmail) {
        return this.adminRepository.findByEmail(bloggerEmail);
    }

    @Override
    public JSONObject createAdmin(HttpServletRequest httpServletRequest, AdminDto requestBodyDto) throws JSONException {
        Admin existAdmin = this.adminRepository.findByEmail(requestBodyDto.getEmail());
        if (existAdmin == null) {
            Admin admin = Converter.adminDtoToAdmin(requestBodyDto);
            admin.setPassword(PasswordUtil.passwordEncoder().encode(requestBodyDto.getAdminPassword()));
            admin.grantRole(this.roleService.findRole(RoleEnum.ROLE_ADMIN.name()));
            this.adminRepository.save(admin);
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_CREATED, "Creation Complete");
        }
        return utility.createResponse(HttpStatus.FOUND.value(), KeyWord.SUCCESS_EXIST, "Registration Failed");
    }

    @Override
    public List<AdminDto> getAllAdmin(HttpServletRequest httpServletRequest) {
        List<Admin> adminList = this.adminRepository.findAll();
        if (!adminList.isEmpty())
            return adminList.stream().map(admin -> Converter.adminToAdminDto(admin)).collect(Collectors.toList());
        return Arrays.asList();
    }
}
