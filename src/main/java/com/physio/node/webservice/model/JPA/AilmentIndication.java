package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ailment_indication database table.
 * 
 */
@Data
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
	@JsonBackReference(value="ailmentIndications")
	private Ailment ailment;

	public AilmentIndication() {
	}

	public AilmentIndication(String indicationHeader, String indicationDescription, Ailment ailment){
		this.indicationHeader = indicationHeader;
		this.indicationDescription = indicationDescription;
		this.ailment = ailment;
	}


}