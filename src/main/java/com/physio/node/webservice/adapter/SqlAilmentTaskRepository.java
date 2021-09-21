package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.AilmentTaskRepository;
import com.physio.node.webservice.model.JPA.Ailment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SqlAilmentTaskRepository extends AilmentTaskRepository, JpaRepository<Ailment, Integer> {
}
