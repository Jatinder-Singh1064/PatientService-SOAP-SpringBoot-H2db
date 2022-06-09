package com.humber.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.humber.demo.service.PatientServiceImpl;

@Configuration
public class PatientConfig {
	
	@Autowired
	private Bus bus;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(bus, new PatientServiceImpl(entityManagerFactory));
		endpoint.publish("/patientservice");
		return endpoint;
	}

}
