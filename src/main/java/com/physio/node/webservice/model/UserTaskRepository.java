package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTaskRepository {
    List<User> findAll();
    Optional<User> findByIduser(int id);
    List<User> findByUserName(String username);
    User save(User entity);
  Optional<User> findByUserEmail(String email);
    List<User> findAllByUserNameOrUserSurname(String value);
    List<User> findAllUnverifiedUserByUserNameOrUserSurname(String value);
    List<User> findAllVerifiedUserByUserNameOrUserSurname(String value);
    List<User> findAllByUserRole_RoleName(String roleName, Pageable pageable);
    List<User> findAllByUserRole_RoleName(String roleName);
    Long countUnverifiedUser();
    List<User> findAllUserWithModRole();
    Optional<User> findByResetPasswordToken(String resetPasswordToken);
    Optional<User> findByUserPassword(String password);

}
