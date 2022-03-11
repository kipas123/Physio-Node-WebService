package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;

import java.util.List;
import java.util.Optional;

public interface UserServiceTypeTaskRepository {
    VisitSystemUserServiceType save(VisitSystemUserServiceType visitSystemUserServiceType);
    List<VisitSystemUserServiceType> findAllByUserOwnerIduserAndUserServiceTypeActiveOrderByUserServiceTypeDuration(int idUser, boolean active);
    Optional<VisitSystemUserServiceType> findFirstByUserOwnerIduserOrderByUserServiceTypeDuration(int idUser);
    Optional<VisitSystemUserServiceType> findByIdUserServiceType(int idService);
    void delete(VisitSystemUserServiceType visitSystemUserServiceType);
}
