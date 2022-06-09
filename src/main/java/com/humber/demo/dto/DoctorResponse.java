package com.humber.demo.dto;

import javax.xml.bind.annotation.XmlType;
import com.humber.demo.entity.Doctor;

@XmlType(name = "DoctorResponse")
public class DoctorResponse {
	
	private Doctor doctor;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
