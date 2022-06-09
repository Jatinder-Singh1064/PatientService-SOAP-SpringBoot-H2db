package com.humber.demo.service;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import com.humber.demo.dto.*;
import com.humber.demo.entity.Patient;

@WebService(name = "PatientService", endpointInterface = "com.humber.demo.service.PatientService")
public interface PatientService {
	public DoctorResponse getDoctorInfo(@XmlElement(required = true) DoctorRequest request);
	
	public CreatePatientResponse createPatient(@XmlElement(required = true) CreatePatientRequest request);
	
	public Patient GetLatestPatientRecord();
	
	public UpdatePatientResponse updatePatient(@XmlElement(required = true) UpdatePatientRequest request);
	
	public DeletePatientResponse deletePatient(@XmlElement(required = true) DeletePatientRequest request);
	
	public PatientResponse getPatientsList();
}
