package com.physio.node.webservice.adapter.visitSystem;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkHour;
import com.physio.node.webservice.model.UserWorkHourTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserWorkHourTaskRepository extends UserWorkHourTaskRepository,JpaRepository<VisitSystemUserWorkHour, Integer> {
}
