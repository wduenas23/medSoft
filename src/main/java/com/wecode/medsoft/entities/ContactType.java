package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the contact_type database table.
 * 
 */
@Entity
@Table(name="contact_type")
@NamedQuery(name="ContactType.findAll", query="SELECT c FROM ContactType c")
public class ContactType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ct_id")
	private Integer ctId;

	@Temporal(TemporalType.DATE)
	@Column(name="ct_created_date")
	private Date ctCreatedDate;

	@Column(name="ct_description")
	private String ctDescription;

	@Column(name="ct_name")
	private String ctName;

	//bi-directional many-to-one association to PatientContact
	@OneToMany(mappedBy="contactType")
	private List<PatientContact> patientContacts;

	public ContactType() {
	}

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public Date getCtCreatedDate() {
		return this.ctCreatedDate;
	}

	public void setCtCreatedDate(Date ctCreatedDate) {
		this.ctCreatedDate = ctCreatedDate;
	}

	public String getCtDescription() {
		return this.ctDescription;
	}

	public void setCtDescription(String ctDescription) {
		this.ctDescription = ctDescription;
	}

	public String getCtName() {
		return this.ctName;
	}

	public void setCtName(String ctName) {
		this.ctName = ctName;
	}

	public List<PatientContact> getPatientContacts() {
		return this.patientContacts;
	}

	public void setPatientContacts(List<PatientContact> patientContacts) {
		this.patientContacts = patientContacts;
	}

	public PatientContact addPatientContact(PatientContact patientContact) {
		getPatientContacts().add(patientContact);
		patientContact.setContactType(this);

		return patientContact;
	}

	public PatientContact removePatientContact(PatientContact patientContact) {
		getPatientContacts().remove(patientContact);
		patientContact.setContactType(null);

		return patientContact;
	}

}