package com.physio.node.webservice.service;

import com.physio.node.webservice.model.AilmentIndicationTaskRepository;
import com.physio.node.webservice.model.AilmentNoteTaskRepository;
import com.physio.node.webservice.model.AilmentTaskRepository;
import com.physio.node.webservice.model.DTO.AilmentDTO;
import com.physio.node.webservice.model.DTO.AilmentIndicationDTO;
import com.physio.node.webservice.model.DTO.AilmentNoteDTO;
import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.AilmentIndication;
import com.physio.node.webservice.model.JPA.AilmentNote;
import com.physio.node.webservice.model.JPA.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AilmentService {
    private AilmentTaskRepository ailmentTaskRepository;
    private AilmentNoteTaskRepository ailmentNoteTaskRepository;
    private AilmentIndicationTaskRepository ailmentIndicationTaskRepository;

    public AilmentService(AilmentTaskRepository ailmentTaskRepository, AilmentNoteTaskRepository ailmentNoteTaskRepository, AilmentIndicationTaskRepository ailmentIndicationTaskRepository) {
        this.ailmentTaskRepository = ailmentTaskRepository;
        this.ailmentNoteTaskRepository = ailmentNoteTaskRepository;
        this.ailmentIndicationTaskRepository = ailmentIndicationTaskRepository;
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

    public void createAilment(AilmentDTO ailmentDTO){
        //hardcoded
        User userAilment = new User();
        User attendingphysician = new User();
        userAilment.setIduser(2);
        attendingphysician.setIduser(1);
        //--------------------------------


        Ailment createdAilment = new Ailment(ailmentDTO.getAilmentName(), ailmentDTO.getAilmentDescription(), userAilment, attendingphysician);
        ailmentTaskRepository.save(createdAilment);
    }
    public void createAilmentNote(AilmentNoteDTO ailmentNoteDTO){
        Ailment ailment = new Ailment(ailmentNoteDTO.getAilmentId());
        AilmentNote createdAilmentNote = new AilmentNote(ailmentNoteDTO.getNoteHeader(), ailmentNoteDTO.getNoteDescription(), ailment);
        ailmentNoteTaskRepository.save(createdAilmentNote);
    }
    public void createAilmentIndication(AilmentIndicationDTO ailmentIndicationDTO){
        Ailment ailment = new Ailment(ailmentIndicationDTO.getAilmentId());
        AilmentIndication createdAilmentIndication = new AilmentIndication(ailmentIndicationDTO.getIndicationHeader(), ailmentIndicationDTO.getIndicationDescription(), ailment);
        ailmentIndicationTaskRepository.save(createdAilmentIndication);
    }
}
