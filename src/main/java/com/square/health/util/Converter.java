package com.square.health.util;

import com.square.health.dto.AdminDto;
import com.square.health.dto.BloggerDto;
import com.square.health.model.Admin;
import com.square.health.model.Blogger;

public class Converter {

    public static Blogger bloggerDtoToBlogger(BloggerDto bloggerDto) {
        Blogger blogger = new Blogger();
        blogger.setEmail(bloggerDto.getEmail());
        blogger.setPassword(bloggerDto.getBloggerPassword());
        blogger.setUserName(bloggerDto.getBloggerName());
        return blogger;
    }

    public static BloggerDto bloggerToBloggerDto(Blogger blogger) {
        BloggerDto bloggerDto = new BloggerDto();
        bloggerDto.setEmail(blogger.getEmail());
        bloggerDto.setBloggerName(blogger.getUserName());
        bloggerDto.setBloggerStatus(blogger.getStatus().name());
        return bloggerDto;
    }

    public static Admin adminDtoToAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(adminDto.getAdminPassword());
        admin.setUserName(adminDto.getAdminName());
        return admin;
    }

    public static AdminDto adminToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setEmail(admin.getEmail());
        adminDto.setAdminName(admin.getUserName());
        return adminDto;
    }
}
