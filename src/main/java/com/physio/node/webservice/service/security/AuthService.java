package com.physio.node.webservice.service.security;

import com.physio.node.webservice.jwt.JwtTokenProvider;
import com.physio.node.webservice.model.DTO.Auth.PasswordResetModel;
import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.DTO.User.UserWriteModel;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.JPA.UserRole;
import com.physio.node.webservice.model.UserRoleTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import com.physio.node.webservice.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.Principal;
import java.util.Optional;

@Service
public class AuthService {
    private JwtTokenProvider jwtTokenProvider;
    private JavaMailSender getJavaMailSender;
    private UserTaskRepository userTaskRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleTaskRepository userRoleTaskRepository;
    private UserService userService;

    public AuthService(JwtTokenProvider jwtTokenProvider, JavaMailSender getJavaMailSender, UserTaskRepository userTaskRepository, PasswordEncoder passwordEncoder, UserRoleTaskRepository userRoleTaskRepository, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.getJavaMailSender = getJavaMailSender;
        this.userTaskRepository = userTaskRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleTaskRepository = userRoleTaskRepository;
        this.userService = userService;
    }

    public ResponseEntity<?> login(Principal principal){
        if(principal == null){
            //This should be ok http status because this will be used for logout path.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserReadModel userReadModel = userService.findUserByUserEmail(authenticationToken.getName());
        userReadModel.setToken(jwtTokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
    }


    public ResponseEntity<?> executePaswordReset(String email){
        Optional<User> user = userTaskRepository.findByUserEmail(email);
        if(user.isEmpty()) return ResponseEntity.badRequest().build();
        String token = RandomString.make(30);
        user.get().setResetPasswordToken(token);
        userTaskRepository.save(user.get());
        try {
            MimeMessage mimeMessage = getJavaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<h3>Resetowanie hasła</h3>" +
                    "<br/> Kliknij link: http://localhost:4200/pages/password-reset-change/" + token;
//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(email);
            helper.setSubject("Reset hasła");
            helper.setFrom("Fizjo-node.pl");
            getJavaMailSender.send(mimeMessage);
            return ResponseEntity.ok().build();
        }catch (MessagingException e){
            return ResponseEntity.badRequest().build();
        }
    }
    public ResponseEntity<?> changePassword(PasswordResetModel passwordResetModel) {
        Optional<User> user = userTaskRepository.findByResetPasswordToken(passwordResetModel.getToken());
        if(user.isEmpty()) return ResponseEntity.badRequest().build();
        user.get().setUserPassword(passwordEncoder.encode(passwordResetModel.getPassword()));
        user.get().setResetPasswordToken(null);
        userTaskRepository.save(user.get());
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> registerUser(UserWriteModel userWriteModel){
        Optional<User> user = userTaskRepository.findByUserEmail(userWriteModel.getUserEmail());
        if(!user.isEmpty()){
            System.out.println(userWriteModel.getUserEmail());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        UserRole userRole = userRoleTaskRepository.findByRoleName("unverified");
        userWriteModel.setUserPassword(passwordEncoder.encode(userWriteModel.getUserPassword()));
        User createdUser = new User(userWriteModel);
        createdUser.setUserRole(userRole);

        return new ResponseEntity<>(userTaskRepository.save(createdUser), HttpStatus.CREATED);
    }


}
