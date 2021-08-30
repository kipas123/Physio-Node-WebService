package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ailment_indication database table.
 * 
 */
@Entity
@Table(name="ailment_indication")
@NamedQuery(name="AilmentIndication.findAll", query="SELECT a FROM AilmentIndication a")
public class AilmentIndication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idailment_indication")
	private int idailmentIndication;

	@Column(name="indication_description")
	private String indicationDescription;

	@Column(name="indication_header")
	private String indicationHeader;

	//bi-directional many-to-one association to Ailment
	@ManyToOne
	@JsonBackReference
	private Ailment ailment;

	public AilmentIndication() {
	}

	public int getIdailmentIndication() {
		return this.idailmentIndication;
	}

	public void setIdailmentIndication(int idailmentIndication) {
		this.idailmentIndication = idailmentIndication;
	}

	public String getIndicationDescription() {
		return this.indicationDescription;
	}

	public void setIndicationDescription(String indicationDescription) {
		this.indicationDescription = indicationDescription;
	}

	public String getIndicationHeader() {
		return this.indicationHeader;
	}

	public void setIndicationHeader(String indicationHeader) {
		this.indicationHeader = indicationHeader;
	}

	public Ailment getAilment() {
		return this.ailment;
	}

	public void setAilment(Ailment ailment) {
		this.ailment = ailment;
	}

}