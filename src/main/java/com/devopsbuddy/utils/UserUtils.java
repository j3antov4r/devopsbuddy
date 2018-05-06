package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persitence.domain.backend.User;

public class UserUtils {

    private UserUtils() {
        throw new AssertionError("Non Instantiable");
    }

    public static User createBasicUser(String username, String email){
        User user= new User();
        user.setUsername(username);
        user.setPassword("1234");
        user.setEmail(email);
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setPhoneNumber("12222221");
        user.setCountry("VE");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");

        return  user;
    }
}
