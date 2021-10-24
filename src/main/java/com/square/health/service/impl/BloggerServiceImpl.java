package com.square.health.service.impl;

import com.square.health.dto.AdminDto;
import com.square.health.dto.BloggerDto;
import com.square.health.dto.BloggerStatusDto;
import com.square.health.model.Admin;
import com.square.health.model.Blogger;
import com.square.health.repositoy.BloggerRepository;
import com.square.health.service.BloggerService;
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
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    Utility utility;

    @Override
    public JSONObject createBlogger(HttpServletRequest httpServletRequest, BloggerDto requestBodyDto) throws JSONException {
        Optional<Blogger> bloggerOptional = this.bloggerRepository.findByEmail(requestBodyDto.getEmail());
        if (!bloggerOptional.isPresent()) {
            Blogger blogger = Converter.bloggerDtoToBlogger(requestBodyDto);
            blogger.setStatus(StatusEnum.INACTIVE);
            blogger.setPassword(PasswordUtil.passwordEncoder().encode(requestBodyDto.getBloggerPassword()));
            blogger.grantRole(this.roleService.findRole(RoleEnum.ROLE_BLOGGER.name()));
            this.bloggerRepository.save(blogger);
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_CREATED, "Registration Complete");
        }
        return utility.createResponse(HttpStatus.FOUND.value(), KeyWord.SUCCESS_EXIST, "Registration Failed");
    }

    @Override
    public Optional<Blogger> getBlogger(String bloggerEmail) {
        return this.bloggerRepository.findByEmail(bloggerEmail);
    }

    @Override
    public JSONObject approveBlogger(HttpServletRequest httpServletRequest, @RequestBody BloggerStatusDto bloggerStatusDto) throws JSONException {
        Optional<Blogger> optionalBlogger = this.bloggerRepository.findById(bloggerStatusDto.getBloggerId());
        if (optionalBlogger.isPresent()){
            Blogger blogger = optionalBlogger.get();
            blogger.setStatus(StatusEnum.valueOf(bloggerStatusDto.getBloggerStatus()));
            this.bloggerRepository.save(blogger);
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_APPROVE, "Approve Blogger");
        }
        return utility.createResponse(HttpStatus.NOT_FOUND.value(), KeyWord.SUCCESS_FAILED, "Approve Blogger");
    }

    @Override
    public List<BloggerDto> getAllBlogger(HttpServletRequest httpServletRequest) {
        List<Blogger> bloggerList = this.bloggerRepository.findAll();
        if (!bloggerList.isEmpty())
            return bloggerList.stream().map(blogger -> Converter.bloggerToBloggerDto(blogger)).collect(Collectors.toList());
        return Arrays.asList();
    }
}
