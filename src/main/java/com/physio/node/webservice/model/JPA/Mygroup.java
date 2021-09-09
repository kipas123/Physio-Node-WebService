package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.physio.node.webservice.model.DTO.MyGroupDTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mygroup database table.
 * 
 */
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
	@JoinColumn(name="founder_iduser")
	@JsonBackReference
	private User mygroupFounder;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="mygroups")
	@JsonBackReference
	private List<User> users;

	public Mygroup() {
	}

	public Mygroup(String mygroupName, String mygroupDescription, User mygroupFounder) {
		this.mygroupName = mygroupName;
		this.mygroupDescription = mygroupDescription;
		this.mygroupFounder = mygroupFounder;
	}

    public Mygroup(MyGroupDTO myGroupDTO) {
		this.idmygroup = myGroupDTO.getIdMygroup();
		this.mygroupName = myGroupDTO.getMygroupName();
		this.mygroupDescription = myGroupDTO.getMygroupDescription();
    }

    public int getIdmygroup() {
		return this.idmygroup;
	}

	public void setIdmygroup(int idmygroup) {
		this.idmygroup = idmygroup;
	}

	public String getMygroupDescription() {
		return this.mygroupDescription;
	}

	public void setMygroupDescription(String mygroupDescription) {
		this.mygroupDescription = mygroupDescription;
	}

	public String getMygroupName() {
		return this.mygroupName;
	}

	public void setMygroupName(String mygroupName) {
		this.mygroupName = mygroupName;
	}

	public User getMygroupFounder() {
		return mygroupFounder;
	}

	public void setMygroupFounder(User mygroupFounder) {
		this.mygroupFounder = mygroupFounder;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}