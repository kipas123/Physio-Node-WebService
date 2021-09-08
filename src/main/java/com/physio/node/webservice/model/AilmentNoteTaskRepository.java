package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.AilmentNote;

public interface AilmentNoteTaskRepository {
    AilmentNote save(AilmentNote ailmentNote);
}
