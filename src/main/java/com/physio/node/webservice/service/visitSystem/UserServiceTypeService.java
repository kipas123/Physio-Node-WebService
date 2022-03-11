package com.physio.node.webservice.service.visitSystem;

import com.physio.node.webservice.Exception.ResourceBadRequestException;
import com.physio.node.webservice.Exception.ResourceNotFoundException;
import com.physio.node.webservice.adapter.visitSystem.SqlUserServiceTypeTaskRepository;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.DTO.VisitSystem.ChosenTermDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.CurrentDateAndUserDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType.UserServiceTypeReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType.UserServiceTypeWriteModel;
import com.physio.node.webservice.model.DTO.VisitSystem.WorkHourListOfAvailableHourDTO;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;
import com.physio.node.webservice.model.UserServiceTypeTaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceTypeService {
    private SqlUserServiceTypeTaskRepository sqlUserServiceTypeTaskRepository;
    private UserServiceTypeTaskRepository userServiceTypeTaskRepository;
    private UserWorkService userWorkService;

    public UserServiceTypeService(SqlUserServiceTypeTaskRepository sqlUserServiceTypeTaskRepository, UserServiceTypeTaskRepository userServiceTypeTaskRepository, UserWorkService userWorkService) {
        this.sqlUserServiceTypeTaskRepository = sqlUserServiceTypeTaskRepository;
        this.userServiceTypeTaskRepository = userServiceTypeTaskRepository;
        this.userWorkService = userWorkService;
    }

    public List<UserServiceTypeReadModel> getUserServiceType(int userId){
        return sqlUserServiceTypeTaskRepository
                .findAllByUserOwnerIduserAndUserServiceTypeActiveOrderByUserServiceTypeDuration(userId, true)
                .stream().map(UserServiceTypeReadModel::new).collect(Collectors.toList());
    }

    public void addService(UserServiceTypeWriteModel userServiceTypeWriteModel) {
        int hour = userServiceTypeWriteModel.getUserServiceTypeDurationInMinute()/60;
        int minute = userServiceTypeWriteModel.getUserServiceTypeDurationInMinute()%60;
        LocalTime localTime = LocalTime.of(hour, minute);
        User user = new User(userServiceTypeWriteModel.getUserId());
        VisitSystemUserServiceType visitSystemUserServiceType = new VisitSystemUserServiceType(userServiceTypeWriteModel.getUserServiceTypeName(), localTime, user, true);
        userServiceTypeTaskRepository.save(visitSystemUserServiceType);
    }

    public List<UserServiceTypeReadModel> getAvailableUserServiceType(ChosenTermDTO chosenTermDTO) {
        LocalTime chosenTimeTerm = chosenTermDTO.getChosenTimeTerm();
        List<WorkHourListOfAvailableHourDTO> availableHours =userWorkService.getUserAvailableTerms(new CurrentDateAndUserDTO(chosenTermDTO.getChosenDataTerm(),chosenTermDTO.getServiceProviderId()));
        List<VisitSystemUserServiceType> userServiceTypes = userServiceTypeTaskRepository.findAllByUserOwnerIduserAndUserServiceTypeActiveOrderByUserServiceTypeDuration(chosenTermDTO.getServiceProviderId(), true);
        List<VisitSystemUserServiceType> userServiceTypeFiltered = new ArrayList<>();
        List<UserServiceTypeReadModel> userServiceTypeReadModel;
        int sizeOfList = userServiceTypes.size();
        userServiceTypes.stream().forEach(
                userServiceType -> {
                    if (isAvailable(userServiceType, availableHours, chosenTimeTerm)) userServiceTypeFiltered.add(userServiceType);
                }
        );
        if(userServiceTypeFiltered.size()==0) return null;
        userServiceTypeReadModel = userServiceTypeFiltered.stream().map(UserServiceTypeReadModel::new).collect(Collectors.toList());

        return userServiceTypeReadModel;
    }

    private boolean isAvailable(VisitSystemUserServiceType userServiceType, List<WorkHourListOfAvailableHourDTO> listOfHours, LocalTime localTime) {
        boolean isTest = false;
        int minute = userServiceType.getUserServiceTypeDuration().getMinute();
        int hour = userServiceType.getUserServiceTypeDuration().getHour();
        long duration = hour * 60 + minute;
        LocalTime timeOfLastAvailableHour = listOfHours.get(listOfHours.size() - 1).getAvailableHour();
        LocalTime timeOfEndHypotheticalService = localTime.plusMinutes(duration);
        Long timeBetweenLastAvailableHourAndtimeOfEndHypotheticalService = Duration.between(
                timeOfEndHypotheticalService, timeOfLastAvailableHour
        ).toMinutes();
        if (timeBetweenLastAvailableHourAndtimeOfEndHypotheticalService < 0) return false;


        for (int i = 0; i < listOfHours.size(); i++) {
            if (listOfHours.get(i).getAvailableHour().equals(localTime)) {
                isTest = true;
                Long durationBetween = Duration.between(listOfHours.get((int) (i + (duration / 15))).getAvailableHour(), timeOfEndHypotheticalService).toMinutes();
                if (durationBetween < 0) return false;
                for (int k = 0; k <= duration / 15; k++) {
                    if (listOfHours.get(i + k).isEmpty() == false) return false;
                }
            }
        }
        if (isTest == false) return false;
        return true;

    }

    public void deleteServiceById(int serviceId){
        VisitSystemUserServiceType visitSystemUserServiceType = userServiceTypeTaskRepository.findByIdUserServiceType(serviceId).orElseThrow(() -> new ResourceNotFoundException("Not found service!"));
        visitSystemUserServiceType.setUserServiceTypeActive(false);
        try{
            userServiceTypeTaskRepository.save(visitSystemUserServiceType);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }
}
