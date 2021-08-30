package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserTaskRepository extends UserTaskRepository, JpaRepository<User, Integer> {
//    @Query("select b from User b join fetch b.groups where b.userName = :name")
//    User getUser(@Param("name") String userName);
}
