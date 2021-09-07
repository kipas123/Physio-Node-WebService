package com.physio.node.webservice.service;

import com.physio.node.webservice.model.AilmentTaskRepository;
import com.physio.node.webservice.model.DTO.AilmentDTO;
import com.physio.node.webservice.model.DTO.MyGroupDTO;
import com.physio.node.webservice.model.DTO.UserDTO;
import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AilmentService {
    private AilmentTaskRepository ailmentTaskRepository;

    public AilmentService(AilmentTaskRepository ailmentTaskRepository) {
        this.ailmentTaskRepository = ailmentTaskRepository;
    }
    public List<AilmentDTO> findUserAilmentByIdUser(int id){  //by id
        return ailmentTaskRepository.findAllByUserIduser(id)
                .stream().
                        map(AilmentDTO::new).
                        collect(Collectors.toList());
    }
    public AilmentDTO findAilmentByIdAilment(int id){  //by id
        return ailmentTaskRepository.findAllByUserIduser(id)
                .stream().
                        map(AilmentDTO::new).
                        collect(Collectors.toList()).get(0);
    }

    public void createAilment(AilmentDTO ailment){
        //hardcoded
        User userAilment = new User();
        User attendingphysician = new User();
        userAilment.setIduser(2);
        attendingphysician.setIduser(1);
        //--------------------------------


        Ailment createdAilment = new Ailment(ailment.getAilmentName(), ailment.getAilmentDescription(), userAilment, attendingphysician);
        ailmentTaskRepository.save(createdAilment);
    }
}
