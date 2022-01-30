package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the authorizations database table.
 * 
 */
@Entity
@Table(name="authorizations")
@NamedQuery(name="Authorization.findAll", query="SELECT a FROM Authorization a")
public class Authorization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="auth_id")
	private Integer authId;

	@Temporal(TemporalType.DATE)
	@Column(name="auth_created_date")
	private Date authCreatedDate;

	@Column(name="auth_display_name")
	private String authDisplayName;

	@Column(name="auth_icon")
	private String authIcon;

	@Column(name="auth_name")
	private String authName;

	@Column(name="auth_order")
	private Double authOrder;

	@Column(name="auth_parent")
	private Boolean authParent;
	
	@Column(name="auth_parent_id")
	private Integer authParentId;
	
	@Column(name="auth_link")
	private String authLink;

	//bi-directional many-to-one association to RoleAuthorization
	@OneToMany(mappedBy="authorization")
	private List<RoleAuthorization> roleAuthorizations;

	public Authorization() {
	}

	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Date getAuthCreatedDate() {
		return this.authCreatedDate;
	}

	public void setAuthCreatedDate(Date authCreatedDate) {
		this.authCreatedDate = authCreatedDate;
	}

	public String getAuthDisplayName() {
		return this.authDisplayName;
	}

	public void setAuthDisplayName(String authDisplayName) {
		this.authDisplayName = authDisplayName;
	}

	public String getAuthIcon() {
		return this.authIcon;
	}

	public void setAuthIcon(String authIcon) {
		this.authIcon = authIcon;
	}

	public String getAuthName() {
		return this.authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public Double getAuthOrder() {
		return this.authOrder;
	}

	public void setAuthOrder(Double authOrder) {
		this.authOrder = authOrder;
	}

	public Boolean getAuthParent() {
		return this.authParent;
	}

	public void setAuthParent(Boolean authParent) {
		this.authParent = authParent;
	}

	public List<RoleAuthorization> getRoleAuthorizations() {
		return this.roleAuthorizations;
	}

	public void setRoleAuthorizations(List<RoleAuthorization> roleAuthorizations) {
		this.roleAuthorizations = roleAuthorizations;
	}

	public RoleAuthorization addRoleAuthorization(RoleAuthorization roleAuthorization) {
		getRoleAuthorizations().add(roleAuthorization);
		roleAuthorization.setAuthorization(this);

		return roleAuthorization;
	}

	public RoleAuthorization removeRoleAuthorization(RoleAuthorization roleAuthorization) {
		getRoleAuthorizations().remove(roleAuthorization);
		roleAuthorization.setAuthorization(null);

		return roleAuthorization;
	}

	public Integer getAuthParentId() {
		return authParentId;
	}

	public void setAuthParentId(Integer authParentId) {
		this.authParentId = authParentId;
	}

	public String getAuthLink() {
		return authLink;
	}

	public void setAuthLink(String authLink) {
		this.authLink = authLink;
	}

}