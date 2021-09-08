package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ailment_note database table.
 * 
 */
@Entity
@Table(name="ailment_note")
@NamedQuery(name="AilmentNote.findAll", query="SELECT a FROM AilmentNote a")
public class AilmentNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idailment_note")
	private int idailmentNote;

	@Column(name="note_description")
	private String noteDescription;

	@Column(name="note_header")
	private String noteHeader;

	//bi-directional many-to-one association to Ailment
	@ManyToOne
	@JsonBackReference(value="ailmentNotes")
	private Ailment ailment;

	public AilmentNote() {
	}

	public AilmentNote(String noteHeader, String noteDescription, Ailment ailment){
		this.noteHeader = noteHeader;
		this.noteDescription = noteDescription;
		this.ailment = ailment;
	}

	public int getIdailmentNote() {
		return this.idailmentNote;
	}

	public void setIdailmentNote(int idailmentNote) {
		this.idailmentNote = idailmentNote;
	}

	public String getNoteDescription() {
		return this.noteDescription;
	}

	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	public String getNoteHeader() {
		return this.noteHeader;
	}

	public void setNoteHeader(String noteHeader) {
		this.noteHeader = noteHeader;
	}

	public Ailment getAilment() {
		return this.ailment;
	}

	public void setAilment(Ailment ailment) {
		this.ailment = ailment;
	}

}