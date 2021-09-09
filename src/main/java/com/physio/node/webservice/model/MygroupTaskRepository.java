package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Mygroup;

import java.util.List;

public interface MygroupTaskRepository {
    List<Mygroup> findAllByUsers_Iduser(int id);
    Mygroup findByIdmygroup(int id);
    List<Mygroup> findAll();
    Mygroup save(Mygroup entity);
}
