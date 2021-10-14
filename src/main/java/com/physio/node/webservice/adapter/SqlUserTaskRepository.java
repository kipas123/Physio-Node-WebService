package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface SqlUserTaskRepository extends UserTaskRepository, JpaRepository<User, Integer> {
      @Query("SELECT u FROM User u WHERE u.userEmail=:value OR u.userName=:value OR u.userSurname=:value")
      List<User> findAllByUserNameOrUserSurname(@Param("value") String value);

      @Query("SELECT u FROM User u WHERE u.userEmail=:value OR u.userName=:value OR u.userSurname=:value AND u.userRole.roleName = 'unverified'")
      List<User> findAllUnverifiedUserByUserNameOrUserSurname(@Param("value") String value);

      @Query("SELECT u FROM User u WHERE u.userRole.roleName='physiotherapist' OR u.userRole.roleName='admin' OR u.userRole.roleName='coach'")
      List<User> findAllUserWithModRole();


      @Query("SELECT COUNT(u) FROM User u WHERE u.userRole.roleName='unverified'")
      Long countUnverifiedUser();

//    User findByIduser(int id User);
}
