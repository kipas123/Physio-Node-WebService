package com.physio.node.webservice.adapter.visitSystem;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;
import com.physio.node.webservice.model.UserServiceTypeTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SqlUserServiceTypeTaskRepository extends UserServiceTypeTaskRepository, JpaRepository<VisitSystemUserServiceType, Integer> {
}
