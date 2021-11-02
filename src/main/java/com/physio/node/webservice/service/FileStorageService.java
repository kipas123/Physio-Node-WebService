package com.physio.node.webservice.service;

import com.physio.node.webservice.model.AilmentFilesTaskRepository;
import com.physio.node.webservice.model.DTO.FileSystem.AilmentFileDTO;
import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.AilmentFiles;
import com.physio.node.webservice.model.JPA.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private AilmentFilesTaskRepository ailmentFilesTaskRepository;

    public FileStorageService(AilmentFilesTaskRepository ailmentFilesTaskRepository) {
        this.ailmentFilesTaskRepository = ailmentFilesTaskRepository;
    }

    public AilmentFiles store(MultipartFile file, int userId, int ailmentId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        AilmentFiles ailmentFiles = new AilmentFiles(fileName, file.getContentType(), file.getBytes());
        ailmentFiles.setDateAdded(new Date());
        ailmentFiles.setAilment(new Ailment(ailmentId));
        ailmentFiles.setUser(new User(userId));
        return ailmentFilesTaskRepository.save(ailmentFiles);
    }

    public AilmentFiles getFile(String id) {
        return ailmentFilesTaskRepository.findByIdailmentFiles(id).get();
    }

    public Stream<AilmentFiles> getAllFiles() {

        return ailmentFilesTaskRepository.findAll().stream();
    }

    public List<AilmentFileDTO> getAllFilesByAilmentId(int ailmentId) {
      List<AilmentFileDTO> ailmentFileDTO = ailmentFilesTaskRepository.findAllByAilment_IdailmentOrderByDataAsc(ailmentId).stream().map(
                dbFile -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/fileSystem/file/")
                            .path(dbFile.getIdailmentFiles())
                            .toUriString();

                    return new AilmentFileDTO(
                            dbFile.getIdailmentFiles(),
                            dbFile.getName(),
                            fileDownloadUri,
                            dbFile.getType(),
                            dbFile.getData().length,
                            dbFile.getDateAdded(),
                            dbFile.getUser().getIduser());
                }).collect(Collectors.toList());
      return ailmentFileDTO;
    }
    public ResponseEntity<?> deleteFile(String id) {
        AilmentFiles ailmentFiles = this.getFile(id);
        this.ailmentFilesTaskRepository.delete(ailmentFiles);
        return ResponseEntity.ok().build();
    }
}
