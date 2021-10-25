package com.square.health.repositoy;

import com.square.health.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikePost,Long> {

    Optional<LikePost> findByBloggerId(Long valueOf);
}
