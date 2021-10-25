package com.square.health.service.impl;

import com.square.health.model.Blogger;
import com.square.health.repositoy.BloggerRepository;
import com.square.health.util.enumutil.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BloggerUserDetailService implements UserDetailsService {

    @Autowired
    private BloggerRepository bloggerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Blogger> optionalBlogger = this.bloggerRepository.findByEmail(email);
        if (!optionalBlogger.isPresent()) {
            throw new UsernameNotFoundException("Blogger doesn't exist!");
        }
        Blogger blogger = optionalBlogger.get();
        return User.builder().username(blogger.getEmail()).password(blogger.getPassword()).authorities(new SimpleGrantedAuthority(RoleEnum.ROLE_BLOGGER.name())).build();
    }

}
