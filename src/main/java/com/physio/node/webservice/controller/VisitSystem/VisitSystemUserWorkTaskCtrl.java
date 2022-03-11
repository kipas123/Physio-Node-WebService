package com.physio.node.webservice.controller.VisitSystem;

import com.physio.node.webservice.model.DTO.VisitSystem.CurrentDateAndUserDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour.UserWorkHourReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour.UserWorkHourWriteModel;
import com.physio.node.webservice.model.DTO.VisitSystem.WorkHourListOfAvailableHourDTO;
import com.physio.node.webservice.service.visitSystem.UserWorkService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Set<Integer>>  getListOfAvailableDay(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        Set<Integer> listOfAvailableDay = userWorkService.getListOfAvailableDay(currentDateAndUserDTO);
        return new ResponseEntity<>(listOfAvailableDay, HttpStatus.OK);
    }

    @PostMapping("/listOfAvailableHour")
    public ResponseEntity<List<WorkHourListOfAvailableHourDTO>> getListOfAvailableHour(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        List<WorkHourListOfAvailableHourDTO> workHourListOfAvailableHourDTO = userWorkService.getListOfAvailableHour(currentDateAndUserDTO);
        return new ResponseEntity<>(workHourListOfAvailableHourDTO, HttpStatus.OK);
    }

    @PostMapping("/getUserAvailableTerms")
    public ResponseEntity<List<WorkHourListOfAvailableHourDTO>> getUserAvailableTerms(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        List<WorkHourListOfAvailableHourDTO> workHourListOfAvailableHourDTO = userWorkService.getUserAvailableTerms(currentDateAndUserDTO);
        return new ResponseEntity<>(workHourListOfAvailableHourDTO, HttpStatus.OK);
    }

    @PostMapping("/getUserWorkHour")
    public ResponseEntity<List<UserWorkHourReadModel>> getUserWorkHour(@RequestBody CurrentDateAndUserDTO currentDateAndUserDTO){
        List<UserWorkHourReadModel> userWorkHourReadModel = userWorkService.getUserWorkHour( currentDateAndUserDTO.getCurrentDate(),currentDateAndUserDTO.getUserId());
        return new ResponseEntity<>(userWorkHourReadModel, HttpStatus.OK);
    }

    @PostMapping("/createWorkHour")
    public ResponseEntity<?> createUserWorkHour(@RequestBody UserWorkHourWriteModel userWorkHourWriteModel){

        userWorkService.createUserWorkHour(userWorkHourWriteModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/deleteWorkHourByIdWorkHour/{workHourId}")
    public ResponseEntity<?> deleteWorkHourByIdWorkHour(@PathVariable int workHourId){
        userWorkService.deleteWorkHourByWorkHourId(workHourId);
        return new ResponseEntity<>(HttpStatus.OK);
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
