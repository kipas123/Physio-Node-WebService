package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.physio.node.webservice.model.DTO.MyGroupWriteModel;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mygroup database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Mygroup.findAll", query="SELECT m FROM Mygroup m")
public class Mygroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmygroup;

	@Column(name="mygroup_description")
	private String mygroupDescription;

	@Column(name="mygroup_name")
	private String mygroupName;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="mygroup_owner")
	@JsonBackReference
	private User mygroupOwner;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="mygroups")
	@JsonBackReference
	private List<User> users;

	public Mygroup() {
	}

	public Mygroup(String mygroupName, String mygroupDescription, User mygroupOwner) {
		this.mygroupName = mygroupName;
		this.mygroupDescription = mygroupDescription;
		this.mygroupOwner = mygroupOwner;
	}

    public Mygroup(MyGroupWriteModel myGroupWriteModel) {
		this.idmygroup = myGroupWriteModel.getIdmygroup();
		this.mygroupName = myGroupWriteModel.getMygroupName();
		this.mygroupDescription = myGroupWriteModel.getMygroupDescription();
		if(myGroupWriteModel.getMygroupOwner()!=null){
			this.mygroupOwner = new User(myGroupWriteModel.getMygroupOwner());
		}
    }



}