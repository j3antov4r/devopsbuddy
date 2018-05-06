package com.devopsbuddy;

import com.devopsbuddy.backend.persitence.domain.backend.Role;
import com.devopsbuddy.backend.persitence.domain.backend.User;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication

public class DevopsbuddyApplication implements CommandLineRunner{

	private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);

	@Autowired
	private UserService service;



	public static void main(String[] args) {

		SpringApplication.run(DevopsbuddyApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		String username="user";
		String email= "user@gmail.com";
		User user= UserUtils.createBasicUser(username, email);
		Set<Role> myRoles = new HashSet<>();
		LOG.debug("Creating user with username {}", user.getUsername());
		service.createUser(user, PlanEnum.BASIC, myRoles);
		LOG.info("Created {} user successfully", user.getUsername());
	}
}
