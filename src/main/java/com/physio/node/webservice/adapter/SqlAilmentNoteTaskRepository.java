package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.AilmentNoteTaskRepository;
import com.physio.node.webservice.model.JPA.AilmentNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlAilmentNoteTaskRepository extends AilmentNoteTaskRepository, JpaRepository<AilmentNote, Integer> {
}
