package com.kvtsoft.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.demo.entity.Student;

public class UpdateStudent {

	public static void main(String[] args) {

		int studentId = 1;

		// create session factory and a session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		try {

			Session session = factory.getCurrentSession();
			session.beginTransaction();

			// Retrieving student object from the database instead of creating new one
			Student theStudent = session.get(Student.class, studentId);

			// update the name and email of student
			theStudent.setLastName("Uchiha");
			theStudent.setEmail("sasuke@konaha.in");

			System.out.println("Updating the object...");

			// save the object in the memory
			session.saveOrUpdate(theStudent);

			// update all the specific column within the table
			// session.createQuery("update Student set lastName='Uchiha'").executeUpdate();
//			session.createQuery("update Student set lastName='kavtiyal' where email='adarshkavtiyal@gmail.com'")
//					.executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Id: " + theStudent.getId() + " have been updated.");

		} catch (Exception e) {
			System.out.println("An error occured. Cannot update or save the data !!");
			e.printStackTrace();
		} finally {
			System.out.println("Closing the factory session.");
			factory.close();
		}

	}

}
