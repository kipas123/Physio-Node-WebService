package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserWriteModel {
    private int userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userSurname;
    private Date userDob;

    public UserWriteModel(User user) {
        //this.password = user.getUserPassword();
        this.userName = user.getUserName();
        this.userSurname=user.getUserSurname();
        this.userEmail = user.getUserEmail();
        this.userId = user.getIduser();
        this.userDob=user.getUserDob();
    }
    public UserWriteModel(int userId, String userEmail, String userPassword,  String userName, String userSurname, Date userDob){
        this.userId=userId;
        this.userEmail=userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userSurname=userSurname;
        this.userDob=userDob;
    }
}
