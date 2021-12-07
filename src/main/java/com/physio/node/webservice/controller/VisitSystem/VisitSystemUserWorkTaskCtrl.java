package com.physio.node.webservice.controller.VisitSystem;

import com.physio.node.webservice.model.DTO.VisitSystem.CurrentDateAndUserDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour.UserWorkHourReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour.UserWorkHourWriteModel;
import com.physio.node.webservice.model.DTO.VisitSystem.WorkHourListOfAvailableHourDTO;
import com.physio.node.webservice.service.visitSystem.UserWorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/visit-system")
public class VisitSystemUserWorkTaskCtrl {
    private UserWorkService userWorkService;

    public VisitSystemUserWorkTaskCtrl(UserWorkService userWorkService) {
        this.userWorkService = userWorkService;
    }

    @PostMapping("/listOfAvailableDay")
    public Set<Integer>  getListOfAvailableDay(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        return userWorkService.getListOfAvailableDay(currentDateAndUserDTO);
    }

    @PostMapping("/listOfAvailableHour")
    public List<WorkHourListOfAvailableHourDTO> getListOfAvailableHour(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        return userWorkService.getListOfAvailableHour(currentDateAndUserDTO);
    }

    @PostMapping("/getUserAvailableTerms")
    public List<WorkHourListOfAvailableHourDTO> getUserAvailableTerms(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        return userWorkService.getUserAvailableTerms(currentDateAndUserDTO);
    }

    @PostMapping("/getUserWorkHour")
    public List<UserWorkHourReadModel> getUserWorkHour(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        return userWorkService.getUserWorkHour( currentDateAndUserDTO.getCurrentDate(),currentDateAndUserDTO.getUserId());
    }

    @PostMapping("/createWorkHour")
    public ResponseEntity<?> createUserWorkHour(@RequestBody UserWorkHourWriteModel userWorkHourWriteModel){
        return this.userWorkService.createUserWorkHour(userWorkHourWriteModel);
    }

    @GetMapping("/deleteWorkHourByIdWorkHour/{workHourId}")
    public ResponseEntity<?> deleteWorkHourByIdWorkHour(@PathVariable int workHourId){
        return this.userWorkService.deleteWorkHourByWorkHourId(workHourId);
    }
//    @GetMapping("/getAvailableDays")
//    public Set<Integer> getDate(){
//        java.sql.Date startDate = java.sql.Date.valueOf("2021-10-10");
//        java.sql.Date endDate = java.sql.Date.valueOf("2021-11-20");
//        Set<Integer> days = new HashSet<>();
//        Set<Date> dateWork =  workHourtTaskRepository.getWorkDate(startDate, endDate, 1);
//        dateWork.stream().forEach(date -> days.add(date.getDate()));
//
//        return  days;
//    }

}
