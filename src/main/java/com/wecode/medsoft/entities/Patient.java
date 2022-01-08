package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pt_id")
	private Integer ptId;

	@Temporal(TemporalType.DATE)
	@Column(name="pt_birthday")
	private Date ptBirthday;

	@Column(name="pt_identification")
	private String ptIdentification;

	@Column(name="pt_last_name")
	private String ptLastName;

	@Column(name="pt_name")
	private String ptName;

	//bi-directional many-to-one association to PatientContact
	@ManyToOne
	@JoinColumn(name="pt_pc_id")
	private PatientContact patientContact;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="patient")
	private List<Transaction> transactions;

	public Patient() {
	}

	public Integer getPtId() {
		return this.ptId;
	}

	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}

	public Date getPtBirthday() {
		return this.ptBirthday;
	}

	public void setPtBirthday(Date ptBirthday) {
		this.ptBirthday = ptBirthday;
	}

	public String getPtIdentification() {
		return this.ptIdentification;
	}

	public void setPtIdentification(String ptIdentification) {
		this.ptIdentification = ptIdentification;
	}

	public String getPtLastName() {
		return this.ptLastName;
	}

	public void setPtLastName(String ptLastName) {
		this.ptLastName = ptLastName;
	}

	public String getPtName() {
		return this.ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public PatientContact getPatientContact() {
		return this.patientContact;
	}

	public void setPatientContact(PatientContact patientContact) {
		this.patientContact = patientContact;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setPatient(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setPatient(null);

		return transaction;
	}

}