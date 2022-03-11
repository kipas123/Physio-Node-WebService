package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.AilmentIndication;

import java.util.Optional;

public interface AilmentIndicationTaskRepository {
    AilmentIndication save(AilmentIndication ailmentIndication);
}
