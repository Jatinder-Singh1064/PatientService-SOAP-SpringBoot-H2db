package com.humber.demo.data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.humber.demo.entity.Doctor;
import com.humber.demo.entity.Patient;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>  {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Doctor doctor1 = new Doctor("doctor1");
		Patient patient1 = new Patient("patient1", "disease1", getBirthDateObj("1975-06-04"), doctor1);
		Patient patient2 = new Patient("patient2", "disease2", getBirthDateObj("1979-08-10"), doctor1);

		doctor1.add(patient1);
		doctor1.add(patient2);

		entityManager.persist(doctor1);
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();

		Doctor doctor2 = new Doctor("doctor2");
		Patient patient3 = new Patient("patient3", "disease2", getBirthDateObj("1985-10-04"), doctor2);
		Patient patient4 = new Patient("patient4", "disease4", getBirthDateObj("1998-11-24"), doctor2);

		doctor2.add(patient3);
		doctor2.add(patient4);

		entityManager.persist(doctor2);
		entityManager.getTransaction().commit();		
	}
	
	public Date getBirthDateObj(String date) {
		LocalDate birthDate = LocalDate.parse(date);
		Instant startOfDay = birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		return Date.from(startOfDay);
	}
}
