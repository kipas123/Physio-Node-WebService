package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.AilmentIndicationTaskRepository;
import com.physio.node.webservice.model.JPA.AilmentIndication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlAilmentIndicationTaskRepository extends AilmentIndicationTaskRepository, JpaRepository<AilmentIndication, Integer> {
}
