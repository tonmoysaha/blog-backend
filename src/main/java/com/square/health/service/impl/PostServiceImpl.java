package com.square.health.service.impl;

import com.square.health.dto.PostDto;
import com.square.health.dto.PostStatusDto;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            post.setStatus(StatusEnum.INACTIVE);
            postRepository.save(post);
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_CREATED, "Post Created");
        }
        return utility.createResponse(HttpStatus.FOUND.value(), KeyWord.SUCCESS_EXIST, "Post Created");
    }

    @Override
    public JSONObject deletePost(HttpServletRequest httpServletRequest, Long postId) throws JSONException {
        Optional<Post> optionalPost = this.postRepository.findById(postId);
        if (optionalPost.isPresent()){
            this.postRepository.delete(optionalPost.get());
            return utility.createResponse(HttpStatus.NOT_FOUND.value(), KeyWord.SUCCESS_DELETED, "Deleted successful");
        }
        return utility.createResponse(HttpStatus.NOT_FOUND.value(), KeyWord.SUCCESS_FAILED, "Post Not Found");
    }

    @Override
    public JSONObject approvePost(HttpServletRequest httpServletRequest, PostStatusDto postStatusDto) throws JSONException {
        Optional<Post> optionalPost = this.postRepository.findById(postStatusDto.getPostId());
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setStatus(StatusEnum.valueOf(postStatusDto.getPostStatus()));
            this.postRepository.save(post);
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_UPDATED, "Post Updated");
        }
        return utility.createResponse(HttpStatus.NOT_FOUND.value(), KeyWord.SUCCESS_FAILED, "Post Not Found");
    }

    @Override
    public JSONObject deletePostForBlogger(HttpServletRequest httpServletRequest, Long postId, Long bloggerId) throws JSONException {
        Optional<Post> optionalPost = this.postRepository.findByIdAndBloggerId(postId, bloggerId);
        if (optionalPost.isPresent()){
            this.postRepository.delete(optionalPost.get());
            return utility.createResponse(HttpStatus.NOT_FOUND.value(), KeyWord.SUCCESS_DELETED, "Deleted successful");
        }
        return utility.createResponse(HttpStatus.NOT_FOUND.value(), KeyWord.SUCCESS_FAILED, "Post Not Found");
    }

    @Override
    public List<PostDto> getAllActivePost(HttpServletRequest httpServletRequest) {
        List<Post> postList = this.postRepository.findByStatus(StatusEnum.ACTIVE);
        if (!postList.isEmpty())
            return postList.stream().map(post -> Converter.postToPostDto(post)).collect(Collectors.toList());
        return Arrays.asList();
    }

    @Override
    public List<PostDto> getAllPost(HttpServletRequest httpServletRequest) {
        List<Post> postList = this.postRepository.findAll();
        if (!postList.isEmpty())
            return postList.stream().map(post -> Converter.postToPostDto(post)).collect(Collectors.toList());
        return Arrays.asList();
    }
}
