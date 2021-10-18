package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the services_category database table.
 * 
 */
@Entity
@Table(name="services_category")
@NamedQuery(name="ServicesCategory.findAll", query="SELECT s FROM ServicesCategory s")
public class ServicesCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sc_id")
	private Integer scId;

	@Temporal(TemporalType.DATE)
	@Column(name="sc_created_date")
	private Date scCreatedDate;

	@Column(name="sc_description")
	private String scDescription;

	@Column(name="sc_name")
	private String scName;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="servicesCategory")
	private List<Service> services;

	public ServicesCategory() {
	}

	public Integer getScId() {
		return this.scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public Date getScCreatedDate() {
		return this.scCreatedDate;
	}

	public void setScCreatedDate(Date scCreatedDate) {
		this.scCreatedDate = scCreatedDate;
	}

	public String getScDescription() {
		return this.scDescription;
	}

	public void setScDescription(String scDescription) {
		this.scDescription = scDescription;
	}

	public String getScName() {
		return this.scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setServicesCategory(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setServicesCategory(null);

		return service;
	}

}