package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create Session

		Session session = factory.getCurrentSession();

		// use the session object to save the java object

		try {
			// create the objects

			Instructor tempInstructor = new Instructor("Susan", "Public", "susan@luv2code.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/susan",
					"Video Games");

			// associate the objects

			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			// this will also save the instructor detail object because of casecadeType=ALL

			System.out.println("Saving Instructor : " + tempInstructor);
			session.save(tempInstructor);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done");
		} finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
