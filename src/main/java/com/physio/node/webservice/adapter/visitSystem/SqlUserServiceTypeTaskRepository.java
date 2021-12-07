package com.physio.node.webservice.adapter.visitSystem;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;
import com.physio.node.webservice.model.UserServiceTypeTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserServiceTypeTaskRepository extends UserServiceTypeTaskRepository, JpaRepository<VisitSystemUserServiceType, Integer> {
}
