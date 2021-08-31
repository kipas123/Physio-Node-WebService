package com.physio.node.webservice.service;

import com.physio.node.webservice.model.AilmentTaskRepository;
import com.physio.node.webservice.model.DTO.AilmentDTO;
import com.physio.node.webservice.model.DTO.UserDTO;
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
}
