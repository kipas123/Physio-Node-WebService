package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.AilmentFilesTaskRepository;
import com.physio.node.webservice.model.JPA.AilmentFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SqlAilmentFilesTaskRepository extends AilmentFilesTaskRepository,JpaRepository<AilmentFiles, String> {

}
