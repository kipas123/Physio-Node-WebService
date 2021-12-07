package com.physio.node.webservice.adapter.visitSystem;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkDay;
import com.physio.node.webservice.model.UserWorkDayTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Set;

public interface SqlUserWorkDayTaskRepository extends UserWorkDayTaskRepository,JpaRepository<VisitSystemUserWorkDay, Integer> {
    @Query(value = "SELECT (w.userWorkDay) from VisitSystemUserWorkDay w WHERE w.userWorkDay >=:startDate and w.userWorkDay<=:endDate and w.user.iduser=:iduser")
    Set<Date> getUserWorkDate(@Param("startDate") java.sql.Date startDate, @Param("endDate") java.sql.Date endDate, @Param("iduser") int iduser);
}
