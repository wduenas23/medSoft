package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the patient_contact database table.
 * 
 */
@Entity
@Table(name="patient_contact")
@NamedQuery(name="PatientContact.findAll", query="SELECT p FROM PatientContact p")
public class PatientContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pc_id")
	private Integer pcId;

	@Column(name="pc_address")
	private String pcAddress;

	@Column(name="pc_phone_number")
	private String pcPhoneNumber;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="patientContact")
	private List<Patient> patients;

	//bi-directional many-to-one association to ContactType
	@ManyToOne
	@JoinColumn(name="pc_ct_id")
	private ContactType contactType;

	public PatientContact() {
	}

	public Integer getPcId() {
		return this.pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public String getPcAddress() {
		return this.pcAddress;
	}

	public void setPcAddress(String pcAddress) {
		this.pcAddress = pcAddress;
	}

	public String getPcPhoneNumber() {
		return this.pcPhoneNumber;
	}

	public void setPcPhoneNumber(String pcPhoneNumber) {
		this.pcPhoneNumber = pcPhoneNumber;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setPatientContact(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setPatientContact(null);

		return patient;
	}

	public ContactType getContactType() {
		return this.contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

}