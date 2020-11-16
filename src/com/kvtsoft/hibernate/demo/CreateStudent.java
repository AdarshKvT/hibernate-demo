package com.kvtsoft.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.demo.entity.Student;

public class CreateStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			Student firstObject = new Student("Sasuke", "KvT", "sasuke@gmail.com");
			Student secondObect = new Student("Kakashi", "KvT", "kakashi@gmail.com");
			Student thirdObject = new Student("Itachi", "KvT", "itachi@gmail.com");

			// create an object
			System.out.println("Creating new object...");

			// start a transmission
			session.beginTransaction();

			// save the student object
			session.save(firstObject);
			session.save(secondObect);
			session.save(thirdObject);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Object has been saved successfully");
			System.out.println("Saved object. Generated id: " + firstObject.getId());
			System.out.println("Saved object. Generated id: " + secondObect.getId());
			System.out.println("Saved object. Generated id: " + thirdObject.getId());

		} catch (Exception e) {
			System.out.println("An error occured. Cannot save the object !!");

			e.printStackTrace();
		} finally {
			System.out.println("Closing the factory connection");
			factory.close();
		}

	}

}

//Test firstObject = new Test("Kathryn Newton");
//Test secondObect = new Test("Elle Fanning");
//Test thirdObject = new Test("Tom Holland");