package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persitence.domain.backend.Plan;
import com.devopsbuddy.backend.persitence.domain.backend.Role;
import com.devopsbuddy.backend.persitence.domain.backend.User;
import com.devopsbuddy.backend.persitence.domain.backend.UserRole;
import com.devopsbuddy.backend.persitence.repositories.PlanRepository;
import com.devopsbuddy.backend.persitence.repositories.RoleRepository;
import com.devopsbuddy.backend.persitence.repositories.UserRepository;
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



    @Before
    public void init(){
        Assert.assertNotNull(planRepo);
        Assert.assertNotNull(roleRepo);
        Assert.assertNotNull(userRepo);
    }

    private Plan createBasicPlan(){
        Plan plan= new Plan(PlanEnum.BASIC);
        return plan;

    }

    private Plan createBasicPlan(PlanEnum planEnum){
        Plan plan= new Plan(planEnum);
        return plan;

    }

    private User storeBasicUser(String username, String email){
        Plan myPlan = createBasicPlan();
        myPlan= planRepo.save(myPlan);

        Assert.assertNotNull(myPlan);
        Role myRole = createRole();
        myRole = roleRepo.save(myRole);
        Assert.assertNotNull(myRole);

        User myUser = UserUtils.createBasicUser(username, email);
        myUser.setPlan(myPlan);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole myUserRol = new UserRole(myUser, myRole);
        userRoles.add(myUserRol);

        myUser.getUserRoles().addAll(userRoles);

        myUser = userRepo.save(myUser);

        return myUser;
    }



    private Role createRole(){
        Role role = new Role(RolesEnum.BASIC);

        return role;
    }

    private Role createRole(RolesEnum rolesEnum){
        Role role = new Role(rolesEnum);

        return role;
    }


    @Test
    public void shouldCreateAPlan() throws Exception{
        Plan basicPlan= createBasicPlan();
        planRepo.save(basicPlan);
        Plan myPlan= planRepo.getOne(PlanEnum.BASIC.getId());
        Assert.assertNotNull(myPlan);

    }

    @Test
    public void shouldCreateARole() throws Exception{
        Role userRole = createRole(RolesEnum.BASIC);
        roleRepo.save(userRole);
        Role myRol = roleRepo.getOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(myRol);
        //Assert.assertEquals(userRole, myRol);

    }

    @Test
    public void shouldCreateAnUser() throws Exception {

        User myUser = storeBasicUser("user", "user@gmail.com");

        User savedUser= userRepo.getOne(myUser.getId());

        Assert.assertNotNull(savedUser);
        Assert.assertTrue(savedUser.getId() != 0 );
        Assert.assertNotNull(savedUser.getPlan().getId());
        Set<UserRole> savedUserRoles = savedUser.getUserRoles();
        for (UserRole ur: savedUserRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

    @Test
    public void shouldDeleteUser() {
        User myUser = storeBasicUser("user2", "user2@gmail.com");
        userRepo.deleteById(myUser.getId());
        Assert.assertTrue(!userRepo.existsById(myUser.getId()));
    }
}
