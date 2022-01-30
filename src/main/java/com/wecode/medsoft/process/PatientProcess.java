package com.wecode.medsoft.process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.incomes.RequestIncome;
import com.wecode.medsoft.contracts.patient.PatientInfo;
import com.wecode.medsoft.entities.ContactType;
import com.wecode.medsoft.entities.Patient;
import com.wecode.medsoft.entities.PatientContact;
import com.wecode.medsoft.persistence.ContactTypeRepository;
import com.wecode.medsoft.persistence.PatientContactRepository;
import com.wecode.medsoft.persistence.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientProcess {

	private PatientRepository patientRepository;
	private PatientContactRepository contactRepository; 
	private ContactTypeRepository contactTypeRepository; 
	
	@Autowired
	public PatientProcess(PatientRepository patientRepository,PatientContactRepository contactRepository,ContactTypeRepository contactTypeRepository) {
		this.patientRepository=patientRepository;
		this.contactRepository=contactRepository;
		this.contactTypeRepository=contactTypeRepository;
	}
	
	@Transactional
	public Patient createPatient(RequestIncome requestIncome) throws Exception {
		Patient patient=null;
		Date birthday=null;
		try {
			/*List<Patient> patients=patientRepository.findByPtIdentification(requestIncome.getPatient().getIdentification());
			if(patients!=null && patients.size()>0) {
				return patients.get(0);
			}	*/	
			
			List<Patient> patients=patientRepository.findByPtName(requestIncome.getPatient().getName());
			if(patients!=null && patients.size()>0) {
				for (Patient pat : patients) {
					if(pat.getPtLastName().equals(requestIncome.getPatient().getLastName())) {
						return pat;
					}
				}
			}
			
			patient=new Patient();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			birthday=(requestIncome.getPatient().getBirthday()!=null && !"".equals(requestIncome.getPatient().getBirthday()))?formatter.parse(requestIncome.getPatient().getBirthday()):null;
			patient.setPtBirthday(birthday);
			patient.setPatientContact(createPatientContactFromIncome(requestIncome));
			patient.setPtIdentification(requestIncome.getPatient().getIdentification());
			patient.setPtLastName(requestIncome.getPatient().getLastName());
			patient.setPtName(requestIncome.getPatient().getName());
			patientRepository.save(patient);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return patient;
	}
	
	@Transactional
	public PatientContact createPatientContactFromIncome(RequestIncome requestIncome) {
		PatientContact contact=null;
		try {
			contact=new PatientContact();
			List<ContactType> contactTypes= contactTypeRepository.findByCtName("RESIDENCIA");
			contact.setContactType(contactTypes.get(0));
			contact.setPcAddress(requestIncome.getPatient().getAddress());
			contact.setPcPhoneNumber(requestIncome.getPatient().getPhone());
			contact= contactRepository.save(contact);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return contact;
		
	}
	
	public ResponseEntity<PatientInfo> getPatientById(String id) {
		PatientInfo patientInfo=null;
		try {
			List<Patient> patient=patientRepository.findByPtIdentification(id);
			if(patient!=null && patient.size()>0 ) {
				patientInfo=new PatientInfo();
				patientInfo.setAddress(patient.get(0).getPatientContact().getPcAddress());
				patientInfo.setBirthday(patient.get(0).getPtBirthday()!=null?patient.get(0).getPtBirthday().toString():"");
				patientInfo.setIdentification(patient.get(0).getPtIdentification());
				patientInfo.setLastName(patient.get(0).getPtLastName());
				patientInfo.setName(patient.get(0).getPtName());
				patientInfo.setPhone(patient.get(0).getPatientContact().getPcPhoneNumber());
				patientInfo.setId(patient.get(0).getPtId());
				
				return new ResponseEntity<>(patientInfo,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(patientInfo,HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(patientInfo,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
