package com.physio.node.webservice.service;

import com.physio.node.webservice.model.AilmentIndicationTaskRepository;
import com.physio.node.webservice.model.AilmentNoteTaskRepository;
import com.physio.node.webservice.model.AilmentTaskRepository;
import com.physio.node.webservice.model.DTO.Ailment.AilmentReadModel;
import com.physio.node.webservice.model.DTO.Ailment.AilmentIndicationDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentNoteDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentWriteModel;
import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.AilmentIndication;
import com.physio.node.webservice.model.JPA.AilmentNote;
import com.physio.node.webservice.model.JPA.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<AilmentReadModel> findAllUserAilmentByIdUser(int id){  //by id
        return ailmentTaskRepository.findAllByUserIduser(id)
                .stream().
                        map(ailment ->
                                new AilmentReadModel(ailment.getIdailment(), ailment.getAilmentName(), ailment.getAilmentDescription()))
                             .collect(Collectors.toList());
    }
    public AilmentReadModel findAilmentByIdAilment(int id){  //by id
        Optional<Ailment> ailment = ailmentTaskRepository.findFirstByIdailment(id);
        if(ailment.isEmpty()){
            throw new NullPointerException("Brak choroby");
        }
        AilmentReadModel ailmentReadModel = new AilmentReadModel(ailment.get());
        return ailmentReadModel;
    }

    public void createAilment(AilmentWriteModel ailmentWriteModel){
        User userAilment = new User();
        User attendingphysician = new User();
        userAilment.setIduser(ailmentWriteModel.getUser());
        attendingphysician.setIduser(ailmentWriteModel.getAttendingphysician());

        Ailment createdAilment = new Ailment(ailmentWriteModel.getAilmentName(), ailmentWriteModel.getAilmentDescription(), userAilment, attendingphysician);
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
