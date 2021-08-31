package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.AilmentTaskRepository;
import com.physio.node.webservice.model.JPA.Ailment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SqlAilmentTaskRepository extends AilmentTaskRepository, JpaRepository<Ailment, Integer> {
}
