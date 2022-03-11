package com.physio.node.webservice.service;

import com.physio.node.webservice.Exception.ResourceBadRequestException;
import com.physio.node.webservice.Exception.ResourceNotFoundException;
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
        List<Ailment> ailments = ailmentTaskRepository.findAllByUserIduser(id);
        if (ailments.isEmpty()) {
            throw new ResourceNotFoundException("Not found for IdUser" + id);
        }
        return ailments
                .stream().
                        map(ailment ->
                                new AilmentReadModel(ailment.getIdailment(), ailment.getAilmentName(), ailment.getAilmentDescription(), ailment.getAttendingphysician()))
                             .collect(Collectors.toList());
    }
    public AilmentReadModel findAilmentByIdAilment(int id){  //by id
        Ailment ailment = ailmentTaskRepository.findFirstByIdailment(id).orElseThrow(() -> new ResourceNotFoundException("Not found for idAilment: " + id));
        AilmentReadModel ailmentReadModel = new AilmentReadModel(ailment);
        return ailmentReadModel;
    }

    public void createAilment(AilmentWriteModel ailmentWriteModel){
        User userAilment = new User();
        User attendingphysician = new User();
        userAilment.setIduser(ailmentWriteModel.getUser());
        attendingphysician.setIduser(ailmentWriteModel.getAttendingphysician());

        Ailment createdAilment = new Ailment(ailmentWriteModel.getAilmentName(), ailmentWriteModel.getAilmentDescription(), userAilment, attendingphysician);
        try{
            ailmentTaskRepository.save(createdAilment);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }
    public void createAilmentNote(AilmentNoteDTO ailmentNoteDTO){
        Ailment ailment = new Ailment(ailmentNoteDTO.getAilmentId());
        AilmentNote createdAilmentNote = new AilmentNote(ailmentNoteDTO.getNoteHeader(), ailmentNoteDTO.getNoteDescription(), ailment);
        try{
            ailmentNoteTaskRepository.save(createdAilmentNote);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }
    public void createAilmentIndication(AilmentIndicationDTO ailmentIndicationDTO){
        Ailment ailment = new Ailment(ailmentIndicationDTO.getAilmentId());
        AilmentIndication createdAilmentIndication = new AilmentIndication(ailmentIndicationDTO.getIndicationHeader(), ailmentIndicationDTO.getIndicationDescription(), ailment);
        try{
            ailmentIndicationTaskRepository.save(createdAilmentIndication);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }

    public void deleteAilment(int ailmentId) {
        try{
            ailmentTaskRepository.delete(new Ailment(ailmentId));
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument" + e);
        }
    }
}
