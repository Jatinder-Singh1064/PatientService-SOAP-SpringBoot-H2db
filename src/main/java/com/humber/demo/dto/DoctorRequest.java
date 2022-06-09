package com.humber.demo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DoctorRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoctorRequest {
	
	@XmlElement(required = true)
    private String doctorName;
 
    public String getDoctorName() {
        return doctorName;
    }
 
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

}
