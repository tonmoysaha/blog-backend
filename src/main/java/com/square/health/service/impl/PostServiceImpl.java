package com.square.health.service.impl;

import com.square.health.dto.PostDto;
import com.square.health.model.Blogger;
import com.square.health.model.Post;
import com.square.health.repositoy.BloggerRepository;
import com.square.health.repositoy.PostRepository;
import com.square.health.repositoy.RoleRepository;
import com.square.health.service.PostService;
import com.square.health.util.Converter;
import com.square.health.util.KeyWord;
import com.square.health.util.Utility;
import com.square.health.util.enumutil.StatusEnum;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    Utility utility;

    @Override
    public JSONObject createPost(HttpServletRequest httpServletRequest, PostDto requestBodyDto) throws JSONException {
        Optional<Blogger> optionalBlogger = this.bloggerRepository.findById(Long.valueOf(requestBodyDto.getBloggerId()));
        if (optionalBlogger.isPresent()) {
            Post post = Converter.postDtoToPost(requestBodyDto);
            post.setBlogger(optionalBlogger.get());
            post.setStatus(StatusEnum.ACTIVE);
            postRepository.save(post);
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_CREATED, "Post Created");
        }
        return utility.createResponse(HttpStatus.FOUND.value(), KeyWord.SUCCESS_EXIST, "Post Created");
    }
}
