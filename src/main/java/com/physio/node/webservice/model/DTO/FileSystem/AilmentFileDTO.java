package com.physio.node.webservice.model.DTO.FileSystem;

import com.physio.node.webservice.model.JPA.AilmentFiles;
import lombok.Data;

import java.util.Date;

@Data
public class AilmentFileDTO {
    private String idailment_files;
    private String name;
    private String url;
    private String type;
    private long size;
    private Date dateAdded;
    private int userId;

    public AilmentFileDTO(String idailment_files, String name, String url, String type, long size, Date dateAdded, int userId) {
        this.idailment_files = idailment_files;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.dateAdded = dateAdded;
        this.userId = userId;
    }
}
