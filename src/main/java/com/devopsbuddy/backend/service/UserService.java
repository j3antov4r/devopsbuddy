package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persitence.domain.backend.Plan;
import com.devopsbuddy.backend.persitence.domain.backend.Role;
import com.devopsbuddy.backend.persitence.domain.backend.User;
import com.devopsbuddy.backend.persitence.domain.backend.UserRole;
import com.devopsbuddy.backend.persitence.repositories.PlanRepository;
import com.devopsbuddy.backend.persitence.repositories.RoleRepository;
import com.devopsbuddy.backend.persitence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Transactional
    public User createUser(User user, PlanEnum planEnum, Set<Role> userRoles){

        String encryptedPass= passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPass);

        Plan myPlan = new Plan(planEnum);

        if(!planRepository.existsById(planEnum.getId())){
            myPlan = planRepository.save(myPlan);
        }

        user.setPlan(myPlan);

        Set<UserRole> userRolesSet= new HashSet<>();
        for (Role ur: userRoles) {
            if(roleRepository.existsById(ur.getId())){
               Role myRole = roleRepository.getOne(ur.getId());
               UserRole myUserRole = new UserRole(user, myRole);
               userRolesSet.add(myUserRole);
            }
        }

        user.getUserRoles().addAll(userRolesSet);

        user = userRepository.save(user);

        return user;
    }

}
