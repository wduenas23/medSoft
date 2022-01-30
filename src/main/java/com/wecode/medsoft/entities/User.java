package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.ColumnTransformer;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usr_id")
	private Integer usrId;

	@Column(name="usr_last_name")
	private String usrLastName;

	@Column(name="usr_name")
	private String usrName;

	@Column(name="usr_password")
	private String usrPassword;

	@Column(name="usr_user")
	private String usrUser;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="usr_role_id")
	private Role role;

	public User() {
	}

	public Integer getUsrId() {
		return this.usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public String getUsrLastName() {
		return this.usrLastName;
	}

	public void setUsrLastName(String usrLastName) {
		this.usrLastName = usrLastName;
	}

	public String getUsrName() {
		return this.usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPassword() {
		return this.usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public String getUsrUser() {
		return this.usrUser;
	}

	public void setUsrUser(String usrUser) {
		this.usrUser = usrUser;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}