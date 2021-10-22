package com.square.health.service.impl;

import com.square.health.dto.BloggerDto;
import com.square.health.model.Blogger;
import com.square.health.repositoy.BloggerRepository;
import com.square.health.service.BloggerService;
import com.square.health.service.RoleService;
import com.square.health.util.Converter;
import com.square.health.util.Utility;
import com.square.health.util.enumutil.RoleEnum;
import com.square.health.util.enumutil.StatusEnum;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
        Blogger blogger = Converter.bloggerDtoToBlogger(requestBodyDto);
        blogger.setStatus(StatusEnum.INACTIVE);
        blogger.grantRole(this.roleService.findRole(RoleEnum.ROLE_BLOGGER.name()));
        this.bloggerRepository.save(blogger);
        return utility.createResponse(401, "Success", "Registration Complete");
    }
}
