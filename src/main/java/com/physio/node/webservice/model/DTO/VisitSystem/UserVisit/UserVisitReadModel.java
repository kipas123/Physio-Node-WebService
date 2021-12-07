package com.physio.node.webservice.model.DTO.VisitSystem.UserVisit;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType.UserServiceTypeReadModel;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisit;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class UserVisitReadModel {
    private UserReadModel userDTO;
    private LocalTime userVisitTime;
    private UserServiceTypeReadModel userServiceTypeDTO;
    private Date visitDate;
    private UserReadModel visitProvider;

    public UserVisitReadModel(VisitSystemUserVisit visitSystemUserVisit){
        this.userDTO = new UserReadModel(visitSystemUserVisit.getUser());
        this.userVisitTime = visitSystemUserVisit.getUserVisitTime();
        this.userServiceTypeDTO = new UserServiceTypeReadModel(visitSystemUserVisit.getVisitSystemUserServiceType());
        this.visitDate = visitSystemUserVisit.getVisitSystemUserWorkDay().getUserWorkDay();
        this.visitProvider = new UserReadModel(visitSystemUserVisit.getUser());
    }
}
