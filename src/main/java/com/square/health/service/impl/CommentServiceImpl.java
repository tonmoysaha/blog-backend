package com.square.health.service.impl;

import com.square.health.dto.CommentDto;
import com.square.health.model.Blogger;
import com.square.health.model.Comment;
import com.square.health.model.Post;
import com.square.health.repositoy.BloggerRepository;
import com.square.health.repositoy.CommentRepository;
import com.square.health.repositoy.PostRepository;
import com.square.health.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    Utility utility;

    @Override
    public List<CommentDto> getAllActiveComment(HttpServletRequest httpServletRequest, Long postId) {
        List<Comment> commentList = this.commentRepository.findByPostIdAndStatus(postId, StatusEnum.ACTIVE);
        if (!commentList.isEmpty())
            return commentList.stream().map(comment -> Converter.commentToCommentDto(comment)).collect(Collectors.toList());
        return Arrays.asList();
    }

    @Override
    public JSONObject createComment(HttpServletRequest httpServletRequest, CommentDto requestBodyDto) throws JSONException {
        Optional<Blogger> optionalBlogger = this.bloggerRepository.findById(Long.valueOf(requestBodyDto.getBloggerId()));
        Optional<Post> optionalPost = this.postRepository.findById(Long.valueOf(requestBodyDto.getPostId()));
        if (optionalBlogger.isPresent() && optionalPost.isPresent()){
            Comment comment = Converter.commentDtoToComment(requestBodyDto);
            comment.setBlogger(optionalBlogger.get());
            comment.setPost(optionalPost.get());
            comment.setStatus(StatusEnum.ACTIVE);
            this.commentRepository.save(comment);
            return utility.createResponse(HttpStatus.CREATED.value(), KeyWord.SUCCESS_CREATED, "Comment Created");
        }
        return utility.createResponse(HttpStatus.FOUND.value(), KeyWord.SUCCESS_EXIST, "Comment Created");
    }
}