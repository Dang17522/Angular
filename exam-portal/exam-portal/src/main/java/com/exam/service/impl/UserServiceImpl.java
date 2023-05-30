package com.exam.service.impl;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepo;
import com.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public User create(User user, Set<UserRole> userRoles) throws Exception {
        User userResponse = userRepo.findByUsername(user.getUsername());
        if (userResponse != null) {
            throw new Exception("User already present!");
        } else {
            for (UserRole ur : userRoles) {
                roleRepo.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            userResponse = userRepo.save(user);
        }
        return userResponse;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

	@Override
	public <S extends User> S save(S entity) {
		return userRepo.save(entity);
	}
    
}
