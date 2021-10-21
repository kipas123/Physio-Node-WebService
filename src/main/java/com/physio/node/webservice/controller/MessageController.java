package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Message.MessageReadModel;
import com.physio.node.webservice.model.DTO.Message.MessageWriteModel;
import com.physio.node.webservice.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/getMessage/{id}/{size}/{page}")
    List<MessageReadModel> getMessageByAilmentId(@PathVariable int id, @PathVariable int size, @PathVariable int page){
        return this.messageService.findMessageByAilmentId(id, size, page);
    }
    @GetMapping("/countMessage/{id}")
    Long getMessageByAilmentId(@PathVariable int id){
        return messageService.countMessageByAilmentId(id);
    }

    @PostMapping("/sendMessage")
    ResponseEntity<?> sendMessage(@RequestBody MessageWriteModel messageWriteModel){
        return messageService.sendMessage(messageWriteModel);
    }


}
