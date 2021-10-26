package com.square.health.service.impl;

import com.square.health.dto.LikeDto;
import com.square.health.model.Blogger;
import com.square.health.model.LikePost;
import com.square.health.model.Post;
import com.square.health.repositoy.BloggerRepository;
import com.square.health.repositoy.LikeRepository;
import com.square.health.repositoy.PostRepository;
import com.square.health.service.LikeService;
import com.square.health.util.KeyWord;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    Utility utility;

    @Override
    public JSONObject createLike(HttpServletRequest httpServletRequest, LikeDto likeDto) throws JSONException {
        Optional<Blogger> optionalBlogger = this.bloggerRepository.findById(Long.valueOf(likeDto.getBloggerId()));
        Optional<Post> optionalPost = this.postRepository.findById(Long.valueOf(likeDto.getPostId()));
        Optional<LikePost> likeRepositoryByBloggerId = this.likeRepository.findByBloggerId(Long.valueOf(likeDto.getBloggerId()));
        if (optionalBlogger.isPresent() && optionalPost.isPresent()){
            if ((likeRepositoryByBloggerId.isPresent())){
                likeRepositoryByBloggerId.get().setLikePost(likeDto.isLikePost());
                this.likeRepository.save(likeRepositoryByBloggerId.get());
            }else {
                LikePost likePost = new LikePost();
                likePost.setBlogger(optionalBlogger.get());
                likePost.setPost(optionalPost.get());
                likePost.setLikePost(likeDto.isLikePost());
                this.likeRepository.save(likePost);
            }
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_CREATED, "Like");
        }
        return utility.createResponse(HttpStatus.FOUND.value(), KeyWord.SUCCESS_EXIST, "Like Created");
    }

    @Override
    public JSONObject getAllLikeOfPost(HttpServletRequest httpServletRequest, Long postId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        long l = this.likeRepository.countByPostId(postId);
        jsonObject.put("likes", l);
        return jsonObject;
    }
}
