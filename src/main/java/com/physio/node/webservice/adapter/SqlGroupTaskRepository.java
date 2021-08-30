package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.GroupTaskRepository;
import com.physio.node.webservice.model.JPA.Mygroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SqlGroupTaskRepository extends GroupTaskRepository,JpaRepository<Mygroup, Integer> {
}
