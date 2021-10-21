package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.physio.node.webservice.model.DTO.User.UserWriteModel;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_password")
	private String userPassword;


	@Temporal(TemporalType.DATE)
	@Column(name="user_dob")
	private Date userDob;


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
	@OneToMany(mappedBy="mygroupOwner")
	@JsonBackReference
	private List<Mygroup> mygroupOwner;

	//bi-directional many-to-many association to Mygroup
	@OneToMany(mappedBy="user")
	@JsonManagedReference
	private List<Mygroup_Users> userMygroups;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="user_role_iduser_role")
	private UserRole userRole;

	@OneToMany
	@JsonManagedReference
	private List<Message> message;

	@Transient
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User() {
	}
	public User(UserWriteModel user) {
		this.userName = user.getUserName();
		this.userSurname = user.getUserSurname();
		this.userEmail = user.getUserEmail();
		this.userDob = user.getUserDob();
		this.iduser=user.getUserId();
		this.userPassword=user.getUserPassword();
	}
	public User(int iduser){
		this.iduser = iduser;
	}



//	public Ailment removeAilments1(Ailment userAilment) {
//		getUserAilment().remove(userAilment);
//		userAilment.setUser(null);
//
//		return userAilment;
//	}
//
//	public Ailment addAttendingphysicianAilment(Ailment attendingphysicianAilment) {
//		getAttendingphysicianAilment().add(attendingphysicianAilment);
//		attendingphysicianAilment.setAttendingphysician(this);
//
//		return attendingphysicianAilment;
//	}
//
//	public Ailment removeAttendingphysicianAilment(Ailment attendingphysicianAilment) {
//		getAttendingphysicianAilment().remove(attendingphysicianAilment);
//		attendingphysicianAilment.setAttendingphysician(null);
//
//		return attendingphysicianAilment;
//	}


//	public Mygroup addFounderMygroups(Mygroup mygroupOwner) {
//		getFounderMygroups().add(mygroupOwner);
//		mygroupOwner.setMygroupOwner(this);
//
//		return mygroupOwner;
//	}
//
//	public Mygroup removeFounderMygroups(Mygroup mygroupOwner) {
//		getFounderMygroups().remove(mygroupOwner);
//		mygroupOwner.setMygroupOwner(null);
//
//		return mygroupOwner;
//	}


}