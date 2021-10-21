package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageTaskRepository {
    List<Message> findAllByAilmentIdailmentOrderByPostDateDesc(int idailment, Pageable pageable);
    Message save(Message entity);
    Long countMessageByAilmentId(int idailment);
}
