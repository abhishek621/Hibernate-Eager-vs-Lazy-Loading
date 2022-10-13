package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create Session

		Session session = factory.getCurrentSession();

		// use the session object to save the java object

		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;
			Instructor tempInstrutor = session.get(Instructor.class, theId);

			System.out.println("luv2code: Instructor : " + tempInstrutor);
			
			System.out.println("luv2code: Courses : " + tempInstrutor.getCourses());

			// commit the transaction
			session.getTransaction().commit();
			
			//close the session for testing
			
			session.close();
			
			System.out.println("\nluv2code : The session is now closed!\n");
			
			// option 1 : call getter method while session is open
			
			//since the courses are lazy loaded... this will fail
			//get courses for the instructor
			System.out.println("luv2code: Courses : " + tempInstrutor.getCourses());

			System.out.println("luv2code: Done");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}

}
