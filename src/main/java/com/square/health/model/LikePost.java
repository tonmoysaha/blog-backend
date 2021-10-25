package com.square.health.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LikePost extends BaseEntity{

    boolean likePost;
    boolean disLikePost;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Blogger blogger;

    public boolean isLikePost() {
        return likePost;
    }

    public void setLikePost(boolean likePost) {
        this.likePost = likePost;
    }

    public boolean isDisLikePost() {
        return disLikePost;
    }

    public void setDisLikePost(boolean disLikePost) {
        this.disLikePost = disLikePost;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Blogger getBlogger() {
        return blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }
}
