package com.physio.node.webservice.controller.VisitSystem;

import com.physio.node.webservice.model.DTO.VisitSystem.ChosenTermDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType.UserServiceTypeReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType.UserServiceTypeWriteModel;
import com.physio.node.webservice.service.visitSystem.UserServiceTypeService;
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
    public List<UserServiceTypeReadModel> getUserServiceType(@PathVariable int userId) {
        return userServiceTypeService.getUserServiceType(userId);
    }

    @PostMapping("/createUserServiceType")
    public ResponseEntity<?> createUserServiceType(@RequestBody UserServiceTypeWriteModel userServiceTypeWriteModel){
        System.out.println("kestem t");
        return userServiceTypeService.addService(userServiceTypeWriteModel);
    }

    @PostMapping("/getAvailableUserServiceType")
    public List<UserServiceTypeReadModel> getAvailableUserServiceType(@RequestBody ChosenTermDTO chosenTermDTO){
        return userServiceTypeService.getAvailableUserServiceType(chosenTermDTO);
    }

    @GetMapping("/deleteServiceById/{serviceId}")
    public ResponseEntity<?> deleteServiceById(@PathVariable int serviceId){
        return userServiceTypeService.deleteServiceById(serviceId);
    }



}
