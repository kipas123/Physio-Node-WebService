package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.Message.MessageReadModel;
import com.physio.node.webservice.model.DTO.Message.MessageWriteModel;
import com.physio.node.webservice.model.JPA.Message;
import com.physio.node.webservice.model.MessageTaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageTaskRepository messageTaskRepository;

    public MessageService(MessageTaskRepository messageTaskRepository) {
        this.messageTaskRepository = messageTaskRepository;
    }

    public List<MessageReadModel> findMessageByAilmentId(int idailment, int size, int page){
        return messageTaskRepository.findAllByAilmentIdailmentOrderByPostDateDesc(idailment, PageRequest.of(page,size))
                .stream()
                .map(MessageReadModel::new)
                .collect(Collectors.toList());
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
    public Long countMessageByAilmentId(int ailmentid){
        return messageTaskRepository.countMessageByAilmentId(ailmentid);
    }

}
