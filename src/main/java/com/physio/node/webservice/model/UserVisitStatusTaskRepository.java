package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisitStatus;

import java.util.Optional;

public interface UserVisitStatusTaskRepository {
    Optional<VisitSystemUserVisitStatus> findByIdUserVisitStatus(int id);
}
