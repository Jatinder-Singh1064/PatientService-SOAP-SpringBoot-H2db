package com.humber.demo.service;

import java.util.List;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import com.humber.demo.dto.CreatePatientRequest;
import com.humber.demo.dto.CreatePatientResponse;
import com.humber.demo.dto.DeletePatientRequest;
import com.humber.demo.dto.DeletePatientResponse;
import com.humber.demo.dto.DoctorRequest;
import com.humber.demo.dto.DoctorResponse;
import com.humber.demo.dto.PatientResponse;
import com.humber.demo.dto.UpdatePatientRequest;
import com.humber.demo.dto.UpdatePatientResponse;
import com.humber.demo.entity.Doctor;
import com.humber.demo.entity.Patient;

@Service
@HandlerChain(file="../handler/handler-chain.xml")
public class PatientServiceImpl implements PatientService {

	private EntityManagerFactory entityManagerFactory;

	public PatientServiceImpl(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	// READ - GET
	@Override
	public DoctorResponse getDoctorInfo(DoctorRequest request) {
		String name = request.getDoctorName();

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select a from Doctor a where a.name = :name");
		query.setParameter("name", name);
		Doctor doctor = (Doctor) query.getSingleResult();
		entityManager.getTransaction().commit();

		DoctorResponse response = new DoctorResponse();
		response.setDoctor(doctor);
		return response;
	}

	// CREATE - POST
	@Override
	public CreatePatientResponse createPatient(CreatePatientRequest request) {
		Patient p = GetLatestPatientRecord();
		int patient_id = p.getId() + 1;

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery(
				"insert into patient (id, name, disease, date_of_birth, doctor_id) VALUES (:id, :name, :disease, :date_of_birth, :doctor_id)");
		query.setParameter("id", patient_id);
		query.setParameter("name", request.getName());
		query.setParameter("disease", request.getDisease());
		query.setParameter("date_of_birth", request.getDateOfBirth());
		query.setParameter("doctor_id", request.getDoctor_id());
		int result = query.executeUpdate();
		System.out.println("Insert Rows affected: " + result);

		Query squery = entityManager.createQuery("select p from Patient p where p.id = (select max(id) from Patient)");
		Patient patient = (Patient) squery.getSingleResult();
		entityManager.getTransaction().commit();

		CreatePatientResponse response = new CreatePatientResponse();
		response.setPatient(patient);
		return response;
	}

	@WebMethod(exclude = true)
	public Patient GetLatestPatientRecord() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query squery = entityManager.createQuery("select p from Patient p where p.id = (select max(id) from Patient)");
		Patient patient = (Patient) squery.getSingleResult();
		entityManager.getTransaction().commit();
		return patient;
	}

	// UPDATE - PUT
	@Override
	public UpdatePatientResponse updatePatient(UpdatePatientRequest request) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery(
				"update Patient a SET a.disease = :disease, a.date_of_birth = :dob WHERE a.name = :name AND a.id = :id");
		query.setParameter("disease", request.getDisease());
		query.setParameter("dob", request.getDateOfBirth());
		query.setParameter("name", request.getName());
		query.setParameter("id", request.getId());

		int result = query.executeUpdate();
		System.out.println("Update Rows affected: " + result);

		if (result > 0) {
			Query squery = entityManager.createQuery("select p from Patient p where p.id = :id");
			squery.setParameter("id", request.getId());
			Patient patient = (Patient) squery.getSingleResult();

			entityManager.getTransaction().commit();
			UpdatePatientResponse response = new UpdatePatientResponse();
			response.setPatient(patient);
			return response;
		}

		return null;
	}

	// DELETE - DELETE
	@Override
	public DeletePatientResponse deletePatient(DeletePatientRequest request) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query squery = entityManager.createQuery("select p from Patient p where p.id = :id");
		squery.setParameter("id", request.getId());
		Patient patient = (Patient) squery.getSingleResult();
		
		Query query = entityManager.createNativeQuery("delete FROM Patient a WHERE a.name = :name AND a.id = :id");
		query.setParameter("name", request.getName());
		query.setParameter("id", request.getId());

		int result = query.executeUpdate();
		System.out.println("DELETE Rows affected: " + result);
		
		if (result > 0) {
			entityManager.getTransaction().commit();
			
			DeletePatientResponse response = new DeletePatientResponse();
			response.setPatient(patient);
			return response;
		}
		
		return null;
	}
	
	public PatientResponse getPatientsList() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select p from Patient p");
		List<Patient> patientList = (List<Patient>) query.getResultList();
		entityManager.getTransaction().commit();

		PatientResponse response = new PatientResponse();
		response.setPatientsList(patientList);
		return response;
	}

}
