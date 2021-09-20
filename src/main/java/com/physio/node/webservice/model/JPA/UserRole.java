package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.physio.node.webservice.model.DTO.UserRoleDTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iduser_role")
	private int iduserRole;

	@Column(name="role_frequency")
	private int roleFrequency;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userRole")
	@JsonBackReference
	private List<User> users;

	public UserRole() {
	}

	public UserRole(UserRoleDTO userRoleDTO) {
		this.iduserRole=userRoleDTO.getIduserRole();
		this.roleName=userRoleDTO.getRoleName();
	}

	public int getIduserRole() {
		return this.iduserRole;
	}

	public void setIduserRole(int iduserRole) {
		this.iduserRole = iduserRole;
	}

	public int getRoleFrequency() {
		return this.roleFrequency;
	}

	public void setRoleFrequency(int roleFrequency) {
		this.roleFrequency = roleFrequency;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserRole(null);

		return user;
	}

}