package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persitence.domain.backend.Plan;
import com.devopsbuddy.backend.persitence.domain.backend.Role;
import com.devopsbuddy.backend.persitence.domain.backend.User;
import com.devopsbuddy.backend.persitence.domain.backend.UserRole;
import com.devopsbuddy.backend.persitence.repositories.PlanRepository;
import com.devopsbuddy.backend.persitence.repositories.RoleRepository;
import com.devopsbuddy.backend.persitence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
@Transactional
public class RepositoriesIntegrationTest {

    @Autowired
    private PlanRepository planRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    private static final Integer BASIC_PLAN_ID = 1;
    private static final Integer BASIC_ROLE_ID = 1;


    @Before
    public void init(){
        Assert.assertNotNull(planRepo);
        Assert.assertNotNull(roleRepo);
        Assert.assertNotNull(userRepo);
    }

    private Plan createBasicPlan(){
        Plan plan= new Plan();
        plan.setId(BASIC_PLAN_ID);
        plan.setName("Basic");
        return plan;

    }

    private Role createRole(){
        Role role = new Role();
        role.setId(BASIC_ROLE_ID);
        role.setName("USER_ROLE");
        return role;
    }

    private User createBasicUser(){
        User user= new User();
        user.setUsername("basicUser");
        user.setPassword("1234");
        user.setEmail("basic@123.com");
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setPhoneNumber("12222221");
        user.setCountry("VE");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");

        return  user;
    }

    @Test
    public void shouldCreateAPlan() throws Exception{
        Plan basicPlan= createBasicPlan();
        planRepo.save(basicPlan);
        Plan myPlan= planRepo.getOne(BASIC_PLAN_ID);
        Assert.assertNotNull(myPlan);

    }

    @Test
    public void shouldCreateARole() throws Exception{
        Role userRole = createRole();
        roleRepo.save(userRole);
        Role myRol = roleRepo.getOne(BASIC_ROLE_ID);
        Assert.assertNotNull(myRol);
        //Assert.assertEquals(userRole, myRol);

    }

    @Test
    public void shouldCreateAnUser() throws Exception {
        Plan myPlan = createBasicPlan();
        myPlan= planRepo.save(myPlan);

        Assert.assertNotNull(myPlan);
        Role myRole = createRole();
        myRole = roleRepo.save(myRole);
        Assert.assertNotNull(myRole);

        User myUser = createBasicUser();
        myUser.setPlan(myPlan);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole myUserRol = new UserRole();
        myUserRol.setRole(myRole);
        myUserRol.setUser(myUser);
        userRoles.add(myUserRol);

        myUser.getUserRoles().addAll(userRoles);

        myUser = userRepo.save(myUser);

        User savedUser= userRepo.getOne(myUser.getId());

        Assert.assertNotNull(savedUser);
        Assert.assertTrue(savedUser.getId() != 0 );
        Assert.assertNotNull(savedUser.getPlan().getId());
        Set<UserRole> savedUserRoles = savedUser.getUserRoles();
        for (UserRole ur: userRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }
}
