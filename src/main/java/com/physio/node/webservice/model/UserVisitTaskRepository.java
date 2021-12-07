package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisit;

import java.sql.Date;
import java.util.List;

public interface UserVisitTaskRepository {
    List<VisitSystemUserVisit> findAllByVisitSystemUserWorkDay_UserWorkDayAndVisitSystemUserWorkDayUserIduser(Date date, int userId);
    List<VisitSystemUserVisit> findAllByUserIduserAndVisitSystemUserVisitStatusIdUserVisitStatus(int userId, int visitStatus);
    VisitSystemUserVisit save(VisitSystemUserVisit visitSystemUserVisit);
    List<VisitSystemUserVisit> getUserVisitFromDate(java.sql.Date fromDate,int iduser);
    List<VisitSystemUserVisit> findAllByUserIduser(int idUser);
    List<VisitSystemUserVisit> findAllByVisitSystemUserWorkDay_UserIduser(int idUser);
}
