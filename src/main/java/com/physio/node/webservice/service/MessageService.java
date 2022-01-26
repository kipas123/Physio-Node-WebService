package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.Message.MessageReadModel;
import com.physio.node.webservice.model.DTO.Message.MessageWriteModel;
import com.physio.node.webservice.model.JPA.Message;
import com.physio.node.webservice.model.JPA.MessageRoom;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MessageRoomTaskRepository;
import com.physio.node.webservice.model.MessageTaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageTaskRepository messageTaskRepository;
    private MessageRoomTaskRepository messageRoomTaskRepository;

    public MessageService(MessageTaskRepository messageTaskRepository, MessageRoomTaskRepository messageRoomTaskRepository) {
        this.messageTaskRepository = messageTaskRepository;
        this.messageRoomTaskRepository = messageRoomTaskRepository;
    }

    public int getMessageRoom(int firstUserId, int secondUserId) {
        User firstUser = new User(firstUserId);
        User secondUser = new User(secondUserId);
        Optional<MessageRoom> messageRoom = this.messageRoomTaskRepository.findFirstByMembershipContainsAndMembershipContains(firstUser,secondUser);
        if(messageRoom.isEmpty()){
            MessageRoom newMessageRoom = new MessageRoom();
            List<User> listOfMembership = new ArrayList<>();
            listOfMembership.add(firstUser);
            listOfMembership.add(secondUser);
            newMessageRoom.setMembership(listOfMembership);
            newMessageRoom = messageRoomTaskRepository.save(newMessageRoom);
            return newMessageRoom.getIdmessageRoom();
        }
        return messageRoom.get().getIdmessageRoom();
    }

    public List<MessageReadModel> getMessageByRoomId(int messageRoomId, int size, int page) {

        List<Message> messageList = messageTaskRepository.findAllByMessageRoomIdmessageRoomOrderByPostDate(messageRoomId, PageRequest.of(page,size));
        return messageList.stream().map(MessageReadModel::new).collect(Collectors.toList());
    }

    public Long countMessageByMessageRoomId(int messageRoomId) {
        return messageTaskRepository.countMessageByAilmentId(messageRoomId);
    }

    public ResponseEntity<?> sendMessage(MessageWriteModel messageWriteModel){
        Message message = new Message(messageWriteModel);
        message.setPostDate(new Date());
        Message message1 = messageTaskRepository.save(message);
        if(message1==null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }
}
