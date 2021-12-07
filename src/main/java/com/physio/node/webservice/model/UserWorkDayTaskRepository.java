package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkDay;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

public interface UserWorkDayTaskRepository {
    Optional<VisitSystemUserWorkDay> findByUserWorkDayAndUserIduser(Date date, int idUser);
    VisitSystemUserWorkDay save(VisitSystemUserWorkDay visitSystem_userWorkDay);
    Set<java.util.Date> getUserWorkDate(java.sql.Date startDate, java.sql.Date endDate, int iduser);
}
