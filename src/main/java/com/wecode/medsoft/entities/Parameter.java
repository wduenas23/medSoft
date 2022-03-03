package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the parameters database table.
 * 
 */
@Entity
@Table(name="parameters")
@NamedQuery(name="Parameter.findAll", query="SELECT p FROM Parameter p")
public class Parameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pmt_id")
	private String pmtId;

	@Column(name="pmt_context")
	private String pmtContext;


	@Column(name="pmt_value")
	private String pmtValue;

	public Parameter() {
	}

	public String getPmtId() {
		return this.pmtId;
	}

	public void setPmtId(String pmtId) {
		this.pmtId = pmtId;
	}

	public String getPmtContext() {
		return this.pmtContext;
	}

	public void setPmtContext(String pmtContext) {
		this.pmtContext = pmtContext;
	}


	public String getPmtValue() {
		return this.pmtValue;
	}

	public void setPmtValue(String pmtValue) {
		this.pmtValue = pmtValue;
	}

}