package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the role_authorization database table.
 * 
 */
@Entity
@Table(name="role_authorization")
@NamedQuery(name="RoleAuthorization.findAll", query="SELECT r FROM RoleAuthorization r")
public class RoleAuthorization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rauth_id")
	private Integer rauthId;

	@Temporal(TemporalType.DATE)
	@Column(name="rauth_created_date")
	private Date rauthCreatedDate;

	//bi-directional many-to-one association to Authorization
	@ManyToOne
	@JoinColumn(name="rauth_auth_id")
	private Authorization authorization;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="rauth_role_id")
	private Role role;

	public RoleAuthorization() {
	}

	public Integer getRauthId() {
		return this.rauthId;
	}

	public void setRauthId(Integer rauthId) {
		this.rauthId = rauthId;
	}

	public Date getRauthCreatedDate() {
		return this.rauthCreatedDate;
	}

	public void setRauthCreatedDate(Date rauthCreatedDate) {
		this.rauthCreatedDate = rauthCreatedDate;
	}

	public Authorization getAuthorization() {
		return this.authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}