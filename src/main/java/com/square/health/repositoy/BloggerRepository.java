package com.square.health.repositoy;

import com.square.health.model.Admin;
import com.square.health.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloggerRepository extends JpaRepository<Blogger,Long> {
}
