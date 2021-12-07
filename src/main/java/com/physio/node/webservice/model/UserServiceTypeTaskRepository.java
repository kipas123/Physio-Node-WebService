package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;

import java.util.List;
import java.util.Optional;

public interface UserServiceTypeTaskRepository {
    VisitSystemUserServiceType save(VisitSystemUserServiceType visitSystemUserServiceType);
    List<VisitSystemUserServiceType> findAllByUserOwnerIduserOrderByUserServiceTypeDuration(int idUser);
    Optional<VisitSystemUserServiceType> findFirstByUserOwnerIduserOrderByUserServiceTypeDuration(int idUser);
    void delete(VisitSystemUserServiceType visitSystemUserServiceType);
}
