package com.square.health.repositoy;

import com.square.health.model.Post;
import com.square.health.model.Role;
import com.square.health.util.enumutil.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByIdAndBloggerId(Long postId, Long bloggerId);

    List<Post> findByStatus(StatusEnum active);
}
