package com.kvtsoft.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.demo.entity.Student;

public class RetriveStudent {

	public static void main(String[] args) {

		int id = 1;

		// create session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {

			// start a transmission
			session.beginTransaction();

			// retrieve student object using primary key
			Student dbStudent = session.get(Student.class, id);

			if (dbStudent != null) {
				System.out.println("Object has been retrieved succesfully");
				System.out.println("dbStudent: " + dbStudent);

			} else {
				System.out.println("Object with id: " + id + " not found");
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("An error occured. Cannot retieve object !!");
		} finally {
			System.out.println("Closing the factory connection");
			factory.close();
		}

	}

}
