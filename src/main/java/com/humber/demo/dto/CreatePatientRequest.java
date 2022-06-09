package com.humber.demo.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CreatePatientRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreatePatientRequest {
	
	@XmlElement(required = true)
    private String name;
	
	@XmlElement
	private String disease;

	@XmlElement
	private Date dateOfBirth;

	@XmlElement
	private int doctor_id;
	
	public String getName() {
		return name;
	}

	public void setName(String patientName) {
		this.name = patientName;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	
}
