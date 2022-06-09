package com.humber.demo.dto;

import java.util.List;
import com.humber.demo.entity.Patient;

public class PatientResponse {

	private List<Patient> patientsList;

	public List<Patient> getPatientsList() {
		return patientsList;
	}

	public void setPatientsList(List<Patient> patientsList) {
		this.patientsList = patientsList;
	}
	
}
