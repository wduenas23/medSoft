package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleId;

	@Temporal(TemporalType.DATE)
	@Column(name="role_created_date")
	private Date roleCreatedDate;

	@Column(name="role_description")
	private String roleDescription;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-one association to RoleAuthorization
	@OneToMany(mappedBy="role")
	private List<RoleAuthorization> roleAuthorizations;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<User> users;

	public Role() {
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getRoleCreatedDate() {
		return this.roleCreatedDate;
	}

	public void setRoleCreatedDate(Date roleCreatedDate) {
		this.roleCreatedDate = roleCreatedDate;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RoleAuthorization> getRoleAuthorizations() {
		return this.roleAuthorizations;
	}

	public void setRoleAuthorizations(List<RoleAuthorization> roleAuthorizations) {
		this.roleAuthorizations = roleAuthorizations;
	}

	public RoleAuthorization addRoleAuthorization(RoleAuthorization roleAuthorization) {
		getRoleAuthorizations().add(roleAuthorization);
		roleAuthorization.setRole(this);

		return roleAuthorization;
	}

	public RoleAuthorization removeRoleAuthorization(RoleAuthorization roleAuthorization) {
		getRoleAuthorizations().remove(roleAuthorization);
		roleAuthorization.setRole(null);

		return roleAuthorization;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}