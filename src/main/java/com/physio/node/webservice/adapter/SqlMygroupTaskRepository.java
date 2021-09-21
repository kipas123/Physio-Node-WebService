package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.JPA.Mygroup;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SqlMygroupTaskRepository extends MygroupTaskRepository,JpaRepository<Mygroup, Integer> {
}
