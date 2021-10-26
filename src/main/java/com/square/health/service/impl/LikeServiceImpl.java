package com.square.health.service.impl;

import com.square.health.dto.LikeDto;
import com.square.health.model.Blogger;
import com.square.health.model.LikePost;
import com.square.health.model.Post;
import com.square.health.repositoy.BloggerRepository;
import com.square.health.repositoy.LikeRepository;
import com.square.health.repositoy.PostRepository;
import com.square.health.service.LikeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public JSONObject createLike(HttpServletRequest httpServletRequest, LikeDto likeDto) {
        Optional<Blogger> optionalBlogger = this.bloggerRepository.findById(Long.valueOf(likeDto.getBloggerId()));
        Optional<Post> optionalPost = this.postRepository.findById(Long.valueOf(likeDto.getPostId()));
        if (optionalBlogger.isPresent() && optionalPost.isPresent()){
            LikePost likePost = new LikePost();
            likePost.setBlogger(optionalBlogger.get());
            likePost.setPost(optionalPost.get());
            likePost.setLikePost(likeDto.isLikePost());
            this.likeRepository.save(likePost);
        }
        return new JSONObject();
    }

    @Override
    public JSONObject getAllLikeOfPost(HttpServletRequest httpServletRequest, Long postId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        long l = this.likeRepository.countByPostId(postId);
        jsonObject.put("likes", l);
        return jsonObject;
    }
}
