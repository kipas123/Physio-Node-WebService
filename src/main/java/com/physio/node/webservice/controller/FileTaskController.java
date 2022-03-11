package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.FileSystem.AilmentFileDTO;
import com.physio.node.webservice.model.JPA.AilmentFiles;
import com.physio.node.webservice.service.FileStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fileSystem")
@CrossOrigin(origins = "http://localhost:4200")
public class FileTaskController {

    private FileStorageService fileStorageService;

    public FileTaskController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userIdStr,
                                             @RequestParam("ailmentId") String ailmentIdStr) {
        String message = "";
        try {
            int userId = Integer.parseInt(userIdStr);
            int ailmentId = Integer.parseInt(ailmentIdStr);
            fileStorageService.store(file, userId, ailmentId);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

//    @GetMapping("/files")
//    public ResponseEntity<List<AilmentFileDTO>> getListFiles() {
//        List<AilmentFileDTO> files = fileStorageService.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getIdailmentFiles())
//                    .toUriString();
//
//            return new AilmentFileDTO(
//                    dbFile.getIdailmentFiles(),
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length,
//                    dbFile.getDateAdded(),
//                    dbFile.getUser().getIduser());
//        }).collect(Collectors.toList());
//
//        System.out.println("Zaczekaj");
//        return new ResponseEntity<>(files, HttpStatus.OK);
//    }

    @GetMapping("/files/{ailmentId}")
    public ResponseEntity<List<AilmentFileDTO>> getListFiles(@PathVariable int ailmentId) {
        List<AilmentFileDTO> ailmentFileDTO = fileStorageService.getAllFilesByAilmentId(ailmentId);
        if(ailmentFileDTO.size()!=0){
            return ResponseEntity.status(HttpStatus.OK).body(ailmentFileDTO);

        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        AilmentFiles fileDB = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
    @GetMapping("/deleteFile/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable String id) {
        return this.fileStorageService.deleteFile(id);
    }
}
