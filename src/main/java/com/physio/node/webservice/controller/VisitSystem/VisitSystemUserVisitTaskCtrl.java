package com.physio.node.webservice.controller.VisitSystem;

import com.physio.node.webservice.model.DTO.VisitSystem.CurrentDateAndUserDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserVisit.UserVisitReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserVisit.UserVisitWriteModel;
import com.physio.node.webservice.service.visitSystem.UserVisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/visit-system")
public class VisitSystemUserVisitTaskCtrl {
    private UserVisitService userVisitService;

    public VisitSystemUserVisitTaskCtrl(UserVisitService userVisitService) {
        this.userVisitService = userVisitService;
    }

    @PostMapping("/getVisitToAccept")
    public ResponseEntity<List<UserVisitReadModel>> getVisitToAccept(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        List<UserVisitReadModel> userVisitReadModel = userVisitService.getVisitToAccept(currentDateAndUserDTO);
        return new ResponseEntity<>(userVisitReadModel, HttpStatus.OK);
    }

    @PostMapping("/getVisit")
    public ResponseEntity<List<UserVisitReadModel>> getVisit(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        List<UserVisitReadModel> userVisitReadModel = userVisitService.getVisit(currentDateAndUserDTO);
        return new ResponseEntity<>(userVisitReadModel, HttpStatus.OK);
    }

    @PostMapping("/bookVisit")
        public ResponseEntity<?> bookVisit(@RequestBody UserVisitWriteModel userVisitWriteModel){
        userVisitService.bookVisit(userVisitWriteModel);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    @PostMapping("/getUserUpcomingVisit")
    public ResponseEntity<List<UserVisitReadModel>> getUserUpcomingVisit(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        List<UserVisitReadModel> userVisitReadModel = userVisitService.getUserVisitFromDate(currentDateAndUserDTO);
        return new ResponseEntity<>(userVisitReadModel, HttpStatus.OK);
    }
    {}
    @GetMapping("/getUserVisit/{userId}")
    public ResponseEntity<List<UserVisitReadModel>> getUserVisit(@PathVariable int userId){
        List<UserVisitReadModel> userVisitReadModel = userVisitService.getUserVisit(userId);
        return new ResponseEntity<>(userVisitReadModel, HttpStatus.OK);
    }

    @GetMapping("/getProviderVisit/{userId}/{page}/{size}")
    public ResponseEntity<List<UserVisitReadModel>> getProviderVisit(@PathVariable int userId, @PathVariable int page, @PathVariable int size){
        List<UserVisitReadModel> userVisitReadModel = userVisitService.getProviderVisit(userId, page, size);
        return new ResponseEntity<>(userVisitReadModel, HttpStatus.OK);
    }

    @GetMapping("/countProviderVisit/{userId}")
    public ResponseEntity<Long> countProviderVisit(@PathVariable int userId){
        Long countProviderVisit = userVisitService.countProviderVisit(userId);
        return new ResponseEntity<>(countProviderVisit, HttpStatus.OK);
    }
    @GetMapping("/changeVisitStatus/{visitId}/{statusId}")
    public ResponseEntity<?> changeVisitStatus(@PathVariable int visitId, @PathVariable int statusId){
        userVisitService.changeVisitStatus(visitId,statusId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
