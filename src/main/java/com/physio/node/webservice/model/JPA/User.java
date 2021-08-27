package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	@Column(name="`user_email`")
	private String userEmail;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to Ailment
	@OneToMany(mappedBy="user")
	@JsonManagedReference
	private List<Ailment> ailments;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="user_role_iduser_role")
	@JsonManagedReference
	private UserRole userRole;

	//bi-directional many-to-many association to Group
	@ManyToMany
	@JoinTable(
		name="user_groups"
		, joinColumns={
			@JoinColumn(name="user_iduser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="group_idgroup")
			}
		)
	@JsonBackReference
	private List<Group> groups;

	public User() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getUserE_mail() {
		return this.userEmail;
	}

	public void setUserE_mail(String userE_mail) {
		this.userEmail = userE_mail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Ailment> getAilments() {
		return this.ailments;
	}

	public void setAilments(List<Ailment> ailments) {
		this.ailments = ailments;
	}

	public Ailment addAilment(Ailment ailment) {
		getAilments().add(ailment);
		ailment.setUser(this);

		return ailment;
	}

	public Ailment removeAilment(Ailment ailment) {
		getAilments().remove(ailment);
		ailment.setUser(null);

		return ailment;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}