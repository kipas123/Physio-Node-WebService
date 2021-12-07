package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkHour;

import java.sql.Date;
import java.util.List;

public interface UserWorkHourTaskRepository {
    List<VisitSystemUserWorkHour> findAllByVisitSystemUserWorkDay_UserWorkDayAndVisitSystemUserWorkDayUserIduser(Date date, int idUser);
    VisitSystemUserWorkHour save(VisitSystemUserWorkHour visitSystem_userWorkHour);
    void delete (VisitSystemUserWorkHour visitSystemUserWorkHour);
}
