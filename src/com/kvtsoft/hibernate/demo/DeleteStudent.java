package com.kvtsoft.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.demo.entity.Student;

public class DeleteStudent {

	public static void main(String[] args) {

		int studentId = 5;

		// create session factory and a session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		try {

			Session session = factory.getCurrentSession();
			session.beginTransaction();

			System.out.println("Deleting the object...");
			int result = session.createSQLQuery("delete from Student where id= '" + studentId + "' ").executeUpdate();
			System.out.println("\nresult: " + result);

			// commit the transaction
			session.getTransaction().commit();

			if (result != 0) {
				System.out.println("Id: " + studentId + " has been deleted.");
			} else {
				System.out.println("Id: " + studentId + " not found. No deletion operation performed !!");
			}

		} catch (Exception e) {
			System.out.println("\nAn error occured. Cannot update or save the data !!");
			e.printStackTrace();
		} finally {
			System.out.println("\nClosing the factory session.");
			factory.close();
		}

	}

}
