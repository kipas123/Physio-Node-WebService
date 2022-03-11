package com.physio.node.webservice.service;

import com.physio.node.webservice.Exception.ResourceBadRequestException;
import com.physio.node.webservice.Exception.ResourceNotFoundException;
import com.physio.node.webservice.model.DTO.Message.MessageNotificationDTO;
import com.physio.node.webservice.model.DTO.Message.MessageReadModel;
import com.physio.node.webservice.model.DTO.Message.MessageWriteModel;
import com.physio.node.webservice.model.JPA.Message;
import com.physio.node.webservice.model.JPA.MessageNotification;
import com.physio.node.webservice.model.JPA.MessageRoom;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MessageNotificationTaskRepository;
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
    private MessageNotificationTaskRepository messageNotificationTaskRepository;

    public MessageService(MessageTaskRepository messageTaskRepository, MessageRoomTaskRepository messageRoomTaskRepository, MessageNotificationTaskRepository messageNotificationTaskRepository) {
        this.messageTaskRepository = messageTaskRepository;
        this.messageRoomTaskRepository = messageRoomTaskRepository;
        this.messageNotificationTaskRepository = messageNotificationTaskRepository;
    }

    public int getMessageRoom(int firstUserId, int secondUserId) {
        User firstUser = new User(firstUserId);
        User secondUser = new User(secondUserId);
        Optional<MessageRoom> messageRoom = this.messageRoomTaskRepository.findFirstByMembershipContainsAndMembershipContains(firstUser,secondUser);
        if(messageRoom.isEmpty()){ //uwaga
            MessageRoom newMessageRoom = new MessageRoom();
            List<User> listOfMembership = new ArrayList<>();
            listOfMembership.add(firstUser);
            listOfMembership.add(secondUser);
            newMessageRoom.setMembership(listOfMembership);
            try{
                newMessageRoom = messageRoomTaskRepository.save(newMessageRoom);
                return newMessageRoom.getIdmessageRoom();
            }catch (Exception e){
                throw new ResourceBadRequestException("Error: Bad argument");
            }
        }
        return messageRoom.get().getIdmessageRoom();
    }

    public List<MessageReadModel> getMessageByRoomId(int messageRoomId, int size, int page) {

        List<Message> messageList = messageTaskRepository.findAllByMessageRoomIdmessageRoomOrderByPostDateDesc(messageRoomId, PageRequest.of(page,size));
        if (messageList.isEmpty()) {
            throw new ResourceNotFoundException("Not found!");
        }
        return messageList.stream().map(MessageReadModel::new).collect(Collectors.toList());
    }

    public Long countMessageByMessageRoomId(int messageRoomId) {
        return messageTaskRepository.countMessageByAilmentId(messageRoomId);
    }

    public ResponseEntity<?> sendMessage(MessageWriteModel messageWriteModel){
        Date postDate = new Date();
        Optional<MessageRoom> messageRoom = messageRoomTaskRepository.findFirstByIdmessageRoom(messageWriteModel.getIdmessageRoom());
        List<User> listOfMessageRoomUser = messageRoom.get().getMembership();
        List<User> userRicipment = new ArrayList<>();
        listOfMessageRoomUser.forEach(user -> {
            if(user.getIduser()!=messageWriteModel.getIduser()){
                userRicipment.add(user);
            }
        });
        Optional<MessageNotification> messageNotification = messageNotificationTaskRepository.findFirstByUserRecipient_IduserAndUserSender_Iduser(userRicipment.get(0).getIduser(), messageWriteModel.getIduser());
        if(messageNotification.isEmpty()) {
            MessageNotification newMessageNotification = new MessageNotification(postDate, userRicipment.get(0), new User(messageWriteModel.getIduser()));
            messageNotificationTaskRepository.save(newMessageNotification);
        }else{
            messageNotification.get().setPost_date(postDate);
            messageNotificationTaskRepository.save(messageNotification.get());
        }

        Message message = new Message(messageWriteModel);

        message.setPostDate(new Date());
        Message message1 = messageTaskRepository.save(message);
        if(message1==null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    public List<MessageNotificationDTO> getUserMessageNotification(int userId){
        List<MessageNotificationDTO> messageNotificationDTO = messageNotificationTaskRepository.findAllByUserRecipient_Iduser(userId)
                .stream()
                .map(MessageNotificationDTO::new).collect(Collectors.toList());
        if (messageNotificationDTO.isEmpty()) {
            throw new ResourceNotFoundException("Not found!");
        }
        return messageNotificationDTO;
    }

    public void deleteMessageNotification(int messageNotification) {
        try{
            messageNotificationTaskRepository.delete(new MessageNotification(messageNotification));
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }
}
