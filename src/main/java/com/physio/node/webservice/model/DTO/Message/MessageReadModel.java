package com.physio.node.webservice.model.DTO.Message;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.JPA.Message;
import lombok.Data;

import java.util.Date;

@Data
public class MessageReadModel {

private int idmessage;
private String messageText;
private Date postDate;
private UserReadModel messageOwner;
private int idmessageRoom;

    public MessageReadModel(Message message){
        this.idmessage = message.getIdmessage();
        this.messageText = message.getMessageText();
        this.messageOwner = new UserReadModel(message.getUser());
        this.postDate = message.getPostDate();
//        this.idmessageRoom = message.getMessageRoom().getIdmessageRoom();
    }
}
