package com.physio.node.webservice.service.visitSystem;

import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.DTO.VisitSystem.CurrentDateAndUserDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour.UserWorkHourReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour.UserWorkHourWriteModel;
import com.physio.node.webservice.model.DTO.VisitSystem.WorkHourListOfAvailableHourDTO;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisit;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkDay;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkHour;
import com.physio.node.webservice.model.UserServiceTypeTaskRepository;
import com.physio.node.webservice.model.UserVisitTaskRepository;
import com.physio.node.webservice.model.UserWorkDayTaskRepository;
import com.physio.node.webservice.model.UserWorkHourTaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserWorkService {
    private UserWorkHourTaskRepository userWorkHourTaskRepository;
    private UserWorkDayTaskRepository userWorkDayTaskRepository;
    private UserVisitTaskRepository userVisitTaskRepository;
    private UserServiceTypeTaskRepository userServiceTypeTaskRepository;

    public UserWorkService(UserWorkHourTaskRepository userWorkHourTaskRepository, UserWorkDayTaskRepository userWorkDayTaskRepository, UserVisitTaskRepository userVisitTaskRepository, UserServiceTypeTaskRepository userServiceTypeTaskRepository) {
        this.userWorkHourTaskRepository = userWorkHourTaskRepository;
        this.userWorkDayTaskRepository = userWorkDayTaskRepository;
        this.userVisitTaskRepository = userVisitTaskRepository;
        this.userServiceTypeTaskRepository = userServiceTypeTaskRepository;
    }

    public List<WorkHourListOfAvailableHourDTO> getListOfAvailableHour(CurrentDateAndUserDTO currentDateAndUserDTO){
        LocalTime startTime = LocalTime.of(00, 00);
        List<WorkHourListOfAvailableHourDTO> listOfHours = new ArrayList<>();
        for (int i = 0; i < 96; i++) {
            listOfHours.add(new WorkHourListOfAvailableHourDTO(startTime.plusMinutes(15 * i),true));
        }
        List<UserWorkHourReadModel> workHourTimeDTO = this.getUserWorkHour(currentDateAndUserDTO.getCurrentDate(), currentDateAndUserDTO.getUserId());
        workHourTimeDTO.stream().forEach(workHourTimeDTO1 -> {
            LocalTime startHour = workHourTimeDTO1.getUserWorkHourBeginningTime();
            LocalTime endHour = workHourTimeDTO1.getUserWorkHourEndingTime();
            boolean isLastHour = false;
            for (int i = 0; i < listOfHours.size(); i++) {
                if(listOfHours.get(i).getAvailableHour().equals(startHour)){ isLastHour=true;}
                if(isLastHour==true){listOfHours.get(i).setEmpty(false);}
                if(listOfHours.get(i).getAvailableHour().equals(endHour)){isLastHour=false;}
            }
        });
        return listOfHours;

    }

    public List<UserWorkHourReadModel> getUserWorkHour(Date date, int userId) {
        List<UserWorkHourReadModel> userWorkHourReadModel = userWorkHourTaskRepository.findAllByVisitSystemUserWorkDay_UserWorkDayAndVisitSystemUserWorkDayUserIduser(date, userId)
                .stream()
                .map(UserWorkHourReadModel::new).collect(Collectors.toList());
        System.out.println("Test");
        return userWorkHourReadModel;
    }

    public void createUserWorkHour(UserWorkHourWriteModel userWorkHourWriteModel){
        VisitSystemUserWorkDay visitSystem_userWorkDay;
        Optional<VisitSystemUserWorkDay> userWorkDay = userWorkDayTaskRepository.findByUserWorkDayAndUserIduser(userWorkHourWriteModel.getUserWorkDay(), userWorkHourWriteModel.getUserId());
        if(userWorkDay.isEmpty()){
            User user = new User(userWorkHourWriteModel.getUserId());
            System.out.println(userWorkHourWriteModel.getUserWorkDay());
            visitSystem_userWorkDay = new VisitSystemUserWorkDay(userWorkHourWriteModel.getUserWorkDay(), user);
            visitSystem_userWorkDay = userWorkDayTaskRepository.save(visitSystem_userWorkDay);
            VisitSystemUserWorkHour visitSystem_userWorkHour = new VisitSystemUserWorkHour(userWorkHourWriteModel.getUserWorkHourBeginningTime(), userWorkHourWriteModel.getUserWorkHourEndingTime(),visitSystem_userWorkDay);
            userWorkHourTaskRepository.save(visitSystem_userWorkHour);
            return;
        }
        VisitSystemUserWorkHour visitSystem_userWorkHour = new VisitSystemUserWorkHour(userWorkHourWriteModel.getUserWorkHourBeginningTime(), userWorkHourWriteModel.getUserWorkHourEndingTime(),userWorkDay.get());
        userWorkHourTaskRepository.save(visitSystem_userWorkHour);
    }

    public Set<Integer>  getListOfAvailableDay(CurrentDateAndUserDTO currentDateAndUserDTO) {
        LocalDate currentDate=  currentDateAndUserDTO.getCurrentDate().toLocalDate();
        LocalDate startDate = currentDateAndUserDTO.getCurrentDate().toLocalDate().withDayOfMonth(1);
        LocalDate endDate = currentDateAndUserDTO.getCurrentDate().toLocalDate().withDayOfMonth(31);
        Set<Integer> days = new HashSet<>();
        Set<java.util.Date> dateWork =  userWorkDayTaskRepository.getUserWorkDate(Date.valueOf(startDate), Date.valueOf(endDate), 1);
        dateWork.stream().forEach(date -> days.add(date.getDate()));
        return  days;

    }

    public List<WorkHourListOfAvailableHourDTO> getUserAvailableTerms(CurrentDateAndUserDTO currentDateAndUserDTO) {
        Double test;
        List<WorkHourListOfAvailableHourDTO> listOfHours = new ArrayList<>();
        List<VisitSystemUserWorkHour> visitSystemUserWorkHours = userWorkHourTaskRepository.findAllByVisitSystemUserWorkDay_UserWorkDayAndVisitSystemUserWorkDayUserIduser(currentDateAndUserDTO.getCurrentDate(), currentDateAndUserDTO.getUserId());
        Optional<VisitSystemUserServiceType> visitSystemUserServiceType = userServiceTypeTaskRepository.findFirstByUserOwnerIduserOrderByUserServiceTypeDuration(currentDateAndUserDTO.getUserId());
        int shortestUserServiceTypeMinute = visitSystemUserServiceType.get().getUserServiceTypeDuration().getMinute();
        int shortestUserServiceTypeHour = visitSystemUserServiceType.get().getUserServiceTypeDuration().getHour();
        long durationShortestUserServiceType = shortestUserServiceTypeHour * 60 + shortestUserServiceTypeMinute;
        visitSystemUserWorkHours.stream().forEach(
                visitSystemUserWorkHour -> {
                    long elapsedMinutes = Duration.between(visitSystemUserWorkHour.getUserWorkHour_beginningTime(), visitSystemUserWorkHour.getUserWorkHour_endingTime()).toMinutes();

                    for (int k = 0; k <= elapsedMinutes; k += 15) {
                        LocalTime localTime = visitSystemUserWorkHour.getUserWorkHour_beginningTime().plusMinutes(k);
//                        if(Duration.between(localTime, visitSystemUserWorkHour.getUserWorkHour_endingTime()).toMinutes() < durationShortestUserServiceType ){
//                            continue;
//                        }

                        if (elapsedMinutes == k) {
                            localTime = localTime.plusMinutes(elapsedMinutes%15);
                            listOfHours.add(new WorkHourListOfAvailableHourDTO(localTime, false));
                        } else  {
                            listOfHours.add(new WorkHourListOfAvailableHourDTO(localTime, true));
                        }
                    }

                }
        );

        //reserved hours
        List<VisitSystemUserVisit> visitSystemUserVisits = userVisitTaskRepository
                .findAllImportantVisit(currentDateAndUserDTO.getCurrentDate(), currentDateAndUserDTO.getUserId());

        visitSystemUserVisits.stream().forEach(
                visitSystemUserVisit -> {
                    int minute = visitSystemUserVisit.getVisitSystemUserServiceType().getUserServiceTypeDuration().getMinute();
                    int hour = visitSystemUserVisit.getVisitSystemUserServiceType().getUserServiceTypeDuration().getHour();
                    long duration = hour * 60 + minute;
                    for (int k = 0; k < listOfHours.size(); k++) {
                        if (visitSystemUserVisit.getUserVisitTime().equals(listOfHours.get(k).getAvailableHour())) {
                            for (int x = 0; x < duration / 15; x++) {
                                listOfHours.get(k + x).setEmpty(false);
                            }
                        }
                    }
                }
        );

        return listOfHours;
    }

    public void deleteWorkHourByWorkHourId(int workHourId) {
        VisitSystemUserWorkHour visitSystemUserWorkHour = new VisitSystemUserWorkHour(workHourId);
        userWorkHourTaskRepository.delete(visitSystemUserWorkHour);
    }
}
