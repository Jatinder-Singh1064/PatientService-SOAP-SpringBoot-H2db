package com.humber.demo.dto;

import javax.xml.bind.annotation.XmlType;
import com.humber.demo.entity.Patient;

@XmlType(name = "CreatePatientResponse")
public class CreatePatientResponse {

	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}	
}
