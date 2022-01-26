package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.physio.node.webservice.model.DTO.Message.MessageReadModel;
import com.physio.node.webservice.model.DTO.Message.MessageWriteModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="message")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmessage")
    private int idmessage;

    @Column(name = "message_text", columnDefinition = "MEDIUMTEXT")
    private String messageText;

    @Column(name = "post_date")
    private Date postDate;

    @ManyToOne
    @JsonBackReference
    private MessageRoom messageRoom;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Message() {
    }
    public Message(MessageWriteModel messageWriteModel){
        this.messageText = messageWriteModel.getMessageText();
        this.user = new User(messageWriteModel.getIduser());
        this.messageRoom = new MessageRoom(messageWriteModel.getIdmessageRoom());
    }
}
