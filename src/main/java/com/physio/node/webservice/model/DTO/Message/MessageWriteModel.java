package com.physio.node.webservice.model.DTO.Message;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.JPA.Message;
import lombok.Data;

import java.util.Date;

@Data
public class MessageWriteModel {

private String messageText;
private int iduser;
private int idailment;


    public MessageWriteModel(String messageText, int iduser, int idailment){
        this.messageText = messageText;
        this.iduser = iduser;
        this.idailment = idailment;
    }
}
