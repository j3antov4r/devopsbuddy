package com.devopsbuddy.backend.persitence.repositories;

import com.devopsbuddy.backend.persitence.domain.backend.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long > {
}
