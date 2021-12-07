package com.physio.node.webservice.controller.VisitSystem;

import com.physio.node.webservice.model.DTO.VisitSystem.CurrentDateAndUserDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserVisit.UserVisitReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserVisit.UserVisitWriteModel;
import com.physio.node.webservice.service.visitSystem.UserVisitService;
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
    public List<UserVisitReadModel> getVisitToAccept(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        return userVisitService.getVisitToAccept(currentDateAndUserDTO);
    }

    @PostMapping("/getVisit")
    public List<UserVisitReadModel> getVisit(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        return userVisitService.getVisit(currentDateAndUserDTO);
    }

    @PostMapping("/bookVisit")
        public ResponseEntity<?> bookVisit(@RequestBody UserVisitWriteModel userVisitWriteModel){
        System.out.println(userVisitWriteModel);
            return userVisitService.bookVisit(userVisitWriteModel);
        }

    @PostMapping("/getUserUpcomingVisit")
    public List<UserVisitReadModel> getUserUpcomingVisit(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        return userVisitService.getUserVisitFromDate(currentDateAndUserDTO);
    }
    {}
    @GetMapping("/getUserVisit/{userId}")
    public List<UserVisitReadModel> getUserVisit(@PathVariable int userId){
        return userVisitService.getUserVisit(userId);
    }

    @GetMapping("/getProviderVisit/{userId}")
    public List<UserVisitReadModel> getProviderVisit(@PathVariable int userId){
        return userVisitService.getProviderVisit(userId);
    }

}
