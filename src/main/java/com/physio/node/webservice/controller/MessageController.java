package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Message.MessageReadModel;
import com.physio.node.webservice.model.DTO.Message.MessageWriteModel;
import com.physio.node.webservice.model.JPA.Message;
import com.physio.node.webservice.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

//    @GetMapping("/getMessage/{id}/{size}/{page}")
//    List<MessageReadModel> getMessageByAilmentId(@PathVariable int id, @PathVariable int size, @PathVariable int page){
//        return this.messageService.findMessageByAilmentId(id, size, page);
//    }
//    @GetMapping("/countMessage/{id}")
//    Long getMessageByAilmentId(@PathVariable int id){
//        return messageService.countMessageByAilmentId(id);
//    }
//



    @PostMapping("/sendMessage")
    ResponseEntity<?> sendMessage(@RequestBody MessageWriteModel messageWriteModel){
        return messageService.sendMessage(messageWriteModel);
    }
    @GetMapping("/getMessageRoom/{firstUserId}/{secondUserId}")
    public int getMessageRoom(@PathVariable int firstUserId, @PathVariable int secondUserId){
        return this.messageService.getMessageRoom(firstUserId, secondUserId);

    }


    @GetMapping("/getMessageByRoomId/{messageRoomId}/{size}/{page}")
    public List<MessageReadModel> getMessageByRoomId(@PathVariable int messageRoomId, @PathVariable int size, @PathVariable int page){
        return this.messageService.getMessageByRoomId(messageRoomId, size, page);
    }

    @GetMapping("/countMessageByMessageRoomId/{messageRoomId}")
    public Long countMessageByMessageRoomId(@PathVariable int messageRoomId){
        return this.messageService.countMessageByMessageRoomId(messageRoomId);
    }






}
