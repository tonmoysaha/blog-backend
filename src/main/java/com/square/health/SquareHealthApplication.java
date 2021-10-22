package com.square.health;

import com.square.health.model.Admin;
import com.square.health.model.Role;
import com.square.health.repositoy.AdminRepository;
import com.square.health.repositoy.RoleRepository;
import com.square.health.util.enumutil.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class SquareHealthApplication {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${admin.square}")
    private String adminEmail;

    @Value("${admin.password.square}")
    private String adminPassword;

    public static void main(String[] args) {
        SpringApplication.run(SquareHealthApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(AdminRepository adminRepository, RoleRepository roleRepository) {
        return args -> {
        	Optional<Role> byRole = roleRepository.findByRole(RoleEnum.ROLE_ADMIN.name());
            if (!byRole.isPresent()) {
                Role role = new Role();
                role.setRole(RoleEnum.ROLE_ADMIN.name());
                roleRepository.save(role);
            }
			Admin byEmail = adminRepository.findByEmail(adminEmail);
			if (byEmail == null) {
                Admin admin = new Admin();
                admin.setPassword(bCryptPasswordEncoder.encode(adminPassword).toString());
                admin.setUserName("tonmoy saha opi");
                admin.setEmail(adminEmail);
                Optional<Role> role = roleRepository.findByRole(RoleEnum.ROLE_ADMIN.name());
                admin.setRoles(Arrays.asList(role.get()));
                adminRepository.save(admin);
            }
        };
    }

}
