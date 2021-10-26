package com.square.health.repositoy;

import com.square.health.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikePost,Long> {

    long countByPostId(Long postId);

    long countByPostIdAndLikePostTrue(Long valueOf);

    Optional<LikePost> findByBloggerIdAndPostId(Long valueOf, Long valueOf1);
}
