package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Message.MessageNotificationDTO;
import com.physio.node.webservice.model.DTO.Message.MessageReadModel;
import com.physio.node.webservice.model.DTO.Message.MessageWriteModel;
import com.physio.node.webservice.model.JPA.Message;
import com.physio.node.webservice.service.MessageService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getMessageRoom(@PathVariable int firstUserId, @PathVariable int secondUserId){
        int messageRoodId = this.messageService.getMessageRoom(firstUserId, secondUserId);
        return new ResponseEntity<>(messageRoodId, HttpStatus.OK);

    }


    @GetMapping("/getMessageByRoomId/{messageRoomId}/{size}/{page}")
    public ResponseEntity<List<MessageReadModel>> getMessageByRoomId(@PathVariable int messageRoomId, @PathVariable int size, @PathVariable int page){
        List<MessageReadModel> messageReadModel = messageService.getMessageByRoomId(messageRoomId, size, page);
        return new ResponseEntity<>(messageReadModel, HttpStatus.OK);
    }

    @GetMapping("/countMessageByMessageRoomId/{messageRoomId}")
    public ResponseEntity<Long> countMessageByMessageRoomId(@PathVariable int messageRoomId){
        Long counter = messageService.countMessageByMessageRoomId(messageRoomId);
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }

    @GetMapping("/getUserMessageNotification/{userId}")
    public ResponseEntity<List<MessageNotificationDTO>> getUserMessageNotification(@PathVariable int userId){
        List<MessageNotificationDTO> messageNotificationDTO = messageService.getUserMessageNotification(userId);
        return new ResponseEntity<>(messageNotificationDTO, HttpStatus.OK);
    }

    @GetMapping("/deleteUserMessageNotification/{messageNotification}")
    public ResponseEntity<?> deleteUserMessageNotification(@PathVariable int messageNotification){
        messageService.deleteMessageNotification(messageNotification);
        return ResponseEntity.ok().build();
    }






}
