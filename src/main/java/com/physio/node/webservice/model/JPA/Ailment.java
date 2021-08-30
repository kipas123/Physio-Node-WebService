package com.physio.node.webservice.model.JPA;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ailment database table.
 * 
 */
@Entity
@NamedQuery(name="Ailment.findAll", query="SELECT a FROM Ailment a")
public class Ailment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idailment;

	@Lob
	@Column(name="ailment_description")
	private String ailmentDescription;

	@Column(name="ailment_name")
	private String ailmentName;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JsonBackReference
	private User user;

	//bi-directional many-to-one association to AilmentFilepath
	@OneToMany(mappedBy="ailment")
	@JsonManagedReference
	private List<AilmentFilepath> ailmentFilepaths;

	//bi-directional many-to-one association to AilmentIndication
	@OneToMany(mappedBy="ailment")
	@JsonManagedReference
	private List<AilmentIndication> ailmentIndications;

	//bi-directional many-to-one association to AilmentNote
	@OneToMany(mappedBy="ailment")
	@JsonManagedReference
	private List<AilmentNote> ailmentNotes;

	public Ailment() {
	}

	public int getIdailment() {
		return this.idailment;
	}

	public void setIdailment(int idailment) {
		this.idailment = idailment;
	}

	public String getAilmentDescription() {
		return this.ailmentDescription;
	}

	public void setAilmentDescription(String ailmentDescription) {
		this.ailmentDescription = ailmentDescription;
	}

	public String getAilmentName() {
		return this.ailmentName;
	}

	public void setAilmentName(String ailmentName) {
		this.ailmentName = ailmentName;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AilmentFilepath> getAilmentFilepaths() {
		return this.ailmentFilepaths;
	}

	public void setAilmentFilepaths(List<AilmentFilepath> ailmentFilepaths) {
		this.ailmentFilepaths = ailmentFilepaths;
	}

	public AilmentFilepath addAilmentFilepath(AilmentFilepath ailmentFilepath) {
		getAilmentFilepaths().add(ailmentFilepath);
		ailmentFilepath.setAilment(this);

		return ailmentFilepath;
	}

	public AilmentFilepath removeAilmentFilepath(AilmentFilepath ailmentFilepath) {
		getAilmentFilepaths().remove(ailmentFilepath);
		ailmentFilepath.setAilment(null);

		return ailmentFilepath;
	}

	public List<AilmentIndication> getAilmentIndications() {
		return this.ailmentIndications;
	}

	public void setAilmentIndications(List<AilmentIndication> ailmentIndications) {
		this.ailmentIndications = ailmentIndications;
	}

	public AilmentIndication addAilmentIndication(AilmentIndication ailmentIndication) {
		getAilmentIndications().add(ailmentIndication);
		ailmentIndication.setAilment(this);

		return ailmentIndication;
	}

	public AilmentIndication removeAilmentIndication(AilmentIndication ailmentIndication) {
		getAilmentIndications().remove(ailmentIndication);
		ailmentIndication.setAilment(null);

		return ailmentIndication;
	}

	public List<AilmentNote> getAilmentNotes() {
		return this.ailmentNotes;
	}

	public void setAilmentNotes(List<AilmentNote> ailmentNotes) {
		this.ailmentNotes = ailmentNotes;
	}

	public AilmentNote addAilmentNote(AilmentNote ailmentNote) {
		getAilmentNotes().add(ailmentNote);
		ailmentNote.setAilment(this);

		return ailmentNote;
	}

	public AilmentNote removeAilmentNote(AilmentNote ailmentNote) {
		getAilmentNotes().remove(ailmentNote);
		ailmentNote.setAilment(null);

		return ailmentNote;
	}

}