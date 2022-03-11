package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UserVisitTaskRepository {
    List<VisitSystemUserVisit> findAllImportantVisit(java.sql.Date date,int iduser);
    List<VisitSystemUserVisit> findAllByVisitSystemUserWorkDay_UserWorkDayAndVisitSystemUserWorkDayUserIduserAndVisitSystemUserVisitStatus_IdUserVisitStatus(Date date, int userId, int idVisitStatus);
    List<VisitSystemUserVisit> findAllByUserIduserAndVisitSystemUserVisitStatusIdUserVisitStatus(int userId, int visitStatus);
    VisitSystemUserVisit save(VisitSystemUserVisit visitSystemUserVisit);
    List<VisitSystemUserVisit> getUserVisitFromDate(java.sql.Date fromDate,int iduser);
    List<VisitSystemUserVisit> findAllByUserIduser(int idUser);
//    List<VisitSystemUserVisit> findAllByVisitSystemUserWorkDay_UserIduser(int idUser, Pageable pageable);
    List<VisitSystemUserVisit> findAllByVisitSystemUserWorkDay_UserIduserAndVisitSystemUserVisitStatus_IdUserVisitStatus(int idUser, Pageable pageable, int statusId);
    Optional<VisitSystemUserVisit> findByIdUserVisit(int idVisit);
    Long countProviderVisit(@Param("iduser") int iduser);
}
