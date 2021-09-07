package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
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

	@Temporal(TemporalType.DATE)
	@Column(name="user_dob")
	private Date userDob;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_surname")
	private String userSurname;

	//bi-directional many-to-one association to Ailment
	@OneToMany(mappedBy="user")
	@JsonManagedReference(value="user_iduser")
	private List<Ailment> userAilment;

	//bi-directional many-to-one association to Ailment
	@OneToMany(mappedBy="attendingphysician")
	@JsonManagedReference(value="attendingphysician_iduser")
	private List<Ailment> attendingphysicianAilment;

	//bi-directional many-to-one association to Mygroup
	@OneToMany(mappedBy="founder")
	@JsonManagedReference
	private List<Mygroup> founderMygroups;

	//bi-directional many-to-many association to Mygroup
	@ManyToMany
	@JsonManagedReference
	@JoinTable(
		name="user_mygroup"
		, joinColumns={
			@JoinColumn(name="user_iduser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="mygroup_idmygroup")
			}
		)
	private List<Mygroup> mygroups;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="user_role_iduser_role")
	private UserRole userRole;

	public User() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Date getUserDob() {
		return this.userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return this.userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	List<Ailment> getUserAilment() {
		return userAilment;
	}

	void setUserAilment(List<Ailment> userAilment) {
		this.userAilment = userAilment;
	}

	List<Ailment> getAttendingphysicianAilment() {
		return attendingphysicianAilment;
	}

	void setAttendingphysicianAilment(List<Ailment> attendingphysicianAilment) {
		this.attendingphysicianAilment = attendingphysicianAilment;
	}

	public Ailment addUserAilment(Ailment userAilment) {
		getUserAilment().add(userAilment);
		userAilment.setUser(this);

		return userAilment;
	}

	public Ailment removeAilments1(Ailment userAilment) {
		getUserAilment().remove(userAilment);
		userAilment.setUser(null);

		return userAilment;
	}

	public Ailment addAttendingphysicianAilment(Ailment attendingphysicianAilment) {
		getAttendingphysicianAilment().add(attendingphysicianAilment);
		attendingphysicianAilment.setAttendingphysician(this);

		return attendingphysicianAilment;
	}

	public Ailment removeAttendingphysicianAilment(Ailment attendingphysicianAilment) {
		getAttendingphysicianAilment().remove(attendingphysicianAilment);
		attendingphysicianAilment.setAttendingphysician(null);

		return attendingphysicianAilment;
	}


	public Mygroup addFounderMygroups(Mygroup founderMygroups) {
		getFounderMygroups().add(founderMygroups);
		founderMygroups.setFounder(this);

		return founderMygroups;
	}

	public Mygroup removeFounderMygroups(Mygroup founderMygroups) {
		getFounderMygroups().remove(founderMygroups);
		founderMygroups.setFounder(null);

		return founderMygroups;
	}

	List<Mygroup> getFounderMygroups() {
		return founderMygroups;
	}

	void setFounderMygroups(List<Mygroup> founderMygroups) {
		this.founderMygroups = founderMygroups;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}