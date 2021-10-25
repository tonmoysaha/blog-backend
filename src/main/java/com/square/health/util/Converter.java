package com.square.health.util;

import com.square.health.dto.*;
import com.square.health.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        bloggerDto.setBloggerId(String.valueOf(blogger.getId()));
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

    public static Post postDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setPostBody(postDto.getPostBody());
        return post;
    }

    public static PostDto postToPostDto(Post post) {
        PostDto postDto = new PostDto();
        Blogger blogger = post.getBlogger();
        postDto.setPostId(String.valueOf(post.getId()));
        postDto.setPostBody(post.getPostBody());
        postDto.setStatus(post.getStatus().name());
        postDto.setPostId(String.valueOf(post.getId()));
        postDto.setBloggerId(String.valueOf(blogger.getId()));
        postDto.setBloggerName(blogger.getUserName());
//        postDto.setTotalLikes(post.getLikePosts().size());
        return postDto;
    }

    public static Comment commentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        return comment;
    }

    public static CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setBloggerName(comment.getBlogger().getUserName());
        commentDto.setStatus(comment.getStatus().name());
        commentDto.setComment(comment.getComment());
        commentDto.setCommentId(String.valueOf(comment.getId()));
        return commentDto;
    }
}
