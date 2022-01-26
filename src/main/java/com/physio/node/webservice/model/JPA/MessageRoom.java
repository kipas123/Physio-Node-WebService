package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "message_room")
@Data
public class MessageRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmessageRoom;


    @OneToMany(mappedBy="messageRoom")
    @JsonManagedReference
    private List<Message> message;

    @ManyToMany
    @JoinTable(name = "message_room_membership",
            joinColumns = @JoinColumn(name = "message_room_idmessage_room"),
            inverseJoinColumns = @JoinColumn(name = "user_iduser"))
    List<User> membership;

    public MessageRoom(){}

    public MessageRoom(int idmessageRoom){
        this.idmessageRoom = idmessageRoom;
    }
}
