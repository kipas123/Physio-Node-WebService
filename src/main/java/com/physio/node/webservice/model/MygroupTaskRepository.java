package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.User;

import java.util.List;

public interface MygroupTaskRepository {
    Mygroup findByIdmygroup(int id);
    List<Mygroup> findAll();
    Mygroup save(Mygroup entity);
    List<Mygroup> findAllByMygroupOwner(User user);
}
