package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.Message;
import com.physio.node.webservice.model.MessageTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SqlMessageTaskRepository extends MessageTaskRepository,JpaRepository<Message, Integer> {
    @Query("SELECT COUNT(u) FROM Message u WHERE u.ailment.idailment=:idailment")
    Long countMessageByAilmentId(@Param("idailment") int idailment);
}
