package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ailment_filepath database table.
 * 
 */
@Entity
@Table(name="ailment_filepath")
@NamedQuery(name="AilmentFilepath.findAll", query="SELECT a FROM AilmentFilepath a")
public class AilmentFilepath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idailment_filepath")
	private int idailmentFilepath;

	private String path;

	//bi-directional many-to-one association to Ailment
	@ManyToOne
	@JsonBackReference(value="ailmentFilepaths")
	private Ailment ailment;

	public AilmentFilepath() {
	}

	public int getIdailmentFilepath() {
		return this.idailmentFilepath;
	}

	public void setIdailmentFilepath(int idailmentFilepath) {
		this.idailmentFilepath = idailmentFilepath;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Ailment getAilment() {
		return this.ailment;
	}

	public void setAilment(Ailment ailment) {
		this.ailment = ailment;
	}

}