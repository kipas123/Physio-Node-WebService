package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.MessageNotification;

import java.util.List;
import java.util.Optional;

public interface MessageNotificationTaskRepository {
    MessageNotification save(MessageNotification messageNotification);
    List<MessageNotification> findAllByUserRecipient_Iduser(int idUser);
    Optional<MessageNotification> findFirstByUserRecipient_IduserAndUserSender_Iduser(int userRecipient, int userSender);
    void deleteByIdmessageNotification(int messageNotification);
    void delete(MessageNotification messageNotification);
}
