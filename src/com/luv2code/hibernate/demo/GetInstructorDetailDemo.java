package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		// create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create Session

		Session session = factory.getCurrentSession();

		// use the session object to save the java object

		try {

			// start a transaction
			session.beginTransaction();

			// get the Instructor Detail

			int theId = 1;

			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

			// print the Instructor Detail

			System.out.println("InstructorDetail : " + tempInstructorDetail);

			// print the associated instructor

			System.out.println("The Associated Instructor : " + tempInstructorDetail.getInstructor());

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
