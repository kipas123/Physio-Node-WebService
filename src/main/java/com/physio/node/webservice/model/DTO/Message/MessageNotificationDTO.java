package com.physio.node.webservice.model.DTO.Message;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.JPA.MessageNotification;
import lombok.Data;

import java.util.Date;

@Data
public class MessageNotificationDTO {
    private int idMessageNotification;
    private UserReadModel userSender;
    private Date postDate;

    public MessageNotificationDTO(MessageNotification messageNotification){
        this.idMessageNotification = messageNotification.getIdmessageNotification();
        this.userSender = new UserReadModel(messageNotification.getUserSender());
        this.postDate = messageNotification.getPost_date();
    }
}
