package com.physio.node.webservice.model;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.physio.node.webservice.model.JPA.AilmentFiles;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface AilmentFilesTaskRepository {
    AilmentFiles save(AilmentFiles ailmentFiles);
    Optional<AilmentFiles> findByIdailmentFiles(String id);
    List<AilmentFiles> findAll();
    List<AilmentFiles> findAllByAilment_IdailmentOrderByDataAsc(int idAilment);
    void delete(AilmentFiles ailmentFiles);

}
