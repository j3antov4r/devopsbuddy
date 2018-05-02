package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persitence.domain.backend.Role;
import com.devopsbuddy.backend.persitence.domain.backend.User;
import com.devopsbuddy.backend.persitence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService uservice;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldCreateNewUser() throws Exception {

        Set<Role> urSet = new HashSet<>();
        User myUser = UserUtils.createBasicUser();
        urSet.add(new Role(RolesEnum.BASIC));

        User savedUser= uservice.createUser(myUser, PlanEnum.BASIC, urSet);
        Assert.assertNotNull(savedUser);
        Assert.assertNotNull(savedUser.getId());


    }
}
