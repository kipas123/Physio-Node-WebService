package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the ailment_filepath database table.
 * 
 */
@Data
@Entity
@Table(name="ailment_files")
@NamedQuery(name="AilmentFiles.findAll", query="SELECT a FROM AilmentFiles a")
public class AilmentFiles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="idailment_files")
	private String idailmentFiles;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "date_added")
	private Date dateAdded;

	@Lob
	@Column(name = "data")
	private byte[] data;

	//bi-directional many-to-one association to Ailment
	@ManyToOne
	@JsonBackReference(value="ailmentFiles")
	private Ailment ailment;

	@ManyToOne
	@JsonBackReference
	private User user;

	public AilmentFiles() {
	}


	public AilmentFiles(String fileName, String contentType, byte[] bytes) {
		this.name = fileName;
		this.type = contentType;
		this.data = bytes;
	}
}