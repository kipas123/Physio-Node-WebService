package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Ailment;

import java.util.List;

public interface AilmentTaskRepository {
    List<Ailment> findAllByUserIduser(int user);
    List<Ailment> findByIdailment(int id);
    Ailment save(Ailment ailment);
}
