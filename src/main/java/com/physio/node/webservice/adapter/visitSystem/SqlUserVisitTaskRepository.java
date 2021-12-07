package com.physio.node.webservice.adapter.visitSystem;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisit;
import com.physio.node.webservice.model.UserVisitTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SqlUserVisitTaskRepository extends UserVisitTaskRepository, JpaRepository<VisitSystemUserVisit, Integer> {
    @Query(value = "SELECT (v) from VisitSystemUserVisit v WHERE v.visitSystemUserWorkDay.userWorkDay >=:startDate and v.user.iduser=:iduser")
    List<VisitSystemUserVisit> getUserVisitFromDate(@Param("startDate") java.sql.Date fromDate, @Param("iduser") int iduser);



}
