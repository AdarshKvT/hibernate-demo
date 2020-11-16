package com.kvtsoft.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.demo.entity.Student;

public class QueryStudent {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// create session factory and a session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			String fetchQuery = "from Student";

			// Retrieving Students: lastName = 'Kavtiyal'
			String lastNameQuery = "from Student s where s.lastName='kavtiyal'";

			// Retrieving Students using OR predicate
			String orQuery = "from Student s where s.lastName='KvT' OR s.firstName='Adarsh'";

			// Retrieving Students using LIKE predicate
			String likeQuery = "from Student s where s.email LIKE 'adarshkavtiyal@gmail.com' ";

			// query database using HQL
			List<Student> studentList = session.createQuery(fetchQuery).getResultList();

			if (studentList != null && !(studentList.isEmpty())) {
				System.out.println("\nObjects have been retrived");

				// display the retrieved objects
				displayStudents(studentList);

			} else {
				System.out.println("\nObeject not found !!");
			}

			// commit the transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("An error occured. Cannot perform query");
			e.printStackTrace();
		} finally {
			System.out.println("\n\nClosing the factory session");
			factory.close();
		}
	}

	private static void displayStudents(List<Student> studentList) {
		for (Student tempStudent : studentList) {
			System.out.println(tempStudent);
		}
	}

}
