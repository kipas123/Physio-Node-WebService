package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ailment database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Ailment.findAll", query="SELECT a FROM Ailment a")
public class Ailment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idailment;

	@Column(name="ailment_description")
	private String ailmentDescription;

	@Column(name="ailment_name")
	private String ailmentName;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_iduser")
	@JsonBackReference(value="user_iduser")
	private User user;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="attendingphysician_iduser")
	@JsonBackReference(value="attendingphysician_iduser")
	private User attendingphysician;

	//bi-directional many-to-one association to AilmentFilepath
	@OneToMany(mappedBy="ailment")
	@JsonManagedReference(value="ailmentFilepaths")
	private List<AilmentFilepath> ailmentFilepaths;

	//bi-directional many-to-one association to AilmentIndication
	@OneToMany(mappedBy="ailment")
	@JsonManagedReference(value="ailmentIndications")
	private List<AilmentIndication> ailmentIndications;

	//bi-directional many-to-one association to AilmentNote
	@OneToMany(mappedBy="ailment")
	@JsonManagedReference(value="ailmentNotes")
	private List<AilmentNote> ailmentNotes;

	public Ailment() {
	}
	public Ailment(int idailment){
		this.idailment = idailment;
	}

	public Ailment(String ailmentName, String ailmentDescription, User user, User attendingphysician) {
		this.ailmentName = ailmentName;
		this.ailmentDescription = ailmentDescription;
		this.user = user;
		this.attendingphysician=attendingphysician;
	}

//
//	public AilmentFilepath addAilmentFilepath(AilmentFilepath ailmentFilepath) {
//		getAilmentFilepaths().add(ailmentFilepath);
//		ailmentFilepath.setAilment(this);
//
//		return ailmentFilepath;
//	}
//
//	public AilmentFilepath removeAilmentFilepath(AilmentFilepath ailmentFilepath) {
//		getAilmentFilepaths().remove(ailmentFilepath);
//		ailmentFilepath.setAilment(null);
//
//		return ailmentFilepath;
//	}
//
//	public void setAilmentIndications(List<AilmentIndication> ailmentIndications) {
//		this.ailmentIndications = ailmentIndications;
//	}
//
//	public AilmentIndication addAilmentIndication(AilmentIndication ailmentIndication) {
//		getAilmentIndications().add(ailmentIndication);
//		ailmentIndication.setAilment(this);
//
//		return ailmentIndication;
//	}
//
//	public AilmentIndication removeAilmentIndication(AilmentIndication ailmentIndication) {
//		getAilmentIndications().remove(ailmentIndication);
//		ailmentIndication.setAilment(null);
//
//		return ailmentIndication;
//	}

//	public AilmentNote addAilmentNote(AilmentNote ailmentNote) {
//		getAilmentNotes().add(ailmentNote);
//		ailmentNote.setAilment(this);
//
//		return ailmentNote;
//	}
//
//	public AilmentNote removeAilmentNote(AilmentNote ailmentNote) {
//		getAilmentNotes().remove(ailmentNote);
//		ailmentNote.setAilment(null);
//
//		return ailmentNote;
//	}

}