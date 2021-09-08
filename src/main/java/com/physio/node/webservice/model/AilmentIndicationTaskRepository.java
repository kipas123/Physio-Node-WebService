package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.AilmentIndication;

public interface AilmentIndicationTaskRepository {
    AilmentIndication save(AilmentIndication ailmentIndication);
}
