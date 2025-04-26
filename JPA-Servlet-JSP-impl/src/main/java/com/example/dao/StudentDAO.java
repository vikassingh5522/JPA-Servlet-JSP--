package com.example.dao;

import com.example.model.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StudentDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

	public void createStudent(Student student) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public Student getStudentById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Student.class, id);
		} finally {
			em.close();
		}
	}

	public List<Student> getAllStudents() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
		} finally {
			em.close();
		}
	}

	public void updateStudent(Student student) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(student);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void deleteStudent(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Student student = em.find(Student.class, id);
			if (student != null) {
				em.remove(student);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}