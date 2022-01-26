package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.MessageRoom;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MessageRoomTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SqlMessageRoomTaskRepository extends MessageRoomTaskRepository, JpaRepository<MessageRoom, Integer> {
//    List<MessageRoom> findAllByFirstUserIduser

}
