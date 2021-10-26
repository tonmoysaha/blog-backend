package com.square.health.model;

import com.square.health.util.enumutil.StatusEnum;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    @Column(length = 1000)
    private String postBody;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    private Blogger blogger;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<LikePost> likePosts;

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Blogger getBlogger() {
        return blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }

    public List<LikePost> getLikePosts() {
        return likePosts;
    }

    public void setLikePosts(List<LikePost> likePosts) {
        this.likePosts = likePosts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
