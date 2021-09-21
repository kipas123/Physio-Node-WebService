package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Ailment;

import java.util.List;
import java.util.Optional;

public interface AilmentTaskRepository {
    List<Ailment> findAllByUserIduser(int user);
    Optional<Ailment> findFirstByIdailment(int id);
    Ailment save(Ailment ailment);

}
