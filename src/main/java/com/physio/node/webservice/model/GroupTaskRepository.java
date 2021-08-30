package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Mygroup;

import java.util.List;

public interface GroupTaskRepository {
    List<Mygroup> findAllByUsers_Iduser(int id);
}
