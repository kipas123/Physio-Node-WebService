package com.physio.node.webservice.adapter.visitSystem;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisitStatus;
import com.physio.node.webservice.model.UserVisitStatusTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserVisitStatusTaskRepository extends UserVisitStatusTaskRepository, JpaRepository<VisitSystemUserVisitStatus, Integer> {
}
