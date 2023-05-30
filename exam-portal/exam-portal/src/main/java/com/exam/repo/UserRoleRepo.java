package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Long>{

}
