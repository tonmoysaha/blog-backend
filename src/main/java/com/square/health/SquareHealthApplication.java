package com.square.health;

import com.square.health.repositoy.AdminRepository;
import com.square.health.repositoy.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SquareHealthApplication {

	@Autowired
	private AdminRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SquareHealthApplication.class, args);
	}

}
