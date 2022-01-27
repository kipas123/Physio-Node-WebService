package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "message_notification")
public class MessageNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmessageNotification;

    private Date post_date;

    @ManyToOne
    @JoinColumn(name="recipient_iduser")
    @JsonBackReference
    private User userRecipient;

    @ManyToOne
    @JoinColumn(name="sender_iduser")
    @JsonBackReference
    private User userSender;

    public  MessageNotification(){

    }
    public  MessageNotification(int notificationId){
        this.idmessageNotification = notificationId;
    }

    public MessageNotification(Date post_date, User userRecipient, User userSender) {
        this.post_date = post_date;
        this.userRecipient = userRecipient;
        this.userSender = userSender;
    }
}
