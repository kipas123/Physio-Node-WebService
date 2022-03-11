package com.physio.node.webservice.controller.VisitSystem;

import com.physio.node.webservice.model.DTO.VisitSystem.ChosenTermDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType.UserServiceTypeReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType.UserServiceTypeWriteModel;
import com.physio.node.webservice.service.visitSystem.UserServiceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/visit-system")
public class VisitSystemUserServiceTypeTaskCtrl {
    private UserServiceTypeService userServiceTypeService;

    public VisitSystemUserServiceTypeTaskCtrl(UserServiceTypeService userServiceTypeService) {
        this.userServiceTypeService = userServiceTypeService;
    }

    @GetMapping("/getUserServiceType/{userId}")
    public ResponseEntity<List<UserServiceTypeReadModel>> getUserServiceType(@PathVariable int userId) {
        List<UserServiceTypeReadModel> userServiceTypeReadModel = userServiceTypeService.getUserServiceType(userId);
        return new ResponseEntity<>(userServiceTypeReadModel, HttpStatus.OK);
    }

    @PostMapping("/createUserServiceType")
    public ResponseEntity<?> createUserServiceType(@RequestBody UserServiceTypeWriteModel userServiceTypeWriteModel){
        userServiceTypeService.addService(userServiceTypeWriteModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getAvailableUserServiceType")
    public ResponseEntity<List<UserServiceTypeReadModel>> getAvailableUserServiceType(@RequestBody ChosenTermDTO chosenTermDTO){
        List<UserServiceTypeReadModel> userServiceTypeReadModel = userServiceTypeService.getAvailableUserServiceType(chosenTermDTO);
        return new ResponseEntity<>(userServiceTypeReadModel, HttpStatus.OK);
    }

    @GetMapping("/deleteServiceById/{serviceId}")
    public ResponseEntity<?> deleteServiceById(@PathVariable int serviceId){
        userServiceTypeService.deleteServiceById(serviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
