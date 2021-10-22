package com.square.health.service;

import com.square.health.model.Admin;

import java.util.Optional;

public interface AdminService {
    Admin getAdmin(String bloggerEmail);
}
