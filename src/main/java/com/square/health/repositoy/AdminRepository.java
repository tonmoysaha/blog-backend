package com.square.health.repositoy;

import com.square.health.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByUserName(String email);

    Admin findByEmail(String email);
}
