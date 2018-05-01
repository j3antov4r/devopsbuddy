package com.devopsbuddy.backend.persitence.repositories;

import com.devopsbuddy.backend.persitence.domain.backend.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
