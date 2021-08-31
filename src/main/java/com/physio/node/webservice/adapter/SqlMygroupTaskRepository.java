package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.JPA.Mygroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SqlMygroupTaskRepository extends MygroupTaskRepository,JpaRepository<Mygroup, Integer> {
}
