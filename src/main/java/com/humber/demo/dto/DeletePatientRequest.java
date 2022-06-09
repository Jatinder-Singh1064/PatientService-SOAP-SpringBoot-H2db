package com.humber.demo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DeletePatientRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeletePatientRequest {
	
	@XmlElement(required = true)
    private int id;
	
	@XmlElement(required = true)
    private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
