package com.exam.service;

import java.util.Set;

import com.exam.entity.User;
import com.exam.entity.UserRole;

public interface UserService {
    User create(User user, Set<UserRole> userRoles) throws Exception;

    User findByUsername(String username);

    void deleteById(Long id);

	<S extends User> S save(S entity);
}
