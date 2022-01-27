package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.MessageRoom;
import com.physio.node.webservice.model.JPA.User;

import java.util.Optional;

public interface MessageRoomTaskRepository {
    Optional<MessageRoom> findFirstByMembershipContainsAndMembershipContains(User user, User user2);
    MessageRoom save(MessageRoom messageRoom);
    Optional<MessageRoom> findFirstByIdmessageRoom(int idMessageRoom);
}
