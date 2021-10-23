package com.square.health.repositoy;

import com.square.health.model.Comment;
import com.square.health.model.Post;
import com.square.health.util.enumutil.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostIdAndStatus(Long postId, StatusEnum active);
}
