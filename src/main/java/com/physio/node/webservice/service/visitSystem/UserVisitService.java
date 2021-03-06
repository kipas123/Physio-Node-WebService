package com.physio.node.webservice.service.visitSystem;

import com.physio.node.webservice.Exception.ResourceBadRequestException;
import com.physio.node.webservice.Exception.ResourceNotFoundException;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.DTO.VisitSystem.CurrentDateAndUserDTO;
import com.physio.node.webservice.model.DTO.VisitSystem.UserVisit.UserVisitReadModel;
import com.physio.node.webservice.model.DTO.VisitSystem.UserVisit.UserVisitWriteModel;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisit;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserVisitStatus;
import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkDay;
import com.physio.node.webservice.model.UserVisitStatusTaskRepository;
import com.physio.node.webservice.model.UserVisitTaskRepository;
import com.physio.node.webservice.model.UserWorkDayTaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserVisitService {
    private UserVisitTaskRepository userVisitTaskRepository;
    private UserWorkDayTaskRepository userWorkDayTaskRepository;
    private UserVisitStatusTaskRepository userVisitStatusTaskRepository;

    public UserVisitService(UserVisitTaskRepository userVisitTaskRepository, UserWorkDayTaskRepository userWorkDayTaskRepository, UserVisitStatusTaskRepository userVisitStatusTaskRepository) {
        this.userVisitTaskRepository = userVisitTaskRepository;
        this.userWorkDayTaskRepository = userWorkDayTaskRepository;
        this.userVisitStatusTaskRepository = userVisitStatusTaskRepository;
    }

    public List<UserVisitReadModel> getVisitToAccept(CurrentDateAndUserDTO currentDateAndUserDTO) {
        List<UserVisitReadModel> userVisitReadModel = userVisitTaskRepository.findAllByUserIduserAndVisitSystemUserVisitStatusIdUserVisitStatus(currentDateAndUserDTO.getUserId(), 3)
                .stream().map(UserVisitReadModel::new).collect(Collectors.toList());
//        if (userVisitReadModel.isEmpty()) {
//            throw new ResourceNotFoundException("Not found");
//        }
        return userVisitReadModel;
    }

    public List<UserVisitReadModel> getVisit(CurrentDateAndUserDTO currentDateAndUserDTO) {
        List<UserVisitReadModel> userVisitReadModel = userVisitTaskRepository.findAllByVisitSystemUserWorkDay_UserWorkDayAndVisitSystemUserWorkDayUserIduserAndVisitSystemUserVisitStatus_IdUserVisitStatus(currentDateAndUserDTO.getCurrentDate(),currentDateAndUserDTO.getUserId(), 1)
                .stream().map(UserVisitReadModel::new).collect(Collectors.toList());
//        if (userVisitReadModel.isEmpty()) {
//            throw new ResourceNotFoundException("Not found");
//        }
        return userVisitReadModel;
    }

    public void bookVisit(UserVisitWriteModel userVisitWriteModel) {
        VisitSystemUserWorkDay userWorkDay = userWorkDayTaskRepository.findByUserWorkDayAndUserIduser(userVisitWriteModel.getBookingDate(), userVisitWriteModel.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Not found!"));
        User user = new User(userVisitWriteModel.getUserId());
        VisitSystemUserServiceType visitSystemUserServiceType = new VisitSystemUserServiceType(Integer.parseInt(userVisitWriteModel.getUserServiceId()));
        VisitSystemUserVisitStatus visitSystemUserVisitStatus = userVisitStatusTaskRepository.findByIdUserVisitStatus(3).get();
        VisitSystemUserVisit visitSystemUserVisit = new VisitSystemUserVisit(userVisitWriteModel.getBookingTime(), userWorkDay, visitSystemUserServiceType,user, visitSystemUserVisitStatus, false);
        try{
            userVisitTaskRepository.save(visitSystemUserVisit);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }

    public List<UserVisitReadModel> getUserVisitFromDate(CurrentDateAndUserDTO currentDateAndUserDTO) {
        List<UserVisitReadModel> userVisitReadModels = userVisitTaskRepository.getUserVisitFromDate(currentDateAndUserDTO.getCurrentDate(),currentDateAndUserDTO.getUserId())
        .stream().map(UserVisitReadModel::new).collect(Collectors.toList());
        if (userVisitReadModels.isEmpty()) {
            throw new ResourceNotFoundException("Not found");
        }
        return userVisitReadModels;
    }

    public List<UserVisitReadModel> getUserVisit(int userId) {
        List<UserVisitReadModel> userVisitReadModel = userVisitTaskRepository.findAllByUserIduser(userId).stream().map(UserVisitReadModel::new).collect(Collectors.toList());
        if (userVisitReadModel.isEmpty()) {
            throw new ResourceNotFoundException("Not found");
        }
        return userVisitReadModel;
    }

    public List<UserVisitReadModel> getProviderVisit(int userId, int page, int size) {
        List<UserVisitReadModel> userVisitReadModel = userVisitTaskRepository.findAllByVisitSystemUserWorkDay_UserIduserAndVisitSystemUserVisitStatus_IdUserVisitStatus(userId, PageRequest.of(page,size),1)
                .stream().map(UserVisitReadModel::new).collect(Collectors.toList());
        if (userVisitReadModel.isEmpty()) {
            throw new ResourceNotFoundException("Not found");
        }
        return userVisitReadModel;
    }
    public void changeVisitStatus(int visitId, int statusId){
        VisitSystemUserVisit visitSystemUserVisit = userVisitTaskRepository.findByIdUserVisit(visitId).orElseThrow(() -> new ResourceNotFoundException("Not found!"));
        visitSystemUserVisit.setVisitSystemUserVisitStatus(new VisitSystemUserVisitStatus(statusId));
        try{
            userVisitTaskRepository.save(visitSystemUserVisit);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }

    public Long countProviderVisit(int userId)
    {
        Long numberOfProviderVisit = userVisitTaskRepository.countProviderVisit(userId);
        if (numberOfProviderVisit == null) {
            throw new ResourceNotFoundException("Not found");
        }
        return numberOfProviderVisit;
    }
}
