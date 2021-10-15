package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.Mygroup_Users;
import com.physio.node.webservice.model.JPA.User;

import java.util.List;
import java.util.Optional;

public interface Mygroup_UsersTaskRepository {
    Mygroup_Users save(Mygroup_Users mygroup_users);
    List<Mygroup_Users> findAllByUser_IduserAndMygroup_Idmygroup(int userId, int mygroupId);
    void delete(Mygroup_Users mygroup_users);
    Optional<Mygroup_Users> findByUserIduserAndMygroupIdmygroup(int idUser, int groupId);
}
