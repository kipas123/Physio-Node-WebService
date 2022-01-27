package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.MessageNotification;
import com.physio.node.webservice.model.MessageNotificationTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlMessageNotificationTaskRepository extends MessageNotificationTaskRepository, JpaRepository<MessageNotification, Integer> {
}
